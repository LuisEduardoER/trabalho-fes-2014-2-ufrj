package br.com.vocealuga.modelo.entidade;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Motorista implements Entidade{
	
	
	@Id
    @GeneratedValue
    private Integer id;
     
	@Column(unique=false, nullable=false)
	private String nome;
	
	@Column(unique=false, nullable=false)
	private GregorianCalendar dataNascimento;
	
	@Column(unique=false, nullable=false)
	private String cnh;
	
	@Column(unique=false, nullable=false)
	private String numeroApolice;
	
	@Column(unique=false, nullable=false)
	private GregorianCalendar validadeCNH;
	
	@Column(unique=false, nullable=false)
	private Boolean deficiente;
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="motoristas")
	private List<Reserva> reservas;
	
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
	public GregorianCalendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(GregorianCalendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	public String getNumeroApolice() {
		return numeroApolice;
	}
	public void setNumeroApolice(String numeroApolice) {
		this.numeroApolice = numeroApolice;
	}
	public GregorianCalendar getValidadeCNH() {
		return validadeCNH;
	}
	public void setValidadeCNH(GregorianCalendar validadeCNH) {
		this.validadeCNH = validadeCNH;
	}
	public Boolean getDeficiente() {
		return deficiente;
	}
	public void setDeficiente(Boolean deficiente) {
		this.deficiente = deficiente;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
}
