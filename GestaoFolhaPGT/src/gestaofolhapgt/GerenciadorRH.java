package gestaofolhapgt;

import java.util.ArrayList;

public class GerenciadorRH {
    private ArrayList<Funcionario> funcionarios;
    
    public GerenciadorRH() {
        this.funcionarios = new ArrayList<>();
    }
    
    // Cadastrar funcionário
    public void cadastrarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        System.out.println("✓ Funcionário cadastrado com sucesso!");
    }
    
    // Listar todos os funcionários
    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        
        System.out.println("\n===== LISTA DE FUNCIONÁRIOS =====");
        for (int i = 0; i < funcionarios.size(); i++) {
            System.out.println((i + 1) + ". " + funcionarios.get(i));
        }
        System.out.println("=================================\n");
    }
    
    // Buscar por CPF (opcional, mas útil)
    public Funcionario buscarPorCPF(String cpf) {
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }
        return null;
    }
    
    // Calcular folha total (opcional)
    public double calcularFolhaTotal() {
        double total = 0;
        for (Funcionario f : funcionarios) {
            if (f instanceof FuncionarioAssalariado) {
                total += ((FuncionarioAssalariado) f).calcularSalario();
            } else if (f instanceof FuncionarioHorista) {
                total += ((FuncionarioHorista) f).calcularSalario();
            }
        }
        return total;
    }
}