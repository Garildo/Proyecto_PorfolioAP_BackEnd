/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PorfolioAplicacion.demo.Controller;

import com.PorfolioAplicacion.demo.Dto.DtoExperienciaLaboral;
import com.PorfolioAplicacion.demo.Entity.ExperienciaLaboral;
import com.PorfolioAplicacion.demo.Security.Controller.Mensaje;
import com.PorfolioAplicacion.demo.Service.SExperienciaLaboral;
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
@RequestMapping("/explab")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://demofrontend-b30f4.web.app") 
public class CExperienciaLaboral {
    @Autowired
    SExperienciaLaboral sExperienciaLaboral;
    
    @GetMapping("/lista")
    public ResponseEntity<List<ExperienciaLaboral>> list(){
       List<ExperienciaLaboral> list = sExperienciaLaboral.list();
       return new ResponseEntity(list, HttpStatus.OK); 
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<ExperienciaLaboral> getById(@PathVariable("id")int id){
        if(!sExperienciaLaboral.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }//esta llave no estaba//
        ExperienciaLaboral experiencia = sExperienciaLaboral.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        // Para chequear que el Id existe e eliminarlo
        if(!sExperienciaLaboral.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        sExperienciaLaboral.delete(id);
        
        return new ResponseEntity(new Mensaje("Exp. Laboral eliminada con existo!"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoExperienciaLaboral dtoexp){
        if(StringUtils.isBlank(dtoexp.getNombreEL()))
            return new ResponseEntity(new Mensaje("El nombre es Obligatoriio"), HttpStatus.BAD_REQUEST);
        if(sExperienciaLaboral.existsByNombreEL(dtoexp.getNombreEL()))
            return new ResponseEntity(new Mensaje("Esa Experiencia ya existe"), HttpStatus.BAD_REQUEST);
        ExperienciaLaboral experiencia = new ExperienciaLaboral(dtoexp.getNombreEL(), dtoexp.getDescripcionEL());
        sExperienciaLaboral.save(experiencia);
        
        return new ResponseEntity(new Mensaje("Experiencia Laboral Egregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody DtoExperienciaLaboral dtoexp){
        // Para chequear si existe el ID
        if(!sExperienciaLaboral.existsById(id)){
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        // Valida Experiencias Laborales (se puede eliminar validación)
        if(sExperienciaLaboral.existsByNombreEL(dtoexp.getNombreEL()) && sExperienciaLaboral.getByNombreEL(dtoexp.getNombreEL()).get().getId()!=id){
            return new ResponseEntity(new Mensaje("Esa Experiencia Laboral ya existe"), HttpStatus.BAD_REQUEST);
        }
        //Validacion no puede estar Vacio!
        if(StringUtils.isBlank(dtoexp.getNombreEL())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        ExperienciaLaboral experiencia = sExperienciaLaboral.getOne(id).get();
        experiencia.setNombreEL(dtoexp.getNombreEL());
        experiencia.setDescripcionEL(dtoexp.getDescripcionEL());
        
        sExperienciaLaboral.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia Laboral Actualizada"), HttpStatus.OK);
    }

}
