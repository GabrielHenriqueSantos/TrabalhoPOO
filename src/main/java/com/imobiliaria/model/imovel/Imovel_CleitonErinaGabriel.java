package com.imobiliaria.model.imovel;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Imovel_CleitonErinaGabriel implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int proxCod = 1;
    protected String codigoImovel;
    protected String endereco;
    protected LocalDate dataConstrucao;
    protected float areaTotal;
    protected float areaConstruida;
    protected int qtdDormitórios;
    protected int qtdBanheiros;
    protected int qtdVagasGaragem;
    protected float valorIPTU;
    protected float valorVenda;
    protected float valorAluguel;
    protected boolean disponivel;
    protected Operacao tipoOperacao;

    public Imovel_CleitonErinaGabriel() {
    }

    public Imovel_CleitonErinaGabriel(String cod, String endereco, LocalDate dataConstrucao, float areaTotal, float areaConstruida, int qtdDormitórios, int qtdBanheiros, int qtdVagasGaragem, float valorIPTU, float valorVenda, float valorAluguel, Operacao tipoOperacao) {
        this.codigoImovel = proxCod++ + cod;
        this.endereco = endereco;
        this.dataConstrucao = dataConstrucao;
        this.areaTotal = areaTotal;
        this.areaConstruida = areaConstruida;
        this.qtdDormitórios = qtdDormitórios;
        this.qtdBanheiros = qtdBanheiros;
        this.qtdVagasGaragem = qtdVagasGaragem;
        this.valorIPTU = valorIPTU;
        this.valorVenda = valorVenda;
        this.valorAluguel = valorAluguel;
        this.disponivel = true;
        this.tipoOperacao = tipoOperacao;
    }

    public String getCodigoImovel(){
        return codigoImovel;
    }

    public String getDataConstrucao_String() {
        return dataConstrucao.toString();
    }

    public float getValorAluguel() {
        return valorAluguel;
    }

    public Operacao getTipoOperacao() {
        return tipoOperacao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel){
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Imóvel nº"+codigoImovel
                +"\nEndereço: "+endereco
                +"\nData de construção: "+getDataConstrucao_String()
                +"\nÁrea Total: " +areaTotal+"m^2 Área Construida: "+areaConstruida
                +"\nQuantidade de Banheiros: "+ qtdBanheiros
                +"\nQuantidade de Dormitórios: "+qtdDormitórios
                +"\nVagas na Garagem: "+qtdVagasGaragem
                +"\nValor IPTU: "+valorIPTU
                +((tipoOperacao == Operacao.VENDA)?"\nValor Venda: "+valorVenda:"\nValor aluguel base: "+getValorAluguel());
    }
}
