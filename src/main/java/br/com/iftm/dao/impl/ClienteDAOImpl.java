package br.com.iftm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.iftm.dao.ClienteDAO;
import br.com.iftm.entily.Cliente;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

	@Autowired // chama o metodo construtor sem ter que declarar
	private SessionFactory sessionFactory; // chama função atraves da classe DAOConfig

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.iftm.dao.ClienteDAO#create(br.com.iftm.entily.Cliente)
	 */

	@Override
	public Cliente create(Cliente cliente) {

		// salva no banco
		sessionFactory.getCurrentSession().save(cliente);
		sessionFactory.getCurrentSession().flush();
		return cliente;
	}

	@Override
	public List<Cliente> read() {

		// lista no banco
		return sessionFactory.getCurrentSession().createCriteria(Cliente.class).list();
	}

	@Override
	public List<Cliente> readByName(String nome) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Cliente.class);
		criteria.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE).ignoreCase()); // adiciona restrição
		return criteria.list();
	}

	@Override
	public List<Cliente> readByCpf(String cpf) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Cliente.class);
		criteria.add(Restrictions.like("cpf", cpf, MatchMode.ANYWHERE).ignoreCase()); // adiciona restrição
		return criteria.list();
	}

	@Override
	public Cliente update(Cliente cliente) {

		// atualiza no banco
		sessionFactory.getCurrentSession().update(cliente);
		sessionFactory.getCurrentSession().flush();
		return cliente;
	}

	@Override
	public void delete(Integer id) {

		// deleta no banco
		Cliente excluiCliente = sessionFactory.getCurrentSession().get(Cliente.class, id);
		sessionFactory.getCurrentSession().delete(excluiCliente);
	}

}
