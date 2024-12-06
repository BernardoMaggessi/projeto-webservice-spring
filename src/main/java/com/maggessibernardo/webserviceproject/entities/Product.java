package com.maggessibernardo.webserviceproject.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Classe que representa um produto no sistema.
 * 
 * A classe é mapeada como uma entidade JPA que corresponde à tabela "tb_product"
 * no banco de dados. Cada instância de {@link Product} contém informações sobre
 * um produto específico, como nome, descrição, preço e URL da imagem.
 * 
 * O relacionamento entre {@link Product} e {@link Category} é um relacionamento
 * "muitos para muitos", onde um produto pode pertencer a várias categorias e
 * uma categoria pode conter vários produtos. Esse relacionamento é mapeado por
 * meio da tabela de junção "tb_product_category".
 * 
 * A classe implementa a interface {@link Serializable} para permitir sua
 * serialização em processos de comunicação de rede e persistência de dados.
 * 
 * @author BERNARDO MAGGESSI
 */
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Nome do produto
	private String name;

	// Descrição do produto
	private String description;

	// Preço do produto
	private Double price;

	// URL da imagem do produto
	private String imgUrl;

	
	@ManyToMany
	@JoinTable(name = "tb_product_category",
		joinColumns = @JoinColumn(name = "product_id"),  // Chave estrangeira para o produto
		inverseJoinColumns = @JoinColumn(name = "category_id")) // Chave estrangeira para a categoria
	private Set<Category> categories = new HashSet<>();

	
	public Product() {
	}

	
	
	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	
	public long getId() {
		return id;
	}

	
	public void setId(long id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	
	public Set<Category> getCategories() {
		return categories;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return id == other.id;
	}
}
