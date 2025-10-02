package com.imobiliaria.model.usuario;

import java.time.LocalDate;

public class Cliente_CleitonErinaGabriel extends Usuario_CleitonErinaGabriel{
    private static final long serialVersionUID = 1L;
    protected LocalDate dataCadastro;

    public Cliente_CleitonErinaGabriel(String cpf, String nome, String rg, LocalDate dataNascimento, String endereco, String cep, String telefone, String email) {
        super("CL", cpf, nome, rg, dataNascimento, endereco, cep, telefone, email);
        this.dataCadastro = dataCadastro;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    @Override
    public String toString() {
        return "Cliente: " + super.toString() + "\nData de Cadastro: " + getDataCadastro();
    }
}
