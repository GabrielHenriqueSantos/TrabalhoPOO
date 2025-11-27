package com.imobiliaria.model.relatorio;

import com.imobiliaria.model.imovel.Imovel_CleitonErinaGabriel;

import java.util.List;

public class RelatorioListaImoveis extends RelatorioTemplate {
    private final List<Imovel_CleitonErinaGabriel> imoveis;
    private final String filtro;

    public RelatorioListaImoveis(List<Imovel_CleitonErinaGabriel> imoveis, String filtro) {
        super("Lista de Imóveis - " + filtro);
        this.imoveis = imoveis;
        this.filtro = filtro;
    }

    @Override
    protected String gerarConteudo() {
        if (imoveis.isEmpty()) {
            return "Nenhum imóvel encontrado para o filtro: " + filtro + "\n";
        }

        StringBuilder conteudo = new StringBuilder();
        conteudo.append("Total de imóveis: ").append(imoveis.size()).append("\n\n");

        for (Imovel_CleitonErinaGabriel imovel : imoveis) {
            conteudo.append(imovel).append("\n");
            conteudo.append("--------------------------------------------\n");
        }
        return conteudo.toString();
    }
}
