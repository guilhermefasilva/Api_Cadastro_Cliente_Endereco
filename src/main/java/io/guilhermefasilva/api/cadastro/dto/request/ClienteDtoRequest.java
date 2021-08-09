package io.guilhermefasilva.api.cadastro.dto.request;

import io.guilhermefasilva.api.cadastro.entity.Cliente;

public class ClienteDtoRequest {

	private String nome;
	private String Cpf;
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cliente converter() {
		return new Cliente(nome, Cpf, email);
	}

}
