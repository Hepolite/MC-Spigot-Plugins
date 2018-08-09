package com.hepolite.test.api.config.values;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.Sound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hepolite.api.config.Config;
import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.Property;
import com.hepolite.api.config.values.Sounds;
import com.hepolite.api.config.values.Sounds.Component;
import com.hepolite.api.unit.Time;

class SoundsTest
{
	private static final IConfig config = new Config(new Property("test"));
	private static final IProperty property = new Property("property");

	@BeforeEach
	void setUp() throws Exception
	{
		config.clear();
	}

	@Test
	void testUpdate()
	{
		final Sounds soundA = new Sounds();
		final Sounds soundB = new Sounds();
		soundB.add().delay(Time.fromTicks(5));
		soundB.add().delay(Time.fromTicks(8));

		assertTrue(soundA.done(0));
		assertFalse(soundB.done(0));
		assertFalse(soundB.done(7));
		assertTrue(soundB.done(8));
	}

	@Test
	void testSave()
	{
		final Sounds sounds = new Sounds(0.8f);
		sounds.add().sound(Sound.AMBIENT_CAVE).volume(0.25f);
		sounds.add().sound(Sound.MUSIC_CREDITS).delay(Time.fromSeconds(6));
		config.set(property, sounds);

		assertEquals(0.8f, sounds.volume);
		final IProperty soundA = property.child("Sound 1");
		assertEquals("ambient_cave", config.getString(soundA.child("sound")));
		assertEquals(0.25f, config.getFloat(soundA.child("volume")));
		final IProperty soundB = property.child("Sound 2");
		assertEquals("music_credits", config.getString(soundB.child("sound")));
		assertEquals("6s", config.getString(soundB.child("delay")));
	}
	@Test
	void testLoad()
	{
		config.set(property.child("volume"), 4.5f);
		final IProperty soundA = property.child("Whatever");
		config.set(soundA.child("sound"), "AMBIENT underwater_exit");
		config.set(soundA.child("pitch"), 1.2f);
		final IProperty soundB = property.child("Other sound");
		config.set(soundB.child("sound"), "weather_rain");
		config.set(soundB.child("volume"), 3.5f);

		final Sounds sounds = config.getValue(property, new Sounds());
		assertEquals(4.5f, sounds.volume);
		assertEquals(2, sounds.size());
	}

	static class ComponentTest
	{
		@Test
		void testSave()
		{
			final Component component = new Component().sound(Sound.MUSIC_END)
				.volume(0.7f).pitch(0.45f).delay(Time.fromSeconds(2));
			config.set(property, component);

			assertEquals("music_end",
				config.getString(property.child("sound")));
			assertEquals(0.7f, config.getFloat(property.child("volume")));
			assertEquals(0.45f, config.getFloat(property.child("pitch")));
			assertEquals("2s", config.getString(property.child("delay")));
		}
		@Test
		void testLoad()
		{
			config.set(property.child("sound"), "music end");
			config.set(property.child("volume"), 0.35f);
			config.set(property.child("pitch"), 0.95f);
			config.set(property.child("delay"), Time.fromTicks(5));

			final Component comp = config.getValue(property, new Component());
			assertEquals(Sound.MUSIC_END, comp.sound);
			assertEquals(0.35f, comp.volume);
			assertEquals(0.95f, comp.pitch);
			assertEquals(5, comp.delay.asTicks());
		}
	}
}
