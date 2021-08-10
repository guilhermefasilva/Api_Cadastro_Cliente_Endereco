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

import io.guilhermefasilva.api.cadastro.dto.request.ClienteDtoRequest;
import io.guilhermefasilva.api.cadastro.dto.request.ClienteDtoRequestUpdate;
import io.guilhermefasilva.api.cadastro.dto.response.ClienteDtoResponse;
import io.guilhermefasilva.api.cadastro.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	
	@Autowired
	private ClienteService clienteService;
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDtoResponse> create(@RequestBody ClienteDtoRequest clienteDtoRequest){
		ClienteDtoResponse clienteResponse = clienteService.insert(clienteDtoRequest);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteResponse.getId()).toUri();
		return ResponseEntity.created(uri).body(clienteResponse);
	}



	@GetMapping
	public ResponseEntity<List<ClienteDtoResponse>> findAll() {
		List<ClienteDtoResponse> clientes =  clienteService.getAllClients();
		return ResponseEntity.ok().body(clientes);
		
	}
	
	


	@GetMapping("/{id}")
	public ResponseEntity<ClienteDtoResponse> findById(@PathVariable Long id){
		ClienteDtoResponse clienteResponse = clienteService.getClientById(id);
		return ResponseEntity.ok().body(clienteResponse);
	}
	
	
	

	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<ClienteDtoResponse> atualizar(@PathVariable Long id, @RequestBody ClienteDtoRequestUpdate clienteDtoRequestUpdate){
		ClienteDtoResponse clienteResponse = clienteService.update(id, clienteDtoRequestUpdate);
		return ResponseEntity.ok().body(clienteResponse);
	}
	

	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		this.clienteService.delete(id);
		return ResponseEntity.noContent().build();		
	}
	
}
