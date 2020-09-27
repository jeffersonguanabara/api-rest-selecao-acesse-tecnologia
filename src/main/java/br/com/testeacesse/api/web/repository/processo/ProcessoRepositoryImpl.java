package br.com.testeacesse.api.web.repository.processo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.testeacesse.api.web.domain.Processo;
import br.com.testeacesse.api.web.domain.Solicitante;
import br.com.testeacesse.api.web.domain.Solicitante_;
import br.com.testeacesse.api.web.repository.filter.ProcessoFilter;
import br.com.testeacesse.api.web.repository.filter.SolicitanteFilter;

public class ProcessoRepositoryImpl implements ProcessoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Processo> pesquisar(ProcessoFilter processoFilter) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Processo> criteria = builder.createQuery(Processo.class);
		Root<Processo> root = criteria.from(Processo.class);
		
		SolicitanteFilter solicitanteFilter = new SolicitanteFilter();
		solicitanteFilter.setNome("");
		Predicate[] predicates = criarRestricoes(processoFilter, solicitanteFilter, builder, root);
		criteria.where(predicates);		
		
		TypedQuery<Processo> query = entityManager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(ProcessoFilter processoFilter, SolicitanteFilter solicitanteFilter,
			CriteriaBuilder builder, Root<Processo> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		System.out.println(processoFilter.toString());
		System.out.println(solicitanteFilter.toString());
				
		if(!StringUtils.isEmpty(processoFilter.getNumero_processo()) || (processoFilter.getNumero_processo() != null)) {
			System.out.println("Numero de processo");
			predicates.add(builder.like(root.get("numero_processo"), "%" + processoFilter.getNumero_processo() + "%")); 		
		}
		if((!StringUtils.isEmpty(solicitanteFilter.getNome())) || (solicitanteFilter.getNome() != null)) {
			System.out.println("solicitante");
			Join<Solicitante, Processo> solicitanteJoin =
			  root.join(Solicitante_.id, JoinType.INNER); 
			predicates = (List<Predicate>) root.join(solicitanteFilter.getNome(), JoinType.INNER);
			 
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public Page<Processo> filtrar(ProcessoFilter processoFilter, SolicitanteFilter solicitanteFilter, Pageable pageable) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Processo> criteria = builder.createQuery(Processo.class);
		Root<Processo> root = criteria.from(Processo.class);
		
		Predicate[] predicates = criarRestricoes(processoFilter, solicitanteFilter, builder, root);
		criteria.where(predicates);		
		
		TypedQuery<Processo> query = entityManager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		System.out.println("Query result: " + query.getResultList());
		
		return new PageImpl<>(query.getResultList(), pageable, total(processoFilter, solicitanteFilter));
	}

	private Long total(ProcessoFilter processoFilter, SolicitanteFilter solicitanteFilter) {
		System.out.println("Total");
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Processo> root = criteria.from(Processo.class);
		
		Predicate[] predicates = criarRestricoes(processoFilter, solicitanteFilter, builder , root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return entityManager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Processo> query, Pageable pageable) {
		
		System.out.println("Paginação");
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
		System.out.println("Query: " + query);
	}
}
