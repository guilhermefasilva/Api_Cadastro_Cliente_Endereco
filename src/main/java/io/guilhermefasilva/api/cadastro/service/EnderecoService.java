package io.guilhermefasilva.api.cadastro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.guilhermefasilva.api.cadastro.dto.request.EnderecoDtoRequest;
import io.guilhermefasilva.api.cadastro.dto.response.EnderecoDtoResponse;
import io.guilhermefasilva.api.cadastro.entity.Endereco;
import io.guilhermefasilva.api.cadastro.exception.ResourceNotFoundException;
import io.guilhermefasilva.api.cadastro.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	public List<EnderecoDtoResponse> findAll() {
		List<Endereco> endereco = enderecoRepository.findAll();
		return mapList(endereco, EnderecoDtoResponse.class);
	}

	public EnderecoDtoResponse findById(Long id) {
		Endereco endereco = enderecoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(id));
		return modelMapper.map(endereco, EnderecoDtoResponse.class);
	}

	public EnderecoDtoResponse insert(EnderecoDtoRequest enderecoDtoRequest) {
		Endereco endereco = modelMapper.map(enderecoDtoRequest, Endereco.class);
		this.enderecoRepository.save(endereco);
		return modelMapper.map(endereco, EnderecoDtoResponse.class);
	}

	public EnderecoDtoResponse update(Long id, EnderecoDtoRequest enderecoDtoRequest) {
		Endereco endereco = enderecoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(id));
		
		endereco.setLogradouro(enderecoDtoRequest.getLogradouro());
		endereco.setNumero(enderecoDtoRequest.getNumero());
		endereco.setBairro(enderecoDtoRequest.getBairro());
		endereco.setCidade(enderecoDtoRequest.getCidade());
		
		this.enderecoRepository.save(endereco);

		return modelMapper.map(endereco, EnderecoDtoResponse.class);

	}

	public void delete(Long id) {
		Endereco endereco = enderecoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(id));
		this.enderecoRepository.delete(endereco);
	}

	
	private <S,T> List<T> mapList (List<S> origem, Class<T> classeAlvo){
		return origem.stream()
				.map(e -> modelMapper.map(e, classeAlvo))
				.collect(Collectors.toList());
	}
	
	
	
	
}
