package com.imobiliaria;


import com.imobiliaria.controller.Imobiliaria_CleitonErinaGabriel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private class GeradorDeCodigo {
        private static Map<String, Integer> proximo = new HashMap<>();

        public static int gerar(String tipo) {
            int atual = proximo.getOrDefault(tipo, 0) + 1;
            proximo.put(tipo, atual);
            return atual;
        }
    }


    private static Scanner entrada = new Scanner(System.in);
    private static Imobiliaria_CleitonErinaGabriel imobiliaria = new Imobiliaria_CleitonErinaGabriel("Imobiliaria Yeager","Shiganshina, Paradis");

    public static void main(String[] args) {
        while (menu());

        imobiliaria.salvarTodosArrayList();
        entrada.close();

    }

    private static boolean menu(){
        System.out.println("Selecione a opção desejada: \n" +
                            "1: Cadastros\n" +
                            "2: Venda Aluguel\n" +
                            "3: Relatórios\n"+
                            "4: Sair\n" );
        int op = entrada.nextInt();
        switch (op){
            case 1:
                cadastros();
                return true;
            case 2:
                //operacao();
                return true;
            case 3:
                relatorios();
                return true;
            case 4:
                return false;
            default:
                return true;
        }
    }

    private static boolean cadastros(){
        System.out.println("Selecione a opção:\n" +
                            "1: Cadastrar Cliente\n" +
                            "2: Cadastrar Corretor\n" +
                            "3: Cadastrar Imóvel\n" +
                            "4: Cadastrar Seguro\n");
        int op = entrada.nextInt();
        switch (op){
            case 1:
                return novoCliente();
            case 2:
                return novoCorretor();
            case 3:
                //relatorios();
                return true;
            case 4:
                return true;
            default:
                return cadastros();
        }
    }

    private static boolean novoCliente(){
        System.out.print("Dados do Cliente:\nCPF: ");
        String cpf = entrada.next();
        entrada.nextLine();
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        System.out.print("RG: ");
        String rg = entrada.next();
        System.out.print("Data de Nascimento(AAAA-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(entrada.next());
        entrada.nextLine();
        System.out.print("Endereço: ");
        String endereco = entrada.nextLine();
        System.out.print("CEP: ");
        String cep = entrada.next();
        System.out.print("Telefone: ");
        String telefone = entrada.next();
        System.out.print("Email: ");
        String email = entrada.next();
        return imobiliaria.novoCliente(cpf,nome,rg,dataNascimento,endereco,cep,telefone,email);
    }

    private static boolean novoCorretor(){
        System.out.print("Dados do Corretor:\nCPF: ");
        String cpf = entrada.next();
        System.out.print("Nome: ");
        String nome = entrada.next();
        System.out.print("RG: ");
        String rg = entrada.next();
        System.out.print("Data de Nascimento(AAAA-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(entrada.next());
        System.out.print("Endereço: ");
        String endereco = entrada.next();
        System.out.print("CEP: ");
        String cep = entrada.next();
        System.out.print("Telefone: ");
        String telefone = entrada.next();
        System.out.print("Email: ");
        String email = entrada.next();
        System.out.print("CRECI: ");
        String creci = entrada.next();
        System.out.print("Salário: ");
        float salario = entrada.nextFloat();
        System.out.print("PIS: ");
        String pis = entrada.next();
        System.out.print("Data de admissão(AAAA-MM-DD): ");
        LocalDate dataAdmissao = LocalDate.parse(entrada.next());
        return imobiliaria.novoCorretor(cpf,nome,rg,dataNascimento,endereco,cep,telefone,email,creci,salario,pis,dataAdmissao);
    }

    private static boolean novoImovel(){
        return false;
    }

    private static boolean relatorios(){
        System.out.println("Selecione a opção desejada: \n" +
                "1: Todos Cliente cadastrados\n");
        int op = entrada.nextInt();
        switch (op){
            case 1:
                System.out.println(imobiliaria.listarTodosClientes());
                return true;
        }
        return false;
    }
}