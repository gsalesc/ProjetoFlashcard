package util;

import java.awt.Component;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LeituraArquivo {
	public LeituraArquivo() {
	}
	
	//flashcards
	public List<String> lerConteudoArquivo(String caminho){
		List<String> conteudo = null;
		
		try {
			conteudo = Files.readAllLines(Paths.get(caminho, ""), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conteudo;
	}
	
	//caixas
	public List<String> lerConteudoArquivo(String caminho, int i) {
		List<String> conteudo = null;
		
		try {
			conteudo = Files.readAllLines(Paths.get(caminho + "caixa" + String.valueOf(i) + ".txt"), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conteudo;	
	}
	
	public String caminhoImagem(Component comp) {
		String caminho = "";
		JFileChooser explorador = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
		explorador.setFileFilter(filter);
		int retorno = explorador.showOpenDialog(comp);
		
		if(retorno == JFileChooser.APPROVE_OPTION) {
			caminho = explorador.getSelectedFile().getAbsolutePath();
		}
		
		return caminho;
	}
}
