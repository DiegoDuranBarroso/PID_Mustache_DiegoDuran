# Proyecto Spring Boot – Gestión de Usuarios y Direcciones

Proyecto desarrollado con **Spring Boot** que permite gestionar usuarios y sus direcciones asociadas utilizando el motor de plantillas **Mustache**.

## 📌 Funcionalidades

### Usuarios
- Listar usuarios
- Añadir usuario
- Editar usuario (solo nombre)
- Borrar usuario

### Direcciones
- Añadir direcciones a un usuario
- Listar direcciones asociadas a cada usuario
- Borrado automático de direcciones al eliminar un usuario

> ⚠️ No se permite editar direcciones (según requisitos).

---

## 🛠️ Tecnologías utilizadas

- Java 17+
- Spring Boot 3
- Spring MVC
- Spring Data JPA (Hibernate)
- Mustache (motor de plantillas)
- Maven
- Base de datos relacional (H2 / MySQL / MariaDB)
- Tomcat embebido

---

## 🌐 Rutas principales

| Método | URL | Descripción |
|------|-----|-------------|
| GET | `/` | Página principal |
| GET | `/listUsuarios` | Listado de usuarios |
| GET | `/addUsuario` | Formulario añadir usuario |
| POST | `/guardarUsuario` | Guardar usuario |
| GET | `/editUsuario/{id}` | Editar usuario |
| POST | `/updateUsuario` | Actualizar usuario |
| POST | `/deleteUsuario/{id}` | Borrar usuario |
| GET | `/addDireccion/{id}` | Añadir dirección a usuario |
| POST | `/guardarDireccion` | Guardar dirección |

---

## 🖥️ Vistas (Mustache)

El proyecto utiliza **Mustache**, por lo que:
- ❌ No se usan atributos `th:*`
- ✅ Se utilizan variables simples `{{variable}}`
- ✅ Se pasan datos planos desde el controlador cuando es necesario

Ejemplo:
```html
<input type="text" name="nombre" value="{{nombre}}">