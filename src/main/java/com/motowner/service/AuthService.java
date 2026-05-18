package com.motowner.service;

import com.motowner.model.Usuario;
import com.motowner.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Servicio encargado de procesar la lógica de negocio y seguridad para el sistema.
 * Aquí se realizan las validaciones obligatorias de la guía de evaluación.
 * @author David
 */
@Service
public class AuthService {

    // Conectamos al Jefe de Seguridad con nuestro "Buscador" (Repository)
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * VALIDACIÓN 1: Lógica para registrar un nuevo usuario.
     * Verifica que el nombre no esté repetido antes de guardarlo.
     */
    public String registrarUsuario(Usuario usuario) {
        // Le pedimos al buscador que revise si ese nombre ya existe en la base de datos
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            // Si el buscador lo encuentra, frenamos el registro y devolvemos un error
            return "Error: El nombre de usuario ya se encuentra registrado.";
        }
        
        // Si no existe, el Jefe de Seguridad da la orden de guardarlo en MySQL
        usuarioRepository.save(usuario);
        return "Usuario registrado de manera satisfactoria.";
    }

    /**
     * VALIDACIÓN 2: Lógica para el inicio de sesión (Login).
     * Compara los datos ingresados con los reales en la base de datos.
     */
    public String loginUsuario(String username, String password) {
        // Buscamos al usuario por su nombre
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);

        // Regla de verificación: ¿El usuario existe Y la contraseña escrita es igual a la guardada?
        if (usuarioOpt.isPresent() && usuarioOpt.get().getPassword().equals(password)) {
            // Si todo coincide, damos luz verde
            return "Autenticación satisfactoria.";
        } else {
            // Si el usuario no existe o la clave está mal, bloqueamos el acceso
            return "Error en la autenticación: Credenciales incorrectas.";
        }
    }
}