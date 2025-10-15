package com.imobiliaria.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class RelatorioView extends VBox {

    private final TextArea txtLista;

    public RelatorioView(String relatorio, Runnable r) {

        setPadding(new Insets(50));
        setSpacing(10);

        Label lblTitulo = new Label("Imóveis Cadastrados:");
        lblTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        txtLista = new TextArea();
        txtLista.setEditable(false);
        txtLista.setWrapText(true);
        txtLista.setPrefHeight(720);

        Button btnFechar = new Button("Fechar");
        btnFechar.setOnAction(e -> r.run());

        getChildren().addAll(lblTitulo, txtLista, btnFechar);

        atualizarLista(relatorio);
    }

    public void atualizarLista(String relatorio) {
        txtLista.setText(relatorio);
    }
}

