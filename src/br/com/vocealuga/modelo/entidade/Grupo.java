package br.com.vocealuga.modelo.entidade;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Grupo implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable=false)
	private String nome;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="grupo" , cascade=CascadeType.ALL)
	private List<Modelo> modelos;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="grupo" , cascade=CascadeType.PERSIST)
	private List<Reserva> reservas;

	@Column(nullable=false)
	private Boolean padrao;
	
	@Column(nullable=false, unique=true)
	private Integer prioridade;
	
	@Column(nullable=false)
	private BigDecimal valorDiaria;
	
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

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Boolean getPadrao() {
		return padrao;
	}

	public void setPadrao(Boolean padrao) {
		this.padrao = padrao;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public BigDecimal getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(BigDecimal valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

}
