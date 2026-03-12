package gestaofolhapgt;

public class FuncionarioHorista extends Funcionario {

    private int horasTrabalhadas;
    private double valorHora;

    public FuncionarioHorista(String nome, String cpf, double valorHora) {
        super(nome, cpf, 0); // salarioBase não é usado no horista
        this.valorHora = valorHora;
        this.horasTrabalhadas = 0;
    }

    // Getters e Setters
    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    // Polimorfismo: horas trabalhadas * valor da hora
    @Override
    public double calcularPagamento() {
        return horasTrabalhadas * valorHora;
    }

    @Override
<<<<<<< HEAD
    public double calcularPagamento() {
        return horasTrabalhadas * valorHora;
}
=======
    public String toString() {
        return "Horista: " + getNome() +
               " | CPF: " + getCpf() +
               " | Horas: " + horasTrabalhadas +
               " | Valor/hora: R$ " + String.format("%.2f", valorHora) +
               " | Total: R$ " + String.format("%.2f", calcularPagamento());
    }
>>>>>>> feature-heranca
}