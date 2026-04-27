package com.example.PID_Mustache_DiegoDuran.commandLineRunner;

import com.example.PID_Mustache_DiegoDuran.domain.ActividadUsuario;
import com.example.PID_Mustache_DiegoDuran.domain.Direccion;
import com.example.PID_Mustache_DiegoDuran.domain.PerfilUsuario;
import com.example.PID_Mustache_DiegoDuran.domain.TipoDireccion;
import com.example.PID_Mustache_DiegoDuran.domain.Usuario;
import com.example.PID_Mustache_DiegoDuran.repositories.ActividadUsuarioRepository;
import com.example.PID_Mustache_DiegoDuran.repositories.DireccionRepository;
import com.example.PID_Mustache_DiegoDuran.repositories.PerfilUsuarioRepository;
import com.example.PID_Mustache_DiegoDuran.repositories.TipoDireccionRepository;
import com.example.PID_Mustache_DiegoDuran.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final DireccionRepository direccionRepository;
    private final TipoDireccionRepository tipoDireccionRepository;
    private final PerfilUsuarioRepository perfilUsuarioRepository;
    private final ActividadUsuarioRepository actividadUsuarioRepository;

    public MyCommandLineRunner(UsuarioRepository usuarioRepository,
                               DireccionRepository direccionRepository,
                               TipoDireccionRepository tipoDireccionRepository,
                               PerfilUsuarioRepository perfilUsuarioRepository,
                               ActividadUsuarioRepository actividadUsuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.direccionRepository = direccionRepository;
        this.tipoDireccionRepository = tipoDireccionRepository;
        this.perfilUsuarioRepository = perfilUsuarioRepository;
        this.actividadUsuarioRepository = actividadUsuarioRepository;
    }

    @Override
    public void run(String... args) {
        poblarBD();
    }

    void poblarBD() {
        TipoDireccion calle = crearTipoDireccionSiNoExiste("Calle", "Direccion de tipo calle");
        TipoDireccion piso = crearTipoDireccionSiNoExiste("Piso", "Direccion de tipo piso");

        crearUsuarioLuikySiNoExiste(calle, piso);
        crearUsuarioPedroSiNoExiste(calle);
    }

    private void crearUsuarioLuikySiNoExiste(TipoDireccion calle, TipoDireccion piso) {
        if (usuarioRepository.findByNombreIgnoreCase("Luiky").isPresent()) {
            return;
        }

        Usuario luiky = crearUsuario("Luiky");
        crearPerfil(luiky, "luiky@example.com", "600111222", LocalDate.now().minusMonths(3));
        crearDireccion(luiky, "Calle 1", "Ciudad 1", calle);
        crearDireccion(luiky, "Piso 2", "Ciudad 2", piso);
        crearActividad(luiky, "Alta de usuario", "Creacion inicial del perfil", LocalDate.now().minusMonths(3));
        crearActividad(luiky, "Revision de datos", "Comprobacion de direcciones asociadas", LocalDate.now().minusDays(12));
    }

    private void crearUsuarioPedroSiNoExiste(TipoDireccion calle) {
        if (usuarioRepository.findByNombreIgnoreCase("Pedro").isPresent()) {
            return;
        }

        Usuario pedro = crearUsuario("Pedro");
        crearPerfil(pedro, "pedro@example.com", "600333444", LocalDate.now().minusMonths(1));
        crearDireccion(pedro, "Calle 3", "Ciudad 3", calle);
        crearActividad(pedro, "Contacto inicial", "Primer registro de actividad del usuario", LocalDate.now().minusDays(20));
    }

    private Usuario crearUsuario(String nombre) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        return usuarioRepository.save(usuario);
    }

    private TipoDireccion crearTipoDireccionSiNoExiste(String nombre, String descripcion) {
        return tipoDireccionRepository.findByNombreIgnoreCase(nombre)
                .orElseGet(() -> crearTipoDireccion(nombre, descripcion));
    }

    private TipoDireccion crearTipoDireccion(String nombre, String descripcion) {
        TipoDireccion tipoDireccion = new TipoDireccion();
        tipoDireccion.setNombre(nombre);
        tipoDireccion.setDescripcion(descripcion);
        return tipoDireccionRepository.save(tipoDireccion);
    }

    private void crearDireccion(Usuario usuario, String calle, String ciudad, TipoDireccion tipoDireccion) {
        Direccion direccion = new Direccion();
        direccion.setCalle(calle);
        direccion.setCiudad(ciudad);
        direccion.setUsuario(usuario);
        direccion.setTipoDireccion(tipoDireccion);
        direccionRepository.save(direccion);
    }

    private void crearPerfil(Usuario usuario, String email, String telefono, LocalDate fechaRegistro) {
        PerfilUsuario perfilUsuario = new PerfilUsuario();
        perfilUsuario.setEmail(email);
        perfilUsuario.setTelefono(telefono);
        perfilUsuario.setFechaRegistro(fechaRegistro);
        perfilUsuario.setUsuario(usuario);
        perfilUsuarioRepository.save(perfilUsuario);
    }

    private void crearActividad(Usuario usuario, String titulo, String descripcion, LocalDate fecha) {
        ActividadUsuario actividad = new ActividadUsuario();
        actividad.setTitulo(titulo);
        actividad.setDescripcion(descripcion);
        actividad.setFecha(fecha);
        actividad.setUsuario(usuario);
        actividadUsuarioRepository.save(actividad);
    }
}
