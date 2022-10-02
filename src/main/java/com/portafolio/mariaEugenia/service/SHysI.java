/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mariaEugenia.service;

import com.portafolio.mariaEugenia.entity.HysI;
import com.portafolio.mariaEugenia.repository.RHysI;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SHysI {
     @Autowired
    RHysI rHysI;
    
    public List<HysI> list(){
        return rHysI.findAll();
    }
    
    public Optional<HysI> getOne(int id){
        return rHysI.findById(id);
    }

    public Optional<HysI> getByNombre(String nombre){
        return rHysI.findByNombre(nombre);
    }
    
    public void save(HysI skillI){
        rHysI.save(skillI);
    }
    
    public void delete(int id){
        rHysI.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rHysI.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return rHysI.existsByNombre(nombre);
    }     
}
