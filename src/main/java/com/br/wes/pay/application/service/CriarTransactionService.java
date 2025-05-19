package com.br.wes.pay.application.service;


import com.br.wes.pay.application.contracts.AuthorizerGateway;
import com.br.wes.pay.application.contracts.TransactionGateway;
import com.br.wes.pay.application.contracts.WalletGateway;
import com.br.wes.pay.application.service.exception.UnauthorizedTransactionException;
import com.br.wes.pay.domain.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarTransactionService {
    private final TransactionGateway transactionGateway;
    private final WalletGateway walletRepository;
    private final NotificationService notificationService;
    private final AuthorizerGateway authorizedService;
    private final TrasactionValidator transactionValidator;

    public CriarTransactionService(TransactionGateway transactionGateway,
                                   WalletGateway walletRepository,
                                   AuthorizerGateway authorizedService,
                                   NotificationService notificationService,
                                   TrasactionValidator transactionValidator) {
        this.transactionGateway = transactionGateway;
        this.walletRepository = walletRepository;
        this.authorizedService = authorizedService;
        this.notificationService = notificationService;
        this.transactionValidator = transactionValidator;
    }

    @Transactional
    public Transaction create(Transaction transaction) {
        // 1 validar
        transactionValidator.validate(transaction);

        // 2 chamar serviços externos
        // authorize transaction
        var authorization = authorizedService.authorize(transaction);

        // 2 criar a transação
        var newTransaction = transactionGateway.save(transaction);


        // 3 debitar da carteira
        var walletPayer = walletRepository.findById(transaction.payer()).get();
        var walletPayee = walletRepository.findById(transaction.payee()).get();

        walletRepository.update(walletPayer.debit(transaction.amount()));
        walletRepository.update(walletPayee.credit(transaction.amount()));


        if (!authorization.isAuthorized()){
            throw new UnauthorizedTransactionException("Transaction not authorized");
        }

        //notificação
        notificationService.notify(transaction);
        return newTransaction;
    }
}
