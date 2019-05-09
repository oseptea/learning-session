package com.learningsession.find_payable_amount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.learningsession.find_payable_amount.Item.ItemCategories;

public class Cart {
	
	private User user;
	private Map<Item, Integer> itemsOnDiscount;
	private Map<Item, Integer> itemsNoDiscount;
	
	private static final String PREFIX_MONEY 	= "$ ";
	private static final String FORMAT_MONEY 	= "%,.2f";
	private static final String FORMAT_ITEM 	= "%s (%s @%s)";
	private static final Integer FIXED_LEN 		= 40;
	
	public Cart(User user) {
		this.user = user;
		this.itemsOnDiscount = new HashMap<>();
		this.itemsNoDiscount = new HashMap<>();
	}
	
	public boolean isDiscountAble() {
		return user != null && !itemsOnDiscount.isEmpty();
	}
	
	public void addToCart(final Item item, final Integer qty) {
		if (ItemCategories.GROCERIES.equals(item.getCategory())) {
			itemsNoDiscount.put(item, qty);
		} else {
			itemsOnDiscount.put(item, qty);
		}
	}
	
	public void removeItem(final Item item) {
		itemsOnDiscount.remove(item);
		itemsNoDiscount.remove(item);
	}
	
	public BigDecimal getTotalPriceOnDiscount() {
		return summary(itemsOnDiscount);
	}
	
	public BigDecimal getTotalPriceNoDiscount() {
		return summary(itemsNoDiscount);
	}
	
	public BigDecimal getPayableAmount() {
		System.out.println("\n\n");
		System.out.println("--- RECEIPT ---");
		System.out.println(user.toString());
		BigDecimal totalOnDiscount = getTotalPriceOnDiscount();
		Double discount;
		if (isDiscountAble()) {
			System.out.println("---");
			System.out.println(StringUtils.rightPad("> Sub Total:", FIXED_LEN, " ") + PREFIX_MONEY.concat(String.format(FORMAT_MONEY, totalOnDiscount)));
			discount = DiscountUtil.defineDiscountByUser(user);
			if (discount != null && discount > 0d) {
				totalOnDiscount = totalOnDiscount.multiply((BigDecimal.valueOf(100d-discount))).divide(BigDecimal.valueOf(100));
			}
			System.out.println(StringUtils.rightPad("> Discount:", FIXED_LEN, " ") + String.format(FORMAT_MONEY, discount).concat("%"));
			System.out.println(StringUtils.rightPad("> Summary Total:", FIXED_LEN, " ") + PREFIX_MONEY.concat(String.format(FORMAT_MONEY, totalOnDiscount)));
		}
		BigDecimal total = totalOnDiscount.add(getTotalPriceNoDiscount());
		System.out.println("---");
		System.out.println(StringUtils.rightPad(">> Grand Total:", FIXED_LEN, " ") + PREFIX_MONEY.concat(String.format(FORMAT_MONEY, total)));
		BigDecimal cashback = DiscountUtil.defineCashBackOfPrice(total);
		System.out.println(StringUtils.rightPad(">> Cashback:", FIXED_LEN, " ") + PREFIX_MONEY.concat(String.format(FORMAT_MONEY, cashback)));
		BigDecimal payableAmount = total.subtract(cashback).setScale(2, RoundingMode.HALF_UP);
		System.out.println("---");
		System.out.println(StringUtils.rightPad(">> Payable Amount:", FIXED_LEN, " ") + PREFIX_MONEY.concat(String.format(FORMAT_MONEY, payableAmount)));
		System.out.println("---");
		return payableAmount;
	}
	
	private BigDecimal summary(final Map<Item, Integer> map) {
		BigDecimal total = BigDecimal.ZERO;
		if (!map.isEmpty()) {
			System.out.println("---");
		}
		for (Entry<Item, Integer> entry : map.entrySet()) {
			Item item = entry.getKey();
			Integer qty = entry.getValue();
			BigDecimal sum = item.getPrice().multiply(BigDecimal.valueOf(qty));
			total = total.add(sum);
			System.out.println(StringUtils.rightPad(String.format(FORMAT_ITEM, item.getName(), qty, String.format(FORMAT_MONEY, item.getPrice())), FIXED_LEN, " ") + PREFIX_MONEY.concat(String.format(FORMAT_MONEY, sum)));
		}
		return total;
	}
	
}
