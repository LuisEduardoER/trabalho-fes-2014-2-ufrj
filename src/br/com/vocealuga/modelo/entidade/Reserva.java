package br.com.vocealuga.modelo.entidade;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Reserva implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne(optional=false)
	private Cliente cliente;
	
	@ManyToOne(optional=false)
	private Filial filial;

	@ManyToOne(optional=true)
	private Modelo modelo;
	
	@ManyToOne(optional=true)
	private Grupo grupo;
	
	@ManyToOne(optional=true)
	private Carro carro;
	
	@Column(nullable=false, columnDefinition="BOOLEAN DEFAULT 0")
	private Boolean efetivada;
	
	private GregorianCalendar dataLocacao;
	
	private GregorianCalendar dataDevolucao;

	private BigDecimal valor;
	
	private BigDecimal tarifas;

	private GregorianCalendar realDataDevolucao;
	
	@ManyToOne(optional=true)
	private Filial filialDevolucao;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="reserva")
	private List<Pagamento> pagamentos;
	
	@ManyToMany
	private List<Motorista> motoristas;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public GregorianCalendar getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(GregorianCalendar dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public GregorianCalendar getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(GregorianCalendar dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Boolean getEfetivada() {
		return efetivada;
	}

	public void setEfetivada(Boolean efetivada) {
		this.efetivada = efetivada;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTarifas() {
		return tarifas;
	}

	public void setTarifas(BigDecimal tarifas) {
		this.tarifas = tarifas;
	}

	public GregorianCalendar getRealDataDevolucao() {
		return realDataDevolucao;
	}

	public void setRealDataDevolucao(GregorianCalendar realDataDevolucao) {
		this.realDataDevolucao = realDataDevolucao;
	}

	public Filial getFilialDevolucao() {
		return filialDevolucao;
	}

	public void setFilialDevolucao(Filial filialDevolucao) {
		this.filialDevolucao = filialDevolucao;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public List<Motorista> getMotoristas() {
		return motoristas;
	}

	public void setMotoristas(List<Motorista> motoristas) {
		this.motoristas = motoristas;
	}

	
}
