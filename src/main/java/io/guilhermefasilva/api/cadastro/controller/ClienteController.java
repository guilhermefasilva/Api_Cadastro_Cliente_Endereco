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
	
//	@Autowired
//	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	

	
//	@PostMapping
//	@Transactional
//	public ResponseEntity<ClienteDtoResponse> create(@RequestBody ClienteDtoRequest clienteDtoRequest, UriComponentsBuilder uriBuilder) {
//		Cliente cliente = clienteDtoRequest.converter();
//		clienteRepository.save(cliente); 
//		
//		URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
//		return ResponseEntity.created(uri).body(new ClienteDtoResponse(cliente));
//		
//	}

	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDtoResponse> create(@RequestBody ClienteDtoRequest clienteDtoRequest){
		ClienteDtoResponse clienteResponse = clienteService.insert(clienteDtoRequest);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteResponse.getId()).toUri();
		return ResponseEntity.created(uri).body(clienteResponse);
	}

//	@GetMapping
//	public List<ClienteDtoResponse> getAllClients(){
//		List<Cliente> clientes = clienteRepository.findAll();
//		return ClienteDtoResponse.converter(clientes);
//	}

	@GetMapping
	public ResponseEntity<List<ClienteDtoResponse>> findAll() {
		List<ClienteDtoResponse> clientes =  clienteService.getAllClients();
		return ResponseEntity.ok().body(clientes);
		
	}
	
	
//	@GetMapping("/{id}")
//	public ClienteDtoResponse getClientById(@PathVariable Long id) {
//		Cliente cliente = clienteRepository.getById(id);
//		return new ClienteDtoResponse(cliente);
//	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDtoResponse> findById(@PathVariable Long id){
		ClienteDtoResponse clienteResponse = clienteService.getClientById(id);
		return ResponseEntity.ok().body(clienteResponse);
	}
	
	
	
//	@PutMapping("/{id}")
//	@Transactional
//	public  ResponseEntity<ClienteDtoResponse> updateClient(@PathVariable Long id, @RequestBody ClienteDtoRequestUpdate clienteDtoRequestUpdate) {
//		Cliente cliente = clienteDtoRequestUpdate.atualizar(id, clienteRepository);
//		
//		return ResponseEntity.ok(new ClienteDtoResponse(cliente));
//	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<ClienteDtoResponse> atualizar(@PathVariable Long id, @RequestBody ClienteDtoRequestUpdate clienteDtoRequestUpdate){
		ClienteDtoResponse clienteResponse = clienteService.update(id, clienteDtoRequestUpdate);
		return ResponseEntity.ok().body(clienteResponse);
	}
	
//	@DeleteMapping("{id}")
//	public ResponseEntity<?> deleteClient(@PathVariable Long id){
//		this.clienteRepository.deleteById(id);
//		return ResponseEntity.ok().build();
//	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		this.clienteService.delete(id);
		return ResponseEntity.noContent().build();		
	}
	
}
