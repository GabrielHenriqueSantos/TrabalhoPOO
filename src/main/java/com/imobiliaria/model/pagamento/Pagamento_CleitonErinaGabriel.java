package com.imobiliaria.model.pagamento;

import java.io.Serializable;

public abstract class Pagamento_CleitonErinaGabriel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tipoPagamento;

    public Pagamento_CleitonErinaGabriel(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    @Override
    public abstract String toString();
}
