package br.com.vocealuga.modelo.services;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Carro;
import br.com.vocealuga.modelo.entidade.Grupo;
import br.com.vocealuga.modelo.entidade.Reserva;
import br.com.vocealuga.util.Util;
import br.com.vocealuga.util.UtilData;

public class AdministrativoService extends BaseService {

	public static List<Reserva> alocarReservas() throws Exception {
		
		Reserva reserva = new Reserva();
		GregorianCalendar amanha = UtilData.agora(); 
		amanha = UtilData.somaDias(1, amanha);
		
		amanha = UtilData.retornaDataSemHora(amanha);
		
		reserva.setEfetivada(false);
		
		//Restricoes restricoes = Restricao.gerar("dataLocacao", valorMinimo, valorMaximo, criterio)
		reserva.setDataLocacao(amanha);
		
		//busca reservas iguais a reserva com data amanha
		List<Reserva> reservas = Buscador.listar(reserva);
		
		if(Util.preenchido(reservas)){
			
			//Cria lista de reservas falhas (as que o gerente terá que pedir de outra filial)
			List<Reserva> reservasFalhas = new ArrayList<Reserva>();
			
			//Lista de reservas com carros para salvar após iteração
			List<Reserva> reservasSucedidas = new ArrayList<Reserva>();
			
			for(Reserva iteraReserva : reservas){
				Carro carro = null;
				
				if(Util.preenchido(iteraReserva.getModelo())){
					//Caso a reserva tenha modelo, pesquisa pelo modelo
					carro = CarroService.selecionarCarroDisponivel(iteraReserva.getModelo(), iteraReserva.getFilial());
				}else{
					//Caso não, pesquisa pelo grupo (mesmo que o grupo seja nulo)
					Grupo grupo = null;
					if(Util.preenchido(iteraReserva.getGrupo())){
						grupo = iteraReserva.getGrupo();
					}
					
					carro = CarroService.selecionarCarroDisponivel(grupo, iteraReserva.getFilial());
				}
				
				if(Util.preenchido(carro)){					
					iteraReserva.setCarro(carro);
					
					//Adiciona na lista de sucedidas
					reservasSucedidas.add(iteraReserva);
					
				}else{
					//Nao encontrou carro, adiciona na lista de reservas falhas
					reservasFalhas.add(iteraReserva);
				}
			}
			
			if(Util.preenchido(reservasSucedidas)){
				ReservaService.salvar(reservasSucedidas);
			}
			
			if(Util.preenchido(reservasFalhas)){
				return reservasFalhas;
			}
		}		
		
		
		return null;
	}

}
