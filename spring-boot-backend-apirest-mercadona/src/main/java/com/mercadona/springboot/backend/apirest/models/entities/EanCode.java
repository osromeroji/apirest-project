package com.mercadona.springboot.backend.apirest.models.entities;

import java.io.Serializable;

public class EanCode implements Serializable {

	private Supplier supplier;

	private Product product;

	private String destination;
	

	@Override
	public String toString() {
		return "EanCode [supplier=" + supplier + ", product=" + product + ", destination=" + destination + "]";
	}


	public Supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public String getDestination() {
		return destination;
	}

	public EanCode() {
		super();
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
