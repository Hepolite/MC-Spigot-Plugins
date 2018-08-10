package com.hepolite.api.cmd.elements;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdParseException;
import com.hepolite.api.user.IUser;

public class CmdElementPlayer extends CmdElementValue
{
	public CmdElementPlayer(final String key)
	{
		super(key);
	}

	@Override
	protected Object parse(final IUser user, final CmdArgs args)
		throws CmdParseException
	{
		final String arg = args.consume();
		final Player player = Bukkit.getPlayer(arg);
		if (player == null)
			throw new CmdParseException(
				String.format("Expected a player, but '%s' was not", arg));
		return player;
	}
}
