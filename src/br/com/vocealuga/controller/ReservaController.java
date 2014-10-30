package br.com.vocealuga.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Cliente;
import br.com.vocealuga.modelo.entidade.Filial;
import br.com.vocealuga.modelo.entidade.Grupo;
import br.com.vocealuga.modelo.entidade.Modelo;
import br.com.vocealuga.modelo.entidade.Motorista;
import br.com.vocealuga.modelo.entidade.Reserva;
import br.com.vocealuga.modelo.services.ReservaService;
import br.com.vocealuga.util.Util;

public class ReservaController {

	
	/**
	 * Formulario de criacao da reserva
	 */
	public void criacaoReserva(){
		
	}
	
	/**
	 * Método que efetivamente valida e cria a reserva.
	 * Retorna a reserva salva do banco.
	 * @param motoristas 
	 * @param reserva
	 */
	public boolean criarReserva(String nomeFilial, String cpf, String DataLocacao, String DataDevolucao, String nomeModelo, String nomeGrupo, List<Motorista> motoristas) {
		Reserva reserva = new Reserva();
		
		Filial filial = new Filial();
		filial.setNome(nomeFilial);
		filial = new Buscador().selecionar(filial);
		reserva.setFilial(filial);
		
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		reserva.setCliente(cliente);
		
		
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = df.parse(DataLocacao);
			Calendar dataLocacao = new GregorianCalendar();
			dataLocacao.setTime(date);
			reserva.setDataLocacao( (GregorianCalendar) dataLocacao );
			
			date = df.parse(DataDevolucao);
			Calendar dataDevolucao = new GregorianCalendar();
			dataDevolucao.setTime(date);
			reserva.setDataDevolucao( (GregorianCalendar) dataDevolucao );
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(Util.preenchido(nomeModelo)){
			Modelo modelo = new Modelo();
			modelo.setNome(nomeModelo);
			reserva.setModelo(modelo);
		}
		
		reserva.setEfetivada(false);
		
		if(Util.preenchido(nomeGrupo)){
			Grupo grupo = new Grupo();
			grupo.setNome(nomeGrupo);
			
			reserva.setGrupo(grupo);
		}
		
		if(Util.preenchido(reserva)){
			reserva.setMotoristas(motoristas);
			reserva = ReservaService.calcularValorReserva(reserva);
			Reserva reservaFinal;
			try {
				reservaFinal = ReservaService.salvarReserva(reserva);
				if(Util.preenchido(reservaFinal)){
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		
		//Reserva nao preenchida. Retornando para o formulario de criacao
		return false;
	}
	
	/**
	 * Lista todas as reservas
	 * @return lista de reservas
	 */
	public List<Reserva> listarReservas(){
		return Buscador.listar(new Reserva());
	}
	

	public Reserva calcularTarifa(Reserva reserva){
		try {
			reserva = ReservaService.calcularTarifa(reserva);
			return reserva;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
