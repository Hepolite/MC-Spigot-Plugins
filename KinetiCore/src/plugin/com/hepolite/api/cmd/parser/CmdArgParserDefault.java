package com.hepolite.api.cmd.parser;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdParseException;

public final class CmdArgParserDefault implements ICmdArgParser
{
	@Override
	public CmdArgs parse(final String input) throws CmdParseException
	{
		return new CmdArgs(input.split(" "));
	}
}
