package com.imobiliaria.config;

public class Configuracao_CleitonErinaGabriel {
    private static final String arquivoAlugueis = "/data/alugueis.db";
    private static final String arquivoVendas = "/data/vendas.db";
    private static final String srquivoImoveis = "/data/imoveis.db";
    private static final String arquivoClientes = "/data/clientes.db";
    private static final String arquivoCorretores = "/data/corretores.db";
    private static final String arqquivoSeguros = "/data/seguros.db";

    public Configuracao_CleitonErinaGabriel() {
    }

    public String getArquivoAlugueis() {
        return arquivoAlugueis;
    }

    public String getArquivoVendas() {
        return arquivoVendas;
    }

    public String getSrquivoImoveis() {
        return srquivoImoveis;
    }

    public String getArquivoClientes() {
        return arquivoClientes;
    }

    public String getArquivoCorretores() {
        return arquivoCorretores;
    }

    public String getArqquivoSeguros() {
        return arqquivoSeguros;
    }
}
