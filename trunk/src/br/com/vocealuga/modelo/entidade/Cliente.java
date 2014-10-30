package br.com.vocealuga.modelo.entidade;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cliente implements Entidade {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique=false, nullable=false)
	private String nome;
	
	@Column(unique=true, nullable=false)
	private String cpf;
	
	@Column(unique=false, nullable=false)
	private GregorianCalendar dataNascimento;
	
	@Column(unique=false)
	private String cartao;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="cliente" , cascade=CascadeType.ALL)
	private List<Reserva> reservas;
	
	@OneToOne(optional=true)
	private ListaNegra listaNegra;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public GregorianCalendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(GregorianCalendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getCartao() {
		return cartao;
	}
	public void setCartao(String cartao) {
		this.cartao = cartao;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public ListaNegra getListaNegra() {
		return listaNegra;
	}
	public void setListaNegra(ListaNegra listaNegra) {
		this.listaNegra = listaNegra;
	}
	
}
