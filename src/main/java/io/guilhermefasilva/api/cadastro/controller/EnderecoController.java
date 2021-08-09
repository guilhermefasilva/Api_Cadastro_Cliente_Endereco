package io.guilhermefasilva.api.cadastro.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.guilhermefasilva.api.cadastro.dto.request.EnderecoDtoRequest;
import io.guilhermefasilva.api.cadastro.dto.response.EnderecoDtoResponse;
import io.guilhermefasilva.api.cadastro.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping
	public ResponseEntity<List<EnderecoDtoResponse>> findAll() {
		List<EnderecoDtoResponse> enderecoResponse = enderecoService.findAll();
		return ResponseEntity.ok().body(enderecoResponse);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EnderecoDtoResponse> findById(@PathVariable Long id) {
		EnderecoDtoResponse enderecoResponse = enderecoService.findById(id);
		return ResponseEntity.ok().body(enderecoResponse);

	}

	@PostMapping
	@Transactional
	public ResponseEntity<EnderecoDtoResponse> create(@RequestBody EnderecoDtoRequest enderecoRequest) {
		EnderecoDtoResponse enderecoResponse = enderecoService.insert(enderecoRequest);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(enderecoResponse.getId())
				.toUri();
		return ResponseEntity.created(uri).body(enderecoResponse);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EnderecoDtoResponse> update(@PathVariable Long id, @RequestBody EnderecoDtoRequest enderecoRequest) {
		EnderecoDtoResponse enderecoResponse = enderecoService.update(id, enderecoRequest);
		return ResponseEntity.ok().body(enderecoResponse);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		this.enderecoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
