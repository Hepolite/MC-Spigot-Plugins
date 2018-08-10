package com.hepolite.api.task;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.hepolite.api.unit.Time;

public final class TaskSynchronized implements ITask
{
	private final JavaPlugin plugin;
	private final Runnable payload;

	private int id = -1;

	public TaskSynchronized(final JavaPlugin plugin, final Runnable payload)
	{
		this.plugin = plugin;
		this.payload = payload;
	}

	@Override
	public boolean start(final Time startup)
	{
		// Due to a bug in scheduling, a repeating task must be scheduled
		// instead...
		// return start(startup, Time.fromYears(100));
		if (id != -1)
			return false;
		if (startup.asTicks() == 0)
			id = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, payload);
		else
			id = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, payload,
				startup.asTicks());
		return id != -1;
	}
	@Override
	public boolean start(final Time startup, final Time repeat)
	{
		if (id != -1)
			return false;
		id = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, payload,
			startup.asTicks(), repeat.asTicks());
		return id != -1;
	}
	@Override
	public boolean stop()
	{
		if (id == -1)
			return false;
		Bukkit.getServer().getScheduler().cancelTask(id);
		id = -1;
		return true;
	}
}
