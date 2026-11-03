# API Volcán Gas

Backend REST del proyecto **Distribuidora de Gas El Volcán**, desarrollado en el contexto del curso **DSY1104 – Desarrollo FullStack II**.

Su propósito es proporcionar una API que centralice el acceso a los datos y las operaciones del negocio, permitiendo que la aplicación web consulte y gestione la información de productos, clientes y ventas.

## Proyecto

Volcán Gas es una plataforma orientada a apoyar la gestión de una distribuidora de gas licuado a domicilio, facilitando la administración de sus productos y operaciones comerciales.

El proyecto se divide en dos componentes:

| Componente | Repositorio | Descripción |
|---|---|---|
| Frontend | [web-volcan-gas](https://github.com/0HolaSoyYo0/web-volcan-gas) | Interfaz web desarrollada con React. |
| Backend | [api-volcan-gas](https://github.com/0HolaSoyYo0/api-volcan-gas) | API REST encargada de la lógica de negocio y persistencia de datos. |

Ambos componentes se desarrollan de manera independiente y se comunicarán mediante solicitudes HTTP. Actualmente, el frontend utiliza datos locales para representar el catálogo de productos, por lo que la integración con el backend todavía está pendiente.

## Objetivo

Desarrollar una API REST que permita administrar los principales recursos del sistema mediante operaciones CRUD (crear, consultar, actualizar y eliminar).

Su implementación busca separar la interfaz de usuario de la lógica de negocio y el acceso a datos, proporcionando una estructura organizada y preparada para incorporar nuevas funcionalidades.

## Alcance actual

La primera etapa del backend se concentra en tres recursos principales:

- **Productos:** administrar la información de los cilindros disponibles en el catálogo.
- **Clientes:** almacenar y gestionar la información de los clientes.
- **Ventas:** registrar y administrar las ventas realizadas.

Cada recurso contará con operaciones CRUD independientes. En esta etapa, las entidades no utilizarán asociaciones JPA entre sí; las referencias necesarias se manejarán mediante identificadores simples.

Este alcance permite establecer la base funcional de la API sin incorporar anticipadamente procesos comerciales más complejos.

## Arquitectura

El backend utiliza una arquitectura por capas, separando las responsabilidades de cada componente:

| Capa | Responsabilidad |
|---|---|
| **Model** | Representar las entidades del sistema y su estructura persistente. |
| **Repository** | Gestionar el acceso a la base de datos mediante Spring Data JPA. |
| **Service** | Centralizar las operaciones y reglas de negocio. |
| **Controller** | Exponer los recursos mediante endpoints REST. |

El flujo general de una solicitud será:

`Frontend → Controller → Service → Repository → Base de datos`

Esta separación facilita el mantenimiento, la organización del código y la evolución independiente de cada responsabilidad.

## Tecnologías

- **Java 25:** lenguaje de programación.
- **Spring Boot 4.1.2:** framework principal del backend.
- **Spring Web MVC:** construcción de la API REST.
- **Spring Data JPA:** persistencia y acceso a datos.
- **Hibernate:** implementación ORM.
- **MySQL 9.7.4:** sistema de gestión de base de datos.
- **Flyway:** control de versiones y evolución del esquema de base de datos.
- **Spring Validation:** validación de datos.
- **Springdoc OpenAPI:** documentación de la API.

## Estado del desarrollo

El proyecto se encuentra en una etapa inicial de construcción.

Actualmente cuenta con la estructura base de Spring Boot, las dependencias principales, la configuración de conexión a MySQL y una migración inicial para las tablas de productos, clientes y ventas.

Todavía se encuentra pendiente la implementación completa de las entidades JPA, repositorios, servicios, controladores REST y sus respectivas pruebas. La integración con el frontend también forma parte del trabajo futuro.

## Proyección

Una vez completada la base CRUD, el backend podrá evolucionar para dar soporte a funcionalidades adicionales del sistema, como autenticación, gestión de pedidos, carrito de compras, pagos, inventario y seguimiento de entregas.

Estas funcionalidades corresponden a etapas posteriores y no forman parte del alcance inicial.

---

**Proyecto académico · DSY1104 – Desarrollo FullStack II**  
**Distribuidora de Gas El Volcán**
