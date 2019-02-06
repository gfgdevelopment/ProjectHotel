package br.com.iftm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.iftm.dao.TipoQuartoDAO;
import br.com.iftm.entily.TipoQuarto;

@Repository
public class TipoQuartoDAOImpl implements TipoQuartoDAO {

	@Autowired // chama o metodo construtor sem ter que declarar
	private SessionFactory sessionFactory; // chama função atraves da classe DAOConfig

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.iftm.dao.TipoQuartoDAO#create(br.com.iftm.entily.TipoQuarto)
	 */

	@Override
	public TipoQuarto create(TipoQuarto tipoQuarto) {

		// salva no banco
		sessionFactory.getCurrentSession().save(tipoQuarto);
		sessionFactory.getCurrentSession().flush();

		return tipoQuarto;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public List<TipoQuarto> read() {

		// lista no banco
		return sessionFactory.getCurrentSession().createCriteria(TipoQuarto.class).list();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public List<TipoQuarto> readByName(String descricao) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TipoQuarto.class);
		criteria.add(Restrictions.like("descricao", descricao, MatchMode.ANYWHERE).ignoreCase()); // adiciona restrição

		return criteria.list();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public TipoQuarto update(TipoQuarto tipoQuarto) {

		// atualiza no banco
		sessionFactory.getCurrentSession().update(tipoQuarto);
		sessionFactory.getCurrentSession().flush();

		return tipoQuarto;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void delete(Integer id) {

		// delete no banco
		TipoQuarto tipoQuarto = new TipoQuarto(); // Classe onde está o atributo
		tipoQuarto.setCodigo(id); // instancia para pegar o id e assim excluir

		sessionFactory.getCurrentSession().delete(tipoQuarto);
	}

}
