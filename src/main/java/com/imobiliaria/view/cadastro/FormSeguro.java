package com.imobiliaria.view.cadastro;

import com.imobiliaria.controller.SeguroController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FormSeguro extends GridPane {

    private final SeguroController controller;

    public FormSeguro(SeguroController controller) {
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setVgap(10);
        this.setHgap(10);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPrefWidth(480);

        TextField txtNomeSeguradora = new TextField();
        TextField txtTipo = new TextField();
        TextArea txtDescricao = new TextArea();
        TextField txtValor = new TextField();

        txtDescricao.setPrefRowCount(5);
        txtDescricao.setWrapText(true);


        this.add(new Label("Nome da Seguradora:"), 0, 0);
        this.add(txtNomeSeguradora, 1, 0);

        this.add(new Label("Tipo de Seguro:"), 0, 1);
        this.add(txtTipo, 1, 1);

        this.add(new Label("Descrição:"), 0, 2);
        this.add(txtDescricao, 1, 2);

        this.add(new Label("Valor (R$):"), 0, 3);
        this.add(txtValor, 1, 3);

        Button btnSalvar = new Button("Salvar");
        Button btnCancelar = new Button("Cancelar");

        this.add(new HBox(5,btnSalvar,btnCancelar), 1, 4);

        btnSalvar.setOnAction(e -> {
            try {
                String nome = txtNomeSeguradora.getText().trim();
                String tipo = txtTipo.getText().trim();
                String descricao = txtDescricao.getText().trim();
                float valor = Float.parseFloat(txtValor.getText());

                if (nome.isEmpty() || tipo.isEmpty() || descricao.isEmpty()) {
                    showAlert("Erro", "Preencha todos os campos!", Alert.AlertType.ERROR);
                    return;
                }

                String cod = controller.cadastraSeguro(nome, tipo, descricao, valor);

                if (!cod.isEmpty()) {
                    showAlert("Sucesso", "Seguro cadastrado com sucesso!\nCódigo: "+cod, Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Erro", "Falha ao cadastrar seguro.", Alert.AlertType.ERROR);
                }

            } catch (NumberFormatException ex) {
                showAlert("Erro", "O valor deve ser numérico.", Alert.AlertType.ERROR);
            } catch (Exception ex) {
                showAlert("Erro", "Falha inesperada: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        });
        btnCancelar.setOnAction(e -> {
            controller.cancelaCadastro();
        });
    }

    private void showAlert(String titulo, String msg, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

