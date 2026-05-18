package com.motowner.controller;

import com.motowner.model.Usuario;
import com.motowner.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST que expone los Servicios Web de Registro y Login.
 * Funciona como un punto de acceso reutilizable para aplicaciones Web o Móviles.
 * @author David
 */
@RestController
@RequestMapping("/api/auth") // Esta será la dirección base en internet (URL) para usar estos servicios
public class AuthController {

    // Conectamos al "Mesero" con el "Jefe de Seguridad" (Service)
    @Autowired
    private AuthService authService;

    /**
     * INDICADOR 1: Servicio Web para el Registro de Usuarios.
     * Recibe los datos y responde si se pudo crear o si hubo un error.
     */
    @PostMapping("/register")
    public ResponseEntity<String> registrar(@RequestBody Usuario usuario) {
        // Le mandamos los datos al servicio para que aplique las validaciones
        String resultado = authService.registrarUsuario(usuario);
        
        // Si la respuesta empieza con "Error", devolvemos un estado de fallo (Bad Request)
        if (resultado.startsWith("Error")) {
            return ResponseEntity.badRequest().body(resultado);
        }
        // Si todo sale bien, devolvemos un mensaje de éxito (OK)
        return ResponseEntity.ok(resultado);
    }

    /**
     * INDICADOR 2: Servicio Web para el Inicio de Sesión (Login).
     * Recibe el usuario y clave, y valida si tiene permiso de entrar.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        // Le pedimos al servicio que verifique las credenciales en la base de datos
        String resultado = authService.loginUsuario(usuario.getUsername(), usuario.getPassword());
        
        // Si las credenciales están mal, devolvemos un estado de no autorizado (401 Unauthorized)
        if (resultado.startsWith("Error")) {
            return ResponseEntity.status(401).body(resultado);
        }
        // Si la autenticación es correcta, responde con el mensaje satisfactorio obligatorio
        return ResponseEntity.ok(resultado);
    }
}