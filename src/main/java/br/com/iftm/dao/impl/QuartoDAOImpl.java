package br.com.iftm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.iftm.dao.QuartoDAO;
import br.com.iftm.entily.Quarto;
import br.com.iftm.enums.Status;

@Repository
public class QuartoDAOImpl implements QuartoDAO {

	@Autowired // chama o metodo construtor sem ter que declarar
	private SessionFactory sessionFactory; // chama função atraves da classe DAOConfig

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.iftm.dao.QuartoDAO#create(br.com.iftm.entily.Quarto)
	 */

	@Override
	public Quarto create(Quarto quarto) {

		// salva no banco
		sessionFactory.getCurrentSession().save(quarto);
		sessionFactory.getCurrentSession().flush();

		return quarto;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public List<Quarto> read() {

		// lista no banco
		return sessionFactory.getCurrentSession().createCriteria(Quarto.class).list();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Quarto readById(Integer id) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Quarto.class);

		criteria.add(Restrictions.eq("id", id));

		return (Quarto) criteria.uniqueResult();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public List<Quarto> readByStatus(Status status) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Quarto.class);
		criteria.add(Restrictions.eq("status", status)); // busca por igualdade

		return criteria.list();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Quarto update(Quarto quarto) {

		// atualiza no banco
		sessionFactory.getCurrentSession().update(quarto);
		sessionFactory.getCurrentSession().flush();

		return quarto;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void delete(Integer id) {

		// delete no banco
		Quarto quarto = new Quarto(); // Classe onde está o atributo
		quarto.setCodQuarto(id); // instancia para pegar o id e assim excluir

		sessionFactory.getCurrentSession().delete(quarto);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////

}
