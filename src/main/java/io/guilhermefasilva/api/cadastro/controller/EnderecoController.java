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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;
	
	
	@PostMapping
	@Transactional
	@ApiOperation(
			httpMethod = "POST",
			value = "Persistir endereço na base de dados",
			response = EnderecoDtoResponse.class,
			nickname = "postEndereco",
			notes = "Varios valores de status podem ser fornacidos com strings separadas por virgulas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Requisição bem sucedida"),
			@ApiResponse(code = 201, message = "Recurso Criado, requisição bem sucedida"),
			@ApiResponse(code = 400, message = "Erro de validação, requisição não atendida")
			})
	public ResponseEntity<EnderecoDtoResponse> create(@ApiParam(value = "Valores para cadastrar endereço", required = true) @RequestBody  EnderecoDtoRequest enderecoRequest) {
		EnderecoDtoResponse enderecoResponse = enderecoService.insert(enderecoRequest);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(enderecoResponse.getId())
				.toUri();
		return ResponseEntity.created(uri).body(enderecoResponse);
	}

	@GetMapping
	@ApiOperation(
			httpMethod = "GET",
			value = "Listar todos endereços",
			response = EnderecoDtoResponse.class,
			nickname = "findAll",
			notes = "Não há parametros a serem repassados, apenas a chamada da URI fará com que todos endereços persistidos"
					+ "\n no banco de dados, sejam retornados no corpo da requisição ")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Requisição bem sucedida"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 503, message = "Servidor não pode manipular a requisição")
	})
	public ResponseEntity<List<EnderecoDtoResponse>> findAll() {
		List<EnderecoDtoResponse> enderecoResponse = enderecoService.findAll();
		return ResponseEntity.ok().body(enderecoResponse);
	}

	@GetMapping("/{id}")
	@ApiOperation(
			httpMethod = "GET",	
			value ="Pesquisar endereços por Id",
			response = EnderecoDtoResponse.class,
			nickname = "getById",
			notes = "Um id pode ser passado como parametro na URI para que a busca por um endereço especifico seja efetuada."
					+ "\n A requisição trará o endereço no corpo da resposta caso este exista no banco de dados ")
	
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Requisição bem sucedida"),
			@ApiResponse(code = 404, message = "Recurso solicitado não encontrado")
	})
	public ResponseEntity<EnderecoDtoResponse> findById(@ApiParam(value = "Id do endereço", required = true)@PathVariable Long id) {
		EnderecoDtoResponse enderecoResponse = enderecoService.findById(id);
		return ResponseEntity.ok().body(enderecoResponse);

	}

	

	@PutMapping("/{id}")
	@Transactional
	@ApiOperation(
			httpMethod = "PUT",	
			value ="Atualizar endereços",
			response = EnderecoDtoResponse.class,
			nickname = "updateEndereco",
			notes = "Um id pode ser passado como parametro na URI referenciando o endereço a ser atualizado."
					+ "No corpo da requisição deverá ser informado os valores para atualização dos campos desejados. ")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Requisição bem sucedida"),
			@ApiResponse(code = 404, message = "Recurso solicitado não encontrado")
			
	})
	public ResponseEntity<EnderecoDtoResponse> update(@PathVariable Long id,@ApiParam("Id do endereco") @RequestBody EnderecoDtoRequest enderecoRequest) {
		EnderecoDtoResponse enderecoResponse = enderecoService.update(id, enderecoRequest);
		return ResponseEntity.ok().body(enderecoResponse);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(
			httpMethod = "DELETE",	
			value ="Deletar endereços",
			nickname = "deleteEndereco",
			notes = "Um id pode ser passado como parametro na URI referenciando o endereço a ser deletado.")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Não há conteudos a ser exibidos ou enviados, requisição bem sucedida"),
			@ApiResponse(code = 400, message = "Recurso não pode ser excluido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado")
			})
	public ResponseEntity<?> delete(@PathVariable @ApiParam("Id do endereco") Long id) {
		this.enderecoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
