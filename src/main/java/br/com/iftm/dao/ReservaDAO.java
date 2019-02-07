package br.com.iftm.dao;

import java.util.List;

import br.com.iftm.entily.Reserva;

public interface ReservaDAO {

	Reserva create(Reserva reserva);

	List<Reserva> read();

	Reserva readById(Integer codigo);

	Reserva update(Reserva reserva);

	void delete(Integer id);

}
