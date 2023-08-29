package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.CaixaController;
import controller.FlashcardController;
import model.Flashcard;
import util.Customizacao;
import util.FlashcardConstants;
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
	JogoForm pai;
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
	
	public FlashcardJogoForm(Flashcard atual, LadoCarta lado, JogoForm pai) {
		setLado(lado);
		setPai(pai);
		setAtual(atual);
		iniciarComponentes();
		carregarEventos();
		carregarFrenteVerso();
		carregarCarta();
	}
	
	//inicio
	public FlashcardJogoForm(List<String> codCartas, Flashcard atual, LadoCarta lado, JogoForm pai) {
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
	
	public FlashcardJogoForm(List<String> codCartas, Flashcard atual, LadoCarta lado, JogoForm pai, int prox) {
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
				moverCartaParaCaixaSeguinte();
				retornaJanela().getPai().acertos++;
				irParaProximaCarta();
			}
		});
		
		nao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moverCartaParaCaixaAnterior();
				irParaProximaCarta();
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
				getPai().setVisible(false);
				JogoForm jogo = new JogoForm();
				jogo.setVisible(true);
				JogoForm.isOpened = true;
				FlashcardJogoForm.isOpened = false;
				mostrarPlacar();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
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
	
	
	private void moverCartaParaCaixaSeguinte() {
		FlashcardController fc = new FlashcardController();
		CaixaController cc = new CaixaController((this.getPai().opcaoCombo)+1);
		int proxCaixa = (this.getPai().opcaoCombo)+2; //2 -> posicaovetor+1
				
		if(proxCaixa <= FlashcardConstants.TOTAL_CAIXAS) {
			Flashcard flash = fc.buscarCartaCodigoController(codigoCartas.get(getIndexCartaAtual()));
			cc.moverCartaProximaController(flash.getCodigo(), proxCaixa);
			
		}
	}
	
	private void moverCartaParaCaixaAnterior() {
		FlashcardController fc = new FlashcardController();
		CaixaController cc = new CaixaController((this.getPai().opcaoCombo)+1);
		int proxCaixa = this.getPai().opcaoCombo + 0; // 0 -> posicaovetor-1
				
		if(proxCaixa > 0) {
			Flashcard flash = fc.buscarCartaCodigoController(getCodigoCartas().get(getIndexCartaAtual()));
			cc.moverCartaAnteriorController(flash.getCodigo(), proxCaixa);
		}
	}
	
	private void irParaProximaCarta() {
		if(getIndexCartaAtual()+1 < getNumeroCartas()) {
			FlashcardController fc = new FlashcardController();
			
			int prox = getIndexCartaAtual()+1;
			String proximoCodigo = getCodigoCartas().get(prox);
			
			Flashcard proxima = fc.buscarCartaCodigoController(proximoCodigo);
			FlashcardJogoForm novoForm = new FlashcardJogoForm(codigoCartas, proxima, LadoCarta.VERSO, this.getPai(), prox);
			getPai().tentativas++;
			
			this.setVisible(false);
			novoForm.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "Fim das cartas dessa caixa!");
			getPai().setVisible(false);
			JogoForm jogo = new JogoForm();
			jogo.setVisible(true);
			JogoForm.isOpened = true;
			this.setVisible(false);
			FlashcardJogoForm.isOpened = false;
			mostrarPlacar();
		}
	}
	
	public void mostrarPlacar() {
		JOptionPane.showMessageDialog(null, "Número de cartas passadas: " + (this.getPai().tentativas+1) + "\nLembrou-se de: " + this.getPai().acertos);
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

	public JogoForm getPai() {
		return pai;
	}

	public void setPai(JogoForm pai) {
		this.pai = pai;
	}

	public int getNumeroCartas() {
		return numeroCartas;
	}

	public void setNumeroCartas(int numeroCartas) {
		this.numeroCartas = numeroCartas;
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
	
	private FlashcardJogoForm retornaJanela() {
		return this;
	}
}
