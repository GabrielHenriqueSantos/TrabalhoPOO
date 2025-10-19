package com.imobiliaria.view.Dialog;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.application.Platform;

import java.time.LocalDate;
import java.util.Optional;

public class DialogSelecionarData {

    public static LocalDate mostrarDialogSelecionarData() {
        Dialog<LocalDate> dialog = new Dialog<>();
        dialog.setTitle("Selecionar Data");
        dialog.setHeaderText("Selecione a data base para o relatório de vendas (últimos 30 dias):");

        ButtonType okButtonType = new ButtonType("Gerar Relatório", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        DatePicker datePicker = new DatePicker(LocalDate.now()); // valor padrão: hoje
        datePicker.setPrefWidth(200);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Data base:"), 0, 0);
        grid.add(datePicker, 1, 0);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(datePicker::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return datePicker.getValue();
            }
            return null;
        });

        Optional<LocalDate> result = dialog.showAndWait();
        return result.orElse(null);
    }
}

