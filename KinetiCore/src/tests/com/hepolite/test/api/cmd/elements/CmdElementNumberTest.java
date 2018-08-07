package com.hepolite.test.api.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.CmdParseException;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.api.user.UserConsole;
import com.hepolite.api.user.IUser;

class CmdElementNumberTest
{
	@Test
	void testParse()
	{
		final IUser user = new UserConsole();
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
		final IUser user = new UserConsole();
		final CmdContext context = new CmdContext();

		assertThrows(CmdParseException.class, () -> GenericArgs.intNum("key")
			.parse(user, new CmdArgs("invalid"), context));
	}
}
