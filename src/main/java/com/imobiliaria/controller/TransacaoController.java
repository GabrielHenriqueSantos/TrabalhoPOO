package com.imobiliaria.controller;

import com.imobiliaria.model.Imobiliaria_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Operacao;
import com.imobiliaria.model.pagamento.Pagamento_CleitonErinaGabriel;
import com.imobiliaria.service.TransacaoService;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

public class TransacaoController {
    private final Imobiliaria_CleitonErinaGabriel imobiliaria;
    private Pagamento_CleitonErinaGabriel pagamento;
    private final List<String> codImoveisVenda;
    private final List<String> codImoveisAluguel;
    private final List<String> codClientes;
    private final List<String> codCorretores;
    private TransacaoService transacao;

    private Runnable onFecharForm;
    private Consumer<TransacaoService.PagamentoRequest> onPagamento;

    public TransacaoController() {
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

    public void setOnFecharForm(Runnable r) {
        this.onFecharForm = r;
    }

    public void fechar() {
        onFecharForm.run();
    }

    public void setOnPagamento(Consumer<TransacaoService.PagamentoRequest> callback) {
        this.onPagamento = callback;
    }

    public boolean novaVenda(String metodo, String codCliente, String codCorretor, String codImovel) {
        transacao = new TransacaoService();
        if(!transacao.novaVenda(metodo,codCliente,codCorretor,codImovel))
            return false;
        if(onPagamento != null){
            onPagamento.accept(transacao.getRequest());
        }
        return true;
    }

    public boolean novoAluguel(String metodo, String codCliente, String codCorretor, String codImovel, LocalDate dataDevolucao, LocalDate dataPagamentoMensal, List<String> segurosContratados) {
        transacao = new TransacaoService();
        if(!transacao.novoAluguel(metodo,codCliente,codCorretor,codImovel,dataDevolucao,dataPagamentoMensal,segurosContratados))
            return false;
        if (onPagamento != null){
            onPagamento.accept(transacao.getRequest());
        }
        return true;
    }

    public void novoPagamento(String tipoPagamento, String nome, String bandeira, String numero) {
        transacao.setPagamento(tipoPagamento, nome, bandeira, numero);
    }

    public void novoPagamento(String tipoPagamento) {
        transacao.setPagamento(tipoPagamento);
    }

}
