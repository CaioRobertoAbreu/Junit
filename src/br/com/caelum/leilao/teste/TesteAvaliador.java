package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.dominio.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TesteAvaliador {

    @Test
    public void testMenorMaiorValor() {
        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");
        Usuario diego = new Usuario("Diego");

        Leilao leilao = new Leilao("MacBook Pro");
        leilao.propoe(new Lance(diego, 3000));
        leilao.propoe(new Lance(joao, 4000));
        leilao.propoe(new Lance(maria, 5000));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        double maiorEsperado = 5000;
        double menorEsperado = 3000;
        double mediaEsperado = 4000;

        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(mediaEsperado, leiloeiro.getMedia(), 0.00001);
    }

    @Test
    public void devePassarComApenasUmLance() {
        Usuario micael = new Usuario("Micael");
        Leilao leilao = new Leilao("Iphone 11 Pro");

        leilao.propoe(new Lance(micael, 5000));

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        assertEquals(5000, avaliador.getMenorLance(), 0.00001 );
        assertEquals(5000, avaliador.getMaiorLance(), 0.00001 );
        assertEquals(5000, avaliador.getMedia(), 0.00001 );

    }

    @Test
    public void devePassarOsTresMaioresLances() {
        Usuario pedro = new Usuario("Pedro");
        Usuario gabriel = new Usuario("Gabriel");
        Usuario guilherme = new Usuario("Guilherme");

        Leilao leilao = new Leilao("Anel de Diamente");
        leilao.propoe(new Lance(pedro, 180000));
        leilao.propoe(new Lance(guilherme, 150000));
        leilao.propoe(new Lance(guilherme, 165000));
        leilao.propoe(new Lance(gabriel, 190000));
        leilao.propoe(new Lance(gabriel, 155000));

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);
        List<Lance> maioresValores = avaliador.getMaioresValores();

        assertEquals(3, maioresValores.size());
        assertEquals(190000, maioresValores.get(0).getValor(), 0.00001);
        assertEquals(180000, maioresValores.get(1).getValor(), 0.00001);
        assertEquals(165000, maioresValores.get(2).getValor(), 0.00001);
    }

    @Test
    public void devePassarComNumerosAleatorios(){
        Usuario pedro = new Usuario("Pedro");
        Usuario gabriel = new Usuario("Gabriel");

        Leilao leilao = new Leilao("X Box One");
        Random valores = new Random();

        double valorAleatorio1 = valores.nextDouble() * 5000;
        double valorAleatorio2 = valores.nextDouble() * 5000;

        leilao.propoe(new Lance(pedro, valorAleatorio1));
        leilao.propoe(new Lance(gabriel, valorAleatorio2));

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        assertEquals(2, avaliador.getMaioresValores().size());

        System.out.println("Valor 1: " + new DecimalFormat("#,###.##").format(valorAleatorio1));
        System.out.println("Valor 2: " + new DecimalFormat("#,###.##").format(valorAleatorio2));
        System.out.println("Menor Valor: " + new DecimalFormat("#,###.##").format(avaliador.getMenorLance()));
        System.out.println("Maior Valor: " + new DecimalFormat("#,###.##").format(avaliador.getMaiorLance()));


    }
}
