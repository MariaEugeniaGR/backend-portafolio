/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mariaEugenia.controller;

import com.portafolio.mariaEugenia.dto.DtoAcercaDM;
import com.portafolio.mariaEugenia.entity.AcercaDM;
import com.portafolio.mariaEugenia.security.controller.Mensaje;
import com.portafolio.mariaEugenia.service.SAcercaDM;
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
@RequestMapping("/acercadm")
@CrossOrigin(origins = "https://frontend-portafolio.web.app")
public class CAcercaDM {
    @Autowired
    SAcercaDM sAcercaDM;
    
    @GetMapping("/lista")
  public ResponseEntity<List<AcercaDM>> list(){
      List<AcercaDM> list = sAcercaDM.list();
      return new ResponseEntity(list, HttpStatus.OK);
  }  
  
   @GetMapping("/detail/{id}")
    public ResponseEntity<AcercaDM> getById(@PathVariable("id") int id){
        if(!sAcercaDM.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        AcercaDM acercaDM = sAcercaDM.getOne(id).get();
        return new ResponseEntity(acercaDM, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sAcercaDM.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sAcercaDM.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
    
  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody DtoAcercaDM dtoacercaDM){
      if(StringUtils.isBlank(dtoacercaDM.getDescripcion()))
          return new ResponseEntity(new Mensaje("La Descripcion es obligatorio"), HttpStatus.BAD_REQUEST);
      
    AcercaDM acercaDM = new AcercaDM(dtoacercaDM.getDescripcion());
    sAcercaDM.save(acercaDM);
    
    return new ResponseEntity(new Mensaje("descripcion agregada"), HttpStatus.OK);
  }
  
  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoAcercaDM dtoacercaDM){
      //Validamos si existe el ID
      if(!sAcercaDM.existsById(id))
          return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
      //No puede estar vacio
      if(StringUtils.isBlank(dtoacercaDM.getDescripcion()))
          return new ResponseEntity(new Mensaje("La descripcion es obligatorio"), HttpStatus.BAD_REQUEST);
      
      AcercaDM acercaDM = sAcercaDM.getOne(id).get();
        acercaDM.setDescripcion((dtoacercaDM.getDescripcion()));
  
      sAcercaDM.save(acercaDM);
      return new ResponseEntity(new Mensaje("Descripcion actualizada"), HttpStatus.OK);
  }  
}
