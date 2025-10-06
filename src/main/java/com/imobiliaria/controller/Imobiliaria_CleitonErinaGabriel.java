package com.imobiliaria.controller;

import com.imobiliaria.config.Configuracao_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.CasaResidencial_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Comercial_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Imovel_CleitonErinaGabriel;
import com.imobiliaria.model.imovel.Operacao;
import com.imobiliaria.model.imovel.PredioResidencial_CleitonErinaGabriel;
import com.imobiliaria.model.operacao.Aluguel_CleitonErinaGabriel;
import com.imobiliaria.model.operacao.Seguro_CleitonErinaGabriel;
import com.imobiliaria.model.operacao.Venda_CleitonErinaGabriel;
import com.imobiliaria.model.pagamento.Cartao_CleitonErinaGabriel;
import com.imobiliaria.model.pagamento.Dinheiro_CleitonErinaGabriel;
import com.imobiliaria.model.pagamento.Pagamento_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Cliente_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Corretor_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Usuario_CleitonErinaGabriel;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Imobiliaria_CleitonErinaGabriel {
    private String nome;
    private String endereco;
    private ArrayList<Aluguel_CleitonErinaGabriel> alugueis;
    private ArrayList<Venda_CleitonErinaGabriel> vendas;
    private ArrayList<Imovel_CleitonErinaGabriel> imoveis;
    private ArrayList<Usuario_CleitonErinaGabriel> clientes;
    private ArrayList<Usuario_CleitonErinaGabriel> corretores;

    private ArrayList<Seguro_CleitonErinaGabriel> seguros;

    public Imobiliaria_CleitonErinaGabriel(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.alugueis = carregarArquivo(Configuracao_CleitonErinaGabriel.ARQUIVO_ALUGUEIS);
        this.vendas = carregarArquivo(Configuracao_CleitonErinaGabriel.ARQUIVO_VENDAS);
        this.imoveis = carregarArquivo(Configuracao_CleitonErinaGabriel.ARQUIVO_IMOVEIS);
        this.clientes = carregarArquivo(Configuracao_CleitonErinaGabriel.ARQUIVO_CLIENTES);
        this.corretores = carregarArquivo(Configuracao_CleitonErinaGabriel.ARQUIVO_CORRETORES);
        this.seguros = carregarArquivo(Configuracao_CleitonErinaGabriel.ARQUIVO_SEGUROS);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> ArrayList<T> carregarArquivo(String path){
        File arquivo = new File(path);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))){
            return (ArrayList<T>) ois.readObject();
        }catch (ClassNotFoundException e){
            System.err.println("Classe não encontrada durante desserialização: "+e.getMessage());
        }catch (IOException e){
            return new ArrayList<>();
        }catch (ClassCastException e){
            System.err.println("Erro ao converter objeto para ArrayList: "+e.getMessage());
        }
        return new ArrayList<>();
    }

    public static <T extends Serializable> boolean salvarArquivo(ArrayList<T> arq, String path){
        try {
            File dir = new File(Configuracao_CleitonErinaGabriel.DIR);
            if (!dir.exists()) {
                dir.mkdirs();

            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
                oos.writeObject(arq);
                return true;
            }
        }catch (IOException e) {
            System.err.println("Erro ao Salvar arquivo: "+e.getMessage());
            return false;
        }
    }

    public void salvarTodosArrayList(){
        salvarArquivo(alugueis, Configuracao_CleitonErinaGabriel.ARQUIVO_ALUGUEIS);
        salvarArquivo(vendas, Configuracao_CleitonErinaGabriel.ARQUIVO_VENDAS);
        salvarArquivo(imoveis, Configuracao_CleitonErinaGabriel.ARQUIVO_IMOVEIS);
        salvarArquivo(seguros, Configuracao_CleitonErinaGabriel.ARQUIVO_SEGUROS);
        salvarArquivo(clientes, Configuracao_CleitonErinaGabriel.ARQUIVO_CLIENTES);
        salvarArquivo(corretores, Configuracao_CleitonErinaGabriel.ARQUIVO_CORRETORES);
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNome() {
        return nome;
    }

    /*******************************************************************************************************************
     * Cadastros
     *******************************************************************************************************************/
    //FIXME:
    //      Code smell
    public boolean novoCliente(String cpf, String nome, String rg, LocalDate dataNascimento, String endereco, String cep,String telefone, String email){
        Usuario_CleitonErinaGabriel novoCliente = new Cliente_CleitonErinaGabriel(cpf, nome, rg, dataNascimento, endereco, cep, telefone, email);
        return clientes.add(novoCliente);
    }

    //FIXME:
    //      Code smell
    public boolean novoCorretor(String cpf, String nome, String rg, LocalDate dataNascimento, String endereco, String cep,
                                String telefone, String email, String creci, float salario, String pis, LocalDate dataAdmissao){
        Usuario_CleitonErinaGabriel novoCorretor = new Corretor_CleitonErinaGabriel( cpf, nome, rg, dataNascimento, endereco, cep, telefone, email, creci, salario, pis, dataAdmissao);
        return corretores.add(novoCorretor);
    }

    //FIXME:
    //      Code smell
    //Novo Prédio residencial
    public boolean novoImovel(String endereco, LocalDate dataConstrucao, float areaTotal, float areaConstruida,
                              int qtdDormitórios, int qtdBanheiros, int qtdVagasGaragem, float valorIPTU, float valorVenda,
                              float valorAluguel, Operacao tipoOperacao, int andar, int numApto, float valorCondominio){
        PredioResidencial_CleitonErinaGabriel novoAp = new PredioResidencial_CleitonErinaGabriel(endereco,dataConstrucao,areaTotal,areaConstruida,qtdDormitórios,qtdBanheiros,qtdVagasGaragem,valorIPTU,valorVenda,valorAluguel,tipoOperacao,andar,numApto,valorCondominio);
        return imoveis.add(novoAp);
    }

    //FIXME:
    //      Code smell
    //Nova Casa
    public boolean novoImovel(String endereco, LocalDate dataConstrucao, float areaTotal, float areaConstruida,
                              int qtdDormitórios, int qtdBanheiros, int qtdVagasGaragem, float valorIPTU, float valorVenda,
                              float valorAluguel, Operacao tipoOperacao){
        CasaResidencial_CleitonErinaGabriel novaCasa = new CasaResidencial_CleitonErinaGabriel(endereco, dataConstrucao, areaTotal, areaConstruida, qtdDormitórios, qtdBanheiros, qtdVagasGaragem, valorIPTU, valorVenda, valorAluguel, tipoOperacao);
        return imoveis.add(novaCasa);
    }

    //FIXME:
    //      Code smell
    //Novo Predio Comeercial
    public boolean novoImovel(String endereco, LocalDate dataConstrucao, float areaTotal, float areaConstruida, int qtdDormitórios, int qtdBanheiros, int qtdVagasGaragem, float valorIPTU, float valorVenda, float valorAluguel, Operacao tipoOperacao, float taxaImpostoFederal){
        Comercial_CleitonErinaGabriel novoComercio = new Comercial_CleitonErinaGabriel(endereco, dataConstrucao, areaTotal, areaConstruida, qtdDormitórios, qtdBanheiros, qtdVagasGaragem, valorIPTU, valorVenda, valorAluguel, tipoOperacao, taxaImpostoFederal);
        return imoveis.add(novoComercio);
    }

    //FIXME:
    //      Melhorar pagamento
    public boolean novaVenda(String codCliente, String codCorretor, String codImovel, float valorTotalVenda, Pagamento_CleitonErinaGabriel formaPagamento){
        Cliente_CleitonErinaGabriel cliente = buscaCliente(codCliente);
        Corretor_CleitonErinaGabriel corretor = buscaCorretor(codCorretor);
        Imovel_CleitonErinaGabriel imovel = buscaImovel(codImovel);
        if(cliente == null || corretor ==null || imovel == null)
            return false;
        Venda_CleitonErinaGabriel venda = new Venda_CleitonErinaGabriel(cliente, corretor, imovel, valorTotalVenda, formaPagamento);
        return vendas.add(venda);
    }
    //FIXME
    public boolean novoAluguel(String  codCliente, String  codCorretor,  String codImovel, LocalDate dataDevolucao, LocalDate dataPagamentoMensal, Pagamento_CleitonErinaGabriel formaPagamento, List<String> segurosContratados) {
        Cliente_CleitonErinaGabriel cliente = buscaCliente(codCliente);
        Corretor_CleitonErinaGabriel corretor = buscaCorretor(codCorretor);
        Imovel_CleitonErinaGabriel imovel = buscaImovel(codImovel);
        if(cliente == null || corretor ==null || imovel == null)
            return false;
        ArrayList<Seguro_CleitonErinaGabriel> segAl = new ArrayList<>();
        Seguro_CleitonErinaGabriel seg;
        for(String codSeg:segurosContratados){
            seg = buscaSeguro(codSeg);
            if(seg != null){
                segAl.add(seg);
            }
        }
        Aluguel_CleitonErinaGabriel aluguel = new Aluguel_CleitonErinaGabriel(cliente,corretor,imovel,dataDevolucao,dataPagamentoMensal,formaPagamento,segAl);
        return alugueis.add(aluguel);
    }

    public boolean novoSeguro(String nomeSeguradora, String tipo, String descricao, float valor){
        return seguros.add(new Seguro_CleitonErinaGabriel(nomeSeguradora, tipo, descricao,valor));
    }

    public Pagamento_CleitonErinaGabriel novoPagamento(String tipoPagamento, String nome, String bandeira, String numero){
        return new Cartao_CleitonErinaGabriel(tipoPagamento,nome,bandeira,numero);
    }

    public Pagamento_CleitonErinaGabriel novoPagamento(String tipoPagamento){
        return new Dinheiro_CleitonErinaGabriel(tipoPagamento);
    }



    /*******************************************************************************************************************
     * Métodos de busca
     *******************************************************************************************************************/
    public Cliente_CleitonErinaGabriel buscaCliente(String codCliente){
        for(Usuario_CleitonErinaGabriel c:clientes){
            if(c.getCodigoUsuario().equals(codCliente))
                return (Cliente_CleitonErinaGabriel) c;
        }
        return null;
    }

    public Corretor_CleitonErinaGabriel buscaCorretor(String codCorretor){
        for(Usuario_CleitonErinaGabriel c:corretores){
            if(c.getCodigoUsuario().equals(codCorretor))
                return (Corretor_CleitonErinaGabriel) c;
        }
        return null;
    }

    public Imovel_CleitonErinaGabriel buscaImovel(String codImovel){
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i.getCodigoImovel().equals(codImovel)){
                return i;
            }
        }
        return null;
    }

    public Seguro_CleitonErinaGabriel buscaSeguro(String codSeguro){
        for(Seguro_CleitonErinaGabriel s:seguros){
            if(s.getCodigoSeguro().equals(codSeguro)){
                return s;
            }
        }
        return null;
    }

    /*******************************************************************************************************************/

    public String listarImoveis(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis cadastrados:\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            sb.append(i).append("\n");
            sb.append("\n");
        }
        return sb.toString();
    }

    public String listarCasaResidencial(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todas as casa residenciais cadastradas:\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i instanceof CasaResidencial_CleitonErinaGabriel) {
                sb.append(i).append("\n");
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String listarPredioResidencial(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todas as casa residenciais cadastradas:\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i instanceof PredioResidencial_CleitonErinaGabriel) {
                sb.append(i).append("\n");
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String listarImoveisComerciais(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todas as casa residenciais cadastradas:\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i instanceof Comercial_CleitonErinaGabriel) {
                sb.append(i).append("\n");
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String listarImoveisLocacao(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis disponiveis para locação\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i.getTipoOperacao() == Operacao.ALUGUEL && i.isDisponivel()){
                sb.append(i).append("\n");
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String listarImoveisAlugados(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis alugados\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i.getTipoOperacao() == Operacao.ALUGUEL && !i.isDisponivel()){
                sb.append(i).append("\n");
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String listarImoveisAVenda(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis disponiveis para venda\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i.getTipoOperacao() == Operacao.VENDA && i.isDisponivel()){
                sb.append(i).append("\n");
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String listarImoveisVendidos(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis vendidos\n");
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i.getTipoOperacao() == Operacao.VENDA && !i.isDisponivel()){
                sb.append(i).append("\n");
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String listarAlugueisAtrasados(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis com Aluguel atrasado\n");
        for(Aluguel_CleitonErinaGabriel aluguel: alugueis){
            if(!aluguel.isPago() && aluguel.getDataPagamentoMensal().getDayOfMonth() < LocalDate.now().getDayOfMonth()){
                sb.append(aluguel).append("\n");
            }
        }
        return sb.toString();
    }

    public String listarImoveisAlugadosCliente(String codCliente){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis alugados ao Cliente :").append(codCliente).append("\n");
        for(Aluguel_CleitonErinaGabriel aluguel:alugueis){
            if(aluguel.getCliente().getCodigoUsuario().equals(codCliente)){
                sb.append(aluguel).append("\n");
            }
        }
        return sb.toString();
    }

    public String listarImoveisVendidosCliente(String codCliente){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Imóveis vendidos ao Cliente :").append(codCliente).append("\n");
        for(Aluguel_CleitonErinaGabriel aluguel:alugueis){
            if(aluguel.getCliente().getCodigoUsuario().equals(codCliente)){
                sb.append(aluguel).append("\n");
            }
        }
        return sb.toString();
    }

    public String listarTodosCorretores(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos os Corretores:\n");
        for(Usuario_CleitonErinaGabriel corretor:corretores){
            sb.append(corretor).append("\n");
        }
        return sb.toString();
    }

    public String listarTodosClientes(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos os Clientes:\n");
        for(Usuario_CleitonErinaGabriel cliente:clientes){
            sb.append(cliente).append("\n");
        }
        return sb.toString();
    }

    public String listarAlugueisAtrasadosClientes(){
        StringBuilder sb = new StringBuilder();
        Cliente_CleitonErinaGabriel cl;
        sb.append("Todos Clientes com Aluguel atrasado\n");
        for(Aluguel_CleitonErinaGabriel aluguel: alugueis){
            if(!aluguel.isPago() && aluguel.getDataPagamentoMensal().getDayOfMonth() < LocalDate.now().getDayOfMonth()){
                cl = aluguel.getCliente();
                sb.append("Código: ").append(cl.getCodigoUsuario()).append("\n").append(cl.getNome()).append("\n");
            }
        }
        return sb.toString();
    }

    public String listaAlugueisFinalizados(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Alugueis Finalizados\n");
        for(Aluguel_CleitonErinaGabriel aluguel:alugueis){
            if(aluguel.isFinalizado()){
                sb.append(aluguel).append("\n");
            }
        }
        return sb.toString();
    }

    public String listaAlugueisAtivos(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos Alugueis ativos\n");
        for(Aluguel_CleitonErinaGabriel aluguel:alugueis){
            if(!aluguel.isFinalizado()){
                sb.append(aluguel).append("\n");
            }
        }
        return sb.toString();
    }

    public String listaVendas(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todas as Vendas:\n");
        for(Venda_CleitonErinaGabriel venda:vendas){
            sb.append(venda);
        }
        return sb.toString();
    }


    public String vendaPorMes(LocalDate fimMes){
        StringBuilder sb = new StringBuilder();
        LocalDate inicioMes = fimMes.minusDays(30);
        float totalLucro = 0;
        sb.append("Todas Vendas Realizados no mês\n").append(inicioMes).append(" até ").append(fimMes).append("\n");
        for(Venda_CleitonErinaGabriel venda:vendas){
            if(venda.getDataVenda().isAfter(inicioMes) && (venda.getDataVenda().isEqual(fimMes)||venda.getDataVenda().isBefore(fimMes))){
                totalLucro += venda.getValorTotalVenda();
                sb.append(venda).append("\n");
            }
        }
        sb.append("Lucro no periodo: ").append(totalLucro).append("\n");
        return sb.toString();
    }

    public String todosSeguros(){
        StringBuilder sb = new StringBuilder();
        sb.append("Todos seguros cadastrados: \n");
        for(Seguro_CleitonErinaGabriel seguro:seguros){
            sb.append(seguro).append("\n");
        }
        return sb.toString();
    }



}
