package com.hepolite.api.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.hepolite.api.cmd.CmdDispatcher;
import com.hepolite.api.cmd.CmdHandler;
import com.hepolite.api.event.Handler;

public abstract class Plugin extends JavaPlugin implements IPlugin
{
	protected final Handler handler = new Handler(this);
	protected final CmdHandler commands = new CmdHandler();

	// ...

	@Override
	public void onDisable()
	{
		handler.onDisable();
	}
	@Override
	public void onReload()
	{
		handler.onReload();
	}
	@Override
	public void onTick(final int tick)
	{
		handler.onTick(tick);
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command,
		final String label, final String[] args)
	{
		return CmdDispatcher.dispatch(sender, commands, args);
	}
}
