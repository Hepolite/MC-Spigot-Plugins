package com.hepolite.kineticore.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.kineticore.cmd.CmdArgs;
import com.hepolite.kineticore.cmd.CmdContext;
import com.hepolite.kineticore.cmd.GenericArgs;
import com.hepolite.kineticore.user.ConsoleUser;
import com.hepolite.kineticore.user.IUser;

class CmdElementOptionalTest
{
	@Test
	void testParse()
	{
		final IUser user = new ConsoleUser();
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
