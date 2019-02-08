package br.com.iftm.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.iftm.business.BusinessExecption;
import br.com.iftm.business.ClienteBusiness;
import br.com.iftm.dao.ClienteDAO;
import br.com.iftm.entily.Cliente;
import br.com.iftm.entily.Telefone;

@Service
@Transactional // serve para que ocorra a transação do programa com o banco
public class ClienteBusinessImpl implements ClienteBusiness {

	@Autowired // procura pela classe, evita de instanciar
	private ClienteDAO dao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED) // exige que abre a transação e propaga para outros métodos
	public Cliente create(Cliente cliente) throws BusinessExecption {

		// validação se está preenchido ou não, dado obrigatório (objeto é string)
		if (StringUtils.isEmpty(cliente.getNome())) {
			throw new BusinessExecption("Nome Requerido!"); // excessão disparada pela camada Business
		}

		// validação se está preenchido ou não, dado obrigatório (objeto é string)
		if (StringUtils.isEmpty(cliente.getCpf())) {
			throw new BusinessExecption("CPF Requerido!"); // excessão disparada pela camada Business
		}

		// validação se está preenchido ou não, dado obrigatório (objeto é string)
		if (StringUtils.isEmpty(cliente.getSexo())) {
			throw new BusinessExecption("Sexo Requerido!"); // excessão disparada pela camada Business
		}

		// dado obrigatório, (objeto se compara com NULL)
		if (cliente.getTelefones() == null || cliente.getTelefones().isEmpty()) {
			throw new BusinessExecption("Pelo menos um telefone Requerido!"); // possui chave estrangeira
		}

		for (Telefone telefone : cliente.getTelefones()) {

			if (telefone.getDdd() == null) {
				throw new BusinessExecption("DDD Requerido!");
			}

			if (telefone.getNumero() == null) {
				throw new BusinessExecption("Número Telefone Requerido!");
			}

			telefone.setClientes(cliente);

		}

		return dao.create(cliente); // trata a parte de persistência (via interface);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	@Transactional(readOnly = true) // exige que faça somente leitura
	public List<Cliente> read() throws BusinessExecption {

		return dao.read();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	@Transactional(readOnly = true) // exige que faça somente leitura
	public List<Cliente> readByName(String nome) throws BusinessExecption {

		// validação
		if (StringUtils.isEmpty(nome)) {
			throw new BusinessExecption("Nome Requerido!"); // excessão disparada pela camada Business
		}
		return dao.readByName(nome); // trata a parte de persistência (via interface)
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	@Transactional(readOnly = true) // exige que faça somente leitura
	public List<Cliente> readByCpf(String cpf) throws BusinessExecption {

		// validação
		if (StringUtils.isEmpty(cpf)) {
			throw new BusinessExecption("CPF Requerido!"); // excessão disparada pela camada Business
		}
		return dao.readByCpf(cpf); // trata a parte de persistência (via interface)
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	@Transactional(propagation = Propagation.REQUIRED) // exige que abre a transação e propaga para outros métodos
	public Cliente update(Cliente cliente) throws BusinessExecption {

		// validação se está preenchido ou não, dado obrigatório (objeto é string)

		if (cliente.getCodigo() == null) {
			throw new BusinessExecption("Codigo Requerido!"); // excessão disparada pela camada Business
		}

		if (StringUtils.isEmpty(cliente.getNome())) {
			throw new BusinessExecption("Nome Requerido!"); // excessão disparada pela camada Business
		}

		// validação se está preenchido ou não, dado obrigatório (objeto é string)
		if (StringUtils.isEmpty(cliente.getCpf())) {
			throw new BusinessExecption("CPF Requerido!"); // excessão disparada pela camada Business
		}

		// validação se está preenchido ou não, dado obrigatório (objeto é string)
		if (StringUtils.isEmpty(cliente.getSexo())) {
			throw new BusinessExecption("Sexo Requerido!"); // excessão disparada pela camada Business
		}

		// dado obrigatório, (objeto se compara com NULL)
		if (cliente.getTelefones() == null || cliente.getTelefones().isEmpty()) {
			throw new BusinessExecption("Pelo menos um telefone Requerido!"); // possui chave estrangeira
		}

		for (Telefone telefone : cliente.getTelefones()) {

			if (telefone.getDdd() == null) {
				throw new BusinessExecption("DDD Requerido!");
			}

			if (telefone.getNumero() == null) {
				throw new BusinessExecption("Número Telefone Requerido!");
			}

			telefone.setClientes(cliente);

		}

		return dao.update(cliente);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	@Transactional(propagation = Propagation.REQUIRED) // exige que abre a transação e propaga para outros métodos
	public void delete(Integer id) throws BusinessExecption {

		if (id == null) {
			throw new BusinessExecption("Codigo Requerido!"); // excessão disparada pela camada Business
		}
		dao.delete(id); // trata a parte de persistência (via interface)
	}

	@Override
	@Transactional(readOnly = true) // exige que faça somente leitura (PESQUISA POR ID)
	public Cliente readById(Integer id) throws BusinessExecption {

		if (id == null) {

			throw new BusinessExecption("Código Requerido!");
		}

		return dao.readById(id);
	}

}
