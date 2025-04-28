import java.util.Date;

public class Pessoa {
    private String nome;
    private Date nascimento;
    private  Sexo sexo;
    private String telefone;

    public Pessoa(String nome, Date nascimento, Sexo sexo, String telefone) {
        this.nome = nome;
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
