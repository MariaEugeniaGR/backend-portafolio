/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portafolio.mariaEugenia.repository;

import com.portafolio.mariaEugenia.entity.Hys;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface RHys extends JpaRepository<Hys, Integer>{
    Optional<Hys> findByNombre (String nombre);
    public boolean existsByNombre (String nombre);
}
