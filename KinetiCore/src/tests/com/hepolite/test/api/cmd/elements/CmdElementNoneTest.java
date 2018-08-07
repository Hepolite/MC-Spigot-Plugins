package com.hepolite.test.api.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.CmdParseException;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.api.user.UserConsole;
import com.hepolite.api.user.IUser;

class CmdElementNoneTest
{
	@Test
	void testParse()
	{
		final IUser user = new UserConsole();
		final CmdContext context = new CmdContext();

		GenericArgs.none().parse(user, new CmdArgs(), context);
		assertThrows(CmdParseException.class,
			() -> GenericArgs.none().parse(user, new CmdArgs("test"), context));
	}
}
