package com.imobiliaria.view.cadastro;

import com.imobiliaria.controller.ImovelController;
import com.imobiliaria.model.imovel.Operacao;
import com.imobiliaria.model.imovel.TipoImovel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FormImovel extends GridPane {

    private final ImovelController controller;
    private boolean isAluguel = true;

    public FormImovel(ImovelController controller) {
        this.controller = controller;
        this.setPadding(new Insets(20));
        this.setVgap(10);
        this.setHgap(10);
        this.setAlignment(Pos.TOP_CENTER);
        ColumnConstraints col1 = new ColumnConstraints(160);
        ColumnConstraints col2 = new ColumnConstraints(250, 400, Double.MAX_VALUE);
        col2.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().addAll(col1, col2);

        TextField txtEndereco = new TextField();
        DatePicker dpDataConstrucao = new DatePicker(LocalDate.now());
        dpDataConstrucao.setEditable(false);
        TextField txtAreaTotal = new TextField();
        TextField txtAreaConstruida = new TextField();
        TextField txtDormitorios = new TextField();
        TextField txtBanheiros = new TextField();
        TextField txtVagas = new TextField();
        TextField txtIPTU = new TextField();
        TextField txtVenda = new TextField();
        TextField txtAluguel = new TextField();

        ComboBox<Operacao> cbOperacao = new ComboBox<>();
        cbOperacao.getItems().addAll(Operacao.values());


        ComboBox<TipoImovel> cbTipoImovel = new ComboBox<>();
        cbTipoImovel.getItems().addAll(TipoImovel.values());


        TextField txtAndar = new TextField();
        TextField txtNumApto = new TextField();
        TextField txtCondominio = new TextField();
        TextField txtTaxaFederal = new TextField();
        Label lblVenda = new Label("Valor Venda:");
        Label lblAluguel = new Label("Valor Aluguel:");

        this.add(new Label("Tipo de Imóvel:"), 0, 0);
        this.add(cbTipoImovel, 1, 0);
        this.add(new Label("Endereço:"), 0, 1);
        this.add(txtEndereco, 1, 1);
        this.add(new Label("Data Construção:"), 0, 2);
        this.add(dpDataConstrucao, 1, 2);
        this.add(new Label("Área Total:"), 0, 3);
        this.add(txtAreaTotal, 1, 3);
        this.add(new Label("Área Construída:"), 0, 4);
        this.add(txtAreaConstruida, 1, 4);
        this.add(new Label("Dormitórios:"), 0, 5);
        this.add(txtDormitorios, 1, 5);
        this.add(new Label("Banheiros:"), 0, 6);
        this.add(txtBanheiros, 1, 6);
        this.add(new Label("Vagas Garagem:"), 0, 7);
        this.add(txtVagas, 1, 7);
        this.add(new Label("Valor IPTU:"), 0, 8);
        this.add(txtIPTU, 1, 8);
        this.add(lblVenda, 0, 9);
        this.add(txtVenda, 1, 9);
        this.add(lblAluguel, 0, 10);
        this.add(txtAluguel, 1, 10);
        this.add(new Label("Operação:"), 0, 11);
        this.add(cbOperacao, 1, 11);

        Label lblAndar = new Label("Andar:");
        Label lblNumApto = new Label("Número Apto:");
        Label lblCondominio = new Label("Valor Condomínio:");
        Label lblTaxaFederal = new Label("Taxa Imposto Federal:");

        this.add(lblAndar, 0, 12);
        this.add(txtAndar, 1, 12);
        this.add(lblNumApto, 0, 13);
        this.add(txtNumApto, 1, 13);
        this.add(lblCondominio, 0, 14);
        this.add(txtCondominio, 1, 14);
        this.add(lblTaxaFederal, 0, 15);
        this.add(txtTaxaFederal, 1, 15);

        lblAndar.setVisible(false);
        lblNumApto.setVisible(false);
        lblCondominio.setVisible(false);
        lblTaxaFederal.setVisible(false);
        txtAndar.setVisible(false);
        txtNumApto.setVisible(false);
        txtCondominio.setVisible(false);
        txtTaxaFederal.setVisible(false);

        lblVenda.setVisible(false);
        txtVenda.setVisible(false);

        cbTipoImovel.setOnAction(e -> {
            TipoImovel tipo = cbTipoImovel.getValue();
            boolean isPredio = tipo == TipoImovel.PREDIO_RESIDENCIAL;
            boolean isComercial = tipo == TipoImovel.COMERCIAL;

            lblAndar.setVisible(isPredio);
            txtAndar.setVisible(isPredio);
            lblNumApto.setVisible(isPredio);
            txtNumApto.setVisible(isPredio);
            lblCondominio.setVisible(isPredio);
            txtCondominio.setVisible(isPredio);

            lblTaxaFederal.setVisible(isComercial);
            txtTaxaFederal.setVisible(isComercial);

        });
        cbTipoImovel.getSelectionModel().selectFirst();

        cbOperacao.setOnAction(_->{
            isAluguel = cbOperacao.getValue() == Operacao.ALUGUEL;
            txtAluguel.setVisible(isAluguel);
            lblAluguel.setVisible(isAluguel);
            lblVenda.setVisible(!isAluguel);
            txtVenda.setVisible(!isAluguel);
        });
        cbOperacao.getSelectionModel().selectFirst();
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setPrefWidth(180);
        btnSalvar.setStyle("-fx-background-color: #4CAF50; -fx-text-fil: white; -fx-font-weight: bold");
        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setPrefWidth(180);
        btnCancelar.setStyle("-fx-background-color: #C41F1F; -fx-text-fill: white; -fx-font-weight: bold");
        this.add(new HBox(5,btnSalvar,btnCancelar), 1, 16);

        btnSalvar.setOnAction(e -> {
            try {
                TipoImovel tipo = cbTipoImovel.getValue();
                if (tipo == null) {
                    showAlert("Erro", "Selecione o tipo de imóvel.", Alert.AlertType.ERROR);
                    return;
                }
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("endereco", txtEndereco.getText());
                parametros.put("dataConstrucao", dpDataConstrucao.getValue());
                parametros.put("areaTotal", Float.parseFloat(txtAreaTotal.getText()));
                parametros.put("areaConstruida", Float.parseFloat(txtAreaConstruida.getText()));
                parametros.put("qtdDormitorios", Integer.parseInt(txtDormitorios.getText()));
                parametros.put("qtdBanheiros", Integer.parseInt(txtBanheiros.getText()));
                parametros.put("qtdVagasGaragem", Integer.parseInt(txtVagas.getText()));
                parametros.put("valorIPTU", Float.parseFloat(txtIPTU.getText()));
                parametros.put("valorVenda", (isAluguel)?0f:Float.parseFloat(txtVenda.getText()));
                parametros.put("valorAluguel", (isAluguel)?Float.parseFloat(txtAluguel.getText()):0f);
                parametros.put("tipoOperacao", cbOperacao.getValue());

                switch (tipo) {
                    case TipoImovel.PREDIO_RESIDENCIAL:
                        parametros.put("andar", Integer.parseInt(txtAndar.getText()));
                        parametros.put("numApto", Integer.parseInt(txtNumApto.getText()));
                        parametros.put("valorCondominio", Float.parseFloat(txtCondominio.getText()));
                        break;
                    case TipoImovel.COMERCIAL:
                        parametros.put("taxaImpostoFederal", Float.parseFloat(txtTaxaFederal.getText()));
                        break;
                }

                String cod = controller.cadastrarImovel(tipo, parametros);
                if (cod != null) {
                    showAlert("Sucesso", "Imóvel cadastrado com sucesso!\nCódigo: "+cod, Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Erro", "Falha ao cadastrar imóvel.", Alert.AlertType.ERROR);
                }

            } catch (Exception ex) {
                showAlert("Erro", "Dados inválidos: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        });
        btnCancelar.setOnAction(e->{
            controller.cancelarCadastro();
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

