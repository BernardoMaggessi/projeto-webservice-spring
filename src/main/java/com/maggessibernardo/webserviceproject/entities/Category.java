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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

   
    public Category() {}

    
    public Category(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }


    
    public long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

   
    public Set<Product> getProducts() {
        return products;
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
        Category other = (Category) obj;
        return id == other.id;
    }

}