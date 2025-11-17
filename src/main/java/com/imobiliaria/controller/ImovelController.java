package com.imobiliaria.controller;

import com.imobiliaria.model.Imobiliaria_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Operacao;

import java.time.LocalDate;

public class ImovelController {
    private final Imobiliaria_CleitonErinaGabriel imobiliaria;
    private Runnable onFecharForm;

    public ImovelController() {
        this.imobiliaria = Imobiliaria_CleitonErinaGabriel.getInstance();
    }

    public void setOnFecharForm(Runnable r) {
        this.onFecharForm = r;
    }

    public String cadastrarImovel(String endereco, LocalDate dataConstrucao, float areaTotal, float areaConstruida,
                                   int qtdDormitorios, int qtdBanheiros, int qtdVagasGaragem, float valorIPTU, float valorVenda,
                                   float valorAluguel, Operacao tipoOperacao) {
        String  cod = imobiliaria.novoImovel(endereco, dataConstrucao, areaTotal, areaConstruida, qtdDormitorios, qtdBanheiros,
                                                 qtdVagasGaragem, valorIPTU, valorVenda, valorAluguel, tipoOperacao);
        if (!cod.isBlank() && onFecharForm != null) {
            onFecharForm.run();
        }
        return cod;
    }

    public String  cadastrarImovel(String endereco, LocalDate dataConstrucao, float areaTotal, float areaConstruida,
                                   int qtdDormitorios, int qtdBanheiros, int qtdVagasGaragem, float valorIPTU, float valorVenda,
                                   float valorAluguel, Operacao tipoOperacao, int andar, int numApto, float valorCondominio) {
        String  cod = imobiliaria.novoImovel(endereco, dataConstrucao, areaTotal, areaConstruida, qtdDormitorios, qtdBanheiros,
                                                qtdVagasGaragem, valorIPTU, valorVenda, valorAluguel, tipoOperacao, andar, numApto, valorCondominio);
        if (!cod.isBlank() && onFecharForm != null) {
            onFecharForm.run();
        }
        return cod;
    }

    public String  cadastrarImovel(String endereco, LocalDate dataConstrucao, float areaTotal, float areaConstruida,
                                   int qtdDormitorios, int qtdBanheiros, int qtdVagasGaragem, float valorIPTU, float valorVenda,
                                   float valorAluguel, Operacao tipoOperacao, float taxaImpostoFederal) {
        String  cod = imobiliaria.novoImovel(endereco, dataConstrucao, areaTotal, areaConstruida, qtdDormitorios, qtdBanheiros,
                                                 qtdVagasGaragem, valorIPTU, valorVenda, valorAluguel, tipoOperacao,taxaImpostoFederal);
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
