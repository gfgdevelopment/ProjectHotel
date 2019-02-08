package br.com.iftm.controller;

import java.util.List;

import javax.websocket.server.PathParam;

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
import br.com.iftm.business.QuartoBusiness;
import br.com.iftm.entily.Quarto;
import br.com.iftm.enums.Status;

@RestController // habilita Classe como um servico rest.
@RequestMapping(value = "/quarto") // Nome do Serviço.
public class QuartoRest {

	@Autowired // procura pela classe, evita de instanciar
	private QuartoBusiness business;

	// CREATE
	@PostMapping()
	public ResponseEntity<?> create(@RequestBody Quarto quarto) { // requestBody está vindo no corpo da
																	// requisição

		// pegando da camada de negócio
		try {
			quarto = business.create(quarto);
			// devolve o objeto criado
			return ResponseEntity.ok(quarto);
		} catch (BusinessExecption e) {
			e.printStackTrace();
			// mensagem de erro
			return ResponseEntity.badRequest().body(e);
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////

	// READ
	@GetMapping
	public ResponseEntity<?> read() {

		try {
			List<Quarto> retornaLista = business.read();

			if (retornaLista.isEmpty()) {
				return ResponseEntity.notFound().build();
			} else {
				// devolve a lista
				return ResponseEntity.ok(retornaLista);
			}

		} catch (BusinessExecption e) {
			// mensagem de erro
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e); // retorna um codigo de badRequest
		}
	}

	// READ BY ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> readById(@PathVariable("id") Integer id) {

		try {
			Quarto readByName = business.readById(id);

			if (readByName == null) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok(readByName);
		} catch (BusinessExecption e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e);
		}
	}

	// READ BY STATUS (buscar por status)
	@GetMapping("/filtro/status") // rota que será retornada algum dado
	public ResponseEntity<?> readByStatus(@PathParam("status") Status status) {

		try {
			List<Quarto> retorna = business.readByStatus(status);

			if (retorna == null || retorna.isEmpty()) {
				return ResponseEntity.notFound().build();
			} else {
				// devolve a lista
				return ResponseEntity.ok(retorna);
			}

		} catch (BusinessExecption e) {
			// mensagem de erro
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e); // retorna um codigo de badRequest
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////

	// UPDATE
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Quarto quarto) { // requestBody está vindo no corpo da
																	// requisição

		// pegando da camada de negócio
		try {
			quarto = business.update(quarto);

			// devolve o objeto criado
			return ResponseEntity.ok(quarto);
		} catch (BusinessExecption e) {
			e.printStackTrace();
			// mensagem de erro
			return ResponseEntity.badRequest().body(e);
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////

	// DELETE
	@DeleteMapping(value = "/{id}") // deletando pelo código, no caso ID
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {

		try {
			business.delete(id);
			return ResponseEntity.ok().build();

		} catch (BusinessExecption e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e);
		}
	}

}
