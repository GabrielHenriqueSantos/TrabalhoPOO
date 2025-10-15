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
        MainScene mainscene = new MainScene();
        stage.setScene(mainscene);
        stage.setTitle("Imobiliaria");
        stage.setMaximized(false);
        stage.show();
        stage.setOnCloseRequest(e->{
            mainscene.salvarESair();
        });
    }
}