package com.example.PID_Mustache_DiegoDuran.repositories;

import com.example.PID_Mustache_DiegoDuran.domain.TipoDireccion;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TipoDireccionRepository extends CrudRepository<TipoDireccion, Long> {

    List<TipoDireccion> findAllByOrderByNombreAsc();

    List<TipoDireccion> findByNombreInOrderByNombreAsc(Collection<String> nombres);

    Optional<TipoDireccion> findByNombreIgnoreCase(String nombre);
}
