package br.com.iftm.business;

import java.util.List;

import br.com.iftm.entily.Quarto;
import br.com.iftm.enums.Status;

public interface QuartoBusiness {

	Quarto create(Quarto quarto) throws BusinessExecption;

	/**
	 * 
	 */
	List<Quarto> read() throws BusinessExecption;

	Quarto readById(Integer id) throws BusinessExecption;

	List<Quarto> readByStatus(Status status) throws BusinessExecption;;

	/**
	 * 
	 */
	Quarto update(Quarto quarto) throws BusinessExecption;

	/**
	 * 
	 */

	void delete(Integer id) throws BusinessExecption;

}
