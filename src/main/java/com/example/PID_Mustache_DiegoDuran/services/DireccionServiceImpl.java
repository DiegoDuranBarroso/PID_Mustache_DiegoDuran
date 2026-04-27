package com.example.PID_Mustache_DiegoDuran.services;

import com.example.PID_Mustache_DiegoDuran.domain.Direccion;
import com.example.PID_Mustache_DiegoDuran.domain.TipoDireccion;
import com.example.PID_Mustache_DiegoDuran.domain.Usuario;
import com.example.PID_Mustache_DiegoDuran.exceptions.ResourceNotFoundException;
import com.example.PID_Mustache_DiegoDuran.repositories.DireccionRepository;
import com.example.PID_Mustache_DiegoDuran.repositories.TipoDireccionRepository;
import com.example.PID_Mustache_DiegoDuran.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DireccionServiceImpl implements DireccionService {

    private final DireccionRepository direccionRepository;
    private final UsuarioRepository usuarioRepository;
    private final TipoDireccionRepository tipoDireccionRepository;

    public DireccionServiceImpl(DireccionRepository direccionRepository,
                                UsuarioRepository usuarioRepository,
                                TipoDireccionRepository tipoDireccionRepository) {
        this.direccionRepository = direccionRepository;
        this.usuarioRepository = usuarioRepository;
        this.tipoDireccionRepository = tipoDireccionRepository;
    }

    @Override
    public void crearDireccion(Long usuarioId, String calle, String ciudad, Long tipoDireccionId) {
        if (!StringUtils.hasText(calle) || !StringUtils.hasText(ciudad)) {
            throw new IllegalArgumentException("La calle y la ciudad son obligatorias");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + usuarioId));
        TipoDireccion tipoDireccion = tipoDireccionRepository.findById(tipoDireccionId)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de direccion no encontrado: " + tipoDireccionId));

        Direccion direccion = new Direccion();
        direccion.setCalle(calle.trim());
        direccion.setCiudad(ciudad.trim());
        direccion.setUsuario(usuario);
        direccion.setTipoDireccion(tipoDireccion);

        direccionRepository.save(direccion);
    }
}
