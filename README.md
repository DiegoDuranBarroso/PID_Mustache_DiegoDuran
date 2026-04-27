# Proyecto Spring Boot - Gestion de Usuarios y Direcciones

Aplicacion web desarrollada con Spring Boot para gestionar usuarios, direcciones y actividades usando vistas Mustache.

## Funcionalidades

- Listar usuarios con perfil, direcciones y actividades.
- Crear usuarios.
- Editar el nombre de un usuario.
- Borrar usuarios junto con sus datos asociados.
- Anadir direcciones a un usuario.
- Anadir actividades a un usuario.
- Validar datos obligatorios en formularios.
- Mostrar una pagina de error cuando se solicita un recurso inexistente.

## Tecnologias

- Java 17
- Spring Boot 3.4.0
- Spring MVC
- Spring Data JPA / Hibernate
- Mustache
- Maven
- H2 persistente en fichero
- Tomcat embebido

## Ejecucion

El proyecto queda configurado para ejecutarse con Maven.

```bash
mvn spring-boot:run
```

Para cargar datos de ejemplo al arrancar, usa el perfil `dev`:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

La aplicacion queda disponible en:

```text
http://localhost:8080
```

La consola H2 queda disponible en:

```text
http://localhost:8080/h2-console
```

Datos de conexion H2:

```text
JDBC URL: jdbc:h2:file:./data/pid_mustache
Usuario: sa
Contrasena:
```

## Rutas principales

| Metodo | URL | Descripcion |
| --- | --- | --- |
| GET | `/` | Pagina principal |
| GET | `/listUsuarios` | Listado de usuarios |
| GET | `/addUsuario` | Formulario de alta de usuario |
| POST | `/guardarUsuario` | Guardar usuario |
| GET | `/editUsuario/{id}` | Editar usuario |
| POST | `/updateUsuario` | Actualizar usuario |
| POST | `/deleteUsuario/{id}` | Borrar usuario |
| GET | `/addDireccion/{id}` | Formulario de alta de direccion |
| POST | `/guardarDireccion` | Guardar direccion |
| GET | `/addActividad/{id}` | Formulario de alta de actividad |
| POST | `/guardarActividad` | Guardar actividad |

## Notas de diseno

- Se usa Maven como herramienta de construccion principal.
- Los datos de prueba estan aislados en el perfil `dev`.
- Las relaciones dependientes de `Usuario` usan cascada y `orphanRemoval` para evitar registros colgados.
- Las validaciones se declaran en las entidades y se refuerzan en la capa de servicio.
