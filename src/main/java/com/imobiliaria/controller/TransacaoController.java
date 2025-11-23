package com.imobiliaria.controller;

import com.imobiliaria.model.Imobiliaria_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Imovel_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Operacao;
import com.imobiliaria.model.operacao.Aluguel_CleitonErinaGabriel;
import com.imobiliaria.model.operacao.Seguro_CleitonErinaGabriel;
import com.imobiliaria.model.operacao.Transacao;
import com.imobiliaria.model.operacao.Venda_CleitonErinaGabriel;
import com.imobiliaria.model.pagamento.Pagamento_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Cliente_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Corretor_CleitonErinaGabriel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TransacaoController {
    private final Imobiliaria_CleitonErinaGabriel imobiliaria;
    private Pagamento_CleitonErinaGabriel pagamento;
    private final List<String> codImoveisVenda;
    private final List<String> codImoveisAluguel;
    private final List<String> codClientes;
    private final List<String> codCorretores;
    private Transacao transacao;

    private Runnable onFecharForm;
    private Consumer<PagamentoRequest> onPagamento;

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

    public void setOnPagamento(Consumer<PagamentoRequest> callback) {
        this.onPagamento = callback;
    }

    public boolean novaVenda(String metodo, String codCliente, String codCorretor, String codImovel) {
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
        if(onPagamento != null){
            onPagamento.accept(new PagamentoRequest(metodo, valor));
        }
        return imobiliaria.novaVenda(venda);
    }

    public boolean novoAluguel(String metodo, String codCliente, String codCorretor, String codImovel, LocalDate dataDevolucao, LocalDate dataPagamentoMensal, List<String> segurosContratados) {
        Cliente_CleitonErinaGabriel cliente = imobiliaria.buscaCliente(codCliente);
        Corretor_CleitonErinaGabriel corretor = imobiliaria.buscaCorretor(codCorretor);
        Imovel_CleitonErinaGabriel imovel = imobiliaria.buscaImovel(codImovel);
        if (cliente == null || corretor == null || imovel == null)
            return false;
        imovel.setDisponivel(false);
        ArrayList<Seguro_CleitonErinaGabriel> segAl = new ArrayList<>();
        Seguro_CleitonErinaGabriel seg;
        for (String codSeg : segurosContratados) {
            seg = imobiliaria.buscaSeguro(codSeg);
            if (seg != null) {
                segAl.add(seg);
            }
        }
        Aluguel_CleitonErinaGabriel aluguel = new Aluguel_CleitonErinaGabriel.Builder()
                .cliente(cliente)
                .corretor(corretor)
                .imovel(imovel)
                .dataDevolucao(dataDevolucao)
                .dataPagamentoMensal(dataPagamentoMensal)
                .segurosContratados(segAl).build();
        transacao = aluguel;
        if(onPagamento != null){
            onPagamento.accept(new PagamentoRequest(metodo,aluguel.getValorTotalAluguel()));
        }
        return imobiliaria.novoAluguel(aluguel);
    }

    public void novoPagamento(String tipoPagamento, String nome, String bandeira, String numero) {
        transacao.setFormaPagamento(imobiliaria.novoPagamento(tipoPagamento, nome, bandeira, numero));
    }

    public void novoPagamento(String tipoPagamento) {
        transacao.setFormaPagamento(imobiliaria.novoPagamento(tipoPagamento));
    }

    public record PagamentoRequest(String metodo, float valor){}
}
