
package com.PorfolioAplicacion.demo.Repository;

import com.PorfolioAplicacion.demo.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BANGHO/Egerino
 */

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Integer>{
  public Optional<Persona> findByNombre(String nombre);
  public boolean existsByNombre(String nombre);
}
