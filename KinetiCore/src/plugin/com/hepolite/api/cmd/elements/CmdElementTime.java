package com.hepolite.api.cmd.elements;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdParseException;
import com.hepolite.api.unit.Time;
import com.hepolite.api.user.IUser;

public final class CmdElementTime extends CmdElementValue
{
	public CmdElementTime(final String key)
	{
		super(key);
	}

	@Override
	protected Object parse(final IUser user, final CmdArgs args)
		throws CmdParseException
	{
		return Time.fromString(args.consume());
	}
}
