package com.hepolite.api.config.values;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
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

	public Sounds()
	{
		this(1.0f);
	}
	public Sounds(final float volume)
	{
		this.volume = volume;
	}

	// ...

	/**
	 * Creates a new sound component and adds it to the sound.
	 * 
	 * @return The component instance that was created
	 */
	public Component add()
	{
		final Component component = new Component();
		components.add(component);
		return component;
	}

	/**
	 * @return The number of components associated with this sound
	 */
	public int size()
	{
		return components.size();
	}

	// ...

	/**
	 * Invoked each tick for as long as the sound is playing
	 * 
	 * @param location The location at which the sound should be played from (or
	 *            null if its played globally)
	 * @param players The players that should be allowed to hear the sound (or
	 *            null if everybody should hear it)
	 * @param tick The current sound tick, the number of ticks since the sound
	 *            started playing
	 */
	public void update(final Location location, final Player[] players,
		final int tick)
	{
		// Figure out which players to play the sound for
		final Collection<Player> targets = new ArrayList<>();
		if (players == null || players.length == 0)
			targets.addAll(Bukkit.getOnlinePlayers());
		else
			for (final Player player : players)
				targets.add(player);

		// Actually play any missing sounds
		for (final Component c : components)
		{
			if (c.delay.asTicks() != tick)
				continue;

			targets.forEach(p -> {
				if (location == null)
					p.playSound(p.getLocation(), c.sound, Float.MAX_VALUE,
						c.pitch);
				else
					p.playSound(location, c.sound, volume * c.volume, c.pitch);
			});
		}
	}
	/**
	 * Checks if the sound has played all components, and should be terminated
	 * 
	 * @param tick The number of ticks since the sound started playing
	 * @return True iff the sound is done playing
	 */
	public boolean done(final int tick)
	{
		int last = 0;
		for (final Component component : components)
			last = Math.max(last, (int) component.delay.asTicks());
		return tick >= last;
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
	{
		config.set(property.child("volume"), volume);
		for (int i = 0; i < components.size(); ++i)
			config.set(property.child("Sound " + (i + 1)), components.get(i));
	}
	@Override
	public void load(final IConfig config, final IProperty property)
	{
		volume = config.getFloat(property.child("volume"), 1.0f);
		config.properties(property).forEach(key -> {
			if (!key.name().equals("volume"))
				config.getValue(key, add());
		});
	}

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
		public Component delay(final Time delay)
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
		{
			config.set(property.child("sound"), sound.toString().toLowerCase());
			config.set(property.child("delay"), delay);
			config.set(property.child("volume"), volume);
			config.set(property.child("pitch"), pitch);
		}
		@Override
		public void load(final IConfig config, final IProperty property)
		{
			sound = config.getEnum(property.child("sound"), Sound::valueOf);
			delay = config.getValue(property.child("delay"), delay);
			volume = config.getFloat(property.child("volume"), 1.0f);
			pitch = config.getFloat(property.child("pitch"), 1.0f);
		}
	}
}
