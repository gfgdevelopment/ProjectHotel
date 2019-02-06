package br.com.iftm.business;

import java.util.List;

import br.com.iftm.entily.Quarto;

public interface QuartoBusiness {

	// metodo de status

	Quarto create(Quarto quarto) throws BusinessExecption;

	/**
	 * 
	 */
	List<Quarto> read() throws BusinessExecption;

	/**
	 * 
	 */
	Quarto update(Quarto quarto) throws BusinessExecption;

	/**
	 * 
	 */
	void delete(Integer id) throws BusinessExecption;

}
