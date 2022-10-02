/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portafolio.mariaEugenia.repository;

import com.portafolio.mariaEugenia.entity.AcercaDM;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RAcercaDM extends JpaRepository<AcercaDM, Integer>{
    public Optional<AcercaDM> findByDescripcion(String descripcion);
    public boolean existsByDescripcion(String descripcion);  
}
