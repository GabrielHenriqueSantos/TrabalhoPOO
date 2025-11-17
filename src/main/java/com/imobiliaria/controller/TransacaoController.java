package com.imobiliaria.controller;

import com.imobiliaria.model.Imobiliaria_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Imovel_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Operacao;
import com.imobiliaria.model.operacao.Aluguel_CleitonErinaGabriel;
import com.imobiliaria.model.operacao.Venda_CleitonErinaGabriel;
import com.imobiliaria.model.pagamento.Pagamento_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Cliente_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Corretor_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Usuario_CleitonErinaGabriel;
import com.imobiliaria.view.operacao.JanelaPagamento;
import javafx.stage.Modality;

import java.time.LocalDate;
import java.util.List;

public class TransacaoController {
    private final Imobiliaria_CleitonErinaGabriel imobiliaria;
    private Pagamento_CleitonErinaGabriel pagamento;
    private final List<String> codImoveisVenda;
    private final List<String> codImoveisAluguel;
    private final List<String> codClientes;
    private final List<String> codCorretores;
    private Runnable onFecharForm;

    public TransacaoController(){
        this.imobiliaria = Imobiliaria_CleitonErinaGabriel.getInstance();
        this.codImoveisVenda = imobiliaria.getCodigosImoveisDisp(Operacao.VENDA);
        this.codImoveisAluguel = imobiliaria.getCodigosImoveisDisp(Operacao.ALUGUEL);
        this.codClientes = imobiliaria.getCodigosClientes();
        this.codCorretores = imobiliaria.getCodigosCorretores();
    }

    public List<String> getCodCorretores() {
        return codCorretores;
    }

    public List<String> getCodClientes() {
        return codClientes;
    }

    public List<String> getCodImoveisVenda() {
        return codImoveisVenda;
    }

    public List<String> getCodImoveisAluguel() {
        return codImoveisAluguel;
    }

    public void setOnFecharForm(Runnable r){
        this.onFecharForm = r;
    }

    public void fechar(){
        onFecharForm.run();
    }

    public void abrirPagamento(String metodo, float valor){
        JanelaPagamento jPagamento = new JanelaPagamento(metodo, valor, this);
        jPagamento.initModality(Modality.APPLICATION_MODAL);
        jPagamento.showAndWait();
    }

    public boolean novaVenda(String metodo, String codCliente, String codCorretor, String codImovel){
        Cliente_CleitonErinaGabriel cliente = imobiliaria.buscaCliente(codCliente);
        Corretor_CleitonErinaGabriel corretor = imobiliaria.buscaCorretor(codCorretor);
        Imovel_CleitonErinaGabriel imovel = imobiliaria.buscaImovel(codImovel);
        float valor = imovel.getValorVenda();
        abrirPagamento(metodo, valor);
        Venda_CleitonErinaGabriel venda = imobiliaria.novaVenda(cliente,corretor,imovel,valor,pagamento);
        return venda != null;

    }

    public boolean novoAluguel(String metodo, String codCliente, String codCorretor, String codImovel, LocalDate
            dataDevolucao, LocalDate dataPagamentoMensal, List<String> segurosContratados){
        Cliente_CleitonErinaGabriel cliente = imobiliaria.buscaCliente(codCliente);
        Corretor_CleitonErinaGabriel corretor = imobiliaria.buscaCorretor(codCorretor);
        Imovel_CleitonErinaGabriel imovel = imobiliaria.buscaImovel(codImovel);
        Aluguel_CleitonErinaGabriel aluguel = imobiliaria.novoAluguel(cliente,corretor,imovel,dataDevolucao,dataPagamentoMensal,segurosContratados);
        if(aluguel == null){
            return false;
        }
        abrirPagamento(metodo, aluguel.getValorTotalAluguel());
        aluguel.setFormaPagamento(pagamento);
        return true;
    }

    public void novoPagamento(String tipoPagamento, String nome, String bandeira, String numero) {
        pagamento = imobiliaria.novoPagamento(tipoPagamento,nome,bandeira,numero);
    }

    public void novoPagamento(String tipoPagamento){
        pagamento = imobiliaria.novoPagamento(tipoPagamento);
    }
}
