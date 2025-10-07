package com.imobiliaria.controller;

import com.imobiliaria.model.Imobiliaria_CleitonErinaGabriel;

public class SeguroController {
    private final Imobiliaria_CleitonErinaGabriel imobiliaria;
    private Runnable onFecharForm;

    public SeguroController(Imobiliaria_CleitonErinaGabriel imobiliaria){
        this.imobiliaria = imobiliaria;
    }

    public void setOnFecharForm(Runnable r) {
        this.onFecharForm = r;
    }

    public String cadastraSeguro(String nomeSeguradora, String tipo, String descricao, float valor){
        String cod = imobiliaria.novoSeguro(nomeSeguradora,tipo,descricao,valor);
        if(!cod.isEmpty() && onFecharForm != null){
            onFecharForm.run();
        }
        return cod;
    }

    public void cancelaCadastro(){
        if(onFecharForm!=null)
            onFecharForm.run();
    }
}
