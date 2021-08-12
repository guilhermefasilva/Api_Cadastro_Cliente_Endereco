package io.guilhermefasilva.api.cadastro.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import io.guilhermefasilva.api.cadastro.entity.Cliente;
import io.guilhermefasilva.api.cadastro.entity.Endereco;
import io.guilhermefasilva.api.cadastro.repository.ClienteRepository;
import io.guilhermefasilva.api.cadastro.repository.EnderecoRepository;

@Configuration
public class ConfigTeste implements CommandLineRunner{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		Cliente c1 = new Cliente("Maria", "12345678901", "maria@email.com");
		Cliente c2 = new Cliente("Joao", "12345678911", "joao@email.com");
		
		clienteRepository.saveAll(Arrays.asList(c1, c2));
		
		
		Endereco e1 = new Endereco("Av Amazonas", 150, "Centro", "Fortaleza", c1);
		Endereco e2 = new Endereco("Rua Bom Sucesso", 320, "Nações", "Arcos", c2);
		Endereco e3 = new Endereco("Rua Almirante Tamandare", 587, "São José", "Divinópolis", c1);
		Endereco e4 = new Endereco("Av bandeirantes", 698, "São Januario", "São Paulo", c2);
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4));
		
		
		
		
		
		
		
		
		
	}

	

}
