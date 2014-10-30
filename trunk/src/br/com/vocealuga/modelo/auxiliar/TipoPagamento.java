package br.com.vocealuga.modelo.auxiliar;

public enum TipoPagamento {
	
	DINHEIRO("Dinheiro"), CREDITO("Cr�dito"), DEBITO("D�bito"), CHEQUE("Cheque");
	
	private final String nome;
	
	TipoPagamento(String nome){
		this.nome = nome;
	}
	
	public String toString(){
		return nome;
	}
}
