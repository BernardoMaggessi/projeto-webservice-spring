package com.maggessibernardo.webserviceproject.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
/**
 * Classe que representa uma categoria de produtos no sistema.
 * 
 * Esta classe é mapeada como uma entidade JPA que corresponde à tabela "tb_category"
 * no banco de dados. Ela contém informações relacionadas à categoria, como o nome da
 * categoria e os produtos que pertencem a ela.
 * 
 * A categoria pode estar associada a múltiplos produtos, estabelecendo um relacionamento
 * "muitos para muitos" com a classe {@link Product}. O relacionamento é mapeado pela 
 * coleção de categorias na classe {@link Product}.
 * 
 * A classe implementa a interface {@link Serializable} para garantir que a instância da
 * entidade possa ser serializada e transferida entre sistemas ou persistida no banco de dados.
 * 
 * @author BERNARDO MAGGESSI
 */
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    // Atributo que representa o identificador único da categoria.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Atributo que armazena o nome da categoria.
    private String name;

    /**
     * Relacionamento "muitos para muitos" com a entidade {@link Product}.
     * Cada categoria pode ter múltiplos produtos associados a ela.
     * A associação é realizada por meio da coleção "categories" na classe {@link Product}.
     * O uso de @JsonIgnore evita que o JSON gerado para a categoria inclua os produtos
     * associados a ela, prevenindo recursões infinitas em caso de busca de categorias.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    /**
     * Construtor padrão (sem argumentos) necessário para o JPA.
     * O JPA requer um construtor público ou protegido sem argumentos para
     * realizar a instância da entidade.
     */
    public Category() {}

    /**
     * Construtor completo para a criação de uma instância de {@link Category} com
     * os valores passados como parâmetros.
     * 
     * @param id   Identificador único da categoria.
     * @param name Nome da categoria.
     */
    public Category(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    // Métodos Getters e Setters

    /**
     * Retorna o identificador único da categoria.
     * 
     * @return O identificador único da categoria.
     */
    public long getId() {
        return id;
    }

    /**
     * Define o identificador único da categoria.
     * 
     * @param id O identificador único da categoria a ser definido.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o nome da categoria.
     * 
     * @return O nome da categoria.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da categoria.
     * 
     * @param name O nome da categoria a ser definido.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o conjunto de produtos associados a esta categoria.
     * 
     * @return O conjunto de produtos associados à categoria.
     */
    public Set<Product> getProducts() {
        return products;
    }

    // Métodos hashCode e equals

    /**
     * Sobrescreve o método {@link Object#hashCode()} para garantir uma comparação
     * correta dos objetos {@link Category} com base no identificador único (id).
     * 
     * @return O código de hash calculado a partir do identificador da categoria.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Sobrescreve o método {@link Object#equals(Object)} para garantir que duas
     * categorias sejam consideradas iguais se tiverem o mesmo identificador único (id).
     * 
     * @param obj O objeto a ser comparado com a categoria atual.
     * @return Verdadeiro se as categorias forem iguais, caso contrário, falso.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Category other = (Category) obj;
        return id == other.id;
    }

}