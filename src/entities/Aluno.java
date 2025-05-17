package entities;

import enums.Sexo;

import java.util.Date;

public class Aluno extends Pessoa {
    private String curso;
    private  boolean especial;

    public Aluno(String nome, Date nascimento, Sexo sexo,  String email, int matricula, String curso, boolean especial) {
        super(nome, nascimento, sexo, email,matricula);
        this.curso = curso;
        this.especial = especial;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

}
