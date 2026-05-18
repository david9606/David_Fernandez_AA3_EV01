package com.motowner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de arranque para el ecosistema MotOwner.
 * Cumple con los estándares de codificación Java y la arquitectura de Spring Boot.
 * * @author David (Tu Apellido)
 * @version 1.0
 */
@SpringBootApplication
public class MotOwnerFrameworkApplication {

    public static void main(String[] args) {
        // Ejecución e inicio del servidor web embebido Tomcat en el puerto 8080
        SpringApplication.run(MotOwnerFrameworkApplication.class, args);
    }
}