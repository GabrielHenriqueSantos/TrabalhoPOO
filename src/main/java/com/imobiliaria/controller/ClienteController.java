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

    public boolean cadastrarCliente(String cpf, String nome, String rg, LocalDate dataNascimento, String endereco, String cep, String telefone, String email) {
        boolean sucesso = imobiliaria.novoCliente(cpf, nome, rg, dataNascimento, endereco, cep, telefone, email);
        if(sucesso && onFecharForm != null){
            onFecharForm.run();
        }
        return sucesso;
    }

    public void cancelarCadastro(){
        onFecharForm.run();
    }
}
