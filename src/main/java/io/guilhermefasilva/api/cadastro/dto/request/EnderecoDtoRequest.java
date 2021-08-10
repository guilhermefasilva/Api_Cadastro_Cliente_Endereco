package io.guilhermefasilva.api.cadastro.dto.request;

import io.guilhermefasilva.api.cadastro.entity.Endereco;
import lombok.Data;

@Data
public class EnderecoDtoRequest {
	
	private String logradouro;
	private int numero;
	private String bairro;
	private String cidade;
	private ClienteDtoRequest cliente;
	
	public Endereco converter() {
		return new Endereco(logradouro, numero, bairro, cidade, cliente.converter());
	}
}
