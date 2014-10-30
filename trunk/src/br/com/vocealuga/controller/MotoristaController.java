package br.com.vocealuga.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Motorista;
import br.com.vocealuga.modelo.services.MotoristaService;
import br.com.vocealuga.util.Util;

public class MotoristaController {
	
	/**
	 * Método que efetivamente valida e cria a motorista.
	 * Retorna a motorista salva do banco.
	 * @param motorista
	 */
	public boolean criarMotorista(String nome, String cnh, String validadeCnh, String data, String numApolice, boolean deficiente) {
		Motorista motorista = new Motorista();
		motorista.setNome(nome);
		motorista.setCnh(cnh);
		motorista.setNumeroApolice(numApolice);
		motorista.setDeficiente(deficiente);
		
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = df.parse(data);
			Calendar dataNascimento = new GregorianCalendar();
			dataNascimento.setTime(date);
			motorista.setDataNascimento( (GregorianCalendar) dataNascimento );
			
			date = df.parse(validadeCnh);
			Calendar validadeCNH = new GregorianCalendar();
			validadeCNH.setTime(date);
			motorista.setValidadeCNH( (GregorianCalendar) validadeCNH );
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(Util.preenchido(motorista)){
			Motorista motoristaFinal;
			try {
				motoristaFinal = MotoristaService.salvarMotorista(motorista);
				if(Util.preenchido(motoristaFinal)){
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		
		//Motorista nao preenchida. Retornando para o formulario de criacao
		return false;
	}
	
	public Motorista selecionarMotorista(String cnh){
		return MotoristaService.selecionarMotorista(cnh);
	}

	/**
	 * Lista todas as motoristas
	 * @return lista de motoristas
	 */
	public List<Motorista> listarMotoristas(){
		return Buscador.listar(new Motorista());
	}
	
}
