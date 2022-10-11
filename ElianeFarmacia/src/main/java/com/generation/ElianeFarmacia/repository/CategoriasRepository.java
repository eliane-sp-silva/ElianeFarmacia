package com.generation.ElianeFarmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.ElianeFarmacia.model.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {

	//ContainingIgnoreCase somente para string
	public List<Categorias>findByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);

}
