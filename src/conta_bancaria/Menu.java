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

		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;

		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "jo�o Silva", 1000.00f, 100.00f);
		contas.cadastrar(cc1);

		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 123, 2, "Maria Silva", 1000.00f, 12);
		contas.cadastrar(cp1);

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
			System.out.println("Entre com a op��o desejada:                          ");
			System.out.println("*****************************************************");

			try {
				opcao = leia.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Digite valores inteiros: ");
				leia.nextLine();
				opcao = 0;
			}

			if (opcao == 10) {
				System.out.println("\nBank of Gen- O futuro � aqui!");
				sobre();
				leia.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println("Criar Conta\n\n");

				System.out.println("Digite o n�mero da Ag�ncia");
				agencia = leia.nextInt();

				System.out.println("Digite o nome do Titular: ");
				leia.skip("\\R");
				titular = leia.nextLine();

				System.out.println("Digite o tipo da Conta (1 - CC ou 2 - CP): ");
				tipo = leia.nextInt();

				System.out.println("Digite o saldo da conta: ");
				saldo = leia.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o limite da conta: ");
					limite = leia.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o aniversario da conta: ");
					aniversario = leia.nextInt();
					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
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
				System.out.println("Consultar dados da Conta - por n�mero\n\n");

				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();

				contas.procurarPorNumero(numero);
				keyPress();
				break;
			case 4:
				System.out.println("Atualizar dados da Conta\n\n");

				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();

				var buscaConta = contas.buscarNaCollection(numero);

				if (buscaConta != null) {
					tipo = buscaConta.getTipo();

					System.out.println("Digite o n�mero da ag�ncia: ");
					agencia = leia.nextInt();
					System.out.println("Digite o nome do titular: ");
					leia.skip("\\R");
					titular = leia.nextLine();

					System.out.println("Digite o Saldo da conta (R$): ");
					saldo = leia.nextFloat();

					switch (tipo) {
					case 1 -> {
						System.out.println("Digite o limite de Cr�dito (R$): ");
						limite = leia.nextFloat();

						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));

					}
					case 2 -> {
						System.out.println("Digite o dia do aniversario da conta: ");
						aniversario = leia.nextInt();

						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, aniversario));

					}
					default -> {
						System.out.println("Tipo de conta inv�lido!");
					}
					}
				} else {
					System.out.println("A conta n�o foi encontrada!");
				}
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

				System.out.println("Digite o N�mero da conta: ");
				numero = leia.nextInt();

				do {
					System.out.println("Digite o Valor do Saque (R$): ");
					valor = leia.nextFloat();
				} while (valor <= 0);

				contas.sacar(numero, valor);

				keyPress();
				break;
			case 7:
				System.out.println("Dep�sito\n\n");

				System.out.println("Digite o N�mero da conta: ");
				numero = leia.nextInt();

				do {
					System.out.println("Digite o Valor do Dep�sito (R$): ");
					valor = leia.nextFloat();
				} while (valor <= 0);

				contas.depositar(numero, valor);

				keyPress();
				break;
			case 8:
				System.out.println("Transfer�ncia entre Contas\n\n");

				System.out.println("Digite o Numero da Conta de Origem: ");
				numero = leia.nextInt();
				System.out.println("Digite o N�mero da Conta de Destino: ");
				numeroDestino = leia.nextInt();

				do {
					System.out.println("Digite o valor da Transfer�ncia (R$): ");
					valor = leia.nextFloat();
				} while (valor <= 0);

				keyPress();
				break;
			default:
				System.out.println("\nOp��o Inv�lida!\n");
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
		} catch (IOException e) {
			System.out.println("Voc� pressionou uma tecla inv�lida!");
		}
	}
}