package com.hepolite.kineticore.cmd;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map.Entry;

import com.hepolite.kineticore.cmd.parser.CmdArgParserString;
import com.hepolite.kineticore.cmd.parser.ICmdArgParser;
import com.hepolite.kineticore.user.IUser;

public final class CmdHandler
{
	private final HashMap<String, Entry<ICmd, ICmdArgParser>> cmds = new HashMap<>();

	// ...

	/**
	 * Registers the given command. The command will be parsed using the string
	 * form, where each argument may be separated using quotation marks.
	 * 
	 * @param cmd The command to register
	 */
	public void register(final ICmd cmd)
	{
		register(cmd, new CmdArgParserString());
	}
	/**
	 * Registers the given command. The command will be parsed using the
	 * provided parser.
	 * 
	 * @param cmd The command to register
	 * @param parser The parser to parse the command input
	 */
	public void register(final ICmd cmd, final ICmdArgParser parser)
	{
		cmds.put(cmd.getName().toLowerCase(),
			new AbstractMap.SimpleEntry<>(cmd, parser));
	}

	// ...

	/**
	 * Attempts to execute the given input command as the provided user.
	 * 
	 * @param user The user executing the command
	 * @param name The name of the command to execute
	 * @param input The input entered by the user
	 */
	public void execute(final IUser user, final String name, final String input)
	{
		final Entry<ICmd, ICmdArgParser> entry = cmds.get(name.toLowerCase());
		if (entry == null)
			return; // TODO: Give message to user, saying invalid cmd

		final ICmd cmd = entry.getKey();
		final ICmdArgParser parser = entry.getValue();
		final CmdContext context = new CmdContext();

		try
		{
			cmd.getStructure().parse(user, parser.parse(input), context);
		}
		catch (final CmdParseException e)
		{
			// TODO: Give message to user, saying invalid input
			return;
		}

		try
		{
			cmd.execute(user, context);
		}
		catch (final CmdExecutionException e)
		{
			// TODO: Give message to user, saying problem executing command
		}
	}
}
