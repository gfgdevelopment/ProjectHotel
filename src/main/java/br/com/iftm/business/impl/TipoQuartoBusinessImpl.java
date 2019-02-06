package br.com.iftm.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.iftm.business.BusinessExecption;
import br.com.iftm.business.TipoQuartoBusiness;
import br.com.iftm.dao.TipoQuartoDAO;
import br.com.iftm.entily.TipoQuarto;

@Service
@Transactional // serve para que ocorra a transação do programa com o banco
public class TipoQuartoBusinessImpl implements TipoQuartoBusiness {

	@Autowired // procura pela classe, evita de instanciar
	private TipoQuartoDAO dao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED) // exige que abre a transação e propaga para outros métodos
	public TipoQuarto create(TipoQuarto tipoQuarto) throws BusinessExecption {

		// validação se está preenchido ou não
		if (StringUtils.isEmpty(tipoQuarto.getDescricao())) {

			throw new BusinessExecption("Descricao Requerida!"); // excessão disparada pela camada Business
		}

		// dado obrigatório, (objeto é Int)
		if (tipoQuarto.getValor() == null) {
			throw new BusinessExecption("Valor Requerido!"); // excessão disparada pela camada Business
		}

		return dao.create(tipoQuarto); // trata a parte de persistência (via interface)
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	@Transactional(readOnly = true) // exige que faça somente leitura
	public List<TipoQuarto> read() throws BusinessExecption {

		// chama a camada DAO (dados)
		return dao.read(); // trata a parte de persistência (via interface)
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	@Transactional(readOnly = true) // exige que faça somente leitura
	public List<TipoQuarto> readByName(String descricao) throws BusinessExecption {

		// validação
		if (StringUtils.isEmpty(descricao)) {
			throw new BusinessExecption("Descricao Requerida!"); // excessão disparada pela camada Business
		}
		return dao.readByName(descricao); // trata a parte de persistência (via interface)
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	@Transactional(propagation = Propagation.REQUIRED) // exige que abre a transação e propaga para outros métodos
	public TipoQuarto update(TipoQuarto tipoQuarto) throws BusinessExecption {

		if (tipoQuarto.getCodigo() == null) {
			throw new BusinessExecption("Descricao Requerida!"); // excessão disparada pela camada Business
		}
		// validação se está preenchido ou não
		if (StringUtils.isEmpty(tipoQuarto.getDescricao())) {

			throw new BusinessExecption("Nome Requerido!"); // excessão disparada pela camada Business
		}
		// dado obrigatório, (objeto é Int)
		if (tipoQuarto.getValor() == null) {
			throw new BusinessExecption("Valor Requerido!"); // excessão disparada pela camada Business
		}

		return dao.update(tipoQuarto); // trata a parte de persistência (via interface)
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	@Transactional(propagation = Propagation.REQUIRED) // exige que abre a transação e propaga para outros métodos
	public void delete(Integer id) throws BusinessExecption {

		if (id == null) {

			throw new BusinessExecption("Nome Requerido!"); // excessão disparada pela camada Business
		}
		dao.delete(id); // trata a parte de persistência (via interface)
	}

}
