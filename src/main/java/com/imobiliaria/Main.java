package com.imobiliaria;


import com.imobiliaria.controller.Imobiliaria_CleitonErinaGabriel;

public class Main {
    public static void main(String[] args) {
        Imobiliaria_CleitonErinaGabriel imobiliaria = new Imobiliaria_CleitonErinaGabriel("Imobiliaria Yeager","Shiganshina, Paradis");
        int op;
        while (true){
            op =  menu();
            switch (op){
                case 1:
            }
        }
    }

    private static int menu(){
        System.out.println("Selecione a opção desejada: \n" +
                            "1: Cadastrar Cliente\n" +
                            "2: Cadastrar Corretor\n" +
                            "3: Cadastrar Imovel\n"+
                            "4: Vender Imovel\n" +
                            "5: Alugar Imovel\n");
    }
}