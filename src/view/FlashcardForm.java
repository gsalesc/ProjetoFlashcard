package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.FlashcardController;
import model.Flashcard;
import util.Customizacao;
import util.Imagem;
import util.LadoCarta;

public class FlashcardForm extends JFrame {
	public static boolean isOpened = false;
	String numeroCarta;
	JPanel carta;
	JPanel superior, inferior;
	JButton virar, esquerda, direita;
	JButton excluir, trocar;
	JPanel frente, verso;
	LadoCarta lado;
	List<Flashcard> baralho;
	Flashcard atual;
	Component pai;
	
	public FlashcardForm(String num) {
		super(num);
		iniciarComponentes();
	}
	
	public FlashcardForm(Flashcard carta, String num) {
		super(num);
		iniciarComponentes();
	}
		
	public FlashcardForm(List<Flashcard> baralho, Flashcard atual, String num, Component pai) {
		super(num);
		iniciarComponentes();
		setBaralho(baralho);
		setAtual(atual);
		setLado(LadoCarta.VERSO);
		carregarFrenteVerso();
		carregarCarta();
		iniciarEventos(this);
		setPai(pai);
		numeroCarta = num;
	}
	
	public FlashcardForm(List<Flashcard> baralho, Flashcard atual, String num, LadoCarta lado, Component pai) {
		super(num);
		iniciarComponentes();
		setBaralho(baralho);
		setAtual(atual);
		setLado(lado);
		carregarFrenteVerso();
		carregarCarta();
		setPai(pai);
		iniciarEventos(this);
		numeroCarta = num;
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
		mudarHandCursorBotao(virar);
		inferior.add(virar);
		
		esquerda = new JButton("<");
		mudarHandCursorBotao(esquerda);
		inferior.add(esquerda);
		
		direita = new JButton(">");
		mudarHandCursorBotao(direita);
		inferior.add(direita);
		
		excluir = new JButton("Excluir");
		mudarHandCursorBotao(excluir);
		superior.add(excluir);
		
		trocar = new JButton("Trocar");
		mudarHandCursorBotao(trocar);
		superior.add(trocar);
	}
	
	private void carregarCarta() {
		//LineBorder borda = new LineBorder(Color.DARK_GRAY);
		//carta.setBorder(borda);
		
		if(lado == LadoCarta.FRENTE) {
			this.carta.add(frente);
		}
		else if(lado == LadoCarta.VERSO) {
			this.carta.add(verso);
		}
	}
	
	private void virarCarta() {
		FlashcardForm novoForm = null;
		
		if(lado == LadoCarta.FRENTE) {
			novoForm = new FlashcardForm(baralho, atual, numeroCarta, LadoCarta.VERSO, this.getPai());
		}
		else if(lado == LadoCarta.VERSO) {
			novoForm = new FlashcardForm(baralho, atual, numeroCarta, LadoCarta.FRENTE, this.getPai());
		}
		
		this.setVisible(false);
		novoForm.setVisible(true);
	}
	
	private void trocarCartaDireita() {
		int posicaoCartaAtual = this.getBaralho().indexOf(this.getAtual());
		if((posicaoCartaAtual+1) <= this.getBaralho().size()-1) {
			Flashcard proximo = this.getBaralho().get(posicaoCartaAtual+1);
			FlashcardForm novoForm = new FlashcardForm(baralho, proximo, String.valueOf((proximo.getCodigo())), this.getPai());
			this.setVisible(false);
			reproduzirSomVirar();
			novoForm.setVisible(true);
		}
	}
	
	private void trocarCartaEsquerda() {
		int posicaoCartaAtual = this.getBaralho().indexOf(this.getAtual());
		if((posicaoCartaAtual-1) >= 0) {
			Flashcard anterior = this.getBaralho().get(posicaoCartaAtual-1);
			FlashcardForm novoForm = new FlashcardForm(baralho, anterior, String.valueOf((anterior.getCodigo())),this.getPai());
			this.setVisible(false);
			reproduzirSomVirar();
			novoForm.setVisible(true);
		}
	}
	
	private void reproduzirSomVirar() {	

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
	
	private void iniciarEventos(FlashcardForm carta) {
		virar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				virarCarta();
			}
		});
		
		excluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				excluirCarta();
			}
		});
		
		esquerda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarCartaEsquerda();
			}
		});
		
		direita.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarCartaDireita();
			}
		});
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
	
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				FlashcardForm.isOpened = false;
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
		
	}
	
	private void excluirCarta() {
		FlashcardController fc = new FlashcardController();
		fc.excluirCarta(this.getAtual());
		
		this.getPai().setVisible(false);
		
		TabuleiroForm tab = new TabuleiroForm();
		tab.setVisible(true);
		
		this.setVisible(false);
	}

	public void mudarHandCursorBotao(JButton btn) {
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public List<Flashcard> getBaralho() {
		return baralho;
	}

	public void setBaralho(List<Flashcard> baralho) {
		this.baralho = baralho;
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

	public Component getPai() {
		return pai;
	}

	public void setPai(Component pai) {
		this.pai = pai;
	}
}
