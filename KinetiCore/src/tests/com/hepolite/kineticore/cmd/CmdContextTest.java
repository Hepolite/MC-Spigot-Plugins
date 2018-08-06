package com.hepolite.kineticore.cmd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.kineticore.cmd.CmdContext.Snapshot;
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
		context.put("foo", 1, 2, "test", 42.0);
		context.put("bar", 3);

		assertEquals((Integer) 1, context.get("foo", 0));
		assertEquals((Integer) 0, context.get("faa", 0));
	}
	@Test
	void testGetAll()
	{
		final CmdContext context = new CmdContext();
		context.put("foo", 1);
		context.put("bar", 2, 3, 4);

		assertArrayEquals(generics(2, 3, 4),
			context.getAll("bar", new Integer[0]));
	}

	@Test
	void testSnapshot()
	{
		final CmdContext context = new CmdContext();
		final Snapshot snapshot = context.getSnapshot();

		context.put("foo", "Hello World!");
		assertTrue(context.has("foo"));
		context.restoreSnapshot(snapshot);
		assertFalse(context.has("foo"));
	}
}
