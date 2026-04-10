# DOSW-Library


## Descripción del Proyecto

**DOSW-Library** es una solución de backend diseñada para la administración de recursos bibliográficos en entornos académicos. La aplicación gestiona el ciclo de vida de los libros, facilitando procesos de control de inventario, registro de préstamos y gestión de devoluciones. El sistema integra un módulo de seguridad basado en roles para restringir el acceso a operaciones sensibles, garantizando un entorno administrativo confiable y eficiente.

---

## Tecnologías Usadas

* **Java 21**
* **Spring Boot 3.4.3**
* **Spring Data JPA**
* **Spring Security con JWT**
* **MongoDB**
* **Maven**
* **JUnit 5**
* **JaCoCo**

---

## Funcionalidades

* Gestión de catálogo de libros.
* Sistema de préstamos y disponibilidad.
* Seguridad y autorización por roles.
* Documentación de API integrada.


## Diagrama de contexto

El sistema se compone de dos módulos principales: la Plataforma, que gestiona la interacción con el usuario, y el Core, que procesa la lógica interna. Ambos interactúan con una Base de Datos externa para la persistencia de la información.

![Diagrama de contexto.png](imagenes/Diagrama%20de%20contexto.png)

## Diagrama general

![Diagrama general.png](imagenes/Diagrama%20general.png)

Interfaz de Usuario: Punto de interacción final para la gestión de libros, usuarios y préstamos.

Backend API (Núcleo): Procesa la lógica de negocio, validaciones y excepciones del sistema.

Base de Datos: Almacena el inventario, registros de usuarios e historial de transacciones.

Flujo de interacción: Interfaz de Usuario ➔ Backend API ➔ Base de Datos.

## Diagrama de clases

![Diagrama de clases.png](imagenes/Diagrama%20de%20clases.png)

El diagrama de clases centra su lógica en la entidad Loan, la cual vincula a User con Book. Este modelo registra temporalmente el préstamo y utiliza una enumeración de Status para validar rigurosamente el estado del ejemplar (activo o retornado).


## Diagrama especifico

![Diagrama Especifico.png](imagenes/Diagrama%20Especifico.png)


El diagrama organiza la lógica del sistema en una arquitectura de capas: los Mappers transforman los datos de entrada para los Controladores, quienes delegan el procesamiento a los Services. Finalmente, los servicios emplean Validators para garantizar el cumplimiento de las reglas de negocio antes de procesar la información.

Flujo lógico: Mappers ➔ Controladores ➔ Services ➔ Validators.


## Diagrama Entidad-Relacion

El modelo ER  estructura la base de datos mediante la tabla Loan, que actúa como entidad asociativa central. A través de llaves foráneas, conecta las tablas User y Book en relaciones de uno a muchos ($1:N$), permitiendo la trazabilidad de múltiples solicitudes por usuario e ingresos históricos por cada ejemplar.

![Diagrama Entidad-Relacion.png](imagenes/Diagrama%20Entidad-Relacion.png)

## Diagrama No Relacional

Este diagrama detalla un modelo de datos NoSQL basado en documentos, diseñado para optimizar el rendimiento mediante desnormalización.

Entidades Core: User y Book actúan como los documentos maestros que proveen datos al resto del sistema.

Datos Embebidos: Se utilizan relaciones de inclusión (punteadas) hacia metaData y Disponibilidad para agilizar las consultas sin realizar cruces complejos.

Gestión de Préstamos: La entidad Loan centraliza la operación, vinculando a los usuarios con los libros.

Histórico: LoanHistory almacena de forma embebida los registros de préstamos, garantizando la trazabilidad histórica de cada transacción.

![Diagrama No Relacional.png](imagenes/Diagrama%20No%20Relacional.png)