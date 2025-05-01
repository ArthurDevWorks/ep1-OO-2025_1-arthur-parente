package entities;

import enums.Sexo;

import java.util.Date;

public class Professor extends Pessoa {

    private Turma turma;

    public Professor(String nome, int matricula, Date nascimento, Sexo sexo, String telefone, Turma turma) {
        super(nome, matricula, nascimento, sexo, telefone);
        this.turma = turma;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

}
