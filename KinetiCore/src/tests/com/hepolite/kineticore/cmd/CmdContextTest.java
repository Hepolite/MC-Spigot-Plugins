package com.hepolite.kineticore.cmd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.kineticore.util.BaseTest;

class CmdContextTest extends BaseTest
{
	@Test
	void testPut()
	{
		final CmdContext context = new CmdContext();

		assertFalse(context.has("foo"));
		context.put("foo", 42);
		assertTrue(context.has("foo"));
	}

	@Test
	void testGet()
	{
		final CmdContext context = new CmdContext();
		context.put("foo", 1);
		context.put("foo", 3);
		context.put("bar", 3);

		assertEquals((Integer) 1, context.get("foo", 0));
		assertEquals((Integer) 0, context.get("faa", 0));
	}
	@Test
	void testGetAll()
	{
		final CmdContext context = new CmdContext();
		context.put("foo", 1);
		context.put("bar", 2);
		context.put("bar", 3);

		assertArrayEquals(generics(2, 3),
			context.getAll("bar", new Integer[0]));
	}
}