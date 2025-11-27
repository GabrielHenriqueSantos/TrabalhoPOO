package com.imobiliaria.service;

import com.imobiliaria.model.Imobiliaria_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Imovel_CleitonErinaGabriel;
import com.imobiliaria.model.operacao.Aluguel_CleitonErinaGabriel;
import com.imobiliaria.model.operacao.Seguro_CleitonErinaGabriel;
import com.imobiliaria.model.operacao.Transacao;
import com.imobiliaria.model.operacao.Venda_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Cliente_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Corretor_CleitonErinaGabriel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransacaoService {
    private Imobiliaria_CleitonErinaGabriel imobiliaria;
    private Transacao transacao;
    private PagamentoRequest request;

    public TransacaoService(){
        imobiliaria = Imobiliaria_CleitonErinaGabriel.getInstance();
    }

    public PagamentoRequest getRequest(){
        return request;
    }

    public void setPagamento(String tipoPagamento, String nome, String bandeira, String numero){
        transacao.setFormaPagamento(imobiliaria.novoPagamento(tipoPagamento,nome,bandeira,numero));
    }

    public void setPagamento(String tipoPagamento){
        transacao.setFormaPagamento(imobiliaria.novoPagamento(tipoPagamento));
    }

    public boolean novaVenda(String metodo,String codCliente, String codCorretor, String codImovel){
        Cliente_CleitonErinaGabriel cliente = imobiliaria.buscaCliente(codCliente);
        Corretor_CleitonErinaGabriel corretor = imobiliaria.buscaCorretor(codCorretor);
        Imovel_CleitonErinaGabriel imovel = imobiliaria.buscaImovel(codImovel);
        if (cliente == null || corretor == null || imovel == null)
            return false;
        imovel.setDisponivel(false);
        float valor = imovel.getValorVenda();
        Venda_CleitonErinaGabriel venda = new Venda_CleitonErinaGabriel.Builder()
                .cliente(cliente).corretor(corretor).valorTotalVenda(valor).imovel(imovel).build();
        transacao = venda;
        request = new PagamentoRequest(metodo,valor);
        return imobiliaria.novaVenda(venda);
    }

    public boolean novoAluguel(String metodo, String codCliente, String codCorretor, String codImovel, LocalDate dataDevolucao, LocalDate dataPagamentoMensal, List<String> segurosContratados){
        Cliente_CleitonErinaGabriel cliente = imobiliaria.buscaCliente(codCliente);
        Corretor_CleitonErinaGabriel corretor = imobiliaria.buscaCorretor(codCorretor);
        Imovel_CleitonErinaGabriel imovel = imobiliaria.buscaImovel(codImovel);
        if (cliente == null || corretor == null || imovel == null)
            return false;
        imovel.setDisponivel(false);
        ArrayList<Seguro_CleitonErinaGabriel> segAl = imobiliaria.buscaSeguros(segurosContratados);
        Aluguel_CleitonErinaGabriel aluguel = new Aluguel_CleitonErinaGabriel.Builder()
                .cliente(cliente)
                .corretor(corretor)
                .imovel(imovel)
                .dataDevolucao(dataDevolucao)
                .dataPagamentoMensal(dataPagamentoMensal)
                .segurosContratados(segAl).build();
        transacao = aluguel;
        request = new PagamentoRequest(metodo,aluguel.getValorTotalAluguel());
        return true;
    }

    public record PagamentoRequest(String metodo, float valor){}
}
