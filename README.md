# Teach Game

Este es un proyecto demo de Spring Boot de Teach Game para el grupo 6.

---

# Introducción / Contexto

Teach Game es una aplicación web educativa en desarrollo que permite a los docentes crear clases interactivas en materias como música, matemáticas, inglés y ciencias, ofreciendo herramientas visuales simples y accesibles para mejorar la participación y comprensión de los estudiantes.

### Justificación

En la actualidad, muchos docentes carecen de herramientas digitales intuitivas que les permitan diseñar clases dinámicas y atractivas sin requerir conocimientos técnicos avanzados.

Aunque existen diversas plataformas educativas, muchas de ellas son complejas, costosas o poco adaptadas a las necesidades reales del aula.

Por esta razón, Teach Game propone una solución sencilla que permita a los profesores:

- Crear actividades interactivas.
- Integrar contenido multimedia.
- Facilitar el aprendizaje mediante herramientas digitales accesibles.

### Breve descripción del dominio / temática del proyecto integrador

Teach Game busca:

✔ Mejorar la experiencia educativa a través de la interactividad.  
✔ Fomentar el uso de tecnologías accesibles.  
✔ Ofrecer una alternativa gratuita y funcional como apoyo en el aula.

---

# Estructura del Proyecto

El proyecto sigue una arquitectura en capas típica de aplicaciones Spring Boot:

proyecto-teach-game-grupo-6/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/grupo6/
│ │ │ ├── ProyectoTeachGameApplication.java ← Clase principal
│ │ │ ├── controller/ ← Controladores REST
│ │ │ ├── service/ ← Lógica de negocio
│ │ │ ├── repository/ ← Acceso a datos (JPA)
│ │ │ ├── model/
│ │ │ │ ├── entity/ ← Entidades JPA
│ │ │ │ └── dto/ ← Transfer Objects
│ │ │ ├── exception/ ← Excepciones personalizadas
│ │ │ └── config/ ← Configuraciones (CORS, Seguridad, etc.)
│ │ └── resources/
│ │ ├── application.properties ← Configuración general
│ │ └── application-dev.properties ← Configuración desarrollo
│ └── test/
│ └── java/com/grupo6/ ← Tests unitarios e integración
├── pom.xml ← Dependencias Maven
└── README.md
└── render.yaml



---

## Descripción de Capas

| Carpeta | Descripción | Responsabilidad |
|--------|-------------|----------------|
| controller/ | Controladores REST | Manejo de peticiones HTTP y respuestas |
| service/ | Servicios | Lógica de negocio y reglas de dominio |
| repository/ | Repositorios JPA | Acceso y persistencia de datos |
| model/entity/ | Entidades JPA | Mapeo de tablas de base de datos |
| model/dto/ | Data Transfer Objects | Transferencia de datos entre capas |
| exception/ | Excepciones | Manejo de errores personalizados |
| config/ | Configuración | Beans, CORS, Seguridad, etc. |
| test/ | Tests | Pruebas unitarias e integración |

---

# Objetivos

## Objetivo General

Desarrollar una aplicación web educativa que permita a los profesores crear, organizar y gestionar clases y actividades interactivas, clasificadas por materia, utilizando tecnologías modernas como Spring Boot y arquitectura basada en servicios.

## Objetivos Específicos

- Incorporar contenidos multimedia, como enlaces a videos, audios o recursos educativos externos, para enriquecer las actividades de aprendizaje.
- Implementar funcionalidades básicas mediante Spring Boot, incluyendo controladores, servicios, repositorios y manejo de entidades.
- Desarrollar una API REST que permita gestionar información como clases, materias y actividades.
- Aplicar principios de arquitectura por capas, separando la lógica de negocio, el acceso a datos y la capa de presentación.
- Publicar el avance del proyecto en GitHub utilizando control de versiones.
- Diseñar una interfaz amigable e intuitiva que facilite el uso de la plataforma por parte de los docentes.

---

# Alcance del Proyecto (Scope)

## Qué se va a desarrollar

Durante el desarrollo del proyecto se implementarán los siguientes módulos:

- Gestión de materias o asignaturas (crear, consultar, editar y eliminar).
- Registro de clases o actividades educativas.
- Asociación de actividades con una materia específica.
- Inclusión de recursos multimedia (enlaces a videos, audios o documentos).
- Creación de una API REST utilizando Spring Boot.
- Organización del proyecto siguiendo una arquitectura en capas.

Capas utilizadas:

- Controller
- Service
- Repository
- Model / Entity

Además:

- Uso de Maven para gestión de dependencias.
- Control de versiones con Git y GitHub.
- Documentación del proyecto en README.md.

## Qué NO se desarrollará en esta versión

- Sistema completo de autenticación de usuarios.
- Roles avanzados (administrador, docente, estudiante).
- Frontend avanzado (React, Angular, etc.).
- Sistema automático de evaluación de estudiantes.
- Integración con bases de datos externas en la nube.
- Inteligencia artificial o recomendaciones automáticas.
- Aplicación móvil nativa.

