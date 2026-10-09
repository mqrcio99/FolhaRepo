package gestaofolhapgt;

import java.util.Locale;

/** Funcionário remunerado por salário-base e bônus. */
public class FuncionarioAssalariado extends Funcionario {

    private double salarioBase;
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
        super(nome, cpf);
        this.salarioBase = validarValorNaoNegativo("Salário-base", salarioBase);
        this.bonus = validarValorNaoNegativo("Bônus", bonus);
    }

    /** @return salário-base do funcionário */
    public double getSalarioBase() {
        return salarioBase;
    }

    /** Atualiza o salário-base, rejeitando valores negativos ou não finitos. */
    public void setSalarioBase(double salarioBase) {
        this.salarioBase = validarValorNaoNegativo("Salário-base", salarioBase);
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
        return salarioBase + bonus;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        Locale locale = Locale.forLanguageTag("pt-BR");
        return "Assalariado: " + getNome() +
               " | CPF: " + getCpf() +
               " | Salário Base: R$ " + String.format(locale, "%.2f", salarioBase) +
               " | Bônus: R$ " + String.format(locale, "%.2f", bonus) +
               " | Total: R$ " + String.format(locale, "%.2f", calcularPagamento());
    }
}