package com.imobiliaria.view.Dialog;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.application.Platform;

import java.util.List;
import java.util.Optional;

public class DialogSelecionarCliente {

    public static String mostrarDialogSelecionarCliente(List<String> codigosClientes) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Selecionar Cliente");
        dialog.setHeaderText("Escolha o código do cliente:");

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        ComboBox<String> cbClientes = new ComboBox<>();
        cbClientes.getItems().addAll(codigosClientes);
        cbClientes.setPromptText("Selecione o cliente...");
        cbClientes.setPrefWidth(200);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Cliente:"), 0, 0);
        grid.add(cbClientes, 1, 0);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(cbClientes::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return cbClientes.getValue();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }
}
