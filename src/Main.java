import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Date;
import java.util.List;
import persistence.*;
import entities.*;
import enums.*;

public class Main {

    //Cria uma unica instacia do scanner para ser utilizada
    private static final Scanner sc = new Scanner(System.in);

    //Formatação de data
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static void main(String[] args) {

        try {
            int option;
            do {
                System.out.println("\n======== MENU PRINCIPAL ========");
                System.out.println("1. Modo Aluno");
                System.out.println("2. Modo Disciplina/Turma");
                System.out.println("3. Modo Avaliacao/Frequencia");
                System.out.println("0. Encerrar programa");
                System.out.print("Escolha uma opção: ");

                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        modoAluno();
                        break;
                    case 2:
                        modoDisciplinaTurma();
                        break;
                    case 3:
                        modoAvaliacaoFrequencia();
                        break;
                    case 0:
                        System.out.println("Programa encerrado");
                        break;
                    default:
                        System.out.println("Opcao Invalida!");
                }
            } while (option != 0);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    // =================== MODO ALUNO =========================
    private static void modoAluno() throws IOException, ParseException {
        int optionAluno;
        do {
            System.out.println("\n======== MODO ALUNO ========");
            System.out.println("1. Cadastrar aluno");
            System.out.println("2. Editar aluno");
            System.out.println("3. Listar alunos");
            System.out.println("4. Matricular aluno");
            System.out.println("5. Visualizar alunos e disciplinas");
            System.out.println("0. Voltar ao menu anterior");

            optionAluno = sc.nextInt();
            sc.nextLine();

            switch (optionAluno) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    editarAluno();
                    break;
                case 3:
                    listarAlunos();
                    break;
                case 4:
                    matricularAluno();
                    break;
                case 5:
                    visualizarAlunosEDisciplinas();
                    break;
                case 0:
                    System.out.println("Voltando ao menu anterior...");
                    break;
                default:
                    System.out.println("Opcao Invalida!");
            }

        } while (optionAluno != 0);
    }

    // =================== MODO DISCIPLINA/TURMA =========================
    private static void modoDisciplinaTurma() throws IOException, ParseException {
        int optionDisc;
        do {
            System.out.println("\n======== MODO DISCIPLINA/TURMA ========");
            System.out.println("1. Cadastrar disciplina");
            System.out.println("2. Cadastrar turma");
            System.out.println("3. Listar turmas");
            System.out.println("4. Visualizar disciplinas e turmas");
            System.out.println("0. Voltar ao menu anterior");

            optionDisc = sc.nextInt();
            sc.nextLine();

            switch (optionDisc) {
                case 1:
                    cadastrarDisciplina();
                    break;
                case 2:
                    cadastrarTurma();
                    break;
                case 3:
                    listarTurmas();
                    break;
                case 4:
                    visualizarDisciplinasETurmas();
                    break;
                case 0:
                    System.out.println("Voltando ao menu anterior...");
                    break;
                default:
                    System.out.println("Opcao Invalida!");
            }

        } while (optionDisc != 0);
    }

    // =================== MODO AVALIACAO/FREQUENCIA =========================
    private static void modoAvaliacaoFrequencia() throws IOException, ParseException {
        int optionAva;
        do {
            System.out.println("\n======== MODO AVALIACAO/FREQUENCIA ========");
            System.out.println("1. Lancar notas e presencas");
            System.out.println("2. Calcular media final e frequencia");
            System.out.println("3. Verificar aprovacao");
            System.out.println("4. Visualizar relatorios");
            System.out.println("5. Visualizar boletins");
            System.out.println("0. Voltar ao menu anterior");

            optionAva = sc.nextInt();
            sc.nextLine();

            switch (optionAva) {
                case 1:
                    lancarNotasPresencas();
                    break;
                case 2:
                    calcularMediaEFrequencia();
                    break;
                case 3:
                    verificarAprovacao();
                    break;
                case 4:
                    visualizarRelatorios();
                    break;
                case 5:
                    visualizarBoletins();
                    break;
                case 0:
                    System.out.println("Voltando ao menu anterior...");
                    break;
                default:
                    System.out.println("Opcao Invalida!");
            }


        } while (optionAva != 0);
    }

