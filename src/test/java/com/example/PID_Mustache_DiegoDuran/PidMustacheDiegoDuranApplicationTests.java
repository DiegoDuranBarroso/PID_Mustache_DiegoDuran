package com.example.PID_Mustache_DiegoDuran;

import com.example.PID_Mustache_DiegoDuran.domain.Usuario;
import com.example.PID_Mustache_DiegoDuran.exceptions.ResourceNotFoundException;
import com.example.PID_Mustache_DiegoDuran.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(properties = {
		"spring.datasource.url=jdbc:h2:mem:pid_mustache_test",
		"spring.jpa.hibernate.ddl-auto=create-drop"
})
class PidMustacheDiegoDuranApplicationTests {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void crearUsuarioGuardaNombreNormalizado() {
		Usuario usuario = new Usuario();
		usuario.setNombre("  Ana  ");

		usuarioService.crearUsuario(usuario);

		assertThat(usuarioService.findById(usuario.getId()).getNombre()).isEqualTo("Ana");
	}

	@Test
	void findByIdLanzaExcepcionSiNoExiste() {
		assertThatThrownBy(() -> usuarioService.findById(999L))
				.isInstanceOf(ResourceNotFoundException.class)
				.hasMessageContaining("Usuario no encontrado");
	}

	@Test
	void updateNombreRechazaNombreVacio() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Pedro");
		usuarioService.crearUsuario(usuario);

		assertThatThrownBy(() -> usuarioService.updateNombre(usuario.getId(), " "))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("El nombre es obligatorio");
	}

	@Test
	void updateNombreRechazaNombreDemasiadoLargo() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Pedro");
		usuarioService.crearUsuario(usuario);

		assertThatThrownBy(() -> usuarioService.updateNombre(usuario.getId(), "a".repeat(81)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("El nombre no puede superar 80 caracteres");
	}

	@Test
	void guardarUsuarioRedirigeAlListado() throws Exception {
		mockMvc.perform(post("/guardarUsuario").param("nombre", "Lucia"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/listUsuarios"));
	}

	@Test
	void addUsuarioMuestraFormulario() throws Exception {
		mockMvc.perform(get("/addUsuario"))
				.andExpect(status().isOk());
	}
}
