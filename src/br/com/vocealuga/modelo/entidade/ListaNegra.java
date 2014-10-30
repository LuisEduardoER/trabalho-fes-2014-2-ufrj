package br.com.vocealuga.modelo.entidade;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class ListaNegra implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable=false)
	private String motivo;
	
	@Column(nullable=false)
	private GregorianCalendar dataInsercao;
	
	@OneToOne(mappedBy="listaNegra", optional=false)
	@PrimaryKeyJoinColumn
	private Cliente cliente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public GregorianCalendar getDataInsercao() {
		return dataInsercao;
	}

	public void setDataInsercao(GregorianCalendar dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
