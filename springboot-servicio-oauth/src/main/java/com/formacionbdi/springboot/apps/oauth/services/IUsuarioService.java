package com.formacionbdi.springboot.apps.oauth.services;

import org.springframework.web.bind.annotation.PutMapping;

import com.formacionbdi.springboot.app.usuarios.commons.models.entity.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);
	
	@PutMapping("/usuarios/{id}")
	public Usuario update (Usuario usuario, Long id);
}