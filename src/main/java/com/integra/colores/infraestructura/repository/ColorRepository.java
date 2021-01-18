package com.integra.colores.infraestructura.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.integra.colores.dominio.entidad.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
	
	@Query("SELECT c.id, c.name, c.color FROM Color c")
	public List<Object[]> listarColores();
	
	@Query("SELECT c.id, c.name, c.color FROM Color c")
	public Page<Object[]> listarColoresPorPagina(Pageable pagina);
	
//	@Query("SELECT c.id, c.name, c.color FROM Color c")
//	public Page<Object[]> listarColoresPorPagina(Pageable pagina);

}
