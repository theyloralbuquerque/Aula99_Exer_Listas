package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Funcionario;



public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Funcionario> list = new ArrayList<>(); // instancia��o da lista
													// List = lista
													// Funcionario = tipo da lista
													// list = nome da vari�vel lista tipo Funcionario
													// new ArrayList<>() = instancia��o da interface List, implementando a classe ArrayList<>(). 								
		
		System.out.print("Quantos funcion�rios deseja registrar? ");
		int n = sc.nextInt();
		
		for (int i=0; i<n; i++) {
			System.out.println();
			System.out.printf("Funcion�rio #%d: \n", i+1);
			System.out.print("ID: ");
			Integer id = sc.nextInt();
			while (hasId(list, id)) { // enquanto o hasId for verdadeiro, fa�a! Chama a fun��o hasId.
				System.out.println("Esse ID j� existe! Tente novamente: ");
				id = sc.nextInt();
			}
		
			System.out.print("Nome: ");
			sc.nextLine();  // consome a quebra de linha pendente anterior.
			String name = sc.nextLine();
			System.out.print("Sal�rio: ");
			Double salary = sc.nextDouble();
			
			Funcionario fun = new Funcionario(id, name, salary);  // instancia��o do objeto a cada repeti��o.
			
			list.add(fun); // insere "fun" � lista (adiciona os dados digitados � lista, passando esses valores para o construtor.	
		}
		
		
		// Parte 2 - Atualiza o sal�rio do funcion�rio.
		System.out.println();
		System.out.print("Digite o ID do funcion�rio que ter� o sal�rio aumentado: ");
		int id = sc.nextInt();
		
		// Integer pos = position(list, idSalary); // chama a fun��o position. Procura a posi��o do valor de idSalary dentro da lista.
		
		Funcionario fun = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null); // express�o Lambda.
		// classe      // stream � um tipo do java que aceita exress�es Lambda
		// .filter = filtra(na lista apenas os itens x iguais � "id) // .findFirst() = e pega o primeiro.
		//. orElse(null) = se n�o, retorna um valor null.
		// busca na lista a primeira ocorr�ncia de um funcion�rio que tenha um id igual ao digitado.
		if (fun == null) { // se o valor de fun for null, mostre.
			System.out.print("Esse ID n�o existe.");	
		} 
		else { 
			System.out.print("Digite e porcentagem que deseja aumentar do sal�rio: ");
			double porcentage = sc.nextDouble(); 
			fun.increaseSalary(porcentage); // chama o m�todo que aumenta o sal�rio.
		}
		
		
		// PARTE 3 - Exibe os ids, funcion�rios e seus sal�rios.
		System.out.println("Lista de Funcion�rios: ");
		System.out.println();
		for (Funcionario obj : list) { // aqui usou-se o for each. Para cada item da classe Funcionario(vari�vel fun) da Lista list, fa�a.
			System.out.println(obj);
		}
	
		
		
		sc.close();
	}
	
	// Fun��o que verifica se o id do funcion�rio � v�lido ou n�o para que possa ter o sal�rio aumentado.
	/*public static Integer position(List<Funcionario> list, int id) { // tem que ser static, para chamar uma fun��o dentro de uma mesma classe, saindo de uma fun��o main static, a fun��o auxiliar tamb�m tem que ser static.
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getId() == id) {
				return i;
			}
		}
		return null;
	}
	*/
	
	// Fun��o que verifica se o id do funcion�rio j� foi ou n�o digitado.
	public static boolean hasId(List<Funcionario> list, int id) { // retorna verdadeiro ou falso(boolean).
		Funcionario fun = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		return fun != null; // retorna um valor diferente de null, retornar� V ou F. Se for diferente de nulo � V, se for nulo � F.
	}
	
}
