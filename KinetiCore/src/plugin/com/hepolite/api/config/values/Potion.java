package com.hepolite.api.config.values;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.IValue;
import com.hepolite.api.unit.Time;
import com.hepolite.api.util.StringConverter;

public final class Potion implements IValue
{
	private static final Random random = new Random();

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

	/**
	 * Applies the potion to all specified targets.
	 * 
	 * @param targets The targets to apply the potion to
	 */
	public void apply(final LivingEntity... targets)
	{
		effects.forEach((type, effect) -> {
			// Construct actual potion effect
			final int duration = (int) effect.duration.asTicks();
			final int level = effect.level < 0 ? effect.level
				: effect.level - 1;
			final PotionEffect potion = new PotionEffect(type.get(), duration,
				level, effect.ambient, effect.particles);

			// Apply effect to all applicable targets
			for (final LivingEntity target : targets)
				if (random.nextFloat() < chance * effect.chance)
					target.addPotionEffect(potion, true);
		});
	}

	// ...

	@Override
	public void save(final IConfig config, final IProperty property)
	{
		config.set(property.child("chance"), chance);
		effects.forEach((type, effect) -> config
			.set(property.child(type.toString()), effect));
	}
	@Override
	public void load(final IConfig config, final IProperty property)
	{
		chance = config.getFloat(property.child("chance"), 1.0f);
		effects.clear();
		config.properties(property).forEach(key -> {
			final PotionType type = StringConverter.toEnum(key.name(),
				PotionType::valueOf);
			if (type != null)
				config.getValue(key, add(type));
		});
	}

	// ...

	public static final class Effect implements IValue
	{
		public int level = 1;
		public Time duration = Time.fromInstant();
		public boolean ambient = false;
		public boolean particles = true;
		public float chance = 1.0f;

		// ...

		public Effect level(final int level)
		{
			this.level = level;
			return this;
		}
		public Effect duration(final Time duration)
		{
			this.duration = duration;
			return this;
		}
		public Effect ambient(final boolean ambient)
		{
			this.ambient = ambient;
			return this;
		}
		public Effect particles(final boolean particles)
		{
			this.particles = particles;
			return this;
		}
		public Effect chance(final float chance)
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
			config.set(property.child("particles"), particles);
			config.set(property.child("chance"), chance);
		}
		@Override
		public void load(final IConfig config, final IProperty property)
		{
			level = config.getInt(property.child("level"));
			duration = config.getValue(property.child("duration"), new Time());
			ambient = config.getBool(property.child("ambient"));
			particles = config.getBool(property.child("particles"), true);
			chance = config.getFloat(property.child("chance"), 1.0f);
		}
	}
}
