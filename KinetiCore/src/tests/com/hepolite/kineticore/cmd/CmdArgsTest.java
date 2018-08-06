package com.hepolite.kineticore.cmd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.kineticore.cmd.CmdArgs.Snapshot;
import com.hepolite.kineticore.util.BaseTest;

class CmdArgsTest extends BaseTest
{
	@Test
	void testSize()
	{
		assertEquals(1, new CmdArgs(strings("Test")).size());
		assertEquals(2, new CmdArgs(strings("Test", "Foo")).size());

		final CmdArgs args = new CmdArgs("Hello", "World!");
		assertEquals(2, args.size());
		args.consume();
		assertEquals(1, args.size());
	}
	@Test
	void testEmpty()
	{
		assertTrue(new CmdArgs(strings()).empty());
		assertFalse(new CmdArgs(strings("Bar")).empty());
	}

	@Test
	void testConsume()
	{
		final CmdArgs argsA = new CmdArgs(strings("Hello", "World"));
		final CmdArgs argsB = new CmdArgs(strings("This", "is", "a", "test"));

		assertEquals("Hello", argsA.consume());
		assertEquals("World", argsA.consume());
		assertThrows(CmdExecutionException.class, () -> argsA.consume());

		assertArrayEquals(strings("This", "is", "a"), argsB.consume(3));
		assertArrayEquals(strings("test"), argsB.consume(1));
		assertThrows(CmdExecutionException.class, () -> argsB.consume());
	}

	@Test
	void testSnapshot()
	{
		final CmdArgs args = new CmdArgs(strings("Hello", "World"));
		final Snapshot snapshot = args.getSnapshot();

		assertEquals("Hello", args.consume());
		args.restoreSnapshot(snapshot);
		assertEquals("Hello", args.consume());
	}
}
