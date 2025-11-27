package com.imobiliaria.model.relatorio;

import com.imobiliaria.model.usuario.Usuario_CleitonErinaGabriel;

import java.util.List;

public class RelatorioUsuarios extends RelatorioTemplate {
    private final List<? extends Usuario_CleitonErinaGabriel> usuario;

    public RelatorioUsuarios(List<? extends Usuario_CleitonErinaGabriel> usuario, String filtro) {
        super("Lista de Usuários - " + filtro);
        this.usuario = usuario;
    }

    @Override
    protected String gerarConteudo() {
        if (usuario.isEmpty()) {
            return "Nenhum cliente cadastrado.\n";
        }

        StringBuilder conteudo = new StringBuilder();
        conteudo.append("Total de clientes: ").append(usuario.size()).append("\n\n");

        for (Usuario_CleitonErinaGabriel cliente : usuario) {
            conteudo.append(cliente).append("\n");
            conteudo.append("--------------------------------------------\n");
        }

        return conteudo.toString();
    }
}
