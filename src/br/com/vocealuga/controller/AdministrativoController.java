package br.com.vocealuga.controller;

import java.util.List;

import br.com.vocealuga.modelo.entidade.Reserva;
import br.com.vocealuga.modelo.services.AdministrativoService;

public class AdministrativoController {

	public List<Reserva> alocarReservas() throws Exception{
	
		List<Reserva> reservas = AdministrativoService.alocarReservas();
		
		return reservas;
		
	}
	
}
