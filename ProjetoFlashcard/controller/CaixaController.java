package controller;

import java.util.ArrayList;
import java.util.List;

import model.Caixa;
import model.CaixaModel;
import util.EscritaArquivo;
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
		carregarCaixaController();
	}
	
	public void carregarCaixaController() {
		CaixaModel cm = new CaixaModel();
		this.getCaixa().setCodigoCartas(cm.carregarCaixa(getNumeroCaixa()));
	}
	
	public void moverCartaProximaController(int indexCartaAtual, int proxCaixa) {
		CaixaModel cm = new CaixaModel();
		cm.moverCartaProxima(indexCartaAtual, proxCaixa);
	}

	public void moverCartaAnteriorController(int indexCartaAtual, int antCaixa) {
		CaixaModel cm = new CaixaModel();
		cm.moverCartaAnterior(indexCartaAtual, antCaixa);
	}
	
	public void adicionarCodigoCartaController() {
		FlashcardController fm = new FlashcardController();
		int codigo = Integer.valueOf(fm.pegarUltimoCodigoController());
		String cod = String.valueOf(codigo);
		
		CaixaModel cm = new CaixaModel();
		cm.adicionarCodigoCarta(cod, getNumeroCaixa());
	}
	
	
	public void excluirCartaController(int codigo) {
		CaixaModel caixa = new CaixaModel();
		caixa.excluirCarta(codigo, getNumeroCaixa());
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