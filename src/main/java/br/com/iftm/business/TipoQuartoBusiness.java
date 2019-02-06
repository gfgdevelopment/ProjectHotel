package br.com.iftm.business;

import java.util.List;

import br.com.iftm.entily.TipoQuarto;

public interface TipoQuartoBusiness {

	TipoQuarto create(TipoQuarto tipoQuarto) throws BusinessExecption;

	/**
	 * 
	 */
	List<TipoQuarto> read() throws BusinessExecption;

	/**
	 * 
	 */
	List<TipoQuarto> readByName(String descricao) throws BusinessExecption;

	/**
	 * 
	 */
	TipoQuarto update(TipoQuarto tipoQuarto) throws BusinessExecption;

	/**
	 * 
	 */
	void delete(Integer id) throws BusinessExecption;
}
