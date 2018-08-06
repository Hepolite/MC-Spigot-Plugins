package com.hepolite.kineticore.cmd.elements;

import com.hepolite.kineticore.cmd.CmdArgs;
import com.hepolite.kineticore.cmd.CmdContext;
import com.hepolite.kineticore.cmd.CmdParseException;
import com.hepolite.kineticore.user.IUser;

public class CmdElementFirst implements ICmdElement
{
	private final ICmdElement[] elements;

	public CmdElementFirst(final ICmdElement... elements)
	{
		this.elements = elements;
	}

	@Override
	public void parse(final IUser user, final CmdArgs args,
		final CmdContext context) throws CmdParseException
	{
		final CmdContext.Snapshot contextSnapshot = context.getSnapshot();
		final CmdArgs.Snapshot argsSnapshot = args.getSnapshot();

		for (final ICmdElement element : elements)
			try
			{
				element.parse(user, args, context);
				break;
			}
			catch (final CmdParseException e)
			{
				context.restoreSnapshot(contextSnapshot);
				args.restoreSnapshot(argsSnapshot);
			}
	}
}
