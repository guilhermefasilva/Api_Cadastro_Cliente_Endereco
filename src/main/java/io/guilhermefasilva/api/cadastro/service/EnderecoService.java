package io.guilhermefasilva.api.cadastro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.guilhermefasilva.api.cadastro.dto.request.EnderecoDtoRequest;
import io.guilhermefasilva.api.cadastro.dto.response.EnderecoDtoResponse;
import io.guilhermefasilva.api.cadastro.entity.Endereco;
import io.guilhermefasilva.api.cadastro.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<EnderecoDtoResponse> findAll() {
		List<Endereco> endereco = enderecoRepository.findAll();
		return EnderecoDtoResponse.converter(endereco);
	}

	public EnderecoDtoResponse findById(Long id) {
		Optional<Endereco> endereco = enderecoRepository.findById(id);
		return new EnderecoDtoResponse(endereco.get());
	}

	public EnderecoDtoResponse insert(EnderecoDtoRequest enderecoDtoRequest) {
		Endereco endereco = enderecoRepository.save(enderecoDtoRequest.converter());
		return new EnderecoDtoResponse(endereco);
	}

	public EnderecoDtoResponse update(Long id, EnderecoDtoRequest enderecoDtoRequest) {
		Endereco endereco = enderecoRepository.getById(id);
		
		endereco.setLogradouro(enderecoDtoRequest.getLogradouro());
		endereco.setNumero(enderecoDtoRequest.getNumero());
		endereco.setBairro(enderecoDtoRequest.getBairro());
		endereco.setCidade(enderecoDtoRequest.getCidade());
		
		this.enderecoRepository.save(endereco);

		return new EnderecoDtoResponse(endereco);

	}

	public void delete(Long id) {
		this.enderecoRepository.deleteById(id);
	}

}
