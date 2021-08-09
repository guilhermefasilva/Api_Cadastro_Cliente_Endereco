package io.guilhermefasilva.api.cadastro.dto.request;

public class ClienteDtoRequestUpdate {
	
	private String nome;
	private String email;
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
//	public Cliente atualizar(Long id, ClienteRepository clienteRepository) {
//		Cliente cliente = clienteRepository.getById(id);
//		
//		cliente.setNome(this.nome);
//		cliente.setEmail(this.email);
//		
//		return cliente;
//	}
	
	
	

}
