package com.imobiliaria.controller;

import com.imobiliaria.model.Imobiliaria_CleitonErinaGabriel;
import com.imobiliaria.view.cadastro.FormCliente;
import com.imobiliaria.view.cadastro.FormCorretor;
import javafx.scene.layout.BorderPane;

public class MainController {
    private final Imobiliaria_CleitonErinaGabriel imobiliaria;
    private final BorderPane root;

    public MainController(Imobiliaria_CleitonErinaGabriel imobiliaria, BorderPane root){
        this.imobiliaria = imobiliaria;
        this.root = root;
    }

    public void abrirCadastroCliente(){
        ClienteController control = new ClienteController(imobiliaria);
        control.setOnFecharForm(() -> root.setCenter(null));
        FormCliente form = new FormCliente(control);
        root.setCenter(form);
    }

    public void abrirCadastroCorretor(){
        CorretorController control = new CorretorController(imobiliaria);
        control.setOnFecharForm(()->root.setCenter(null));
        FormCorretor form = new FormCorretor(control);
        root.setCenter(form);
    }
}
