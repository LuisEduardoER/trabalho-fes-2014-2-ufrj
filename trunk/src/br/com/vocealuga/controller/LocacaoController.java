package br.com.vocealuga.controller;

import java.util.List;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Reserva;
import br.com.vocealuga.modelo.services.LocacaoService;
import br.com.vocealuga.util.Util;

public class LocacaoController {

	/**
	 * Método que efetivamente valida e cria a locacao.
	 * Retorna a locacao salva do banco.
	 * @param locacao
	 */
	public boolean criarLocacao(Reserva reserva){
		
		if(Util.preenchido(reserva) && Util.preenchido(reserva.getId())){
			Reserva filtro = new Reserva();
			filtro.setId(reserva.getId());
			reserva = new Buscador().selecionar(filtro);
		}
		
		if(Util.preenchido(reserva)){
			Reserva reservaFinal;
			
			try {
				reservaFinal = LocacaoService.salvarReserva(reserva);
				if(Util.preenchido(reservaFinal)){
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		
		//Locacao nao preenchida. Retornando para o formulario de criacao
		return false;
	}
	
	/**
	 * Lista todas as locacaos
	 * @return lista de locacaos
	 */
	public List<Reserva> listarLocacaos(){
		return Buscador.listar(new Reserva());
	}
	
	public boolean devolver(Reserva reserva){
		try {
			return LocacaoService.devolver(reserva);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
}
