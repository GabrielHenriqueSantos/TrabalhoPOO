package com.imobiliaria.controller;

import com.imobiliaria.config.Configuracao_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.*;
import com.imobiliaria.model.operacao.Aluguel_CleitonErinaGabriel;
import com.imobiliaria.model.operacao.Seguro_CleitonErinaGabriel;
import com.imobiliaria.model.operacao.Venda_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Cliente_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Corretor_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Usuario_CleitonErinaGabriel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.TransferQueue;

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



    public Imobiliaria_CleitonErinaGabriel(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.alugueis = new ArrayList<>();
        this.vendas = new ArrayList<>();
        this.imoveis = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.corretores = new ArrayList<>();
        this.seguros = new ArrayList<>();
    }

    public boolean novoCliente(String cpf, String nome, String rg, LocalDate dataNascimento, String endereco, String cep, String telefone, String email){
        Cliente_CleitonErinaGabriel novoCliente = new Cliente_CleitonErinaGabriel(cpf, nome, rg, dataNascimento, endereco, cep, telefone, email);
        clientes.add(novoCliente);
        return true;
    }

    public String listarImoveis(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis cadastrados:\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            sb.append(i).append("\n");
            sb.append("\n");
        }
        return sb.toString();
    }

    public String listarCasaResidencial(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todas as casa residenciais cadastradas:\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i instanceof CasaResidencial_CleitonErinaGabriel) {
                sb.append(i).append("\n");
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
                sb.append(i).append("\n");
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
                sb.append(i).append("\n");
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
                sb.append(i).append("\n");
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
                sb.append(i).append("\n");
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
                sb.append(i).append("\n");
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
                sb.append(i).append("\n");
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String listarAlugueisAtrasados(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis com Aluguel atrasado\n");
        for(Aluguel_CleitonErinaGabriel aluguel: alugueis){
            if(!aluguel.isPago() && aluguel.getDataPagamentoMensal().getDayOfMonth() < LocalDate.now().getDayOfMonth()){
                sb.append(aluguel).append("\n");
            }
        }
        return sb.toString();
    }

    public String listarImoveisAlugadosCliente(String codCliente){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis alugados ao Cliente :").append(codCliente).append("\n");
        for(Aluguel_CleitonErinaGabriel aluguel:alugueis){
            if(aluguel.getCliente().getCodigoUsuario().equals(codCliente)){
                sb.append(aluguel).append("\n");
            }
        }
        return sb.toString();
    }

    public String listarImoveisVendidosCliente(String codCliente){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis vendidos ao Cliente :").append(codCliente).append("\n");
        for(Aluguel_CleitonErinaGabriel aluguel:alugueis){
            if(aluguel.getCliente().getCodigoUsuario().equals(codCliente)){
                sb.append(aluguel).append("\n");
            }
        }
        return sb.toString();
    }

    public String listarTodosCorretores(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos os Corretores:\n");
        for(Usuario_CleitonErinaGabriel corretor:corretores){
            sb.append(corretor).append("\n");
        }
        return sb.toString();
    }

    public String listarTodosClientes(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos os Corretores:\n");
        for(Usuario_CleitonErinaGabriel corretor:corretores){
            sb.append(corretor).append("\n");
        }
        return sb.toString();
    }

    public String listarAlugueisAtrasadosClientes(){
        StringBuilder sb = new StringBuilder();
        Cliente_CleitonErinaGabriel cl;
        sb.append("Todos Clientes com Aluguel atrasado\n");
        for(Aluguel_CleitonErinaGabriel aluguel: alugueis){
            if(!aluguel.isPago() && aluguel.getDataPagamentoMensal().getDayOfMonth() < LocalDate.now().getDayOfMonth()){
                cl = aluguel.getCliente();
                sb.append("Código: ").append(cl.getCodigoUsuario()).append("\n").append(cl.getNome()).append("\n");
            }
        }
        return sb.toString();
    }

    public String listaAlugueisFinalizados(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Alugueis Finalizados\n");
        for(Aluguel_CleitonErinaGabriel aluguel:alugueis){
            if(aluguel.isFinalizado()){
                sb.append(aluguel).append("\n");
            }
        }
        return sb.toString();
    }

    public String listaAlugueisAtivos(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Alugueis ativos\n");
        for(Aluguel_CleitonErinaGabriel aluguel:alugueis){
            if(!aluguel.isFinalizado()){
                sb.append(aluguel).append("\n");
            }
        }
        return sb.toString();
    }

    public String listaVendas(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todas as Vendas:\n");
        for(Venda_CleitonErinaGabriel venda:vendas){
            sb.append(venda);
        }
        return sb.toString();
    }


    public String vendaPorMes(LocalDate fimMes){
        StringBuilder sb = new StringBuilder();
        LocalDate inicioMes = fimMes.minusDays(30);
        float totalLucro = 0;
        sb.append("Todas Vendas Realizados no mês\n").append(inicioMes).append(" até ").append(fimMes).append("\n");
        for(Venda_CleitonErinaGabriel venda:vendas){
            if(venda.getDataVenda().isAfter(inicioMes) && (venda.getDataVenda().isEqual(fimMes)||venda.getDataVenda().isBefore(fimMes))){
                totalLucro += venda.getValorTotalVenda();
                sb.append(venda).append("\n");
            }
        }
        sb.append("Lucro no periodo: ").append(totalLucro).append("\n");
        return sb.toString();
    }

    public String todosSeguros(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos seguros cadastrados: \n");
        for(Seguro_CleitonErinaGabriel seguro:seguros){
            sb.append(seguro).append("\n");
        }
        return sb.toString();
    }



}
