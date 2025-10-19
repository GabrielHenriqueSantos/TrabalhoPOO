package com.imobiliaria.view.operacao;

import com.imobiliaria.controller.TransacaoController;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormTransacao extends VBox {

    private TransacaoController controller;

    public FormTransacao(TransacaoController controller) {
        this.controller = controller;
        setPadding(new Insets(20));
        setSpacing(15);
        setStyle("-fx-background-color: #f8f8f8;");

        Label lblTitulo = new Label("Nova operação");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        lblTitulo.setAlignment(Pos.CENTER);

        RadioButton rbVenda = new RadioButton("Venda");
        RadioButton rbAluguel = new RadioButton("Aluguel");
        ToggleGroup grupoTipo = new ToggleGroup();
        rbVenda.setToggleGroup(grupoTipo);
        rbAluguel.setToggleGroup(grupoTipo);
        rbAluguel.setSelected(true);

        HBox tipoBox = new HBox(15, rbVenda, rbAluguel);
        tipoBox.setAlignment(Pos.CENTER_LEFT);

        RadioButton rbDinheiro = new RadioButton("Dinheiro");
        RadioButton rbCartao = new RadioButton("Cartão");
        ToggleGroup tgPagamento = new ToggleGroup();
        rbDinheiro.setToggleGroup(tgPagamento);
        rbCartao.setToggleGroup(tgPagamento);

        HBox pagamentoBox = new HBox(15, rbDinheiro, rbCartao);
        pagamentoBox.setAlignment(Pos.CENTER_LEFT);

        ComboBox<String> cbCliente = new ComboBox<>();
        cbCliente.getItems().addAll(controller.getCodClientes());
        cbCliente.getSelectionModel().selectFirst();

        ComboBox<String> cbCorretor = new ComboBox<>();
        cbCorretor.getItems().addAll(controller.getCodCorretores());
        cbCorretor.getSelectionModel().selectFirst();

        ComboBox<String> cbImovel = new ComboBox<>();
        cbImovel.getItems().addAll(controller.getCodImoveisAluguel());
        cbImovel.getSelectionModel().selectFirst();

        DatePicker dpDataDevolucao = new DatePicker(LocalDate.now().plusMonths(6));
        dpDataDevolucao.setEditable(false);

        //Cria DatePicker
        DatePicker dpDataPagamentoMensal = criaDataPagamento();

        TextField txtSeguros = new TextField();
        txtSeguros.setPromptText("códigos separados por vírgula");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);
        grid.setPadding(new Insets(10, 10, 10, 10));
        ColumnConstraints col1 = new ColumnConstraints(160); // coluna dos labels
        ColumnConstraints col2 = new ColumnConstraints(250, 400, Double.MAX_VALUE);
        col2.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().addAll(col1, col2);

        int row = 0;
        grid.add(new Label("Código do Cliente:"), 0, row);
        grid.add(cbCliente, 1, row++);

        grid.add(new Label("Código do Corretor:"), 0, row);
        grid.add(cbCorretor, 1, row++);

        grid.add(new Label("Código do Imóvel:"), 0, row);
        grid.add(cbImovel, 1, row++);

        grid.add(new Label("Forma de Pagamento:"), 0, row);
        grid.add(pagamentoBox, 1, row++);

        grid.add(new Label("Data de Devolução (Aluguel):"), 0, row);
        grid.add(dpDataDevolucao, 1, row++);

        grid.add(new Label("Data de Pagamento Mensal:"), 0, row);
        grid.add(dpDataPagamentoMensal, 1, row++);

        grid.add(new Label("Seguros:"), 0, row);
        grid.add(txtSeguros, 1, row++);

        Button btnContinuar = new Button("Continuar");
        btnContinuar.setPrefWidth(180);
        btnContinuar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        btnContinuar.setOnAction(e -> {
            String codCliente = cbCliente.getValue();
            String codCorretor = cbCorretor.getValue();
            String codImovel = cbImovel.getValue();
            if(rbVenda.isSelected()){
                boolean ok = controller.novaVenda("Cartão",codCliente,codCorretor,codImovel);
                mostrarAlerta(ok,"Venda");
                controller.fechar();
            }else if(rbAluguel.isSelected()){
                LocalDate dataDev = dpDataDevolucao.getValue();
                LocalDate dataPag = dpDataPagamentoMensal.getValue();
                List<String> seguros = new ArrayList<>();
                String strSeguros = txtSeguros.getText().replaceAll("\\s+","");

                if (!strSeguros.isEmpty()) {
                    seguros = Arrays.asList(strSeguros.split(","));
                }
                String metodo = rbCartao.isSelected() ? "Cartão" : "Dinheiro";
                boolean ok = controller.novoAluguel(metodo,codCliente,codCorretor,codImovel,dataDev,dataPag,seguros);
                mostrarAlerta(ok, "Aluguel");
                controller.fechar();
            }else{
                mostrarErro("Selecione o tipo de transação (Venda ou Aluguel).");
            }
        });

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setPrefWidth(180);
        btnCancelar.setStyle("-fx-background-color: #C41F1F; -fx-text-fill: white; -fx-font-weight: bold;");
        btnCancelar.setOnAction(_->{
            controller.fechar();
        });
        HBox botoesBox = new HBox(15,btnContinuar, btnCancelar);
        botoesBox.setAlignment(Pos.CENTER_RIGHT);
        botoesBox.setPadding(new Insets(10, 0, 0, 0));

        getChildren().addAll(lblTitulo, tipoBox, grid, botoesBox);

        grupoTipo.selectedToggleProperty().addListener(_ ->{
            boolean isVenda = rbVenda.isSelected();
            dpDataDevolucao.setDisable(isVenda);
            dpDataDevolucao.setVisible(!isVenda);
            dpDataPagamentoMensal.setDisable(isVenda);
            dpDataPagamentoMensal.setVisible(!isVenda);
            txtSeguros.setDisable(isVenda);
            txtSeguros.setVisible(!isVenda);
            rbCartao.setSelected(isVenda);
            rbDinheiro.setDisable(isVenda);
            cbImovel.getItems().clear();
            cbImovel.getItems().addAll(isVenda ? controller.getCodImoveisVenda() : controller.getCodImoveisAluguel());
            cbImovel.getSelectionModel().selectFirst();
        });
    }

    private DatePicker criaDataPagamento(){
        DatePicker datePicker = new DatePicker(LocalDate.now().plusDays(5));
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #eee; -fx-text-fill: #888;");
                }
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);

        return datePicker;
    }

    private void mostrarAlerta(boolean sucesso, String tipo) {
        Alert alert = new Alert(sucesso ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(sucesso ? tipo + " registrada com sucesso!" : "Erro ao registrar " + tipo + ".");
        alert.showAndWait();
    }

    private void mostrarErro(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

