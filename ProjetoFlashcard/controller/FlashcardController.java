package controller;

import java.util.List;
import java.util.Random;

import model.Flashcard;
import model.FlashcardModel;

public class FlashcardController {

	public List<Flashcard> separarFlashcardsController() {
		FlashcardModel fm =  new FlashcardModel();
		List<Flashcard> flashcards = fm.separarFlashcards();
		
		return flashcards;
	}
	
	public Flashcard sortear() {
		Flashcard sorteado;
		Random r = new Random();
		FlashcardModel fm =  new FlashcardModel();
		List<Flashcard> flashcards = fm.separarFlashcards();
		int numSorteado = r.nextInt() % flashcards.size();
		
		while(numSorteado > flashcards.size() || numSorteado < 0)
			numSorteado = r.nextInt() % flashcards.size();
		
		sorteado = flashcards.get(numSorteado);
		return sorteado;
	}
	
	public String pegarUltimoCodigoController() {
		FlashcardModel fm = new FlashcardModel();
		return fm.pegarUltimoCodigo();
	}
	
	public void adicionarFlashcardController(String pergunta, String imagemPergunta, String descricao, String imagemDescricao) {
		FlashcardModel fm = new FlashcardModel();
		fm.adicionarFlashcard(pergunta, imagemPergunta, descricao, imagemDescricao);
		
		//inserirNaCaixaUm
		CaixaController cc = new CaixaController(1);
		cc.adicionarCodigoCartaController();
	}
	
	public void excluirCartaController(Flashcard atual) {
		FlashcardModel fm =  new FlashcardModel();
		fm.excluirCarta(atual);
		
		//começar procura a partir da caixa 1
		CaixaController cc = new CaixaController(1);
		cc.excluirCartaController(atual.getCodigo());
	}

	public Flashcard buscarCartaCodigoController(String codigo)  {
		FlashcardModel fm = new FlashcardModel();
		Flashcard carta = fm.buscarCartaCodigo(codigo);
		return carta;
	}

}
