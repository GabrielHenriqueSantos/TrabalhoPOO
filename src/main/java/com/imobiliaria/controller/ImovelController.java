package com.imobiliaria.controller;

import com.imobiliaria.model.Imobiliaria_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Imovel_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Operacao;
import com.imobiliaria.model.imovel.TipoImovel;
import com.imobiliaria.model.imovel.factory.FactoryImovel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ImovelController {
    private final Imobiliaria_CleitonErinaGabriel imobiliaria;
    private Runnable onFecharForm;

    public ImovelController() {
        this.imobiliaria = Imobiliaria_CleitonErinaGabriel.getInstance();
    }

    public void setOnFecharForm(Runnable r) {
        this.onFecharForm = r;
    }

    public String cadastrarImovel(TipoImovel tipo, Map<String, Object> parametros){
        Imovel_CleitonErinaGabriel im = FactoryImovel.factory(tipo,parametros);
        String cod = imobiliaria.novoImovel(im);
        if (!cod.isBlank() && onFecharForm != null){
            onFecharForm.run();
        }
        return cod;
    }



    public void cancelarCadastro(){
        if(onFecharForm != null)
            onFecharForm.run();
    }
}
