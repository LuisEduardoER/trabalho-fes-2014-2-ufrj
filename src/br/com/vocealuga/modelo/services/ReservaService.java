package br.com.vocealuga.modelo.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Cliente;
import br.com.vocealuga.modelo.entidade.Filial;
import br.com.vocealuga.modelo.entidade.Grupo;
import br.com.vocealuga.modelo.entidade.Modelo;
import br.com.vocealuga.modelo.entidade.Motorista;
import br.com.vocealuga.modelo.entidade.Reserva;
import br.com.vocealuga.util.Util;
import br.com.vocealuga.util.UtilData;

public class ReservaService extends BaseService{

	public static final BigDecimal TARIFA_DEVOLUCAO_FILIAL_DIFERENTE = new BigDecimal(50);
	
	/**
	 * Valida e salva a Reserva
	 * @param reserva
	 * @throws Exception
	 */
	public static Reserva salvarReserva(Reserva reserva) throws Exception{
		if(Util.preenchido(reserva)){
			//Valida a reserva conforme regras de negocio
			reserva = validarReserva(reserva);
			if(Util.preenchido(reserva)){
				salvar(reserva);
				return reserva;
			}else{
				throw new Exception("Dados não preenchidos corretamente");
			}
		}else{
			throw new Exception("Objeto nulo");
		}
	}

	/**
	 * Valida a reserva para ver se todos os campos estão preenchidos corretamente
	 * @param reserva
	 * @return null se encontrar problema ou reserva preenchida
	 */
	private static Reserva validarReserva(Reserva reserva) {
		
		if(Util.vazio(reserva.getMotoristas())){
			return null;
		}else{
			List<Motorista> motoristas = new ArrayList<Motorista>();
			for(Motorista motorista : reserva.getMotoristas()){
				motorista = new Buscador().selecionar(motorista);
				if(Util.preenchido(motorista)){
					motoristas.add(motorista);
				}
			}
			if(Util.preenchido(motoristas)){
				reserva.setMotoristas(motoristas);
			}else{
				return null;
			}
		}
		
		
		if(Util.vazio(reserva.getDataDevolucao()) || Util.vazio(reserva.getDataLocacao())){
			return null;
		}
		if(Util.preenchido(reserva.getCliente()) && Util.preenchido(reserva.getCliente().getCpf())){
			Cliente cliente = new Cliente();
			cliente.setCpf(reserva.getCliente().getCpf());
			cliente = new Buscador().selecionar(cliente);
			
			if(Util.vazio(cliente)){
				return null;
			}else{
				//Verifica se est� na lista negra
				boolean isListaNegra = ListaNegraService.isListaNegra(cliente);
				
				if(isListaNegra){
					return null;
				}
				
				reserva.setCliente(cliente);
			}
		}else{
			return null;
		}
		
		if(Util.preenchido(reserva.getFilial()) && Util.preenchido(reserva.getFilial().getId())){
			Filial filial = new Filial();
			filial.setId(reserva.getFilial().getId());
			filial = new Buscador().selecionar(filial);
			
			if(Util.vazio(filial)){
				return null;
			}else{
				reserva.setFilial(filial);
			}
		}else{
			return null;
		}
		
		if(Util.preenchido(reserva.getModelo()) && Util.preenchido(reserva.getModelo().getNome())){
			Modelo modelo = new Modelo();
			modelo.setNome(reserva.getModelo().getNome());
			modelo = new Buscador().selecionar(modelo);
			
			if(Util.vazio(modelo)){
				return null;
			}else{
				reserva.setModelo(modelo);
			}
		}
		
		if(Util.preenchido(reserva.getGrupo()) && Util.preenchido(reserva.getGrupo().getNome())){
			Grupo grupo = new Grupo();
			grupo.setNome(reserva.getGrupo().getNome());
			grupo = new Buscador().selecionar(grupo);
			
			if(Util.vazio(grupo)){
				return null;
			}else{
				reserva.setGrupo(grupo);
			}
		}
		
		return reserva;
		
	}
	
	public static Reserva calcularTarifa(Reserva reserva) throws Exception {
		
		if(Util.preenchido(reserva) && Util.preenchido(reserva.getId())){
			
			reserva = new Buscador().selecionar(reserva);
			if(Util.preenchido(reserva)){
				inicializa(reserva, reserva.getMotoristas());
				BigDecimal tarifasTotais = BigDecimal.ZERO;
				Integer diferencaDias = UtilData.calculaDuracaoDiasArredondaPraCima(reserva.getRealDataDevolucao(), reserva.getDataLocacao());
				if(reserva.getFilialDevolucao().getId() != reserva.getFilial().getId()){
					tarifasTotais = tarifasTotais.add(TARIFA_DEVOLUCAO_FILIAL_DIFERENTE);
				}
				
				Grupo grupo = null;
				if(Util.preenchido(reserva.getGrupo())){
					grupo = reserva.getGrupo();
				}
				
				if(Util.preenchido(reserva.getModelo()) && Util.preenchido(reserva.getModelo().getGrupo())){
					grupo = reserva.getModelo().getGrupo();
				}
				
				if(Util.vazio(grupo)){
					grupo = GrupoService.selecionarGrupoPadrao();
				}
				
				if(Util.preenchido(grupo)){
					tarifasTotais = tarifasTotais.add(grupo.getValorDiaria().multiply(new BigDecimal(diferencaDias)).subtract(reserva.getValor()));
					
					reserva.setTarifas(tarifasTotais);
					salvarReserva(reserva);
					
					return reserva;
				}else{
					return null;
				}
			}
		}	
			
		return null;
	}
	
	public static Reserva calcularValorReserva(Reserva reserva){
		Integer diferencaDias = UtilData.diferencaDias(reserva.getDataLocacao(), reserva.getDataDevolucao());
		
		Grupo grupo = null;
		if(Util.preenchido(reserva.getGrupo())){
			if(Util.vazio(reserva.getGrupo().getId())){
				grupo = new Buscador().selecionar(reserva.getGrupo());
			}else{
				grupo = reserva.getGrupo();
			}
		}
		
		if(Util.preenchido(reserva.getModelo())){
			Modelo modelo = null;
			if(Util.vazio(reserva.getModelo().getId())){
				modelo = new Buscador().selecionar(reserva.getModelo());
			}else{
				modelo = reserva.getModelo();
			}
			if(Util.preenchido(modelo) && Util.preenchido(modelo.getGrupo())){
				grupo = modelo.getGrupo();
			}
		}
		
		if(Util.vazio(grupo)){
			grupo = GrupoService.selecionarGrupoPadrao();
		}
		
		if(Util.preenchido(grupo)){
			reserva.setValor(grupo.getValorDiaria().multiply(new BigDecimal(diferencaDias)));
			
			return reserva;
		}
		
		return null;
	}

}
