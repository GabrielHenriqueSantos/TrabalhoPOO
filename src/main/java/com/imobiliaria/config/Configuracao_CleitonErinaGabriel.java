package com.imobiliaria.config;

import java.time.LocalDate;

public class Configuracao_CleitonErinaGabriel {

    private String arquivoAlugueis;
    private String arquivoVendas;
    private String arquivoImoveis;
    private String arquivoClientes;
    private String arquivoCorretores;
    private String arquivoSeguros;
    //bonificação do valor do aluguel pra pagamento dentro do prazo
    private float taxaBonificacao;

    //o sistema mais simples pra atribuir código às classes
    private int geraCodigoAluguel = 0;
    private int geraCodigoVenda = 0;
    private int geraCodigoSeguro = 0;
    private int geraCodigoUsuario = 0;
    private int geraCodigoImovel = 0;

    //data de hoje pra poder verificar o vencimento do aluguel
    private LocalDate dataDeHoje = LocalDate.now();

    public Configuracao_CleitonErinaGabriel(){
        //carregamento dos arquivos vai aqui
    }

    public Configuracao_CleitonErinaGabriel(float taxaBonificacao){
        //outra
        this.taxaBonificacao = taxaBonificacao;
    }

    public void salvarConfiguracao(){

    }

    public String getArquivoAlugueis() {
        return arquivoAlugueis;
    }

    public void setArquivoAlugueis(String arquivoAlugueis) {
        this.arquivoAlugueis = arquivoAlugueis;
    }

    public String getArquivoVendas() {
        return arquivoVendas;
    }

    public void setArquivoVendas(String arquivoVendas) {
        this.arquivoVendas = arquivoVendas;
    }

    public String getArquivoImoveis() {
        return arquivoImoveis;
    }

    public void setArquivoImoveis(String arquivoImoveis) {
        this.arquivoImoveis = arquivoImoveis;
    }

    public String getArquivoClientes() {
        return arquivoClientes;
    }

    public void setArquivoClientes(String arquivoClientes) {
        this.arquivoClientes = arquivoClientes;
    }

    public String getArquivoCorretores() {
        return arquivoCorretores;
    }

    public void setArquivoCorretores(String arquivoCorretores) {
        this.arquivoCorretores = arquivoCorretores;
    }

    public String getArquivoSeguros() {
        return arquivoSeguros;
    }

    public void setArquivoSeguros(String arquivoSeguros) {
        this.arquivoSeguros = arquivoSeguros;
    }

    public float getTaxaBonificacao() {
        return taxaBonificacao;
    }

    public int getGeraCodigoAluguel() {
        return geraCodigoAluguel;
    }

    public void setGeraCodigoAluguel(int geraCodigoAluguel) {
        this.geraCodigoAluguel = geraCodigoAluguel;
    }

    public int getGeraCodigoVenda() {
        return geraCodigoVenda;
    }

    public void setGeraCodigoVenda(int geraCodigoVenda) {
        this.geraCodigoVenda = geraCodigoVenda;
    }

    public int getGeraCodigoSeguro() {
        return geraCodigoSeguro;
    }

    public void setGeraCodigoSeguro(int geraCodigoSeguro) {
        this.geraCodigoSeguro = geraCodigoSeguro;
    }

    public int getGeraCodigoUsuario() {
        return geraCodigoUsuario;
    }

    public void setGeraCodigoUsuario(int geraCodigoUsuario) {
        this.geraCodigoUsuario = geraCodigoUsuario;
    }

    public int getGeraCodigoImovel() {
        return geraCodigoImovel;
    }

    public void setGeraCodigoImovel(int geraCodigoImovel) {
        this.geraCodigoImovel = geraCodigoImovel;
    }

    public LocalDate getDataDeHoje() {
        return dataDeHoje;
    }

    public void setDataDeHoje(LocalDate dataDeHoje) {
        this.dataDeHoje = dataDeHoje;
    }
}