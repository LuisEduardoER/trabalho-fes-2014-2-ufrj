package br.com.vocealuga.modelo.auxiliar;

public enum TipoPagamento {
	
	DINHEIRO("Dinheiro"), CREDITO("Crédito"), DEBITO("Débito"), CHEQUE("Cheque");
	
	private final String nome;
	
	TipoPagamento(String nome){
		this.nome = nome;
	}
	
	public String toString(){
		return nome;
	}
}
