package io.guilhermefasilva.api.cadastro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	
		
	public List<ClienteDtoResponse> getAllClients(){
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes.stream().map(c -> modelMapper.map(c, ClienteDtoResponse.class)).collect(Collectors.toList());
		
		}
	
	
	public ClienteDtoResponse getClientById(Long id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Cliente não encontrado"));
		return modelMapper.map(cliente, ClienteDtoResponse.class);
		
		
	}
	
	
	public ClienteDtoResponse insert( ClienteDtoRequest clienteDtoRequest) {
		Cliente cliente = modelMapper.map(clienteDtoRequest, Cliente.class);
		this.clienteRepository.save(cliente); 
		return modelMapper.map(cliente, ClienteDtoResponse.class);
		
	}
	
	public void delete(Long id){
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Cliente não encontrado"));
		clienteRepository.delete(cliente);		
	}
	
	
	public  ClienteDtoResponse update(Long id, ClienteDtoRequestUpdate clienteRequest) {
			Cliente cliente = clienteRepository.findById(id)
					.orElseThrow(()-> new RuntimeException("Cliente não encontrado"));
			cliente.setNome(clienteRequest.getNome());
			cliente.setEmail(clienteRequest.getEmail());
			this.clienteRepository.save(cliente);
			return modelMapper.map(cliente, ClienteDtoResponse.class);
		}
	
	
	

}
