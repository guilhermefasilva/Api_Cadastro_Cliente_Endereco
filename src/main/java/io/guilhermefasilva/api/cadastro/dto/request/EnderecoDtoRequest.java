package io.guilhermefasilva.api.cadastro.dto.request;

import io.guilhermefasilva.api.cadastro.entity.Endereco;

public class EnderecoDtoRequest {
	
	
	private String logradouro;
	private int numero;
	private String bairro;
	private String cidade;
	
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
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
	
	
	public Endereco converter() {
		return new Endereco(logradouro, numero, bairro, cidade );
	}
	
	

}
