package com.hepolite.test.api.config.values;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hepolite.api.config.Config;
import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.Property;
import com.hepolite.api.config.values.PotionType;
import com.hepolite.api.config.values.PotionTypeSet;
import com.hepolite.test.api.util.BaseTest;

class PotionTypeSetTest extends BaseTest
{
	private final IConfig config = new Config();
	private final IProperty property = new Property("property");

	private final PotionTypeSet types = new PotionTypeSet(PotionType.WEAKNESS,
		PotionType.BLINDNESS, PotionType.INVISIBILITY);

	@BeforeEach
	void setUp() throws Exception
	{
		config.clear();
	}

	@Test
	void testSave()
	{
		config.set(property, types);
		assertArrayEquals(strings("blindness", "invisibility", "weakness"),
			config.getStrings(property));
	}
	@Test
	void testLoad()
	{
		config.set(property, strings("blindness", "InvIsibiLITy", "WEAKNESS"));
		assertEquals(types, config.getValue(property, new PotionTypeSet()));
	}
}
