package com.br.wes.pay.application.contracts;

import com.br.wes.pay.domain.Transaction;

public interface ConsultarTrasactionGateway {
    
    Transaction findById(Integer id);
    Transaction listByPayee();
}
