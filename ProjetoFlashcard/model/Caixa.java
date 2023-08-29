package model;

import java.util.LinkedList;
import java.util.List;

public class Caixa {
	private List<String> codigoCartas;
	
	public Caixa() {
		setCodigoCartas(new LinkedList<String>());
	}

	public List<String> getCodigoCartas() {
		return codigoCartas;
	}

	public void setCodigoCartas(List<String> cartas) {
		this.codigoCartas = cartas;
	}
	
	public int getTamanhoCaixa() {
		return this.getCodigoCartas().size();
	}
}
