package br.com.vocealuga.modelo.services;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Cliente;
import br.com.vocealuga.util.Util;

public class ClienteService extends BaseService{

	/**
	 * Valida e salva a Cliente
	 * @param cliente
	 * @throws Exception
	 */
	public static Cliente salvarCliente(Cliente cliente) throws Exception{
		if(Util.preenchido(cliente)){
			//Valida a cliente conforme regras de negocio
			//cliente.setDeficiente(false);
			Cliente cliente2 = ClienteService.validarCliente(cliente);
			if(Util.preenchido(cliente2)){
				cliente = validarCliente(cliente);
				if(Util.preenchido(cliente)){
					//cliente.setDeficiente(false);
					salvar(cliente);
					return cliente;
				}else{
					throw new Exception("Dados não preenchidos corretamente");
				}
			}else{
				throw new Exception("Dados não preenchidos corretamente");
			}
		}else{
			throw new Exception("Objeto nulo");
		}
	}

	/**
	 * Valida a cliente para ver se todos os campos estão preenchidos corretamente
	 * @param cliente
	 * @return null se encontrar problema ou cliente preenchida
	 */
	public static Cliente validarCliente(Cliente cliente) {
		
		if( Util.vazio(cliente.getNome()) || Util.vazio(cliente.getCpf()) || Util.vazio(cliente.getCartao()) ){
			return null;
		}
		if( Util.preenchido(cliente.getCpf())){
			Cliente filtro = new Cliente();
			filtro.setCpf(cliente.getCpf());
			filtro = new Buscador().selecionar(filtro);
			
			if(Util.preenchido(filtro)){
				filtro.setNome(cliente.getNome());
				filtro.setCartao(cliente.getCartao());
				filtro.setDataNascimento(cliente.getDataNascimento());
				return filtro;
			}
		}
		
		return cliente;
		
	}
	
	public static Cliente selecionarCliente(String cpf){
		if(Util.preenchido(cpf)){
			Cliente filtro = new Cliente();
			filtro.setCpf(cpf);
			filtro = new Buscador().selecionar(filtro);
			return filtro;
		}else
			return null;
	}

}
