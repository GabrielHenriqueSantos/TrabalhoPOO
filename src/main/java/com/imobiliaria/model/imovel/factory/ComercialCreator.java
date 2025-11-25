package com.imobiliaria.model.imovel.factory;

import com.imobiliaria.model.imovel.CasaResidencial_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Comercial_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Imovel_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Operacao;

import java.time.LocalDate;
import java.util.Map;

public class ComercialCreator implements ImovelCreator{

    @Override
    public Imovel_CleitonErinaGabriel criar(Map<String, Object> data) {
        String endereco = (String) data.get("endereco");
        LocalDate dataConstrucao = (LocalDate) data.get("dataContrucao");
        float areaTotal = (float) data.get("areaTotal");
        float areaConstruida = (float) data.get("areaConstruida");
        int qtdDormitorios = (int) data.get("qtdDormitorios");
        int qtdBanheiros = (int) data.get("qtdBanheiros");
        int qtdVagasGaragem = (int) data.get("qtdVagasGaragem");
        float valorIPTU = (float) data.get("valorIPTU");
        float valorVenda = (float) data.get("valorVenda");
        float valorAluguel = (float) data.get("valorAluguel");
        Operacao tipoOperacao = (Operacao) data.get("tipoOperacao");
        float taxaImpostoFederal = (float) data.get("taxaImpostoFederal");

        return new Comercial_CleitonErinaGabriel(
                endereco,
                dataConstrucao, areaTotal, areaConstruida,
                qtdDormitorios, qtdBanheiros, qtdVagasGaragem,
                valorIPTU, valorVenda, valorAluguel, tipoOperacao,taxaImpostoFederal
        );
    }
}
