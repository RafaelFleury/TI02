import java.util.Scanner;

public class somaInteiros {
	
	public static void main(String[] args) {
		System.out.println("Digite um numero inteiro: ");
		Scanner leitor = new Scanner(System.in);
		int num1 = leitor.nextInt();
		
		leitor.close();
		System.out.println("Digite outro numero inteiro: ");
		leitor = new Scanner(System.in);
		int num2 = leitor.nextInt();
		leitor.close();
		
		int resposta = num1 + num2;
		
		System.out.println("A soma dos dois numeros eh: " + resposta);
		
	}
}
