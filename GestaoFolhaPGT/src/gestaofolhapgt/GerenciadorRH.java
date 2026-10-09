package gestaofolhapgt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Gerencia o cadastro e os cálculos dos funcionários. */
public class GerenciadorRH {

    private final List<Funcionario> funcionarios;

    /** Cria um gerenciador sem funcionários cadastrados. */
    public GerenciadorRH() {
        funcionarios = new ArrayList<>();
    }

    /**
     * Cadastra um funcionário se seu CPF ainda não estiver registrado.
     *
     * @param funcionario funcionário a cadastrar
     * @throws IllegalArgumentException se o funcionário for nulo ou tiver CPF duplicado
     */
    public void cadastrarFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não pode ser nulo.");
        }
        if (buscarPorCPF(funcionario.getCpf()) != null) {
            throw new IllegalArgumentException("Já existe funcionário cadastrado com esse CPF.");
        }
        funcionarios.add(funcionario);
    }

    /**
     * Retorna uma cópia não modificável dos funcionários cadastrados.
     *
     * @return lista imutável com os funcionários
     */
    public List<Funcionario> listarFuncionarios() {
        return Collections.unmodifiableList(new ArrayList<>(funcionarios));
    }

    /**
     * Busca um funcionário por CPF, aceitando CPF com ou sem pontuação.
     *
     * @param cpf CPF válido
     * @return funcionário encontrado ou {@code null} quando não houver correspondência
     * @throws IllegalArgumentException se o CPF for inválido
     */
    public Funcionario buscarPorCPF(String cpf) {
        String cpfNormalizado = Funcionario.normalizarCpf(cpf);
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpfNormalizado)) {
                return funcionario;
            }
        }
        return null;
    }

    /**
     * Soma os pagamentos dos funcionários cadastrados.
     *
     * @return custo total da folha
     */
    public double calcularFolhaTotal() {
        double total = 0;
        for (Funcionario funcionario : funcionarios) {
            total += funcionario.calcularPagamento();
        }
        return total;
    }
}