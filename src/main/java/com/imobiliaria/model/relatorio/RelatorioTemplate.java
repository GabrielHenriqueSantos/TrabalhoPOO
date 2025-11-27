package com.imobiliaria.model.relatorio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class RelatorioTemplate {
    protected final String titulo;

    public RelatorioTemplate(String titulo) {
        this.titulo = titulo;
    }

    public final String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append(gerarCabecalho());
        relatorio.append(gerarTitulo());
        relatorio.append(gerarConteudo());
        relatorio.append(gerarRodape());
        return relatorio.toString();
    }

    private String gerarCabecalho() {
        return "============================================\n" +
                "           IMOBILIÁRIA YEAGER\n" +
                "         Shiganshina, 660\n" +
                "============================================\n\n";
    }

    private String gerarTitulo() {
        return "RELATÓRIO: " + titulo.toUpperCase() + "\n" +
                "Data: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\n" +
                "--------------------------------------------\n\n";
    }

    private String gerarRodape() {
        return "\n--------------------------------------------\n" +
                "Relatório gerado automaticamente\n" +
                "============================================\n";
    }

    protected abstract String gerarConteudo();
}