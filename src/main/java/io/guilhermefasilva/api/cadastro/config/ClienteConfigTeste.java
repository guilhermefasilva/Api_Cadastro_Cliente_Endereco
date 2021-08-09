package io.guilhermefasilva.api.cadastro.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import io.guilhermefasilva.api.cadastro.entity.Cliente;
import io.guilhermefasilva.api.cadastro.repository.ClienteRepository;

@Configuration
public class ClienteConfigTeste implements CommandLineRunner{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Cliente c1 = new Cliente("Maria", "12345678901", "maria@email.com");
		Cliente c2 = new Cliente("Joao", "12345678911", "joao@email.com");
		
		clienteRepository.saveAll(Arrays.asList(c1, c2));
		
		
		
	}

	

}
