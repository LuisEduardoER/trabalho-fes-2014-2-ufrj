package br.com.vocealuga.modelo.services;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Filial;
import br.com.vocealuga.util.Util;

public class FilialService extends BaseService{

	/**
	 * Valida e salva a Filial
	 * @param filial
	 * @throws Exception
	 */
	public static Filial salvarFilial(Filial filial) throws Exception{
		if(Util.preenchido(filial)){
			//Valida a filial conforme regras de negocio
			filial = validarFilial(filial);
			if(Util.preenchido(filial)){
				Filial filialExistente = new Buscador().selecionar(filial);
				if(Util.preenchido(filialExistente)){
					throw new Exception("Filial já existente.");
				}
				
				salvar(filial);
				return filial;
			}else{
				throw new Exception("Dados não preenchidos corretamente");
			}
		}else{
			throw new Exception("Objeto nulo");
		}
	}

	/**
	 * Valida a filial para ver se todos os campos estão preenchidos corretamente
	 * @param filial
	 * @return null se encontrar problema ou filial preenchida
	 */
	private static Filial validarFilial(Filial filial) {
		
		if(Util.vazio(filial.getNome())){
			return null;
		}else{
			return filial;
		}
	}
}
