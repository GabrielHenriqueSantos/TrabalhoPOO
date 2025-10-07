package com.imobiliaria.view;

import com.imobiliaria.controller.MainController;
import com.imobiliaria.model.Imobiliaria_CleitonErinaGabriel;
import com.imobiliaria.view.cadastro.FormCliente;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;


public class MainScene extends Scene {
    private static final double MIN_WIDTH = 1280.0;
    private static final double MIN_HEIGHT = 720.0;

    private final MainController controller;

    private final BorderPane layout;

    public MainScene(){
        super(new BorderPane(), MIN_WIDTH, MIN_HEIGHT, true, SceneAntialiasing.BALANCED);
        layout = (BorderPane) this.getRoot();
        controller = new MainController(new Imobiliaria_CleitonErinaGabriel("Imobiliaria Yeager","Shiganshina"), layout);
        layout.setTop(initMenuBar());
    }

    private MenuBar initMenuBar(){

        return new MenuBar(menuCadastro(), menuRelatorio(), menuOperacao());
    }

    private Menu menuCadastro(){
        Menu cadastro = new Menu("Cadastro");
        MenuItem miImovel = new MenuItem("Imóvel");
        Menu usuario = new Menu("Usuário");
        MenuItem miCliente = new MenuItem("Cliente");
        MenuItem miCorretor = new MenuItem("Corretor");
        usuario.getItems().addAll(miCliente,miCorretor);
        MenuItem miSeguro = new MenuItem("Seguro");
        cadastro.getItems().addAll(miImovel,usuario,miSeguro);

        miCliente.setOnAction(e->{
            controller.abrirCadastroCliente();
        });
        miCorretor.setOnAction(e->{
            controller.abrirCadastroCorretor();
        });
        miImovel.setOnAction(e->{
            controller.abrirCadastroImovel();
        });
        miSeguro.setOnAction(e->{
            controller.abrirCadastroSeguro();
        });
        return cadastro;
    }

    private Menu menuRelatorio(){
        Menu relatorios = new Menu("Relatórios");
        Menu mImovel = new Menu("Imóvel");
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
        mImovel.getItems().addAll(miCasa,miPredio,miComercial,miLocacao,miAlugados,miDispVenda,miVendidos,miAluguelAtrasado,miAlugadoCliente,miCompradoCliente);

        Menu mUsuario = new Menu("Usuário");
        MenuItem miCorretores = new MenuItem("Corretores");
        MenuItem miClientes = new MenuItem("Cliente");
        MenuItem miClientesAluguelAtrasado = new MenuItem("Com Aluguel atrasado");
        mUsuario.getItems().addAll(miCorretores,miClientes,miClientesAluguelAtrasado);

        Menu mAluguel = new Menu("Aluguel");
        MenuItem miFinalizados = new MenuItem("Finalizados");
        MenuItem miDentrodoPrazoLoc = new MenuItem("Dentro do prazo de Locação");
        mAluguel.getItems().addAll(miFinalizados,miDentrodoPrazoLoc);

        Menu mVenda = new Menu("Venda");
        MenuItem miVendas = new MenuItem("Todas Vendas");
        MenuItem miVendasMes = new MenuItem("Vendas em um mês");
        mVenda.getItems().addAll(miVendas, miVendasMes);

        MenuItem miSeguro = new MenuItem("Seguros");
        relatorios.getItems().addAll(mImovel,mUsuario,mAluguel,mVenda,miSeguro);
        return relatorios;
    }

    private Menu menuOperacao(){
        Menu operacao = new Menu("Operação");
        MenuItem miVenda = new MenuItem("Nova Venda");
        MenuItem miAluguel = new MenuItem("Novo Aluguel");
        operacao.getItems().addAll(miVenda,miAluguel);
        return operacao;
    }

}
