package controller;

import java.util.List;
import java.util.Random;

import model.Baralho;
import model.Flashcard;
import util.FlashcardConstants;
import util.LeituraArquivo;

public class BaralhoController {
	private Baralho baralho;
	
	public BaralhoController() {
		baralho = new Baralho();
		carregarBaralho();
	}
	
	public void carregarBaralho() {
		LeituraArquivo arquivo = new LeituraArquivo();
		FlashcardController fc = new FlashcardController();
		
		List<String> conteudo = arquivo.lerConteudoArquivo(FlashcardConstants.CAMINHO_FLASHCARDS);
		this.getBaralho().setCartas(fc.separarFlashcards(conteudo));
	}
	
	public Flashcard sortear() {
		Flashcard sorteado;
		Random r = new Random();
		int numSorteado = r.nextInt() % this.retornaBaralho().getCartas().size();
		
		while(numSorteado > this.retornaBaralho().getCartas().size() || numSorteado < 0)
			numSorteado = r.nextInt() % this.retornaBaralho().getCartas().size();
		
		sorteado = this.retornaBaralho().getCartas().get(numSorteado);
		return sorteado;
	}
	
	public Baralho retornaBaralho(){
		return this.getBaralho();
	}
	
	public int tamanhoBaralho(){
		return this.getBaralho().getTamanhoBaralho();
	}

	public Baralho getBaralho() {
		return baralho;
	}

	public void setBaralho(Baralho baralho) {
		this.baralho = baralho;
	}
}
