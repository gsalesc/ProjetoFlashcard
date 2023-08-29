package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.FlashcardController;
import model.Flashcard;
import util.Customizacao;
import util.FlashcardConstants;

public class TabuleiroForm extends JFrame{
	public static boolean isOpened = false;
	private JPanel principal;
	private JPanel superior, esquerda, direita, inferior;
	private JButton adicionar, sortear;
	
	public TabuleiroForm(){
		super("Tabuleiro de Flashcards");
		iniciarComponentes();
		adicionarMenuSuperior();
		iniciarEventos();
		carregarFlashcards();
	}
	
	private void iniciarComponentes() {
		this.setLayout(new BorderLayout());
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		
		principal = new JPanel();
		principal.setBackground(Color.LIGHT_GRAY);
		
		superior = new JPanel();
		superior.setSize(this.getWidth(), 300);
		
		esquerda = new JPanel();
		esquerda.setSize(300, this.getHeight());
		
		direita = new JPanel();
		direita.setSize(300, this.getHeight());
		
		inferior = new JPanel();
		inferior.setSize(this.getWidth(), 300);
		
		this.add(principal, BorderLayout.CENTER);
		this.add(superior, BorderLayout.NORTH);
		this.add(esquerda, BorderLayout.WEST);
		this.add(direita, BorderLayout.EAST);
		this.add(inferior, BorderLayout.SOUTH);
	}
	
	private void adicionarMenuSuperior() {
		adicionar = new JButton("Adicionar");
		adicionar.setHorizontalAlignment(JButton.LEFT);
		adicionar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		superior.add(adicionar);
		
		sortear = new JButton("Sortear");
		sortear.setHorizontalAlignment(JButton.LEFT);
		sortear.setCursor(new Cursor(Cursor.HAND_CURSOR));
		superior.add(sortear);
	}
	
	private void carregarFlashcards() {
		
		FlashcardController fc = new FlashcardController();
		List<Flashcard> bar = fc.separarFlashcardsController();
		int qtdCartas = bar.size();
		
		principal.setLayout(new GridLayout(5, 8));
			
		if(qtdCartas <= FlashcardConstants.MAX_CARTAS) {
			for(int i = 0; i < FlashcardConstants.MAX_CARTAS; i++) {
				
				FlashcardPanel carta;
				//carta.setSize(principal.getWidth()/5, principal.getHeight()/colunas);
				
				if(i < qtdCartas) {
					Flashcard flashcard = bar.get(i);
					
					carta = new FlashcardPanel(flashcard);
					carta.setFlashcard(flashcard);
					carta.setBackground(Color.ORANGE);
					carta.setCursor(new Cursor(Cursor.HAND_CURSOR));
					LineBorder borda = new LineBorder(Color.DARK_GRAY, 3);
					carta.setBorder(borda);
					
					Customizacao.escreverNumeroCarta(carta, (i+1));
					carregarEventoCarta(bar, carta, String.valueOf(i+1));
				}
				else {
					carta = new FlashcardPanel();
					carta.setBackground(Color.DARK_GRAY);
				}
				
				principal.add(carta);
			}
		}
		
	}
	
	private void sortearCarta() {
		FlashcardController fc = new FlashcardController();
		Flashcard novo = fc.sortear();
		FlashcardForm flashcard = new FlashcardForm(fc.separarFlashcardsController(), novo, String.valueOf(novo.getCodigo()), this);
		flashcard.setVisible(true);
	}
	
	private void carregarEventoCarta(List<Flashcard> baralho, FlashcardPanel carta, String num) {
		Flashcard flash = carta.getFlashcard();
		
		Color cor = carta.getBackground();
		carta.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {
				carta.setBackground(cor);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				carta.setBackground(Color.CYAN);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(FlashcardForm.isOpened == false) {
					FlashcardForm flashcard = new FlashcardForm(baralho, flash, num, janelaTabuleiro());
					flashcard.setVisible(true);
					
					FlashcardForm.isOpened = true;
				}
			}
		});
	}
	
	private void iniciarEventos() {
		sortear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(FlashcardForm.isOpened == false) {
					sortearCarta();				
					FlashcardForm.isOpened = true;
				}
			}
		});
		
		adicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adicionarCarta();
			}
		});
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {}
			
			@Override
			public void windowIconified(WindowEvent e) {}
			
			@Override
			public void windowDeiconified(WindowEvent e) {}
			
			@Override
			public void windowDeactivated(WindowEvent e) {}
			
			@Override
			public void windowClosing(WindowEvent e) {
				int input = JOptionPane.showConfirmDialog(null, "Deseja sair do tabuleiro?");
				if(input == JOptionPane.YES_OPTION) {
					janelaTabuleiro().setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					
					MenuForm mn = new MenuForm();
					mn.setVisible(true);
					
					TabuleiroForm.isOpened = false;
				}
				else {
					janelaTabuleiro().setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {}
			
			@Override
			public void windowActivated(WindowEvent e) {}
		});
	}
	
	private void adicionarCarta() {
		FlashcardController fc = new FlashcardController();
		List<Flashcard> bar = fc.separarFlashcardsController();
		
		if(bar.size() <= FlashcardConstants.MAX_CARTAS) {
			if(AdicionarFlashcardForm.isOpened == false) {
				AdicionarFlashcardForm aff = new AdicionarFlashcardForm(this);
				aff.setVisible(true);
				
				AdicionarFlashcardForm.isOpened = true;
			}
		}
		else JOptionPane.showMessageDialog(null, "Limite de cartas já foi atingido");
	}
	
	private JFrame janelaTabuleiro() {
		return this;
	}
}
