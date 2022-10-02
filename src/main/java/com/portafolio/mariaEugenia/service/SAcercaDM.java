/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mariaEugenia.service;

import com.portafolio.mariaEugenia.entity.AcercaDM;
import com.portafolio.mariaEugenia.repository.RAcercaDM;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SAcercaDM {
  @Autowired
    RAcercaDM rAcercaDM;
    
    public List<AcercaDM> list(){
        return rAcercaDM.findAll();
    }
    
    public Optional<AcercaDM> getOne(int id){
        return rAcercaDM.findById(id);
    }

    public Optional<AcercaDM> getByDescipcion(String descripcion){
        return rAcercaDM.findByDescripcion(descripcion);
    }
    
    public void save(AcercaDM acercaDM){
        rAcercaDM.save(acercaDM);
    }
    
    public void delete(int id){
        rAcercaDM.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rAcercaDM.existsById(id);
    }
    
    public boolean existsByDescripcion(String descripcion){
        return rAcercaDM.existsByDescripcion(descripcion);
    }   
}
