package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EscritaArquivo {
	public void escreverConteudoArquivo(List<String> linhas){		
		try {
			Files.write(Paths.get(FlashcardConstants.CAMINHO_FLASHCARDS), linhas);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void escreverConteudoArquivoCaixas(List<String> linhas, int caixa){		
		try {
			Files.write(Paths.get(FlashcardConstants.CAMINHO_CAIXAS + "caixa" + String.valueOf(caixa) + ".txt"), linhas);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
