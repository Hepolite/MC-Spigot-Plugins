package com.hepolite.kineticore.cmd;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import com.hepolite.kineticore.cmd.CmdArgs.Snapshot;

class CommandArgumentsTest
{
	@Test
	void testSize()
	{
		assertEquals(1, new CmdArgs(this.args("Test")).size());
		assertEquals(2, new CmdArgs(this.args("Test", "Foo")).size());
	}
	@Test
	void testEmpty()
	{
		assertTrue(new CmdArgs(this.args()).empty());
		assertFalse(new CmdArgs(this.args("Bar")).empty());
	}

	@Test
	void testConsume()
	{
		final CmdArgs argsA = new CmdArgs(this.args("Hello", "World"));
		final CmdArgs argsB = new CmdArgs(this.args("This", "is", "a", "test"));

		assertEquals("Hello", argsA.consume());
		assertEquals("World", argsA.consume());
		assertThrows(CommandProcessException.class, () -> argsA.consume());

		assertArrayEquals(strings("This", "is", "a"), argsB.consume(3));
		assertArrayEquals(strings("test"), argsB.consume(1));
		assertThrows(CommandProcessException.class, () -> argsB.consume());
	}

	@Test
	void testSnapshot()
	{
		final CmdArgs args = new CmdArgs(this.args("Hello", "World"));
		final Snapshot snapshot = args.getSnapshot();

		assertEquals("Hello", args.consume());
		args.restoreSnapshot(snapshot);
		assertEquals("Hello", args.consume());
	}

	// ...

	Collection<String> args(final String... arguments)
	{
		return Arrays.asList(arguments);
	}
	String[] strings(final String... values)
	{
		return values;
	}
}
