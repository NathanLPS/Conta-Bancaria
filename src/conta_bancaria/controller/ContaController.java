package conta_bancaria.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import conta_bancaria.model.Conta;
import conta_bancaria.repository.ContaRepository;

public class ContaController implements ContaRepository {

	// Criar a Collection
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();

	// Vari�vel para receber o n�mero da Conta
	int numero = 0;

	@Override
	public void procurarPorNumero(int numero) {
		var conta = buscarNaCollection(numero);

		if (conta != null)
			conta.visualizar();
		else
			System.out.println("A conta n�mero: " + numero + " n�o foi encontrada!");
	}
	
	/*public void procurarPorNome(String titular) {
		
		List<Conta> listaNomes = listaContas.stream()
				.filter(c -> c.getTitular().contains(titular))
				.collect(Collectors.toList());
		
		System.out.println(listaNomes);
	}*/

	@Override
	public void listarTodas() {
		for (var conta : listaContas) {
			conta.visualizar();
		}

	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("A conta numero: " + conta.getNumero() + " foi criada com Sucesso!");

	}

	@Override
	public void atualizar(Conta conta) {
		var buscaConta = buscarNaCollection(conta.getNumero());
		
		if(buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("\nA Conta numero: " + numero + " foi atualizada");
		}else
			System.out.println("\nA Conta numero: " + numero + " n�o foi encontrada!");

	}

	@Override
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);

		if (conta != null)
			if (listaContas.remove(conta) == true)
				System.out.println("A conta: " + numero + " foi excluida com sucesso!");
			else
				System.out.println("A conta: " + numero + "n�o foi encontrada!");

	}

	@Override
	public void sacar(int numero, float valor) {

		var conta = buscarNaCollection(numero);

		if (conta != null) {
			if (conta.sacar(valor) == true)
				System.out.println("\nO Saque na Conta numero:" + numero + " foi efetuado com sucesso!");

		} else
			System.out.println("\nA Conta n�mero: " + numero + " n�o foi encontrada!");

	}

	@Override
	public void depositar(int numero, float valor) {

		var conta = buscarNaCollection(numero);

		if (conta != null) {
			conta.depositar(valor);
			System.out.println("\nO Dep�sito na Conta numero:" + numero + " foi efetuado com sucesso!");

		} else
			System.out.println("\nA Conta n�mero: " + numero + " n�o foi encontrada ou n�o � uma Conta Corrente!");

	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		
		var contaOrigem = buscarNaCollection(numeroOrigem);
		var contaDestino = buscarNaCollection(numeroDestino);
		
		if( contaOrigem != null && contaDestino != null) {
			
			if(contaOrigem.sacar(valor) == true) {
				contaDestino.depositar(valor);
				System.out.println("\nA Transfer�ncia foi efetuada com sucesso");
			}
			
		}else 
			System.out.println("\nA Conta Origem e/ou Desstino n�o foram encontradas!");

	}

	/* M�todos Auxiliares */
	public int gerarNumero() {
		return ++numero;
	}

	public Conta buscarNaCollection(int numero) {

		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta;
			}
		}
		return null;
	}

}
