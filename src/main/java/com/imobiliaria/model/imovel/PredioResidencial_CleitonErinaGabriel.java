package com.imobiliaria.model.imovel;

import java.time.LocalDate;

public class PredioResidencial_CleitonErinaGabriel extends Imovel_CleitonErinaGabriel{
    private int andar;
    private int numApto;
    private float valorCondominio;

    public PredioResidencial_CleitonErinaGabriel(int codigoImovel, String endereco, LocalDate dataConstrucao, float areaTotal, float areaConstruida, int qtdDormitórios, int qtdBanheeiros, int qtdVagasGaragem, float valorIPTU, float valorVenda, float valorAluguel, int andar, int numApto, float valorCondominio) {
        super(codigoImovel, endereco, dataConstrucao, areaTotal, areaConstruida, qtdDormitórios, qtdBanheeiros, qtdVagasGaragem, valorIPTU, valorVenda, valorAluguel);
        this.andar = andar;
        this.numApto = numApto;
        this.valorCondominio = valorCondominio;
    }

    public int getNumApto() {
        return numApto;
    }

    public int getAndar() {
        return andar;
    }

    public float getValorCondominio() {
        return valorCondominio;
    }

    public float getValorAluguel(){
        return super.valorAluguel + valorCondominio;
    }

    @Override
    public String toString() {
        return "Apartamento" + super.toString()+"\nAndar: "+andar
                +"Número Apartamento: "+numApto+"\nValor Condomínio: "+valorCondominio;
    }
}
