package com.hepolite.kineticore.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.kineticore.cmd.CmdArgs;
import com.hepolite.kineticore.cmd.CmdContext;
import com.hepolite.kineticore.cmd.CmdParseException;
import com.hepolite.kineticore.user.ConsoleUser;
import com.hepolite.kineticore.user.IUser;

class CmdElementValueTest
{
	class Mock extends CmdElementValue
	{
		public Mock(final String key)
		{
			super(key);
		}

		@Override
		protected Object parse(final IUser user, final CmdArgs args)
			throws CmdParseException
		{
			return args.consume();
		}
	}

	// ...

	@Test
	void testParse()
	{
		final IUser user = new ConsoleUser();
		final CmdArgs args = new CmdArgs("foo", "bar");
		final CmdContext context = new CmdContext();

		new Mock("FOO").parse(user, args, context);
		new Mock("BAR").parse(user, args, context);

		assertEquals("foo", context.get("FOO", ""));
		assertEquals("bar", context.get("BAR", ""));
	}
}
