package persistence;

import entities.Disciplina;
import enums.Modalidade;
import persistence.FileManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {
    private static final String FILE_NAME = "disciplinas.txt";

    public static void save(Disciplina disciplina) throws IOException {
        List<String> data = new ArrayList<>();
        data.add(disciplinaToLine(disciplina));
        FileManager.saveToFile(FILE_NAME, data,true);
    }

    public static List<Disciplina> findAll() throws IOException {
        List<String> lines = FileManager.readFromFile(FILE_NAME);
        List<Disciplina> disciplinas = new ArrayList<>();

        for (String line : lines) {
            disciplinas.add(lineToDisciplina(line));
        }

        return disciplinas;
    }

    public static Disciplina findByCodigo(String codigo) throws IOException {
        List<Disciplina> disciplinas = findAll();
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getCodigo().equals(codigo)) {
                return disciplina;
            }
        }
        return null;
    }

    public static void update(Disciplina disciplina) throws IOException {
        List<Disciplina> disciplinas = findAll();
        boolean found = false;

        for (int i = 0; i < disciplinas.size(); i++) {
            if (disciplinas.get(i).getCodigo().equals(disciplina.getCodigo())) {
                disciplinas.set(i, disciplina);
                found = true;
                break;
            }
        }

        if (!found) {
            disciplinas.add(disciplina);
        }

        saveAll(disciplinas);
    }

    private static void saveAll(List<Disciplina> disciplinas) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Disciplina disciplina : disciplinas) {
            lines.add(disciplinaToLine(disciplina));
        }
        FileManager.saveToFile(FILE_NAME, lines,false);
    }

    private static String disciplinaToLine(Disciplina disciplina) {
        return String.format("%s|%s|%d|%d|%s|%d",
                disciplina.getCodigo(),
                disciplina.getNome(),
                disciplina.getSemestre(),
                disciplina.getQtdHoras(),
                disciplina.getModalidade(),
                disciplina.getFormaAvaliacao());
    }

    private static Disciplina lineToDisciplina(String line) {
        String[] parts = line.split("\\|");

        return new Disciplina(
                parts[1],                           // nome
                parts[0],                           // codigo
                Integer.parseInt(parts[2]),         // semestre
                Integer.parseInt(parts[3]),         // qtdHoras
                Modalidade.valueOf(parts[4]),      // modalidade
                Integer.parseInt(parts[5])          // formaAvaliacao
        );
    }
}
