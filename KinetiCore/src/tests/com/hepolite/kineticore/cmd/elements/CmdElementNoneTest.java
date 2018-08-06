package com.hepolite.kineticore.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.kineticore.cmd.CmdArgs;
import com.hepolite.kineticore.cmd.CmdContext;
import com.hepolite.kineticore.cmd.CmdParseException;
import com.hepolite.kineticore.cmd.GenericArgs;
import com.hepolite.kineticore.user.ConsoleUser;
import com.hepolite.kineticore.user.IUser;

class CmdElementNoneTest
{
	@Test
	void testParse()
	{
		final IUser user = new ConsoleUser();
		final CmdContext context = new CmdContext();

		GenericArgs.none().parse(user, new CmdArgs(), context);
		assertThrows(CmdParseException.class,
			() -> GenericArgs.none().parse(user, new CmdArgs("test"), context));
	}
}
