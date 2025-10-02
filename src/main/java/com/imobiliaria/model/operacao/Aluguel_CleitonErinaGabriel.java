package com.imobiliaria.model.operacao;

import com.imobiliaria.config.GeradorCodigo_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Imovel_CleitonErinaGabriel;
import com.imobiliaria.model.pagamento.Pagamento_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Cliente_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Corretor_CleitonErinaGabriel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Aluguel_CleitonErinaGabriel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigoAluguel;
    private Cliente_CleitonErinaGabriel cliente;
    private Corretor_CleitonErinaGabriel corretor;
    private Imovel_CleitonErinaGabriel imovel;
    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;
    private LocalDate dataPagamentoMensal;
    private float valorTotalAluguel;
    private Pagamento_CleitonErinaGabriel formaPagamento;
    private ArrayList<Seguro_CleitonErinaGabriel> segurosContratados;
    private boolean finalizado;
    private boolean pago;

    //funções
    public float calcularValorTotal(float bonificacao) {
        float valorTotal = valorTotalAluguel;

        if(!verificarAtraso()){ //o cliente ganha um desconto por pagar dentro do prazo
            valorTotal = valorTotal - (valorTotalAluguel * bonificacao);
        }

        if(possuiSeguro()){
            for(Seguro_CleitonErinaGabriel i : segurosContratados) {
                valorTotal += i.getValor();
            }
        }

        return valorTotal;
    }

    public boolean possuiSeguro(){
        return !segurosContratados.isEmpty();
    }

    public boolean verificarAtraso(){
        return LocalDate.now().isAfter(dataPagamentoMensal);
    }

    @Override
    public String toString(){
        return codigoAluguel + "\n"
                + cliente.toString() + "\n"
                + corretor.toString() + "\n"
                + imovel.toString() + "\n"
                + dataAluguel.toString() + "\n"
                + dataDevolucao.toString() + "\n"
                + dataPagamentoMensal.toString() + "\n"
                + valorTotalAluguel + "\n"
                + formaPagamento.toString() + "\n"
                + segurosContratados.toString() + "\n"
                + finalizado + "\n"
                + pago + "\n";
    }

    //construtores

    public Aluguel_CleitonErinaGabriel(Cliente_CleitonErinaGabriel cliente, Corretor_CleitonErinaGabriel corretor, Imovel_CleitonErinaGabriel imovel, LocalDate dataDevolucao, LocalDate dataPagamentoMensal, Pagamento_CleitonErinaGabriel formaPagamento, ArrayList<Seguro_CleitonErinaGabriel> segurosContratados) {
        this.codigoAluguel = GeradorCodigo_CleitonErinaGabriel.gerar("AL");
        this.cliente = cliente;
        this.corretor = corretor;
        this.imovel = imovel;
        this.dataAluguel = LocalDate.now();
        this.dataDevolucao = dataDevolucao;
        this.dataPagamentoMensal = dataPagamentoMensal;
        this.valorTotalAluguel = imovel.getValorAluguel()+valorTotalSeguro();
        this.formaPagamento = formaPagamento;
        this.segurosContratados = segurosContratados;
        this.finalizado = false;
        this.pago = false;
    }

    public float valorTotalSeguro(){
        float valor = 0f;
        for(Seguro_CleitonErinaGabriel s:segurosContratados){
            valor+=s.getValor();
        }
        return valor;
    }

    //getters e setters
    public String getCodigoAluguel() {
        return codigoAluguel;
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

    public LocalDate getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(LocalDate dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public LocalDate getDataPagamentoMensal() {
        return dataPagamentoMensal;
    }

    public void setDataPagamentoMensal(LocalDate dataPagamentoMensal) {
        this.dataPagamentoMensal = dataPagamentoMensal;
    }

    public float getValorTotalAluguel() {
        return valorTotalAluguel;
    }

    public void setValorTotalAluguel(float valorTotalAluguel) {
        this.valorTotalAluguel = valorTotalAluguel;
    }

    public Pagamento_CleitonErinaGabriel getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(Pagamento_CleitonErinaGabriel formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public ArrayList<Seguro_CleitonErinaGabriel> getSegurosContratados() {
        return segurosContratados;
    }

    public void setSegurosContratados(ArrayList<Seguro_CleitonErinaGabriel> segurosContratados) {
        this.segurosContratados = segurosContratados;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}
