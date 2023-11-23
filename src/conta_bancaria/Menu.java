package conta_bancaria;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta_bancaria.controller.ContaController;
import conta_bancaria.model.ContaCorrente;
import conta_bancaria.model.ContaPoupanca;
import conta_bancaria.util.Cores;

public class Menu {
	public static void main(String[] args) {

		Scanner leia = new Scanner(System.in);
		
		ContaController contas = new ContaController();
		
		int opcao, numero, agencia, tipo, aniversario;
		String titular;
		float saldo, limite;
		
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "joão Silva", 1000.00f, 100.00f);
		contas.cadastrar(cc1);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 123, 2, "Maria Silva", 1000.00f, 12);
		contas.cadastrar(cp1);
		
		/*Criar Objetos da classe para testes*/
		/*Conta c1= new Conta(1, 123, 1, "Victoria Moraes", 100000.0f);
		c1.visualizar();
		System.out.println("Exibir Saldo: " + c1.getSaldo());
		c1.setSaldo(200000.00f);
		c1.visualizar();
		c1.sacar(210000.00f);
		c1.visualizar();
		c1.depositar(5000.00f);
		c1.visualizar();
		
		//Teste classe Conta Corrente
		ContaCorrente cc1 = new ContaCorrente(1, 123, 1, "José da Silva", 0.0f, 1000.0f);
		cc1.visualizar();
		cc1.sacar(12000.0f);
		cc1.visualizar();
		cc1.depositar(5000.0f);
		cc1.visualizar();
		
		//Teste classe Conta Poupança
		ContaPoupanca cp1 = new ContaPoupanca(2, 123, 2, "Maria dos Santos", 100000.0f, 15);
		cp1.visualizar();
        cp1.sacar(1000.0f);
		cp1.visualizar();
		cp1.depositar(5000.0f);
		cp1.visualizar();*/

		while (true) {
			
			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND);
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                   Bank of Gen                       ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("*****************************************************");

			try {
				opcao = leia.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Digite valores inteiros: ");
				leia.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out.println("\nBank of Gen- O futuro é aqui!");
				sobre();
                 leia.close();
				System.exit(0);
			}

			switch (opcao) {
				case 1:
					System.out.println("Criar Conta\n\n");
					
					System.out.println("Digite o número da Agência");
					agencia = leia.nextInt();
					
					System.out.println("Digite o nome do Titular: ");
					leia.skip("\\R");
					titular = leia.nextLine();
					
					System.out.println("Digite o tipo da Conta (1 - CC ou 2 - CP): ");
					tipo = leia.nextInt();
					
					System.out.println("Digite o saldo da conta: ");
					saldo = leia.nextFloat();
					
					switch(tipo) {
					case 1 -> {
						System.out.println("Digite o limite da conta: ");
						limite =leia.nextFloat();
						contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println("Digite o aniversario da conta: ");
						aniversario =leia.nextInt();
						contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
					}
				}
					keyPress();
					break;
				case 2:
					System.out.println("Listar todas as Contas\n\n");
						contas.listarTodas();
					keyPress();	
					break;
				case 3:
					System.out.println("Consultar dados da Conta - por número\n\n");
					
						System.out.println("Digite o numero da conta: ");
						numero = leia.nextInt();
						
						contas.procurarPorNumero(numero);
					keyPress();	
					break;
				case 4:
					System.out.println("Atualizar dados da Conta\n\n");
					keyPress();
					break;
				case 5:
					System.out.println("Apagar a Conta\n\n");
					
					System.out.println("Digite o numero da conta: ");
					numero = leia.nextInt();
					
					contas.deletar(numero);
					
					keyPress();
					break;
				case 6:
					System.out.println("Saque\n\n");
					keyPress();
					break;
				case 7:
					System.out.println("Depósito\n\n");
					keyPress();
					break;
				case 8:
					System.out.println("Transferência entre Contas\n\n");
					keyPress();
					break;
				default:
					System.out.println("\nOpção Inválida!\n");
					keyPress();
					break;
			}
		}
	}
    
	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: Nathan Lopes da Silva          ");
		System.out.println("Generation Brasil - generation@generation.org            ");
		System.out.println("https://github.com/NathanLPS/Conta-Bancaria.git          ");
		System.out.println("*********************************************************");
	}
	
	public static void keyPress() {
		
		try {
			
			System.out.println("\n\nPressione a tecla Enter para continuar...");
			System.in.read();
		}catch(IOException e) {
			System.out.println("Você pressionou uma tecla inválida!");
		}
	}
}