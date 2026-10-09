package gestaofolhapgt;

import java.util.Locale;

/** Funcionário remunerado por salário-base e bônus. */
public class FuncionarioAssalariado extends Funcionario {

    private double bonus;

    /**
     * Cria um funcionário assalariado.
     *
     * @param nome nome do funcionário
     * @param cpf CPF válido
     * @param salarioBase salário-base não negativo
     * @param bonus bônus não negativo
     */
    public FuncionarioAssalariado(String nome, String cpf, double salarioBase, double bonus) {
        super(nome, cpf, salarioBase);
        this.bonus = validarValorNaoNegativo("Bônus", bonus);
    }

    /** @return bônus do funcionário */
    public double getBonus() {
        return bonus;
    }

    /** Atualiza o bônus, rejeitando valores negativos ou não finitos. */
    public void setBonus(double bonus) {
        this.bonus = validarValorNaoNegativo("Bônus", bonus);
    }

    /** {@inheritDoc} */
    @Override
    public double calcularPagamento() {
        return getSalarioBase() + bonus;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        Locale locale = Locale.forLanguageTag("pt-BR");
        return "Assalariado: " + getNome() +
               " | CPF: " + getCpf() +
               " | Salário Base: R$ " + String.format(locale, "%.2f", getSalarioBase()) +
               " | Bônus: R$ " + String.format(locale, "%.2f", bonus) +
               " | Total: R$ " + String.format(locale, "%.2f", calcularPagamento());
    }
}