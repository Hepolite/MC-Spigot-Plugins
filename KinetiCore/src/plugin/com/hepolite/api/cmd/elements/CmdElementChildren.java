package com.hepolite.api.cmd.elements;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.CmdParseException;
import com.hepolite.api.cmd.ICmd;
import com.hepolite.api.user.IUser;

public class CmdElementChildren implements ICmdElement
{
	private final ICmd[] children;

	public CmdElementChildren(final ICmd... children)
	{
		this.children = children;
	}

	@Override
	public void parse(final IUser user, final CmdArgs args,
		final CmdContext context) throws CmdParseException
	{
		final String name = args.consume();

		for (final ICmd child : children)
			if (name.equalsIgnoreCase(child.getName()))
			{
				child.getStructure().parse(user, args, context);
				child.execute(user, context);
				return;
			}
		throw new CmdParseException(
			String.format("Expected a child command, but '%s' is not", name));
	}
}
