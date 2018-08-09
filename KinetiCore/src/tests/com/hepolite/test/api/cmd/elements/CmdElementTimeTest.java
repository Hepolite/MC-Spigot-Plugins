package com.hepolite.test.api.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.api.unit.Time;
import com.hepolite.api.user.IUser;
import com.hepolite.api.user.UserConsole;

class CmdElementTimeTest
{
	@Test
	void testParse()
	{
		final IUser user = new UserConsole();
		final CmdArgs args = new CmdArgs("10t", "3s 10t");
		final CmdContext context = new CmdContext();

		GenericArgs.time("foo").parse(user, args, context);
		GenericArgs.time("bar").parse(user, args, context);

		assertEquals(Time.fromTicks(10), context.get("foo", new Time()));
		assertEquals(Time.fromTicks(70), context.get("bar", new Time()));
	}
}
