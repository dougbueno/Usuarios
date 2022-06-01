package com.example.usuario.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usuario.exceptions.ResourceNotFoundException;
import com.example.usuario.model.Usuario;
import com.example.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repository;

	public List<Usuario> buscarTodosUsuarios() {
		return repository.findAll();
	}

	public List<Usuario> ordenacaoNome() {
		List<Usuario> lista = repository.findAll();
		lista.sort(Comparator.comparing(Usuario::getNome));
		return lista;
	}

	public List<Usuario> ordenacaoIdade() {
		List<Usuario> lista = repository.findAll();
		lista.sort(Comparator.comparing(Usuario::getIdade));
		return lista;
	}

	public void inserirUsuario(Usuario usuario) {
		repository.save(usuario);
	}

	public Usuario buscarUsuario(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario não existe com esse ID:: " + id));
	}

	public void deletarUsuario(Long id) {
		repository.deleteById(id);
	}

	public Usuario atualizarUsuario(Long id, Usuario usuario) {
		Usuario dadosUsuario = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario não existe com esse ID:: " + id));
		dadosUsuario.setId(id);
		dadosUsuario.setIdade(usuario.getIdade());
		dadosUsuario.setNome(usuario.getNome());
		repository.save(dadosUsuario);
		return dadosUsuario;
	}

}
