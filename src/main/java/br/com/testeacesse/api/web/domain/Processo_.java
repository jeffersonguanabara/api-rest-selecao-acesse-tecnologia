package br.com.testeacesse.api.web.domain;

import java.time.Instant;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Processo.class)
public abstract class Processo_ {

	public static volatile SingularAttribute<Processo, String> numero_processo;
	public static volatile SingularAttribute<Processo, Instant> data_de_criacao;
	public static volatile SingularAttribute<Processo, LocalDate> data_do_processo;
	public static volatile SingularAttribute<Processo, Solicitante> solicitante;
	public static volatile SingularAttribute<Processo, Long> id;
	public static volatile SingularAttribute<Processo, String> arquivo_pdf;

	public static final String NUMERO_PROCESSO = "numero_processo";
	public static final String DATA_DE_CRIACAO = "data_de_criacao";
	public static final String DATA_DO_PROCESSO = "data_do_processo";
	public static final String SOLICITANTE = "solicitante";
	public static final String ID = "id";
	public static final String ARQUIVO_PDF = "arquivo_pdf";

}

