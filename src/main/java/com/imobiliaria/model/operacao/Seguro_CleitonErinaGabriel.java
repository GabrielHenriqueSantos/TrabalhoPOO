package com.imobiliaria.model.operacao;

import java.io.Serializable;

public class Seguro_CleitonErinaGabriel implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int proxCod = 1;
    private String codigoSeguro;
    private String nomeSeguradora;
    private String tipo;
    private String descricao;
    private float valor;

    @Override
    public String toString(){
        return codigoSeguro + "\n"
        + nomeSeguradora + "\n"
        + tipo + "\n"
        + descricao + "\n"
        + valor + "\n";
    }

    //construtores


    public Seguro_CleitonErinaGabriel(String nomeSeguradora, String tipo, String descricao, float valor) {
        this.codigoSeguro = (proxCod++) + "SEG";
        this.nomeSeguradora = nomeSeguradora;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
    }

    //getters e setters
    public String getCodigoSeguro() {
        return codigoSeguro;
    }

    public String getNomeSeguradora() {
        return nomeSeguradora;
    }

    public void setNomeSeguradora(String nomeSeguradora) {
        this.nomeSeguradora = nomeSeguradora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
