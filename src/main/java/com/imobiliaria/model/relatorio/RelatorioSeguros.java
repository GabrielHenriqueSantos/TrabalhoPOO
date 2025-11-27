package com.imobiliaria.model.relatorio;

import com.imobiliaria.model.operacao.Seguro_CleitonErinaGabriel;

import java.util.List;

public class RelatorioSeguros extends RelatorioTemplate{
    private final List<Seguro_CleitonErinaGabriel> seguros;

    public RelatorioSeguros(List<Seguro_CleitonErinaGabriel> seguros){
        super("Seguros");
        this.seguros = seguros;
    }

    @Override
    protected String gerarConteudo() {
        if (seguros.isEmpty()) {
            return "Nenhum seguro cadastrado.\n";
        }

        StringBuilder conteudo = new StringBuilder();
        conteudo.append("Total de Seguros: ").append(seguros.size()).append("\n\n");

        for (Seguro_CleitonErinaGabriel seg : seguros) {
            conteudo.append(seg).append("\n");
            conteudo.append("--------------------------------------------\n");
        }

        return conteudo.toString();
    }
}
