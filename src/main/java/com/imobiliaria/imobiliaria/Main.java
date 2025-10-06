package com.imobiliaria.imobiliaria;


import com.imobiliaria.view.MainScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        stage.setScene(new MainScene());
        stage.setMaximized(true);
        stage.show();
    }
}