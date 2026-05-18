package com.motowner.repository;

import com.motowner.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositorio encargado de las consultas automáticas hacia la tabla de usuarios.
 * Al heredar de JpaRepository, ganamos funciones como guardar, buscar y eliminar sin escribir código SQL.
 * @author David
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    /**
     * Método personalizado que busca un usuario en la base de datos usando su nombre de usuario.
     * Esto nos servirá para verificar si un nombre ya está tomado o si las credenciales de login existen.
     */
    Optional<Usuario> findByUsername(String username);
}