package com.maggessibernardo.webserviceproject.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maggessibernardo.webserviceproject.entities.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Classe que representa uma ordem (pedido) no sistema.
 * 
 * A classe é mapeada como uma entidade JPA que corresponde à tabela "tb_order"
 * no banco de dados. Ela contém informações relacionadas ao pedido realizado
 * pelo cliente, como o momento da criação do pedido, seu status e os itens
 * associados a ele.
 * 
 * A ordem está associada a um único cliente e pode conter múltiplos itens,
 * sendo esses itens representados por um relacionamento "muitos para muitos"
 * com a classe {@link OrderItem}.
 * 
 * A classe implementa a interface {@link Serializable} para permitir sua
 * serialização em processos de comunicação de rede e persistência de dados.
 * 
 * @author BERNARDO MAGGESSI
 */
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    // Atributo que representa o identificador único da ordem.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Atributo que armazena o momento em que a ordem foi criada. A data é
    // formatada no padrão ISO 8601 para garantir a compatibilidade com os
    // sistemas que consomem os dados via JSON.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    // Atributo que define o status atual da ordem. O valor é armazenado como
    // um número inteiro, sendo mapeado para o tipo enum {@link OrderStatus}.
    private Integer orderStatus;

    /**
     * Relacionamento "muitos para um" com a entidade {@link User}. Cada ordem
     * está associada a um único cliente.
     * A chave estrangeira "client_id" é automaticamente gerenciada pelo JPA.
     */
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
    
    /**
     * Relacionamento "um para muitos" com a entidade {@link OrderItem}. Cada
     * ordem pode ter múltiplos itens associados a ela.
     * A associação é realizada via chave composta (composta pela chave da ordem
     * e a chave do item), mapeada pelo atributo "id.order".
     */
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    /**
     * Construtor padrão (sem argumentos) necessário para o JPA.
     * O JPA requer um construtor público ou protegido sem argumentos para
     * realizar a instância da entidade.
     */
    public Order() {
    }

    /**
     * Construtor completo para a criação de uma instância de {@link Order} com
     * os valores passados como parâmetros. Este construtor facilita a criação
     * de ordens a partir de objetos já existentes.
     * 
     * @param id         Identificador único da ordem.
     * @param moment     Momento em que o pedido foi realizado.
     * @param orderStatus Status da ordem, representado pelo enum {@link OrderStatus}.
     * @param client     O cliente que realizou a ordem.
     */
    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        super();
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;
    }

    // Métodos Getters e Setters

    /**
     * Retorna o identificador único da ordem.
     * 
     * @return O identificador único da ordem.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador único da ordem.
     * 
     * @param id O identificador único da ordem a ser definido.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o momento (data e hora) em que a ordem foi criada.
     * 
     * @return O momento em que a ordem foi criada.
     */
    public Instant getMoment() {
        return moment;
    }

    /**
     * Define o momento (data e hora) em que a ordem foi criada.
     * 
     * @param moment O momento a ser definido.
     */
    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    /**
     * Retorna o cliente associado a esta ordem.
     * 
     * @return O cliente associado à ordem.
     */
    public User getClient() {
        return client;
    }

    /**
     * Define o cliente associado a esta ordem.
     * 
     * @param client O cliente a ser associado à ordem.
     */
    public void setClient(User client) {
        this.client = client;
    }

    /**
     * Retorna o status da ordem como um valor do tipo enum {@link OrderStatus}.
     * O valor do status é armazenado como um número inteiro, mas é convertido
     * para o tipo enum na hora da leitura.
     * 
     * @return O status da ordem como um valor do tipo {@link OrderStatus}.
     */
    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    /**
     * Define o status da ordem a partir de um valor do tipo enum {@link OrderStatus}.
     * O valor do status é convertido para um número inteiro para ser armazenado.
     * 
     * @param orderStatus O status a ser atribuído à ordem.
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus.getCode();
    }

    /**
     * Retorna o conjunto de itens associados a esta ordem.
     * 
     * @return O conjunto de itens da ordem.
     */
    public Set<OrderItem> getItems() {
        return items;
    }

    // Métodos hashCode e equals

    /**
     * Sobrescreve o método {@link Object#hashCode()} para garantir uma comparação
     * correta dos objetos {@link Order} com base no identificador único (id).
     * 
     * @return O código de hash calculado a partir do identificador da ordem.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Sobrescreve o método {@link Object#equals(Object)} para garantir que duas
     * ordens sejam consideradas iguais se tiverem o mesmo identificador único (id).
     * 
     * @param obj O objeto a ser comparado com a ordem atual.
     * @return Verdadeiro se as ordens forem iguais, caso contrário, falso.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        return Objects.equals(id, other.id);
    }

}