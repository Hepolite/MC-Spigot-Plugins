package com.hepolite.kineticore.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.kineticore.cmd.CmdArgs;
import com.hepolite.kineticore.cmd.CmdContext;
import com.hepolite.kineticore.cmd.GenericArgs;
import com.hepolite.kineticore.cmd.MockCmd;
import com.hepolite.kineticore.user.ConsoleUser;
import com.hepolite.kineticore.user.IUser;

class CmdElementChildrenTest
{
	@Test
	void testParse()
	{
		final MockCmd mockA = new MockCmd("foo");
		final MockCmd mockB = new MockCmd("bar");

		final IUser user = new ConsoleUser();
		final CmdArgs args = new CmdArgs("bar");
		final CmdContext context = new CmdContext();
		final ICmdElement sequence = GenericArgs.children(mockA, mockB);

		sequence.parse(user, args, context);
		assertFalse(mockA.executed);
		assertTrue(mockB.executed);
	}
}
