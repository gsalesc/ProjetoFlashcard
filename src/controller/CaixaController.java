package controller;

import java.util.ArrayList;
import java.util.List;
import model.Caixa;
import util.FlashcardConstants;
import util.LeituraArquivo;

public class CaixaController {
	private Caixa caixa;
	private int numeroCaixa;
	
	public CaixaController() {
		setCaixa(new Caixa());
	}
	
	public CaixaController(int i) {
		setCaixa(new Caixa());
		setNumeroCaixa(i);
		carregarCaixa();
	}
	
	public void carregarCaixa() {
		LeituraArquivo arquivo = new LeituraArquivo();
		FlashcardController fc = new FlashcardController();
		
		List<String> conteudo = arquivo.lerConteudoArquivo(FlashcardConstants.CAMINHO_CAIXAS, this.getNumeroCaixa());
		this.getCaixa().setCodigoCartas(this.separarCodigoCaixas(conteudo));
	}
	
	private List<String> separarCodigoCaixas(List<String> conteudo) {
		ArrayList<String> codigos = new ArrayList<String>();
		
		for(String linha : conteudo) {
			codigos.add(linha);
		}
		
		return codigos;
	}
	
	public int getNumeroCaixa() {
		return numeroCaixa;
	}

	public void setNumeroCaixa(int numeroCaixa) {
		this.numeroCaixa = numeroCaixa;
	}
	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}	
}