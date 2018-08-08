package com.hepolite.kineticore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.hepolite.api.attribute.AttributeDatabase;
import com.hepolite.api.cmd.CmdDispatcher;
import com.hepolite.api.cmd.CmdHandler;
import com.hepolite.api.config.Property;
import com.hepolite.api.event.Handler;
import com.hepolite.kineticore.attribute.Attributes;
import com.hepolite.kineticore.cmd.CmdDebug;
import com.hepolite.kineticore.database.Database;
import com.hepolite.kineticore.database.DatabaseHandler;

public final class KinetiCore extends JavaPlugin
{
	private static KinetiCore INSTANCE;
	private final Handler handler = new Handler(this);
	private final CmdHandler commands = new CmdHandler();

	private final Database database = new Database();
	private final AttributeDatabase attributes = new AttributeDatabase();

	// ...

	/**
	 * Register a custom user data handler only in the plugin initialization
	 * method. Make sure the plugin initialization order is such that KinetiCore
	 * initializes first.
	 * 
	 * @return The database instance
	 */
	public static Database getDatabase()
	{
		return INSTANCE.database;
	}
	/**
	 * @return The attributes database instance
	 */
	public static AttributeDatabase getAttributes()
	{
		return INSTANCE.attributes;
	}

	// ...

	public static final void INFO(final String msg)
	{
		if (INSTANCE != null)
			INSTANCE.getLogger().info(msg);
	}
	public static final void WARN(final String msg)
	{
		if (INSTANCE != null)
			INSTANCE.getLogger().warning(msg);
	}
	public static final void FATAL(final String msg)
	{
		if (INSTANCE != null)
			INSTANCE.getLogger().severe("!!FATAL!! " + msg);
	}

	// ...

	@Override
	public void onEnable()
	{
		INSTANCE = this;

		// Set up utilities
		commands.register(new CmdDebug());

		database.setDataFolder(new Property(getDataFolder()).child("users"));
		database.register("attributes", attributes);

		// Ensure sub-systems are ready to roll
		handler.register(new Attributes(this, attributes));
		handler.register(new DatabaseHandler(this, database));
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command,
		final String label, final String[] args)
	{
		return CmdDispatcher.dispatch(sender, commands, args);
	}
}
