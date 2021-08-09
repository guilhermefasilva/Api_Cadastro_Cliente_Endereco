package io.guilhermefasilva.api.cadastro.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import io.guilhermefasilva.api.cadastro.entity.Endereco;
import io.guilhermefasilva.api.cadastro.repository.EnderecoRepository;

@Configuration
public class EnderecoConfigTeste implements CommandLineRunner {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
			Endereco e1 = new Endereco("Av Amazonas", 150, "Centro", "Fortaleza");
			Endereco e2 = new Endereco("Rua Bom Sucesso", 320, "Nações", "Arcos");
			Endereco e3 = new Endereco("Rua Almirante Tamandare", 587, "São José", "Divinópolis");
			
			
			enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
	}
	
	
}
