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
	
	// Relacionamento muitos para um com a entidade Order (um pedido pode ter muitos itens)
	@ManyToOne
	@JoinColumn(name = "order_id")  // Chave estrangeira que referencia o pedido
	private Order order;

	// Relacionamento muitos para um com a entidade Product (um item de pedido pode ter um produto)
	@ManyToOne
	@JoinColumn(name = "product_id")  // Chave estrangeira que referencia o produto
	private Product product;

	/**
	 * Retorna o pedido associado a este item de pedido.
	 * 
	 * @return O pedido associado a este item de pedido.
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * Define o pedido associado a este item de pedido.
	 * 
	 * @param order O pedido a ser associado ao item de pedido.
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * Retorna o produto associado a este item de pedido.
	 * 
	 * @return O produto associado a este item de pedido.
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Define o produto associado a este item de pedido.
	 * 
	 * @param product O produto a ser associado ao item de pedido.
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * Sobrescreve o método {@link Object#hashCode()} para garantir uma comparação
	 * correta das chaves compostas com base nos atributos de {@link Order} e 
	 * {@link Product}.
	 * 
	 * @return O código de hash calculado a partir dos campos order e product.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}

	/**
	 * Sobrescreve o método {@link Object#equals(Object)} para garantir que duas
	 * chaves compostas sejam consideradas iguais se tiverem os mesmos valores
	 * para order e product.
	 * 
	 * @param obj O objeto a ser comparado com a chave composta atual.
	 * @return Verdadeiro se as chaves compostas forem iguais, caso contrário, falso.
	 */
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
