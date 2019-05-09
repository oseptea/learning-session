package com.learningsession.find_payable_amount;

import java.math.BigDecimal;

public class Item {
	
	private String name;
	private BigDecimal price;
	private ItemCategories category;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ItemCategories getCategory() {
		return category;
	}

	public void setCategory(ItemCategories category) {
		this.category = category;
	}

	public Item() {
		super();
	}
	
	public static Item newInstance(String name, ItemCategories category, BigDecimal price) {
		Item i = new Item();
		i.setName(name);
		i.setCategory(category);
		i.setPrice(price);
		return i;
	}
	
	public enum ItemCategories {
		GROCERIES, FRESH, TEXTILE;
	}
	
}
