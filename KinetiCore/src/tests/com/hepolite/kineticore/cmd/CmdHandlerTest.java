package com.hepolite.kineticore.cmd;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import com.hepolite.kineticore.user.ConsoleUser;

class CmdHandlerTest
{
	@Test
	void testExecute()
	{
		final MockCmd mock = new MockCmd("foo");

		final CmdHandler handler = new CmdHandler();
		handler.register(mock);
		handler.execute(new ConsoleUser(), "foo", "");

		assertTrue(mock.executed);
	}
}
