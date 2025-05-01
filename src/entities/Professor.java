package entities;

import enums.Sexo;

import java.util.Date;

public class Professor extends Pessoa {

    private double salario;

    public Professor(String nome, int matricula, Date nascimento, Sexo sexo, String email, double salario) {
        super(nome, matricula, nascimento, sexo, email);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
