/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PorfolioAplicacion.demo.Controller;

import com.PorfolioAplicacion.demo.Dto.dtoEducacion;
import com.PorfolioAplicacion.demo.Entity.Educacion;
import com.PorfolioAplicacion.demo.Security.Controller.Mensaje;
import com.PorfolioAplicacion.demo.Service.Seducacion;
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

/**
 *
 * @author BANGHO/Egerino
 */
@RestController
@RequestMapping("/educacion")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://demofrontend-b30f4.web.app")
public class CEducacion {
    @Autowired
    Seducacion sEducacion;
   
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id")int id){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }//esta llave no estaba//
        Educacion educacion = sEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe ese ID"), HttpStatus.NOT_FOUND);
        }
        sEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoedu){
        if(StringUtils.isBlank(dtoedu.getNombreEd()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sEducacion.existsByNombreEd(dtoedu.getNombreEd()))
            return new ResponseEntity(new Mensaje("Esa Educacion ya existe"), HttpStatus.BAD_REQUEST);
        Educacion educacion = new Educacion(dtoedu.getNombreEd(), dtoedu.getDescripcionEd());
        sEducacion.save(educacion);
        
        return new ResponseEntity(new Mensaje("Educacion Agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody dtoEducacion dtoedu){
        // Para chequear si existe el ID
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        // Valida Educacion (se puede eliminar validación)
        if(sEducacion.existsByNombreEd(dtoedu.getNombreEd()) && sEducacion.getByNombreEd(dtoedu.getNombreEd()).get().getId()!=id){
            return new ResponseEntity(new Mensaje("Esa Educación ya existe"), HttpStatus.BAD_REQUEST);
        }
        //Validacion no puede estar Vacio!
        if(StringUtils.isBlank(dtoedu.getNombreEd())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }   
        
        Educacion educacion = sEducacion.getOne(id).get();
        educacion.setNombreEd(dtoedu.getNombreEd());
        educacion.setDescripcionEd(dtoedu.getDescripcionEd());
        
        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Educación Actualizada"), HttpStatus.OK);
    }
    
}
