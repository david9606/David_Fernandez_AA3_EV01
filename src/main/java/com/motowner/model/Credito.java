package com.motowner.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ESTÁNDAR DE CODIFICACIÓN - CAPA MODELO
 * Esta clase representa la entidad 'Credito' en el patrón arquitectónico MVC.
 * Utiliza anotaciones de Spring Data JPA para mapearse como una tabla en MySQL.
 * * @author David Fernandez
 * @version 1.0
 */
@Entity
@Table(name = "simulaciones") // Define el nombre real de la tabla en MySQL
public class Credito {

    // Identificador único autoincremental para la base de datos (Llave Primaria)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Atributos privados aplicando el principio de encapsulamiento
    private String nombreCliente;
    private double valorMoto;
    private int cuotasSemanales = 72; // Estándar comercial definido para MotOwner

    /**
     * Constructor por defecto requerido obligatoriamente por JPA.
     */
    public Credito() {
    }

    /**
     * Constructor personalizado para inicializar una simulación de crédito.
     * @param nombreCliente Nombre del usuario que solicita la simulación.
     * @param valorMoto Valor comercial del vehículo a financiar.
     */
    public Credito(String nombreCliente, double valorMoto) {
        this.nombreCliente = nombreCliente;
        this.valorMoto = valorMoto;
    }

    /**
     * MÉTODO DE LÓGICA DE NEGOCIO
     * Realiza el cálculo matemático para determinar el cobro por semana.
     * @return El valor aproximado redondeado de la cuota.
     */
    public double calcularCuotaSemanal() {
        if (this.valorMoto <= 0) {
            return 0;
        }
        return this.valorMoto / this.cuotasSemanales;
    }

    // ==========================================
    // MÉTODOS MÁNDATORIOS ACCESORES (GETTERS Y SETTERS)
    // ==========================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public double getValorMoto() {
        return valorMoto;
    }

    public void setValorMoto(double valorMoto) {
        this.valorMoto = valorMoto;
    }

    public int getCuotasSemanales() {
        return cuotasSemanales;
    }

    public void setCuotasSemanales(int cuotasSemanales) {
        this.cuotasSemanales = cuotasSemanales;
    }
}