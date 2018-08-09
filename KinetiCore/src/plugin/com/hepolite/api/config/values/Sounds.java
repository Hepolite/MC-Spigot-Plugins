package com.hepolite.api.config.values;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.IValue;
import com.hepolite.api.unit.Time;
import com.hepolite.kineticore.KinetiCore;

public final class Sounds implements IValue
{
	private final ArrayList<Component> components = new ArrayList<>();

	public float volume = 1.0f;

	// ...

	/**
	 * Invoked each tick for as long as the sound is playing
	 * 
	 * @param tick The current sound tick, the number of ticks since the sound
	 *            started playing
	 * @return True iff the last component of the sound has played
	 */
	public boolean update(final int tick)
	{
		return false;
	}

	/**
	 * Plays the sound globally, while only the specified players can hear it.
	 * If no players are specified, all players will be able to hear the sound.
	 * 
	 * @param players The players that should hear the sound
	 */
	public void play(final Player... players)
	{
		KinetiCore.getSounds().play(this, players);
	}
	/**
	 * Plays the sound at a specific location, while only the specified players
	 * can hear it. If no players are specified, all players will be able to
	 * hear the sound.
	 * 
	 * @param location The location the sound is played from
	 * @param players The players that should hear the sound
	 */
	public void play(final Location location, final Player... players)
	{
		KinetiCore.getSounds().play(this, location, players);
	}

	// ...

	@Override
	public void save(final IConfig config, final IProperty property)
	{}
	@Override
	public void load(final IConfig config, final IProperty property)
	{}

	// ...

	public static final class Component implements IValue
	{
		public Sound sound = Sound.ENTITY_PLAYER_HURT;
		public Time delay = Time.fromInstant();
		public float volume = 1.0f;
		public float pitch = 1.0f;

		// ...

		public Component sound(final Sound sound)
		{
			this.sound = sound;
			return this;
		}
		public Component time(final Time delay)
		{
			this.delay = delay;
			return this;
		}
		public Component volume(final float volume)
		{
			this.volume = volume;
			return this;
		}
		public Component pitch(final float pitch)
		{
			this.pitch = pitch;
			return this;
		}

		@Override
		public void save(final IConfig config, final IProperty property)
		{}
		@Override
		public void load(final IConfig config, final IProperty property)
		{}
	}
}
