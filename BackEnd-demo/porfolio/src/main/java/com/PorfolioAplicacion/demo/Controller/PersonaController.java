package com.PorfolioAplicacion.demo.Controller;

import com.PorfolioAplicacion.demo.Dto.dtoPersona;
import com.PorfolioAplicacion.demo.Entity.Persona;
import com.PorfolioAplicacion.demo.Security.Controller.Mensaje;
import com.PorfolioAplicacion.demo.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://demofrontend-b30f4.web.app")
public class PersonaController {
    @Autowired
    ImpPersonaService personaService;
   
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id")int id){
        if(!personaService.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }//esta llave no estaba//
        
        Persona persona = personaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!personaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe ese ID"), HttpStatus.NOT_FOUND);
        }
        personaService.delete(id);
        return new ResponseEntity(new Mensaje("Persona eliminada"), HttpStatus.OK);
    }*/
    
    /*@PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtopersona){
        if(StringUtils.isBlank(dtopersona.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(personaService.existsByNombre(dtoedu.getNombre()))
            return new ResponseEntity(new Mensaje("Esa Persona ya existe"), HttpStatus.BAD_REQUEST);
        Persona persona = new Persona(dtopersona.getNombre(), dtopersona.getDescripcion());
        personaService.save(persona);
        
        return new ResponseEntity(new Mensaje("Persona Agregada"), HttpStatus.OK);
    }*/
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody dtoPersona dtopersona){
        // Para chequear si existe el ID
        if(!personaService.existsById(id)){
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        // Valida Persona (se puede eliminar validación)
        if(personaService.existsByNombre(dtopersona.getNombre()) && personaService.getByNombre(dtopersona.getNombre()).get().getId()!=id){
            return new ResponseEntity(new Mensaje("Esa Persona ya existe"), HttpStatus.BAD_REQUEST);
        }
        //Validacion no puede estar Vacio!
        if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }   
        
        Persona persona = personaService.getOne(id).get();
        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setDescripcion(dtopersona.getDescripcion());
        persona.setImg(dtopersona.getImg());
        
        personaService.save(persona);
        return new ResponseEntity(new Mensaje("Persona Actualizada"), HttpStatus.OK);
    }
    
}
