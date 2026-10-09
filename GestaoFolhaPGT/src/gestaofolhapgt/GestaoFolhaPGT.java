package gestaofolhapgt;

import java.util.Scanner;
import java.util.Locale;

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
            System.out.println("3. Listar Funcionários");
            System.out.println("4. Exibir Folha de Pagamento Total");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

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
                    System.out.printf(Locale.forLanguageTag("pt-BR"), "Total da Folha: R$ %.2f%n", gerenciador.calcularFolhaTotal());
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
