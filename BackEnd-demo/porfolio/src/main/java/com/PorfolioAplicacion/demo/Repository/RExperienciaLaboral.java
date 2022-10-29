/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PorfolioAplicacion.demo.Repository;

import com.PorfolioAplicacion.demo.Entity.ExperienciaLaboral;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BANGHO/Egerino
 */
@Repository
public interface RExperienciaLaboral extends JpaRepository<ExperienciaLaboral, Integer>{
    public Optional<ExperienciaLaboral> findByNombreEL(String nombreEL);
    
    public boolean existsByNombreEL(String nombreEL);
}
