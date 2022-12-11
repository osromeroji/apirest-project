package com.mercadona.springboot.backend.apirest.models.entities;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "proveedores")
public class Supplier implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 7, unique = true)
	private String code;

	private String name;

	/*@JsonManagedReference
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@Transient
	private List<Product> products = new ArrayList<>();*/
	
	public Supplier() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
