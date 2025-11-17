package com.imobiliaria.view;

import com.imobiliaria.controller.MainController;
import com.imobiliaria.model.Imobiliaria_CleitonErinaGabriel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class MainScene extends Scene {
    private static final double MIN_WIDTH = 854.0;
    private static final double MIN_HEIGHT = 480;

    private final MainController controller;

    private final BorderPane layout;
    private final VBox info;

    public MainScene() {
        super(new BorderPane(), MIN_WIDTH, MIN_HEIGHT, true, SceneAntialiasing.BALANCED);
        layout = (BorderPane) this.getRoot();
        String nomeImo = "Imobiliaria Yeager";
        String endereco = "Shiganshina, 660";
        controller = new MainController(layout);
        info = infoImobiliaria(nomeImo,endereco);
        layout.setTop(initMenuBar());
        layout.setCenter(info);
    }

    private MenuBar initMenuBar() {

        return new MenuBar(menuCadastro(), menuRelatorio(), menuOperacao());
    }

    private Menu menuCadastro() {
        Menu cadastro = new Menu("Cadastro");
        MenuItem miImovel = new MenuItem("Imóvel");
        Menu usuario = new Menu("Usuário");
        MenuItem miCliente = new MenuItem("Cliente");
        MenuItem miCorretor = new MenuItem("Corretor");
        usuario.getItems().addAll(miCliente, miCorretor);
        MenuItem miSeguro = new MenuItem("Seguro");
        cadastro.getItems().addAll(miImovel, usuario, miSeguro);

        miCliente.setOnAction(e -> {
            controller.abrirCadastroCliente();
        });
        miCorretor.setOnAction(e -> {
            controller.abrirCadastroCorretor();
        });
        miImovel.setOnAction(e -> {
            controller.abrirCadastroImovel();
        });
        miSeguro.setOnAction(e -> {
            controller.abrirCadastroSeguro();
        });
        return cadastro;
    }

    private Menu menuRelatorio() {
        Menu relatorios = new Menu("Relatórios");
        Menu mImovel = menuRelatorioImovel();
        Menu mUsuario = menuRelatorioUsuario();
        Menu mAluguel = menuRelatorioAluguel();
        Menu mVenda = menuRelatorioVenda();

        MenuItem miSeguro = new MenuItem("Seguros");
        miSeguro.setOnAction(_->{
            controller.listarTodosSeguros();
        });
        relatorios.getItems().addAll(mImovel, mUsuario, mAluguel, mVenda, miSeguro);

        return relatorios;
    }

    private Menu menuRelatorioImovel(){
        Menu mImovel = new Menu("Imóvel");
        MenuItem miTodosImoveis = new MenuItem("Todo Imóveis");
        MenuItem miCasa = new MenuItem("Casa Residencial");
        MenuItem miPredio = new MenuItem("Prédio Residencial");
        MenuItem miComercial = new MenuItem("Comercial");
        MenuItem miLocacao = new MenuItem("Dispiniveis para Locação");
        MenuItem miAlugados = new MenuItem("Alugados");
        MenuItem miDispVenda = new MenuItem("Disponiveis para Venda");
        MenuItem miVendidos = new MenuItem("Vendidos");
        MenuItem miAluguelAtrasado = new MenuItem("Aluguel Atrasado");
        MenuItem miAlugadoCliente = new MenuItem("Alugado por Cliente");
        MenuItem miCompradoCliente = new MenuItem("Comprado por Cliente");
        mImovel.getItems().addAll(miTodosImoveis,miCasa, miPredio, miComercial, miLocacao, miAlugados, miDispVenda, miVendidos, miAluguelAtrasado, miAlugadoCliente, miCompradoCliente);

        miTodosImoveis.setOnAction(_->{
            controller.listarTodosImoveis();
        });

        miCasa.setOnAction(_->{
            controller.listarTodasCasas();
        });
        miPredio.setOnAction(_->{
            controller.listarTodosApts();
        });
        miComercial.setOnAction(_->{
            controller.listarTodosComercios();
        });
        miLocacao.setOnAction(_->{
            controller.listarDispLocacao();
        });
        miAlugados.setOnAction(_->{
            controller.listarAlugados();
        });
        miDispVenda.setOnAction(_->{
            controller.listarImoveisDispVenda();
        });
        miVendidos.setOnAction(_->{
            controller.listarImoveisVendidos();
        });
        miAluguelAtrasado.setOnAction(_->{
            controller.listarAlugueisAtrasados();
        });
        miAlugadoCliente.setOnAction(_->{
            controller.listarAlugadoCliente();
        });
        miCompradoCliente.setOnAction(_->{
            controller.listarCompradoCliente();
        });

        return mImovel;
    }

    private Menu menuRelatorioUsuario(){
        Menu mUsuario = new Menu("Usuário");
        MenuItem miCorretores = new MenuItem("Corretores");
        MenuItem miClientes = new MenuItem("Cliente");
        MenuItem miClientesAluguelAtrasado = new MenuItem("Com Aluguel atrasado");
        mUsuario.getItems().addAll(miCorretores, miClientes, miClientesAluguelAtrasado);

        miCorretores.setOnAction(_->{
            controller.listarTodosCorretores();
        });

        miClientes.setOnAction(_->{
            controller.listarTodosClientes();
        });
        miClientesAluguelAtrasado.setOnAction(_->{
            controller.listarClienteAluguelAtrasado();
        });
        return mUsuario;
    }

    private Menu menuRelatorioAluguel(){
        Menu mAluguel = new Menu("Aluguel");
        MenuItem miFinalizados = new MenuItem("Finalizados");
        MenuItem miDentrodoPrazoLoc = new MenuItem("Dentro do prazo de Locação");
        mAluguel.getItems().addAll(miFinalizados, miDentrodoPrazoLoc);

        miFinalizados.setOnAction(_->{
            controller.listarAlugueisFinalizados();
        });
        miDentrodoPrazoLoc.setOnAction(_->{
            controller.listarAlugueisAtivos();
        });
        return mAluguel;
    }

    private Menu menuRelatorioVenda(){
        Menu mVenda = new Menu("Venda");
        MenuItem miVendas = new MenuItem("Todas Vendas");
        MenuItem miVendasMes = new MenuItem("Vendas em um mês");
        mVenda.getItems().addAll(miVendas, miVendasMes);

        miVendas.setOnAction(_->{
            controller.listarTodasVendas();
        });

        miVendasMes.setOnAction(_->{
            controller.listarVendasMes();
        });
        return mVenda;
    }

    private Menu menuOperacao() {
        Menu operacao = new Menu("Operação");
        MenuItem miTransacao = new MenuItem("Nova Transação");
        operacao.getItems().add(miTransacao);
        miTransacao.setOnAction(_->{
            controller.novaTransacao();
        });
        return operacao;
    }

    public void salvarESair(){
        controller.salvarESair();
    }

    public VBox infoImobiliaria(String nome, String end){
        Text nomeImobiliaria = new Text(nome);
        nomeImobiliaria.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        nomeImobiliaria.setFill(Color.DARKBLUE);

        Text endereco = new Text(end);
        endereco.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        endereco.setFill(Color.DIMGRAY);

        VBox info = new VBox(5); // espaçamento entre as linhas
        info.setAlignment(Pos.CENTER);
        info.getChildren().addAll(nomeImobiliaria, endereco);

        return info;

    }

}
