package com.imobiliaria.controller;

import com.imobiliaria.model.Imobiliaria_CleitonErinaGabriel;

import java.time.LocalDate;

public class ClienteController {
    private final Imobiliaria_CleitonErinaGabriel imobiliaria;
    private Runnable onFecharForm;

    public ClienteController(Imobiliaria_CleitonErinaGabriel imobiliaria) {
        this.imobiliaria = imobiliaria;
    }
    public void setOnFecharForm(Runnable r) {
        this.onFecharForm = r;
    }

    public String cadastrarCliente(String cpf, String nome, String rg, LocalDate dataNascimento, String endereco, String cep, String telefone, String email) {
        String cod = imobiliaria.novoCliente(cpf, nome, rg, dataNascimento, endereco, cep, telefone, email);
        if(!cod.isBlank() && onFecharForm != null){
            onFecharForm.run();
        }
        return cod;
    }

    public void cancelarCadastro(){
        if(onFecharForm != null)
            onFecharForm.run();
    }
}
