package gestaofolhapgt;

import java.util.ArrayList;

public class GerenciadorRH {

    private ArrayList<Funcionario> funcionarios;

    public GerenciadorRH() {
        funcionarios = new ArrayList<>();
    }

    // Cadastrar funcionário (qualquer tipo)
    public void cadastrarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        System.out.println("✔ Funcionário cadastrado com sucesso!");
    }

    // Listar todos os funcionários
    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        System.out.println("\n====== FOLHA DE PAGAMENTO ======");
        for (int i = 0; i < funcionarios.size(); i++) {
            System.out.println((i + 1) + ". " + funcionarios.get(i));
        }
        System.out.println("================================");
        System.out.printf("CUSTO TOTAL DA FOLHA: R$ %.2f%n", calcularFolhaTotal());
        System.out.println("================================\n");
    }

    // Buscar por CPF
    public Funcionario buscarPorCPF(String cpf) {
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }
        return null;
    }

    // Calcular folha total usando polimorfismo
    public double calcularFolhaTotal() {
        double total = 0;
        for (Funcionario f : funcionarios) {
            total += f.calcularPagamento(); // polimorfismo: Java chama o método certo de cada tipo
        }
        return total;
    }
}