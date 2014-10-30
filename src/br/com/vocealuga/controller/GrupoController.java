package br.com.vocealuga.controller;

import java.util.List;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Grupo;
import br.com.vocealuga.modelo.services.GrupoService;
import br.com.vocealuga.util.Util;

public class GrupoController {
	
	/**
	 * Método que efetivamente valida e cria a grupo.
	 * Retorna a grupo salva do banco.
	 * @param grupo
	 */
	public boolean criarGrupo(Grupo grupo){
		
		if(Util.preenchido(grupo) && Util.preenchido(grupo.getNome())){
			Grupo grupoFinal;
			try {
				grupoFinal = GrupoService.salvarGrupo(grupo);
				if(Util.preenchido(grupoFinal)){
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		
		//Grupo nao preenchida. Retornando para o formulario de criacao
		return false;
		
	}
	
	/**
	 * Lista todas as grupos
	 * @return lista de grupos
	 */
	public static List<Grupo> listarGrupos(){
		return Buscador.listar(new Grupo());
	}
}
