package com.hepolite.kineticore.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.kineticore.cmd.CmdArgs;
import com.hepolite.kineticore.cmd.CmdContext;
import com.hepolite.kineticore.cmd.CmdParseException;
import com.hepolite.kineticore.cmd.GenericArgs;
import com.hepolite.kineticore.user.ConsoleUser;
import com.hepolite.kineticore.user.IUser;

class CmdElementNumberTest
{
	@Test
	void testParse()
	{
		final IUser user = new ConsoleUser();
		final CmdArgs args = new CmdArgs("-42", "997");
		final CmdContext context = new CmdContext();

		GenericArgs.intNum("foo").parse(user, args, context);
		GenericArgs.intNum("bar").parse(user, args, context);

		assertEquals(-42, (int) context.get("foo", 0));
		assertEquals(997, (int) context.get("bar", 0));
	}
	@Test
	void testParseInvalid()
	{
		final IUser user = new ConsoleUser();
		final CmdContext context = new CmdContext();

		assertThrows(CmdParseException.class, () -> GenericArgs.intNum("key")
			.parse(user, new CmdArgs("invalid"), context));
	}
}
