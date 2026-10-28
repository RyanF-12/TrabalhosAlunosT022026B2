package objetos;

import java.util.Date;

public class Livro {

	private String titulo;
	private String categoria;
	private Date dataEmprestimo;
	
	public Livro(String titulo, String categoria) {
		this.titulo = titulo;
		this.categoria = categoria;
	}
	
	public Livro() {

	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}
	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	
	public String validarDisponibilidade() {
	    String VERDE = "\u001B[32m";
	    String VERMELHO = "\u001B[31m";
	    String RESET = "\u001B[0m";
		if(dataEmprestimo == null) {
			return VERDE+"Disponivel"+RESET;
		}else {
			return VERMELHO+"Indisponivel"+RESET;
		}
	}
}
