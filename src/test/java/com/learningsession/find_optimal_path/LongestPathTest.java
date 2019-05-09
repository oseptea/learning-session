package com.learningsession.find_optimal_path;

import org.junit.Assert;
import org.junit.Test;

public class LongestPathTest {

	@Test
	public void main_test() {
		final String expected = "INF 0 2 9 8 10";
		Assert.assertEquals(expected, LongestPath.test());
	}
}
