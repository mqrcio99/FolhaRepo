package gestaofolhapgt;

import java.util.Locale;

/** Representa os dados e o comportamento comum de um funcionário. */
public abstract class Funcionario {

    private String nome;
    private String cpf;
    protected double salarioBase;

    /**
     * Cria um funcionário com dados validados.
     *
     * @param nome nome não vazio
     * @param cpf CPF válido, com ou sem pontuação
     * @param salarioBase salário-base não negativo
     */
    public Funcionario(String nome, String cpf, double salarioBase) {
        this.nome = validarNome(nome);
        this.cpf = normalizarCpf(cpf);
        this.salarioBase = validarValorNaoNegativo("Salário-base", salarioBase);
    }

    /** @return nome do funcionário */
    public String getNome() {
        return nome;
    }

    /** @return CPF normalizado, contendo somente dígitos */
    public String getCpf() {
        return cpf;
    }

    /** @return salário-base */
    public double getSalarioBase() {
        return salarioBase;
    }

    /** Atualiza o nome do funcionário. */
    public void setNome(String nome) {
        this.nome = validarNome(nome);
    }

    /** Atualiza o CPF após normalização e validação dos dígitos verificadores. */
    public void setCpf(String cpf) {
        this.cpf = normalizarCpf(cpf);
    }

    /** Atualiza o salário-base, rejeitando valores negativos ou não finitos. */
    public void setSalarioBase(double salarioBase) {
        this.salarioBase = validarValorNaoNegativo("Salário-base", salarioBase);
    }

    /**
     * Calcula o pagamento conforme a regra do tipo concreto de funcionário.
     *
     * @return valor do pagamento
     */
    public abstract double calcularPagamento();

    /** Normaliza e valida um CPF brasileiro. */
    static String normalizarCpf(String cpf) {
        if (cpf == null || !cpf.matches("[0-9.\\-\\s]+")) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        String cpfNormalizado = cpf.replaceAll("\\D", "");
        if (!cpfValido(cpfNormalizado)) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        return cpfNormalizado;
    }

    /** Valida números usados em valores monetários. */
    protected static double validarValorNaoNegativo(String campo, double valor) {
        if (!Double.isFinite(valor) || valor < 0) {
            throw new IllegalArgumentException(campo + " deve ser um valor não negativo.");
        }
        return valor;
    }

    private static String validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ficar vazio.");
        }
        return nome.trim();
    }

    private static boolean cpfValido(String cpf) {
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0;
        for (int indice = 0; indice < 9; indice++) {
            soma += (cpf.charAt(indice) - '0') * (10 - indice);
        }
        int primeiroDigito = calcularDigitoVerificador(soma);
        if (cpf.charAt(9) - '0' != primeiroDigito) {
            return false;
        }

        soma = 0;
        for (int indice = 0; indice < 10; indice++) {
            soma += (cpf.charAt(indice) - '0') * (11 - indice);
        }
        int segundoDigito = calcularDigitoVerificador(soma);
        return cpf.charAt(10) - '0' == segundoDigito;
    }

    private static int calcularDigitoVerificador(int soma) {
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format(Locale.forLanguageTag("pt-BR"), "%s | CPF: %s | Pagamento: R$ %.2f",
                nome, cpf, calcularPagamento());
    }
}