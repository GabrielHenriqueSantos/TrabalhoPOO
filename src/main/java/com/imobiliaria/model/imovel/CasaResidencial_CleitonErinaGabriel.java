package com.imobiliaria.model.imovel;

import java.time.LocalDate;

public class CasaResidencial_CleitonErinaGabriel extends Imovel_CleitonErinaGabriel{
    public CasaResidencial_CleitonErinaGabriel() {
    }

    public CasaResidencial_CleitonErinaGabriel(int codigoImovel, String endereco, LocalDate dataConstrucao, float areaTotal, float areaConstruida, int qtdDormitórios, int qtdBanheiros, int qtdVagasGaragem, float valorIPTU, float valorVenda, float valorAluguel, boolean disponivel, Operacao tipoOperacao) {
        super("CR", endereco, dataConstrucao, areaTotal, areaConstruida, qtdDormitórios, qtdBanheiros, qtdVagasGaragem, valorIPTU, valorVenda, valorAluguel, disponivel, tipoOperacao);
    }
}
