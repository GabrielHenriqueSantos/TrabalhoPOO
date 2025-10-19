package com.imobiliaria.controller;

import com.imobiliaria.model.Imobiliaria_CleitonErinaGabriel;
import com.imobiliaria.view.Dialog.DialogSelecionarCliente;
import com.imobiliaria.view.Dialog.DialogSelecionarData;
import com.imobiliaria.view.RelatorioView;
import com.imobiliaria.view.cadastro.FormCliente;
import com.imobiliaria.view.cadastro.FormCorretor;
import com.imobiliaria.view.cadastro.FormImovel;
import com.imobiliaria.view.cadastro.FormSeguro;
import com.imobiliaria.view.operacao.FormTransacao;
import javafx.scene.layout.BorderPane;

import java.time.LocalDate;
import java.util.List;

public class MainController {
    private final Imobiliaria_CleitonErinaGabriel imobiliaria;
    private final BorderPane root;

    public MainController(Imobiliaria_CleitonErinaGabriel imobiliaria, BorderPane root){
        this.imobiliaria = imobiliaria;
        this.root = root;
    }

    public void salvarESair() {
        imobiliaria.salvarTodosArrayList();
    }

    public void abrirCadastroCliente(){
        ClienteController control = new ClienteController(imobiliaria);
        control.setOnFecharForm(() -> root.setCenter(null));
        FormCliente form = new FormCliente(control);
        root.setCenter(form);
    }

    public void abrirCadastroCorretor(){
        CorretorController control = new CorretorController(imobiliaria);
        control.setOnFecharForm(()->root.setCenter(null));
        FormCorretor form = new FormCorretor(control);
        root.setCenter(form);
    }

    public void abrirCadastroImovel(){
        ImovelController control = new ImovelController(imobiliaria);
        control.setOnFecharForm(()->root.setCenter(null));
        FormImovel form = new FormImovel(control);
        root.setCenter(form);
    }

    public void abrirCadastroSeguro(){
        SeguroController control = new SeguroController(imobiliaria);
        control.setOnFecharForm(()->root.setCenter(null));
        FormSeguro form = new FormSeguro(control);
        root.setCenter(form);
    }

    private void mostrarRelatorio(String relatorio){
        RelatorioView rv = new RelatorioView(relatorio,()->root.setCenter(null));
        root.setCenter(rv);
    }

    public void novaTransacao() {
        TransacaoController control = new TransacaoController(imobiliaria);
        control.setOnFecharForm(()->root.setCenter(null));
        FormTransacao form = new FormTransacao(control);
        root.setCenter(form);
    }

    public void listarTodosImoveis(){
        mostrarRelatorio(imobiliaria.listarImoveis());
    }

    public void listarTodasCasas(){
        mostrarRelatorio(imobiliaria.listarCasaResidencial());
    }

    public void listarTodosApts() {
        mostrarRelatorio(imobiliaria.listarPredioResidencial());
    }

    public void listarTodosComercios() {
        mostrarRelatorio(imobiliaria.listarImoveisComerciais());
    }

    public void listarDispLocacao() {
        mostrarRelatorio(imobiliaria.listarImoveisLocacao());
    }

    public void listarAlugados() {
        mostrarRelatorio(imobiliaria.listarImoveisAlugados());
    }

    public void listarImoveisDispVenda() {
        mostrarRelatorio(imobiliaria.listarImoveisAVenda());
    }

    public void listarImoveisVendidos() {
        mostrarRelatorio(imobiliaria.listarImoveisVendidos());
    }


    public void listarAlugueisAtrasados() {
        mostrarRelatorio(imobiliaria.listarAlugueisAtrasados());
    }

    public void listarAlugadoCliente() {
        List<String> codClientes = imobiliaria.getCodigosClientes();
        String cliente = DialogSelecionarCliente.mostrarDialogSelecionarCliente(codClientes);
        if(cliente != null){
            mostrarRelatorio(imobiliaria.listarImoveisAlugadosCliente(cliente));
        }
    }

    public void listarCompradoCliente() {
        List<String> codClientes = imobiliaria.getCodigosClientes();
        String cliente = DialogSelecionarCliente.mostrarDialogSelecionarCliente(codClientes);
        if(cliente != null){
            mostrarRelatorio(imobiliaria.listarImoveisVendidosCliente(cliente));
        }
    }

    public void listarTodosCorretores() {
        mostrarRelatorio(imobiliaria.listarTodosCorretores());
    }

    public void listarTodosClientes() {
        mostrarRelatorio(imobiliaria.listarTodosClientes());
    }

    public void listarClienteAluguelAtrasado() {
        mostrarRelatorio(imobiliaria.listarAlugueisAtrasadosClientes());
    }

    public void listarAlugueisFinalizados() {
        mostrarRelatorio(imobiliaria.listarAlugueisFinalizados());
    }

    public void listarAlugueisAtivos() {
        mostrarRelatorio(imobiliaria.listarAlugueisAtivos());
    }

    public void listarTodasVendas() {
        mostrarRelatorio(imobiliaria.listarTodasVendas());
    }

    public void listarVendasMes(){
        LocalDate fimMes = DialogSelecionarData.mostrarDialogSelecionarData();
        if(fimMes != null){
            mostrarRelatorio(imobiliaria.vendaPorMes(fimMes));
        }
    }

    public void listarTodosSeguros() {
        mostrarRelatorio(imobiliaria.listaTodosSeguros());}
}
