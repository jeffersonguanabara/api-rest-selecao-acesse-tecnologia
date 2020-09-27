package br.com.testeacesse.api.web.repository.processo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.testeacesse.api.web.domain.Processo;
import br.com.testeacesse.api.web.repository.filter.ProcessoFilter;
import br.com.testeacesse.api.web.repository.filter.SolicitanteFilter;

public interface ProcessoRepositoryQuery {

	public List<Processo> pesquisar(ProcessoFilter processoFilter);
	public Page<Processo> filtrar(ProcessoFilter processoFilter, SolicitanteFilter solicitanteFilter, 
			Pageable pageable);
}
