package br.com.iftm.dao;

import java.util.List;

import br.com.iftm.entily.Quarto;

public interface QuartoDAO {

	/**
	 * Método responsável por persistir o objeto {@link Quarto} na base de dados.
	 * 
	 * @param quarto (Objeto a ser persistido)
	 * @return Objeto persistido
	 */
	Quarto create(Quarto quarto);

	/**
	 * Método responsavel por recuperar da base de dados todos os objetos
	 * {@link quarto}.
	 * 
	 * @return lista de {@link Quarto}
	 */
	List<Quarto> read();

	/**
	 * Método responsavel por persisitir (atualizar) na base de dados o objeto
	 * 
	 * @param quarto
	 * @return Objeto a ser persistido.
	 */
	Quarto update(Quarto quarto);

	/**
	 * Método responsavel por excluir da base de dados o objeto referente ao id
	 * informado.
	 * 
	 * @param id
	 */
	void delete(Integer id);

}
