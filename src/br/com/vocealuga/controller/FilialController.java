package br.com.vocealuga.controller;

import java.util.List;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Filial;
import br.com.vocealuga.modelo.services.FilialService;
import br.com.vocealuga.util.Util;

public class FilialController {
	
	/**
	 * Método que efetivamente valida e cria a filial.
	 * Retorna a filial salva do banco.
	 * @param filial
	 */
	public boolean criarFilial(String nome){
		Filial filial = new Filial();
		filial.setNome(nome);
		if(Util.preenchido(filial)){
			Filial filialFinal;
			try {
				filialFinal = FilialService.salvarFilial(filial);
				if(Util.preenchido(filialFinal)){
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		
		//Filial nao preenchida. Retornando para o formulario de criacao
		return false;
		
	}
	
	/**
	 * Lista todas as filiais
	 * @return lista de filiais
	 */
	public static List<Filial> listarFiliais(){
		return Buscador.listar(Filial.class);
	}
}
