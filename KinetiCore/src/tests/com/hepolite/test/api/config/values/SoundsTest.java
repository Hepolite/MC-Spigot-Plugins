package com.hepolite.test.api.config.values;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.Sound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hepolite.api.config.Config;
import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.Property;
import com.hepolite.api.config.values.Sounds.Component;
import com.hepolite.api.unit.Time;

class SoundsTest
{
	private static final IConfig config = new Config();
	private static final IProperty property = new Property("property");

	@BeforeEach
	void setUp() throws Exception
	{
		config.clear();
	}

	@Test
	void test()
	{
		fail("Not yet implemented");
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
