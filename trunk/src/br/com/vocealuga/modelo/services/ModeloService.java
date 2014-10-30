package br.com.vocealuga.modelo.services;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Grupo;
import br.com.vocealuga.modelo.entidade.Modelo;
import br.com.vocealuga.util.Util;

public class ModeloService extends BaseService{

	/**
	 * Valida e salva a Modelo
	 * @param modelo
	 * @throws Exception
	 */
	public static Modelo salvarModelo(Modelo modelo) throws Exception{
		if(Util.preenchido(modelo)){
			//Valida a modelo conforme regras de negocio
			modelo = validarModelo(modelo);
			if(Util.preenchido(modelo)){
				salvar(modelo);
				return modelo;
			}else{
				throw new Exception("Dados não preenchidos corretamente");
			}
		}else{
			throw new Exception("Objeto nulo");
		}
	}

	/**
	 * Valida a modelo para ver se todos os campos estão preenchidos corretamente
	 * @param modelo
	 * @return null se encontrar problema ou modelo preenchida
	 */
	private static Modelo validarModelo(Modelo modelo) {
		
		if(Util.vazio(modelo.getNome())){
			return null;
		}
		if(Util.preenchido(modelo.getGrupo()) && Util.preenchido(modelo.getGrupo().getNome())){
			Grupo grupo = new Grupo();
			grupo.setNome(modelo.getGrupo().getNome());
			grupo = new Buscador().selecionar(grupo);
			
			if(Util.vazio(grupo)){
				return null;
			}else{
				modelo.setGrupo(grupo);
			}
		}else{
			return null;
		}
		
		return modelo;
		
	}
}
