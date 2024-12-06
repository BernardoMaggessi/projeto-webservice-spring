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

	// Identificador único do produto (chave primária)
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

	// Relacionamento muitos para muitos com a entidade Category
	@ManyToMany
	@JoinTable(name = "tb_product_category",
		joinColumns = @JoinColumn(name = "product_id"),  // Chave estrangeira para o produto
		inverseJoinColumns = @JoinColumn(name = "category_id")) // Chave estrangeira para a categoria
	private Set<Category> categories = new HashSet<>();

	/**
	 * Construtor padrão (sem argumentos) necessário para o JPA.
	 * O JPA requer um construtor público ou protegido sem argumentos para
	 * realizar a instância da entidade.
	 */
	public Product() {
	}

	/**
	 * Construtor completo para criar um produto com todos os parâmetros necessários.
	 * 
	 * @param id O identificador único do produto.
	 * @param name O nome do produto.
	 * @param description A descrição do produto.
	 * @param price O preço do produto.
	 * @param imgUrl A URL da imagem do produto.
	 */
	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	/**
	 * Retorna o identificador único do produto.
	 * 
	 * @return O identificador único do produto.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Define o identificador único do produto.
	 * 
	 * @param id O identificador do produto a ser definido.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Retorna o nome do produto.
	 * 
	 * @return O nome do produto.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define o nome do produto.
	 * 
	 * @param name O nome do produto a ser definido.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retorna a descrição do produto.
	 * 
	 * @return A descrição do produto.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Define a descrição do produto.
	 * 
	 * @param description A descrição do produto a ser definida.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Retorna o preço do produto.
	 * 
	 * @return O preço do produto.
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Define o preço do produto.
	 * 
	 * @param price O preço do produto a ser definido.
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Retorna a URL da imagem do produto.
	 * 
	 * @return A URL da imagem do produto.
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * Define a URL da imagem do produto.
	 * 
	 * @param imgUrl A URL da imagem do produto a ser definida.
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * Retorna as categorias associadas a este produto.
	 * 
	 * @return O conjunto de categorias associadas a este produto.
	 */
	public Set<Category> getCategories() {
		return categories;
	}

	// Métodos hashCode e equals

	/**
	 * Sobrescreve o método {@link Object#hashCode()} para garantir uma comparação
	 * correta dos objetos {@link Product} com base no identificador único (id).
	 * 
	 * @return O código de hash calculado a partir do identificador do produto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Sobrescreve o método {@link Object#equals(Object)} para garantir que dois
	 * produtos sejam considerados iguais se tiverem o mesmo identificador único (id).
	 * 
	 * @param obj O objeto a ser comparado com o produto atual.
	 * @return Verdadeiro se os produtos forem iguais, caso contrário, falso.
	 */
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
