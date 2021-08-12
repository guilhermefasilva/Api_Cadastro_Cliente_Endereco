package io.guilhermefasilva.api.cadastro.dto.response;

import lombok.Data;

@Data
public class ClienteDtoResponse {
	
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	 
}
