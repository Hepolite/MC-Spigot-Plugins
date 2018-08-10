package com.hepolite.api.cmd.elements;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdArgs.Snapshot;
import com.hepolite.api.cmd.CmdParseException;
import com.hepolite.api.user.IUser;

public class CmdElementPlayerOrUser extends CmdElementValue
{
	public CmdElementPlayerOrUser(final String key)
	{
		super(key);
	}

	@Override
	protected Object parse(final IUser user, final CmdArgs args)
		throws CmdParseException
	{
		String argument = "<missing>";
		Player player = null;

		final Snapshot snapshot = args.getSnapshot();
		try
		{
			argument = args.consume();
			player = Bukkit.getPlayer(argument);
		}
		catch (final CmdParseException e)
		{}
		if (player == null)
		{
			args.restoreSnapshot(snapshot);
			player = Bukkit.getPlayer(user.getUUID());
		}

		if (player == null)
			throw new CmdParseException(String.format(
				"Expected a player, but neither '%s' nor you were one",
				argument, user.getUUID()));
		return player;
	}
}
