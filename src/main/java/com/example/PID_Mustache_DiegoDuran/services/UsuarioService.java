package com.example.PID_Mustache_DiegoDuran.services;

import com.example.PID_Mustache_DiegoDuran.domain.Usuario;

import java.util.List;


public interface UsuarioService {
	
	public List <Usuario> findAllUsers();

	public void crearUsuario(Usuario usuario);

	public Usuario findById(Long id);

	public void updateNombre(Long id, String nombre);

	public void deleteById(Long id);

}
