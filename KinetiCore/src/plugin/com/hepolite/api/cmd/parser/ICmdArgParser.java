package com.hepolite.api.cmd.parser;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdParseException;

public interface ICmdArgParser
{
	/**
	 * Parses the input into a sequence of arguments.
	 * 
	 * @param input The raw command input from the user
	 * @return The parsed arguments
	 * @throws CmdParseException If the parsing failed for any reason
	 */
	CmdArgs parse(String input) throws CmdParseException;
}
