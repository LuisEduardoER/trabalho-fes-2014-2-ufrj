package br.com.vocealuga.modelo.services;

import java.util.List;

import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Carro;
import br.com.vocealuga.modelo.entidade.Filial;
import br.com.vocealuga.modelo.entidade.Grupo;
import br.com.vocealuga.modelo.entidade.Modelo;
import br.com.vocealuga.util.Util;
import br.com.vocealuga.util.UtilData;

public class CarroService extends BaseService{

	/**
	 * Valida e salva a Carro
	 * @param carro
	 * @throws Exception
	 */
	public static Carro salvarCarro(Carro carro) throws Exception{
		if(Util.preenchido(carro)){
			//Valida a carro conforme regras de negocio
			carro = validarCarro(carro);
			if(Util.preenchido(carro)){
				carro.setDisponivel(true);
				carro.setEstaAdequado(true);
				carro.setUltimaRevisao(UtilData.agora());
				salvar(carro);
				return carro;
			}else{
				throw new Exception("Dados não preenchidos corretamente");
			}
		}else{
			throw new Exception("Objeto nulo");
		}
	}

	/**
	 * Valida a carro para ver se todos os campos estão preenchidos corretamente
	 * @param carro
	 * @return null se encontrar problema ou carro preenchida
	 */
	private static Carro validarCarro(Carro carro) {
		
		if(Util.vazio(carro.getAno()) || Util.vazio(carro.getQuilometragem())){
			return null;
		}
		if(Util.preenchido(carro.getModelo()) && Util.preenchido(carro.getModelo().getNome())){
			Modelo modelo = new Modelo();
			modelo.setNome(carro.getModelo().getNome());
			modelo = new Buscador().selecionar(modelo);
			
			if(Util.vazio(modelo)){
				return null;
			}else{
				carro.setModelo(modelo);
			}
		}else{
			return null;
		}

		if(Util.preenchido(carro.getFilial()) && Util.preenchido(carro.getFilial().getId())){
			Filial filial = new Filial();
			filial.setId(carro.getFilial().getId());
			filial = new Buscador().selecionar(filial);
			
			if(Util.vazio(filial)){
				return null;
			}else{
				carro.setFilial(filial);
			}
		}else{
			return null;
		}

		
		return carro;
		
	}

	/**
	 * Dado um modelo, seleciona algum carro disponivel.
	 * NECESSARIO REVER O METODO
	 * @param modelo
	 * @return
	 */
	public static Carro selecionarCarroDisponivel(Modelo modelo, Filial filial) {
		
		Carro selecionado = null;
		
		Carro carro = new Carro();
		carro.setFilial(filial);
		carro.setModelo(modelo);
		carro.setDisponivel(true);
		List<Carro> carros = Buscador.listar(carro);
		
		if(Util.preenchido(carros)){
			selecionado = carros.get(0);
		}
		
		if(Util.preenchido(selecionado)){
			selecionado.setDisponivel(false);
			salvar(selecionado);
		}
		return selecionado;
	}
	
	public static Carro selecionarCarroDisponivel(Grupo grupo, Filial filial) {
		
		if(Util.preenchido(grupo) && Util.preenchido(grupo.getNome())){
			
			//pega grupo escolhido
			Grupo filtro = new Grupo();
			filtro.setNome(grupo.getNome());
			grupo = new Buscador().selecionar(filtro);
			
		}else{
			//pega grupo que eh padrao
			grupo = GrupoService.selecionarGrupoPadrao();
			
		}
		
		if(Util.preenchido(grupo)){
			//carregando lista de modelos porque mapeamento eh lazy
			inicializa(grupo, grupo.getModelos());
			
			if(Util.preenchido(grupo.getModelos())){
				
				//busca no grupo e retorna um carro se tiver
				for(Modelo modelo : grupo.getModelos()){
					Carro carroSelecionado = selecionarCarroDisponivel(modelo, filial);
					if(Util.preenchido(carroSelecionado)){
						return carroSelecionado;
					}
				}
				
				//buscando grupo de prioridade superior
				Grupo filtro = new Grupo();
				filtro.setPrioridade(grupo.getPrioridade()+1);
				grupo = new Buscador().selecionar(filtro);
				
				//se encontrou grupo superior, busca nele
				if(Util.preenchido(grupo)){
					return selecionarCarroDisponivel(grupo, filial);
				}else{
					//se tudo falhar retorna null
					return null;
				}
				
			}
		}
		
		return null;
		
	}

	public static void devolverCarro(Carro carro) {
		
		if(Util.preenchido(carro) && Util.preenchido(carro.getId())){
			
			carro = new Buscador().selecionar(carro);
			if(Util.preenchido(carro)){
				
				carro.setDisponivel(true);
				salvar(carro);
			}
		}
		
	}

	public static boolean existeCarroDisponivel(Modelo modelo,
			Filial filial) {
		
		Carro selecionado = null;
		
		Carro carro = new Carro();
		carro.setFilial(filial);
		carro.setModelo(modelo);
		carro.setDisponivel(true);
		List<Carro> carros = Buscador.listar(carro);
		
		if(Util.preenchido(carros)){
			selecionado = carros.get(0);
		}
		
		if(Util.preenchido(selecionado)){
			return true;
		}else{
			return false;
		}
		
	}
	
}
