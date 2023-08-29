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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.CaixaController;
import controller.FlashcardController;
import model.Flashcard;
import util.Customizacao;
import util.FlashcardConstants;
import util.LadoCarta;

public class JogoForm extends JFrame {
	public static boolean isOpened = false;
	private JPanel principal;
	private JPanel superior, esquerda, direita, inferior;
	private JPanel caixa1, caixa2, caixa3, caixa4, caixa5;
	private JPanel localCartas;
	private JButton jogar;
	private JLabel mostrarAcertos;
	private JComboBox<String> comboCaixas;
	public int opcaoCombo;
	public int tentativas;
	public int acertos;
	
	public JogoForm() {
		iniciarComponentes();
		adicionarMenuSuperior();
		adicionarMenuInferior();
		iniciarCaixas();
		iniciarEventos();
		//preencherCaixa();
	}
	
	private void iniciarComponentes() {
		this.setSize(900, 400);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		
		principal = new JPanel();
		principal.setLayout(new GridLayout(1, 5));
		principal.setBackground(Color.DARK_GRAY);
		
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
	
	private void iniciarCaixas() {
		caixa1 = new JPanel();
		caixa1.setBackground(Color.DARK_GRAY);
		caixa1.setLayout(new BorderLayout());
		construirCaixa(caixa1, 1);
		localCartas = new JPanel();
		preencherCaixa(localCartas, 1);
		definirBordas(caixa1);
		caixa1.add(localCartas);
		principal.add(caixa1);

		caixa2 = new JPanel();
		caixa2.setBackground(Color.DARK_GRAY);
		caixa2.setLayout(new BorderLayout());
		construirCaixa(caixa2, 2);
		localCartas = new JPanel();
		preencherCaixa(localCartas, 2);
		definirBordas(caixa2);
		caixa2.add(localCartas);
		principal.add(caixa2);
		
		caixa3 = new JPanel();
		caixa3.setBackground(Color.DARK_GRAY);
		caixa3.setLayout(new BorderLayout());
		construirCaixa(caixa3, 3);
		localCartas = new JPanel();
		preencherCaixa(localCartas, 3);
		definirBordas(caixa3);
		caixa3.add(localCartas);
		principal.add(caixa3);
		
		caixa4 = new JPanel();
		caixa4.setBackground(Color.DARK_GRAY);
		caixa4.setLayout(new BorderLayout());
		construirCaixa(caixa4, 4);
		localCartas = new JPanel();
		preencherCaixa(localCartas, 4);
		definirBordas(caixa4);
		caixa4.add(localCartas);
		principal.add(caixa4);
		
		caixa5 = new JPanel();
		caixa5.setBackground(Color.DARK_GRAY);
		caixa5.setLayout(new BorderLayout());
		construirCaixa(caixa5, 5);
		localCartas = new JPanel();
		preencherCaixa(localCartas, 5);
		definirBordas(caixa5);
		caixa5.add(localCartas);
		principal.add(caixa5);
	}
	
	private void construirCaixa(JPanel caixa, int i) {
		JLabel nomeCaixa = new JLabel("Caixa "+ i);
		nomeCaixa.setForeground(Color.WHITE);
		nomeCaixa.setHorizontalAlignment(JLabel.CENTER);	
		caixa.add(nomeCaixa, BorderLayout.SOUTH);
	}

	private void adicionarMenuSuperior() {
		JLabel labelCaixas = new JLabel("Selecione uma caixa: ");
		superior.add(labelCaixas);
		
		comboCaixas = new JComboBox<String>();
		comboCaixas.addItem("Caixa 1");
		comboCaixas.addItem("Caixa 2");
		comboCaixas.addItem("Caixa 3");
		comboCaixas.addItem("Caixa 4");
		comboCaixas.addItem("Caixa 5");
		superior.add(comboCaixas);
		
		jogar = new JButton("Jogar");
		jogar.setHorizontalAlignment(JButton.RIGHT);
		jogar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		superior.add(jogar);
	
	}
	
	private void adicionarMenuInferior() {
		mostrarAcertos = new JLabel("Acertos: " + String.valueOf(acertos));
		inferior.add(mostrarAcertos);
	}
	
	private void preencherCaixa(JPanel local, int caixa) {
		definirLayout(local);
		//int caixas = FlashcardConstants.TOTAL_CAIXAS;
		
		CaixaController cc = new CaixaController(caixa);
		List<String> cartas = cc.getCaixa().getCodigoCartas();
			
		for(int i = 0; i < FlashcardConstants.MAX_CARTAS; i++) {
			if(i < cartas.size()) {
				String cod = cartas.get(i);
				JPanel panel = new JPanel();
				panel.setBackground(Color.ORANGE);
				
				LineBorder borda = new LineBorder(Color.DARK_GRAY, 3);
				panel.setBorder(borda);
				
				if(!(cod.equals(""))) {
					Customizacao.escreverNumeroCarta(panel, Integer.valueOf(cod));
					carregarEventoCarta(panel);
					local.add(panel);
				}
				
			}
			else {
				JPanel panel = new JPanel();
				panel.setBackground(Color.DARK_GRAY);
				local.add(panel);
			}
		}
		
	}
	
	private void definirBordas(JPanel panel) {
		LineBorder borda = new LineBorder(Color.LIGHT_GRAY, 10);
		panel.setBorder(borda);
	}
	
	private void definirLayout(JPanel panel) {
		panel.setLayout(new GridLayout(5,8));
	}
	
	private void carregarEventoCarta(JPanel carta) {		
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
				/* FlashcardController fc = new FlashcardController();
				 * Flashcard carta = fc.buscarCartaPorCódigo(index);
				 * 
				 * 
				 * 
				 * 
				 * */
			}
		});
	}
	
	private void iniciarEventos() {
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
				int input = JOptionPane.showConfirmDialog(null, "Deseja sair do jogo?");
				if(input == JOptionPane.YES_OPTION) {
					janelaJogo().setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					
					MenuForm mn = new MenuForm();
					mn.setVisible(true);
					JogoForm.isOpened = false;
				}
				else {
					janelaJogo().setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {}
			
			@Override
			public void windowActivated(WindowEvent e) {}
		});
		
		comboCaixas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opcaoCombo = comboCaixas.getSelectedIndex();
			}
		});
		
		jogar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciarJogo();
			}
		});
	}
	
	private void iniciarJogo() {
		FlashcardController fc = new FlashcardController();
		
		CaixaController cc = new CaixaController(opcaoCombo+1);
		List<String> cartas = cc.getCaixa().getCodigoCartas();
		
		if(cartas.size() > 0) {
			if(FlashcardJogoForm.isOpened == false) {
				Flashcard carta = fc.buscarCartaCodigoController(cartas.get(0));
				FlashcardJogoForm fjf = new FlashcardJogoForm(cartas, carta, LadoCarta.VERSO, this, 0);
				fjf.setVisible(true);
				tentativas = 0;
				acertos = 0;
			}
		} else JOptionPane.showMessageDialog(null, "Não há cartas nessa caixa!");
	}
	
	private JFrame janelaJogo() {
		return this;
	}
}
