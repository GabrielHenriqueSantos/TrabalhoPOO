package com.imobiliaria.model.imovel;

import java.time.LocalDate;

public class Comercial_CleitonErinaGabriel extends Imovel_CleitonErinaGabriel{
    private float taxaImpostoFederal;

    public Comercial_CleitonErinaGabriel(String endereco, LocalDate dataConstrucao, float areaTotal, float areaConstruida, int qtdDormitórios, int qtdBanheiros, int qtdVagasGaragem, float valorIPTU, float valorVenda, float valorAluguel, boolean disponivel, Operacao tipoOperacao, float taxaImpostoFederal) {
        super("CM", endereco, dataConstrucao, areaTotal, areaConstruida, qtdDormitórios, qtdBanheiros, qtdVagasGaragem, valorIPTU, valorVenda, valorAluguel, disponivel, tipoOperacao);
        this.taxaImpostoFederal = taxaImpostoFederal;
    }

    public float getValorAluguel(){
        return super.valorAluguel * taxaImpostoFederal;
    }

    public String toString(){
        return "Prédio comercial\n"+super.toString()+"\n";
    }
}
