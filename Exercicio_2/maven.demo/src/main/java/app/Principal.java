package app;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import dao.UsuarioDAO;
import model.Usuario;

public class Principal {
	public static void main(String[] args) throws Exception {
		UsuarioDAO usrDAO = new UsuarioDAO();
		Scanner scan = new Scanner(System.in);
		
		int opcao = 0;
		
		do {
			System.out.println("\n1- Inserir\n2- Listar\n3- Atualizar\n4- Excluir\n5- Sair\n\n -> ");
			opcao = Integer.parseInt(scan.nextLine());
			
			switch(opcao) {
				case 1:
					if (Inserir(usrDAO)) {
						System.out.println("Usuario foi inserido.");
					} else {
						System.out.println();
					}
					break;
				case 2:
					Listar(usrDAO);
					break;
				case 3:
					if (Atualizar(usrDAO)) {
						System.out.println("Usuario atualizado.");
					} else {
						System.out.println("Usuario nao foi atualizado");
					}
					break;
				case 4:
					if (Excluir(usrDAO)) {
						System.out.println("Usuario excluido");
					} else {
						System.out.println("Usuario nao foi excluido");
					}
					break;
				case 5:
					System.out.println("Saida feita com sucesso.");
					break;
			}
			
		} while(opcao != 5);
		scan.close();
	}
	
	public static boolean Inserir(UsuarioDAO usrDAO) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\nSexo: ");
		char sexo = scan.nextLine().toUpperCase().charAt(0);
		
		System.out.println("\nLogin: ");
		String login = scan.nextLine();
		
		System.out.println("\nSenha: ");
		String senha = scan.nextLine();
		
		Usuario usr = new Usuario(Math.abs(new Random().nextInt()), login, senha, sexo);
		
		boolean resposta = usrDAO.insert(usr);
		scan.close();
		return resposta;
	}
	
	public static void Listar(UsuarioDAO usrDAO) {
		List<Usuario> lista = usrDAO.get("codigo");
		for (int i = 0; i < lista.size(); i++) System.out.println(lista.get(i));
	}
	
	public static boolean Atualizar(UsuarioDAO usrDAO) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Codigo do usuario: ");
		int codigo = Integer.parseInt(scan.nextLine());
		
		System.out.println("\nSexo: ");
		char sexo = scan.nextLine().toUpperCase().charAt(0);
		
		System.out.println("\nLogin: ");
		String login = scan.nextLine();
		
		System.out.println("\nSenha: ");
		String senha = scan.nextLine();
		
		boolean resposta = usrDAO.update(new Usuario(codigo, login, senha, sexo));
		scan.close();
		return resposta;
		}
	
	public static boolean Excluir(UsuarioDAO usrDAO) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Codigo do usuario: ");
		int usr = Integer.parseInt(scan.nextLine());
		boolean resposta = usrDAO.delete(usr); 
		scan.close();
		return resposta;
	}
}
