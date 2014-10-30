package br.com.vocealuga.modelo.services;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Carro;
import br.com.vocealuga.modelo.entidade.Filial;
import br.com.vocealuga.modelo.entidade.Modelo;
import br.com.vocealuga.modelo.entidade.Reserva;
import br.com.vocealuga.util.Util;
import br.com.vocealuga.util.UtilData;

public class LocacaoService extends BaseService{

	public static boolean devolver(Reserva reserva) throws Exception{
		if(Util.preenchido(reserva) && Util.preenchido(reserva.getId()) && Util.preenchido(reserva.getFilialDevolucao())){
			
			Reserva reservaForm = new Reserva();
			reservaForm.setId(reserva.getId());
			reservaForm = new Buscador().selecionar(reservaForm);
			
			if(Util.preenchido(reservaForm)){
				//atualiza data de devolucao da reserva pra hoje
				reservaForm.setRealDataDevolucao(UtilData.agora());
				
				//procura filial de id correspondente ao entrado pelo funcionario
				Filial filialDev = new Buscador().selecionar(reserva.getFilialDevolucao());
				reservaForm.setFilialDevolucao(filialDev);
				
				inicializa(reserva, reserva.getMotoristas());
				ReservaService.salvar(reservaForm);
				
				ReservaService.calcularTarifa(reservaForm);
				
				CarroService.devolverCarro(reservaForm.getCarro());
				
				return Util.preenchido(reservaForm);
			}
		}
		
		return false;
		
	}
	
	/**
	 * Valida e salva a Reserva
	 * @param locacao
	 * @throws Exception
	 */
	public static Reserva salvarReserva(Reserva reserva) throws Exception{
		if(Util.preenchido(reserva)){
			//Valida a locacao conforme regras de negocio
			reserva = validarReserva(reserva);
			if(Util.preenchido(reserva)){
				reserva.setEfetivada(true);
				salvar(reserva);
				return reserva;
			}else{
				throw new Exception("Dados n„o preenchidos corretamente");
			}
		}else{
			throw new Exception("Objeto nulo");
		}
	}

	/**
	 * Valida a locacao para ver se todos os campos est√£o preenchidos corretamente
	 * @param locacao
	 * @return null se encontrar problema ou locacao preenchida
	 * @throws Exception 
	 */
	private static Reserva validarReserva(Reserva locacao) throws Exception {
		
		if(Util.preenchido(locacao) && Util.preenchido(locacao.getId())){
			Reserva reserva = new Reserva();
			reserva.setId(locacao.getId());
			reserva = new Buscador().selecionar(reserva);
			
			if(Util.vazio(reserva)){
				return null;
			}else{
				if(Util.vazio(reserva.getCarro()) || Util.vazio(reserva.getCarro().getId())){
					if(Util.preenchido(reserva.getModelo()) && Util.preenchido(reserva.getModelo().getNome())){
						Carro carro = new Carro();
						carro.setModelo(reserva.getModelo());
						reserva.setCarro(carro);
					}else{
						//ADICIONAR TRECHO QUE SELECIONA MODELO
					}
				}
			}
		
		
			if(Util.vazio(reserva.getCarro()) || !Util.preenchido(reserva.getCarro().getId())){
				if(Util.preenchido(Util.preenchido(reserva.getModelo()) && Util.preenchido(reserva.getModelo().getNome()))){
					Modelo modelo = new Modelo();
					modelo.setNome(reserva.getCarro().getModelo().getNome());
					modelo = new Buscador().selecionar(modelo);
					
					if(Util.vazio(modelo)){
						return null;
					}else{
						reserva.setCarro(CarroService.selecionarCarroDisponivel(modelo, reserva.getFilial()));
						if(Util.vazio(reserva.getCarro())){
							return null;
						}
					}
				}else{
					return null;
				}
				
			}
			
			return reserva;
		}else{
			return null;
		}
	}
}
