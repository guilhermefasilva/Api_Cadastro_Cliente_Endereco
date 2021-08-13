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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	
	@Autowired
	private ClienteService clienteService;
	
	
	@PostMapping
	@Transactional
	@ApiOperation("Persistir um cliente no repositorio de dados")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Cliente Cadastrado"),
			@ApiResponse(code = 400, message = "Erro de Validação, requisição não atendida")
			})
	public ResponseEntity<ClienteDtoResponse> create(@RequestBody @ApiParam("Cliente para cadastro") ClienteDtoRequest clienteDtoRequest){
		ClienteDtoResponse clienteResponse = clienteService.insert(clienteDtoRequest);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteResponse.getId()).toUri();
		return ResponseEntity.created(uri).body(clienteResponse);
	}



	@GetMapping
	@ApiOperation("Listar todos os clientes")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Requisição bem sucedida"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 503, message = "Servidor não pode manipular a requisição") 
	})
	public ResponseEntity<List<ClienteDtoResponse>> findAll() {
		List<ClienteDtoResponse> clientes =  clienteService.getAllClients();
		return ResponseEntity.ok().body(clientes);
		
	}
	
	


	@GetMapping("/{id}")
	@ApiOperation("Pesquisar um cliente por Id especifico")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Requisição bem sucedida"),
			@ApiResponse(code = 404, message = "Recurso não encontrado")
			})
	public ResponseEntity<ClienteDtoResponse> findById(@PathVariable @ApiParam("Id do cliente") Long id){
		ClienteDtoResponse clienteResponse = clienteService.getClientById(id);
		return ResponseEntity.ok().body(clienteResponse);
	}
	
	
	

	
	@PutMapping("{id}")
	@Transactional
	@ApiOperation("Atualizar os dados de um cliente passando o Id correspondente como parametro")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Requisiçao bem sucedida"),
			@ApiResponse(code = 404, message = "Recurso não encontrado")
			})
	public ResponseEntity<ClienteDtoResponse> atualizar(@PathVariable Long id, @ApiParam("Id do cliente") @RequestBody ClienteDtoRequestUpdate clienteDtoRequestUpdate){
		ClienteDtoResponse clienteResponse = clienteService.update(id, clienteDtoRequestUpdate);
		return ResponseEntity.ok().body(clienteResponse);
	}
	

	
	
	@DeleteMapping("{id}")
	@ApiOperation("Deletar um cliente passando o Id correspondente como parametro")
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Não há conteudos a ser exibidos ou enviados, requisição bem sucedida"),
			@ApiResponse(code = 400, message = "Recurso não pode ser excluido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado")
			})
	public ResponseEntity<?> delete(@PathVariable @ApiParam("Id do cliente") Long id){
		this.clienteService.delete(id);
		return ResponseEntity.noContent().build();		
	}
	
}
