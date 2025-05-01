package entities;

import enums.Sexo;

import java.util.Date;

public class Pessoa {
    private String nome;

    private int matricula;
    private Date nascimento;
    private Sexo sexo;
    private String telefone;

    public Pessoa(String nome, int matricula, Date nascimento, Sexo sexo, String telefone) {
        this.nome = nome;
        this.matricula = matricula;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.telefone = telefone;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
