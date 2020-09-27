package br.com.testeacesse.api.web.domain;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_processo")
public class Processo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(
		length = 10, 
		unique = true, 
		nullable = false)
	private String numero_processo;
	private LocalDate  data_do_processo;
	private Instant data_de_criacao = Instant.now();
	@OneToOne(cascade = CascadeType.ALL)
	private Solicitante solicitante;
	private String arquivo_pdf;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero_processo() {
		return numero_processo;
	}
	public void setNumero_processo(String numero_processo) {
		this.numero_processo = numero_processo;
	}
	public LocalDate getData_do_processo() {
		return data_do_processo;
	}
	public void setData_do_processo(LocalDate data_do_processo) {
		this.data_do_processo = data_do_processo;
	}
	public Instant getData_de_criacao() {
		return data_de_criacao;
	}
	public void setData_de_criacao(Instant data_de_criacao) {
		this.data_de_criacao = data_de_criacao;
	}
	public Solicitante getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}
	public String getArquivo_pdf() {
		return arquivo_pdf;
	}
	public void setArquivo_pdf(String arquivo_pdf) {
		this.arquivo_pdf = arquivo_pdf;
	}
}
