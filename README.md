# Ejercicio Practico Mi Negocio 


## Descripción del Proyecto
Este proyecto es un servicio backend desarrollado en **Java** utilizando **Spring Boot**, que se conecta a una base de datos **PostgreSQL** y expone una API REST para la gestión de clientes y sus direcciones. El sistema permite registrar, editar, eliminar y buscar clientes, así como gestionar direcciones adicionales, asegurando que cada cliente tenga una dirección matriz obligatoria.

### Tecnologías Utilizadas
- **Lenguaje**: Java 17 (compatible con Java 8+ según requerimientos)
- **Framework**: Spring Boot 3.4.5
- **Base de Datos**: PostgreSQL
- **Persistencia**: Spring Data JPA con Hibernate
- **Validaciones**: Jakarta Validation (usando `@NotBlank` y `@NotNull`)
- **Pruebas Unitarias**: JUnit y Mockito
- **Herramientas de Build**: Maven
- **Control de Versiones**: Git

### Estructura del Proyecto
El proyecto sigue una arquitectura estándar de Spring Boot, aplicando principios de **Clean Code** y algunos principios de **SOLID** (como el principio de responsabilidad única en los controladores y servicios).

#### Entidades
- **Customer** (Cliente):
    - Campos: `id`, `identificationType`, `identificationNumber`, `name`, `email`, `phoneNumber`, `mainProvince`, `mainCity`, `mainAddress`, `addresses` (relación `@OneToMany` con `Address`).
    - La dirección matriz está embebida en los campos `mainProvince`, `mainCity`, y `mainAddress` para garantizar que sea obligatoria.
    - Validaciones: Todos los campos son obligatorios (`@NotBlank`).

- **Address** (Dirección Adicional):
    - Campos: `id`, `province`, `city`, `mainAddress`, `secondaryAddress`, `customer` (relación `@ManyToOne` con `Customer`).
    - Validaciones: `province`, `city`, y `mainAddress` son obligatorios (`@NotBlank`); `customer` es obligatorio (`@NotNull`).

#### Endpoints Implementados
La API REST expone los siguientes endpoints, todos probados con Postman:

1. **Listar Clientes (`GET /api/customers/search?query=jose`)**:
    - Devuelve una lista de clientes con un filtro de nombre o numero de indetificacione.
    - **Respuesta Ejemplo**:
      ```
      [
         {
        "id": 1,
        "identificationType": "CEDULA",
        "identificationNumber": "1234567890",
        "name": "Raul Reyes",
        "email": "reaul.reyes@minegocio.com",
        "phoneNumber": "555-1234",
        "mainProvince": "Pichincha",
        "mainCity": "Quito",
        "mainAddress": "Av. Amazonas"
   }
      ]
      
      ```

2. **Crear un Cliente (`POST /api/customers`)**:
    - Crea un nuevo cliente con su dirección matriz.
    - **Solicitud Ejemplo**:
      ```
      {
   "identificationType": "CEDULA",
   "identificationNumber": "0987654321",
   "name": "MAnolo Torres",
   "email": "manolo.torres@minegocio.com",
   "phoneNumber": "555-1234",
   "mainProvince": "Pichincha",
   "mainCity": "Quito",
   "mainAddress": "Av. Amazonas",
   "addresses": null
   }
      ```
    - **Respuesta Ejemplo**:
      ```
{
"id": 2,
"identificationType": "CEDULA",
"identificationNumber": "0987654321",
"name": "MAnolo Torres",
"email": "manolo.torres@minegocio.com",
"phoneNumber": "555-1234",
"mainProvince": "Pichincha",
"mainCity": "Quito",
"mainAddress": "Av. Amazonas",
"addresses": null
}
      ```
    - **Validaciones**:
        - Si se envían datos inválidos, se devuelven mensajes de error específicos:
          ```
          {
              "identificationType": "El tipo de identificación es obligatorio",
              "identificationNumber": "El número de identificación es obligatorio",
              "name": "El nombre es obligatorio",
              "email": "El email es obligatorio",
              "phoneNumber": "El número de teléfono es obligatorio",
              "mainProvince": "La provincia matriz es obligatoria",
              "mainCity": "La ciudad matriz es obligatoria",
              "mainAddress": "La dirección matriz es obligatoria"
          }
          ```

