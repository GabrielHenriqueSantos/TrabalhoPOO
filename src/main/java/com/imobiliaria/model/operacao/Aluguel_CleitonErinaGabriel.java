package com.imobiliaria.model.operacao;

import com.imobiliaria.config.GeradorCodigo_CleitonErinaGabriel;
import com.imobiliaria.model.Imobiliaria_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Imovel_CleitonErinaGabriel;
import com.imobiliaria.model.pagamento.Pagamento_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Cliente_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Corretor_CleitonErinaGabriel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Aluguel_CleitonErinaGabriel implements Serializable, Transacao {
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


    public static class Builder{
        private Cliente_CleitonErinaGabriel cliente;
        private Corretor_CleitonErinaGabriel corretor;
        private Imovel_CleitonErinaGabriel imovel;
        private LocalDate dataDevolucao;
        private LocalDate dataPagamentoMensal;
        private Pagamento_CleitonErinaGabriel formaPagamento;
        private ArrayList<Seguro_CleitonErinaGabriel> segurosContratados;

        public Builder cliente(Cliente_CleitonErinaGabriel c){
            this.cliente = c;
            return this;
        }

        public Builder corretor(Corretor_CleitonErinaGabriel cr){
            this.corretor = cr;
            return this;
        }

        public Builder imovel(Imovel_CleitonErinaGabriel im){
            this.imovel = im;
            return this;
        }

        public Builder dataDevolucao(LocalDate dd){
            this.dataDevolucao = dd;
            return this;
        }

        public Builder dataPagamentoMensal(LocalDate dp){
            this.dataPagamentoMensal = dp;
            return this;
        }

        public Builder segurosContratados(ArrayList<Seguro_CleitonErinaGabriel> sc){
            this.segurosContratados = sc;
            return this;
        }

        public Aluguel_CleitonErinaGabriel build(){
             return new Aluguel_CleitonErinaGabriel(this.cliente, this.corretor, this.imovel, this.dataDevolucao, this.dataPagamentoMensal, this.segurosContratados);
        }
    }


    //funções
    public float calcularValorTotal(float bonificacao) {
        float valorTotal = valorTotalAluguel;

        if(pago){ //o cliente ganha um desconto por pagar dentro do prazo
            valorTotal = valorTotal - (valorTotalAluguel * bonificacao);
        }
        pago = true;
        return valorTotal;
    }

    public boolean possuiSeguro(){
        return !segurosContratados.isEmpty();
    }

    private boolean verificarAtraso(){
        return LocalDate.now().isAfter(dataPagamentoMensal);
    }

    public void atualizaDataPagamento(){
        pago = !verificarAtraso();
    }

    @Override
    public String toString() {
        return "Código: " + codigoAluguel + "\n"
                + "Cliente:\n\tCódigo: " + cliente.getCodigoUsuario() + "\n"
                + "\tNome: " + cliente.getNome() + "\n"
                + "Corretor:\n\tCódigo: " + corretor.getCodigoUsuario() + "\n"
                + "\tNome: " + corretor.getNome() + "\n"
                + "Imóvel:\n\tCódigo: " + imovel.getCodigoImovel() + "\n"
                + "\tEndereço: " + imovel.getEndereco() + "\n"
                + "Data do próximo Aluguel: " + dataAluguel + "\n"
                + "Data de Devolução: " + dataDevolucao + "\n"
                + "Data Pagamento Mensal: " + dataPagamentoMensal + "\n"
                + "Valor Total: R$" + valorTotalAluguel + "\n"
                + "Forma de Pagamento:\n\t" + formaPagamento.toString() + "\n"
                + "Seguros Contratados: " + segurosContratados.toString() + "\n"
                + (finalizado ? "Finalizado" : "Em andamento") + "\n"
                + (pago ? "Pago" : "Pendente") + "\n";
    }


    //construtores

    private Aluguel_CleitonErinaGabriel(Cliente_CleitonErinaGabriel cliente, Corretor_CleitonErinaGabriel corretor, Imovel_CleitonErinaGabriel imovel, LocalDate dataDevolucao, LocalDate dataPagamentoMensal, ArrayList<Seguro_CleitonErinaGabriel> segurosContratados) {
        this.codigoAluguel = GeradorCodigo_CleitonErinaGabriel.gerar("AL");
        this.cliente = cliente;
        this.corretor = corretor;
        this.imovel = imovel;
        this.dataAluguel = LocalDate.now();
        this.dataDevolucao = dataDevolucao;
        this.dataPagamentoMensal = dataPagamentoMensal;
        this.segurosContratados = segurosContratados;
        this.valorTotalAluguel = imovel.getValorAluguel()+valorTotalSeguro();
        this.finalizado = false;
        this.pago = true;
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
