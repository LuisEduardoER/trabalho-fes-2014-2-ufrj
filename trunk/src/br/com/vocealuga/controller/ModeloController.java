package br.com.vocealuga.controller;

import java.util.List;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Grupo;
import br.com.vocealuga.modelo.entidade.Modelo;
import br.com.vocealuga.modelo.services.ModeloService;
import br.com.vocealuga.util.Util;

public class ModeloController {

	
	/**
	 * Método que efetivamente valida e cria a modelo.
	 * Retorna a modelo salva do banco.
	 * @param string 
	 * @param modelo
	 */
	public boolean criarModelo(String nome, String nomeGrupo){
		Modelo modelo = new Modelo();
		modelo.setNome(nome);
		
		Grupo grupo = new Grupo();
		grupo.setNome(nomeGrupo);
		grupo = new Buscador().selecionar(grupo);
		if(Util.vazio(grupo)){
			return false;
		}else{
			modelo.setGrupo(grupo);
		}
		
		if(Util.preenchido(modelo)){
			Modelo modeloFinal;
			try {
				modeloFinal = ModeloService.salvarModelo(modelo);
				if(Util.preenchido(modeloFinal)){
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		
		//Modelo nao preenchida. Retornando para o formulario de criacao
		return false;
		
	}
	
	/**
	 * Lista todas as modelos
	 * @return lista de modelos
	 */
	public static List<Modelo> listarModelos(){
		return Buscador.listar(new Modelo());
	}

}
