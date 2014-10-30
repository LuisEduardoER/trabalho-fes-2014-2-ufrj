package br.com.vocealuga.modelo.services;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Grupo;
import br.com.vocealuga.util.Util;

public class GrupoService extends BaseService{

	/**
	 * Valida e salva a Grupo
	 * @param grupo
	 * @throws Exception
	 */
	public static Grupo salvarGrupo(Grupo grupo) throws Exception{
		if(Util.preenchido(grupo)){
			//Valida a grupo conforme regras de negocio
			grupo = validarGrupo(grupo);
			if(Util.preenchido(grupo)){
				salvar(grupo);
				return grupo;
			}else{
				throw new Exception("Dados não preenchidos corretamente");
			}
		}else{
			throw new Exception("Objeto nulo");
		}
	}

	/**
	 * Valida a grupo para ver se todos os campos estão preenchidos corretamente
	 * @param grupo
	 * @return null se encontrar problema ou grupo preenchida
	 */
	private static Grupo validarGrupo(Grupo grupo) {
		
		if(Util.vazio(grupo.getNome())){
			return null;
		}
		
		if(Util.vazio(grupo.getPrioridade())){
			return null;
		}
		
		return grupo;
		
	}

	public static Grupo selecionarGrupoPadrao() {
		Grupo grupo = new Grupo();
		grupo.setPadrao(true);
		
		grupo = new Buscador().selecionar(grupo);
		
		return grupo;
	}
}
