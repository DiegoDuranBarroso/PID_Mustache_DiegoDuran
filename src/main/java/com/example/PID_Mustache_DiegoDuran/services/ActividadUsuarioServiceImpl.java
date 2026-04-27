package com.example.PID_Mustache_DiegoDuran.services;

import com.example.PID_Mustache_DiegoDuran.domain.ActividadUsuario;
import com.example.PID_Mustache_DiegoDuran.domain.Usuario;
import com.example.PID_Mustache_DiegoDuran.exceptions.ResourceNotFoundException;
import com.example.PID_Mustache_DiegoDuran.repositories.ActividadUsuarioRepository;
import com.example.PID_Mustache_DiegoDuran.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Service
public class ActividadUsuarioServiceImpl implements ActividadUsuarioService {

    private final ActividadUsuarioRepository actividadUsuarioRepository;
    private final UsuarioRepository usuarioRepository;

    public ActividadUsuarioServiceImpl(ActividadUsuarioRepository actividadUsuarioRepository,
                                       UsuarioRepository usuarioRepository) {
        this.actividadUsuarioRepository = actividadUsuarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void crearActividad(Long usuarioId, String titulo, String descripcion, LocalDate fecha) {
        if (!StringUtils.hasText(titulo) || fecha == null) {
            throw new IllegalArgumentException("El titulo y la fecha son obligatorios");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + usuarioId));

        ActividadUsuario actividad = new ActividadUsuario();
        actividad.setTitulo(titulo.trim());
        actividad.setDescripcion(descripcion == null ? null : descripcion.trim());
        actividad.setFecha(fecha);
        actividad.setUsuario(usuario);

        actividadUsuarioRepository.save(actividad);
    }
}
