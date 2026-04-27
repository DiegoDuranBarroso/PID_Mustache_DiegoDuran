package com.example.PID_Mustache_DiegoDuran.services;

import com.example.PID_Mustache_DiegoDuran.domain.Usuario;
import com.example.PID_Mustache_DiegoDuran.exceptions.ResourceNotFoundException;
import com.example.PID_Mustache_DiegoDuran.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}


	@Override
	public List<Usuario> findAllUsers() {
		return this.usuarioRepository.findAllWithDirecciones();
	}

	@Override
	public void crearUsuario(Usuario usuario) {
		usuario.setNombre(usuario.getNombre().trim());
		usuarioRepository.save(usuario);
	}

	public Usuario findById(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + id));
	}

	@Transactional
	public void updateNombre(Long id, String nombre) {
		if (!StringUtils.hasText(nombre)) {
			throw new IllegalArgumentException("El nombre es obligatorio");
		}
		String nombreNormalizado = nombre.trim();
		if (nombreNormalizado.length() > 80) {
			throw new IllegalArgumentException("El nombre no puede superar 80 caracteres");
		}
		Usuario u = findById(id);
		u.setNombre(nombreNormalizado);
		usuarioRepository.save(u);
	}

	@Transactional
	public void deleteById(Long id) {
		Usuario usuario = findById(id);
		usuarioRepository.delete(usuario);
	}
}
