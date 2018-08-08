package com.hepolite.test.api.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.api.cmd.elements.ICmdElement;
import com.hepolite.api.user.IUser;
import com.hepolite.api.user.UserConsole;
import com.hepolite.test.api.cmd.MockCmd;

class CmdElementChildrenTest
{
	@Test
	void testParse()
	{
		final MockCmd mockA = new MockCmd("foo", GenericArgs.none());
		final MockCmd mockB = new MockCmd("bar", GenericArgs.none());

		final IUser user = new UserConsole();
		final CmdArgs args = new CmdArgs("bar");
		final CmdContext context = new CmdContext();
		final ICmdElement sequence = GenericArgs.children(mockA, mockB);

		sequence.parse(user, args, context);
		assertFalse(mockA.executed);
		assertTrue(mockB.executed);
	}
}
