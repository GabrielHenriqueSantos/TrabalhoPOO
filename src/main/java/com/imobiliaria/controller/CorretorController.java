package com.imobiliaria.controller;

import com.imobiliaria.model.Imobiliaria_CleitonErinaGabriel;

import java.time.LocalDate;

public class CorretorController {
    private final Imobiliaria_CleitonErinaGabriel imobiliaria;
    private Runnable onFecharForm;

    public CorretorController(Imobiliaria_CleitonErinaGabriel imobiliaria){
        this.imobiliaria = imobiliaria;
    }

    public void setOnFecharForm(Runnable r) {
        this.onFecharForm = r;
    }

    public boolean cadastrarCorretor(String cpf, String nome, String rg, LocalDate dataNascimento, String endereco, String cep,
                                     String telefone, String email, String creci, float salario, String pis, LocalDate dataAdmissao){
        boolean sucesso = imobiliaria.novoCorretor(cpf, nome, rg, dataNascimento, endereco, cep, telefone, email, creci, salario, pis, dataAdmissao);
        if(sucesso && onFecharForm != null){
            onFecharForm.run();
        }
        return sucesso;
    }

    public void cancelarCadastro(){
        onFecharForm.run();
    }
}
