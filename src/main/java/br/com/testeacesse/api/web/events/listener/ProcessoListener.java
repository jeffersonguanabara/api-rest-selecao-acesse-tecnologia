package br.com.testeacesse.api.web.events.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.testeacesse.api.web.events.ProcessoEvent;

@Component
public class ProcessoListener implements ApplicationListener<ProcessoEvent>{

	@Override
	public void onApplicationEvent(ProcessoEvent event) {
		HttpServletResponse response = event.getResponse();
		Long id = event.getId();
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(id).toUri();
			response.setHeader("Location", uri.toASCIIString());	
	}

}
