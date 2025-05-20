package entities;

import enums.Sexo;

import java.util.Date;

public class Professor extends Pessoa {

    private Date contratacao;

    public Professor(String nome, Date nascimento, Sexo sexo, String email, int matricula, Date contratacao) {
        super(nome, nascimento, sexo, email, matricula);
        this.contratacao = contratacao;
    }

    public Professor(String nome){
        super(nome);
    }

    public Date getContratacao() {
        return contratacao;
    }

    public void setContratacao(Date contratacao) {
        this.contratacao = contratacao;
    }
}
