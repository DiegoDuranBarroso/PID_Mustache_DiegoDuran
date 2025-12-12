package com.example.PID_Mustache_DiegoDuran.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	public HomeController() {
		// TODO Auto-generated constructor stub
		System.out.println("\t Builder of " + this.getClass().getSimpleName());
	}
	@GetMapping("/")
	public String index() {
		System.out.println("\n\t Recogo la peticion a / (http://localhost:8080/)\n"
				+ "\t Devuelvo la vista index\n"
				+ "\t index.mustache esta en Templates");
		return "index";
	}

	@GetMapping("/holaTh")
	public String holaPage(Model model) {
		String texto = "Hola mundo con Mustache + Spring";
		model.addAttribute("Bienvenida", texto);
		return "holaTh"; // busca holaTh.mustache
	}

	

}
