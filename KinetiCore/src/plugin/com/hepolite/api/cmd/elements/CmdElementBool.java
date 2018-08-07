package com.hepolite.api.cmd.elements;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdParseException;
import com.hepolite.api.user.IUser;

public final class CmdElementBool extends CmdElementValue
{
	public CmdElementBool(final String key)
	{
		super(key);
	}

	@Override
	protected Object parse(final IUser user, final CmdArgs args)
		throws CmdParseException
	{
		final String arg = args.consume();
		switch (arg.toLowerCase())
		{
		case "t":
		case "true":
		case "y":
		case "yes":
		case "yep":
		case "yup":
			return true;
		case "f":
		case "false":
		case "n":
		case "no":
		case "nope":
			return false;
		default:
			throw new CmdParseException(
				String.format("Expected a bool, but '%s' is not", arg));
		}
	}
}
