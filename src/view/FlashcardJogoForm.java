package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	Component pai;
	int cartas;
	int cartaAtual;
	
	public FlashcardJogoForm() {
		iniciarComponentes();
	}
	
	public FlashcardJogoForm(Flashcard atual, LadoCarta lado) {
		iniciarComponentes();
		setAtual(atual);
		setLado(lado);
		carregarEventos();
		carregarFrenteVerso();
		carregarCarta();
	}
	
	public FlashcardJogoForm(Flashcard atual, LadoCarta lado, Component pai) {
		iniciarComponentes();
		setLado(lado);
		setPai(pai);
		setAtual(atual);
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
		
		lembrou = new JLabel("Lembrou?");
		//inferior.add(lembrou);
		
		virar = new JButton("Virar");
		Customizacao.mudarHandCursorBotao(virar);
		inferior.add(virar);
		
		/*sim = new JButton("Sim");
		Customizacao.mudarHandCursorBotao(sim);
		inferior.add(sim);
		
		nao = new JButton("Não");
		Customizacao.mudarHandCursorBotao(nao);
		inferior.add(nao);*/
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
		//LineBorder borda = new LineBorder(Color.DARK_GRAY);
		//carta.setBorder(borda);
		
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
	}
	
	private void virarCarta() {
		FlashcardJogoForm novoForm = null;
		
		if(lado == LadoCarta.FRENTE) {
			novoForm = new FlashcardJogoForm(atual, LadoCarta.VERSO, this.getPai());
		}
		else if(lado == LadoCarta.VERSO) {
			novoForm = new FlashcardJogoForm(atual, LadoCarta.FRENTE, this.getPai());
		}
		
		this.setVisible(false);
		novoForm.setVisible(true);
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
