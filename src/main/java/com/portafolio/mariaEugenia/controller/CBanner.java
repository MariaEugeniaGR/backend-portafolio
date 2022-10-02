/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mariaEugenia.controller;

import com.portafolio.mariaEugenia.dto.DtoBanner;
import com.portafolio.mariaEugenia.entity.Banner;
import com.portafolio.mariaEugenia.security.controller.Mensaje;
import com.portafolio.mariaEugenia.service.SBanner;
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
@RequestMapping("/banner")
@CrossOrigin(origins = "**")
public class CBanner {
 @Autowired
    SBanner sBanner;
    
    @GetMapping("/lista")
  public ResponseEntity<List<Banner>> list(){
      List<Banner> list = sBanner.list();
      return new ResponseEntity(list, HttpStatus.OK);
  }  
  
   @GetMapping("/detail/{id}")
    public ResponseEntity<Banner> getById(@PathVariable("id") int id){
        if(!sBanner.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Banner banner = sBanner.getOne(id).get();
        return new ResponseEntity(banner, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sBanner.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sBanner.delete(id);
        return new ResponseEntity(new Mensaje("banner eliminado"), HttpStatus.OK);
    }   
    
 @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody DtoBanner dtoBanner){
      if(StringUtils.isBlank(dtoBanner.getImagenB()))
          return new ResponseEntity(new Mensaje("La URL es obligatorio"), HttpStatus.BAD_REQUEST);
      if(sBanner.existsByImagenB(dtoBanner.getImagenB()))
          return new ResponseEntity(new Mensaje("Ese banner existe"), HttpStatus.BAD_REQUEST);
      
    Banner banner = new Banner(dtoBanner.getImagenB());
    sBanner.save(banner);
    
    return new ResponseEntity(new Mensaje("Banner agregado"), HttpStatus.OK);
  }
  
  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoBanner dtoBanner){
      //Validamos si existe el ID
      if(!sBanner.existsById(id))
          return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
      //No puede estar vacio
      if(StringUtils.isBlank(dtoBanner.getImagenB()))
          return new ResponseEntity(new Mensaje("La URL es obligatorio"), HttpStatus.BAD_REQUEST);
      
      Banner banner = sBanner.getOne(id).get();
      banner.setImagenB(dtoBanner.getImagenB());
  
      sBanner.save(banner);
      return new ResponseEntity(new Mensaje("Banner actualizados"), HttpStatus.OK);
  }    
}
