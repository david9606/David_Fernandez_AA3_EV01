package com.motowner.model;

import javax.persistence.*;

/**
 * Entidad que representa la tabla de usuarios en la base de datos MySQL.
 * Esta clase le dice a la base de datos qué columnas debe tener nuestra tabla.
 * @author David
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificación automática y única para cada usuario

    @Column(unique = true, nullable = false)
    private String username; // El nombre de usuario para el login (No se puede repetir)

    @Column(nullable = false)
    private String password; // La contraseña de acceso

    // Constructor vacío obligatorio para que el sistema funcione
    public Usuario() {}

    // Constructor para registrar datos rápidamente
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Funciones estándar (Getters y Setters) para leer y escribir los datos de forma segura
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}