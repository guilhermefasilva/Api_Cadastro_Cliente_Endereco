package io.guilhermefasilva.api.cadastro.dto.request;

import lombok.Data;


@Data
public class ClienteDtoRequest {

	private String nome;
	private String cpf;
	private String email;
}
