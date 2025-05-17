package persistence;

import persistence.MatriculaDAO;
import persistence.FileManager;
import entities.Matriculado;
import persistence.AlunoDAO;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import entities.Aluno;
import entities.Turma;

public class MatriculaDAO {
    private static final String FILE_NAME = "matriculas.txt";

    public static void save(Matriculado matriculado) throws IOException {
        List<String> data = new ArrayList<>();
        data.add(matriculadoToLine(matriculado));
        FileManager.saveToFile(FILE_NAME, data);
    }

    public static List<Matriculado> findAll() throws IOException {
        List<String> lines = FileManager.readFromFile(FILE_NAME);
        List<Matriculado> matriculados = new ArrayList<>();

        for (String line : lines) {
            try {
                Matriculado m = lineToMatriculado(line);
                if (m != null) {
                    matriculados.add(m);
                }
            } catch (Exception e) {
                System.err.println("Erro ao processar linha de matrícula: " + line);
            }
        }

        return matriculados;
    }

    public static Matriculado find(int matriculaAluno, int codigoTurma) throws IOException {
        List<Matriculado> matriculados = findAll();
        for (Matriculado m : matriculados) {
            if (m.getAluno().getMatricula() == matriculaAluno &&
                    m.getTurma().getCodigo() == codigoTurma) {
                return m;
            }
        }
        return null;
    }

    public static List<Matriculado> findByAluno(int matriculaAluno) throws IOException {
        List<Matriculado> all = findAll();
        List<Matriculado> result = new ArrayList<>();

        for (Matriculado m : all) {
            if (m.getAluno().getMatricula() == matriculaAluno) {
                result.add(m);
            }
        }
        return result;
    }

    public static List<Matriculado> findByTurma(int codigoTurma) throws IOException {
        List<Matriculado> all = findAll();
        List<Matriculado> result = new ArrayList<>();

        for (Matriculado m : all) {
            if (m.getTurma().getCodigo() == codigoTurma) {
                result.add(m);
            }
        }
        return result;
    }

    public static int countByAluno(int matriculaAluno) throws IOException {
        return findByAluno(matriculaAluno).size();
    }

    public static int countByTurma(int codigoTurma) throws IOException {
        return findByTurma(codigoTurma).size();
    }

    public static boolean exists(int matriculaAluno, int codigoTurma) throws IOException {
        return find(matriculaAluno, codigoTurma) != null;
    }

    public static void update(Matriculado matriculado) throws IOException {
        List<Matriculado> matriculados = findAll();
        boolean found = false;

        for (int i = 0; i < matriculados.size(); i++) {
            Matriculado m = matriculados.get(i);
            if (m.getAluno().getMatricula() == matriculado.getAluno().getMatricula() &&
                    m.getTurma().getCodigo() == matriculado.getTurma().getCodigo()) {
                matriculados.set(i, matriculado);
                found = true;
                break;
            }
        }

        if (!found) {
            matriculados.add(matriculado);
        }

        saveAll(matriculados);
    }

    private static void saveAll(List<Matriculado> matriculados) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Matriculado m : matriculados) {
            lines.add(matriculadoToLine(m));
        }
        FileManager.saveToFile(FILE_NAME, lines);
    }

    private static String matriculadoToLine(Matriculado matriculado) {
        return String.format("%d|%d|%.2f|%.2f",
                matriculado.getAluno().getMatricula(),
                matriculado.getTurma().getCodigo(),
                matriculado.getNota(),
                matriculado.getPresenca());
    }

    private static Matriculado lineToMatriculado(String line) throws IOException, ParseException {
        String[] parts = line.split("\\|");

        // Busca o aluno e a turma nos respectivos DAOs
        Aluno aluno = AlunoDAO.findByMatricula(Integer.parseInt(parts[0]));
        Turma turma = TurmaDAO.findByCodigo(Integer.parseInt(parts[1]));

        if (aluno == null || turma == null) {
            throw new IOException("Dados inconsistentes na matrícula");
        }

        return new Matriculado(
                aluno,
                turma,
                Double.parseDouble(parts[2]), // nota
                Double.parseDouble(parts[3])  // presenca
        );
    }
}