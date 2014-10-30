package br.com.vocealuga.modelo.services;

import java.math.BigDecimal;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Pagamento;
import br.com.vocealuga.modelo.entidade.Reserva;
import br.com.vocealuga.util.Util;
import br.com.vocealuga.util.UtilData;


public class PagamentoService extends BaseService {

	public static Pagamento efetuarPagamento(Pagamento pagamento) {
		
		Reserva reserva = pagamento.getReserva();
		reserva = new Buscador().selecionar(reserva);
		
		if(Util.preenchido(reserva)){
			pagamento.setReserva(reserva);
		}
		
		pagamento.setDataPagamento(UtilData.agora());
		pagamento = validarPagamento(pagamento);
		if(Util.preenchido(pagamento)){
			salvar(pagamento);
			return pagamento;
		}
		
		return null;
	}

	private static Pagamento validarPagamento(Pagamento pagamento) {
		if(Util.vazio(pagamento.getValor()))
			return null;
		if(Util.vazio(pagamento.getTipoPagamento()))
			return null;
		if(Util.vazio(pagamento.getReserva()))
			return null;
		
		return pagamento;
	}

	public static Pagamento selecionarValorPagamento(Reserva reserva) {
		if(Util.preenchido(reserva) && Util.preenchido(reserva.getId())){
			reserva = new Buscador().selecionar(reserva);
			
			if(Util.preenchido(reserva)){
				BigDecimal valor = BigDecimal.ZERO;
				
				if(Util.preenchido(reserva.getValor())){
					valor = valor.add(reserva.getValor());
				}
				
				if(Util.preenchido(reserva.getTarifas())){
					valor = valor.add(reserva.getTarifas());
				}
				
				inicializa(reserva, reserva.getPagamentos());
				
				if(Util.preenchido(reserva.getPagamentos())){
					for(Pagamento pagamento : reserva.getPagamentos()){
						valor = valor.subtract(pagamento.getValor());
					}
				}
				
				Pagamento pagamento = new Pagamento();
				pagamento.setReserva(reserva);
				pagamento.setValor(valor);
				return pagamento;
			}
		}
		
		return null;
	}
	
	
}
