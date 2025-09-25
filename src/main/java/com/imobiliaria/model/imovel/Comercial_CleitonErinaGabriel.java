package com.imobiliaria.model.imovel;

import java.time.LocalDate;

public class Comercial_CleitonErinaGabriel extends Imovel_CleitonErinaGabriel{
    private float taxaImpostoFederal;

    public Comercial_CleitonErinaGabriel(int codigoImovel, String endereco, LocalDate dataConstrucao, float areaTotal, float areaConstruida, int qtdDormitórios, int qtdBanheeiros, int qtdVagasGaragem, float valorIPTU, float valorVenda, float valorAluguel, float taxaImpostoFederal) {
        super(codigoImovel, endereco, dataConstrucao, areaTotal, areaConstruida, qtdDormitórios, qtdBanheeiros, qtdVagasGaragem, valorIPTU, valorVenda, valorAluguel);
        this.taxaImpostoFederal = taxaImpostoFederal;
    }

    public float getValorAluguel(){
        return super.valorAluguel * taxaImpostoFederal;
    }

    public String toString(){
        return "Prédio comercial\n"+super.toString()+"\n";
    }
}
