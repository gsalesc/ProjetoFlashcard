package model;

import java.util.ArrayList;
import java.util.List;

import controller.FlashcardController;
import util.EscritaArquivo;
import util.FlashcardConstants;
import util.LeituraArquivo;

public class CaixaModel {
	
	public List<String> carregarCaixa(int numCaixa) {
		LeituraArquivo arquivo = new LeituraArquivo();		
		List<String> conteudo = arquivo.lerConteudoArquivo(FlashcardConstants.CAMINHO_CAIXAS, numCaixa);
		
		return separarCodigoCaixas(conteudo);
	}
	
	private List<String> separarCodigoCaixas(List<String> conteudo) {
		ArrayList<String> codigos = new ArrayList<String>();
		for(String linha : conteudo) {
			codigos.add(linha);
		}
		return codigos;
	}
	
	/**
	* Move a carta para a próxima caixa
	*
	* @param  indexCartaAtual Código da carta a ser movida
	* @param  proxCaixa Número da caixa a ser colocada
	*/
	public void moverCartaProxima(int indexCartaAtual, int proxCaixa) {
		LeituraArquivo leitura = new LeituraArquivo();
		EscritaArquivo escrita = new EscritaArquivo();
		
		List<String> caixaAntiga = leitura.lerConteudoArquivo(FlashcardConstants.CAMINHO_CAIXAS, proxCaixa-1);
		int remover = caixaAntiga.indexOf(String.valueOf(indexCartaAtual));
		caixaAntiga.remove(remover);
		escrita.escreverConteudoArquivoCaixas(caixaAntiga, (proxCaixa-1));
		
		List<String> caixaNova = leitura.lerConteudoArquivo(FlashcardConstants.CAMINHO_CAIXAS, proxCaixa);
		caixaNova.add(String.valueOf(indexCartaAtual));
		escrita.escreverConteudoArquivoCaixas(caixaNova, proxCaixa);
	}
	
	/**
	* Move a carta para a caixa anterior
	*
	* @param  indexCartaAtual Código da carta a ser movida
	* @param  antCaixa Número da caixa a ser colocada
	*/
	public void moverCartaAnterior(int indexCartaAtual, int antCaixa) {
		LeituraArquivo leitura = new LeituraArquivo();
		EscritaArquivo escrita = new EscritaArquivo();
		
		List<String> caixaAntiga = leitura.lerConteudoArquivo(FlashcardConstants.CAMINHO_CAIXAS, antCaixa+1);
		int remover = caixaAntiga.indexOf(String.valueOf(indexCartaAtual));
		caixaAntiga.remove(remover);
		escrita.escreverConteudoArquivoCaixas(caixaAntiga, (antCaixa+1));
		
		List<String> caixaNova = leitura.lerConteudoArquivo(FlashcardConstants.CAMINHO_CAIXAS, antCaixa);
		caixaNova.add(String.valueOf(indexCartaAtual));
		escrita.escreverConteudoArquivoCaixas(caixaNova, antCaixa);
	}
	
	public void adicionarCodigoCarta(String codigo, int numCaixa) {
		LeituraArquivo leitura = new LeituraArquivo();
		EscritaArquivo escrita = new EscritaArquivo();
		
		List<String> caixa = leitura.lerConteudoArquivo(FlashcardConstants.CAMINHO_CAIXAS, numCaixa);
		caixa.add(codigo);
		escrita.escreverConteudoArquivoCaixas(caixa, numCaixa);
	}
	
	/**
	* Exclui a carta da caixa a qual pertence baseando-se no seu código
	*
	* @param  codigo Código da carta a ser excluída
	*/
	
	public void excluirCarta(int codigo, int numCaixa) {
		LeituraArquivo leitura = new LeituraArquivo();
		EscritaArquivo escrita = new EscritaArquivo();
		boolean achou = false;
		int caixas = numCaixa;
		List<String> caixa = null;
		
		while(achou == false && caixas <= 5) {
			caixa = leitura.lerConteudoArquivo(FlashcardConstants.CAMINHO_CAIXAS, numCaixa);
			
			if(caixa.indexOf(String.valueOf(codigo)) != -1)
				achou = true;
			else caixas++;
		}
		
		if(achou == true) {
			caixa.remove(String.valueOf(codigo));
			escrita.escreverConteudoArquivoCaixas(caixa, numCaixa);
		}
	}
}
