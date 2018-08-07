package com.hepolite.test.api.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.CmdParseException;
import com.hepolite.api.cmd.elements.CmdElementValue;
import com.hepolite.api.user.UserConsole;
import com.hepolite.api.user.IUser;

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
		final IUser user = new UserConsole();
		final CmdArgs args = new CmdArgs("foo", "bar");
		final CmdContext context = new CmdContext();

		new Mock("FOO").parse(user, args, context);
		new Mock("BAR").parse(user, args, context);

		assertEquals("foo", context.get("FOO", ""));
		assertEquals("bar", context.get("BAR", ""));
	}
}
