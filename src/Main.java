import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int option;

        do{
            Scanner sc = new Scanner(System.in);

            System.out.println("======== MENU PRINCIPAL ========");
            System.out.println("Escolha uma opcao");
            System.out.println("1.Modo aluno");
            System.out.println("2.Modo disciplina/turma");
            System.out.println("3.Modo avaliacao/frequencia");
            System.out.println("0.Encerrar programa");

            option = sc.nextInt();

            switch (option){
                case 1:
                    int optionAluno;
                    do{
                        System.out.println("======== MODO ALUNO ========");
                        System.out.println("Escolha uma opcao");
                        System.out.println("1.Cadastrar aluno");
                        System.out.println("2.Editar aluno");
                        System.out.println("3.Listar alunos");
                        System.out.println("4.Matricular aluno");
                        System.out.println("5.Salvar e visualizar alunos e disciplinas");
                        System.out.println("0.Volta ao menu anterior");
                        optionAluno = sc.nextInt();
                    }while(optionAluno != 0);
                    break;
                case 2:
                    int optionDisc;
                    do{
                        System.out.println("======== MODO DISCIPLINA/TURMA ========");
                        System.out.println("Escolha uma opcao");
                        System.out.println("1.Cadastrar disciplina");
                        System.out.println("2.Cadastrar turma");
                        System.out.println("3.Listar turmas disponiveis");
                        System.out.println("4.Salvar e listar disciplinas e turmas");
                        System.out.println("0.Volta ao menu anterior");
                        optionDisc = sc.nextInt();
                    }while(optionDisc != 0);
                    break;
                case 3:
                    int optionAva;
                    do{
                        System.out.println("======== MODO AVALIACAO/FREQUENCIA ========");
                        System.out.println("Escolha uma opcao");
                        System.out.println("1.Lancar notas e presencas");
                        System.out.println("2.Calcular media final e frequencia");
                        System.out.println("3.Verificar aprovacao");
                        System.out.println("4.Visualizar relatorios");
                        System.out.println("5.Visalizar boletins");
                        System.out.println("0.Volta ao menu anterior");
                        optionAva = sc.nextInt();
                    }while(optionAva != 0);
                    break;
                default:
                    if(option != 0) {
                        System.out.println("Opcao Invalida !");
                        break;
                    }else{
                        System.out.println("Programa encerrado");
                    }
            }

        }while(option != 0);
    }
}
