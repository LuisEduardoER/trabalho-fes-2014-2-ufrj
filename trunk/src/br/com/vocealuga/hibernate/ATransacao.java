package br.com.vocealuga.hibernate;

import org.hibernate.Session;

import br.com.vocealuga.modelo.entidade.Entidade;

/**
 * Classe abstrata que define uma transacao. Define um template a ser implementado por cada
 * transacao do sistema.
 * 
 * @author dalton
 *
 */
public abstract class ATransacao {
	
	/**
	 * Metodo que contem a logica principal da transacao.
	 * @param <ObjetoRetornado> valor retornado pela logica da tranasacao.
	 * @param sessaoAberta Uma conexao ja aberta com o banco e que sera fechada após a execução da logica
	 * principal.
	 * @return
	 */
	public abstract Object executaTransacao(Session sessaoAberta);
	
	/**
	 * Indica se a transacao corrente precisa ser finalizada com um COMMIT.
	 * @return
	 */
	public abstract Boolean precisaDeCommit();
	
	public void excluir(Entidade entidade, Session sessao){
        
        sessao.delete(entidade);
    }
	
	/**
     * Salva um objeto mas não cria histórico.
     */
    public void salvar(Entidade entidade, Session sessao) {

        sessao.saveOrUpdate(entidade);
    }
}
