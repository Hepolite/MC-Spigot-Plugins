package com.hepolite.kineticore.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.kineticore.cmd.CmdArgs;
import com.hepolite.kineticore.cmd.CmdContext;
import com.hepolite.kineticore.cmd.GenericArgs;
import com.hepolite.kineticore.user.ConsoleUser;
import com.hepolite.kineticore.user.IUser;

class CmdElementFirstTest
{
	@Test
	void testParse()
	{
		final IUser user = new ConsoleUser();
		final CmdArgs args = new CmdArgs("42", "7.32");
		final CmdContext context = new CmdContext();

		/// @formatter:off
		final ICmdElement sequence = GenericArgs.first(
			GenericArgs.sequence(
				GenericArgs.byteNum("number"),
				GenericArgs.bool("bool")),
			GenericArgs.sequence(
				GenericArgs.intNum("number"),
				GenericArgs.floatNum("fraction")));
		/// @formatter:on

		sequence.parse(user, args, context);
		assertEquals(false, (boolean) context.get("bool", false));
		assertEquals(42, (int) context.get("number", 0));
		assertEquals(7.32f, (float) context.get("fraction", 0.0f));
	}
}
