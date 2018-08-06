package com.hepolite.kineticore.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.kineticore.cmd.CmdArgs;
import com.hepolite.kineticore.cmd.CmdContext;
import com.hepolite.kineticore.cmd.GenericArgs;
import com.hepolite.kineticore.user.ConsoleUser;
import com.hepolite.kineticore.user.IUser;

class CmdElementSequenceTest
{
	@Test
	void testParse()
	{
		final IUser user = new ConsoleUser();
		final CmdArgs args = new CmdArgs("3.14", "yup", "Hello World!");
		final CmdContext context = new CmdContext();
		final ICmdElement sequence = GenericArgs.sequence(
			GenericArgs.floatNum("float"), GenericArgs.bool("bool"),
			GenericArgs.string("string"));

		sequence.parse(user, args, context);
		assertEquals(3.14f, (float) context.get("float", 0.0f));
		assertEquals(true, (boolean) context.get("bool", false));
		assertEquals("Hello World!", context.get("string", ""));
	}
}
