package br.com.iftm.dao;

import java.util.List;

import br.com.iftm.entily.TipoQuarto;

public interface TipoQuartoDAO {

	/**
	 * Método responsável por persistir o objeto {@link TipoQuarto} na base de
	 * dados.
	 * 
	 * @param tipoQuarto (Objeto a ser persistido)
	 * @return Objeto persistido
	 */
	TipoQuarto create(TipoQuarto tipoQuarto);

	/**
	 * Método responsavel por recuperar da base de dados todos os objetos
	 * {@link tipoQuarto}.
	 * 
	 * @return lista de {@link TipoQuarto}
	 */
	List<TipoQuarto> read();

	/**
	 * Método para recuperar base de dados todos os objetos {@link TipoQuarto}, cujo
	 * seu nome possua parte do parametro Nome.
	 * 
	 * @param nome
	 * @return Lista de {@link TipoQuarto}
	 */
	List<TipoQuarto> readByName(String descricao);

	/**
	 * Método responsavel por persisitir (atualizar) na base de dados o objeto
	 * 
	 * @param tipoQuarto
	 * @return Objeto a ser persistido.
	 */
	TipoQuarto update(TipoQuarto tipoQuarto);

	/**
	 * Método responsavel por excluir da base de dados o objeto referente ao id
	 * informado.
	 * 
	 * @param id
	 */
	void delete(Integer id);

}
