package com.imobiliaria.model.pagamento;

public class Dinheiro_CleitonErinaGabriel extends Pagamento_CleitonErinaGabriel{
    private static final long serialVersionUID = 1L;
    public Dinheiro_CleitonErinaGabriel(String tipoPagamento) {
        super(tipoPagamento);
    }

    @Override
    public String toString() {
        return "Pagamento por Dinheiro";
    }
}
