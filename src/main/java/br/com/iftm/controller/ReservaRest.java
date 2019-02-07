package br.com.iftm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iftm.business.BusinessExecption;
import br.com.iftm.business.ReservaBusiness;
import br.com.iftm.entily.Reserva;

@RestController // habilita Classe como um servico rest.
@RequestMapping(value = "/reservas") // Nome do Serviço.
public class ReservaRest {

	@Autowired // procura pela classe, evita de instanciar
	private ReservaBusiness reservaBusiness;

	// CREATE
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Reserva reserva) {

		try {
			reserva = reservaBusiness.create(reserva);

			return ResponseEntity.ok(reserva);
		} catch (BusinessExecption e) {

			e.printStackTrace();

			// mensagem de erro
			return ResponseEntity.badRequest().body(e);
		}

	}

	// READ
	@GetMapping
	public ResponseEntity<?> read() {

		try {
			List<Reserva> retornaLista = reservaBusiness.read();

			if (retornaLista.isEmpty()) {
				return ResponseEntity.notFound().build();
			} else {
				// devolve a lista
				return ResponseEntity.ok(retornaLista);
			}

		} catch (BusinessExecption e) {

			e.printStackTrace();
			return ResponseEntity.badRequest().body(e); // retorna um codigo de badRequest
		}
	}

	// UPDATE
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Reserva reserva) {

		try {
			reserva = reservaBusiness.update(reserva);

			// devolve o objeto criado
			return ResponseEntity.ok(reserva);
		} catch (BusinessExecption e) {

			e.printStackTrace();
			// mensagem de erro
			return ResponseEntity.badRequest().body(e);
		}

	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// DELETE
	@DeleteMapping(value = "/{codigo}") // deletando pelo código, no caso ID
	public ResponseEntity<?> delete(@PathVariable("codigo") Integer codigo) {

		try {
			reservaBusiness.delete(codigo);
			return ResponseEntity.ok().build();

		} catch (BusinessExecption e) {

			e.printStackTrace();
			return ResponseEntity.badRequest().body(e);
		}
	}

	// Alterado AQUI

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<?> readById(@PathVariable("codigo") Integer id) {

		try {
			Reserva readById = reservaBusiness.readById(id);

			if (readById == null) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok(readById);
		} catch (BusinessExecption e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e);
		}
	}

}
