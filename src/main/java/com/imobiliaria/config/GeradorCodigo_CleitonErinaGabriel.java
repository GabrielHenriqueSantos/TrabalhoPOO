package com.imobiliaria.config;

import java.util.HashMap;
import java.util.Map;

import java.io.*;

public class GeradorCodigo_CleitonErinaGabriel implements Serializable {
    private static Map<String, Integer> proximo = new HashMap<>();

    static {
        carregar();
    }

    public static String gerar(String tipo) {
        int atual = proximo.getOrDefault(tipo, 0) + 1;
        proximo.put(tipo, atual);
        salvar();
        return atual+tipo;
    }

    private static void salvar() {
        try {
            File dir = new File(Configuracao_CleitonErinaGabriel.DIR);
            if (!dir.exists()) {
                dir.mkdirs(); // cria o diretório "data" se não existir
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Configuracao_CleitonErinaGabriel.ARQUIVO_GERADOR))) {
                oos.writeObject(proximo);
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar códigos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void carregar() {
        File arquivo = new File(Configuracao_CleitonErinaGabriel.ARQUIVO_GERADOR);
        if (!arquivo.exists()) {
            proximo = new HashMap<>(); // primeira execução, inicia vazio
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Configuracao_CleitonErinaGabriel.ARQUIVO_GERADOR))) {
            proximo = (Map<String, Integer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar códigos: " + e.getMessage());
            proximo = new HashMap<>();
        }
    }
}



