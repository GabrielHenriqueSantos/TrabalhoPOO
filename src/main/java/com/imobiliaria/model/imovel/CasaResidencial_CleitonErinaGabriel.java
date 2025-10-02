package com.imobiliaria.model.imovel;

import java.time.LocalDate;

public class CasaResidencial_CleitonErinaGabriel extends Imovel_CleitonErinaGabriel{
    private static final long serialVersionUID = 1L;
    public CasaResidencial_CleitonErinaGabriel() {
    }

    public CasaResidencial_CleitonErinaGabriel(String endereco, LocalDate dataConstrucao, float areaTotal, float areaConstruida, int qtdDormitórios, int qtdBanheiros, int qtdVagasGaragem, float valorIPTU, float valorVenda, float valorAluguel, Operacao tipoOperacao) {
        super("CR", endereco, dataConstrucao, areaTotal, areaConstruida, qtdDormitórios, qtdBanheiros, qtdVagasGaragem, valorIPTU, valorVenda, valorAluguel, tipoOperacao);
    }
}
