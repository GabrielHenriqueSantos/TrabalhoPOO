package com.imobiliaria.model.usuario;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Usuario_CleitonErinaGabriel implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int proxCod = 1;
    protected String codigoUsuario;
    protected String nome;
    protected String cpf;
    protected String rg;
    protected LocalDate dataNascimento;
    protected String endereco;
    protected String cep;
    protected String telefone;
    protected String email;

    public Usuario_CleitonErinaGabriel(String cod, String cpf, String nome, String rg, LocalDate dataNascimento, String endereco, String cep, String telefone, String email) {
        this.codigoUsuario = "U"+ (proxCod++) + cod;
        this.cpf = cpf;
        this.nome = nome;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.cep = cep;
        this.telefone = telefone;
        this.email = email;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCep() {
        return cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Codigo de Usuário: " + codigoUsuario + "\nNome: " + nome + "\nCPF: "
        + cpf + "\nRG: " + rg + "\nData de Nascimento: " + getDataNascimento() + "\nEndereço: "
        + endereco + "\nCEP: " + cep + "\nTelefone: " + telefone + "\nE-mail: " + email;
    }
}
