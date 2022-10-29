/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PorfolioAplicacion.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author BANGHO/Egerino
 */
@Entity
public class ExperienciaLaboral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreEL;
    private String descripcionEL;
    
    // Constructor 

    public ExperienciaLaboral() {
    }

    public ExperienciaLaboral(String nombreEL, String descripcionEL) {
        this.nombreEL = nombreEL;
        this.descripcionEL = descripcionEL;
    }
    
    // Getter y Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEL() {
        return nombreEL;
    }

    public void setNombreEL(String nombreEL) {
        this.nombreEL = nombreEL;
    }

    public String getDescripcionEL() {
        return descripcionEL;
    }

    public void setDescripcionEL(String descripcionEL) {
        this.descripcionEL = descripcionEL;
    }
    
}
