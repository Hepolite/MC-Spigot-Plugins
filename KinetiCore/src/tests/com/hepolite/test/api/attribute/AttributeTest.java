package com.hepolite.test.api.attribute;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hepolite.api.attribute.Attribute;
import com.hepolite.api.attribute.Modifier;
import com.hepolite.api.config.Config;
import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.Property;

class AttributeTest
{
	private final IConfig config = new Config();
	private final IProperty property = new Property("property");

	@BeforeEach
	void setUp() throws Exception
	{
		config.clear();
	}

	// ...

	@Test
	void testGetTotal()
	{
		final Attribute attribute = new Attribute();
		attribute.setBase(6.0f);
		attribute.setModifier("A", Modifier.fromScale(0.3f));
		attribute.setModifier("B", Modifier.from(-0.05f, 1.0f, 0.5f));
		attribute.setModifier("C", Modifier.from(0.2f, 2.5f, -0.1f));

		assertEquals(17.08f, attribute.getTotal());
	}
	@Test
	void testGetModifier()
	{
		final Attribute attribute = new Attribute();
		attribute.setModifier("A", Modifier.fromScale(0.3f));
		attribute.setModifier("B", Modifier.from(-0.05f, 1.0f, 0.5f));
		attribute.setModifier("C", Modifier.from(0.2f, 2.5f, -0.1f));

		final Modifier modifier = attribute.getModifier();
		assertEquals(0.45f, modifier.scale);
		assertEquals(3.5f, modifier.constant);
		assertEquals(0.4f, modifier.multiplier);
	}

	@Test
	void testSetModifier()
	{
		final Attribute attribute = new Attribute();

		assertFalse(attribute.hasModifier("key"));
		attribute.setModifier("key", Modifier.fromScale(0.0f));
		assertTrue(attribute.hasModifier("key"));
	}
	@Test
	void testRemoveModifier()
	{
		final Attribute attribute = new Attribute();
		attribute.setModifier("key", Modifier.fromScale(0.0f));

		assertTrue(attribute.hasModifier("key"));
		attribute.removeModifier("key");
		assertFalse(attribute.hasModifier("key"));
	}

	// ...

	@Test
	void testSave()
	{
		final Attribute attributeA = new Attribute(1.0f, -1.0f, 1.0f);
		attributeA.setMax(3.2f);
		final Attribute attributeB = new Attribute(1.0f, -1.0f, 1.0f);
		attributeB.setBase(0.5f).setMin(0.1f);

		config.set(property.child("A"), attributeA);
		assertFalse(config.has(property.child("A.base")));
		assertFalse(config.has(property.child("A.min")));
		assertEquals(3.2f, config.getFloat(property.child("A.max")));
		config.set(property.child("B"), attributeB);
		assertEquals(0.5f, config.getFloat(property.child("B.base")));
		assertEquals(0.1f, config.getFloat(property.child("B.min")));
		assertFalse(config.has(property.child("B.max")));
	}
	@Test
	void testLoad()
	{
		final Attribute attributeA = new Attribute(2.0f, 0.0f, 5.0f);
		final Attribute attributeB = new Attribute(2.0f, 0.0f, 5.0f);

		config.set(property.child("A.base"), 3.0f);
		config.set(property.child("A.min"), 1.2f);
		config.set(property.child("B.max"), 4.5f);
		config.getValue(property.child("A"), attributeA);
		config.getValue(property.child("B"), attributeB);

		assertEquals(3.0f, attributeA.getBase());
		assertEquals(1.2f, attributeA.getMin());
		assertEquals(5.0f, attributeA.getMax());
		assertEquals(2.0f, attributeB.getBase());
		assertEquals(0.0f, attributeB.getMin());
		assertEquals(4.5f, attributeB.getMax());
	}
}
