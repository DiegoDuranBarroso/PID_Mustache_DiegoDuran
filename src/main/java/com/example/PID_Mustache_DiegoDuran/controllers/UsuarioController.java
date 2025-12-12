package com.example.PID_Mustache_DiegoDuran.controllers;

import com.example.PID_Mustache_DiegoDuran.domain.Usuario;
import com.example.PID_Mustache_DiegoDuran.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/listUsuarios")
	public String listUsuariosPage(Model model) {
		model.addAttribute("listaUsuarios", usuarioService.findAllUsers());
		return "listUsuariosBasico"; // listUsuariosBasico.mustache
	}

	@GetMapping("/addUsuario")
	public String mostrarFormularioAddUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "addUsuario"; // addUsuario.mustache
	}

	@PostMapping("/guardarUsuario")
	public String guardarUsuario(Usuario usuario) {
		usuarioService.crearUsuario(usuario);
		return "redirect:/listUsuarios";
	}

	// ---- NUEVO: formulario editar (solo nombre)
	@GetMapping("/editUsuario/{id}")
	public String editUsuario(@PathVariable Long id, Model model) {
		Usuario u = usuarioService.findById(id);
		model.addAttribute("usuario", u);
		return "editUsuario"; // editUsuario.mustache
	}

	// ---- NUEVO: procesar update (solo nombre)
	@PostMapping("/updateUsuario")
	public String updateUsuario(@RequestParam Long id, @RequestParam String nombre) {
		usuarioService.updateNombre(id, nombre);
		return "redirect:/listUsuarios";
	}

	// ---- NUEVO: borrar usuario
	@PostMapping("/deleteUsuario/{id}")
	public String deleteUsuario(@PathVariable Long id) {
		usuarioService.deleteById(id);
		return "redirect:/listUsuarios";
	}
}

