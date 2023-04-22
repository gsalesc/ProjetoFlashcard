package model;

import java.util.LinkedList;
import java.util.List;

public class Baralho {
	private List<Flashcard> cartas;

	public Baralho() {
		setCartas(new LinkedList<Flashcard>());
	}

	public List<Flashcard> getCartas() {
		return cartas;
	}

	public void setCartas(List<Flashcard> list) {
		this.cartas = list;
	}

	public int getTamanhoBaralho() {
		return cartas.size();
	}
}
