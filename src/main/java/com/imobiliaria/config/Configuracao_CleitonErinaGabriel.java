package com.imobiliaria.config;

import java.time.LocalDate;

public class Configuracao_CleitonErinaGabriel {

    //bonificação do valor do aluguel pra pagamento dentro do prazo
    private final float taxaBonificacao = 0.01f;

    //o sistema mais simples pra atribuir código às classes
    private int geraCodigoAluguel = 0;
    private int geraCodigoVenda = 0;
    private int geraCodigoSeguro = 0;
    private int geraCodigoUsuario = 0;
    private int geraCodigoImovel = 0;

    //data de hoje pra poder verificar o vencimento do aluguel
    private LocalDate dataDeHoje = LocalDate.now();
}