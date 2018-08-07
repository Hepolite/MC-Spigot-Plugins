package com.hepolite.test.api.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.api.cmd.elements.ICmdElement;
import com.hepolite.api.user.UserConsole;
import com.hepolite.api.user.IUser;

class CmdElementOptionalTest
{
	@Test
	void testParse()
	{
		final IUser user = new UserConsole();
		final CmdArgs args = new CmdArgs("Test", "1.5");
		final CmdContext context = new CmdContext();
		final ICmdElement sequence = GenericArgs.sequence(
			GenericArgs.optional(GenericArgs.intNum("int")),
			GenericArgs.optional(GenericArgs.string("string")),
			GenericArgs.floatNum("float"));

		sequence.parse(user, args, context);
		assertEquals(0, (int) context.get("int", 0));
		assertEquals("Test", context.get("string", ""));
		assertEquals(1.5f, (float) context.get("float", 0.0f));
	}
}
