package com.example.PID_Mustache_DiegoDuran.services;

import com.example.PID_Mustache_DiegoDuran.domain.Direccion;
import com.example.PID_Mustache_DiegoDuran.domain.Usuario;
import com.example.PID_Mustache_DiegoDuran.repositories.DireccionRepository;
import com.example.PID_Mustache_DiegoDuran.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final DireccionRepository direccionRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
							  DireccionRepository direccionRepository) {

		System.out.println("\t UsuarioServiceImpl constructor ");
		this.usuarioRepository = usuarioRepository;
		this.direccionRepository = direccionRepository;
	}


	//En la capa servicios es donde se implementa la LOGICA de negocio
	@Override
	public List<Usuario> findAllUsers() {
			
		// TODO Asi no es recomendable: Comprobar si hay usuarios, si esta vacio ... 
		return this.usuarioRepository.findAllWithDirecciones();
	}

	@Override
	public void crearUsuario(Usuario usuario) {
		// TODO Logica...
		usuarioRepository.save(usuario);
	}

	public Usuario findById(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));
	}

	@Transactional
	public void updateNombre(Long id, String nombre) {
		Usuario u = findById(id);
		u.setNombre(nombre);
		usuarioRepository.save(u);
	}

	@Transactional
	public void deleteById(Long id) {
		List<Direccion> dirs = direccionRepository.findByUsuarioId(id);
		direccionRepository.deleteAll(dirs);
		usuarioRepository.deleteById(id);
	}
}
