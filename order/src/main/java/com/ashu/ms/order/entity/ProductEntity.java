package com.ashu.ms.order.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ashu.ms.common.dto.Order;
import com.ashu.ms.common.dto.Product;

@Entity
@Table(name = "PRODUCT")
public class ProductEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6852497055930808771L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "N_PRODUCT_ID")
	private long id;

	@Column(name = "C_NAME")
	private String name;

	@Column(name = "C_DESC")
	private String description;

	@Column(name = "N_PRICE")
	private float price;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<OrderEntity> orderList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<OrderEntity> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderEntity> orderList) {
		this.orderList = orderList;
	}

	public Product toDto(boolean additionalDataRequired) {

		Product product = new Product();
		product.setId(getId());
		product.setDescription(getDescription());
		product.setName(getName());
		product.setPrice(getPrice());

		if (additionalDataRequired) {

			// LAZY LOADING is enabled so query execution needed only if required
			List<OrderEntity> orderEnList = getOrderList();
			if (orderEnList != null) {
				List<Order> orderList = new ArrayList<Order>();
				for (OrderEntity orderEn : orderEnList) {
					orderList.add(orderEn.toDto());
				}

				product.setOrderList(orderList);
			}
		}
		return product;
	}
}
