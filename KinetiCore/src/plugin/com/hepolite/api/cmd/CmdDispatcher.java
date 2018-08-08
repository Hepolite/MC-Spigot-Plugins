package com.hepolite.api.cmd;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.hepolite.api.user.IUser;
import com.hepolite.api.user.UserConsole;
import com.hepolite.api.user.UserFactory;

public final class CmdDispatcher
{
	public static boolean dispatch(final CommandSender sender,
		final CmdHandler handler, final String[] args)
	{
		final IUser user;
		if (sender instanceof ConsoleCommandSender)
			user = new UserConsole();
		else if (sender instanceof Player)
			user = UserFactory.from((Player) sender);
		else
			user = null; // TODO: Replace with command block user

		if (args.length == 0)
			return false;
		final String input = StringUtils.join(args, ' ', 1, args.length);
		handler.execute(user, args[0], input);
		return true;
	}
}
