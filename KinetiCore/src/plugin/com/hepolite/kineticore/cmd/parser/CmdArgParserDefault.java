package com.hepolite.kineticore.cmd.parser;

import com.hepolite.kineticore.cmd.CmdArgs;
import com.hepolite.kineticore.cmd.CmdParseException;

public final class CmdArgParserDefault implements ICmdArgParser
{
	@Override
	public CmdArgs parse(final String input) throws CmdParseException
	{
		return new CmdArgs(input.split(" "));
	}
}
