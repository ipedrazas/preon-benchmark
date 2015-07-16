package me.pedrazas.benchmark;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RandomStringTest {
	
	private RandomString rs;

	@Before
	public void setUp() throws Exception {
		rs = new RandomString(6);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRandomString() {		
		assertTrue(rs.nextString().length() == 6);
	}

	@Test
	public void testNextString() {
		String test1 = rs.nextString();
		String test2 = rs.nextString();
		
		assertFalse(test1.equals(test2));
	}

}
