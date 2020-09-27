package br.com.testeacesse.api.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.testeacesse.api.web.domain.Solicitante;

@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante, Long>{
	
}
