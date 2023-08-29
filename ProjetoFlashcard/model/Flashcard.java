package model;

public class Flashcard {
	private int codigo;
	private String pergunta;
	private String imagemPergunta;
	private String descricao;
	private String imagemDescricao;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getPergunta() {
		return pergunta;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getImagemPergunta() {
		return imagemPergunta;
	}
	public void setImagemPergunta(String imagemPergunta) {
		this.imagemPergunta = imagemPergunta;
	}
	public String getImagemDescricao() {
		return imagemDescricao;
	}
	public void setImagemDescricao(String imagemDescricao) {
		this.imagemDescricao = imagemDescricao;
	}
}
