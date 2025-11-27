package com.imobiliaria.model;

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
import com.imobiliaria.model.relatorio.*;
import com.imobiliaria.model.usuario.Cliente_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Corretor_CleitonErinaGabriel;
import com.imobiliaria.model.usuario.Usuario_CleitonErinaGabriel;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Imobiliaria_CleitonErinaGabriel {
    private static volatile Imobiliaria_CleitonErinaGabriel instance;

    private String NOME = "Imobiliaria Yeager";
    private String ENDERECO = "Shiganshina, 660";
    private ArrayList<Aluguel_CleitonErinaGabriel> alugueis;
    private ArrayList<Venda_CleitonErinaGabriel> vendas;
    private ArrayList<Imovel_CleitonErinaGabriel> imoveis;
    private ArrayList<Usuario_CleitonErinaGabriel> clientes;
    private ArrayList<Usuario_CleitonErinaGabriel> corretores;
    private ArrayList<Seguro_CleitonErinaGabriel> seguros;

    private Imobiliaria_CleitonErinaGabriel() {
        this.alugueis = carregarArquivo(Configuracao_CleitonErinaGabriel.ARQUIVO_ALUGUEIS);
        atualizarAtrasos();
        this.vendas = carregarArquivo(Configuracao_CleitonErinaGabriel.ARQUIVO_VENDAS);
        this.imoveis = carregarArquivo(Configuracao_CleitonErinaGabriel.ARQUIVO_IMOVEIS);
        this.clientes = carregarArquivo(Configuracao_CleitonErinaGabriel.ARQUIVO_CLIENTES);
        this.corretores = carregarArquivo(Configuracao_CleitonErinaGabriel.ARQUIVO_CORRETORES);
        this.seguros = carregarArquivo(Configuracao_CleitonErinaGabriel.ARQUIVO_SEGUROS);
    }

    public static Imobiliaria_CleitonErinaGabriel getInstance(){
        if(instance == null){
            synchronized (Imobiliaria_CleitonErinaGabriel.class){
                if(instance == null) instance = new Imobiliaria_CleitonErinaGabriel();
            }
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> ArrayList<T> carregarArquivo(String path) {
        File arquivo = new File(path);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (ArrayList<T>) ois.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("Classe não encontrada durante desserialização: " + e.getMessage());
        } catch (IOException e) {
            return new ArrayList<>();
        } catch (ClassCastException e) {
            System.err.println("Erro ao converter objeto para ArrayList: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public static <T extends Serializable> boolean salvarArquivo(ArrayList<T> arq, String path) {
        try {
            File dir = new File(Configuracao_CleitonErinaGabriel.DIR);
            if (!dir.exists()) {
                dir.mkdirs();

            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
                oos.writeObject(arq);
                return true;
            }
        } catch (IOException e) {
            System.err.println("Erro ao Salvar arquivo: " + e.getMessage());
            return false;
        }
    }

    private void atualizarAtrasos(){
        for(Aluguel_CleitonErinaGabriel a:alugueis){
            a.atualizaDataPagamento();
        }
    }

    public void salvarTodosArrayList() {
        salvarArquivo(alugueis, Configuracao_CleitonErinaGabriel.ARQUIVO_ALUGUEIS);
        salvarArquivo(vendas, Configuracao_CleitonErinaGabriel.ARQUIVO_VENDAS);
        salvarArquivo(imoveis, Configuracao_CleitonErinaGabriel.ARQUIVO_IMOVEIS);
        salvarArquivo(seguros, Configuracao_CleitonErinaGabriel.ARQUIVO_SEGUROS);
        salvarArquivo(clientes, Configuracao_CleitonErinaGabriel.ARQUIVO_CLIENTES);
        salvarArquivo(corretores, Configuracao_CleitonErinaGabriel.ARQUIVO_CORRETORES);
    }

    public String getEndereco() {
        return ENDERECO;
    }

    public String getNome() {
        return NOME;
    }

    public List<String> getCodigosImoveisDisp(Operacao op){
        List<String> codigos = new ArrayList<>();
        for(Imovel_CleitonErinaGabriel i:imoveis){
            if(i.isDisponivel() && i.getTipoOperacao() == op){
                codigos.add(i.getCodigoImovel());
            }
        }
        return codigos;
    }

    public List<String> getCodigosCorretores(){
        List<String> codigos = new ArrayList<>();
        for(Usuario_CleitonErinaGabriel c:corretores){
            codigos.add(c.getCodigoUsuario());
        }
        return codigos;
    }

    public List<String> getCodigosClientes(){
        List<String> codigos = new ArrayList<>();
        for(Usuario_CleitonErinaGabriel c:clientes){
            codigos.add(c.getCodigoUsuario());
        }
        return codigos;
    }

    /*******************************************************************************************************************
     * Cadastros
     *******************************************************************************************************************/

    public String novoCliente(String cpf, String nome, String rg, LocalDate dataNascimento, String endereco, String cep, String telefone, String email) {
        Usuario_CleitonErinaGabriel novoCliente = new Cliente_CleitonErinaGabriel(cpf, nome, rg, dataNascimento, endereco, cep, telefone, email);
        clientes.add(novoCliente);
        return novoCliente.getCodigoUsuario();
    }

    public String novoCorretor(String cpf, String nome, String rg, LocalDate dataNascimento, String endereco, String cep,
                                String telefone, String email, String creci, float salario, String pis, LocalDate dataAdmissao) {
        Usuario_CleitonErinaGabriel novoCorretor = new Corretor_CleitonErinaGabriel(cpf, nome, rg, dataNascimento, endereco, cep, telefone, email, creci, salario, pis, dataAdmissao);
        corretores.add(novoCorretor);
        return novoCorretor.getCodigoUsuario();
    }

    public String  novoImovel(Imovel_CleitonErinaGabriel imovel){
        imoveis.add(imovel);
        return imovel.getCodigoImovel();
    }

    public boolean novaVenda(Venda_CleitonErinaGabriel venda) {
        return vendas.add(venda);
    }

    public boolean novoAluguel(Aluguel_CleitonErinaGabriel aluguel) {
        return  alugueis.add(aluguel);
    }

    public String novoSeguro(String nomeSeguradora, String tipo, String descricao, float valor) {
        Seguro_CleitonErinaGabriel novoSeguro = new Seguro_CleitonErinaGabriel(nomeSeguradora, tipo, descricao, valor);
        seguros.add(novoSeguro);
        return novoSeguro.getCodigoSeguro();
    }

    public Pagamento_CleitonErinaGabriel novoPagamento(String tipoPagamento, String nome, String bandeira, String numero) {
        return new Cartao_CleitonErinaGabriel(tipoPagamento, nome, bandeira, numero);
    }

    public Pagamento_CleitonErinaGabriel novoPagamento(String tipoPagamento) {
        return new Dinheiro_CleitonErinaGabriel(tipoPagamento);
    }


    /*******************************************************************************************************************
     * Métodos de busca
     *******************************************************************************************************************/
    public Cliente_CleitonErinaGabriel buscaCliente(String codCliente) {
        for (Usuario_CleitonErinaGabriel c : clientes) {
            if (c.getCodigoUsuario().equals(codCliente))
                return (Cliente_CleitonErinaGabriel) c;
        }
        return null;
    }

    public Corretor_CleitonErinaGabriel buscaCorretor(String codCorretor) {
        for (Usuario_CleitonErinaGabriel c : corretores) {
            if (c.getCodigoUsuario().equals(codCorretor))
                return (Corretor_CleitonErinaGabriel) c;
        }
        return null;
    }

    public Imovel_CleitonErinaGabriel buscaImovel(String codImovel) {
        for (Imovel_CleitonErinaGabriel i : imoveis) {
            if (i.getCodigoImovel().equals(codImovel)) {
                return i;
            }
        }
        return null;
    }

    public Seguro_CleitonErinaGabriel buscaSeguro(String codSeguro) {
        for (Seguro_CleitonErinaGabriel s : seguros) {
            if (s.getCodigoSeguro().equals(codSeguro)) {
                return s;
            }
        }
        return null;
    }

    public ArrayList<Seguro_CleitonErinaGabriel> buscaSeguros(List<String> codigosSelecionados) {
        return seguros.stream()
                .filter(seg -> codigosSelecionados.contains(seg.getCodigoSeguro()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /*******************************************************************************************************************/

    public String listarImoveis() {
        RelatorioTemplate relatorio = new RelatorioListaImoveis(imoveis, "Todos os Imóveis");
        return relatorio.gerarRelatorio();
    }

    public String listarCasaResidencial() {
        List<Imovel_CleitonErinaGabriel> casas = imoveis.stream()
                .filter(i -> i instanceof CasaResidencial_CleitonErinaGabriel)
                .toList();

        RelatorioTemplate relatorio = new RelatorioListaImoveis(casas, "Casas Residenciais");
        return relatorio.gerarRelatorio();
    }

    public String listarPredioResidencial() {
        List<Imovel_CleitonErinaGabriel> predio = imoveis.stream()
                .filter(i -> i instanceof PredioResidencial_CleitonErinaGabriel)
                .toList();
        RelatorioTemplate relatorio = new RelatorioListaImoveis(predio, "Prédios Residenciais");
        return relatorio.gerarRelatorio();
    }

    public String listarImoveisComerciais() {
        List<Imovel_CleitonErinaGabriel> comercios = imoveis.stream()
                .filter(i->i instanceof Comercial_CleitonErinaGabriel)
                .toList();
        RelatorioTemplate relatorio = new RelatorioListaImoveis(comercios, "Imóveis comerciais");
        return relatorio.gerarRelatorio();
    }

    public String listarImoveisLocacao() {
        List<Imovel_CleitonErinaGabriel> im = imoveis.stream()
                .filter(i->i.getTipoOperacao() == Operacao.ALUGUEL && i.isDisponivel())
                .toList();
        RelatorioTemplate relatorio = new RelatorioListaImoveis(im,"Todos Imóveis disponíveis para aluguel");
        return relatorio.gerarRelatorio();
    }

    public String listarImoveisAlugados() {
        List<Imovel_CleitonErinaGabriel> im = imoveis.stream()
                .filter(i->i.getTipoOperacao() == Operacao.ALUGUEL && !i.isDisponivel())
                .toList();
        RelatorioTemplate relatorio = new RelatorioListaImoveis(im, "Todos Imóveis alugados");
        return relatorio.gerarRelatorio();
    }

    public String listarImoveisAVenda() {
        List<Imovel_CleitonErinaGabriel> im = imoveis.stream()
                .filter(i -> i.getTipoOperacao() == Operacao.VENDA && i.isDisponivel())
                .toList();
        RelatorioTemplate relatorio = new RelatorioListaImoveis(im, "Todos Imóveis disponiveis para venda");
        return relatorio.gerarRelatorio();
    }

    public String listarImoveisVendidos() {
        List<Imovel_CleitonErinaGabriel> im = imoveis.stream()
                .filter(i->i.getTipoOperacao() == Operacao.VENDA && !i.isDisponivel())
                .toList();
        RelatorioTemplate relatorio = new RelatorioListaImoveis(im, ("Todos Imóveis vendidos"));
        return relatorio.gerarRelatorio();
    }

    public String listarAlugueisAtrasados() {
        List<Aluguel_CleitonErinaGabriel> al = alugueis.stream()
                .filter(aluguel -> !aluguel.isPago() && aluguel.getDataPagamentoMensal().getDayOfMonth() < LocalDate.now().getDayOfMonth())
                .toList();
        RelatorioTemplate relatorio = new RelatorioAlugueis(al, "Todos Imóveis com Aluguel atrasado");
        return relatorio.gerarRelatorio();
    }

    public String listarImoveisAlugadosCliente(String codCliente) {
        List<Aluguel_CleitonErinaGabriel> al = alugueis.stream()
                .filter(aluguel -> aluguel.getCliente().getCodigoUsuario().equals(codCliente))
                .toList();
        RelatorioTemplate relatorio = new RelatorioAlugueis(al,"Todos Imóveis alugados ao Cliente: " + codCliente);
        return relatorio.gerarRelatorio();
    }

    public String listarImoveisVendidosCliente(String codCliente) {
        List<Venda_CleitonErinaGabriel> vd = vendas.stream()
                        .filter(venda ->venda.getCliente().getCodigoUsuario().equals(codCliente))
                        .toList();
        RelatorioTemplate relatorio = new RelatorioVendas(vd, "Todos Imóveis vendidos ao Cliente: "+codCliente);
        return relatorio.gerarRelatorio();
    }

    public String listarTodosCorretores() {
        return new RelatorioUsuarios(corretores,"Todos os Corretores").gerarRelatorio();
    }

    public String listarTodosClientes() {
        return new RelatorioUsuarios(clientes, "Todos os Clientes").gerarRelatorio();
    }

    public String listarAlugueisAtrasadosClientes() {
        List<Cliente_CleitonErinaGabriel> cl = alugueis.stream()
                .filter(aluguel -> !aluguel.isPago() && aluguel.getDataPagamentoMensal().isBefore(LocalDate.now()))
                .map(Aluguel_CleitonErinaGabriel::getCliente).distinct().toList();
        RelatorioTemplate relatorio = new RelatorioUsuarios(cl, "Cliente com Alugueis Atrasados");
        return relatorio.gerarRelatorio();
    }

    public String listarAlugueisFinalizados() {
        List<Aluguel_CleitonErinaGabriel> al = alugueis.stream()
                        .filter(Aluguel_CleitonErinaGabriel::isFinalizado).toList();
        RelatorioTemplate relatorio = new RelatorioAlugueis(al,"Todos Alugueis Finalizados");
        return relatorio.gerarRelatorio();
    }

    public String listarAlugueisAtivos() {
        List<Aluguel_CleitonErinaGabriel> al = alugueis.stream()
                        .filter(aluguel -> !aluguel.isFinalizado()).toList();
        RelatorioTemplate relatorio = new RelatorioAlugueis(al,"Todos Alugueis ativos");
        return relatorio.gerarRelatorio();
    }

    public String listarTodasVendas() {
        return new RelatorioVendas(vendas,"Todas as Vendas").gerarRelatorio();
    }

    public String vendaPorMes(LocalDate fimMes) {
        LocalDate inicioMes = fimMes.minusDays(30);
        List<Venda_CleitonErinaGabriel> vd = vendas.stream()
                        .filter(venda->
                                venda.getDataVenda().isAfter(inicioMes) && (venda.getDataVenda().isEqual(fimMes) || venda.getDataVenda().isBefore(fimMes)))
                        .toList();
        RelatorioTemplate relatorio = new RelatorioVendas(vd,"Todas Vendas entre"+ inicioMes+" e "+ fimMes);
        return relatorio.gerarRelatorio();
    }

    public String listaTodosSeguros() {
        return new RelatorioSeguros(seguros).gerarRelatorio();
    }


}
