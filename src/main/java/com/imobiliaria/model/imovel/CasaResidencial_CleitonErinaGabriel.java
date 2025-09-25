package com.imobiliaria.model.imovel;

import java.time.LocalDate;

public class CasaResidencial_CleitonErinaGabriel extends Imovel_CleitonErinaGabriel{
    public CasaResidencial_CleitonErinaGabriel() {
    }

    public CasaResidencial_CleitonErinaGabriel(int codigoImovel, String endereco, LocalDate dataConstrucao, float areaTotal, float areaConstruida, int qtdDormitórios, int qtdBanheeiros, int qtdVagasGaragem, float valorIPTU, float valorVenda, float valorAluguel) {
        super(codigoImovel, endereco, dataConstrucao, areaTotal, areaConstruida, qtdDormitórios, qtdBanheeiros, qtdVagasGaragem, valorIPTU, valorVenda, valorAluguel);
    }
}
