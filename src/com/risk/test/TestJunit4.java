package com.risk.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestJunit4 {
	
	String str1 = new String ("abc");
	String str2 = new String ("abc");
	String str3 = null;
	String str4 = "abc";
	String str5 = "abc";
	
	@Test
	public void testAssertions1()
	{
		assertEquals(str1, str2);
	}
	
	@Test
	public void testAssertions2()
	{
		assertNotNull(str1);
	}
	
	@Test
	public void testAssertions3()
	{
		assertNull(str3);
	}
	
	@Test
	public void testAssertions4()
	{
		assertSame(str4, str5);
	}
	
	@Test
	public void testAssertions5()
	{
		assertNotSame(str1, str3);
	}

}
