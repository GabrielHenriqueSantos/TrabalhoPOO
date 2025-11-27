package com.imobiliaria.model.relatorio;

import com.imobiliaria.model.operacao.Aluguel_CleitonErinaGabriel;

import java.util.List;

public class RelatorioAlugueis extends RelatorioTemplate {
    private final List<Aluguel_CleitonErinaGabriel> alugueis;

    public RelatorioAlugueis(List<Aluguel_CleitonErinaGabriel> alugueis, String tipoRelatorio) {
        super("Relatório de Aluguéis - " + tipoRelatorio);
        this.alugueis = alugueis;
    }

    @Override
    protected String gerarConteudo() {
        if (alugueis.isEmpty()) {
            return "Nenhum aluguel encontrado.\n";
        }

        StringBuilder conteudo = new StringBuilder();
        conteudo.append("Total de aluguéis: ").append(alugueis.size()).append("\n\n");

        for (Aluguel_CleitonErinaGabriel aluguel : alugueis) {
            conteudo.append(aluguel).append("\n");
            conteudo.append("--------------------------------------------\n");
        }

        return conteudo.toString();
    }
}
