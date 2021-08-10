package io.guilhermefasilva.api.cadastro.dto.request;

import io.guilhermefasilva.api.cadastro.entity.Cliente;
import lombok.Data;



@Data
public class ClienteDtoRequest {

	private String nome;
	private String Cpf;
	private String email;

	

	public Cliente converter() {
		return new Cliente(nome, Cpf, email);
	}

}
