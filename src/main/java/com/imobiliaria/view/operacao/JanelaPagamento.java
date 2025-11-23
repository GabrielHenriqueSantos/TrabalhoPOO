package com.imobiliaria.view.operacao;

import com.imobiliaria.controller.TransacaoController;
import com.imobiliaria.model.pagamento.Pagamento_CleitonErinaGabriel;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;

public class JanelaPagamento extends Stage {

    private final TextField txtNomeTitular;
    private final TextField txtBandeira;
    private final TextField txtNumeroCartao;
    private final TextField txtValor;
    private final Button btnConfirmar;
    private final Button btnCancelar;

    private final TransacaoController controller;
    private final String tipoPagamento;

    public JanelaPagamento(String tipoPagamento, float valor, TransacaoController controller){
        this.controller = controller;
        this.tipoPagamento = tipoPagamento;

        setTitle("Finalizar Pagamento");
        setResizable(false);

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f9f9f9;");

        Label lblTitulo = new Label("Finalizar Pagamento - " + tipoPagamento.toUpperCase());
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        txtNomeTitular = new TextField();
        txtBandeira = new TextField();
        txtNumeroCartao = new TextField();
        txtValor = new TextField();

        txtNomeTitular.setPromptText("Nome no cartão");
        txtBandeira.setPromptText("Bandeira (Visa, MasterCard...)");
        txtNumeroCartao.setPromptText("Número do cartão");
        txtValor.setText(Float.toString(valor));
        txtValor.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        txtValor.setEditable(false);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 0, 10, 0));
        ColumnConstraints col1 = new ColumnConstraints(130);
        ColumnConstraints col2 = new ColumnConstraints(250);
        grid.getColumnConstraints().addAll(col1, col2);

        int row = 0;
        if (tipoPagamento.equalsIgnoreCase("Cartão")) {
            grid.add(new Label("Nome no Cartão:"), 0, row);
            grid.add(txtNomeTitular, 1, row++);

            grid.add(new Label("Bandeira:"), 0, row);
            grid.add(txtBandeira, 1, row++);

            grid.add(new Label("Número do Cartão:"), 0, row);
            grid.add(txtNumeroCartao, 1, row++);

            grid.add(new Label("Valor total"),0, row);
            grid.add(txtValor,1, row++ );
        } else {
            Label info = new Label("Pagamento em dinheiro selecionado.\nValor Total: "+valor);
            info.setStyle("-fx-font-size: 13px; -fx-text-fill: #333;");
            grid.add(info, 0, 0, 2, 1);
        }

        btnConfirmar = new Button("Confirmar");
        btnConfirmar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        btnConfirmar.setOnAction(e -> confirmarPagamento());

        btnCancelar = new Button("Cancelar");
        btnCancelar.setStyle("-fx-background-color: #C41F1F; -fx-text-fill: white; -fx-font-weight: bold;");
        btnCancelar.setOnAction(e -> close());

        HBox botoesBox = new HBox(10, btnCancelar, btnConfirmar);
        botoesBox.setAlignment(Pos.CENTER_RIGHT);

        root.getChildren().addAll(lblTitulo, grid, botoesBox);

        Scene scene = new Scene(root);
        setScene(scene);
    }

    private void confirmarPagamento() {
        if (tipoPagamento.equalsIgnoreCase("Cartão")) {
            String nome = txtNomeTitular.getText().trim();
            String bandeira = txtBandeira.getText().trim();
            String numero = txtNumeroCartao.getText().trim();

            if (nome.isEmpty() || bandeira.isEmpty() || numero.isEmpty()) {
                mostrarErro("Preencha todos os campos do cartão.");
                return;
            }

            controller.novoPagamento(tipoPagamento, nome, bandeira, numero);
            mostrarMensagem("Pagamento com cartão finalizado!");
        } else {
            controller.novoPagamento(tipoPagamento);
            mostrarMensagem("Pagamento em dinheiro registrado.");
        }

        close();
    }

    private void mostrarErro(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void mostrarMensagem(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
