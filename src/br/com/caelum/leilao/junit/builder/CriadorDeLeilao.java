package br.com.caelum.leilao.junit.builder;

import br.com.caelum.leilao.junit.dominio.Lance;
import br.com.caelum.leilao.junit.dominio.Leilao;
import br.com.caelum.leilao.junit.dominio.Usuario;

public class CriadorDeLeilao {

    private Leilao leilao;


    public CriadorDeLeilao leiloar(String produto) {
        leilao = new Leilao(produto);
        return this;
    }

    public CriadorDeLeilao lance(Usuario usuario, double valor){
        leilao.propoe(new Lance(usuario, valor));
        return this;
    }

    public Leilao cria() {
        return leilao;
    }
}
