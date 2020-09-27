package br.com.testeacesse.api.web.restcontroller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.testeacesse.api.web.domain.Processo;
import br.com.testeacesse.api.web.events.ProcessoEvent;
import br.com.testeacesse.api.web.repository.filter.ProcessoFilter;
import br.com.testeacesse.api.web.repository.filter.SolicitanteFilter;
import br.com.testeacesse.api.web.service.ProcessoService;

@RestController
@RequestMapping("/api")
public class ProcessoRestController {
	
	@Autowired
	private ProcessoService processoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping("/processos")
	public Page<Processo> listarProcessos(ProcessoFilter processoFilter, SolicitanteFilter solicitanteFilter, Pageable pageable){
		return processoService.buscarProcessos(processoFilter, solicitanteFilter, pageable);
	}
	
	@PostMapping("/processos")
	public ResponseEntity<?> salvarProcesso(@RequestBody @Validated Processo processo, HttpServletResponse response) {
		Processo processo_salvo = processoService.salvarProcesso(processo);
		publisher.publishEvent(new ProcessoEvent(this, response, processo_salvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(processo_salvo);
	}
	
	@DeleteMapping("/processos/{id}")
	public ResponseEntity<?> excluirProcesso(@PathVariable @Validated Long id) {
		processoService.excluirProcesso(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/processos/{id}") 
	public ResponseEntity<?> editarProcesso(@RequestBody @Validated Processo processo, @PathVariable Long id) {		
		return ResponseEntity.ok(processoService.editarProcesso(processo, id));
	}
	
}
