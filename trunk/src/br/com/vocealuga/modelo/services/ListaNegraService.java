package br.com.vocealuga.modelo.services;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Cliente;
import br.com.vocealuga.modelo.entidade.ListaNegra;
import br.com.vocealuga.modelo.entidade.Motorista;
import br.com.vocealuga.util.Util;
import br.com.vocealuga.util.UtilData;

public class ListaNegraService extends BaseService{

	/**
	 * Valida e salva a ListaNegra
	 * @param listaNegra
	 * @throws Exception
	 */
	public static ListaNegra salvarListaNegra(ListaNegra listaNegra) throws Exception{
		if(Util.preenchido(listaNegra)){
			//Valida a listaNegra conforme regras de negocio
			listaNegra = validarListaNegra(listaNegra);
			if(Util.preenchido(listaNegra)){
				listaNegra.setDataInsercao(UtilData.agora());
				salvar(listaNegra);
				
				if(Util.preenchido(listaNegra)){
					Cliente cliente = listaNegra.getCliente();
					cliente.setListaNegra(listaNegra);
					salvar(cliente);
				}
				return listaNegra;
			}else{
				throw new Exception("Dados não preenchidos corretamente");
			}
		}else{
			throw new Exception("Objeto nulo");
		}
	}

	/**
	 * Valida a listaNegra para ver se todos os campos estão preenchidos corretamente
	 * @param listaNegra
	 * @return null se encontrar problema ou listaNegra preenchida
	 */
	private static ListaNegra validarListaNegra(ListaNegra listaNegra) {
		
		if(Util.vazio(listaNegra.getMotivo())){
			return null;
		}
		if(Util.preenchido(listaNegra.getCliente()) && (Util.preenchido(listaNegra.getCliente().getCpf()))){
			Cliente cliente = new Cliente();
			if(Util.preenchido(listaNegra.getCliente().getCpf())){
				cliente.setCpf(listaNegra.getCliente().getCpf());
			
			cliente = new Buscador().selecionar(cliente);
			
			if(Util.vazio(cliente)){
				return null;
			}else{
				listaNegra.setCliente(cliente);
			}
			}else{
				return null;
			}
		}
		return listaNegra;
	}

	/**
	 * Verifica se o motorista está na lista negra
	 * @param motorista
	 * @return true caso esteja. false caso motorista invalido ou caso nao esteja
	 */
	public static boolean isListaNegra(Cliente cliente) {
		if(Util.preenchido(cliente)){
			if(Util.preenchido(cliente.getId())){ 
				ListaNegra listaNegra = new ListaNegra();
				listaNegra.setCliente(cliente);
				
				if(Buscador.contarElementos(listaNegra) > 0){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
