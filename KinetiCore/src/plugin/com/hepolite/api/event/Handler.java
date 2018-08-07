package com.hepolite.api.event;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public class Handler implements IHandler
{
	private final Collection<IHandler> handlers = new ArrayList<>();
	protected final JavaPlugin plugin;

	public Handler(final JavaPlugin plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public void onTick(final int tick)
	{
		handlers.forEach(handler -> handler.onTick(tick));
	}
	@Override
	public void onReload()
	{
		handlers.forEach(handler -> handler.onReload());
	}
	@Override
	public void onDisable()
	{
		handlers.forEach(handler -> handler.onDisable());
	}

	// ...

	/**
	 * Helper function for easier time registering child handlers.
	 * 
	 * @param handler The child handler to register
	 * @return The same handler as passed in for convenience
	 */
	public <T extends IHandler> T register(final T handler)
	{
		handlers.add(handler);
		Bukkit.getPluginManager().registerEvents(handler, plugin);
		return handler;
	}
	/**
	 * Helper function for easier time registering event listeners to the Bukkit
	 * event bus.
	 * 
	 * @param listener
	 * @return The same listener as passed in for convenience
	 */
	public <T extends IListener> T register(final T listener)
	{
		Bukkit.getPluginManager().registerEvents(listener, plugin);
		return listener;
	}

	/**
	 * Helper function for easier time posting events to the Bukkit event bus.
	 * 
	 * @param event The event to post
	 * @return The same event as was passed in for convenience
	 */
	public <T extends Event> T post(final T event)
	{
		Bukkit.getServer().getPluginManager().callEvent(event);
		return event;
	}
}
