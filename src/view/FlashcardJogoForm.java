package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.FlashcardController;
import model.Flashcard;
import util.Customizacao;
import util.Imagem;
import util.LadoCarta;

public class FlashcardJogoForm extends JFrame{
	public static boolean isOpened = false;
	private boolean marcarLembrou = false;
	String numeroCarta;
	JPanel carta;
	JPanel superior, inferior;
	JLabel lembrou;
	JButton sim, nao, virar;
	JPanel frente, verso;
	LadoCarta lado;
	Flashcard atual;
	JFrame pai;
	List<String> codigoCartas;
	int numeroCartas;
	int indexCartaAtual;
	
	public FlashcardJogoForm() {
		iniciarComponentes();
	}
	
	public FlashcardJogoForm(Flashcard atual, LadoCarta lado) {
		setAtual(atual);
		setLado(lado);
		iniciarComponentes();
		carregarEventos();
		carregarFrenteVerso();
		carregarCarta();
	}
	
	public FlashcardJogoForm(Flashcard atual, LadoCarta lado, JFrame pai) {
		setLado(lado);
		setPai(pai);
		setAtual(atual);
		iniciarComponentes();
		carregarEventos();
		carregarFrenteVerso();
		carregarCarta();
	}
	
	public FlashcardJogoForm(List<String> codCartas, Flashcard atual, LadoCarta lado, JFrame pai) {
		setLado(lado);
		setPai(pai);
		setAtual(atual);
		setCodigoCartas(codCartas);
		setNumeroCartas(codCartas.size());
		setIndexCartaAtual(1);
		iniciarComponentes();
		carregarEventos();
		carregarFrenteVerso();
		carregarCarta();

	}
	
	public FlashcardJogoForm(List<String> codCartas, Flashcard atual, LadoCarta lado, JFrame pai, int prox) {
		setLado(lado);
		setPai(pai);
		setAtual(atual);
		setCodigoCartas(codCartas);
		setNumeroCartas(codCartas.size());
		setIndexCartaAtual(prox);
		iniciarComponentes();
		carregarEventos();
		carregarFrenteVerso();
		carregarCarta();
	}
	
	private void iniciarComponentes() {
		this.setSize(300, 350);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		carta = new JPanel();
		this.add(carta, BorderLayout.CENTER);
		
		superior = new JPanel();
		this.add(superior, BorderLayout.NORTH);
		
		inferior = new JPanel();
		this.add(inferior, BorderLayout.SOUTH);
		
		virar = new JButton("Virar");
		Customizacao.mudarHandCursorBotao(virar);
		
		lembrou = new JLabel("Lembrou?");
		
		sim = new JButton("Sim");
		Customizacao.mudarHandCursorBotao(sim);
		
		nao = new JButton("Não");
		Customizacao.mudarHandCursorBotao(nao);
		
		if(lado == LadoCarta.FRENTE) {
			inferior.add(lembrou);
			inferior.add(sim);
			inferior.add(nao);
			inferior.add(virar);
		}
		else {
			inferior.add(virar);
		}
	}

	private void carregarFrenteVerso() {
		Imagem imagem = new Imagem();
		frente = new JPanel();
		JLabel label = new JLabel();
		label.setText(this.getAtual().getDescricao());
		ImageIcon img = new ImageIcon(this.getAtual().getImagemDescricao());
		img.setImage(imagem.redimensionarIcon(img.getImage()));
		Customizacao.ajustarIcon(label);
		label.setIcon(img);
		frente.add(label);
		
		verso = new JPanel();
		label = new JLabel();
		label.setText(this.getAtual().getPergunta());
		img = new ImageIcon(this.getAtual().getImagemPergunta());
		img.setImage(imagem.redimensionarIcon(img.getImage()));
		Customizacao.ajustarIcon(label);
		label.setIcon(img);
		verso.add(label);
	}
	
	private void carregarCarta() {
		if(lado == LadoCarta.FRENTE) {
			this.carta.add(frente);
		}
		else if(lado == LadoCarta.VERSO) {
			this.carta.add(verso);
		}
	}
	
	private void carregarEventos() {
		virar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				virarCarta();
			}
		});
		
		sim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//moverCarta
				//contarAcerto
				irParaProximaCarta();
			}
		});
		
		nao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				irParaProximaCarta();
			}
		});
	}
	
	private void virarCarta() {
		FlashcardJogoForm novoForm = null;
		
		if(lado == LadoCarta.FRENTE) {
			novoForm = new FlashcardJogoForm(getCodigoCartas(), getAtual(), LadoCarta.VERSO, this.getPai(), getIndexCartaAtual());
		}
		else if(lado == LadoCarta.VERSO) {
			novoForm = new FlashcardJogoForm(getCodigoCartas(), getAtual(), LadoCarta.FRENTE, this.getPai(), getIndexCartaAtual());
		}
		
		this.setVisible(false);	
		novoForm.setVisible(true);
	}
	
	
	private void irParaProximaCarta() {
		if(getIndexCartaAtual()+1 < getNumeroCartas()) {
			FlashcardController fc = new FlashcardController();
			
			int prox = getIndexCartaAtual()+1;
			String proximoCodigo = getCodigoCartas().get(prox);
			
			Flashcard proxima = fc.buscarCartaCodigo(proximoCodigo);
			FlashcardJogoForm novoForm = new FlashcardJogoForm(codigoCartas, proxima, LadoCarta.VERSO, this.getPai(), prox);
			
			this.setVisible(false);
			novoForm.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "Fim das cartas dessa caixa!");
			this.setVisible(false);
		}
	}
	
	public Flashcard getAtual() {
		return atual;
	}

	public void setAtual(Flashcard atual) {
		this.atual = atual;
	}

	public LadoCarta getLado() {
		return lado;
	}

	public void setLado(LadoCarta lado) {
		this.lado = lado;
	}

	public JFrame getPai() {
		return pai;
	}

	public void setPai(JFrame pai) {
		this.pai = pai;
	}

	public int getNumeroCartas() {
		return numeroCartas;
	}

	public void setNumeroCartas(int numeroCartas) {
		this.numeroCartas = numeroCartas;
	}
	
	public JFrame retornaJanela() {
		return this;
	}

	public int getIndexCartaAtual() {
		return indexCartaAtual;
	}

	public void setIndexCartaAtual(int cartaAtual) {
		this.indexCartaAtual = cartaAtual;
	}

	public List<String> getCodigoCartas() {
		return codigoCartas;
	}

	public void setCodigoCartas(List<String> codigoCartas) {
		this.codigoCartas = codigoCartas;
	}
}
