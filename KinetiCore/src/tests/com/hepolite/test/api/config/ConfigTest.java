package com.hepolite.test.api.config;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hepolite.api.config.Config;
import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.Property;
import com.hepolite.test.api.util.BaseTest;

class ConfigTest extends BaseTest
{
	final IProperty filepath = new Property("test_config");
	final IProperty property = new Property("property");

	@BeforeEach
	void setUp() throws Exception
	{}
	@AfterEach
	void tearDown() throws Exception
	{
		new Config(filepath).delete();
	}

	// ...

	@Test
	void testSave()
	{
		final IConfig config = new Config(filepath);

		assertFalse(config.exists());
		config.save();
		assertTrue(config.exists());
	}
	@Test
	void testLoad()
	{
		final IConfig configA = new Config(filepath);
		final IConfig configB = new Config(filepath);

		configA.set(property, 5);
		configA.save();
		configB.load();
		assertEquals(5, configB.getInt(property));
	}
	@Test
	void testDelete()
	{
		final IConfig config = new Config(filepath);

		config.save();
		assertTrue(config.exists());
		config.delete();
		assertFalse(config.exists());
	}

	// ...

	@Test
	void testAdd()
	{
		final IConfig config = new Config();

		config.add(property.child("field"), 4);
		config.add(property.child("field"), 6);
		config.add(property.child("some.path"), 7);
		assertEquals(4, config.getInt(property.child("field")));
		assertEquals(7, config.getInt(property.child("some.path")));
	}
	@Test
	void testSet()
	{
		final IConfig config = new Config();

		config.set(property, 4);
		config.set(property, 6);
		assertEquals(6, config.getInt(property));
	}
	@Test
	void testRemove()
	{
		final IConfig config = new Config();

		config.add(property, 3);
		assertTrue(config.has(property));
		config.remove(property);
		assertFalse(config.has(property));
	}
	@Test
	void testClear()
	{
		final IConfig config = new Config();

		config.add(property, 3);
		assertTrue(config.has(property));
		config.clear();
		assertFalse(config.has(property));
	}

	@Test
	void testProperties()
	{
		final IConfig config = new Config();
		config.add(property.child("A"), 0);
		config.add(property.child("B"), 0);

		assertTrue(config.properties().contains(property));
		final Set<IProperty> properties = config.properties(property);
		assertTrue(properties.contains(new Property("A")));
		assertTrue(properties.contains(new Property("B")));
	}

	// ...

	@Test
	void testGetBool()
	{
		final boolean value = true;
		final boolean[] values = bools(true, false, true);

		final IConfig config = new Config();
		config.set(property.child("one"), value);
		config.set(property.child("many"), values);

		assertEquals(value, config.getBool(property.child("one")));
		assertArrayEquals(values, config.getBools(property.child("many")));
	}
	@Test
	void testGetByte()
	{
		final byte value = (byte) 65;
		final byte[] values = bytes((byte) 12, (byte) 78, (byte) -32);

		final IConfig config = new Config();
		config.set(property.child("one"), value);
		config.set(property.child("many"), values);

		assertEquals(value, config.getByte(property.child("one")));
		assertArrayEquals(values, config.getBytes(property.child("many")));
	}
	@Test
	void testGetDouble()
	{
		final double value = 3.14159;
		final double[] values = doubles(-5.5, 10E45, 5.7E-4);

		final IConfig config = new Config();
		config.set(property.child("one"), value);
		config.set(property.child("many"), values);

		assertEquals(value, config.getDouble(property.child("one")));
		assertArrayEquals(values, config.getDoubles(property.child("many")));
	}
	@Test
	void testGetFloat()
	{
		final float value = 1.667f;
		final float[] values = floats(-5.16f, 61.3f, 1561.9f);

		final IConfig config = new Config();
		config.set(property.child("one"), value);
		config.set(property.child("many"), values);

		assertEquals(value, config.getFloat(property.child("one")));
		assertArrayEquals(values, config.getFloats(property.child("many")));
	}
	@Test
	void testGetInt()
	{
		final int value = 42;
		final int[] values = ints(-5, 1670788327, 63);

		final IConfig config = new Config();
		config.set(property.child("one"), value);
		config.set(property.child("many"), values);

		assertEquals(value, config.getInt(property.child("one")));
		assertArrayEquals(values, config.getInts(property.child("many")));
	}
	@Test
	void testGetLong()
	{
		final long value = 42;
		final long[] values = longs(-3L, 0xFFFFFFFFFFFFL, 56161L);

		final IConfig config = new Config();
		config.set(property.child("one"), value);
		config.set(property.child("many"), values);

		assertEquals(value, config.getLong(property.child("one")));
		assertArrayEquals(values, config.getLongs(property.child("many")));
	}
	@Test
	void testGetShort()
	{
		final short value = 42;
		final short[] values = shorts((short) -5, (short) 127, (short) 5);

		final IConfig config = new Config();
		config.set(property.child("one"), value);
		config.set(property.child("many"), values);

		assertEquals(value, config.getShort(property.child("one")));
		assertArrayEquals(values, config.getShorts(property.child("many")));
	}
	@Test
	void testGetString()
	{
		final String value = "Hello World!";
		final String[] values = strings("This", "is", "text");

		final IConfig config = new Config();
		config.set(property.child("one"), value);
		config.set(property.child("many"), values);

		assertEquals(value, config.getString(property.child("one")));
		assertArrayEquals(values, config.getStrings(property.child("many")));
	}

	@Test
	void testGetValue()
	{
		final MockValue value = new MockValue(42, "Hello World!");

		final IConfig config = new Config();
		config.set(property.child("test"), 4);
		config.set(property, value);

		assertEquals(value, config.getValue(property, new MockValue()));
		assertFalse(config.has(property.child("test")));
	}
}
