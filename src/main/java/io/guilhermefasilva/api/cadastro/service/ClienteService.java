package io.guilhermefasilva.api.cadastro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.guilhermefasilva.api.cadastro.dto.request.ClienteDtoRequest;
import io.guilhermefasilva.api.cadastro.dto.request.ClienteDtoRequestUpdate;
import io.guilhermefasilva.api.cadastro.dto.response.ClienteDtoResponse;
import io.guilhermefasilva.api.cadastro.entity.Cliente;
import io.guilhermefasilva.api.cadastro.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public List<ClienteDtoResponse> getAllClients(){
		List<Cliente> clientes = clienteRepository.findAll();
		return ClienteDtoResponse.converter(clientes);
	}
	
	
	public ClienteDtoResponse getClientById(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return new ClienteDtoResponse(cliente.get());
	}
	
	
	public ClienteDtoResponse insert( ClienteDtoRequest clienteDtoRequest) {
		Cliente cliente = clienteDtoRequest.converter();
		this.clienteRepository.save(cliente); 
		return new ClienteDtoResponse(cliente);
		
	}
	
	public void delete(Long id){
		this.clienteRepository.deleteById(id);
	}
	
	
	public  ClienteDtoResponse update(Long id, ClienteDtoRequestUpdate clienteRequest) {
			Cliente cliente = clienteRepository.getById(id);
			
			cliente.setNome(clienteRequest.getNome());
			cliente.setEmail(clienteRequest.getEmail());
			
			this.clienteRepository.save(cliente);
			
			return new ClienteDtoResponse(cliente);
		}
	

}
