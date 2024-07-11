Para esse projeto da  disciplina, analise o estudo de caso de um controle de estoque e desenvolva o programa para solucionar o problema.
O Gestor da empresa que você trabalha, necessita manter um registro dos projetos de TI. Este controle, atualmente, não existe... as informações e iniciativas estão de forma informal em troca de e-mails e mensagens.
Nesta primeira versão, o gestor solicita que você registre os seguintes dados dos projetos:
Nome
Objetivo
Necessidades
Data de início (data no formato dd/mm/aaaa)
Data Final (data no formato dd/mm/aaaa)
Status
O objetivo desta primeira versão é que seja possível incluir, alterar, listar todos, buscar por nome e excluir os dados. Possibilitando um registro único de cada projeto,
no qual para identificar se é único consiste em não existir projetos com nomes duplicados.O Gestor espera que você desenvolva a aplicação no qual exista tratamento dos erros para os cenários possíveis.
Desenvolva um menu para que possa controlar as funcionalidades. A funcionalidade de listar e consultar deverá exibir os dados do projeto. Exemplo:
Projeto:
Nome:
Objetivo:
Necessidades:
Data Início:
Data Final:
Status:

Complete o programa em Java para que possa controlar o cadastro dos dados por meio de uma Collection:
            Implemente a classe “ProjetoImpl” para que resolva as funcionalidades que se espera no programa.​
​
*** NÃO ENVIAR O ARQUIVO (.EXE). ***

CÓDIGO DO PROGRAMA A SER IMPLEMENTADO:
 
*** PRINCIPAL ***
package br.unicesumar.aula;
 
import br.unicesumar.aula.controle.ProjetoDAO;
import br.unicesumar.aula.controle.ProjetoImpl;
import br.unicesumar.aula.exceptions.DadoConsultadoException;
import br.unicesumar.aula.modelo.Projeto;
 
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
 
public class Principal {
    public static void main(String[] args) {
        ProjetoDAO dao = new ProjetoImpl();
        int opcao;
        do{
            System.out.println("Registro de projetos");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Alterar");
            System.out.println("3 - Listar");
            System.out.println("4 - Localizar por nome");
            System.out.println("5 - Excluir");
            System.out.println("\n0 - Sair");
            Scanner scannerMenu = new Scanner(System.in);
            opcao = scannerMenu.nextInt();
            try {
                switch (opcao){
                    case 1:
                        Projeto projeto =new Projeto();
                        projeto.setNome(coletarTexto("Digite o nome do projeto"));
                        projeto.setObjetivo(coletarTexto("Digite os objetivos do projeto"));
                        projeto.setNecessidades(coletarTexto("Digite as necessidades do projeto"));
                        projeto.setDataInicio(coletarTexto("Digite a data de início do projeto"));
                        projeto.setDataFinal(coletarTexto("Digite a data final do projeto"));
                        projeto.setStatus(coletarTexto("Digite o status do projeto"));
                        dao.adicionar(projeto);
                        break;
                    case 2:
                        String nomeConsultaAlterar = coletarTexto("Digite o nome do projeto que está buscando para alterar");
                        Projeto projetoNovo = new Projeto();
                        projetoNovo.setNome(coletarTexto("Digite o nome do projeto"));
                        projetoNovo.setObjetivo(coletarTexto("Digite os objetivos do projeto"));
                        projetoNovo.setNecessidades(coletarTexto("Digite as necessidades do projeto"));
                        projetoNovo.setDataInicio(coletarTexto("Digite a data de início do projeto"));
                        projetoNovo.setDataFinal(coletarTexto("Digite a data final do projeto"));
                        projetoNovo.setStatus(coletarTexto("Digite o status do projeto"));
                        dao.alterar(nomeConsultaAlterar, projetoNovo);
                        break;
                    case 3:
                        System.out.println(dao.listar());
                        break;
                    case 4:
                        String nomeConsultado = coletarTexto("Digite o nome do projeto que está buscando");
                        Projeto projetoEncontrado = dao.consultarPorNome(nomeConsultado);
                        System.out.println(projetoEncontrado);
                        break;
                    case 5:
                        String nomeConsultaExcluir = coletarTexto("Digite o nome do projeto que está buscando para excluir");
                        dao.excluir(nomeConsultaExcluir);
                        break;
                    case 0:
                        break;
                }
 
            }catch (InputMismatchException e){
                System.out.println("Não foi possível registrar corretamente a sua opção.");
            } catch (ParseException | DateTimeParseException e) {
                System.out.println("Não foi possível converter a data, tente informar dd/mm/aaaa");
            } catch (DadoConsultadoException e) {
                System.out.println(e.getMessage());
            }
            coletarTexto("----");
        }while (opcao!=0);
    }
 
