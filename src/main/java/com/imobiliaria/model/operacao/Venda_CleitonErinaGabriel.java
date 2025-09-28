package com.imobiliaria.model.operacao;

import com.imobiliaria.model.imovel.Imovel_CleitonErinaGabriel;
import com.imobiliaria.model.pagamento.Pagamento_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Cliente_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Corretor_CleitonErinaGabriel;

import java.time.LocalDate;

public class Venda_CleitonErinaGabriel {

    private int codigoVenda;
    private Cliente_CleitonErinaGabriel cliente;
    private Corretor_CleitonErinaGabriel corretor;
    private Imovel_CleitonErinaGabriel imovel;
    private LocalDate dataVenda;
    private float valorTotalVenda;
    private Pagamento_CleitonErinaGabriel formaPagamento;
    private boolean finalizada;

    @Override
    public String toString(){
        return codigoVenda + "\n"
                + cliente + "\n"
                + corretor.toString() + "\n"
                + imovel.toString() + "\n"
                + dataVenda.toString() + "\n"
                + valorTotalVenda + "\n"
                + formaPagamento.toString() + "\n"
                + finalizada + "\n";
    }

    public Venda_CleitonErinaGabriel(int codigoVenda, Cliente_CleitonErinaGabriel cliente, Corretor_CleitonErinaGabriel corretor, Imovel_CleitonErinaGabriel imovel, LocalDate dataVenda, float valorTotalVenda, Pagamento_CleitonErinaGabriel formaPagamento, boolean finalizada) {
        this.codigoVenda = codigoVenda;
        this.cliente = cliente;
        this.corretor = corretor;
        this.imovel = imovel;
        this.dataVenda = dataVenda;
        this.valorTotalVenda = valorTotalVenda;
        this.formaPagamento = formaPagamento;
        this.finalizada = finalizada;
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(int codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    public Cliente_CleitonErinaGabriel getCliente() {
        return cliente;
    }

    public void setCliente(Cliente_CleitonErinaGabriel cliente) {
        this.cliente = cliente;
    }

    public Corretor_CleitonErinaGabriel getCorretor() {
        return corretor;
    }

    public void setCorretor(Corretor_CleitonErinaGabriel corretor) {
        this.corretor = corretor;
    }

    public Imovel_CleitonErinaGabriel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel_CleitonErinaGabriel imovel) {
        this.imovel = imovel;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public float getValorTotalVenda() {
        return valorTotalVenda;
    }

    public void setValorTotalVenda(float valorTotalVenda) {
        this.valorTotalVenda = valorTotalVenda;
    }

    public Pagamento_CleitonErinaGabriel getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(Pagamento_CleitonErinaGabriel formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }
}
