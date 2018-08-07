package com.hepolite.api.cmd.elements;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.CmdParseException;
import com.hepolite.api.user.IUser;

public class CmdElementNone implements ICmdElement
{
	@Override
	public void parse(final IUser user, final CmdArgs args,
		final CmdContext context) throws CmdParseException
	{
		if (!args.empty())
			throw new CmdParseException(
				"Expected no arguments but received at least one");
	}
}
