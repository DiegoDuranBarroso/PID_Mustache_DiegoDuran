package com.example.PID_Mustache_DiegoDuran.controllers;

import com.example.PID_Mustache_DiegoDuran.domain.Usuario;
import com.example.PID_Mustache_DiegoDuran.services.DireccionService;
import com.example.PID_Mustache_DiegoDuran.services.TipoDireccionService;
import com.example.PID_Mustache_DiegoDuran.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DireccionController {

    private final DireccionService direccionService;
    private final TipoDireccionService tipoDireccionService;
    private final UsuarioService usuarioService;

    public DireccionController(DireccionService direccionService,
                               TipoDireccionService tipoDireccionService,
                               UsuarioService usuarioService) {
        this.direccionService = direccionService;
        this.tipoDireccionService = tipoDireccionService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/addDireccion/{usuarioId}")
    public String mostrarFormularioAddDireccion(@PathVariable Long usuarioId, Model model) {
        Usuario usuario = usuarioService.findById(usuarioId);
        model.addAttribute("usuario", usuario);
        model.addAttribute("tiposDireccion", tipoDireccionService.findAll());
        model.addAttribute("calle", "");
        model.addAttribute("ciudad", "");
        return "addDireccion";
    }

    @PostMapping("/guardarDireccion")
    public String guardarDireccion(@RequestParam Long usuarioId,
                                   @RequestParam String calle,
                                   @RequestParam String ciudad,
                                   @RequestParam Long tipoDireccionId,
                                   Model model) {
        try {
            direccionService.crearDireccion(usuarioId, calle, ciudad, tipoDireccionId);
        } catch (IllegalArgumentException ex) {
            Usuario usuario = usuarioService.findById(usuarioId);
            model.addAttribute("usuario", usuario);
            model.addAttribute("tiposDireccion", tipoDireccionService.findAll());
            model.addAttribute("validationError", ex.getMessage());
            model.addAttribute("calle", calle);
            model.addAttribute("ciudad", ciudad);
            return "addDireccion";
        }
        return "redirect:/listUsuarios";
    }
}
