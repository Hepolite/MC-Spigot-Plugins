package com.hepolite.api.cmd.elements;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.CmdParseException;
import com.hepolite.api.user.IUser;

public final class CmdElementSequence implements ICmdElement
{
	private final ICmdElement[] elements;

	public CmdElementSequence(final ICmdElement... elements)
	{
		this.elements = elements;
	}

	@Override
	public void parse(final IUser user, final CmdArgs args,
		final CmdContext context) throws CmdParseException
	{
		for (final ICmdElement element : elements)
			element.parse(user, args, context);
	}
}
