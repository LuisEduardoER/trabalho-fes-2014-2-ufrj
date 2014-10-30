package br.com.vocealuga.modelo.entidade;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Filial implements Entidade {

	public List<Reserva> getReservasDevolucoes() {
		return reservasDevolucoes;
	}

	public void setReservasDevolucoes(List<Reserva> reservasDevolucoes) {
		this.reservasDevolucoes = reservasDevolucoes;
	}

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable=false)
	private String nome;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="filial" , cascade=CascadeType.ALL)
	private List<Reserva> reservas;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="filialDevolucao" , cascade=CascadeType.ALL)
	private List<Reserva> reservasDevolucoes;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="filial" , cascade=CascadeType.ALL)
	private List<Carro> carros;
	
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

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}	
}
