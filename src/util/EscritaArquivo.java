package util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EscritaArquivo {
	public void escreverConteudoArquivo(List<String> linhas){		
		try {
			Files.write(Path.of(FlashcardConstants.CAMINHO_FLASHCARDS), linhas);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
