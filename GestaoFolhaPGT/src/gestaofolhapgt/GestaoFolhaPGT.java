package gestaofolhapgt;

import java.util.Scanner;

public class GestaoFolhaPGT {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GerenciadorRH gerenciador = new GerenciadorRH();
        int opcao;

        do {
            System.out.println("\n=============================");
            System.out.println("  SISTEMA DE FOLHA DE PAGAMENTO");
            System.out.println("=============================");
            System.out.println("1. Cadastrar funcionário assalariado");
            System.out.println("2. Cadastrar funcionário horista");
            System.out.println("3. Listar folha de pagamento");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa o buffer

            switch (opcao) {

                case 1:
                    System.out.println("\n-- Cadastro: Funcionário Assalariado --");
                    System.out.print("Nome: ");
                    String nomeA = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpfA = scanner.nextLine();
                    System.out.print("Salário base: R$ ");
                    double salarioBase = scanner.nextDouble();
                    System.out.print("Bônus: R$ ");
                    double bonus = scanner.nextDouble();
                    scanner.nextLine();

                    FuncionarioAssalariado assalariado = new FuncionarioAssalariado(nomeA, cpfA, salarioBase, bonus);
                    gerenciador.cadastrarFuncionario(assalariado);
                    break;

                case 2:
                    System.out.println("\n-- Cadastro: Funcionário Horista --");
                    System.out.print("Nome: ");
                    String nomeH = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpfH = scanner.nextLine();
                    System.out.print("Valor por hora: R$ ");
                    double valorHora = scanner.nextDouble();
                    System.out.print("Horas trabalhadas: ");
                    int horas = scanner.nextInt();
                    scanner.nextLine();

                    FuncionarioHorista horista = new FuncionarioHorista(nomeH, cpfH, valorHora);
                    horista.setHorasTrabalhadas(horas);
                    gerenciador.cadastrarFuncionario(horista);
                    break;

                case 3:
                    gerenciador.listarFuncionarios();
                    break;

                case 4:
                    System.out.println("\nEncerrando o sistema. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 4);

        scanner.close();
    }
}