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
import br.com.iftm.business.ClienteBusiness;
import br.com.iftm.entily.Cliente;

@RestController // habilita Classe como um servico rest.
@RequestMapping(value = "/clientes") // Nome do Serviço.
public class ClienteRest {

	@Autowired // procura pela classe, evita de instanciar
	private ClienteBusiness business;

	// CREATE
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {

		try {
			cliente = business.create(cliente);

			return ResponseEntity.ok(cliente);
		} catch (BusinessExecption e) {

			e.printStackTrace();

			// mensagem de erro
			return ResponseEntity.badRequest().body(e);
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////

	// READ
	@GetMapping
	public ResponseEntity<?> read() {

		try {
			List<Cliente> retornaLista = business.read();

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

	/////////////////////////////////////////////////////////////////////////////////////////

	// READ BY NAME
	@GetMapping("/filtro/nome") // rota que será retornada algum dado)
	public ResponseEntity<?> readByName(@PathParam("nome") String nome) {

		try {
			List<Cliente> retornaLista = business.readByName(nome);

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

	////////////////////////////////////////////////////////////////////////////////////////

	// READ BY CPF
	@GetMapping("/filtro/cpf") // rota que será retornada algum dado)
	public ResponseEntity<?> readByCpf(@PathParam("cpf") String cpf) {

		try {
			List<Cliente> retornaLista = business.readByCpf(cpf);

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

	///////////////////////////////////////////////////////////////////////////////////////

	// UPDATE
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Cliente cliente) {

		try {
			cliente = business.update(cliente);

			// devolve o objeto criado
			return ResponseEntity.ok(cliente);
		} catch (BusinessExecption e) {

			e.printStackTrace();
			// mensagem de erro
			return ResponseEntity.badRequest().body(e);
		}

	}

	//////////////////////////////////////////////////////////////////////////////////////////////

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
