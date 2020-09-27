package br.com.testeacesse.api.web.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.testeacesse.api.web.domain.Processo;
import br.com.testeacesse.api.web.repository.ProcessoRepository;
import br.com.testeacesse.api.web.repository.filter.ProcessoFilter;
import br.com.testeacesse.api.web.repository.filter.SolicitanteFilter;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository processoRepository;
	
	public Processo salvarProcesso(Processo processo) {
		ProcessoFilter processoFilter = new ProcessoFilter();
		processoFilter.setNumero_processo(Integer.toString(LocalDate.now().getYear()));
				
		List<Processo> processos = processoRepository.pesquisar(processoFilter);
			
		if (processos.isEmpty()) {
			System.out.println("Entrou no if");
			processo.setNumero_processo(processoFilter.getNumero_processo().concat("000000"));
		}
		else {
			int numero = Integer.parseInt(
					processos.get(processos.size() - 1).getNumero_processo()) + 1;
			processo.setNumero_processo(Integer.toString(numero)); 
		} 
		return processoRepository.save(processo);
	}
	
	public Page<Processo> buscarProcessos(ProcessoFilter processoFilter, SolicitanteFilter solicitanteFilter, Pageable pageable) {
		return processoRepository.filtrar(processoFilter, solicitanteFilter, pageable);
	}
	
	public void excluirProcesso(Long id) {
		processoRepository.deleteById(id);
	}
	
	public Processo editarProcesso(Processo processo, Long id) {
		Processo processoSalvo = processoRepository.getOne(id);
		if ( processoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(processo, processoSalvo, "id, numero_processo, data_de_criacao, solicitante.id");
		return processoRepository.save(processoSalvo);
	}
}
