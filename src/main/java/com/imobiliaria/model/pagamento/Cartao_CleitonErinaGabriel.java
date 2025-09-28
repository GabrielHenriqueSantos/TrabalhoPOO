package com.imobiliaria.model.pagamento;

public class Cartao_CleitonErinaGabriel extends Pagamento_CleitonErinaGabriel{
    private String nome;
    private String bandeira;
    private String numero;

    public Cartao_CleitonErinaGabriel(String tipoPagamento, String nome, String bandeira, String numero) {
        super(tipoPagamento);
        this.nome = nome;
        this.bandeira = bandeira;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public String getBandeira() {
        return bandeira;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "Pagamento em Cartão: " + toString() + "\nNome: " + nome
        + "\nBandeira: " + bandeira + "\nNúmero: " + numero;
    }
}