3. **Editar un Cliente (`PUT /api/customers/{id}`)**:
    - Actualiza los datos de un cliente existente.
    - **Solicitud Ejemplo**:
      ```
      PUT /api/customers/1
      {
          "identificationType": "CEDULA",
          "identificationNumber": "1234567890",
          "name": "Juan Perez Actualizado",
          "email": "juan.perez.actualizado@example.com",
          "phoneNumber": "555-5678"
      }
      ```
    - **Respuesta Ejemplo**:
      ```
      {
          "id": 1,
          "identificationType": "CEDULA",
          "identificationNumber": "1234567890",
          "name": "Juan Perez Actualizado",
          "email": "juan.perez.actualizado@example.com",
          "phoneNumber": "555-5678",
          "mainProvince": "Pichincha",
          "mainCity": "Quito",
          "mainAddress": "Av. Amazonas 123",
          "addresses": []
      }
      ```

4. **Eliminar un Cliente (`DELETE /api/customers/{id}`)**:
    - Elimina un cliente por su ID.
    - **Solicitud Ejemplo**:
      ```
      DELETE /api/customers/1
      ```
    - **Respuesta**: Código HTTP 204 No Content.

5. **Registrar una Nueva Dirección por Cliente (`POST /api/addresses`)**:
    - Registra una dirección adicional para un cliente existente.
    - **Solicitud Ejemplo**:
      ```
      {
          "province": "Guayas",
          "city": "Guayaquil",
          "mainAddress": "Av. 9 de Octubre",
          "secondaryAddress": "123",
          "customer": {
              "id": 1
          }
      }
      ```
    - **Respuesta Ejemplo**:
      ```
      {
          "id": 1,
          "province": "Guayas",
          "city": "Guayaquil",
          "mainAddress": "Av. 9 de Octubre",
          "secondaryAddress": "123"
      }
      ```

6. **Listar Direcciones Adicionales del Cliente (`GET /api/addresses/by-customer/{id}`)**:
   
### Configuración para Ejecutar la Aplicación

#### Requisitos Previos
1. **Java**: JDK 17 (o superior, compatible con Java 8+).
2. **Maven**: Para gestionar dependencias y construir el proyecto.
3. **PostgreSQL**: Una base de datos local PostgreSQL.
    - Nombre de la base de datos: `minegocio`
    - Usuario: `postgres`
    - Contraseña: (la que configures)
    - Puerto: `5432` (por defecto)

#### Pasos para Configurar y Ejecutar
1. **Clonar el Repositorio**:
   ```
   git clone <URL_DEL_REPOSITORIO>
   cd mi-negocio
   ```

2. **Configurar la Base de Datos**:
    - Crea una base de datos en PostgreSQL:
      ```
      CREATE DATABASE minegocio;
      ```
    - Configura las credenciales en `src/main/resources/application.properties`:
      ```
      spring.datasource.url=jdbc:postgresql://localhost:5432/minegocio
      spring.datasource.username=postgres
      spring.datasource.password=yourpassword
      spring.jpa.hibernate.ddl-auto=create
      spring.jpa.show-sql=true
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
      ```

3. **Construir el Proyecto**:
    - Desde la raíz del proyecto, ejecuta:
      ```
      mvn clean install
      ```

4. **Ejecutar la Aplicación**:
    - Corre la clase principal:
      ```
      mvn spring-boot:run
      ```
    - O ejecuta directamente desde tu IDE (por ejemplo, IntelliJ) corriendo `MiNegocioApplication.java`.

5. **Probar los Endpoints**:
    - Usa Postman para probar los endpoints descritos arriba.
    - La aplicación se ejecuta en `http://localhost:8080`.

### Mejores Prácticas Aplicadas
- **Clean Code**: Nombres descriptivos para clases, métodos y variables (por ejemplo, `Customer`, `Address`, `CustomerController`).
- **Patrones**:
    - Uso del patrón **Repository** con Spring Data JPA (`CustomerRepository`, `AddressRepository`).
    - Separación de responsabilidades entre controladores (`CustomerController`, `AddressController`) y servicios (`CustomerService`, `AddressService`).
- **Validaciones**: Uso de Jakarta Validation para validar los datos de entrada (`@NotBlank`, `@NotNull`).
- **Manejo de Excepciones**: Implementación de un manejador global de excepciones (`GlobalExceptionHandler`) para errores de validación.
- **DTOs**: Uso de `CustomerDTO` para controlar qué datos se devuelven en `GET /api/customers`, excluyendo las direcciones adicionales.

### Notas Adicionales
- No se utilizaron herramientas de versionamiento de base de datos como Liquibase, ya que `spring.jpa.hibernate.ddl-auto` fue suficiente para este caso.
- La dirección matriz se implementó como campos embebidos en `Customer` (`mainProvince`, `mainCity`, `mainAddress`) para garantizar que sea obligatoria y única por cliente.
- La relación `@OneToMany` con `Address` se usa exclusivamente para direcciones adicionales.