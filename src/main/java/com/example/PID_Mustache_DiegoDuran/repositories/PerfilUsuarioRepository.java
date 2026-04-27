package com.example.PID_Mustache_DiegoDuran.repositories;

import com.example.PID_Mustache_DiegoDuran.domain.PerfilUsuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PerfilUsuarioRepository extends CrudRepository<PerfilUsuario, Long> {

    Optional<PerfilUsuario> findByUsuarioId(Long usuarioId);
}
