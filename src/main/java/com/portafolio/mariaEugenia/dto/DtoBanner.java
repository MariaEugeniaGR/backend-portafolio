/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mariaEugenia.dto;

import javax.validation.constraints.NotBlank;


public class DtoBanner {
    @NotBlank
    private String imagenB;

    public DtoBanner() {
    }

    public DtoBanner(String imagenB) {
        this.imagenB = imagenB;
    }

    public String getImagenB() {
        return imagenB;
    }

    public void setImagenB(String imagenB) {
        this.imagenB = imagenB;
    }
}
