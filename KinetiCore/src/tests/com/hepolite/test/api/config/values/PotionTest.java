package com.hepolite.test.api.config.values;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hepolite.api.config.Config;
import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.Property;
import com.hepolite.api.config.values.Potion;
import com.hepolite.api.config.values.Potion.Effect;
import com.hepolite.api.config.values.PotionType;
import com.hepolite.api.unit.Time;

class PotionTest
{
	private static final IConfig config = new Config();
	private static final IProperty property = new Property("property");

	@BeforeEach
	void setUp() throws Exception
	{
		config.clear();
	}

	@Test
	void testAdd()
	{
		final Potion potion = new Potion();

		assertFalse(potion.has(PotionType.SPEED));
		potion.add(PotionType.SPEED);
		assertTrue(potion.has(PotionType.SPEED));
	}
	@Test
	void testRemove()
	{
		final Potion potion = new Potion();
		potion.add(PotionType.SPEED);

		assertTrue(potion.has(PotionType.SPEED));
		potion.remove(PotionType.SPEED);
		assertFalse(potion.has(PotionType.SPEED));
	}

	@Test
	void testSave()
	{
		final Potion potion = new Potion(0.75f);
		potion.add(PotionType.SPEED).setDuration(Time.fromMinutes(10));
		potion.add(PotionType.INSTANT_HEALTH).setLevel(2);
		config.set(property, potion);

		assertEquals(0.75f, config.getFloat(property.child("chance")));
		final IProperty speed = property.child("speed");
		assertEquals(1, config.getInt(speed.child("level")));
		assertEquals("10m", config.getString(speed.child("duration")));
		assertEquals(false, config.getBool(speed.child("ambient")));
		assertEquals(1.0f, config.getFloat(speed.child("chance")));
		final IProperty health = property.child("instant_health");
		assertEquals(2, config.getInt(health.child("level")));
		assertEquals("0t", config.getString(health.child("duration")));
		assertEquals(false, config.getBool(health.child("ambient")));
		assertEquals(1.0f, config.getFloat(health.child("chance")));
	}
	@Test
	void testLoad()
	{
		config.set(property.child("chance"), 0.6f);
		final IProperty speed = property.child("speed");
		config.set(speed.child("duration"), Time.fromSeconds(5));
		config.set(speed.child("ambient"), true);
		final IProperty health = property.child("instant_health");
		config.set(health.child("level"), 4);

		final Potion potion = config.getValue(property, new Potion());
		assertEquals(0.6f, potion.chance);
		assertEquals(5, potion.get(PotionType.SPEED).duration.asSeconds());
		assertEquals(true, potion.get(PotionType.SPEED).ambient);
		assertEquals(4, potion.get(PotionType.INSTANT_HEALTH).level);
	}

	// ...

	static class EffectTest
	{
		@Test
		void testSave()
		{
			final Effect effect = new Effect(2, Time.fromTicks(15), true, 0.4f);
			config.set(property, effect);

			assertEquals(2, config.getInt(property.child("level")));
			assertEquals("15t", config.getString(property.child("duration")));
			assertEquals(true, config.getBool(property.child("ambient")));
			assertEquals(0.4f, config.getFloat(property.child("chance")));
		}
		@Test
		void testLoad()
		{
			config.set(property.child("level"), 4);
			config.set(property.child("duration"), Time.fromTicks(10));
			config.set(property.child("ambient"), true);
			config.set(property.child("chance"), 0.8f);

			final Effect effect = config.getValue(property, new Effect());
			assertEquals(4, effect.level);
			assertEquals(10, effect.duration.asTicks());
			assertEquals(true, effect.ambient);
			assertEquals(0.8f, effect.chance);
		}
	}
}
