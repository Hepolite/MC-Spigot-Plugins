package com.hepolite.kineticore.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.kineticore.cmd.CmdArgs;
import com.hepolite.kineticore.cmd.CmdContext;
import com.hepolite.kineticore.cmd.CmdExecutionException;
import com.hepolite.kineticore.cmd.GenericArgs;
import com.hepolite.kineticore.cmd.ICmd;
import com.hepolite.kineticore.user.ConsoleUser;
import com.hepolite.kineticore.user.IUser;

class CmdElementChildrenTest
{
	static class Mock implements ICmd
	{
		private final String name;

		public boolean executed = false;

		public Mock(final String name)
		{
			this.name = name;
		}

		// ...

		@Override
		public ICmdElement getStructure()
		{
			return GenericArgs.none();
		}

		@Override
		public String getName()
		{
			return name;
		}
		@Override
		public String getDescription()
		{
			return "";
		}
		@Override
		public String getHelp()
		{
			return "";
		}

		@Override
		public void execute(final IUser user, final CmdContext context)
			throws CmdExecutionException
		{
			executed = true;
		}
	}

	@Test
	void testParse()
	{
		final Mock mockA = new Mock("foo");
		final Mock mockB = new Mock("bar");

		final IUser user = new ConsoleUser();
		final CmdArgs args = new CmdArgs("bar");
		final CmdContext context = new CmdContext();
		final ICmdElement sequence = GenericArgs.children(mockA, mockB);

		sequence.parse(user, args, context);
		assertFalse(mockA.executed);
		assertTrue(mockB.executed);
	}
}