    private static String coletarTexto(String descricao){
        Scanner scanner = new Scanner(System.in);
        System.out.println(descricao);
        return scanner.nextLine();
    }
}
 
 
*** JPAUtil ***
package br.unicesumar.aula.util;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 
public class JPAUtil {
    private static EntityManagerFactory entityManagerFactory;
 
    public static EntityManager getEntityManagerFactory() {
        if (entityManagerFactory==null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("projetos");
        }
        return entityManagerFactory.createEntityManager();
    }
}
 
 
*** Projeto ***
package br.unicesumar.aula.modelo;
 
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
 
public class Projeto {
 
    private String nome;
    private String objetivo;
    private String necessidades;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private String status;
 
    private final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
 
    public String getNome() {
        return nome;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }
 
    public String getObjetivo() {
        return objetivo;
    }
 
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
 
    public String getNecessidades() {
        return necessidades;
    }
 
    public void setNecessidades(String necessidades) {
        this.necessidades = necessidades;
    }
 
    public LocalDate getDataInicio() {
        return dataInicio;
    }
 
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
 
    public void setDataInicio(String dataInicio) throws ParseException {
        this.dataInicio = LocalDate.parse(dataInicio, FORMATO_DATA);
    }
 
    public LocalDate getDataFinal() {
        return dataFinal;
    }
 
    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }
 
    public void setDataFinal(String dataFinal) {
        this.dataFinal = LocalDate.parse(dataFinal, FORMATO_DATA);
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projeto projeto = (Projeto) o;
        return Objects.equals(nome, projeto.nome);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
 
    public Projeto copia() {
        Projeto projeto = new Projeto();
        projeto.setNome(this.nome);
        projeto.setObjetivo(this.objetivo);
        projeto.setNecessidades(this.necessidades);
        projeto.setDataInicio(this.dataInicio);
        projeto.setDataFinal(this.dataFinal);
        projeto.setStatus(this.status);
        return projeto;
    }
 
    public void substituir(Projeto projetoNovo) {
        this.setObjetivo(projetoNovo.getObjetivo());
        this.setNome(projetoNovo.getNome());
        this.setNecessidades(projetoNovo.getNecessidades());
        this.setNecessidades(projetoNovo.getNecessidades());
        this.setDataInicio(projetoNovo.getDataInicio());
        this.setDataFinal(projetoNovo.getDataFinal());
        this.setStatus(projetoNovo.getStatus());
    }
 
    @Override
    public String toString() {
        return "\nProjeto: \n" +
                "Nome: " + nome + "\n" +
                "Objetivo: " + objetivo + "\n" +
                "Necessidades: " + necessidades + "\n" +
                "Data Início: " + dataInicio.format(FORMATO_DATA) + "\n" +
                "Data Final: " + dataFinal.format(FORMATO_DATA) + "\n" +
                "Status: " + status + "\n";
    }
}
 
 
*** DadoConsultado***
package br.unicesumar.aula.exceptions;
 
public class DadoConsultadoException extends Exception{
    public DadoConsultadoException(String message) {
        super(message);
    }
}
 
*** ProjetoDAO ***
package br.unicesumar.aula.controle;
 
import br.unicesumar.aula.exceptions.DadoConsultadoException;
import br.unicesumar.aula.modelo.Projeto;
 
import java.util.List;
 
public interface ProjetoDAO {
 
    void adicionar(Projeto projeto);
    List<Projeto> listar();
    Projeto consultarPorNome(String nome) throws DadoConsultadoException;
    Projeto alterar(String nome, Projeto projeto) throws DadoConsultadoException;
    void excluir(Projeto projeto) throws DadoConsultadoException, UnsupportedOperationException;
    void excluir(String nome) throws DadoConsultadoException, UnsupportedOperationException;
}
 
*** ProjetoImpl ***
package br.unicesumar.aula.controle;
 
import br.unicesumar.aula.exceptions.DadoConsultadoException;
import br.unicesumar.aula.modelo.Projeto;
 
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 
public class ProjetoImpl implements ProjetoDAO {
    //Collection que irá armazenar todos os projetos
    private static Set<Projeto> projetos = new HashSet<>();
 
 
    @Override
    public void adicionar(Projeto projeto) {
 
    }
 
    @Override
    public List<Projeto> listar() {
        return null;
    }
 
    @Override
    public Projeto consultarPorNome(String nome) throws DadoConsultadoException {
        return null;
    }
 
    @Override
    public Projeto alterar(String nome, Projeto projeto) throws DadoConsultadoException {
        return null;
    }
 
    @Override
    public void excluir(Projeto projeto) throws DadoConsultadoException, UnsupportedOperationException {
 
    }
 
    @Override
    public void excluir(String nome) throws DadoConsultadoException, UnsupportedOperationException {
 
    }
}




 
