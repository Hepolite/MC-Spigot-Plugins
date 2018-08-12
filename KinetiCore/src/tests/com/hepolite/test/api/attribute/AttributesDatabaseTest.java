package com.hepolite.test.api.attribute;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hepolite.api.attribute.Attribute;
import com.hepolite.api.attribute.AttributeDatabase;
import com.hepolite.api.config.Config;
import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.Property;
import com.hepolite.api.user.IUser;
import com.hepolite.api.user.UserConsole;

class AttributesDatabaseTest
{
	private final IConfig config = new Config();
	private final IProperty property = new Property("property");
	private final IUser user = new UserConsole();

	private final AttributeDatabase database = new AttributeDatabase();
	private final String key = "key";

	@BeforeEach
	void setUp() throws Exception
	{
		config.clear();

		database.clear();
		database.register(key, new Attribute(1.0f, -1.0f, 1.0f));
	}

	@Test
	void testGet()
	{
		final Attribute attr = database.get(user, key);
		assertEquals(1.0f, attr.getBase());
		assertEquals(-1.0f, attr.getMin());
		assertEquals(1.0f, attr.getMax());
		assertThrows(IllegalArgumentException.class,
			() -> database.get(user, "invalid"));
	}

	@Test
	void testSave()
	{
		database.get(user, key).setBase(0.2f);
		database.save(user, config, property);

		final IProperty path = property.child(key);
		assertEquals(0.2f, config.getFloat(path.child("base")));
		assertFalse(config.has(path.child("min")));
		assertFalse(config.has(path.child("max")));
	}
	@Test
	void testLoad()
	{
		final IProperty path = property.child(key);
		config.set(path.child("base"), 0.4f);

		database.load(user, config, property);
		final Attribute attribute = database.get(user, key);
		assertEquals(0.4f, attribute.getBase());
		assertEquals(-1.0f, attribute.getMin());
		assertEquals(1.0f, attribute.getMax());
	}
}
