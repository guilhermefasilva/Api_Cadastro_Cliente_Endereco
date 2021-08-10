package io.guilhermefasilva.api.cadastro.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import io.guilhermefasilva.api.cadastro.entity.Cliente;
import lombok.Getter;

@Getter
public class ClienteDtoResponse {
	
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	 
	
	
	public ClienteDtoResponse(Cliente cliente) {
		
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.cpf = cliente.getCpf();
	}
	
	
	
	public static List<ClienteDtoResponse> converter(List<Cliente> cliente){
		return cliente.stream().map(ClienteDtoResponse::new).collect(Collectors.toList());
	}


	
	
	
	
}
