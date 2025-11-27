package com.imobiliaria.model.relatorio;

import com.imobiliaria.model.operacao.Venda_CleitonErinaGabriel;
import java.util.List;

public class RelatorioVendas extends RelatorioTemplate {
    private final List<Venda_CleitonErinaGabriel> vendas;
    private final double totalLucro;

    public RelatorioVendas(List<Venda_CleitonErinaGabriel> vendas, String filtro) {
        super("Vendas - " + filtro);
        this.vendas = vendas;
        this.totalLucro = vendas.stream().mapToDouble(Venda_CleitonErinaGabriel::getValorTotalVenda).sum();
    }

    @Override
    protected String gerarConteudo() {
        StringBuilder conteudo = new StringBuilder();

        // Statistics section
        conteudo.append("ESTATÍSTICAS:\n");
        conteudo.append("Total de vendas: ").append(vendas.size()).append("\n");
        conteudo.append("Faturamento total: R$ ").append(String.format("%.2f", totalLucro)).append("\n");
        conteudo.append("Ticket médio: R$ ").append(vendas.isEmpty() ? "0.00" :
                String.format("%.2f", totalLucro / vendas.size())).append("\n\n");

        // Details section
        conteudo.append("DETALHES DAS VENDAS:\n");
        for (Venda_CleitonErinaGabriel venda : vendas) {
            conteudo.append(venda).append("\n");
            conteudo.append("--------------------------------------------\n");
        }

        return conteudo.toString();
    }
}