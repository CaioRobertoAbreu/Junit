package br.com.caelum.leilao.junit.teste;

import br.com.caelum.leilao.junit.builder.CriadorDeLeilao;
import br.com.caelum.leilao.junit.dominio.Avaliador;
import br.com.caelum.leilao.junit.dominio.Lance;
import br.com.caelum.leilao.junit.dominio.Leilao;
import br.com.caelum.leilao.junit.dominio.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class TesteAvaliador {

    private final Usuario pedro = new Usuario("Pedro");
    private final Usuario gabriel = new Usuario("Gabriel");
    private final Usuario guilherme = new Usuario("Guilherme");
    private final Avaliador leiloeiro = new Avaliador();

    @Test
    public void naoDeveAvaliarLeilaoSemLance() {
        Leilao leilao = new CriadorDeLeilao().leiloar("MacBook Pro 15").cria();

        Assertions.assertThrows(RuntimeException.class, () -> leiloeiro.avalia(leilao));
    }

    @Test
    public void testMenorMaiorValor() {

        CriadorDeLeilao leilao = new CriadorDeLeilao();
        leilao.leiloar("MacBook Pro 15")
            .lance(pedro, 3000)
            .lance(gabriel, 4000)
            .lance(guilherme, 5000);

        leiloeiro.avalia(leilao.cria());

        double maiorEsperado = 5000;
        double menorEsperado = 3000;
        double mediaEsperado = 4000;

        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(mediaEsperado, leiloeiro.getMedia(), 0.00001);

    }


    @Test()
    public void devePassarComApenasUmLance() {
        CriadorDeLeilao leilao2 = new CriadorDeLeilao();

        leilao2.leiloar("Iphone 11 Pro")
                .lance(pedro, 5000);


        leiloeiro.avalia(leilao2.cria());

        assertEquals(5000, leiloeiro.getMenorLance(), 0.00001 );
        assertEquals(5000, leiloeiro.getMaiorLance(), 0.00001 );
        assertEquals(5000, leiloeiro.getMedia(), 0.00001 );

    }


    @Test
    public void devePassarOsTresMaioresLances() {
        CriadorDeLeilao leilao3 = new CriadorDeLeilao();
        leilao3.leiloar("Anel de Diamante")
                .lance(pedro, 180000)
                .lance(guilherme, 150000)
                .lance(gabriel, 190000)
                .lance(guilherme, 165000)
                .lance(gabriel, 155000);

        leiloeiro.avalia(leilao3.cria());
        List<Lance> maioresValores = leiloeiro.getMaioresValores();

        assertEquals(3, maioresValores.size());
        assertEquals(190000, maioresValores.get(0).getValor(), 0.00001);
        assertEquals(180000, maioresValores.get(1).getValor(), 0.00001);
        assertEquals(165000, maioresValores.get(2).getValor(), 0.00001);
    }


    @Test
    public void devePassarComNumerosAleatorios(){
        CriadorDeLeilao leilao4 = new CriadorDeLeilao();

        Random valores = new Random();

        double valorAleatorio1 = valores.nextDouble() * 5000;
        double valorAleatorio2 = valores.nextDouble() * 5000;

        leilao4.leiloar("X Box One")
            .lance(pedro, valorAleatorio1)
            .lance(gabriel, valorAleatorio2);

        leiloeiro.avalia(leilao4.cria());

        assertEquals(2, leiloeiro.getMaioresValores().size());

        System.out.println("Valor 1: " + new DecimalFormat("#,###.##").format(valorAleatorio1));
        System.out.println("Valor 2: " + new DecimalFormat("#,###.##").format(valorAleatorio2));
        System.out.println("Menor Valor: " + new DecimalFormat("#,###.##").format(leiloeiro.getMenorLance()));
        System.out.println("Maior Valor: " + new DecimalFormat("#,###.##").format(leiloeiro.getMaiorLance()));
    }

    @Test
    public void usandoHamcrest() {
        Leilao leilao = new CriadorDeLeilao().leiloar("Iphone 11 Gold")
                .lance(pedro, 6000)
                .lance(gabriel, 4000)
                .lance(guilherme, 10000)
                .cria();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMenorLance(), equalTo(4000.));
        assertThat(leiloeiro.getMaioresValores(), hasItems(
                new Lance(guilherme, 10000.),
                new Lance(pedro, 6000.),
                new Lance(guilherme, 10000.)
        ));
    }


}
