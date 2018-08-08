package com.hepolite.api.cmd;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map.Entry;

import com.hepolite.api.chat.Color;
import com.hepolite.api.chat.Text;
import com.hepolite.api.cmd.parser.CmdArgParserString;
import com.hepolite.api.cmd.parser.ICmdArgParser;
import com.hepolite.api.user.IUser;

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
		{
			user.sendMessage(Text.of(Color.RED,
				String.format("Invalid command '%s'", name)));
			return;
		}

		final ICmd cmd = entry.getKey();
		final ICmdArgParser parser = entry.getValue();
		final CmdContext context = new CmdContext();

		try
		{
			cmd.getStructure().parse(user, parser.parse(input), context);
			cmd.execute(user, context);
		}
		catch (final CmdParseException e)
		{
			user.sendMessage(Text.of(Color.RED,
				String.format("Invalid input: %s", e.getMessage())));
			return;
		}
		catch (final CmdExecutionException e)
		{
			user.sendMessage(Text.of(Color.RED,
				String.format("Error executing command: %s", e.getMessage())));
		}
		catch (final Exception e)
		{
			user.sendMessage(Text.of(Color.RED,
				"Unexpected error has occurred! See console for details"));
			e.printStackTrace();
		}
	}
}
