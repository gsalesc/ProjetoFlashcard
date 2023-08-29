package model;

import java.util.ArrayList;
import java.util.List;

import controller.FlashcardController;
import util.EscritaArquivo;
import util.FlashcardConstants;
import util.LeituraArquivo;

public class FlashcardModel {
	
	public List<Flashcard> carregarBaralho() {
		FlashcardController fc = new FlashcardController();		
		return fc.separarFlashcardsController();
	}
	
	/**
	* Separa os flashcards de acordo com a estrutura do arquivo de persistência 
	*
	* @param  conteudo Lista de todas as linhas do arquivo de persistência das cartas
	* @return Uma lista com todos os flashcards
	*/
	
	public List<Flashcard> separarFlashcards() {
		LeituraArquivo leitura = new LeituraArquivo();
		
		List<String> conteudo = leitura.lerConteudoArquivo(FlashcardConstants.CAMINHO_FLASHCARDS);
		ArrayList<Flashcard> flashcards = new ArrayList<Flashcard>();
		
		int i = 0;
		Flashcard carta = new Flashcard();
		
		for(String linha : conteudo) {		
			linha = linha.trim();
			
			if(linha.equals("-") || i == 0) {
				carta = new Flashcard();
			}
			
			else {
				String[] separacao = linha.split(";");
				
				if(separacao[0].equals("codigo")) {
					carta.setCodigo(Integer.valueOf(separacao[1]));
				}
				else if(separacao[0].equals("pergunta")) {
					carta.setPergunta(separacao[1]);
				}
				else if(separacao[0].equals("imagemPergunta")) {
					carta.setImagemPergunta(separacao[1]);
				}
				else if(separacao[0].equals("descricao")) {
					carta.setDescricao(separacao[1]);
				}
				else if(separacao[0].equals("imagemDescricao")) {
					carta.setImagemDescricao(separacao[1]);
					flashcards.add(carta);
				}
			}
			
			i++;
	
		}
		
		return flashcards;
	}
	
	/**
	* Adiciona as informações de um flashcard no arquivo de persistência 
	*
	* @param  pergunta Pergunta do flashcard
	* @param  imagemPergunta Caminho da imagem referente à pergunta
	* @param  descricao Descricao do flashcard
	* @param  imagemDescricao Caminho da imagem referente à descrição
	*/
	
	public void adicionarFlashcard(String pergunta, String imagemPergunta, String descricao, String imagemDescricao) {
		LeituraArquivo leitura = new LeituraArquivo();
		EscritaArquivo escrita = new EscritaArquivo();
		
		List<String> linhas = leitura.lerConteudoArquivo(FlashcardConstants.CAMINHO_FLASHCARDS);
		int cod = Integer.valueOf(pegarUltimoCodigo())+1;
		String codigo = String.valueOf(cod);
		
		linhas.add("-");
		linhas.add("codigo;"+codigo);
		linhas.add("pergunta;" + pergunta);
		linhas.add("imagemPergunta;" + imagemPergunta);
		linhas.add("descricao;" + descricao);
		linhas.add("imagemDescricao;" + imagemDescricao);
		
		escrita.escreverConteudoArquivo(linhas);
	}
	
	/**
	* Exclui um flashcard registrado no arquivo de persistência
	*
	* @param  atual O flashcard a ser excluido
	*/
	public void excluirCarta(Flashcard atual) {
		LeituraArquivo leitura = new LeituraArquivo();
		EscritaArquivo escrita = new EscritaArquivo();
		
		List<String> linhas = leitura.lerConteudoArquivo(FlashcardConstants.CAMINHO_FLASHCARDS);
		String ultimoCodigo = pegarUltimoCodigo();
		
		boolean achou = false;
		int i = 0;
		
		while(i < linhas.size() && achou == false) {
			String linha = linhas.get(i);
			
			if(linha.equals("codigo;" + atual.getCodigo()))
				achou = true;
			else
				i++;
		}
		
		//JOptionPane.showMessageDialog(null, "Linha: " + linhas.get((i)) + ", " + (i) + " tamanho: " + linhas.size());
		
		
		//if(!(ultimoCodigo.equals(String.valueOf(atual.getCodigo())))) {
		i--;
			for(int j = 1; j <= 6; j++) {
				linhas.remove(i);
			}
	//	}
		//else {
			//for(int j = 1; j <= 6; j++) {
				//linhas.remove(i);
			//}/
		//}
		
		escrita.escreverConteudoArquivo(linhas);
	}
	
	/**
	* Recupera o último código registrado no arquivo de persistência
	*
	* @return O último código registrado no arquivo de persistência
	*/

	public String pegarUltimoCodigo() {
		LeituraArquivo leitura = new LeituraArquivo();
		
		List<String> conteudo = leitura.lerConteudoArquivo(FlashcardConstants.CAMINHO_FLASHCARDS);
		String cod = "";
		for(String linha : conteudo) {		
			linha = linha.trim();
			
			if(!(linha.equals("-"))) {
			
				String[] separacao = linha.split(";");
				
				if(separacao[0].equals("codigo")) {
					cod = separacao[1];
				}
			}
		}
		
		return cod;
	}
	
	/**
	* Recupera um flashcard pelo código registrado no arquivo de persistência
	*
	* @param  codigo O código do flashcard a ser encontrado
	* @return O flashcard encontrado pelo código
	*/
	public Flashcard buscarCartaCodigo(String codigo)  {
		LeituraArquivo leitura = new LeituraArquivo();
		Flashcard carta = new Flashcard();
		boolean achou = false;
				
		try {
			List<String> linhas = leitura.lerConteudoArquivo(FlashcardConstants.CAMINHO_FLASHCARDS);
			int i = 0;
			
			while(i <= linhas.size() && achou == false) {
				String linha = linhas.get(i);
				
				if(!(linha.equals("-"))) {
					String[] separacao = linha.split(";");
					
					if(separacao[1].equals(codigo)) {
						achou = true;	
					}
					else {
						i++;
					}
				} else i++;
			}
			
			if(achou == true) {
				for(int k = i; k < i+5; k++) {
					String linhaAchada = linhas.get(k);
					String[] separacao = linhaAchada.split(";");
					
					if(separacao[0].equals("codigo")) {
						carta.setCodigo(Integer.valueOf(separacao[1]));
					}
					else if(separacao[0].equals("pergunta")) {
						carta.setPergunta(separacao[1]);
					}
					else if(separacao[0].equals("imagemPergunta")) {
						carta.setImagemPergunta(separacao[1]);
					}
					else if(separacao[0].equals("descricao")) {
						carta.setDescricao(separacao[1]);
					}
					else if(separacao[0].equals("imagemDescricao")) {
						carta.setImagemDescricao(separacao[1]);
					}					
				}
			}
		}
		catch (Exception e) {
		}

		return carta;
	}
}
