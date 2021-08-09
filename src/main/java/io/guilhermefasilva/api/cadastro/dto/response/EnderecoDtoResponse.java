package io.guilhermefasilva.api.cadastro.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import io.guilhermefasilva.api.cadastro.entity.Endereco;

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

	public Long getId() {
		return id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public static List<EnderecoDtoResponse> converter(List<Endereco> endereco) {
			return endereco.stream().map(EnderecoDtoResponse::new).collect(Collectors.toList());
	}
	


}
