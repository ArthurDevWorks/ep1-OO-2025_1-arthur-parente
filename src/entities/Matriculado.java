package entities;

public class Matriculado {

    private Aluno aluno;
    private Turma turma;
    private double nota;
    private double presenca;

    public Matriculado(Aluno aluno, Turma turma, double nota, double presenca) {
        this.aluno = aluno;
        this.turma = turma;
        this.nota = nota;
        this.presenca = presenca;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public double getPresenca() {
        return presenca;
    }

    public void setPresenca(double presenca) {
        this.presenca = presenca;
    }
}
