package br.com.vocealuga.modelo.services;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Cliente;
import br.com.vocealuga.modelo.entidade.Motorista;
import br.com.vocealuga.util.Util;

public class MotoristaService extends BaseService{

	/**
	 * Valida e salva a Motorista
	 * @param motorista
	 * @throws Exception
	 */
	public static Motorista salvarMotorista(Motorista motorista) throws Exception{
		if(Util.preenchido(motorista)){
			//Valida a motorista conforme regras de negocio
			motorista = validarMotorista(motorista);
			if(Util.preenchido(motorista)){
				salvar(motorista);
				return motorista;
			}else{
				throw new Exception("Dados não preenchidos corretamente");
			}
		}else{
			throw new Exception("Objeto nulo");
		}
	}

	/**
	 * Valida a motorista para ver se todos os campos estão preenchidos corretamente
	 * @param motorista
	 * @return null se encontrar problema ou motorista preenchida
	 */
	public static Motorista validarMotorista(Motorista motorista) {
		
		if(Util.vazio(motorista.getNome()) || Util.vazio(motorista.getCnh()) || Util.vazio(motorista.getNumeroApolice())  || Util.vazio(motorista.getDataNascimento()) 
				|| Util.vazio(motorista.getDeficiente()) || Util.vazio(motorista.getValidadeCNH())){
			return null;
		}
		if( Util.preenchido(motorista.getCnh())){
			Motorista filtro = new Motorista();
			filtro.setCnh(motorista.getCnh());
			filtro = new Buscador().selecionar(filtro);
			
			if(Util.preenchido(filtro)){
				filtro.setDataNascimento(motorista.getDataNascimento());
				filtro.setDeficiente(motorista.getDeficiente());
				filtro.setNome(motorista.getNome());
				filtro.setNumeroApolice(motorista.getNumeroApolice());
				filtro.setValidadeCNH(motorista.getValidadeCNH());
				return filtro;
			}
		}
		
		return motorista;
		
	}
	
	public static Motorista selecionarMotorista(String cnh){
		if(Util.preenchido(cnh)){
			Motorista filtro = new Motorista();
			filtro.setCnh(cnh);
			filtro = new Buscador().selecionar(filtro);
			return filtro;
		}else
			return null;
	}

}
