package br.com.iftm.business;

import java.util.List;

import br.com.iftm.entily.Reserva;

public interface ReservaBusiness {

	Reserva create(Reserva reserva) throws BusinessExecption;

	List<Reserva> read() throws BusinessExecption;

	Reserva readById(Integer codigo) throws BusinessExecption;

	Reserva update(Reserva reserva) throws BusinessExecption;

	void delete(Integer id) throws BusinessExecption;

}
