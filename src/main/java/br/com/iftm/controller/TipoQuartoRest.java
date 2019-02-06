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
import br.com.iftm.business.TipoQuartoBusiness;
import br.com.iftm.entily.TipoQuarto;

@RestController // habilita Classe como um servico rest.
@RequestMapping(value = "/tipoquarto") // Nome do Serviço.
public class TipoQuartoRest {

	@Autowired // procura pela classe, evita de instanciar
	private TipoQuartoBusiness business; // acessando a classe

	// CREATE
	@PostMapping()
	public ResponseEntity<?> create(@RequestBody TipoQuarto tipoQuarto) { // requestBody está vindo no corpo da
																			// requisição

		// pegando da camada de negócio
		try {
			tipoQuarto = business.create(tipoQuarto);
			// devolve o objeto criado
			return ResponseEntity.ok(tipoQuarto);
		} catch (BusinessExecption e) {
			e.printStackTrace();
			// mensagem de erro
			return ResponseEntity.badRequest().body(e);
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////

	// READ
	@GetMapping
	public ResponseEntity<?> read() {

		try {
			List<TipoQuarto> retornaLista = business.read();

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

	///////////////////////////////////////////////////////////////////////////////////////////////////////////

	// READ BY NAME (buscar por descricao do quarto)
	@GetMapping("/filtro/descricao") // rota que será retornada algum dado
	public ResponseEntity<?> readByName(@PathParam("descricao") String descricao) {

		try {
			List<TipoQuarto> retornaLista = business.readByName(descricao);

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

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	// UPDATE
	@PutMapping
	public ResponseEntity<?> update(@RequestBody TipoQuarto tipoQuarto) { // requestBody está vindo no corpo da
																			// requisição

		// pegando da camada de negócio
		try {
			tipoQuarto = business.update(tipoQuarto);

			// devolve o objeto criado
			return ResponseEntity.ok(tipoQuarto);
		} catch (BusinessExecption e) {
			e.printStackTrace();
			// mensagem de erro
			return ResponseEntity.badRequest().body(e);
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

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
