package com.imobiliaria.model.usuario;

import java.time.LocalDate;

public class Corretor_CleitonErinaGabriel extends Usuario_CleitonErinaGabriel{
    protected String creci;
    protected float salario;
    protected String pis;
    protected LocalDate dataAdmissao;

    public Corretor_CleitonErinaGabriel(int codigoUsuario, String cpf, String nome, String rg, LocalDate dataNascimento, String endereco, String cep, String telefone, String email, String creci, float salario, String pis, LocalDate dataAdmissao) {
        super(codigoUsuario, cpf, nome, rg, dataNascimento, endereco, cep, telefone, email);
        this.creci = creci;
        this.salario = salario;
        this.pis = pis;
        this.dataAdmissao = dataAdmissao;
    }

    public String getCreci() {
        return creci;
    }

    public float getSalario() {
        return salario;
    }

    public String getPis() {
        return pis;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    @Override
    public String toString() {
        return "Corretor: " + super.toString() + "\nCRECI: " + creci + "\nSalario: " + salario + "\nPIS: "
        + pis + "\nData de Admissão: " + getDataAdmissao();
    }
}
