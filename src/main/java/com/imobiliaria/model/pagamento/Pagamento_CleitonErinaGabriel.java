package com.imobiliaria.model.pagamento;

public abstract class Pagamento_CleitonErinaGabriel {
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
