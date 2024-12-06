package com.maggessibernardo.webserviceproject.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Classe que representa um usuário no sistema.
 * 
 * A classe é mapeada como uma entidade JPA que corresponde à tabela "tb_user"
 * no banco de dados. Ela contém informações relacionadas ao usuário, como nome,
 * e-mail, telefone e senha, além de um relacionamento com a entidade {@link Order}.
 * 
 * O usuário pode ter múltiplos pedidos associados, sendo este relacionamento 
 * mapeado como um para muitos entre {@link User} e {@link Order}.
 * 
 * A classe implementa a interface {@link Serializable} para permitir sua
 * serialização em processos de comunicação de rede e persistência de dados.
 * 
 * @author BERNARDO MAGGESSI
 */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
	
	// Atributo serialVersionUID necessário para garantir a consistência 
	// da serialização em diferentes versões da classe.
	private static final long serialVersionUID = 1L;
	
	// Identificador único do usuário (chave primária)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Nome do usuário
	private String name;

	// E-mail do usuário
	private String email;

	// Telefone do usuário
	private String phone;

	// Senha do usuário
	private String password;

	// Relacionamento um para muitos com a entidade Order (um usuário pode ter muitos pedidos)
	@JsonIgnore  // A anotação evita que os pedidos do usuário sejam serializados automaticamente
	@OneToMany(mappedBy="client")
	private List<Order> orders = new ArrayList<>();  // Instanciando a lista de pedidos

	/**
	 * Construtor padrão (sem argumentos) necessário para o JPA.
	 * O JPA requer um construtor público ou protegido sem argumentos para
	 * realizar a instância da entidade.
	 */
	public User() {}

	/**
	 * Construtor completo para criar um usuário com todos os parâmetros necessários.
	 * 
	 * @param id O identificador único do usuário.
	 * @param name O nome do usuário.
	 * @param email O e-mail do usuário.
	 * @param phone O telefone do usuário.
	 * @param password A senha do usuário.
	 */
	public User(Long id, String name, String email, String phone, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	/**
	 * Retorna o identificador único do usuário.
	 * 
	 * @return O identificador único do usuário.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o identificador único do usuário.
	 * 
	 * @param id O identificador único do usuário a ser definido.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retorna o nome do usuário.
	 * 
	 * @return O nome do usuário.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define o nome do usuário.
	 * 
	 * @param name O nome do usuário a ser definido.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retorna o e-mail do usuário.
	 * 
	 * @return O e-mail do usuário.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Define o e-mail do usuário.
	 * 
	 * @param email O e-mail do usuário a ser definido.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retorna o telefone do usuário.
	 * 
	 * @return O telefone do usuário.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Define o telefone do usuário.
	 * 
	 * @param phone O telefone do usuário a ser definido.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Retorna a senha do usuário.
	 * 
	 * @return A senha do usuário.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Define a senha do usuário.
	 * 
	 * @param password A senha do usuário a ser definida.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Retorna a lista de pedidos associados a este usuário.
	 * 
	 * @return A lista de pedidos associados ao usuário.
	 */
	public List<Order> getOrders() {
		return orders;
	}

	// Métodos hashCode e equals

	/**
	 * Sobrescreve o método {@link Object#hashCode()} para garantir uma comparação
	 * correta dos objetos {@link User} com base no identificador único (id).
	 * 
	 * @return O código de hash calculado a partir do identificador do usuário.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Sobrescreve o método {@link Object#equals(Object)} para garantir que dois
	 * usuários sejam considerados iguais se tiverem o mesmo identificador único (id).
	 * 
	 * @param obj O objeto a ser comparado com o usuário atual.
	 * @return Verdadeiro se os usuários forem iguais, caso contrário, falso.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
}
