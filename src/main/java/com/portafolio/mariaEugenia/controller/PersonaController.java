
package com.portafolio.mariaEugenia.controller;

import com.portafolio.mariaEugenia.dto.DtoPersona;
import com.portafolio.mariaEugenia.entity.Persona;
import com.portafolio.mariaEugenia.security.controller.Mensaje;
import com.portafolio.mariaEugenia.service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/personas")
@CrossOrigin(origins = "https://frontend-portafolio.web.app")

public class PersonaController {
   @Autowired 
   ImpPersonaService impPersonaService;
    @GetMapping("/lista")
      public ResponseEntity<List<Persona>> list(){
      List<Persona> list = impPersonaService.list();
      return new ResponseEntity(list, HttpStatus.OK);
  }  
      @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!impPersonaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Persona persona = impPersonaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    @PreAuthorize ("hasRole('ADMIN')")
     @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody DtoPersona dtoPersona){
      if(StringUtils.isBlank(dtoPersona.getNombre()))
          return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
      if(impPersonaService.existsByNombre(dtoPersona.getNombre()))
          return new ResponseEntity(new Mensaje("Esa persona existe"), HttpStatus.BAD_REQUEST);
      
    Persona persona = new Persona (dtoPersona.getNombre(), dtoPersona.getApellido(), dtoPersona.getImg());
    impPersonaService.save(persona);
    
    return new ResponseEntity(new Mensaje("Persona agregado"), HttpStatus.OK);
  }
     
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!impPersonaService.existsById(id)) {
        return new ResponseEntity(new Mensaje("La persona no existe"), HttpStatus.NOT_FOUND);
        }  
        impPersonaService.delete(id);
        return new ResponseEntity(new Mensaje("persona eliminada"), HttpStatus.OK);
    }
    
      @PreAuthorize("hasRole('ADMIN')")
      @PutMapping("/update/{id}")
     public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoPersona dtoPersona){
      //Validamos si existe el ID
      if(!impPersonaService.existsById(id))
          return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
      //Compara nombre de PROYECTO
      if(impPersonaService.existsByNombre(dtoPersona.getNombre()) && impPersonaService.getByNombre(dtoPersona.getNombre()).get().getId() != id)
          return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
      //No puede estar vacio
      if(StringUtils.isBlank(dtoPersona.getNombre()))
          return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
      
      Persona persona = impPersonaService.getOne(id).get();
      persona.setNombre(dtoPersona.getNombre());
      persona.setApellido(dtoPersona.getApellido());
      persona.setImg(dtoPersona.getImg());
  
      impPersonaService.save(persona);
      return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
   } 
  } 
