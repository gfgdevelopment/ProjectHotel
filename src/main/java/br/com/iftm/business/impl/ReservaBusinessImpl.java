package br.com.iftm.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.iftm.business.BusinessExecption;
import br.com.iftm.business.ReservaBusiness;
import br.com.iftm.dao.ReservaDAO;
import br.com.iftm.entily.Pagamento;
import br.com.iftm.entily.Reserva;

@Service
@Transactional // serve para que ocorra a transação do programa com o banco
public class ReservaBusinessImpl implements ReservaBusiness {

	@Autowired // procura pela classe, evita de instanciar
	private ReservaDAO dao;

	@Override
	public Reserva create(Reserva reserva) throws BusinessExecption {
		// TODO Auto-generated method stub

		if (reserva.getDataEntrada() == null) {
			throw new BusinessExecption("DataDeEntrada Requerida!"); // excessão disparada pela camada Business
		}

		if (reserva.getDataSaida() == null) {
			throw new BusinessExecption("DataDeSaida Requerida!"); // excessão disparada pela camada Business
		}

		if (reserva.getCliente().getCodigo() == null) {
			throw new BusinessExecption("CodigoCliente Requerido!");
		}

		if (reserva.getQuarto().getCodQuarto() == null) {
			throw new BusinessExecption("CodigoQuarto Requerido!");
		}

		// dado obrigatório, (objeto se compara com NULL)
		// if (reserva.getPagamentos() == null || reserva.getPagamentos().isEmpty()) {
		// throw new BusinessExecption("Pelo menos um telefone Requerido!"); // possui
		// chave estrangeira

		// }

		for (Pagamento pagamento : reserva.getPagamentos()) {

			/*
			 * if (pagamento.getFormaDePagamento() == null) { throw new
			 * BusinessExecption("Forma de Pagamento Requerida!"); }
			 * 
			 * if (pagamento.getValor() == null) { throw new
			 * BusinessExecption("Valor Requerido!"); } comentado passa o valor como null
			 */

			pagamento.setReservas(reserva);

		}

		return dao.create(reserva);
	}

	@Override
	public List<Reserva> read() throws BusinessExecption {
		// TODO Auto-generated method stub
		return dao.read();
	}

	////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	@Transactional(readOnly = true)
	public Reserva readById(Integer codigo) throws BusinessExecption {

		if (codigo == null) {

			throw new BusinessExecption("Código Requerido!");
		}

		return dao.readById(codigo);
	}

	////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Reserva update(Reserva reserva) throws BusinessExecption {

		if (reserva.getCodigo() == null) {
			throw new BusinessExecption("Codigo Requerido!"); // excessão disparada pela camada Business
		}

		if (reserva.getDataEntrada() == null) {
			throw new BusinessExecption("DataDeEntrada Requerida!");
		}

		if (reserva.getCliente().getCodigo() == null) {
			throw new BusinessExecption("CodigoCliente Requerido!");
		}

		if (reserva.getQuarto().getCodQuarto() == null) {
			throw new BusinessExecption("CodigoQuarto Requerido!");
		}

		return dao.update(reserva);
	}

	@Override
	public void delete(Integer id) throws BusinessExecption {

		if (id == null) {
			throw new BusinessExecption("Codigo Requerido!");
		}

		dao.delete(id);

	}

}
