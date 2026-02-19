package gestaofolhapgt;

public class FuncionarioAssalariado extends Funcionario {
    
    public FuncionarioAssalariado(String nome, String cpf, double salarioBase) {
        super(nome, cpf, salarioBase);
    }
    
    // Salário fixo mensal
    public double calcularSalario() {
        return getSalarioBase();
    }
    
    @Override
    public String toString() {
        return "Assalariado: " + getNome() + 
               " | CPF: " + getCpf() + 
               " | Salário: R$ " + String.format("%.2f", calcularSalario());
    }

    @Override
    public double calcularPagamento() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}