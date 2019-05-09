package com.learningsession.find_payable_amount;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import com.learningsession.find_payable_amount.Item.ItemCategories;

public class CartTest {
	
	private static final String USER_NAME = "Superman";
	
	@Test
	public void new_user_with_item_discount() throws Exception {
		final BigDecimal expected = new BigDecimal("945.00");
		Cart cart = new Cart(User.newInstance(USER_NAME));
		cart.addToCart(Item.newInstance("CARVIL SHOES /RT", ItemCategories.TEXTILE, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("SALAD BAR", ItemCategories.FRESH, new BigDecimal(30)), 3);
		Assert.assertEquals(expected, cart.getPayableAmount());
	}
	
	@Test
	public void new_user_without_item_discount() throws Exception {
		final BigDecimal expected = new BigDecimal("945.00");
		Cart cart = new Cart(User.newInstance(USER_NAME));
		cart.addToCart(Item.newInstance("VASELINE LSPF2", ItemCategories.GROCERIES, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("DENARA HBL 200", ItemCategories.GROCERIES, new BigDecimal(30)), 3);
		Assert.assertEquals(expected, cart.getPayableAmount());
	}
	
	@Test
	public void new_user_with_combine_item() throws Exception {
		final BigDecimal expected = new BigDecimal("1885.00");
		Cart cart = new Cart(User.newInstance(USER_NAME));
		cart.addToCart(Item.newInstance("CARVIL SHOES /RT", ItemCategories.TEXTILE, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("SALAD BAR", ItemCategories.FRESH, new BigDecimal(30)), 3);
		cart.addToCart(Item.newInstance("VASELINE LSPF2", ItemCategories.GROCERIES, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("DENARA HBL 200", ItemCategories.GROCERIES, new BigDecimal(30)), 3);
		Assert.assertEquals(expected, cart.getPayableAmount());
	}
	
	@Test
	public void employee_user_with_item_discount() throws Exception {
		final BigDecimal expected = new BigDecimal("663.00");
		Cart cart = new Cart(User.newInstance(USER_NAME, true, false));
		cart.addToCart(Item.newInstance("CARVIL SHOES /RT", ItemCategories.TEXTILE, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("SALAD BAR", ItemCategories.FRESH, new BigDecimal(30)), 3);
		Assert.assertEquals(expected, cart.getPayableAmount());
	}
	
	@Test
	public void employee_user_without_item_discount() throws Exception {
		final BigDecimal expected = new BigDecimal("945.00");
		Cart cart = new Cart(User.newInstance(USER_NAME, true, false));
		cart.addToCart(Item.newInstance("VASELINE LSPF2", ItemCategories.GROCERIES, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("DENARA HBL 200", ItemCategories.GROCERIES, new BigDecimal(30)), 3);
		Assert.assertEquals(expected, cart.getPayableAmount());
	}
	
	@Test
	public void employee_user_with_combine_item() throws Exception {
		final BigDecimal expected = new BigDecimal("1603.00");
		Cart cart = new Cart(User.newInstance(USER_NAME, true, false));
		cart.addToCart(Item.newInstance("CARVIL SHOES /RT", ItemCategories.TEXTILE, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("SALAD BAR", ItemCategories.FRESH, new BigDecimal(30)), 3);
		cart.addToCart(Item.newInstance("VASELINE LSPF2", ItemCategories.GROCERIES, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("DENARA HBL 200", ItemCategories.GROCERIES, new BigDecimal(30)), 3);
		Assert.assertEquals(expected, cart.getPayableAmount());
	}
	
	@Test
	public void affiliate_user_with_item_discount() throws Exception {
		final BigDecimal expected = new BigDecimal("851.00");
		Cart cart = new Cart(User.newInstance(USER_NAME, false, true));
		cart.addToCart(Item.newInstance("CARVIL SHOES /RT", ItemCategories.TEXTILE, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("SALAD BAR", ItemCategories.FRESH, new BigDecimal(30)), 3);
		Assert.assertEquals(expected, cart.getPayableAmount());
	}
	
	@Test
	public void affiliate_user_without_item_discount() throws Exception {
		final BigDecimal expected = new BigDecimal("945.00");
		Cart cart = new Cart(User.newInstance(USER_NAME, false, true));
		cart.addToCart(Item.newInstance("VASELINE LSPF2", ItemCategories.GROCERIES, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("DENARA HBL 200", ItemCategories.GROCERIES, new BigDecimal(30)), 3);
		Assert.assertEquals(expected, cart.getPayableAmount());
	}
	
	@Test
	public void affiliate_user_with_combine_item() throws Exception {
		final BigDecimal expected = new BigDecimal("1791.00");
		Cart cart = new Cart(User.newInstance(USER_NAME, false, true));
		cart.addToCart(Item.newInstance("CARVIL SHOES /RT", ItemCategories.TEXTILE, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("SALAD BAR", ItemCategories.FRESH, new BigDecimal(30)), 3);
		cart.addToCart(Item.newInstance("VASELINE LSPF2", ItemCategories.GROCERIES, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("DENARA HBL 200", ItemCategories.GROCERIES, new BigDecimal(30)), 3);
		Assert.assertEquals(expected, cart.getPayableAmount());
	}
	
	@Test
	public void loyal_user_with_item_discount() throws Exception {
		final BigDecimal expected = new BigDecimal("895.50");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -3);
		Cart cart = new Cart(User.newInstance(USER_NAME, cal.getTime(), false, false));
		cart.addToCart(Item.newInstance("CARVIL SHOES /RT", ItemCategories.TEXTILE, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("SALAD BAR", ItemCategories.FRESH, new BigDecimal(30)), 3);
		Assert.assertEquals(expected, cart.getPayableAmount());
	}
	
	@Test
	public void loyal_user_without_item_discount() throws Exception {
		final BigDecimal expected = new BigDecimal("945.00");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -3);
		Cart cart = new Cart(User.newInstance(USER_NAME, cal.getTime(), false, false));
		cart.addToCart(Item.newInstance("VASELINE LSPF2", ItemCategories.GROCERIES, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("DENARA HBL 200", ItemCategories.GROCERIES, new BigDecimal(30)), 3);
		Assert.assertEquals(expected, cart.getPayableAmount());
	}
	
	@Test
	public void loyal_user_with_combine_item() throws Exception {
		final BigDecimal expected = new BigDecimal("1791.00");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -3);
		Cart cart = new Cart(User.newInstance(USER_NAME, cal.getTime(), false, true));
		cart.addToCart(Item.newInstance("CARVIL SHOES /RT", ItemCategories.TEXTILE, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("SALAD BAR", ItemCategories.FRESH, new BigDecimal(30)), 3);
		cart.addToCart(Item.newInstance("VASELINE LSPF2", ItemCategories.GROCERIES, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("DENARA HBL 200", ItemCategories.GROCERIES, new BigDecimal(30)), 3);
		Assert.assertEquals(expected, cart.getPayableAmount());
	}
	
	@Test
	public void loyal_affiliate_user_with_item_discount() throws Exception {
		final BigDecimal expected = new BigDecimal("851.00");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -3);
		Cart cart = new Cart(User.newInstance(USER_NAME, cal.getTime(), false, true));
		cart.addToCart(Item.newInstance("CARVIL SHOES /RT", ItemCategories.TEXTILE, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("SALAD BAR", ItemCategories.FRESH, new BigDecimal(30)), 3);
		Assert.assertEquals(expected, cart.getPayableAmount());
	}
	
	@Test
	public void loyal_affiliate_user_without_item_discount() throws Exception {
		final BigDecimal expected = new BigDecimal("945.00");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -3);
		Cart cart = new Cart(User.newInstance(USER_NAME, cal.getTime(), false, true));
		cart.addToCart(Item.newInstance("VASELINE LSPF2", ItemCategories.GROCERIES, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("DENARA HBL 200", ItemCategories.GROCERIES, new BigDecimal(30)), 3);
		Assert.assertEquals(expected, cart.getPayableAmount());
	}
	
	@Test
	public void loyal_affiliate_user_with_combine_item() throws Exception {
		final BigDecimal expected = new BigDecimal("1791.00");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -3);
		Cart cart = new Cart(User.newInstance(USER_NAME, cal.getTime(), false, true));
		cart.addToCart(Item.newInstance("CARVIL SHOES /RT", ItemCategories.TEXTILE, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("SALAD BAR", ItemCategories.FRESH, new BigDecimal(30)), 3);
		cart.addToCart(Item.newInstance("VASELINE LSPF2", ItemCategories.GROCERIES, new BigDecimal(100)), 9);
		cart.addToCart(Item.newInstance("DENARA HBL 200", ItemCategories.GROCERIES, new BigDecimal(30)), 3);
		Assert.assertEquals(expected, cart.getPayableAmount());
	}

}
