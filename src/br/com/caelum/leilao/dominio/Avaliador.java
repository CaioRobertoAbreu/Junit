package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Avaliador {

    private double maiorLance = Double.NEGATIVE_INFINITY;
    private double menorLance = Double.POSITIVE_INFINITY;
    private double media = 0;
    private List<Lance> maiores;

    public void avalia (Leilao leilao) {
        for(Lance lance: leilao.getLances()){
            if(lance.getValor() > maiorLance) {
                maiorLance = lance.getValor();
            }

            if(lance.getValor() < menorLance) {
                menorLance = lance.getValor();
            }

            media += lance.getValor();
        }
        media = (media / leilao.getLances().size());

        maiores = new ArrayList<>(leilao.getLances());
        maiores.sort(Comparator.comparingDouble(Lance::getValor).reversed());

        if (maiores.size() < 3) {
            maiores = maiores.subList(0, maiores.size());
        } else {
            maiores = maiores.subList(0, 3);
        }
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }

    public double getMedia(){
        return this.media;
    }

    public List<Lance> getMaioresValores() {
        return maiores;
    }

}
