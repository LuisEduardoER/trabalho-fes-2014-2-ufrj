package br.com.vocealuga.controller;

import java.util.List;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Cliente;
import br.com.vocealuga.modelo.entidade.ListaNegra;
import br.com.vocealuga.modelo.services.ListaNegraService;
import br.com.vocealuga.util.Util;

public class ListaNegraController {
	
	/**
	 * Método que efetivamente valida e cria a listaNegra.
	 * Retorna a listaNegra salva do banco.
	 * @param listaNegra
	 */
	public boolean criarListaNegra(String cpf, String motivo){
		
		ListaNegra listaNegra = new ListaNegra();
		listaNegra.setMotivo(motivo);
		
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		cliente = new Buscador().selecionar(cliente);
		if(Util.vazio(cliente)){
			return false;
		}else{
			listaNegra.setCliente(cliente);
		}
		
		if(Util.preenchido(listaNegra)){
			ListaNegra listaNegraFinal;
			
			try {
				listaNegraFinal = ListaNegraService.salvarListaNegra(listaNegra);
				if(Util.preenchido(listaNegraFinal)){
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		
		//ListaNegra nao preenchida. Retornando para o formulario de criacao
		return false;
		
	}
	
	/**
	 * Lista todas as listaNegras
	 * @return lista de listaNegras
	 */
	public List<ListaNegra> listarListaNegras(){
		return Buscador.listar(new ListaNegra());
	}
}
