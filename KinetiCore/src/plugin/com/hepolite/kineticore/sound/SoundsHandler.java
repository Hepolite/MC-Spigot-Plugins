package com.hepolite.kineticore.sound;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.hepolite.api.config.values.Sounds;
import com.hepolite.api.event.Handler;

public final class SoundsHandler extends Handler
{
	private final ArrayList<Sounds> sounds = new ArrayList<>();

	public SoundsHandler(final JavaPlugin plugin)
	{
		super(plugin);
	}

	@Override
	public void onTick(final int tick)
	{}

	// ...

	/**
	 * Plays the sound globally, while only the specified players can hear it.
	 * If no players are specified, all players will be able to hear the sound.
	 * 
	 * @param sound The sound to play
	 * @param players The players that should hear the sound
	 */
	public void play(final Sounds sound, final Player... players)
	{}
	/**
	 * Plays the sound at a specific location, while only the specified players
	 * can hear it. If no players are specified, all players will be able to
	 * hear the sound.
	 * 
	 * @param sound The sound to play
	 * @param location The location the sound is played from
	 * @param players The players that should hear the sound
	 */
	public void play(final Sounds sound, final Location location,
		final Player... players)
	{}
}
