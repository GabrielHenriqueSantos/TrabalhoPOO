package com.imobiliaria.controller;

import com.imobiliaria.config.Configuracao_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.*;
import com.imobiliaria.model.operacao.Aluguel_CleitonErinaGabriel;
import com.imobiliaria.model.operacao.Seguro_CleitonErinaGabriel;
import com.imobiliaria.model.operacao.Venda_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Usuario_CleitonErinaGabriel;

import java.time.LocalDate;
import java.util.ArrayList;

public class Imobiliaria_CleitonErinaGabriel {
    private String nome;
    private String endereco;
    private ArrayList<Aluguel_CleitonErinaGabriel> alugueis;
    private ArrayList<Venda_CleitonErinaGabriel> vendas;
    private ArrayList<Imovel_CleitonErinaGabriel> imoveis;
    //Revisar
    private ArrayList<Usuario_CleitonErinaGabriel> clientes;
    private ArrayList<Usuario_CleitonErinaGabriel> corretores;

    private ArrayList<Seguro_CleitonErinaGabriel> seguros;
    private final Configuracao_CleitonErinaGabriel configuracoes = new Configuracao_CleitonErinaGabriel();


    public Imobiliaria_CleitonErinaGabriel() {
    }

    public Imobiliaria_CleitonErinaGabriel(String nome, String endereco, ArrayList<Aluguel_CleitonErinaGabriel> alugueis, ArrayList<Venda_CleitonErinaGabriel> vendas, ArrayList<Imovel_CleitonErinaGabriel> imoveis, ArrayList<Usuario_CleitonErinaGabriel> clientes, ArrayList<Usuario_CleitonErinaGabriel> corretores, ArrayList<Seguro_CleitonErinaGabriel> seguros) {
        this.nome = nome;
        this.endereco = endereco;
        this.alugueis = alugueis;
        this.vendas = vendas;
        this.imoveis = imoveis;
        this.clientes = clientes;
        this.corretores = corretores;
        this.seguros = seguros;
    }

    public String listarImoveis(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis cadastrados:\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            sb.append(i.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public String listarCasaResidencial(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todas as casa residenciais cadastradas:\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i instanceof CasaResidencial_CleitonErinaGabriel) {
                sb.append(i.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String listarPredioResidencial(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todas as casa residenciais cadastradas:\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i instanceof PredioResidencial_CleitonErinaGabriel) {
                sb.append(i.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String listarImoveisComerciais(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todas as casa residenciais cadastradas:\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i instanceof Comercial_CleitonErinaGabriel) {
                sb.append(i.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String listarImoveisLocacao(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis disponiveis para locação\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i.getTipoOperacao() == Operacao.ALUGUEL && i.isDisponivel()){
                sb.append(i.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String listarImoveisAlugados(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis alugados\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i.getTipoOperacao() == Operacao.ALUGUEL && !i.isDisponivel()){
                sb.append(i.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String listarImoveisAVenda(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis disponiveis para venda\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i.getTipoOperacao() == Operacao.VENDA && i.isDisponivel()){
                sb.append(i.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String listarImoveisVendidos(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis vendidos\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i.getTipoOperacao() == Operacao.VENDA && !i.isDisponivel()){
                sb.append(i.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String listarAlugueisAtrasados(){
        StringBuilder sb = new StringBuilder();
        for(Aluguel_CleitonErinaGabriel aluguel: alugueis){
            if(!aluguel.isPago() && aluguel.getDataPagamentoMensal() < LocalDate.now().getDayOfMonth()){
                sb.append(aluguel.toString());
            }
        }
        return sb.toString();
    }


/*
            TODO
             ✓ Listar todos os Imóveis já alugados por um Cliente em específico.
             ✓ Listar todos os Imóveis já comprados por um Cliente em específico.
             ✓ Listar todos os Corretores cadastrados.
             ✓ Listar todos os Clientes cadastrados.
             ✓ Listar Clientes com aluguéis em atraso.
             ✓ Listar todos os Aluguéis finalizados, ou seja, que já concluíram contrato e foram devolvidos.
             ✓ Listar todos os Aluguéis ainda dentro do prazo de locação.
             ✓ Listar todas as Vendas realizadas.
             ✓ Listar Vendas realizadas em um mês em específico e o total de lucro gerado no mês.
             ✓ Listar todos os tipos de Seguros cadastrados
 */

}
