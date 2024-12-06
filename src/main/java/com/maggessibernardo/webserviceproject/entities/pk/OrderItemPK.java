package com.maggessibernardo.webserviceproject.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import com.maggessibernardo.webserviceproject.entities.Order;
import com.maggessibernardo.webserviceproject.entities.Product;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Classe que representa a chave primária composta para a entidade {@link OrderItem}.
 * 
 * Esta classe é utilizada como chave composta para a tabela "tb_order_item",
 * composta por duas colunas: "order_id" (referente ao pedido) e "product_id" 
 * (referente ao produto). Ambas são associadas às entidades {@link Order} e 
 * {@link Product}, respectivamente.
 * 
 * A chave composta é necessária quando há uma relação de muitos para muitos 
 * entre as entidades {@link Order} e {@link Product}, sendo representada por 
 * uma tabela intermediária {@link OrderItem} que contém essa chave composta.
 * 
 * A classe é marcada como {@link Embeddable} para ser incorporada na 
 * classe {@link OrderItem}.
 * 
 * @author BERNARDO MAGGESSI
 */
@Embeddable
public class OrderItemPK implements Serializable {
	
	// Atributo serialVersionUID necessário para garantir a consistência 
	// da serialização em diferentes versões da classe.
	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne
	@JoinColumn(name = "order_id")  // Chave estrangeira que referencia o pedido
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id")  // Chave estrangeira que referencia o produto
	private Product product;

	
	public Order getOrder() {
		return order;
	}

	
	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	
	public void setProduct(Product product) {
		this.product = product;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}
}
