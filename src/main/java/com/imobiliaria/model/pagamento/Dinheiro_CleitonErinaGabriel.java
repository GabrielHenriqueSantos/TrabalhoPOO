package com.imobiliaria.model.pagamento;

public class Dinheiro_CleitonErinaGabriel extends Pagamento_CleitonErinaGabriel{
    public Dinheiro_CleitonErinaGabriel(String tipoPagamento) {
        super(tipoPagamento);
    }

    @Override
    public String toString() {
        return "Pagamento por Dinheiro";
    }
}
