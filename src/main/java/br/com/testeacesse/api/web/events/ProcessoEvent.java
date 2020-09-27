package br.com.testeacesse.api.web.events;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

public class ProcessoEvent extends ApplicationEvent {
	
	private HttpServletResponse response;
	private Long id;

	public ProcessoEvent(Object source, HttpServletResponse response, Long id) {
		super(source);
		this.response = response;
		this.id = id;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getId() {
		return id;
	}

}
