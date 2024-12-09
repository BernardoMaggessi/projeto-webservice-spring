package com.maggessibernardo.webserviceproject.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maggessibernardo.webserviceproject.entities.pk.OrderItemPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Classe que representa um item de uma ordem (pedido) no sistema.
 * 
 * Esta classe é mapeada como uma entidade JPA que corresponde à tabela "tb_order_item"
 * no banco de dados. Cada instância de {@link OrderItem} refere-se a um produto específico
 * dentro de um pedido, incluindo a quantidade e o preço do produto.
 * 
 * A classe faz uso de uma chave primária composta, representada pela classe {@link OrderItemPK},
 * que encapsula o identificador do pedido e do produto. Esse mapeamento de chave composta é
 * realizado através da anotação {@link EmbeddedId}.
 * 
 * O relacionamento entre {@link OrderItem} e {@link Product} é um relacionamento "muitos para um"
 * onde um produto pode estar em vários itens de pedido, e o pedido pode ter múltiplos produtos.
 * 
 * A classe implementa a interface {@link Serializable} para permitir sua serialização e
 * comunicação em rede, bem como persistência de dados.
 * 
 * @author BERNARDO MAGGESSI
 */
@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// Chave primária composta representada pela classe OrderItemPK
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();

	private Integer quantity;

	private double price;

	public OrderItem() {
	}
	
	public OrderItem(Order order, Product product, Integer quantity, double price) {
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Double getSubTotal() {
		return price * quantity;
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
}
