package br.com.testeacesse.api.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.com.testeacesse.api.web.domain.Processo;
import br.com.testeacesse.api.web.repository.processo.ProcessoRepositoryQuery;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long>, ProcessoRepositoryQuery {
	
}
