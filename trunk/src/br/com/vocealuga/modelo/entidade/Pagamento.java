package br.com.vocealuga.modelo.entidade;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.vocealuga.modelo.auxiliar.TipoPagamento;

@Entity
public class Pagamento implements Entidade {
	
	@Id
	@GeneratedValue
	private Integer Id;
	
	@ManyToOne(optional=false)
	private Reserva reserva;
	
	@Column(nullable=false)
	private GregorianCalendar dataPagamento;
	
	@Enumerated(EnumType.STRING) 
	private TipoPagamento tipoPagamento;
	
	@Column(nullable=false)
	private BigDecimal valor;
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public GregorianCalendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(GregorianCalendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	
}
