package com.learningsession.find_payable_amount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DiscountUtil {
	
	private DiscountUtil() {}
	
	public static double defineDiscountByUser(User user) {
		List<Double> discs = new ArrayList<>();
		if (user.isEmployee()) discs.add(30d);
		if (user.isAffiliate()) discs.add(10d);
		int customerLoyal = calculateDiffRegDateToCurrInYear(user);
		if (customerLoyal > 2) discs.add(5d);
		return discs.isEmpty() ? 0d : Collections.max(discs);
	}
	
	public static BigDecimal defineCashBackOfPrice(BigDecimal price) {
		if (price == null || BigDecimal.ZERO.equals(price)) return BigDecimal.ZERO;
		BigDecimal base = BigDecimal.valueOf(5);
		BigDecimal multiple = BigDecimal.valueOf(100);
		BigDecimal diff = price.remainder(multiple);
		if (BigDecimal.ZERO.equals(diff)) {
			return price.divide(multiple).multiply(base);
		} else {
			return (price.subtract(diff)).divide(multiple).multiply(base);
		}
	}
	
	public static int calculateDiffRegDateToCurrInYear(User user) {
		if (user == null || user.getRegisterDate() == null) return 0;
		long diff = (new Date()).getTime() - user.getRegisterDate().getTime();
	    return (int) (diff / (60 * 60 * 1000 * 24 * 30.41666666 * 12));
	}

}
