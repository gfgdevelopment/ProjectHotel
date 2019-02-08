package br.com.iftm.business;

import java.util.List;

import br.com.iftm.entily.Cliente;

public interface ClienteBusiness {

	Cliente create(Cliente cliente) throws BusinessExecption;

	List<Cliente> read() throws BusinessExecption;

	Cliente readById(Integer id) throws BusinessExecption;

	List<Cliente> readByName(String nome) throws BusinessExecption;

	List<Cliente> readByCpf(String cpf) throws BusinessExecption;

	Cliente update(Cliente cliente) throws BusinessExecption;

	void delete(Integer id) throws BusinessExecption;
}
