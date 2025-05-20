package persistence;

import entities.Aluno;
import entities.Professor;
import enums.Sexo;
import persistence.FileManager;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfessorDAO {
    private static final String FILE_NAME = "professores.txt";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void save(Professor professor) throws IOException {
        List<String> data = new ArrayList<>();
        data.add(professorToLine(professor));
        FileManager.saveToFile(FILE_NAME, data, true);
    }

    public static List<Professor> findAll() throws IOException, ParseException {
        List<String> lines = FileManager.readFromFile(FILE_NAME);
        List<Professor> professores = new ArrayList<>();

        for (String line : lines) {
            professores.add(lineToProfessor(line));
        }

        return professores;
    }

    public static Professor findByMatricula(int matricula) throws IOException, ParseException {
        List<Professor> professores = findAll();
        for (Professor professor : professores) {
            if (professor.getMatricula() == matricula) {
                return professor;
            }
        }
        return null;
    }

    public static void update(Professor professor) throws IOException, ParseException {
        List<Professor> professores = findAll();
        boolean found = false;

        for (int i = 0; i < professores.size(); i++) {
            if (professores.get(i).getMatricula() == professor.getMatricula()) {
                professores.set(i, professor);
                found = true;
                break;
            }
        }

        if (!found) {
            professores.add(professor);
        }

        saveAll(professores);
    }

    private static void saveAll(List<Professor> professores) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Professor professor : professores) {
            lines.add(professorToLine(professor));
        }
        FileManager.saveToFile(FILE_NAME, lines, false);
    }

    private static String professorToLine(Professor professor) {
        return String.format("%d|%s|%s|%s|%s|%s",
                professor.getMatricula(),
                professor.getNome(),
                dateFormat.format(professor.getNascimento()),
                professor.getSexo(),
                professor.getEmail(),
                professor.getContratacao());
    }

    private static Professor lineToProfessor(String line) throws ParseException {
        String[] parts = line.split("\\|");
        Date nascimento = dateFormat.parse(parts[2]);
        Date contratacao = dateFormat.parse(parts[2]);

        return new Professor(
                parts[1],               // nome
                nascimento,             // nascimento
                Sexo.valueOf(parts[3]), // sexo
                parts[4],               // email
                Integer.parseInt(parts[0]), // matricula
                contratacao // contratacao
        );
    }
}