# Reto Literatura 

Este proyecto es una aplicación web llamada " Reto Literatura " que utiliza Spring Boot, Maven y PostgreSQL para su desarrollo, con API Gutendex de libros.

## Tecnologías utilizadas

- Spring Boot: framework para el desarrollo de aplicaciones web en Java.
- Maven: herramienta de gestión de dependencias y construcción de proyectos.
- PostgreSQL: sistema gestor de bases de datos relacional.

## Requisitos

- Java 17 o superior.
- Spring , SpringBoot 3.x o superior
- Maven .
- PostgreSQL 9.x o superior.

## Configuración

Para configurar la aplicación, se recomienda crear un archivo de configuración oculto (por ejemplo, `application-local.properties`) en el directorio raíz del proyecto o cambiar las propiedades existentes en resources/application.properties, son las siguientes propiedades:
spring.application.name=retoliteratura
spring.datasource.url=jdbc:postgresql://localhost:8080/retoliteratura
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

## Recomendaciones

se recomienda seguir la documentacion de gutendex para el consumo de la api , y actualizar el pon.xml con las dependencias utilizadas.
