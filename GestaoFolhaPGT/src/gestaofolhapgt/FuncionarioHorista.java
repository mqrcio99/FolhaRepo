package gestaofolhapgt;

import java.util.Locale;

/** Funcionário remunerado pelas horas trabalhadas. */
public class FuncionarioHorista extends Funcionario {

    private int horasTrabalhadas;
    private double valorHora;

    /**
     * Cria um funcionário horista.
     *
     * @param nome nome do funcionário
     * @param cpf CPF válido
     * @param valorHora valor por hora não negativo
     */
    public FuncionarioHorista(String nome, String cpf, double valorHora) {
        super(nome, cpf, 0);
        this.valorHora = validarValorNaoNegativo("Valor por hora", valorHora);
    }

    /** @return quantidade de horas trabalhadas */
    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    /** Atualiza as horas trabalhadas, rejeitando valores negativos. */
    public void setHorasTrabalhadas(int horasTrabalhadas) {
        if (horasTrabalhadas < 0) {
            throw new IllegalArgumentException("Horas trabalhadas não podem ser negativas.");
        }
        this.horasTrabalhadas = horasTrabalhadas;
    }

    /** @return valor pago por hora */
    public double getValorHora() {
        return valorHora;
    }

    /** Atualiza o valor por hora, rejeitando valores negativos ou não finitos. */
    public void setValorHora(double valorHora) {
        this.valorHora = validarValorNaoNegativo("Valor por hora", valorHora);
    }

    /** {@inheritDoc} */
    @Override
    public double calcularPagamento() {
        return horasTrabalhadas * valorHora;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        Locale locale = Locale.forLanguageTag("pt-BR");
        return "Horista: " + getNome() +
               " | CPF: " + getCpf() +
               " | Horas: " + horasTrabalhadas +
               " | Valor/hora: R$ " + String.format(locale, "%.2f", valorHora) +
               " | Total: R$ " + String.format(locale, "%.2f", calcularPagamento());
    }
}