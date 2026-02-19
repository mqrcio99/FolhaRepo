package gestaofolhapgt;

public class FuncionarioHorista extends Funcionario {
    private int horasTrabalhadas;
    private double valorHora;
    
    public FuncionarioHorista(String nome, String cpf, double valorHora) {
        super(nome, cpf, 0); // salarioBase será calculado
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
    
    // Calcula: horas * valor da hora
    public double calcularSalario() {
        return horasTrabalhadas * valorHora;
    }
    
    @Override
    public String toString() {
        return "Horista: " + getNome() + 
               " | CPF: " + getCpf() + 
               " | Horas: " + horasTrabalhadas +
               " | Valor/hora: R$ " + String.format("%.2f", valorHora) +
               " | Salário: R$ " + String.format("%.2f", calcularSalario());
    }

    @Override
    public double calcularPagamento() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}