package com.hepolite.kineticore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class KinetiCore extends JavaPlugin
{
	@Override
	public void onEnable()
	{}
	@Override
	public void onDisable()
	{}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command,
		final String label, final String[] args)
	{
		return false;
	}
}
