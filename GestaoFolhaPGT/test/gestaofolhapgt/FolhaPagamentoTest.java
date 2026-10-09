package gestaofolhapgt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class FolhaPagamentoTest {

    @Test
    void assalariadoCalculaSalarioBaseMaisBonus() {
        FuncionarioAssalariado funcionario = new FuncionarioAssalariado("Ana", "00000000191", 3000.0, 500.0);

        assertEquals(3500.0, funcionario.calcularPagamento(), 0.0001);
    }

    @Test
    void assalariadoSemBonusRecebeSalarioBase() {
        FuncionarioAssalariado funcionario = new FuncionarioAssalariado("Ana", "00000000191", 3000.0, 0.0);

        assertEquals(3000.0, funcionario.calcularPagamento(), 0.0001);
    }

    @Test
    void toStringAssalariadoApresentaValoresFormatados() {
        FuncionarioAssalariado funcionario = new FuncionarioAssalariado("Ana", "00000000191", 3000.0, 500.0);

        assertEquals("Assalariado: Ana | CPF: 00000000191 | Salário Base: R$ 3000,00"
                + " | Bônus: R$ 500,00 | Total: R$ 3500,00", funcionario.toString());
    }

    @Test
    void horistaCalculaHorasPeloValorHora() {
        FuncionarioHorista funcionario = new FuncionarioHorista("Bia", "00000000272", 20.0);
        funcionario.setHorasTrabalhadas(100);

        assertEquals(2000.0, funcionario.calcularPagamento(), 0.0001);
    }

    @Test
    void horistaSemHorasRecebeZero() {
        FuncionarioHorista funcionario = new FuncionarioHorista("Bia", "00000000272", 20.0);

        assertEquals(0.0, funcionario.calcularPagamento(), 0.0001);
    }

    @Test
    void gerenciadorVazioRetornaFolhaZero() {
        GerenciadorRH gerenciador = new GerenciadorRH();

        assertEquals(0.0, gerenciador.calcularFolhaTotal(), 0.0001);
        assertTrue(gerenciador.listarFuncionarios().isEmpty());
    }

    @Test
    void gerenciadorSomaFolhaMista() {
        GerenciadorRH gerenciador = new GerenciadorRH();
        gerenciador.cadastrarFuncionario(new FuncionarioAssalariado("Ana", "00000000191", 3000.0, 500.0));
        FuncionarioHorista horista = new FuncionarioHorista("Bia", "00000000272", 20.0);
        horista.setHorasTrabalhadas(100);
        gerenciador.cadastrarFuncionario(horista);

        assertEquals(5500.0, gerenciador.calcularFolhaTotal(), 0.0001);
    }

    @Test
    void buscaPorCpfNormalizaPontuacao() {
        GerenciadorRH gerenciador = new GerenciadorRH();
        Funcionario funcionario = new FuncionarioAssalariado("Ana", "00000000191", 3000.0, 0.0);
        gerenciador.cadastrarFuncionario(funcionario);

        assertEquals(funcionario, gerenciador.buscarPorCPF("000.000.001-91"));
    }

    @Test
    void buscaCpfAusenteRetornaNull() {
        GerenciadorRH gerenciador = new GerenciadorRH();

        assertNull(gerenciador.buscarPorCPF("00000000272"));
    }

    @Test
    void gerenciadorRejeitaCpfDuplicadoNormalizado() {
        GerenciadorRH gerenciador = new GerenciadorRH();
        gerenciador.cadastrarFuncionario(new FuncionarioAssalariado("Ana", "00000000191", 3000.0, 0.0));

        assertThrows(IllegalArgumentException.class,
                () -> gerenciador.cadastrarFuncionario(
                        new FuncionarioHorista("Bia", "000.000.001-91", 20.0)));
    }

    @Test
    void valoresNegativosEIdentificadoresInvalidosSaoRejeitados() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioAssalariado("Ana", "00000000191", -1.0, 0.0));
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioAssalariado("Ana", "00000000191", 3000.0, -1.0));
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioHorista("Bia", "00000000272", -1.0));

        FuncionarioHorista horista = new FuncionarioHorista("Bia", "00000000272", 20.0);
        assertThrows(IllegalArgumentException.class, () -> horista.setHorasTrabalhadas(-1));
        assertThrows(IllegalArgumentException.class, () -> horista.setNome("  "));
        assertThrows(IllegalArgumentException.class,
            () -> new FuncionarioHorista("Bia", "123", 20.0));
    }

    @Test
    void listaMistaCalculaPagamentoPolimorfico() {
        FuncionarioAssalariado assalariado = new FuncionarioAssalariado("Ana", "00000000191", 3000.0, 500.0);
        FuncionarioHorista horista = new FuncionarioHorista("Bia", "00000000272", 20.0);
        horista.setHorasTrabalhadas(100);
        List<Funcionario> funcionarios = Arrays.asList(assalariado, horista);

        double total = 0.0;
        for (Funcionario funcionario : funcionarios) {
            total += funcionario.calcularPagamento();
        }

        assertEquals(5500.0, total, 0.0001);
    }

    @Test
    void funcionariosComMesmoCpfSaoIguaisEntreTipos() {
        FuncionarioAssalariado assalariado = new FuncionarioAssalariado("Ana", "00000000191", 3000.0, 0.0);
        FuncionarioHorista horista = new FuncionarioHorista("Ana", "000.000.001-91", 20.0);

        assertEquals(assalariado, horista);
        assertEquals(assalariado.hashCode(), horista.hashCode());
        assertNotEquals(assalariado, new FuncionarioHorista("Bia", "00000000272", 20.0));
    }
}