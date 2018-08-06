package com.hepolite.kineticore.cmd.parser;

import com.hepolite.kineticore.cmd.CmdArgs;

public interface ICommandArgumentParser
{
	/**
	 * Parses the input into a sequence of arguments.
	 * 
	 * @param input The raw command input from the user
	 * @return The parsed arguments
	 * @throws CommandParseException If the parsing failed for any reason
	 */
	CmdArgs parse(String input) throws CommandParseException;
}
