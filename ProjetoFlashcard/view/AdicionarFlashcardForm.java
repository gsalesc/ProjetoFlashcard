package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.FlashcardController;
import util.LeituraArquivo;

public class AdicionarFlashcardForm extends JFrame{
	public static boolean isOpened = false;
	private JLabel labelPergunta, labelDescricao;
	private JLabel labelImagemPergunta, labelImagemDescricao;
	private JTextField txtPergunta, txtDescricao;
	private JTextField txtImagemPergunta, txtImagemDescricao;
	private JButton carregarImagemPergunta, carregarImagemDescricao;
	private JButton adicionar;
	private JFrame pai;
	
	public AdicionarFlashcardForm() {
		super("Adicionar flashcard");
		setSize(500, 400);
		this.setLayout(null);
		iniciarComponentes();
		iniciarEventos();
	}
	
	public AdicionarFlashcardForm(JFrame comp) {
		super("Adicionar flashcard");
		setSize(500, 400);
		this.setLayout(null);
		iniciarComponentes();
		iniciarEventos();
		setPai(comp);
	}
	
	public void iniciarComponentes(){
		labelPergunta = new JLabel("Pergunta:");
		labelPergunta.setBounds(20, 10, 100, 50);
		this.add(labelPergunta);
		
		txtPergunta = new JTextField();
		txtPergunta.setBounds(90, 20, 350, 30);
		this.add(txtPergunta);
		
		labelImagemPergunta = new JLabel("Imagem:");
		labelImagemPergunta.setBounds(20, 50, 100, 50);
		this.add(labelImagemPergunta);
		
		txtImagemPergunta = new JTextField();
		txtImagemPergunta.setBounds(90, 60, 250, 30);
		txtImagemPergunta.setEnabled(false);
		this.add(txtImagemPergunta);
		
		carregarImagemPergunta = new JButton("Carregar");
		carregarImagemPergunta.setBounds(340, 60, 100, 30);
		this.add(carregarImagemPergunta);
		
		labelDescricao = new JLabel("Descrição:");
		labelDescricao.setBounds(20, 160, 100, 50);
		this.add(labelDescricao);
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(90, 170, 350, 30);
		this.add(txtDescricao);
		
		labelImagemDescricao = new JLabel("Imagem:");
		labelImagemDescricao.setBounds(20, 200, 100, 50);
		this.add(labelImagemDescricao);
		
		txtImagemDescricao = new JTextField();
		txtImagemDescricao.setBounds(90, 210, 250, 30);
		txtImagemDescricao.setEnabled(false);
		this.add(txtImagemDescricao);
		
		carregarImagemDescricao = new JButton("Carregar");
		carregarImagemDescricao.setBounds(340, 210, 100, 30);
		this.add(carregarImagemDescricao);
		
		adicionar = new JButton("Adicionar");
		adicionar.setBounds(340, 300, 100, 30);
		this.add(adicionar);
	}
	
	private void iniciarEventos() {
		carregarImagemPergunta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				carregarImagemPergunta();
			}
		});
		
		carregarImagemDescricao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				carregarImagemDescricao();
			}
		});
		
		adicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adicionarFlashcard();
			}
		});
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				AdicionarFlashcardForm.isOpened = false;
			}

			@Override
			public void windowOpened(WindowEvent e) {}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}
			
		});
	}
	
	private void carregarImagemPergunta() {
		LeituraArquivo leitura = new LeituraArquivo();
		String caminho = leitura.caminhoImagem(this);
		
		txtImagemPergunta.setText(caminho);
	}
	
	private void carregarImagemDescricao() {
		LeituraArquivo leitura = new LeituraArquivo();
		String caminho = leitura.caminhoImagem(this);
		
		txtImagemDescricao.setText(caminho);
	}
	
	private void adicionarFlashcard() {
		if(!(txtImagemPergunta.getText().equals("") && txtImagemDescricao.getText().equals(""))) {
			FlashcardController fc = new FlashcardController();
			fc.adicionarFlashcardController(txtPergunta.getText(), txtImagemPergunta.getText(), txtDescricao.getText(), txtImagemDescricao.getText());
			this.getPai().setVisible(false);
			
			TabuleiroForm tab = new TabuleiroForm();
			tab.setVisible(true);
			
			this.setVisible(false);
		}
	}

	public JFrame getPai() {
		return pai;
	}

	public void setPai(JFrame pai) {
		this.pai = pai;
	}
}
