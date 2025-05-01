package entities;

import enums.Modalidade;

public class Disciplina {
    private String nome;
    private String codigo;
    private int semestre;
    private int qtdHoras;

    private Modalidade modalidade;
    private  int formaAvaliacao;

    public Disciplina(String nome, String codigo, int semestre, int qtdHoras, Modalidade modalidade, int formaAvaliacao) {
        this.nome = nome;
        this.codigo = codigo;
        this.semestre = semestre;
        this.qtdHoras = qtdHoras;
        this.modalidade = modalidade;
        this.formaAvaliacao = formaAvaliacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getQtdHoras() {
        return qtdHoras;
    }

    public void setQtdHoras(int qtdHoras) {
        this.qtdHoras = qtdHoras;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public int getFormaAvaliacao() {
        return formaAvaliacao;
    }

    public void setFormaAvaliacao(int formaAvaliacao) {
        this.formaAvaliacao = formaAvaliacao;
    }

}
