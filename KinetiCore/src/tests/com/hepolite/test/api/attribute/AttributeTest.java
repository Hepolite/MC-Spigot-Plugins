package com.hepolite.test.api.attribute;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.attribute.Attribute;
import com.hepolite.api.attribute.Modifier;

class AttributeTest
{
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
}
