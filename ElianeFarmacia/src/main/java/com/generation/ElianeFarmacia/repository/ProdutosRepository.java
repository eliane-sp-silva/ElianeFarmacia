package com.generation.ElianeFarmacia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.ElianeFarmacia.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

	//ContainingIgnoreCase somente para string | Query Method 	
	//esse não é um método padrão então deve ser feito no repository.
	
	public List<Produtos> findAllByNomeContainingIgnoreCase(@Param ("nome") String nome); //MySQL >> select * from tb_produtos where nome like "%titulo%"
	public List<Produtos> findAllByMarcaContainingIgnoreCase(@Param ("marca") String marca); //Param >> define parametro de consulta
	public List<Produtos> findAllByPreco(@Param ("preco") BigDecimal preco);
	
	
}
