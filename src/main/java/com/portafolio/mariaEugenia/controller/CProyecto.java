/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mariaEugenia.controller;

import com.portafolio.mariaEugenia.dto.DtoProyecto;
import com.portafolio.mariaEugenia.entity.Proyecto;
import com.portafolio.mariaEugenia.security.controller.Mensaje;
import com.portafolio.mariaEugenia.service.SProyecto;
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
@RequestMapping("/proyecto")
@CrossOrigin(origins = "*")

public class CProyecto {
    @Autowired
    SProyecto sProyecto;
    
    @GetMapping("/lista")
  public ResponseEntity<List<Proyecto>> list(){
      List<Proyecto> list = sProyecto.list();
      return new ResponseEntity(list, HttpStatus.OK);
  }  
  
   @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id){
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proyecto proyecto = sProyecto.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sProyecto.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sProyecto.delete(id);
        return new ResponseEntity(new Mensaje("proyecto eliminado"), HttpStatus.OK);
    }
    
  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody DtoProyecto dtoProyecto){
      if(StringUtils.isBlank(dtoProyecto.getNombreP()))
          return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
      if(sProyecto.existsByNombreP(dtoProyecto.getNombreP()))
          return new ResponseEntity(new Mensaje("Esa experiencia existe"), HttpStatus.BAD_REQUEST);
      
    Proyecto proyecto = new Proyecto(dtoProyecto.getNombreP(), dtoProyecto.getDescripcionP(), dtoProyecto.getImagenP());
    sProyecto.save(proyecto);
    
    return new ResponseEntity(new Mensaje("Proyecto agregado"), HttpStatus.OK);
  }
  
  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProyecto dtoProyecto){
      //Validamos si existe el ID
      if(!sProyecto.existsById(id))
          return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
      //Compara nombre de PROYECTO
      if(sProyecto.existsByNombreP(dtoProyecto.getNombreP()) && sProyecto.getByNombreP(dtoProyecto.getNombreP()).get().getId() != id)
          return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
      //No puede estar vacio
      if(StringUtils.isBlank(dtoProyecto.getNombreP()))
          return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
      
      Proyecto proyecto = sProyecto.getOne(id).get();
      proyecto.setNombreP(dtoProyecto.getNombreP());
      proyecto.setDescripcionP(dtoProyecto.getDescripcionP());
      proyecto.setImagenP(dtoProyecto.getImagenP());
  
      sProyecto.save(proyecto);
      return new ResponseEntity(new Mensaje("Proyectos actualizados"), HttpStatus.OK);
  } 
}
