package com.hepolite.kineticore.database;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.hepolite.api.event.Handler;
import com.hepolite.api.user.UserFactory;

public final class DatabaseHandler extends Handler
{
	private final Database database;

	public DatabaseHandler(final JavaPlugin plugin, final Database database)
	{
		super(plugin);
		this.database = database;
	}

	@Override
	public void onDisable()
	{
		database.save();
	}

	// ...

	/**
	 * When a player joins the server, their database entries must be
	 * initialized.
	 * 
	 * @param event The player join event
	 */
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerJoin(final PlayerJoinEvent event)
	{
		database.load(UserFactory.from(event.getPlayer()));
	}
	/**
	 * When a player leaves the server, their database entries must be saved to
	 * remain persistent.
	 * 
	 * @param event The player quit event
	 */
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerQuit(final PlayerQuitEvent event)
	{
		database.save(UserFactory.from(event.getPlayer()));
	}
}
