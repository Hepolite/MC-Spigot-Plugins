package com.hepolite.api.config.values;

import java.util.Map;
import java.util.TreeMap;

import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.IValue;
import com.hepolite.api.unit.Time;
import com.hepolite.api.util.StringConverter;

public final class Potion implements IValue
{
	private final Map<PotionType, Effect> effects = new TreeMap<>();
	public float chance;

	public Potion()
	{
		this(1.0f);
	}
	public Potion(final float chance)
	{
		this.chance = chance;
	}

	// ...

	/**
	 * Adds a new potion type to the potion. If the type already exists, the old
	 * value is overwritten.
	 * 
	 * @param type The type to add
	 * @return The effect instance that was added
	 */
	public Effect add(final PotionType type)
	{
		final Effect effect = new Effect();
		effects.put(type, effect);
		return effect;
	}
	/**
	 * Removes the potion type from the potion.
	 * 
	 * @param type The potion type to remove
	 */
	public void remove(final PotionType type)
	{
		effects.remove(type);
	}

	/**
	 * Checks if the given potion type exists within this potion.
	 * 
	 * @param type The potion type to look for
	 * @return True iff the potion type was found
	 */
	public boolean has(final PotionType type)
	{
		return effects.containsKey(type);
	}
	/**
	 * Retrieves the effect of the given type, if it exists. Otherwise, null is
	 * returned.
	 * 
	 * @param type The potion type to retrieve
	 * @return The effect instance, if it exists
	 */
	public Effect get(final PotionType type)
	{
		return effects.get(type);
	}

	// ...

	@Override
	public void save(final IConfig config, final IProperty property)
	{
		effects.forEach((type, effect) -> config
			.set(property.child(type.toString()), effect));
		config.set(property.child("chance"), chance);
	}
	@Override
	public void load(final IConfig config, final IProperty property)
	{
		config.properties(property).forEach(key -> {
			final PotionType type = StringConverter.toEnum(key.name(),
				PotionType::valueOf);
			if (type != null)
				effects.put(type, config.getValue(key, new Effect()));
		});
		chance = config.getFloat(property.child("chance"), 1.0f);
	}

	// ...

	public static final class Effect implements IValue
	{
		public int level;
		public Time duration;
		public boolean ambient;
		public float chance;

		public Effect()
		{
			this(1, Time.fromInstant(), false, 1.0f);
		}
		public Effect(final int level, final Time duration,
			final boolean ambient, final float chance)
		{
			this.level = level;
			this.duration = duration;
			this.ambient = ambient;
			this.chance = chance;
		}

		// ...

		public Effect setLevel(final int level)
		{
			this.level = level;
			return this;
		}
		public Effect setDuration(final Time duration)
		{
			this.duration = duration;
			return this;
		}
		public Effect setAmbient(final boolean ambient)
		{
			this.ambient = ambient;
			return this;
		}
		public Effect setChance(final float chance)
		{
			this.chance = chance;
			return this;
		}

		@Override
		public void save(final IConfig config, final IProperty property)
		{
			config.set(property.child("level"), level);
			config.set(property.child("duration"), duration);
			config.set(property.child("ambient"), ambient);
			config.set(property.child("chance"), chance);
		}
		@Override
		public void load(final IConfig config, final IProperty property)
		{
			level = config.getInt(property.child("level"));
			duration = config.getValue(property.child("duration"), new Time());
			ambient = config.getBool(property.child("ambient"));
			chance = config.getFloat(property.child("chance"), 1.0f);
		}
	}
}
