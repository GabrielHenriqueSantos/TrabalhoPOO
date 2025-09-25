package com.imobiliaria.model.imovel;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Imovel_CleitonErinaGabriel implements Serializable {
    protected int codigoImovel;
    protected String endereco;
    protected LocalDate dataConstrucao;
    protected float areaTotal;
    protected float areaConstruida;
    protected int qtdDormitórios;
    protected int qtdBanheeiros;
    protected int qtdVagasGaragem;
    protected float valorIPTU;
    protected float valorVenda;
    protected float valorAluguel;

    public Imovel_CleitonErinaGabriel() {
    }

    public Imovel_CleitonErinaGabriel(int codigoImovel, String endereco, LocalDate dataConstrucao, float areaTotal, float areaConstruida, int qtdDormitórios, int qtdBanheeiros, int qtdVagasGaragem, float valorIPTU, float valorVenda, float valorAluguel) {
        this.codigoImovel = codigoImovel;
        this.endereco = endereco;
        this.dataConstrucao = dataConstrucao;
        this.areaTotal = areaTotal;
        this.areaConstruida = areaConstruida;
        this.qtdDormitórios = qtdDormitórios;
        this.qtdBanheeiros = qtdBanheeiros;
        this.qtdVagasGaragem = qtdVagasGaragem;
        this.valorIPTU = valorIPTU;
        this.valorVenda = valorVenda;
        this.valorAluguel = valorAluguel;
    }

    public String getDataConstrucao_String() {
        return dataConstrucao.toString();
    }

    @Override
    public String toString() {
        return "Imóvel nº"+codigoImovel+"\nEndereço: "+endereco+"\nData de construção: "+getDataConstrucao_String()+"\nÁrea Total: "
                +areaTotal+"m^2 Área Construida: "+areaConstruida+"\nQuantidade de Banheiros: "+qtdBanheeiros+"\nQuantidade de Dormitórios: "+qtdDormitórios
                +"\nVagas na Garagem: "+qtdVagasGaragem+"\nValor IPTU: "+valorIPTU+"\nValor Venda: "+valorVenda+"\nValor aluguel: "+valorAluguel;
    }
}
