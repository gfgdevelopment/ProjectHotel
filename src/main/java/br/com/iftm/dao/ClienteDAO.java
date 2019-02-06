package br.com.iftm.dao;

import java.util.List;

import br.com.iftm.entily.Cliente;

public interface ClienteDAO {

	Cliente create(Cliente cliente);

	List<Cliente> read();

	List<Cliente> readByName(String nome);

	List<Cliente> readByCpf(String cpf);

	Cliente update(Cliente cliente);

	void delete(Integer id);

}
