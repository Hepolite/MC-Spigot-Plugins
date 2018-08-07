package com.hepolite.test.api.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.api.cmd.elements.ICmdElement;
import com.hepolite.api.user.ConsoleUser;
import com.hepolite.api.user.IUser;

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
