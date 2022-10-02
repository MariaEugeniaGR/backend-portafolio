/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portafolio.mariaEugenia.repository;

import com.portafolio.mariaEugenia.entity.HysI;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RHysI extends JpaRepository<HysI, Integer>{
    Optional<HysI> findByNombre (String nombre);
    public boolean existsByNombre (String nombre);
}