    // =================== FUNÇÕES PARA ARMAZENAR DADOS =========================

    private static void cadastrarAluno() throws IOException, ParseException {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Data nascimento (dd/MM/yyyy): ");
        Date nascimento = dateFormat.parse(sc.nextLine());

        System.out.print("Sexo (MASCULINO/FEMININO/OUTRO): ");
        Sexo sexo = Sexo.valueOf(sc.nextLine().toUpperCase());

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Matrícula: ");
        int matricula = sc.nextInt();
        sc.nextLine();

        System.out.print("Curso: ");
        String curso = sc.nextLine();

        System.out.print("É especial? (true/false): ");
        boolean especial = sc.nextBoolean();
        sc.nextLine();

        Aluno aluno = new Aluno(nome, nascimento, sexo, email, matricula, curso, especial);
        AlunoDAO.save(aluno);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    private static void editarAluno() throws IOException, ParseException {
        System.out.print("Matrícula do aluno: ");
        int matricula = sc.nextInt();
        sc.nextLine();

        Aluno aluno = AlunoDAO.findByMatricula(matricula);
        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        System.out.print("Novo nome (" + aluno.getNome() + "): ");
        String nome = sc.nextLine();
        if (!nome.isEmpty()) aluno.setNome(nome);

        System.out.print("Novo email (" + aluno.getEmail() + "): ");
        String email = sc.nextLine();
        if (!email.isEmpty()) aluno.setEmail(email);

        System.out.print("Novo curso (" + aluno.getCurso() + "): ");
        String curso = sc.nextLine();
        if (!curso.isEmpty()) aluno.setCurso(curso);

        AlunoDAO.update(aluno);
        System.out.println("Aluno atualizado com sucesso!");
    }


    private static void listarAlunos() throws IOException, ParseException {
        List<Aluno> alunos = AlunoDAO.findAll();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("\n=== LISTA DE ALUNOS ===");
        for (Aluno aluno : alunos) {
            System.out.println("Matrícula: " + aluno.getMatricula());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Email: " + aluno.getEmail());
            System.out.println("Curso: " + aluno.getCurso());
            System.out.println("Tipo: " + (aluno.isEspecial() ? "Especial" : "Regular"));
            System.out.println("---------------------");
        }
    }

    private static void matricularAluno() throws IOException, ParseException {
        System.out.print("Matrícula do aluno: ");
        int matriculaAluno = sc.nextInt();
        sc.nextLine();

        // Busca o aluno no arquivo
        Aluno aluno = AlunoDAO.findByMatricula(matriculaAluno);
        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        // Verifica se é aluno especial e já tem 2 matrículas
        if (aluno.isEspecial() && MatriculaDAO.countByAluno(matriculaAluno) >= 2) {
            System.out.println("Alunos especiais não podem se matricular em mais de 2 disciplinas!");
            return;
        }

        // Lista turmas disponíveis
        System.out.println("\nTurmas disponíveis:");
        List<Turma> turmas = TurmaDAO.findAll();
        turmas.forEach(t -> {
            try {
                int vagasOcupadas = MatriculaDAO.countByTurma(t.getCodigo());
                System.out.printf("%d - %s (%s) - Vagas: %d/%d%n",
                        t.getCodigo(),
                        t.getDisciplina().getNome(),
                        t.getHorario(),
                        vagasOcupadas,
                        t.getQtdVagas());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.out.print("Código da turma: ");
        int codigoTurma = sc.nextInt();
        sc.nextLine();

        // Busca a turma selecionada
        Turma turma = TurmaDAO.findByCodigo(codigoTurma);
        if (turma == null) {
            System.out.println("Turma não encontrada!");
            return;
        }

        // Verifica se há vagas disponíveis
        int vagasOcupadas = MatriculaDAO.countByTurma(codigoTurma);
        if (vagasOcupadas >= turma.getQtdVagas()) {
            System.out.println("Não há vagas disponíveis nesta turma!");
            return;
        }

        // Verifica se aluno já está matriculado nesta turma
        if (MatriculaDAO.exists(matriculaAluno, codigoTurma)) {
            System.out.println("Aluno já está matriculado nesta turma!");
            return;
        }

        // Cria a matrícula com notas e presença zeradas
        Matriculado matricula = new Matriculado(aluno, turma, 0, 0);
        MatriculaDAO.save(matricula);
        System.out.println("Matrícula realizada com sucesso!");
    }

    private static void visualizarAlunosEDisciplinas() throws IOException, ParseException {
        System.out.println("\n=== ALUNOS E SUAS DISCIPLINAS ===");

        // Lista todos os alunos
        List<Aluno> alunos = AlunoDAO.findAll();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        // Para cada aluno, busca suas matrículas
        for (Aluno aluno : alunos) {
            System.out.printf("\nAluno: %s (Matrícula: %d)%n", aluno.getNome(), aluno.getMatricula());

            List<Matriculado> matriculas = MatriculaDAO.findByAluno(aluno.getMatricula());
            if (matriculas.isEmpty()) {
                System.out.println("  Nenhuma disciplina matriculada.");
                continue;
            }

            // Lista as disciplinas do aluno
            for (Matriculado matricula : matriculas) {
                Disciplina disciplina = matricula.getTurma().getDisciplina();
                System.out.printf("  - %s (Turma %d - %s)%n",
                        disciplina.getNome(),
                        matricula.getTurma().getCodigo(),
                        matricula.getTurma().getHorario());
            }
        }
    }

    private static void cadastrarDisciplina() throws IOException {
        System.out.print("Nome da disciplina: ");
        String nome = sc.nextLine();

        System.out.print("Código: ");
        String codigo = sc.nextLine();

        // Verifica se código já existe
        if (DisciplinaDAO.findByCodigo(codigo) != null) {
            System.out.println("Já existe uma disciplina com este código!");
            return;
        }

        System.out.print("Semestre (1-10): ");
        int semestre = sc.nextInt();

        System.out.print("Carga horária (horas): ");
        int horas = sc.nextInt();
        sc.nextLine();

        System.out.print("Modalidade (PRESENCIAL/REMOTA): ");
        Modalidade modalidade = Modalidade.valueOf(sc.nextLine().toUpperCase());

        System.out.print("Forma de avaliação (1 ou 2): ");
        int formaAvaliacao = sc.nextInt();
        sc.nextLine();

        // Cria e salva a disciplina
        Disciplina disciplina = new Disciplina(nome, codigo, semestre, horas, modalidade, formaAvaliacao);
        DisciplinaDAO.save(disciplina);
        System.out.println("Disciplina cadastrada com sucesso!");
    }

    private static void cadastrarTurma() throws IOException, ParseException {
        System.out.print("Código da turma: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        // Verifica se turma já existe
        if (TurmaDAO.findByCodigo(codigo) != null) {
            System.out.println("Já existe uma turma com este código!");
            return;
        }

        System.out.print("Quantidade de vagas: ");
        int vagas = sc.nextInt();
        sc.nextLine();

        System.out.print("Sala (ou 'ONLINE' para turmas remotas): ");
        String sala = sc.nextLine();

        System.out.print("Horário (ex: Segunda 14:00-16:00): ");
        String horario = sc.nextLine();

        // Lista professores disponíveis
        System.out.println("\nProfessores disponíveis:");
        List<Professor> professores = ProfessorDAO.findAll();
        professores.forEach(p -> System.out.printf("%d - %s%n", p.getMatricula(), p.getNome()));

        System.out.print("Matrícula do professor: ");
        int matriculaProf = sc.nextInt();
        sc.nextLine();

        // Busca o professor
        Professor professor = ProfessorDAO.findByMatricula(matriculaProf);
        if (professor == null) {
            System.out.println("Professor não encontrado!");
            return;
        }

        // Lista disciplinas disponíveis
        System.out.println("\nDisciplinas disponíveis:");
        List<Disciplina> disciplinas = DisciplinaDAO.findAll();
        disciplinas.forEach(d -> System.out.printf("%s - %s%n", d.getCodigo(), d.getNome()));

        System.out.print("Código da disciplina: ");
        String codigoDisc = sc.nextLine();

        // Busca a disciplina
        Disciplina disciplina = DisciplinaDAO.findByCodigo(codigoDisc);
        if (disciplina == null) {
            System.out.println("Disciplina não encontrada!");
            return;
        }

        // Cria e salva a turma
        Turma turma = new Turma(codigo, vagas, sala, horario, professor, disciplina);
        TurmaDAO.save(turma);
        System.out.println("Turma cadastrada com sucesso!");
    }

    private static void listarTurmas() throws IOException, ParseException {
        List<Turma> turmas = TurmaDAO.findAll();
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }

        System.out.println("\n=== LISTA DE TURMAS ===");
        for (Turma turma : turmas) {
            System.out.printf("Código: %d%n", turma.getCodigo());
            System.out.printf("Disciplina: %s (%s)%n",
                    turma.getDisciplina().getNome(),
                    turma.getDisciplina().getCodigo());
            System.out.printf("Professor: %s%n", turma.getProfessor().getNome());
            System.out.printf("Horário: %s - Sala: %s%n", turma.getHorario(), turma.getSala());

            int vagasOcupadas = MatriculaDAO.countByTurma(turma.getCodigo());
            System.out.printf("Vagas: %d/%d%n", vagasOcupadas, turma.getQtdVagas());
            System.out.println("---------------------");
        }
    }


    private static void visualizarDisciplinasETurmas() throws IOException, ParseException {
        System.out.println("\n=== DISCIPLINAS E SUAS TURMAS ===");

        List<Disciplina> disciplinas = DisciplinaDAO.findAll();
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }

        for (Disciplina disciplina : disciplinas) {
            System.out.printf("\nDisciplina: %s (%s)%n", disciplina.getNome(), disciplina.getCodigo());

            List<Turma> turmas = TurmaDAO.findByDisciplina(disciplina.getCodigo());
            if (turmas.isEmpty()) {
                System.out.println("  Nenhuma turma cadastrada.");
                continue;
            }

            for (Turma turma : turmas) {
                System.out.printf("  - Turma %d: %s - %s (Prof. %s)%n",
                        turma.getCodigo(),
                        turma.getHorario(),
                        turma.getSala(),
                        turma.getProfessor().getNome());
            }
        }
    }

    private static void lancarNotasPresencas() throws IOException, ParseException {
        System.out.print("Matrícula do aluno: ");
        int matricula = sc.nextInt();
        sc.nextLine();

        System.out.print("Código da turma: ");
        int codigoTurma = sc.nextInt();
        sc.nextLine();

        // Busca a matrícula
        Matriculado matriculaAluno = MatriculaDAO.find(matricula, codigoTurma);
        if (matriculaAluno == null) {
            System.out.println("Matrícula não encontrada!");
            return;
        }

        // Verifica se é aluno especial (não recebe notas)
        if (matriculaAluno.getAluno().isEspecial()) {
            System.out.print("Presença (%): ");
            double presenca = sc.nextDouble();
            sc.nextLine();

            matriculaAluno.setPresenca(presenca);
            MatriculaDAO.update(matriculaAluno);
            System.out.println("Presença atualizada com sucesso!");
            return;
        }

        // Para alunos regulares, solicita todas as notas
        System.out.print("Nota P1: ");
        double p1 = sc.nextDouble();

        System.out.print("Nota P2: ");
        double p2 = sc.nextDouble();

        System.out.print("Nota P3: ");
        double p3 = sc.nextDouble();

        System.out.print("Nota Listas: ");
        double listas = sc.nextDouble();

        System.out.print("Nota Seminário: ");
        double seminario = sc.nextDouble();

        System.out.print("Presença (%): ");
        double presenca = sc.nextDouble();
        sc.nextLine();

        // Calcula a média conforme a forma de avaliação da disciplina
        double notaFinal;
        if (matriculaAluno.getTurma().getDisciplina().getFormaAvaliacao() == 1) {
            notaFinal = (p1 + p2 + p3 + listas + seminario) / 5;
        } else {
            notaFinal = (p1 + p2 * 2 + p3 * 3 + listas + seminario) / 8;
        }

        // Atualiza a matrícula
        matriculaAluno.setNota(notaFinal);
        matriculaAluno.setPresenca(presenca);
        MatriculaDAO.update(matriculaAluno);

        System.out.printf("Notas lançadas! Média final: %.2f%n", notaFinal);
    }

    private static void calcularMediaEFrequencia() throws IOException, ParseException {
        System.out.print("Matrícula do aluno: ");
        int matricula = sc.nextInt();
        sc.nextLine();

        System.out.print("Código da turma: ");
        int codigoTurma = sc.nextInt();
        sc.nextLine();

        // Busca a matrícula
        Matriculado matriculaAluno = MatriculaDAO.find(matricula, codigoTurma);
        if (matriculaAluno == null) {
            System.out.println("Matrícula não encontrada!");
            return;
        }

        // Exibe os resultados
        System.out.printf("\n=== RESULTADOS - %s ===%n",
                matriculaAluno.getTurma().getDisciplina().getNome());
        System.out.printf("Média final: %.2f%n", matriculaAluno.getNota());
        System.out.printf("Frequência: %.1f%%%n", matriculaAluno.getPresenca());
    }

    private static void verificarAprovacao() throws IOException, ParseException {
        System.out.print("Matrícula do aluno: ");
        int matricula = sc.nextInt();
        sc.nextLine();

        System.out.print("Código da turma: ");
        int codigoTurma = sc.nextInt();
        sc.nextLine();

        // Busca a matrícula
        Matriculado matriculaAluno = MatriculaDAO.find(matricula, codigoTurma);
        if (matriculaAluno == null) {
            System.out.println("Matrícula não encontrada!");
            return;
        }

        // Verifica aprovação
        boolean aprovado = matriculaAluno.getNota() >= 5 && matriculaAluno.getPresenca() >= 75;

        System.out.printf("\n=== SITUAÇÃO - %s ===%n",
                matriculaAluno.getTurma().getDisciplina().getNome());
        System.out.printf("Média: %.2f (%s)%n",
                matriculaAluno.getNota(),
                matriculaAluno.getNota() >= 5 ? "OK" : "Insuficiente");
        System.out.printf("Frequência: %.1f%% (%s)%n",
                matriculaAluno.getPresenca(),
                matriculaAluno.getPresenca() >= 75 ? "OK" : "Insuficiente");
        System.out.printf("Situação final: %s%n",
                aprovado ? "APROVADO" : "REPROVADO");
    }

    private static void visualizarRelatorios() throws IOException, ParseException {
        System.out.println("\n=== RELATÓRIOS ===");
        System.out.println("1. Por turma");
        System.out.println("2. Por disciplina");
        System.out.println("3. Por professor");
        System.out.print("Escolha o tipo de relatório: ");

        int tipo = sc.nextInt();
        sc.nextLine();

        switch (tipo) {
            case 1:
                relatorioPorTurma();
                break;
            case 2:
                relatorioPorDisciplina();
                break;
            case 3:
                relatorioPorProfessor();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void relatorioPorTurma() throws IOException, ParseException {
        System.out.print("Código da turma: ");
        int codigoTurma = sc.nextInt();
        sc.nextLine();

        Turma turma = TurmaDAO.findByCodigo(codigoTurma);
        if (turma == null) {
            System.out.println("Turma não encontrada!");
            return;
        }

        List<Matriculado> matriculas = MatriculaDAO.findByTurma(codigoTurma);

        System.out.printf("\n=== RELATÓRIO - TURMA %d ===%n", codigoTurma);
        System.out.printf("Disciplina: %s%n", turma.getDisciplina().getNome());
        System.out.printf("Professor: %s%n", turma.getProfessor().getNome());
        System.out.printf("Horário: %s - Sala: %s%n", turma.getHorario(), turma.getSala());
        System.out.printf("Total de alunos: %d%n", matriculas.size());

        long aprovados = matriculas.stream()
                .filter(m -> m.getNota() >= 5 && m.getPresenca() >= 75)
                .count();

        System.out.printf("Aprovados: %d (%.1f%%)%n", aprovados,
                (double) aprovados / matriculas.size() * 100);

        System.out.println("\n=== ALUNOS ===");
        for (Matriculado matricula : matriculas) {
            System.out.printf("%s - Média: %.2f - Frequência: %.1f%% - %s%n",
                    matricula.getAluno().getNome(),
                    matricula.getNota(),
                    matricula.getPresenca(),
                    (matricula.getNota() >= 5 && matricula.getPresenca() >= 75) ?
                            "APROVADO" : "REPROVADO");
        }
    }

    private static void visualizarBoletins() throws IOException, ParseException {
        System.out.print("Matrícula do aluno: ");
        int matricula = sc.nextInt();
        sc.nextLine();

        Aluno aluno = AlunoDAO.findByMatricula(matricula);
        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        List<Matriculado> matriculas = MatriculaDAO.findByAluno(matricula);

        System.out.printf("\n=== BOLETIM - %s ===%n", aluno.getNome());
        for (Matriculado matriculaAluno : matriculas) {
            Disciplina disciplina = matriculaAluno.getTurma().getDisciplina();
            System.out.printf("\nDisciplina: %s%n", disciplina.getNome());
            System.out.printf("Turma: %d - %s%n",
                    matriculaAluno.getTurma().getCodigo(),
                    matriculaAluno.getTurma().getHorario());

            if (aluno.isEspecial()) {
                System.out.printf("Frequência: %.1f%%%n", matriculaAluno.getPresenca());
                System.out.println("Aluno especial - sem notas");
            } else {
                System.out.printf("Média final: %.2f%n", matriculaAluno.getNota());
                System.out.printf("Frequência: %.1f%%%n", matriculaAluno.getPresenca());
                System.out.printf("Situação: %s%n",
                        (matriculaAluno.getNota() >= 5 && matriculaAluno.getPresenca() >= 75) ?
                                "APROVADO" : "REPROVADO");
            }
        }
    }
    private static void relatorioPorDisciplina() throws IOException, ParseException {
        System.out.println("\n=== RELATÓRIO POR DISCIPLINA ===");

        // Lista todas as disciplinas cadastradas
        List<Disciplina> disciplinas = DisciplinaDAO.findAll();
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }

        // Mostra menu de disciplinas disponíveis
        System.out.println("Disciplinas disponíveis:");
        for (int i = 0; i < disciplinas.size(); i++) {
            System.out.printf("%d - %s (%s)%n",
                    i + 1,
                    disciplinas.get(i).getNome(),
                    disciplinas.get(i).getCodigo());
        }

        System.out.print("Escolha a disciplina (número): ");
        int escolha = sc.nextInt() - 1;
        sc.nextLine();

        if (escolha < 0 || escolha >= disciplinas.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        Disciplina disciplina = disciplinas.get(escolha);

        // Busca todas as turmas desta disciplina
        List<Turma> turmas = TurmaDAO.findByDisciplina(disciplina.getCodigo());

        System.out.printf("\n=== RELATÓRIO - %s ===%n", disciplina.getNome());
        System.out.printf("Código: %s%n", disciplina.getCodigo());
        System.out.printf("Semestre: %d%n", disciplina.getSemestre());
        System.out.printf("Carga horária: %d horas%n", disciplina.getQtdHoras());
        System.out.printf("Modalidade: %s%n", disciplina.getModalidade());
        System.out.printf("Total de turmas: %d%n", turmas.size());

        int totalAlunos = 0;
        int totalAprovados = 0;

        System.out.println("\n=== TURMAS ===");
        for (Turma turma : turmas) {
            List<Matriculado> matriculas = MatriculaDAO.findByTurma(turma.getCodigo());
            int aprovados = (int) matriculas.stream()
                    .filter(m -> m.getNota() >= 5 && m.getPresenca() >= 75)
                    .count();

            System.out.printf("\nTurma %d - %s%n", turma.getCodigo(), turma.getHorario());
            System.out.printf("Professor: %s%n", turma.getProfessor().getNome());
            System.out.printf("Sala: %s%n", turma.getSala());
            System.out.printf("Alunos matriculados: %d%n", matriculas.size());
            System.out.printf("Aprovados: %d (%.1f%%)%n",
                    aprovados,
                    matriculas.isEmpty() ? 0 : (aprovados * 100.0 / matriculas.size()));

            totalAlunos += matriculas.size();
            totalAprovados += aprovados;
        }

        System.out.printf("\n=== RESUMO GERAL ===%n");
        System.out.printf("Total de alunos na disciplina: %d%n", totalAlunos);
        System.out.printf("Taxa de aprovação geral: %.1f%%%n",
                totalAlunos == 0 ? 0 : (totalAprovados * 100.0 / totalAlunos));
    }

    private static void relatorioPorProfessor() throws IOException, ParseException {
        System.out.println("\n=== RELATÓRIO POR PROFESSOR ===");

        // Lista todos os professores cadastrados
        List<Professor> professores = ProfessorDAO.findAll();
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
            return;
        }

        // Mostra menu de professores disponíveis
        System.out.println("Professores disponíveis:");
        for (int i = 0; i < professores.size(); i++) {
            System.out.printf("%d - %s (Matrícula: %d)%n",
                    i + 1,
                    professores.get(i).getNome(),
                    professores.get(i).getMatricula());
        }

        System.out.print("Escolha o professor (número): ");
        int escolha = sc.nextInt() - 1;
        sc.nextLine();

        if (escolha < 0 || escolha >= professores.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        Professor professor = professores.get(escolha);

        // Busca todas as turmas deste professor
        List<Turma> turmas = TurmaDAO.findAll().stream()
                .filter(t -> t.getProfessor().getMatricula() == professor.getMatricula())
                .toList();

        System.out.printf("\n=== RELATÓRIO - %s ===%n", professor.getNome());
        System.out.printf("Matrícula: %d%n", professor.getMatricula());
        System.out.printf("Email: %s%n", professor.getEmail());
        System.out.printf("Total de turmas: %d%n", turmas.size());

        int totalAlunos = 0;
        int totalAprovados = 0;

        System.out.println("\n=== DISCIPLINAS MINISTRADAS ===");
        for (Turma turma : turmas) {
            Disciplina disciplina = turma.getDisciplina();
            List<Matriculado> matriculas = MatriculaDAO.findByTurma(turma.getCodigo());
            int aprovados = (int) matriculas.stream()
                    .filter(m -> m.getNota() >= 5 && m.getPresenca() >= 75)
                    .count();

            System.out.printf("\n%s (%s)%n", disciplina.getNome(), disciplina.getCodigo());
            System.out.printf("Turma %d - %s%n", turma.getCodigo(), turma.getHorario());
            System.out.printf("Alunos matriculados: %d%n", matriculas.size());
            System.out.printf("Aprovados: %d (%.1f%%)%n",
                    aprovados,
                    matriculas.isEmpty() ? 0 : (aprovados * 100.0 / matriculas.size()));

            totalAlunos += matriculas.size();
            totalAprovados += aprovados;
        }

        System.out.printf("\n=== RESUMO GERAL ===%n");
        System.out.printf("Total de alunos: %d%n", totalAlunos);
        System.out.printf("Taxa de aprovação geral: %.1f%%%n",
                totalAlunos == 0 ? 0 : (totalAprovados * 100.0 / totalAlunos));

        // Mostra estatísticas adicionais
        if (!turmas.isEmpty()) {
            double mediaTurmas = (double) totalAlunos / turmas.size();
            System.out.printf("Média de alunos por turma: %.1f%n", mediaTurmas);
        }
    }
}