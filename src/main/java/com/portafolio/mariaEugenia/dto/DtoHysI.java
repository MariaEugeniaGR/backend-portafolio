/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mariaEugenia.dto;

import javax.validation.constraints.NotBlank;


public class DtoHysI {
    @NotBlank
    private String nombre;
    @NotBlank
    private int porcentaje;
     @NotBlank
    private String imagen;

    public DtoHysI() {
    }

    public DtoHysI(String nombre, int porcentaje, String imagen) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    

 
}
