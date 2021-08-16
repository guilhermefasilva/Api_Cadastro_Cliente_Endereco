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
	@ApiOperation(
			httpMethod = "POST",
			value = "Persistir um cliente no repositorio de dados",
			response = ClienteDtoResponse.class,
			nickname = "postCliente",
			notes = "Varios valores de status podem ser fornacidos com strings separadas por virgulas")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Requisição bem sucedida"),
			@ApiResponse(code = 201, message = "Recurso criado, requisição bem sucedida"),
			@ApiResponse(code = 400, message = "Erro de Validação, requisição não atendida")
			})
	public ResponseEntity<ClienteDtoResponse> create(@RequestBody @ApiParam("Dados para cadastrar um cliente") ClienteDtoRequest clienteDtoRequest){
		ClienteDtoResponse clienteResponse = clienteService.insert(clienteDtoRequest);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteResponse.getId()).toUri();
		return ResponseEntity.created(uri).body(clienteResponse);
	}


	@GetMapping
	@ApiOperation(
			httpMethod = "GET",
			value = "Listar todos os clientes",
			response = ClienteDtoResponse.class,
			nickname = "findAllCliente",
			notes = "Não há parametros a serem repassados, apenas a chamada da URI fará com que todos endereços persistidos"
					+ "\n no banco de dados, sejam retornados no corpo da requisição")
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
	@ApiOperation(
			httpMethod = "GET",
			value = "Pesquisar um cliente por Id especifico",
			response = ClienteDtoResponse.class,
			nickname = "findById",
			notes = "O parametro a ser repassado na URI deverá ser o Id corresposndente ao cliente que deseja consultar."
					+"\n A requisição trará no corpo da resposta os dados do cliente solicitado caso exista no repositorio de dados.")
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
	@ApiOperation(
			httpMethod = "PUT",
			value = "Atualizar os dados de um cliente passando o Id correspondente como parametro",
			response = ClienteDtoResponse.class,
			nickname = "updateCliente",
			notes = "Como parametro deve ser repassado o Id do cliente que deseja atualizar, o parametro é repassado na URI."
					+"\n No corpo da requisição deverá ser informado os valores para atualização dos campos desejados."
			)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Requisiçao bem sucedida"),
			@ApiResponse(code = 404, message = "Recurso não encontrado")
			})
	public ResponseEntity<ClienteDtoResponse> atualizar(@PathVariable Long id, @ApiParam("Id do cliente") @RequestBody ClienteDtoRequestUpdate clienteDtoRequestUpdate){
		ClienteDtoResponse clienteResponse = clienteService.update(id, clienteDtoRequestUpdate);
		return ResponseEntity.ok().body(clienteResponse);
	}
	
	@DeleteMapping("{id}")
	@ApiOperation(
			httpMethod = "DELETE",
			value = "Deletar um cliente passando o Id correspondente como parametro",
			nickname = "deletarCliente",
			notes = "Para deletar um cliente, deverá ser informado o Id do cliente como parametro na URI"
					+"\n Se o Id existir no banco de dados o cliente será excluido")
			
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
