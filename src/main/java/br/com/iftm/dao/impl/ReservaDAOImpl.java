package br.com.iftm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.iftm.dao.ReservaDAO;
import br.com.iftm.entily.Reserva;

@Repository
public class ReservaDAOImpl implements ReservaDAO {

	@Autowired // chama o metodo construtor sem ter que declarar
	private SessionFactory sessionFactory; // chama função atraves da classe DAOConfig

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.iftm.dao.ReservaDAO#create(br.com.iftm.entily.Reserva)
	 */

	@Override
	public Reserva create(Reserva reserva) {

		// salva no banco
		sessionFactory.getCurrentSession().save(reserva);
		sessionFactory.getCurrentSession().flush();

		return reserva;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public List<Reserva> read() {

		// lista no banco
		return sessionFactory.getCurrentSession().createCriteria(Reserva.class).list();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Reserva readById(Integer codigo) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Reserva.class);

		criteria.add(Restrictions.eq("codigo", codigo));

		return (Reserva) criteria.uniqueResult();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Reserva update(Reserva reserva) {

		// atualiza no banco
		sessionFactory.getCurrentSession().update(reserva);
		sessionFactory.getCurrentSession().flush();

		return reserva;
	}

	@Override
	public void delete(Integer id) {

		// delete no banco
		Reserva reserva = new Reserva(); // Classe onde está o atributo
		reserva.setCodigo(id); // instancia para pegar o id e assim excluir
		sessionFactory.getCurrentSession().delete(reserva);
	}

}
