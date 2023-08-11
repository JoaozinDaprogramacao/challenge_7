package com.br.alura.challange7.domain.destinos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Long>{

	boolean existsByNome(String nome);

	Destino getReferenceByNome(String nome);

}
