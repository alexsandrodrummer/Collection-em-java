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
        if (this == o) 
            return true;
        if (o == null || getClass() != o.getClass()) 
            return false;
        
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
                "Data Inicial: " + dataInicio.format(FORMATO_DATA) + "\n" +
                "Data Final: " + dataFinal.format(FORMATO_DATA) + "\n" +
                "Status: " + status + "\n";
    }
}
