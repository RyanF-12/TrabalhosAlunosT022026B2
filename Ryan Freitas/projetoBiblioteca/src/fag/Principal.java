package fag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import objetos.Livro;
import objetos.Leitor;

public class Principal {

	static Scanner scan = new Scanner(System.in);

	static List<Livro> listaLivros = new ArrayList<Livro>();
	static List<Leitor> listaLeitores = new ArrayList<Leitor>();

	public static void main(String[] args) {

		tabelaInputs();

		System.out.println("Sistema de Biblioteca iniciado!");

		mostrarMenu();
	}

	private static void tabelaInputs() {

		listaLeitores.add(new Leitor("Patrick"));
		listaLeitores.add(new Leitor("Ryan"));
		listaLeitores.add(new Leitor("Pedro"));
		listaLeitores.add(new Leitor("Raquel"));
		listaLeitores.add(new Leitor("Julia"));
		listaLeitores.add(new Leitor("Jeferson"));
		listaLeitores.add(new Leitor("Adenor"));

		listaLivros.add(new Livro("Dom Casmurro", "Machado de Assis"));
		listaLivros.add(new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry"));
		listaLivros.add(new Livro("Harry Potter", "J. K. Rowling"));
		listaLivros.add(new Livro("O Hobbit", "J. R. R. Tolkien"));
		listaLivros.add(new Livro("A Culpa é das Estrelas", "John Green"));
		listaLivros.add(new Livro("O Senhor dos Anéis", "J. R. R. Tolkien"));
		listaLivros.add(new Livro("1984", "George Orwell"));
		listaLivros.add(new Livro("It: A Coisa", "Stephen King"));
		listaLivros.add(new Livro("A Revolução dos Bichos", "George Orwell"));
		listaLivros.add(new Livro("O Alquimista", "Paulo Coelho"));
	}

	public static void mostrarMenu() {

		int escolhaUsuario;

		do {
			System.out.println("\n------ SISTEMA DE BIBLIOTECA ------");
			System.out.println("1 - Listar livros");
			System.out.println("2 - Cadastrar livro");
			System.out.println("3 - Excluir livro");
			System.out.println("4 - Realizar empréstimo");
			System.out.println("5 - Devolver livro");
			System.out.println("6 - Cadastrar leitor");
			System.out.println("7 - Listar leitores");
			System.out.println("0 - Sair");
			System.out.print("Escolha uma opção: ");

			escolhaUsuario = scan.nextInt();
			scan.nextLine();

			validarEscolha(escolhaUsuario);

		} while (escolhaUsuario != 0);
	}

	public static void validarEscolha(int escolhaUsuario) {

		switch (escolhaUsuario) {

		case 0:
			System.out.println("Sistema encerrado. Obrigado por utilizar!");
			break;

		case 1:
			listarLivros();
			break;

		case 2:
			cadastrarLivro();
			break;

		case 3:
			excluirLivro();
			break;

		case 4:
			emprestarLivro();
			break;

		case 5:
			devolverLivro();
			break;

		case 6:
			cadastrarLeitor();
			break;

		case 7:
			listarLeitores();
			break;

		default:
			System.out.println("Insira um valor válido!");
		}
	}

	private static void listarLivros() {

		System.out.println("\n-------- LISTA DE LIVROS --------");

		if (listaLivros.isEmpty()) {
			System.out.println("Não existem livros cadastrados!");
			return;
		}

		for (int i = 0; i < listaLivros.size(); i++) {
			Livro livro = listaLivros.get(i);
			System.out.printf("%d) %s - %s - %s\n", i + 1, livro.getTitulo(), livro.getCategoria(),
					livro.validarDisponibilidade());
		}
		System.out.println();
	}

	private static void cadastrarLivro() {
		Livro livro = new Livro(null, null);

		System.out.print("Informe o título do livro: ");
		livro.setTitulo(scan.nextLine());

		System.out.print("Informe o autor do livro: ");
		livro.setCategoria(scan.nextLine());

		listaLivros.add(livro);
		System.out.println("Livro cadastrado com sucesso!");
	}

	private static void excluirLivro() {

		if (listaLivros.isEmpty()) {
			System.out.println("Não existem livros para excluir!");
			return;
		}

		listarLivros();

		System.out.print("Informe o número do livro que deseja excluir: ");
		int escolhaUsuario = scan.nextInt() - 1;
		scan.nextLine();

		if (escolhaUsuario < 0 || escolhaUsuario >= listaLivros.size()) {
			System.out.println("Número inválido!");
			return;
		}
		if (listaLivros.get(escolhaUsuario).getDataEmprestimo() != null) {
			System.out.println("O livro está emprestado e não pode ser excluído!");
			return;
		}
		listaLivros.remove(escolhaUsuario);
		System.out.println("Livro removido com sucesso!");
	}

	private static void emprestarLivro() {

		if (listaLeitores.isEmpty()) {
			System.out.println("Não existem leitores cadastrados!");
			return;
		}
		if (listaLivros.isEmpty()) {
			System.out.println("Não existem livros cadastrados!");
			return;
		}

		listarLeitores();

		System.out.print("Informe o número do leitor: ");
		int usuario = scan.nextInt() - 1;
		scan.nextLine();

		if (usuario < 0 || usuario >= listaLeitores.size()) {
			System.out.println("Leitor inválido!");
			return;
		}
		if (listaLeitores.get(usuario).getLivrosEmprestados().size() >= 2) {
			System.out.println("O leitor já possui dois livros emprestados!");
			return;
		}

		listarLivros();

		System.out.print("Informe o número do livro que deseja emprestar: ");
		int livro = scan.nextInt() - 1;
		scan.nextLine();

		if (livro < 0 || livro >= listaLivros.size()) {
			System.out.println("Livro inválido!");
			return;
		}
		if (listaLivros.get(livro).getDataEmprestimo() != null) {
			System.out.println("Este livro já está emprestado!");
			return;
		}

		listaLeitores.get(usuario).getLivrosEmprestados().add(listaLivros.get(livro));
		listaLivros.get(livro).setDataEmprestimo(new Date());
		System.out.println("Livro emprestado com sucesso!");
	}

	private static void devolverLivro() {

		if (listaLeitores.isEmpty()) {
			System.out.println("Não existem leitores cadastrados!");
			return;
		}

		listarLeitores();

		System.out.print("Informe o número do leitor que vai devolver o livro: ");
		int usuario = scan.nextInt() - 1;
		scan.nextLine();

		if (usuario < 0 || usuario >= listaLeitores.size()) {
			System.out.println("Leitor inválido!");
			return;
		}
		if (listaLeitores.get(usuario).getLivrosEmprestados().isEmpty()) {
			System.out.println("Este leitor não possui livros emprestados!");
			return;
		}

		System.out.println("\nLivros emprestados por este leitor:");
		List<Livro> livrosEmprestados = listaLeitores.get(usuario).getLivrosEmprestados();

		for (int i = 0; i < livrosEmprestados.size(); i++) {
			System.out.printf("%d) %s\n", i + 1, livrosEmprestados.get(i).getTitulo());
		}

		System.out.print("Informe o número do livro que será devolvido: ");
		int livro = scan.nextInt() - 1;
		scan.nextLine();

		if (livro < 0 || livro >= livrosEmprestados.size()) {
			System.out.println("Número de livro inválido!");
			return;
		}

		livrosEmprestados.get(livro).setDataEmprestimo(null);
		livrosEmprestados.remove(livro);

		System.out.println("Livro devolvido com sucesso!");
	}

	private static void cadastrarLeitor() {
		Leitor leitor = new Leitor(null);
		System.out.print("Informe o nome do leitor: ");
		leitor.setNome(scan.nextLine());
		listaLeitores.add(leitor);
		System.out.println("Leitor cadastrado com sucesso!");
	}

	private static void listarLeitores() {
		System.out.println("\n-------- LISTA DE LEITORES --------");
		if (listaLeitores.isEmpty()) {
			System.out.println("Não existem leitores cadastrados!");
			return;
		}

		for (int i = 0; i < listaLeitores.size(); i++) {
			Leitor leitor = listaLeitores.get(i);
			System.out.printf("%d) %s\n", i + 1, leitor.getNome());
			if (!leitor.getLivrosEmprestados().isEmpty()) {
				System.out.println("   Livros emprestados:");
				for (int j = 0; j < leitor.getLivrosEmprestados().size(); j++) {
					Livro livro = leitor.getLivrosEmprestados().get(j);
					System.out.printf("   - %s\n", livro.getTitulo());
				}
			}
		}
		System.out.println();
	}
}
