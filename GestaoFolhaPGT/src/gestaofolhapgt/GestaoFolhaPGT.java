package gestaofolhapgt;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/** Ponto de entrada e interface de terminal do sistema de folha de pagamento. */
public class GestaoFolhaPGT {

    private static final Locale LOCALE_PT_BR = Locale.forLanguageTag("pt-BR");

    /** Inicia o menu interativo do sistema. */
    public static void main(String[] args) {
        GerenciadorRH gerenciador = new GerenciadorRH();
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao;
            do {
                exibirMenu();
                opcao = lerInteiro(scanner, "Escolha: ", 0, 4);
                switch (opcao) {
                    case 1:
                        cadastrarAssalariado(scanner, gerenciador);
                        break;
                    case 2:
                        cadastrarHorista(scanner, gerenciador);
                        break;
                    case 3:
                        listarFuncionarios(gerenciador);
                        break;
                    case 4:
                        System.out.printf(LOCALE_PT_BR, "Total da Folha: R$ %.2f%n",
                                gerenciador.calcularFolhaTotal());
                        break;
                    case 0:
                        System.out.println("Encerrando...");
                        break;
                    default:
                        throw new IllegalStateException("Opção validada fora do intervalo esperado.");
                }
            } while (opcao != 0);
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=============================");
        System.out.println("  SISTEMA DE FOLHA DE PAGAMENTO");
        System.out.println("=============================");
        System.out.println("1. Cadastrar funcionário assalariado");
        System.out.println("2. Cadastrar funcionário horista");
        System.out.println("3. Listar funcionários");
        System.out.println("4. Exibir custo total da folha");
        System.out.println("0. Sair");
    }

    private static void cadastrarAssalariado(Scanner scanner, GerenciadorRH gerenciador) {
        System.out.println("\n-- Cadastro: Funcionário Assalariado --");
        String nome = lerTextoObrigatorio(scanner, "Nome: ");
        String cpf = lerCpf(scanner, gerenciador);
        double salarioBase = lerValorNaoNegativo(scanner, "Salário base: R$ ");
        double bonus = lerValorNaoNegativo(scanner, "Bônus: R$ ");
        gerenciador.cadastrarFuncionario(new FuncionarioAssalariado(nome, cpf, salarioBase, bonus));
        System.out.println("Funcionário cadastrado com sucesso.");
    }

    private static void cadastrarHorista(Scanner scanner, GerenciadorRH gerenciador) {
        System.out.println("\n-- Cadastro: Funcionário Horista --");
        String nome = lerTextoObrigatorio(scanner, "Nome: ");
        String cpf = lerCpf(scanner, gerenciador);
        double valorHora = lerValorNaoNegativo(scanner, "Valor por hora: R$ ");
        int horas = lerInteiro(scanner, "Horas trabalhadas: ", 0, Integer.MAX_VALUE);
        FuncionarioHorista horista = new FuncionarioHorista(nome, cpf, valorHora);
        horista.setHorasTrabalhadas(horas);
        gerenciador.cadastrarFuncionario(horista);
        System.out.println("Funcionário cadastrado com sucesso.");
    }

    private static void listarFuncionarios(GerenciadorRH gerenciador) {
        List<Funcionario> funcionarios = gerenciador.listarFuncionarios();
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        System.out.println("\n====== FOLHA DE PAGAMENTO ======");
        for (int indice = 0; indice < funcionarios.size(); indice++) {
            System.out.println((indice + 1) + ". " + funcionarios.get(indice));
        }
        System.out.println("================================");
        System.out.printf(LOCALE_PT_BR, "CUSTO TOTAL DA FOLHA: R$ %.2f%n",
                gerenciador.calcularFolhaTotal());
        System.out.println("================================");
    }

    private static String lerTextoObrigatorio(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("O valor não pode ficar vazio.");
        }
    }

    private static String lerCpf(Scanner scanner, GerenciadorRH gerenciador) {
        while (true) {
            String cpf = lerTextoObrigatorio(scanner, "CPF: ");
            try {
                String cpfNormalizado = Funcionario.normalizarCpf(cpf);
                if (gerenciador.buscarPorCPF(cpfNormalizado) != null) {
                    System.out.println("Já existe funcionário cadastrado com esse CPF.");
                    continue;
                }
                return cpfNormalizado;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private static double lerValorNaoNegativo(Scanner scanner, String mensagem) {
        NumberFormat formato = NumberFormat.getNumberInstance(LOCALE_PT_BR);
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            ParsePosition posicao = new ParsePosition(0);
            Number valor = formato.parse(entrada, posicao);
            if (valor != null && posicao.getIndex() == entrada.length()) {
                double numero = valor.doubleValue();
                if (Double.isFinite(numero) && numero >= 0) {
                    return numero;
                }
            }
            System.out.println("Valor inválido. Informe um número não negativo; use vírgula nos decimais.");
        }
    }

    private static int lerInteiro(Scanner scanner, String mensagem, int minimo, int maximo) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
            } catch (NumberFormatException exception) {
            }
            System.out.println("Opção inválida. Informe um número entre " + minimo + " e " + maximo + ".");
        }
    }
}
