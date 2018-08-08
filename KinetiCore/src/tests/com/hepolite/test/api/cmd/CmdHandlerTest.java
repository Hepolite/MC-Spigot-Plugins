package com.hepolite.test.api.cmd;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.cmd.CmdHandler;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.api.user.UserConsole;

class CmdHandlerTest
{
	@Test
	void testExecute()
	{
		final MockCmd mock = new MockCmd("foo", GenericArgs.none());

		final CmdHandler handler = new CmdHandler();
		handler.register(mock);
		handler.execute(new UserConsole(), "foo", "");

		assertTrue(mock.executed);
	}
}
