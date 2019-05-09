package com.learningsession.data_store_and_load;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class StringToArrayTest {
	
	@Test
	public void main_test() throws Exception {
		List<Map<String, String>> arr = StringToArray.test_store();
		Assert.assertNotNull(arr);
		Assert.assertEquals(2, arr.size());
		Assert.assertEquals(StringToArray.text, StringToArray.test_load().trim());
	}
	
	@Test
	public void another_test() throws Exception {
		boolean error = false;
		try {
			new StringToArray();
		} catch(Exception e) {
			error = true;
		}
		Assert.assertEquals(true, error);
	}

}
