package com.example.PID_Mustache_DiegoDuran.controllers;

import com.example.PID_Mustache_DiegoDuran.domain.Usuario;
import com.example.PID_Mustache_DiegoDuran.services.ActividadUsuarioService;
import com.example.PID_Mustache_DiegoDuran.services.UsuarioService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class ActividadUsuarioController {

    private final ActividadUsuarioService actividadUsuarioService;
    private final UsuarioService usuarioService;

    public ActividadUsuarioController(ActividadUsuarioService actividadUsuarioService,
                                      UsuarioService usuarioService) {
        this.actividadUsuarioService = actividadUsuarioService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/addActividad/{usuarioId}")
    public String mostrarFormularioAddActividad(@PathVariable Long usuarioId, Model model) {
        Usuario usuario = usuarioService.findById(usuarioId);
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "");
        model.addAttribute("descripcion", "");
        model.addAttribute("fecha", "");
        return "addActividad";
    }

    @PostMapping("/guardarActividad")
    public String guardarActividad(@RequestParam Long usuarioId,
                                   @RequestParam String titulo,
                                   @RequestParam String descripcion,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
                                   Model model) {
        try {
            actividadUsuarioService.crearActividad(usuarioId, titulo, descripcion, fecha);
        } catch (IllegalArgumentException ex) {
            Usuario usuario = usuarioService.findById(usuarioId);
            model.addAttribute("usuario", usuario);
            model.addAttribute("validationError", ex.getMessage());
            model.addAttribute("titulo", titulo);
            model.addAttribute("descripcion", descripcion);
            model.addAttribute("fecha", fecha == null ? "" : fecha);
            return "addActividad";
        }
        return "redirect:/listUsuarios";
    }
}
