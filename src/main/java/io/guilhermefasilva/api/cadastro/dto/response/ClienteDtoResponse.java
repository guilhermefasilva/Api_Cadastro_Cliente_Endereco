package io.guilhermefasilva.api.cadastro.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import io.guilhermefasilva.api.cadastro.entity.Cliente;

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
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public String getCpf() {
		return cpf;
	}

	
	public static List<ClienteDtoResponse> converter(List<Cliente> cliente){
		return cliente.stream().map(ClienteDtoResponse::new).collect(Collectors.toList());
	}


	
	
	
	
}
