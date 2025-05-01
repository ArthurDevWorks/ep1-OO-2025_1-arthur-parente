package entities;

import enums.Sexo;

import java.util.Date;

public class Pessoa {
    private String nome;
    private int matricula;
    private Date nascimento;
    private Sexo sexo;
    private String email;

    public Pessoa(String nome, int matricula, Date nascimento, Sexo sexo, String email) {
        this.nome = nome;
        this.matricula = matricula;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
