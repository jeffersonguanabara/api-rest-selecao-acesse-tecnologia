package br.com.testeacesse.api.web.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Solicitante.class)
public abstract class Solicitante_ {

	public static volatile SingularAttribute<Solicitante, String> cidade;
	public static volatile SingularAttribute<Solicitante, String> numero;
	public static volatile SingularAttribute<Solicitante, String> bairro;
	public static volatile SingularAttribute<Solicitante, String> nome;
	public static volatile SingularAttribute<Solicitante, Long> id;
	public static volatile SingularAttribute<Solicitante, String> cep;
	public static volatile SingularAttribute<Solicitante, String> rua;

	public static final String CIDADE = "cidade";
	public static final String NUMERO = "numero";
	public static final String BAIRRO = "bairro";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String CEP = "cep";
	public static final String RUA = "rua";

}

