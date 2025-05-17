package persistence;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import persistence.FileManager;
import java.io.IOException;
import java.util.ArrayList;
import entities.Aluno;
import java.util.Date;
import java.util.List;
import enums.Sexo;

public class AlunoDAO {
    private static final String FILE_NAME = "alunos.txt"; //Define o nome do arquivo
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //formata a data

    //Salva o aluno no arquivo
    public static void save(Aluno aluno) throws IOException {
        List<String> data = new ArrayList<>();
        data.add(alunoToLine(aluno));
        FileManager.saveToFile(FILE_NAME, data);
    }

    //Lista os alunos
    public static List<Aluno> findAll() throws IOException, ParseException {
        List<String> lines = FileManager.readFromFile(FILE_NAME);
        List<Aluno> alunos = new ArrayList<>();

        for (String line : lines) {
            alunos.add(lineToAluno(line));
        }

        return alunos;
    }

   //Procura o aluno por matricula
   public static Aluno findByMatricula(int matricula) {
       try {
           List<String> lines = FileManager.readFromFile(FILE_NAME);

           for (String line : lines) {
               String[] parts = line.split("\\|");
               if (Integer.parseInt(parts[4]) == matricula) { // A matrícula está no índice 4
                   return lineToAluno(line);
               }
           }
       } catch (IOException | ParseException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
           System.err.println("Erro ao buscar aluno: " + e.getMessage());
       }
       return null;
   }

   //Atualiza o registro do aluno
    public static void update(Aluno aluno) throws IOException, ParseException {
        List<Aluno> alunos = findAll();
        boolean found = false;

        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getMatricula() == aluno.getMatricula()) {
                alunos.set(i, aluno);
                found = true;
                break;
            }
        }

        if (!found) {
            alunos.add(aluno);
        }

        saveAll(alunos);
    }

    private static void saveAll(List<Aluno> alunos) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Aluno aluno : alunos) {
            lines.add(alunoToLine(aluno));
        }
        FileManager.saveToFile(FILE_NAME, lines);
    }

    private static String alunoToLine(Aluno aluno) {
        return String.format("%d|%s|%s|%s|%s|%s|%b",
                aluno.getMatricula(),
                aluno.getNome(),
                dateFormat.format(aluno.getNascimento()),
                aluno.getSexo(),
                aluno.getEmail(),
                aluno.getCurso(),
                aluno.isEspecial());
    }

    private static Aluno lineToAluno(String line) throws ParseException {
        String[] parts = line.split("\\|");
        Date nascimento = dateFormat.parse(parts[1]);

        return new Aluno(
                parts[0],               // nome
                nascimento,             // nascimento
                Sexo.valueOf(parts[2]), // sexo
                parts[3],               // email
                Integer.parseInt(parts[4]), // matricula
                parts[5],               // curso
                Boolean.parseBoolean(parts[6]) // especial
        );
    }
}