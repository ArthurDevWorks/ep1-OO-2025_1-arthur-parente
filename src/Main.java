import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int option;

        do{
            Scanner sc = new Scanner(System.in);

            System.out.println("Escolha uma opcao");
            System.out.println("1.Modo aluno");
            System.out.println("2.Modo disciplina/turma");
            System.out.println("3.Modo avaliacao/frequencia");
            System.out.println("0.Encerrar programa");

            option = sc.nextInt();

            switch (option){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
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
