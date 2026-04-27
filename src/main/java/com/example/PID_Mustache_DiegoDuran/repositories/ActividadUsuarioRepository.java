package com.example.PID_Mustache_DiegoDuran.repositories;

import com.example.PID_Mustache_DiegoDuran.domain.ActividadUsuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActividadUsuarioRepository extends CrudRepository<ActividadUsuario, Long> {

    List<ActividadUsuario> findByUsuarioIdOrderByFechaDesc(Long usuarioId);
}
