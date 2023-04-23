package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Flashcard;
import util.EscritaArquivo;
import util.FlashcardConstants;
import util.LeituraArquivo;

public class FlashcardController {

	/**
	* Separa os flashcards de acordo com a estrutura do arquivo de persistência 
	*
	* @param  conteudo Lista de todas as linhas do arquivo de persistência das cartas
	* @return Uma lista com todos os flashcards
	*/
	
	public List<Flashcard> separarFlashcards(List<String> conteudo) {
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
		String codigo = pegarUltimoCodigo(linhas);
		
		linhas.add("-");
		linhas.add("codigo;"+codigo);
		linhas.add("pergunta;" + pergunta);
		linhas.add("imagemPergunta;" + imagemPergunta);
		linhas.add("descricao;" + descricao);
		linhas.add("imagemDescricao;" + imagemDescricao);
		
		escrita.escreverConteudoArquivo(linhas);
	}

	private String pegarUltimoCodigo(List<String> conteudo) {
		String cod = "";
		for(String linha : conteudo) {		
			linha = linha.trim();
			
			if(!(linha.equals("-"))) {
			
				String[] separacao = linha.split(";");
				
				if(separacao[0].equals("codigo")) {
					cod = separacao[1];
					int codMaisUm = Integer.valueOf(cod) + 1;
					cod = String.valueOf(codMaisUm);
				}
			}
		}
		
		return cod;
	}

	public void excluirCarta(Flashcard atual) {
		LeituraArquivo leitura = new LeituraArquivo();
		EscritaArquivo escrita = new EscritaArquivo();
		
		List<String> linhas = leitura.lerConteudoArquivo(FlashcardConstants.CAMINHO_FLASHCARDS);
		String ultimoCodigo = pegarUltimoCodigo(linhas);
		
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
		
		
		if(!(ultimoCodigo.equals(String.valueOf(atual.getCodigo())))) {
			i--;
			for(int j = 1; j <= 6; j++) {
				linhas.remove(i);
			}
		}
		else {
			for(int j = 1; j <= 6; j++) {
				linhas.remove(i);
			}
		}
		
		escrita.escreverConteudoArquivo(linhas);
	}


	public Flashcard buscarCartaCodigo(String codigo)  {
		LeituraArquivo leitura = new LeituraArquivo();
		Flashcard carta = new Flashcard();
		boolean achou = false;
		
		String cod = codigo;
		
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
