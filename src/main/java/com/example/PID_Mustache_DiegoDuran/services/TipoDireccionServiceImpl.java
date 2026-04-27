package com.example.PID_Mustache_DiegoDuran.services;

import com.example.PID_Mustache_DiegoDuran.domain.TipoDireccion;
import com.example.PID_Mustache_DiegoDuran.repositories.TipoDireccionRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TipoDireccionServiceImpl implements TipoDireccionService {

    private final TipoDireccionRepository tipoDireccionRepository;

    public TipoDireccionServiceImpl(TipoDireccionRepository tipoDireccionRepository) {
        this.tipoDireccionRepository = tipoDireccionRepository;
    }

    @Override
    public List<TipoDireccion> findAll() {
        return tipoDireccionRepository.findByNombreInOrderByNombreAsc(Arrays.asList("Calle", "Piso"));
    }
}
