package com.generation.ElianeFarmacia.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produtos")
public class Produtos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo Nome é obrigatório!") //NotBlank mais utilizado para strings,mesma função do Not Null do Banco de Dados.
	@Size(min = 5, max = 100, message = "O atributo Nome deve conter no mínimo 04 e no máximo 100 caracteres")
	private String nome;
	
	@NotBlank(message = "O atributo Marca é obrigatório!") 
	@Size(min = 5, max = 100, message = "O atributo Marca deve conter no mínimo 04 e no máximo 100 caracteres")
	private String marca; //Size somente para string
	
	@NotBlank(message = "O atributo Informação Adicional é obrigatório!") 
	@Size(min = 5, max = 100, message = "O atributo Informação Adicional deve conter no mínimo 04 e no máximo 100 caracteres")
	private String info_add;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING) //transforma formato string mais apresentavel pro frontend
    @NotNull(message = "Preço é obrigatório!") //NotNull mais usado para numeros.
    @Positive(message = "O preço deve ser maior do que zero!") //setar para não receber valor negativo
	private BigDecimal preco;
	
	@ManyToOne //muitos produtos tem uma categoria
	@JsonIgnoreProperties("produtos")
	private Categorias categorias;
	
	
	//getters and setters	

	public Long getId() {
		return id;
	}

	public Categorias getCategorias() {
		return categorias;
	}

	public void setCategorias(Categorias categorias) {
		this.categorias = categorias;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getInfo_add() {
		return info_add;
	}

	public void setInfo_add(String info_add) {
		this.info_add = info_add;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	
	
}
