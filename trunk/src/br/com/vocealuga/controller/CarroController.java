package br.com.vocealuga.controller;

import java.util.List;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Carro;
import br.com.vocealuga.modelo.services.CarroService;
import br.com.vocealuga.util.Util;

public class CarroController {

	
	/**
	 * Método que efetivamente valida e cria a carro.
	 * Retorna a carro salva do banco.
	 * @param carro
	 */
	public Carro criarCarro(Carro carro){
		if(Util.preenchido(carro)){
			Carro carroFinal;
			try {
				carroFinal = CarroService.salvarCarro(carro);
				if(Util.preenchido(carroFinal)){
					return carroFinal;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}
		
		//Carro nao preenchida. Retornando para o formulario de criacao
		return null;
		
	}
	
	/**
	 * Lista todas as carros
	 * @return lista de carros
	 */
	public List<Carro> listarCarros(){
		return Buscador.listar(new Carro());
	}
}
