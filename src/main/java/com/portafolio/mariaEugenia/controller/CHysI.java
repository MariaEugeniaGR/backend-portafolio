/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mariaEugenia.controller;

import com.portafolio.mariaEugenia.dto.DtoHysI;
import com.portafolio.mariaEugenia.entity.HysI;
import com.portafolio.mariaEugenia.security.controller.Mensaje;
import com.portafolio.mariaEugenia.service.SHysI;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skilli")
@CrossOrigin(origins = "http://localhost:4200")
public class CHysI {
 @Autowired
    SHysI sHysI;
    
    @GetMapping("/lista")
  public ResponseEntity<List<HysI>> list(){
      List<HysI> list = sHysI.list();
      return new ResponseEntity(list, HttpStatus.OK);
  }  
  
   @GetMapping("/detail/{id}")
    public ResponseEntity<HysI> getById(@PathVariable("id") int id){
        if(!sHysI.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        HysI hysI = sHysI.getOne(id).get();
        return new ResponseEntity(hysI, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sHysI.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sHysI.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
    
  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody DtoHysI dtohysI){
      if(StringUtils.isBlank(dtohysI.getNombre()))
          return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
      if(sHysI.existsByNombre(dtohysI.getNombre()))
          return new ResponseEntity(new Mensaje("Esa Skill ya existe"), HttpStatus.BAD_REQUEST);
      
    HysI hysI = new HysI(dtohysI.getNombre(), dtohysI.getPorcentaje(), dtohysI.getImagen());
    sHysI.save(hysI);
    
    return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
  }
  
  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoHysI dtohysI){
      //Validamos si existe el ID
      if(!sHysI.existsById(id))
          return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
      //Compara nombre de skill
      if(sHysI.existsByNombre(dtohysI.getNombre()) && sHysI.getByNombre(dtohysI.getNombre()).get().getId() != id)
          return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
      //No puede estar vacio
      if(StringUtils.isBlank(dtohysI.getNombre()))
          return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
      
      
      HysI hysI = sHysI.getOne(id).get();
      hysI.setNombre(dtohysI.getNombre());
      hysI.setPorcentaje(dtohysI.getPorcentaje());
      hysI.setImagen(dtohysI.getImagen());
  
      sHysI.save(hysI);
      return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
  }   
}
