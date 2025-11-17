package com.imobiliaria.controller;

import com.imobiliaria.model.Imobiliaria_CleitonErinaGabriel;

import java.time.LocalDate;

public class CorretorController {
    private final Imobiliaria_CleitonErinaGabriel imobiliaria;
    private Runnable onFecharForm;

    public CorretorController(){
        this.imobiliaria = Imobiliaria_CleitonErinaGabriel.getInstance();
    }

    public void setOnFecharForm(Runnable r) {
        this.onFecharForm = r;
    }

    public String cadastrarCorretor(String cpf, String nome, String rg, LocalDate dataNascimento, String endereco, String cep,
                                     String telefone, String email, String creci, float salario, String pis, LocalDate dataAdmissao){
        String cod = imobiliaria.novoCorretor(cpf, nome, rg, dataNascimento, endereco, cep, telefone, email, creci, salario, pis, dataAdmissao);
        if(!cod.isBlank() && onFecharForm != null){
            onFecharForm.run();
        }
        return cod;
    }

    public void cancelarCadastro(){
        if (onFecharForm != null)
            onFecharForm.run();
    }
}
