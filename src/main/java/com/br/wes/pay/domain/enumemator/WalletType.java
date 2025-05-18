package com.br.wes.pay.domain.enumemator;

public enum WalletType {
    COMUM (1), LOJISTA (2);

    private int value;

    private WalletType(int type){
        this.value = value;
    }


    public int getValue(){
        return value;
    }
}
