/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PorfolioAplicacion.demo.Service;

import com.PorfolioAplicacion.demo.Entity.ExperienciaLaboral;
import com.PorfolioAplicacion.demo.Repository.RExperienciaLaboral;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author BANGHO
 */
@Service
@Transactional
public class SExperienciaLaboral {
     @Autowired
    RExperienciaLaboral rExperienciaLaboral;
    
    public List<ExperienciaLaboral> list(){
        return rExperienciaLaboral.findAll();
    }
    
    public Optional<ExperienciaLaboral> getOne (int id){
        return rExperienciaLaboral.findById(id);
    }
    
    public Optional<ExperienciaLaboral> getByNombreEL(String nombreEL){
        return rExperienciaLaboral.findByNombreEL(nombreEL);
    }
    
    public void save(ExperienciaLaboral expe){
        rExperienciaLaboral.save(expe);
    }
    
    public void delete(int id){
        rExperienciaLaboral.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rExperienciaLaboral.existsById(id);
    }
    
    public boolean existsByNombreEL(String nombreEL){
        return rExperienciaLaboral.existsByNombreEL(nombreEL);
    }
}

