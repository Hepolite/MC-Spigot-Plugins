package com.hepolite.kineticore.cmd.parser;

import com.hepolite.kineticore.cmd.CmdArgs;

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
