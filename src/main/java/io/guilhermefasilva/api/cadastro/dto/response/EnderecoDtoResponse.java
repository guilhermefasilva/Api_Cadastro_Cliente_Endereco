package io.guilhermefasilva.api.cadastro.dto.response;

import lombok.Data;

@Data
public class EnderecoDtoResponse {

	private Long id;
	private String logradouro;
	private int numero;
	private String bairro;
	private String cidade;

}
