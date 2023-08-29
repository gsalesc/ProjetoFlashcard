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
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.Flashcard;

public class MenuForm extends JFrame{

	JPanel menuBotoes;
	JPanel superior, esquerda, direita, inferior;
	JButton jogar, gerenciar, opcoes;
	
	public MenuForm() {
		iniciarComponentes();
		carregarEventos();
	}
	
	private void iniciarComponentes() {
		this.setTitle("Flashcards");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		menuBotoes = new JPanel();
		menuBotoes.setBackground(Color.DARK_GRAY);
		menuBotoes.setLayout(new GridLayout(3, 1));
		this.add(menuBotoes, BorderLayout.CENTER);
		
		superior = new JPanel();
		superior.setSize(this.getWidth(), 300);
		
		esquerda = new JPanel();
		esquerda.setSize(300, this.getHeight());
		
		direita = new JPanel();
		direita.setSize(300, this.getHeight());
		
		inferior = new JPanel();
		inferior.setSize(this.getWidth(), 300);
		
		this.add(superior, BorderLayout.NORTH);
		this.add(esquerda, BorderLayout.WEST);
		this.add(direita, BorderLayout.EAST);
		this.add(inferior, BorderLayout.SOUTH);
		
		LineBorder borda = new LineBorder(Color.DARK_GRAY, 3);
		
		jogar = new JButton("Jogar");
		jogar.setBorder(borda);
		jogar.setBackground(Color.ORANGE);
		carregarCustomizacaoBotao(jogar);
		menuBotoes.add(jogar);
		
		gerenciar = new JButton("Gerenciar");
		gerenciar.setName("TabuleiroForm");
		gerenciar.setBorder(borda);
		gerenciar.setBackground(Color.ORANGE);
		carregarCustomizacaoBotao(gerenciar);
		menuBotoes.add(gerenciar);
		
		opcoes = new JButton("Opções");
		opcoes.setBorder(borda);
		opcoes.setBackground(Color.ORANGE);
		carregarCustomizacaoBotao(opcoes);
		menuBotoes.add(opcoes);
	}
	
	private void carregarCustomizacaoBotao(JButton botao) {
		
		Color cor = botao.getBackground();
		botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
		botao.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				botao.setBackground(cor);

			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				botao.setBackground(Color.CYAN);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
	}
	
	private void carregarEventos() {
		
		jogar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JogoForm.isOpened == false) {
					JogoForm jf = new JogoForm();
					jf.setVisible(true);
					
					janelaMenu().dispose();
				}
			}
		});
		gerenciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(TabuleiroForm.isOpened == false) {
					TabuleiroForm tab = new TabuleiroForm();
					tab.setVisible(true);
				
					TabuleiroForm.isOpened = true;
					janelaMenu().dispose();
				}
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
				int input = JOptionPane.showConfirmDialog(null, "Deseja sair?");
				if(input == JOptionPane.YES_OPTION) {
					janelaMenu().setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					System.exit(0);
				}
				else {
					janelaMenu().setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
	}
	
	private JFrame janelaMenu() {
		return this;
	}
}
