package com.hepolite.kineticore;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;

import com.hepolite.api.attribute.AttributeDatabase;
import com.hepolite.api.config.Property;
import com.hepolite.api.plugin.IPlugin;
import com.hepolite.api.plugin.Plugin;
import com.hepolite.api.task.TaskSynchronized;
import com.hepolite.api.unit.Time;
import com.hepolite.kineticore.attribute.Attributes;
import com.hepolite.kineticore.cmd.CmdDebug;
import com.hepolite.kineticore.database.Database;
import com.hepolite.kineticore.database.DatabaseHandler;
import com.hepolite.kineticore.sound.SoundsHandler;

public final class KinetiCore extends Plugin
{
	private static KinetiCore INSTANCE;

	private final Database database = new Database();
	private final AttributeDatabase attributes = new AttributeDatabase();
	private final SoundsHandler sounds = new SoundsHandler(this);

	private final Collection<IPlugin> plugins = new ArrayList<>();
	private int currentTick = 0;

	// ...

	/**
	 * @return The KinetiCore plugin instance
	 */
	public static KinetiCore getInstance()
	{
		return INSTANCE;
	}
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

	/**
	 * @return The sounds handler instance
	 */
	public static SoundsHandler getSounds()
	{
		return INSTANCE.sounds;
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
		startTasks();

		// Set up commands
		commands.register(new CmdDebug());

		// Set up utilities
		database.setDataFolder(new Property(getDataFolder()).child("users"));
		database.register("attributes", attributes);

		// Ensure sub-systems are ready to roll
		handler.register(sounds);
		handler.register(new Attributes(this, attributes));
		handler.register(new DatabaseHandler(this, database));
	}

	// ...

	private void startTasks()
	{
		INFO("Setting up tasks...");
		if (!new TaskSynchronized(this, this::findPlugins)
			.start(Time.fromInstant()))
			FATAL("Could not start initializer task!");
		if (!new TaskSynchronized(this, this::tickPlugins)
			.start(Time.fromInstant(), Time.fromTicks(1)))
			FATAL("Could not start updater task!");
		INFO("Done setting up tasks!");
	}
	private void findPlugins()
	{
		plugins.clear();
		for (final org.bukkit.plugin.Plugin plugin : Bukkit.getPluginManager()
			.getPlugins())
			if (plugin instanceof IPlugin)
				plugins.add((IPlugin) plugin);
		INFO("Found " + plugins.size() + " plugin(s)");
	}
	private void tickPlugins()
	{
		for (final IPlugin plugin : plugins)
			plugin.onTick(currentTick);
		currentTick++;
	}
}
