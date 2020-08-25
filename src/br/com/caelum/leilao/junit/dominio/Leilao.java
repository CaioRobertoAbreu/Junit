package br.com.caelum.leilao.junit.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<>();
	}
	
	public void propoe(Lance lance) {
		if(podeDarLance(lance.getUsuario())) {
			lances.add(lance);
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	private Usuario getUltimoUsuario(){
		return lances.get(lances.size() -1).getUsuario();
	}

	private int quantidadeLances(Usuario usuario) {
		int total = 0;
		for(Lance l : lances){
			if(l.getUsuario().equals(usuario)) {
				total++;
			}
		}

		return total;
	}

	private boolean podeDarLance(Usuario usuario) {
		return lances.isEmpty() || (!getUltimoUsuario().equals(usuario) && quantidadeLances(usuario) < 5);
	}

	
	
}
