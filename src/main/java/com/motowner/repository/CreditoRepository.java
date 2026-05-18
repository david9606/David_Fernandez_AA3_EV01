package com.motowner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.motowner.model.Credito;

/**
 * ESTÁNDAR DE CODIFICACIÓN - CAPA DE PERSISTENCIA (REPOSITORIO)
 * Esta interfaz hereda todos los métodos de JpaRepository (CRUD).
 * Spring Boot implementará automáticamente las consultas a MySQL en tiempo de ejecución.
 * * @author David Fernandez
 * @version 1.0
 */
@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {
    // Al heredar de JpaRepository, ya ganamos métodos como:
    // .save(credito)   --> Para guardar simulaciones en MySQL
    // .findAll()       --> Para traer la lista de todas las simulaciones hechas
}