Estas funcionalidades podrán agregarse en versiones futuras.

---

# Tecnologías y Herramientas (Tech Stack)

**Backend**

- Spring Boot
- Java 21
- Spring Data JPA
- Hibernate

**Base de datos**

- MySQL (producción)
- H2 (desarrollo inicial)

**Herramientas**

- Git
- GitHub
- Maven
- Scalar
- Render
- Lombok

---

# Integrantes del Equipo

| Nombre | Rol principal | Usuario GitHub |
|------|------|------|
| Paola Arismendy | Líder / Backend | @PaolaArismendy |
| Mayerlin Cubides | Frontend Lead | @Ined2011 |
| Johana Peña | Backend / Base de datos | @Johaph3728 |

---

# Modelo de Dominio del Sistema

El modelo de dominio define las entidades principales del sistema y las relaciones entre ellas.  
Estas entidades representan los elementos fundamentales de la plataforma educativa Teach Game.

Las entidades fueron diseñadas utilizando **JPA (Java Persistence API)** y **Lombok** para simplificar el código.

Cada entidad contiene entre **3 y 7 atributos relevantes**, incluyendo campos de auditoría como:

- `createdAt`
- `updatedAt`

---

## Entidades Principales

### Subject (Materia)

Representa una asignatura o área de aprendizaje dentro de la plataforma.

**Atributos**

- id
- name
- description
- createdAt
- updatedAt

Una materia puede contener múltiples clases o actividades educativas.

Relación:

Subject **1 ─── N** Lesson

---

### Lesson (Clase / Actividad)

Representa una clase o actividad educativa creada por el docente.

**Atributos**

- id
- title
- description
- difficultyLevel
- createdAt
- updatedAt

Cada clase pertenece a una materia específica.

Relación:

Lesson **N ─── 1** Subject

---

### Resource (Recurso Multimedia)

Representa recursos educativos asociados a un link seguro externo.

**Atributos**

- id
- title
- url
- type
- createdAt
- updatedAt

Una clase puede tener varios recursos multimedia.

Relación:

Lesson **1 ─── N** Resource

---

## Tecnologías utilizadas en las entidades

Las entidades están implementadas usando:

- `@Entity`
- `@Id`
- `@GeneratedValue`
- `@ManyToOne`
- `@OneToMany`
- Lombok (`@Getter`, `@Setter`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`)

---

# Diagrama de Clases del Dominio (v1)

El siguiente diagrama UML representa la estructura inicial del modelo de dominio del sistema.

Incluye:

- Entidades principales
- Atributos clave
- Relaciones entre entidades

![Diagrama de Dominio v1](docs/diagrama-dominio-v1.png) 

# pendiente subir el correcto de py

*Diagrama inicial del modelo de dominio – versión 1.*

---

# Instrucciones de Instalación y Ejecución

## Requisitos Previos

- Java 21
- Maven 3.6+
- MySQL 8.0+
- Git
- IDE (IntelliJ, VS Code o similar)

---

## Pasos de Instalación

### 1. Clonar repositorio

```bash
git clone https://github.com/Teachgame26/PruebaBacken_16052026.git
cd proyecto-teach-game-grupo-6


2. Crear base de datos
CREATE DATABASE teach_game_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE teach_game_dev CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

Configurar credenciales en:

application.properties
application-dev.properties
3. Compilar proyecto
./mvnw clean install

En Windows:

mvnw.cmd clean install
4. Ejecutar aplicación
./mvnw spring-boot:run
5. Acceder al sistema
http://localhost:8080
Perfiles de Desarrollo

La aplicación soporta múltiples perfiles:

Perfil por defecto

application.properties

Perfil de desarrollo

application-dev.properties

Ejecutar con perfil dev:

./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
Estructura de Código

Al crear nuevas clases respeta la siguiente estructura:

Controllers

com.grupo6.controller

Anotación:

@RestController

Services

com.grupo6.service

Anotación:

@Service

Repositories

com.grupo6.repository

Extiende:

JpaRepository<Entity, ID>

Entities

com.grupo6.model.entity

Anotación:

@Entity

DTOs

com.grupo6.model.dto

Excepciones

com.grupo6.exception
Comandos Útiles
Comando	Descripción
mvnw clean	Limpia la carpeta target
mvnw test	Ejecuta tests unitarios
mvnw compile	Compila el proyecto
mvnw package	Empaqueta como JAR
mvnw help:active-profiles	Muestra perfiles activos
Guía de Contribución

Crear rama

git checkout -b feature/tu-feature

Realizar cambios siguiendo la arquitectura.

Commit claro

git commit -m "feat: descripción del cambio"

Subir cambios

git push origin feature/tu-feature

Crear Pull Request.

Recursos y Referencias

Spring Boot
https://spring.io/projects/spring-boot

Spring Data JPA
https://spring.io/projects/spring-data-jpa

Clean Code — Robert C. Martin
https://www.oreilly.com/library/view/clean-code-a/9780136083238/

Última actualización: Marzo 2026
Grupo: Grupo 6
Proyecto: Teach Game - Aplicación Web Educativa