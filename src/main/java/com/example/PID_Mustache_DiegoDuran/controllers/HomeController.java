package com.example.PID_Mustache_DiegoDuran.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/holaTh")
	public String holaPage(Model model) {
		String texto = "Hola mundo con Mustache + Spring";
		model.addAttribute("Bienvenida", texto);
		return "holaTh"; // busca holaTh.mustache
	}
}
