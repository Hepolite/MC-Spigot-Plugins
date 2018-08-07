package com.hepolite.test.api.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.api.cmd.elements.ICmdElement;
import com.hepolite.api.user.ConsoleUser;
import com.hepolite.api.user.IUser;

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
