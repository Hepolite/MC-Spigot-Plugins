package com.hepolite.test.api.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.api.user.ConsoleUser;
import com.hepolite.api.user.IUser;

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
