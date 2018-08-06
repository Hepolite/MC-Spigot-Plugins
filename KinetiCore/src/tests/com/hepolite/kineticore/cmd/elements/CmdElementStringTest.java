package com.hepolite.kineticore.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.kineticore.cmd.CmdArgs;
import com.hepolite.kineticore.cmd.CmdContext;
import com.hepolite.kineticore.cmd.GenericArgs;
import com.hepolite.kineticore.user.ConsoleUser;
import com.hepolite.kineticore.user.IUser;

class CmdElementStringTest
{
	static class SingleTest
	{
		@Test
		void testParse()
		{
			final IUser user = new ConsoleUser();
			final CmdArgs args = new CmdArgs("Hello", "World");
			final CmdContext context = new CmdContext();

			GenericArgs.string("foo").parse(user, args, context);
			GenericArgs.string("bar").parse(user, args, context);

			assertEquals("Hello", context.get("foo", ""));
			assertEquals("World", context.get("bar", ""));
		}
	}

	static class RemainingTest
	{
		@Test
		void testParse()
		{
			final IUser user = new ConsoleUser();
			final CmdArgs args = new CmdArgs("Hello", "World", "!");
			final CmdContext context = new CmdContext();

			GenericArgs.remainder("foo").parse(user, args, context);

			assertEquals("Hello World !", context.get("foo", ""));
		}
	}
}
