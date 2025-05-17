package entities;

import enums.Sexo;

import java.util.Date;

public class Professor extends Pessoa {

    private double salario;

    public Professor(String nome, Date nascimento, Sexo sexo, String email, int matricula, double salario) {
        super(nome, nascimento, sexo, email, matricula);
        this.salario = salario;
    }

    public Professor(String nome){
        super(nome);
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
