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

	// Quantidade do produto no pedido
	private Integer quantity;

	// Preço unitário do produto
	private double price;

	/**
	 * Construtor padrão (sem argumentos) necessário para o JPA.
	 * O JPA requer um construtor público ou protegido sem argumentos para
	 * realizar a instância da entidade.
	 */
	public OrderItem() {
	}
	
	/**
	 * Construtor completo para a criação de um item de pedido com os valores passados como parâmetros.
	 * 
	 * @param order O pedido ao qual o item pertence.
	 * @param product O produto associado ao item do pedido.
	 * @param quantity A quantidade do produto no pedido.
	 * @param price O preço unitário do produto.
	 */
	public OrderItem(Order order, Product product, Integer quantity, double price) {
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}

	/**
	 * Retorna o pedido ao qual o item está associado.
	 * 
	 * @return O pedido associado a este item.
	 */
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}

	/**
	 * Define o pedido ao qual o item está associado.
	 * 
	 * @param order O pedido a ser associado a este item.
	 */
	public void setOrder(Order order) {
		id.setOrder(order);
	}

	/**
	 * Retorna o produto associado a este item do pedido.
	 * 
	 * @return O produto associado ao item.
	 */
	public Product getProduct() {
		return id.getProduct();
	}

	/**
	 * Define o produto associado a este item do pedido.
	 * 
	 * @param product O produto a ser associado ao item.
	 */
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	/**
	 * Retorna a quantidade do produto no pedido.
	 * 
	 * @return A quantidade do produto no pedido.
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * Define a quantidade do produto no pedido.
	 * 
	 * @param quantity A quantidade do produto no pedido a ser definida.
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * Retorna o preço unitário do produto no pedido.
	 * 
	 * @return O preço unitário do produto.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Define o preço unitário do produto no pedido.
	 * 
	 * @param price O preço unitário do produto a ser definido.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Calcula o subtotal deste item no pedido, ou seja, o preço total
	 * do item considerando sua quantidade e o preço unitário.
	 * 
	 * @return O valor total do item (preço * quantidade).
	 */
	public Double getSubTotal() {
		return price * quantity;
	}

	// Métodos hashCode e equals

	/**
	 * Sobrescreve o método {@link Object#hashCode()} para garantir uma comparação
	 * correta dos objetos {@link OrderItem} com base na chave primária composta (id).
	 * 
	 * @return O código de hash calculado a partir do identificador do item de pedido.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Sobrescreve o método {@link Object#equals(Object)} para garantir que dois
	 * itens de pedido sejam considerados iguais se tiverem a mesma chave primária (id).
	 * 
	 * @param obj O objeto a ser comparado com o item de pedido atual.
	 * @return Verdadeiro se os itens de pedido forem iguais, caso contrário, falso.
	 */
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
