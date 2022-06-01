package com.example.usuario.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.usuario.model.Usuario;
import com.example.usuario.service.UsuarioService;

import lombok.var;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@GetMapping(value = "/usuarios")
	public List<Usuario> buscarUsuarios() {
		return service.buscarTodosUsuarios();
	}

	@GetMapping(value = "/nome")
	public List<Usuario> ordenacaoNome() {
		return service.ordenacaoNome();
	}

	@GetMapping(value = "/idade")
	public List<Usuario> ordenacaoIdade() {
		return service.ordenacaoIdade();
	}

	@PostMapping(value = "/inserir")
	public ResponseEntity<Usuario> inserirUsuario(HttpServletRequest request, UriComponentsBuilder uriComponentsBuilder,
			@RequestBody Usuario usuario) {

		service.inserirUsuario(usuario);

		UriComponents uriComponents = uriComponentsBuilder.path("/inserir/{id}").buildAndExpand(usuario.getId());
		var location = uriComponents.toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping(value = "/usuario/{id}")
	public Usuario buscarUsuario(@PathVariable(value = "id") Long id) {
		return service.buscarUsuario(id);
	}

	@PutMapping(value = "/usuario/{id}")
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable(value = "id") Long id, @RequestBody Usuario usuario) {
		Usuario usuarioNovo = service.atualizarUsuario(id, usuario);
		return ResponseEntity.ok(usuarioNovo);
	}

	@DeleteMapping(value = "/usuario/{id}")
	public ResponseEntity<Long> deletarUsuario(@PathVariable(value = "id") Long id) {

		service.deletarUsuario(id);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}

}
