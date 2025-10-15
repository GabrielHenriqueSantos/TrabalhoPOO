package com.imobiliaria.view.cadastro;

import com.imobiliaria.controller.ClienteController;
import com.imobiliaria.model.usuario.Cliente_CleitonErinaGabriel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.time.LocalDate;

public class FormCliente extends GridPane {

    private final ClienteController controller;

    public FormCliente(ClienteController controller) {

        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setAlignment(Pos.TOP_CENTER);
        ColumnConstraints col1 = new ColumnConstraints(160);
        ColumnConstraints col2 = new ColumnConstraints(250, 400, Double.MAX_VALUE);
        col2.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().addAll(col1, col2);

        TextField txtCpf = new TextField();
        TextField txtNome = new TextField();
        TextField txtRg = new TextField();
        DatePicker dpDataNascimento = new DatePicker();
        TextField txtEndereco = new TextField();
        TextField txtCep = new TextField();
        TextField txtTelefone = new TextField();
        TextField txtEmail = new TextField();

        Button btnSalvar = new Button("Salvar");
        Button btnCancelar = new Button("Cancelar");

        this.add(new Label("CPF:"), 0, 0);
        this.add(txtCpf, 1, 0);
        this.add(new Label("Nome:"), 0, 1);
        this.add(txtNome, 1, 1);
        this.add(new Label("RG:"), 0, 2);
        this.add(txtRg, 1, 2);
        this.add(new Label("Data de Nascimento:"), 0, 3);
        this.add(dpDataNascimento, 1, 3);
        this.add(new Label("Endereço:"), 0, 4);
        this.add(txtEndereco, 1, 4);
        this.add(new Label("CEP:"), 0, 5);
        this.add(txtCep, 1, 5);
        this.add(new Label("Telefone:"), 0, 6);
        this.add(txtTelefone, 1, 6);
        this.add(new Label("Email:"), 0, 7);
        this.add(txtEmail, 1, 7);
        this.add(new HBox(5, btnSalvar, btnCancelar), 1, 8);


        btnSalvar.setOnAction(e -> {
            try {
                String cpf = txtCpf.getText().trim();
                String nome = txtNome.getText().trim();
                String rg = txtRg.getText().trim();
                LocalDate dataNasc = dpDataNascimento.getValue();
                String endereco = txtEndereco.getText().trim();
                String cep = txtCep.getText().trim();
                String telefone = txtTelefone.getText().trim();
                String email = txtEmail.getText().trim();

                String cod = controller.cadastrarCliente(cpf, nome, rg, dataNasc, endereco, cep, telefone, email);

                Alert alert = new Alert(!cod.isBlank() ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                alert.setTitle("Cadastro de Cliente");
                alert.setHeaderText(null);
                alert.setContentText(!cod.isBlank() ? "Cliente cadastrado com sucesso!\n\nCódigo: " + cod : "Erro ao cadastrar cliente.");
                alert.showAndWait();

            } catch (Exception ex) {
                Alert erro = new Alert(Alert.AlertType.ERROR, "Erro: " + ex.getMessage());
                erro.showAndWait();
            }
        });
        btnCancelar.setOnAction(e -> {
            controller.cancelarCadastro();
        });
    }
}
