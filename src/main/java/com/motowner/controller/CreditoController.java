package com.motowner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.motowner.model.Credito;
import com.motowner.repository.CreditoRepository;

/**
 * ESTÁNDAR DE CODIFICACIÓN - CAPA CONTROLADOR (MVC)
 * Clase encargada de interceptar las peticiones HTTP (GET y POST) de la web de MotOwner.
 * Comunica la vista (Thymeleaf) con el repositorio para persistir datos en MySQL.
 * * @author David Fernandez
 * @version 1.0
 */
@Controller
public class CreditoController {

    // Inyección de dependencias: Spring Boot conecta el repositorio automáticamente
    @Autowired
    private CreditoRepository creditoRepository;

    /**
     * Atiende las peticiones GET en la raíz del sitio web.
     * Muestra la pantalla inicial con el formulario de simulación.
     */
    @GetMapping("/")
    public String mostrarFormulario() {
        return "index"; // Busca el archivo index.html en src/main/resources/templates
    }

    /**
     * Atiende las peticiones POST enviadas desde el formulario.
     * Captura los datos de forma segura, procesa el cálculo y almacena el registro en MySQL.
     */
    @PostMapping("/simular")
    public String procesarSimulacion(
            @RequestParam("nombreCliente") String nombre,
            @RequestParam("valorMoto") double valor,
            Model model) {
        
        // 1. Instanciar el objeto del modelo con los datos del usuario
        Credito nuevoCredito = new Credito(nombre, valor);
        
        // 2. Ejecutar la lógica de negocio (Cálculo de la cuota semanal)
        double cuotaCalculada = nuevoCredito.calcularCuotaSemanal();
        
        // 3. PERSISTENCIA: Guardar de forma automática la simulación en la base de datos MySQL
        creditoRepository.save(nuevoCredito);
        
        // 4. Enviar los resultados hacia la vista utilizando el objeto Model de Spring
        model.addAttribute("cliente", nuevoCredito.getNombreCliente());
        model.addAttribute("precio", nuevoCredito.getValorMoto());
        model.addAttribute("semanas", nuevoCredito.getCuotasSemanales());
        model.addAttribute("resultadoCuota", cuotaCalculada);
        
        // 5. Historial: Enviar también la lista completa de simulaciones previas guardadas en BD
        model.addAttribute("historialSimulaciones", creditoRepository.findAll());
        
        return "resultado"; // Redirecciona a la vista resultado.html
    }
}