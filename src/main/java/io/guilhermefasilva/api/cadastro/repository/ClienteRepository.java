package io.guilhermefasilva.api.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.guilhermefasilva.api.cadastro.entity.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>  {

}
