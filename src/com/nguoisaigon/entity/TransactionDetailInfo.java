package com.nguoisaigon.entity;

import java.sql.Date;

public class TransactionDetailInfo {

	private Date addedDate;
	private Integer categoryId;
	private String productId;
	private String productName;
	private Integer quantity;
	private Integer sizeType;
	private Integer stockQuantity;
	private Double unitPrice;

	/**
	 * @return the addedDate
	 */
	public Date getAddedDate() {
		return addedDate;
	}

	/**
	 * @param addedDate
	 *            the addedDate to set
	 */
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the sizeType
	 */
	public Integer getSizeType() {
		return sizeType;
	}

	/**
	 * @param sizeType
	 *            the sizeType to set
	 */
	public void setSizeType(Integer sizeType) {
		this.sizeType = sizeType;
	}

	/**
	 * @return the stockQuantity
	 */
	public Integer getStockQuantity() {
		return stockQuantity;
	}

	/**
	 * @param stockQuantity
	 *            the stockQuantity to set
	 */
	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	/**
	 * @return the unitPrice
	 */
	public Double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice
	 *            the unitPrice to set
	 */
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
