package entities;

import enums.Sexo;

import java.util.Date;

public abstract class Pessoa {
    private String nome;
    private Date nascimento;
    private Sexo sexo;
    private String email;
    private int matricula;

    public Pessoa(String nome, Date nascimento, Sexo sexo, String email, int matricula) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.email = email;
        this.matricula = matricula;
    }

    public Pessoa(String nome) {
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
