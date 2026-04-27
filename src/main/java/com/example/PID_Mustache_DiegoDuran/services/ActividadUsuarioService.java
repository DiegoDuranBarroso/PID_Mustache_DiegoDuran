package com.example.PID_Mustache_DiegoDuran.services;

import java.time.LocalDate;

public interface ActividadUsuarioService {

    void crearActividad(Long usuarioId, String titulo, String descripcion, LocalDate fecha);
}
