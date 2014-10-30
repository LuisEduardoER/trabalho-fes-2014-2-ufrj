package br.com.vocealuga.controller;

import br.com.vocealuga.modelo.entidade.Pagamento;
import br.com.vocealuga.modelo.entidade.Reserva;
import br.com.vocealuga.modelo.services.PagamentoService;


public class PagamentoController {
	
	public Pagamento efetuarPagamento(Pagamento pagamento){
		Pagamento pagamentoRetorno = PagamentoService.efetuarPagamento(pagamento);
		
		return pagamentoRetorno;
	}

	public static Pagamento selecionarValorPagamento(Reserva reserva) {

		Pagamento pagamento = PagamentoService.selecionarValorPagamento(reserva);
		
		return pagamento;
	}
	
}
