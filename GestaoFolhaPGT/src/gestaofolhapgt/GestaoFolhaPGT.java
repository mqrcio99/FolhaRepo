package gestaofolhapgt;

import java.util.Scanner;

public class GestaoFolhaPGT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorRH gerenciador = new GerenciadorRH();
        int opcao;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Cadastrar Funcionário Assalariado");
            System.out.println("2. Cadastrar Funcionário Horista");
            System.out.println("3. Listar Funcionários");
            System.out.println("4. Exibir Folha de Pagamento Total");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Salário Base: ");
                    double salario = scanner.nextDouble();
                    gerenciador.cadastrarFuncionario(new FuncionarioAssalariado(nome, cpf, salario));
                    break;
                case 2:
                    System.out.print("Nome: ");
                    nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    cpf = scanner.nextLine();
                    System.out.print("Valor por Hora: ");
                    double valorHora = scanner.nextDouble();
                    System.out.print("Horas Trabalhadas: ");
                    int horas = scanner.nextInt();
                    FuncionarioHorista horista = new FuncionarioHorista(nome, cpf, valorHora);
                    horista.setHorasTrabalhadas(horas);
                    gerenciador.cadastrarFuncionario(horista);
                    break;
                case 3:
                    gerenciador.listarFuncionarios();
                    break;
                case 4:
                    System.out.printf("Total da Folha: R$ %.2f%n", gerenciador.calcularFolhaTotal());
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
