package com.example.PID_Mustache_DiegoDuran.repositories;

import com.example.PID_Mustache_DiegoDuran.domain.Direccion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DireccionRepository extends CrudRepository<Direccion, Long> {
	
	List <Direccion> findByUsuarioId(Long id);

}
