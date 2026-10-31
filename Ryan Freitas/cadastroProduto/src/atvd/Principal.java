package atvd;

import objetos.Produto;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Principal {

    static Scanner scan = new Scanner(System.in);

    static List<Produto> listaProdutos = new ArrayList<Produto>();

    public static void main(String[] args) {

        tabelaInputs();

        System.out.println("Sistema de Gerenciamento Iniciado!");

        mostrarMenu();
    }

    private static void tabelaInputs() {
        listaProdutos.add(new Produto(1, "Batata", 3.50));
        listaProdutos.add(new Produto(2, "Cenoura", 2.00));
        listaProdutos.add(new Produto(3, "Abobora", 4.00));
        listaProdutos.add(new Produto(4, "Abacaxi", 5.00));
    }

    public static void mostrarMenu() {

        int escolhaUsuario;

        do {
            System.out.println("\n------ SISTEMA DE GERENCIAMENTO ------");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar produto");
            System.out.println("5 - Excluir produto");
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
            cadastrarProduto();
            break;
        case 2:
            listarProdutos();
            break;
        case 3:
            pesquisarId();
            break;
        case 4:
            atualizarProduto();
            break;
        case 5:
            excluirProduto();
            break;
        default:
            System.out.println("Insira um valor válido!");
        }
    }

    private static void cadastrarProduto() {
        System.out.print("Informe o ID do produto(Apenas numeros): ");
        int id = scan.nextInt();
        scan.nextLine();

        if (buscarProdutoPorId(id) != null) {
            System.out.println("Este ID já está cadastrado!");
            return;
        }
        
        if (id < 0) {
            System.out.println("Erro: o id não pode ser negativo!");
            return;
        }

        System.out.print("Informe o nome do produto: ");
        String nome = scan.nextLine();

        System.out.print("Informe o preço do produto: ");
        double preco = scan.nextDouble();
        scan.nextLine();
        
        if (preco < 0) {
            System.out.println("Erro: o preço não pode ser negativo!");
            return;
        }

        Produto produto = new Produto(id, nome, preco);

        listaProdutos.add(produto);

        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void listarProdutos() {
        System.out.println("\n-------- LISTA DE PRODUTOS --------");
        if (listaProdutos.isEmpty()) {
            System.out.println("Não existem produtos cadastrados!");
            return;
        }
        for (int i = 0; i < listaProdutos.size(); i++) {
            Produto produto = listaProdutos.get(i);
            System.out.printf("%d - %s - R$ %.2f\n", produto.getId(), produto.getNome(), produto.getPreco());
        }
        System.out.println();
    }
    
    private static Produto buscarProdutoPorId(int id) {
        for (int i = 0; i < listaProdutos.size(); i++) {
            Produto produto = listaProdutos.get(i);
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    private static void pesquisarId() {
        System.out.print("Informe o ID do produto que deseja buscar: ");
        int id = scan.nextInt();
        scan.nextLine();

        Produto produto = buscarProdutoPorId(id);

        if (produto != null) {
            System.out.println("\nProduto encontrado!");
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.printf("Preço: R$ %.2f\n", produto.getPreco());

        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    private static void atualizarProduto() {
        System.out.print("Informe o ID do produto que deseja atualizar: ");
        int id = scan.nextInt();
        scan.nextLine();

        Produto produto = buscarProdutoPorId(id);

        if (produto != null) {
            System.out.print("Informe o novo nome: ");
            produto.setNome(scan.nextLine());

            System.out.print("Informe o novo preço: ");
            double preco = scan.nextDouble();
            scan.nextLine();

            if (preco < 0) {
                System.out.println("Erro: o preço não pode ser negativo!");
                return;
            }
            produto.setPreco(preco);

            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    private static void excluirProduto() {
        System.out.print("Informe o ID do produto que deseja excluir: ");
        int id = scan.nextInt();
        scan.nextLine();

        Produto produto = buscarProdutoPorId(id);

        if (produto != null) {
            listaProdutos.remove(produto);
            System.out.println("Produto excluído com sucesso!");
        } else {
            System.out.println("Produto não encontrado!");
        }
    }
}