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
import br.com.vocealuga.modelo.services.ClienteService;
import br.com.vocealuga.util.Util;

public class ClienteController {
	
	/**
	 * Método que efetivamente valida e cria a cliente.
	 * Retorna a cliente salva do banco.
	 * @param cliente
	 */
	public boolean criarCliente(String nome, String cpf, String cartao, String data) {
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setCartao(cartao);
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = df.parse(data);
			Calendar dataNascimento = new GregorianCalendar();
			dataNascimento.setTime(date);
			cliente.setDataNascimento( (GregorianCalendar) dataNascimento );
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(Util.preenchido(cliente)){
			Cliente clienteFinal;
			try {
				clienteFinal = ClienteService.salvarCliente(cliente);
				if(Util.preenchido(clienteFinal)){
					System.out.println("Criou cliente");
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		
		//Cliente nao preenchida. Retornando para o formulario de criacao
		return false;
	}
	
	public Cliente selecionarCliente(String cpf){
		return ClienteService.selecionarCliente(cpf);
	}
	
	/**
	 * Lista todas as clientes
	 * @return lista de clientes
	 */
	public List<Cliente> listarClientes(){
		return Buscador.listar(new Cliente());
	}

}
