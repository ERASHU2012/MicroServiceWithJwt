package com.ashu.ms.order.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ashu.ms.common.dto.Order;

@Entity
@Table(name = "ORDER_")
public class OrderEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7922649004979054885L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "N_ORDER_ID")
	private long id;

	@ManyToOne
	@JoinColumn(name = "N_PRODUCT_ID")
	private ProductEntity product;

	@Column(name = "D_PURCHASE_DATE")
	private String purchaseDate;

	@Column(name = "N_QTY")
	private int qty;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Order toDto() {

		Order order = new Order();
		order.setPurchaseDate(getPurchaseDate());
		order.setQty(getQty());
		// TODO
		// order.setRevenueGenerated(get)
		return order;
	}

}
