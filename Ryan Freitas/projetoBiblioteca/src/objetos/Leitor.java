package objetos;

import java.util.ArrayList;
import java.util.List;

public class Leitor {

	private String nome;
	private List<Livro> livrosEmprestados;

	public Leitor(String nome) {
		this.nome = nome;
		this.livrosEmprestados = new ArrayList<Livro>();
	};


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Livro> getLivrosEmprestados() {
		return livrosEmprestados;
	}

	public void setLivrosEmprestados(List<Livro> livrosEmprestados) {
		this.livrosEmprestados = livrosEmprestados;
	}

}
