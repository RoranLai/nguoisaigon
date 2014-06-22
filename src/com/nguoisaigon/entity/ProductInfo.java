package com.nguoisaigon.entity;

import java.util.ArrayList;

public class ProductInfo {
	private String productId;
	private Integer categoryId;
	private String name;
	private String createDate;
	private String description;
	private Double unitPrice;
	private Double salePrice;
	private Integer quantity;
	private Boolean isNew;
	private Boolean isHot;
	private Boolean isSale;
	private Boolean isDelete;
	private String CategoryName;
	private ArrayList<ImageInfo> imageList;
	private ArrayList<SizeInfo> sizeQtyList;
	private String ownerInfo;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	public Boolean getIsSale() {
		return isSale;
	}

	public void setIsSale(Boolean isSale) {
		this.isSale = isSale;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public ArrayList<ImageInfo> getImageList() {
		return imageList;
	}

	public void setImageList(ArrayList<ImageInfo> imageList) {
		this.imageList = imageList;
	}

	public ArrayList<SizeInfo> getSizeQtyList() {
		return sizeQtyList;
	}

	public void setSizeQtyList(ArrayList<SizeInfo> sizeQtyList) {
		this.sizeQtyList = sizeQtyList;
	}

	public String getOwnerInfo() {
		return ownerInfo;
	}

	public void setOwnerInfo(String ownerInfo) {
		this.ownerInfo = ownerInfo;
	}
}
