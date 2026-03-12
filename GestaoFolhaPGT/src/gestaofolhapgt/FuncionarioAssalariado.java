package gestaofolhapgt;

public class FuncionarioAssalariado extends Funcionario {

    private double bonus;

    public FuncionarioAssalariado(String nome, String cpf, double salarioBase, double bonus) {
        super(nome, cpf, salarioBase);
        this.bonus = bonus;
    }

    // Getter e Setter do bonus
    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    // Polimorfismo: salário fixo + bônus
    @Override
    public double calcularPagamento() {
        return getSalarioBase() + bonus;
    }

    @Override
<<<<<<< HEAD
    public double calcularPagamento() {
        return getSalarioBase();
}
=======
    public String toString() {
        return "Assalariado: " + getNome() +
               " | CPF: " + getCpf() +
               " | Salário Base: R$ " + String.format("%.2f", getSalarioBase()) +
               " | Bônus: R$ " + String.format("%.2f", bonus) +
               " | Total: R$ " + String.format("%.2f", calcularPagamento());
    }
>>>>>>> feature-heranca
}