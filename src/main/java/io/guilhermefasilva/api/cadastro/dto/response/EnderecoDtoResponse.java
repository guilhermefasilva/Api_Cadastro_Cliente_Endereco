package io.guilhermefasilva.api.cadastro.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import io.guilhermefasilva.api.cadastro.entity.Endereco;
import lombok.Getter;

@Getter
public class EnderecoDtoResponse {

	private Long id;
	private String logradouro;
	private int numero;
	private String bairro;
	private String cidade;
	
	


	public EnderecoDtoResponse(Endereco endereco) {
		
		this.id = endereco.getId();
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.bairro = endereco.getBairro();
		this.cidade = endereco.getCidade();
	}

	

	public static List<EnderecoDtoResponse> converter(List<Endereco> endereco) {
			return endereco.stream().map(EnderecoDtoResponse::new).collect(Collectors.toList());
	}
	


}
