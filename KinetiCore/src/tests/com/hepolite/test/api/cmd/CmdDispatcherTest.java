package com.hepolite.test.api.cmd;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.cmd.CmdDispatcher;
import com.hepolite.api.cmd.CmdHandler;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.test.api.util.BaseTest;

class CmdDispatcherTest extends BaseTest
{
	@Test
	void testDispatch()
	{
		final MockCmd mock = new MockCmd("test",
			GenericArgs.sequence(GenericArgs.string("1"),
				GenericArgs.string("2"), GenericArgs.string("3")));

		final CmdHandler handler = new CmdHandler();
		handler.register(mock);

		CmdDispatcher.dispatch(null, handler, strings("test", "1", "2", "3"));
		assertTrue(mock.executed);
	}
}
