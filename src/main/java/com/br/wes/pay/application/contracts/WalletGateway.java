package com.br.wes.pay.application.contracts;

import com.br.wes.pay.domain.Wallet;

import java.util.Optional;

public interface WalletGateway {

    Wallet save(Wallet wallet);
    Optional<Wallet> findById(Long id);
    Wallet update(Wallet wallet);
}
