package br.com.iftm.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.iftm.business.BusinessExecption;
import br.com.iftm.business.QuartoBusiness;
import br.com.iftm.dao.QuartoDAO;
import br.com.iftm.entily.Quarto;
import br.com.iftm.enums.Status;

@Service
@Transactional // serve para que ocorra a transação do programa com o banco
public class QuartoBusinessImpl implements QuartoBusiness {

	@Autowired // procura pela classe, evita de instanciar
	private QuartoDAO dao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED) // exige que abre a transação e propaga para outros métodos
	public Quarto create(Quarto quarto) throws BusinessExecption {

		// dado obrigatório, (objeto é Int)
		if (quarto.getNumero() == null) {
			throw new BusinessExecption("Numero Requerido!"); // excessão disparada pela camada Business
		}

		// validação se está preenchido ou não
		if (StringUtils.isEmpty(quarto.getAndar())) {

			throw new BusinessExecption("Andar Requerido!"); // excessão disparada pela camada Business
		}

		// status
		if (StringUtils.isEmpty(quarto.getStatus())) {
			throw new BusinessExecption("O status precisa ser Requerido!"); // excessão disparada pela camada Business
		}

		// TipoQuarto
		if (quarto.getTipoQuarto().getCodigo() == null) {
			throw new BusinessExecption("TipoQuarto Requerido!"); // possui chave estrangeira
		}

		return dao.create(quarto);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	@Transactional(readOnly = true) // exige que faça somente leitura
	public List<Quarto> read() throws BusinessExecption {
		// TODO Auto-generated method stub
		return dao.read();
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	@Transactional(readOnly = true) // exige que faça somente leitura
	public Quarto readById(Integer id) throws BusinessExecption {

		if (id == null) {

			throw new BusinessExecption("Código Requerido!");
		}

		return dao.readById(id);
	}

	@Override
	@Transactional(readOnly = true) // exige que faça somente leitura
	public List<Quarto> readByStatus(Status status) throws BusinessExecption {

		// validação
		if (status == null) {
			throw new BusinessExecption("Status Requerido!"); // excessão disparada pela camada Business
		}
		return dao.readByStatus(status); // trata a parte de persistência (via interface)
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	@Transactional(propagation = Propagation.REQUIRED) // exige que abre a transação e propaga para outros métodos
	public Quarto update(Quarto quarto) throws BusinessExecption {

		if (quarto.getCodQuarto() == null) {
			throw new BusinessExecption("Codigo Requerido!"); // excessão disparada pela camada Business
		}

		// dado obrigatório, (objeto é Int)
		if (quarto.getNumero() == null) {
			throw new BusinessExecption("Numero Requerido!"); // excessão disparada pela camada Business
		}

		// validação se está preenchido ou não
		if (StringUtils.isEmpty(quarto.getAndar())) {

			throw new BusinessExecption("Andar Requerido!"); // excessão disparada pela camada Business
		}

		// status
		if (StringUtils.isEmpty(quarto.getStatus())) {
			throw new BusinessExecption("O status precisa ser Requerido!"); // excessão disparada pela camada Business
		}

		// TipoQuarto
		if (quarto.getTipoQuarto().getCodigo() == null) {
			throw new BusinessExecption("TipoQuarto Requerido!"); // possui chave estrangeira
		}

		return dao.update(quarto);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	@Transactional(propagation = Propagation.REQUIRED) // exige que abre a transação e propaga para outros métodos
	public void delete(Integer id) throws BusinessExecption {

		if (id == null) {

			throw new BusinessExecption("Numero Requerido!"); // excessão disparada pela camada Business
		}
		dao.delete(id); // trata a parte de persistência (via interface)
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
