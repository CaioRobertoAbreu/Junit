package br.com.caelum.leilao.junit.teste;

import br.com.caelum.leilao.junit.builder.CriadorDeLeilao;
import br.com.caelum.leilao.junit.dominio.Leilao;
import br.com.caelum.leilao.junit.dominio.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteLance {

    private Usuario pedro;
    private Usuario joao;
    private Usuario lucas;
    private Usuario felipe;

    @BeforeEach
    public void criaUsuarios() {
        pedro = new Usuario("Pedro");
        joao = new Usuario("Joao");
        lucas = new Usuario("Lucas");
        felipe = new Usuario("Felipe");
    }

    @Test
    public void deveReceberUmLance() {
        CriadorDeLeilao leilao1 = new CriadorDeLeilao();
        leilao1.leiloar("MacBook Pro 15");
        assertEquals(0, leilao1.cria().getLances().size());

        leilao1.lance(pedro, 8000);

        assertEquals(1, leilao1.cria().getLances().size());
        assertEquals(8000, leilao1.cria().getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void deveReceberVariosLances() {
        CriadorDeLeilao leilao2 = new CriadorDeLeilao();
        leilao2.leiloar("MacBook Pro 15")
                .lance(pedro, 8000)
                .lance(joao, 9000)
                .lance(pedro, 10000)
                .lance(joao, 11000);

        Leilao leilao = leilao2.cria();

        assertEquals(4, leilao.getLances().size());

        assertEquals(8000, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(9000, leilao.getLances().get(1).getValor(), 0.00001);
        assertEquals(10000, leilao.getLances().get(2).getValor(), 0.00001);
        assertEquals(11000, leilao.getLances().get(3).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
        CriadorDeLeilao leilao3 = new CriadorDeLeilao();
        Leilao leilao = leilao3.leiloar("MacBook Pro 15")
                .lance(pedro, 8000)
                .lance(pedro, 9000)
                .lance(pedro, 10000)
                .lance(pedro, 11000)
                .cria();

        assertEquals(1, leilao.getLances().size());
        assertEquals(8000, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarMaisQueCincoLancesDoMesmoUsuario() {
        Leilao leilao = new CriadorDeLeilao()
                .leiloar("MacBook Pro 15")
                .lance(lucas, 8000)
                .lance(felipe, 9000)
                .lance(lucas, 10000)
                .lance(felipe, 11000)
                .lance(lucas, 12000)
                .lance(felipe, 13000)
                .lance(lucas, 14000)
                .lance(felipe, 15000)
                .lance(lucas, 16000)
                .lance(felipe, 17000)
                .lance(lucas, 18000)
                .cria();

        assertEquals(10, leilao.getLances().size());

        assertEquals(8000, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(9000, leilao.getLances().get(1).getValor(), 0.00001);
        assertEquals(10000, leilao.getLances().get(2).getValor(), 0.00001);
        assertEquals(11000, leilao.getLances().get(3).getValor(), 0.00001);
        assertEquals(12000, leilao.getLances().get(4).getValor(), 0.00001);
        assertEquals(13000, leilao.getLances().get(5).getValor(), 0.00001);
        assertEquals(14000, leilao.getLances().get(6).getValor(), 0.00001);
        assertEquals(15000, leilao.getLances().get(7).getValor(), 0.00001);
        assertEquals(16000, leilao.getLances().get(8).getValor(), 0.00001);
        assertEquals(17000, leilao.getLances().get(9).getValor(), 0.00001);





    }
}
