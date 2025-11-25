package com.imobiliaria.model.imovel.factory;

import com.imobiliaria.model.imovel.Imovel_CleitonErinaGabriel;

import java.util.Map;

public interface ImovelCreator {

    Imovel_CleitonErinaGabriel criar(Map<String, Object> data);
}
