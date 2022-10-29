/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PorfolioAplicacion.demo.Dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author BANGHO/Egerino
 */

public class DtoExperienciaLaboral {
    @NotBlank
    private String nombreEL;
    @NotBlank
    private String descripcionEL;
    
    // Constructor

    public DtoExperienciaLaboral() {
    }

    public DtoExperienciaLaboral(String nombreEL, String descripcionEL) {
        this.nombreEL = nombreEL;
        this.descripcionEL = descripcionEL;
    }
    
    // Getter y Seter

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
