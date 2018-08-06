package com.hepolite.kineticore.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.kineticore.cmd.CmdArgs;
import com.hepolite.kineticore.cmd.CmdContext;
import com.hepolite.kineticore.cmd.GenericArgs;
import com.hepolite.kineticore.user.ConsoleUser;
import com.hepolite.kineticore.user.IUser;

class CmdElementBoolTest
{
	@Test
	void testParse()
	{
		final IUser user = new ConsoleUser();
		final CmdArgs args = new CmdArgs("true", "yes", "nope");
		final CmdContext context = new CmdContext();

		GenericArgs.bool("foo").parse(user, args, context);
		GenericArgs.bool("bar").parse(user, args, context);
		GenericArgs.bool("baz").parse(user, args, context);

		assertEquals(true, context.get("foo", false));
		assertEquals(true, context.get("bar", false));
		assertEquals(false, context.get("baz", true));
	}
}
