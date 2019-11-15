package com.formacionbdi.springboot.apps.oauth.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.formacionbdi.springboot.app.usuarios.commons.models.entity.Usuario;
import com.formacionbdi.springboot.apps.oauth.services.IUsuarioService;

import feign.FeignException;

@Component
public class AuthenticationSuccedErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccedErrorHandler.class);

	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		String mensaje = "Success Login: " + user.getUsername();
		System.out.println(mensaje);
		log.info(mensaje);
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		
		if(usuario.getIntentos() != null && usuario.getIntentos() > 0) {
			usuario.setIntentos(0);
			usuarioService.update(usuario, usuario.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String mensaje = "Error Login: " + exception.getMessage();
		log.error(mensaje);
		System.out.println(mensaje);

		try {
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			if (usuario.getIntentos() == null) {
				usuario.setIntentos(0);
			}

			log.info("Intento actual es de: " + usuario.getIntentos());
			usuario.setIntentos(usuario.getIntentos() + 1);
			log.info("Intento despues es de: " + usuario.getIntentos());

			if (usuario.getIntentos() >= 3) {
				log.error(String.format("El usuario %s deshabilitado por maximos intentos", usuario.getUsername()));
				usuario.setEnabled(false);

			}

			usuarioService.update(usuario, usuario.getId());

		} catch (FeignException e) {
			log.error(String.format("El usuario %s no existe", authentication.getName()));

		}
	}

}
