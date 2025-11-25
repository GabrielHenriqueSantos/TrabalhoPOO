package com.imobiliaria.model.imovel.factory;

import com.imobiliaria.model.imovel.Imovel_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.TipoImovel;

import java.util.Map;

public class FactoryImovel {

    public static Imovel_CleitonErinaGabriel factory(TipoImovel tipo, Map<String, Object> parametros){
        switch (tipo){
            case COMERCIAL:
                return new ComercialCreator().criar(parametros);
            case CASA_RESIDENCIAL:
                return new CasaResidencialCreator().criar(parametros);
            case PREDIO_RESIDENCIAL:
                return new PredioResidencialCreator().criar(parametros);
            default:
                return null;
        }
    }
}
