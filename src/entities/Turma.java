package entities;

public class Turma {
    private int codigo;
    private int qtdVagas;
    private String sala;
    private String horario;
    private Professor professor;

    public Turma(int codigo, int qtdVagas, String sala, String horario, Professor professor) {
        this.codigo = codigo;
        this.qtdVagas = qtdVagas;
        this.sala = sala;
        this.horario = horario;
        this.professor = professor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQtdVagas() {
        return qtdVagas;
    }

    public void setQtdVagas(int qtdVagas) {
        this.qtdVagas = qtdVagas;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

}
