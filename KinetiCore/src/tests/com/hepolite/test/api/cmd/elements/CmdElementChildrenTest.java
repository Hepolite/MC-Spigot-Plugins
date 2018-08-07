package com.hepolite.test.api.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.api.cmd.elements.ICmdElement;
import com.hepolite.api.user.ConsoleUser;
import com.hepolite.api.user.IUser;
import com.hepolite.test.api.cmd.MockCmd;

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