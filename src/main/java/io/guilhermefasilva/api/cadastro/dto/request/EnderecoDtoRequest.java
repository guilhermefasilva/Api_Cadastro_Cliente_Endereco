package io.guilhermefasilva.api.cadastro.dto.request;

import lombok.Data;

@Data
public class EnderecoDtoRequest {
	
	private String logradouro;
	private int numero;
	private String bairro;
	private String cidade;
	
	
	
}
