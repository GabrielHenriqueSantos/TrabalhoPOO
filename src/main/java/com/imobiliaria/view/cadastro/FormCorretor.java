package com.imobiliaria.view.cadastro;

import com.imobiliaria.controller.ClienteController;
import com.imobiliaria.controller.CorretorController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class FormCorretor extends GridPane {

    private final CorretorController controller;

    public FormCorretor(CorretorController controller) {

        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setAlignment(Pos.TOP_CENTER);


        TextField txtCpf = new TextField();
        TextField txtNome = new TextField();
        TextField txtRg = new TextField();
        DatePicker dpDataNascimento = new DatePicker();
        TextField txtEndereco = new TextField();
        TextField txtCep = new TextField();
        TextField txtTelefone = new TextField();
        TextField txtEmail = new TextField();
        TextField txtCreci = new TextField();
        TextField txtSalario = new TextField();
        TextField txtPis = new TextField();
        DatePicker dpDataAdmissao = new DatePicker();

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
        this.add(new Label("CRECI"), 0, 8);
        this.add(txtCreci, 1, 8);
        this.add(new Label("Salario"), 0, 9);
        this.add(txtSalario, 1, 9);
        this.add(new Label("PIS"), 0, 10);
        this.add(txtPis, 1, 10);
        this.add(new Label("Data de Admissão"), 0, 11);
        this.add(dpDataAdmissao, 1, 11);

        this.add(new HBox(5, btnSalvar, btnCancelar), 1, 12);


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
                String creci = txtCreci.getText().trim();
                float salario = Float.parseFloat(txtSalario.getText().trim());
                String pis = txtPis.getText().trim();
                LocalDate dataAdm = dpDataAdmissao.getValue();

                String cod = controller.cadastrarCorretor(cpf, nome, rg, dataNasc, endereco, cep, telefone, email, creci, salario, pis, dataAdm);

                Alert alert = new Alert(!cod.isBlank() ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                alert.setTitle("Cadastro de Corretor");
                alert.setHeaderText(null);
                alert.setContentText(!cod.isBlank() ? "Corretor cadastrado com sucesso!\nCódigo: "+cod : "Erro ao cadastrar corretor.");
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
