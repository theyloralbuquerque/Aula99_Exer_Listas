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
		
		List<Funcionario> list = new ArrayList<>(); // instanciação da lista
													// List = lista
													// Funcionario = tipo da lista
													// list = nome da variável lista tipo Funcionario
													// new ArrayList<>() = instanciação da interface List, implementando a classe ArrayList<>(). 								
		
		System.out.print("Quantos funcionários deseja registrar? ");
		int n = sc.nextInt();
		
		for (int i=0; i<n; i++) {
			System.out.println();
			System.out.printf("Funcionário #%d: \n", i+1);
			System.out.print("ID: ");
			Integer id = sc.nextInt();
			while (hasId(list, id)) { // enquanto o hasId for verdadeiro, faça! Chama a função hasId.
				System.out.println("Esse ID já existe! Tente novamente: ");
				id = sc.nextInt();
			}
		
			System.out.print("Nome: ");
			sc.nextLine();  // consome a quebra de linha pendente anterior.
			String name = sc.nextLine();
			System.out.print("Salário: ");
			Double salary = sc.nextDouble();
			
			Funcionario fun = new Funcionario(id, name, salary);  // instanciação do objeto a cada repetição.
			
			list.add(fun); // insere "fun" à lista (adiciona os dados digitados à lista, passando esses valores para o construtor.	
		}
		
		
		// Parte 2 - Atualiza o salário do funcionário.
		System.out.println();
		System.out.print("Digite o ID do funcionário que terá o salário aumentado: ");
		int id = sc.nextInt();
		
		// Integer pos = position(list, idSalary); // chama a função position. Procura a posição do valor de idSalary dentro da lista.
		
		Funcionario fun = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null); // expressão Lambda.
		// classe      // stream é um tipo do java que aceita exressões Lambda
		// .filter = filtra(na lista apenas os itens x iguais à "id) // .findFirst() = e pega o primeiro.
		//. orElse(null) = se não, retorna um valor null.
		// busca na lista a primeira ocorrência de um funcionário que tenha um id igual ao digitado.
		if (fun == null) { // se o valor de fun for null, mostre.
			System.out.print("Esse ID não existe.");	
		} 
		else { 
			System.out.print("Digite e porcentagem que deseja aumentar do salário: ");
			double porcentage = sc.nextDouble(); 
			fun.increaseSalary(porcentage); // chama o método que aumenta o salário.
		}
		
		
		// PARTE 3 - Exibe os ids, funcionários e seus salários.
		System.out.println("Lista de Funcionários: ");
		System.out.println();
		for (Funcionario obj : list) { // aqui usou-se o for each. Para cada item da classe Funcionario(variável fun) da Lista list, faça.
			System.out.println(obj);
		}
	
		
		
		sc.close();
	}
	
	// Função que verifica se o id do funcionário é válido ou não para que possa ter o salário aumentado.
	/*public static Integer position(List<Funcionario> list, int id) { // tem que ser static, para chamar uma função dentro de uma mesma classe, saindo de uma função main static, a função auxiliar também tem que ser static.
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getId() == id) {
				return i;
			}
		}
		return null;
	}
	*/
	
	// Função que verifica se o id do funcionário já foi ou não digitado.
	public static boolean hasId(List<Funcionario> list, int id) { // retorna verdadeiro ou falso(boolean).
		Funcionario fun = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		return fun != null; // retorna um valor diferente de null, retornará V ou F. Se for diferente de nulo é V, se for nulo é F.
	}
	
}
