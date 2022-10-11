package com.generation.ElianeFarmacia.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.ElianeFarmacia.model.Produtos;
import com.generation.ElianeFarmacia.repository.CategoriasRepository;
import com.generation.ElianeFarmacia.repository.ProdutosRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ProdutosController {

	@Autowired
	private ProdutosRepository produtosRepository;
	
	@Autowired
	private CategoriasRepository categoriasRepository;
	/*Para termos acesso aos Métodos das Classes Categorias e CategoriasController,
	precisamos inserir uma uma Injeção de Dependência da Classe Categorias, logo abaixo da uma Injeção
	de Dependência da Classe Produtos. Métodos Post e Put serão alterados devido ao relacionamento de tabelas.
	*/
	
	@GetMapping
	public ResponseEntity<List<Produtos>> getAll() //MySQL >> select*from tb_produtos
	{
		return ResponseEntity.ok(produtosRepository.findAll());
	}
	
	@GetMapping("/{id}") //MySQL >> select * from tb_produtos where id
	public ResponseEntity<Produtos> getById(@PathVariable Long id) 
	//PathVariable >>Esta anotação insere o valor enviado no endereço do endpoint, na Variável de Caminho {id}, no parâmetro do Método getById( Long id );

	{
		return produtosRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produtos>> getByTitulo(@PathVariable String nome) 
	{
		return ResponseEntity.ok(produtosRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	
	@GetMapping("/marca/{marca}") // para teste >> http://localhost:8080/produtos/marca/{marca}
	public ResponseEntity<List<Produtos>> getByMarca (@PathVariable String marca)
	{
		return ResponseEntity.ok(produtosRepository.findAllByMarcaContainingIgnoreCase(marca));
	}
	
	@GetMapping("/preco/{preco}") 
	public ResponseEntity<List<Produtos>> getByPreco (@PathVariable BigDecimal preco) //Caminho da variable
	{
		return ResponseEntity.ok(produtosRepository.findAllByPreco(preco));
	}
	
	
	@PostMapping // para testar >> http://localhost:8080/produtos
	public ResponseEntity<Produtos> post(@Valid @RequestBody Produtos produtos)	//Validar o corpo da requisição
	{	if(categoriasRepository.existsById(produtos.getCategorias().getId()))
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(produtosRepository.save(produtos));
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	} 
	
	@PutMapping
	public ResponseEntity<Produtos> put(@Valid @RequestBody Produtos produtos) /*esse Método possui um parâmetro, que é um Objeto da Classe Produtos, chamado produtos.*/
		{
		if (produtosRepository.existsById(produtos.getId())) {
			if (categoriasRepository.existsById(produtos.getCategorias().getId())) 
				return ResponseEntity.status(HttpStatus.OK)
				.body(produtosRepository.save(produtos));
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}") //URL >> http://localhost:8080/produtos/id
	public void delete(@PathVariable Long id) //No MySQL >> DELETE FROM tb_postagens WHERE id =	id; 
	{
		Optional<Produtos> produtos = produtosRepository.findById(id);
		/*Como o Método pode retornar um Objeto Nulo, utilizaremos o Optional para evitar o
		erro NullPointerException. Ao ao invés de utilizarmos o map com as Expressões Lambda,
		utilizaremos o Optional.*/
		
		if(produtos.isEmpty())
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		produtosRepository.deleteById(id);
		
	}	

}
