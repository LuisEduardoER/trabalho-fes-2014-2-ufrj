package br.com.vocealuga.modelo.entidade;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Carro implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable=false)
	private GregorianCalendar ano;
	
	@Column(nullable=false)
	private GregorianCalendar ultimaRevisao;
	
	@Column(nullable=false)
	private Boolean estaAdequado;
	
	@Column(nullable=false)
	private Integer quilometragem;
	
	@Column(nullable=false)
	private Boolean disponivel;
	
	@ManyToOne(optional=false)
	private Filial filial;
	
	@ManyToOne(optional=false)
	private Modelo modelo;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="carro" , cascade=CascadeType.ALL)
	private List<Reserva> reservas;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GregorianCalendar getAno() {
		return ano;
	}

	public void setAno(GregorianCalendar ano) {
		this.ano = ano;
	}

	public GregorianCalendar getUltimaRevisao() {
		return ultimaRevisao;
	}

	public void setUltimaRevisao(GregorianCalendar ultimaRevisao) {
		this.ultimaRevisao = ultimaRevisao;
	}

	public Boolean getEstaAdequado() {
		return estaAdequado;
	}

	public void setEstaAdequado(Boolean estaAdequado) {
		this.estaAdequado = estaAdequado;
	}

	public Integer getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(Integer quilometragem) {
		this.quilometragem = quilometragem;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	}
