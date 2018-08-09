package com.hepolite.kineticore.sound;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.hepolite.api.config.values.Sounds;
import com.hepolite.api.event.Handler;

public final class SoundsHandler extends Handler
{
	private final ArrayList<Entry> entries = new ArrayList<>();
	private int currentTick = 0;

	public SoundsHandler(final JavaPlugin plugin)
	{
		super(plugin);
	}

	@Override
	public void onTick(final int tick)
	{
		currentTick = tick;

		final Collection<Integer> toRemove = new ArrayList<>();
		for (int i = 0; i < entries.size(); ++i)
		{
			final Entry entry = entries.get(i);

			final int currentTick = tick - entry.start;
			Location location = entry.location;
			if (entry.source != null && entry.source.isValid())
				location = entry.source.getLocation();

			entry.sound.update(location, entry.players, currentTick);
			if (entry.sound.done(currentTick))
				toRemove.add(i);
		}
		toRemove.forEach(index -> {
			final int last = entries.size() - 1;
			if (index != last)
				entries.set(index, entries.get(last));
			entries.remove(last);
		});
	}

	// ...

	/**
	 * Plays the sound globally, while only the specified players can hear it.
	 * If no players are specified, all players will be able to hear the sound.
	 * 
	 * @param sound The sound to play
	 * @param players The players that should hear the sound
	 */
	public void play(final Sounds sound, final Player... players)
	{
		entries.add(new Entry(null, null, currentTick, sound, players));
	}
	/**
	 * Plays the sound from the specified location, while only the specified
	 * players can hear it. If no players are specified, all players will be
	 * able to hear the sound.
	 * 
	 * @param location The location the sound is attached to
	 * @param sound The sound to play
	 * @param players The players that should hear the sound
	 */
	public void play(final Location location, final Sounds sound,
		final Player... players)
	{
		entries.add(new Entry(null, location, currentTick, sound, players));
	}
	/**
	 * Plays the sound attached to the specified target, while only the
	 * specified players can hear it. If no players are specified, all players
	 * will be able to hear the sound.
	 * 
	 * @param entity The entity the sound is attached to
	 * @param sound The sound to play
	 * @param players The players that should hear the sound
	 */
	public void play(final Entity entity, final Sounds sound,
		final Player... players)
	{
		entries.add(new Entry(entity, null, currentTick, sound, players));
	}

	// ...

	private static final class Entry
	{
		private final Entity source;
		private final Location location;
		private final Sounds sound;
		private final int start;
		private final Player[] players;

		private Entry(final Entity source, final Location location,
			final int start, final Sounds sound, final Player[] players)
		{
			this.source = source;
			this.location = location;
			this.sound = sound;
			this.start = start;
			this.players = players;
		}
	}
}
