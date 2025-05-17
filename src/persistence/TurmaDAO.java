package persistence;

import entities.Disciplina;
import entities.Professor;
import entities.Turma;
import persistence.FileManager;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {
    private static final String FILE_NAME = "turmas.txt";

    public static void save(Turma turma) throws IOException {
        List<String> data = new ArrayList<>();
        data.add(turmaToLine(turma));
        FileManager.saveToFile(FILE_NAME, data);
    }

    public static List<Turma> findAll() throws IOException, ParseException {
        List<String> lines = FileManager.readFromFile(FILE_NAME);
        List<Turma> turmas = new ArrayList<>();

        for (String line : lines) {
            turmas.add(lineToTurma(line));
        }

        return turmas;
    }

    public static Turma findByCodigo(int codigo) throws IOException, ParseException {
        List<Turma> turmas = findAll();
        for (Turma turma : turmas) {
            if (turma.getCodigo() == codigo) {
                return turma;
            }
        }
        return null;
    }

    public static List<Turma> findByDisciplina(String codigoDisciplina) throws IOException, ParseException {
        List<Turma> turmas = findAll();
        List<Turma> result = new ArrayList<>();

        for (Turma turma : turmas) {
            if (turma.getDisciplina().getCodigo().equals(codigoDisciplina)) {
                result.add(turma);
            }
        }
        return result;
    }

    public static void update(Turma turma) throws IOException, ParseException {
        List<Turma> turmas = findAll();
        boolean found = false;

        for (int i = 0; i < turmas.size(); i++) {
            if (turmas.get(i).getCodigo() == turma.getCodigo()) {
                turmas.set(i, turma);
                found = true;
                break;
            }
        }

        if (!found) {
            turmas.add(turma);
        }

        saveAll(turmas);
    }

    private static void saveAll(List<Turma> turmas) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Turma turma : turmas) {
            lines.add(turmaToLine(turma));
        }
        FileManager.saveToFile(FILE_NAME, lines);
    }

    private static String turmaToLine(Turma turma) {
        return String.format("%d|%d|%s|%s|%d|%s",
                turma.getCodigo(),
                turma.getProfessor().getMatricula(),
                turma.getDisciplina().getCodigo(),
                turma.getSala(),
                turma.getQtdVagas(),
                turma.getHorario());
    }

    private static Turma lineToTurma(String line) throws IOException, ParseException {
        String[] parts = line.split("\\|");

        // Busca o professor e disciplina nos respectivos DAOs
        Professor professor = ProfessorDAO.findByMatricula(Integer.parseInt(parts[1]));
        Disciplina disciplina = DisciplinaDAO.findByCodigo(parts[2]);

        if (professor == null || disciplina == null) {
            throw new IOException("Dados inconsistentes na turma");
        }

        return new Turma(
                Integer.parseInt(parts[0]), // codigo
                Integer.parseInt(parts[4]), // qtdVagas
                parts[3],                  // sala
                parts[5],                  // horario
                professor,
                disciplina
        );
    }
}