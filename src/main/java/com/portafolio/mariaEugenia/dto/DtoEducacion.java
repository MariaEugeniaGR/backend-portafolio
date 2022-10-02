/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mariaEugenia.dto;

import javax.validation.constraints.NotBlank;


public class DtoEducacion {
    @NotBlank
    private String nombreE;
    @NotBlank
    private String fechaI;
    @NotBlank
    private String fechaF;
    @NotBlank
    private String descripcionE;
    @NotBlank
    private String imagenE;
     

    public DtoEducacion() {
    }

    public DtoEducacion(String nombreE, String fechaI, String fechaF, String descripcionE, String imagenE) {
        this.nombreE = nombreE;
        this.fechaI = fechaI;
        this.fechaF = fechaF;
        this.descripcionE = descripcionE;
        this.imagenE = imagenE;
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public String getFechaI() {
        return fechaI;
    }

    public void setFechaI(String fechaI) {
        this.fechaI = fechaI;
    }

    public String getFechaF() {
        return fechaF;
    }

    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
    }

    public String getDescripcionE() {
        return descripcionE;
    }

    public void setDescripcionE(String descripcionE) {
        this.descripcionE = descripcionE;
    }

    public String getImagenE() {
        return imagenE;
    }

    public void setImagenE(String imagenE) {
        this.imagenE = imagenE;
    }
}
