package com.hepolite.test.api.attribute;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.attribute.Modifier;
import com.hepolite.api.attribute.ModifierMap;

class ModifierMapTest
{
	@Test
	void testGetTotal()
	{
		final ModifierMap map = new ModifierMap();
		map.put("A", Modifier.fromConstant(10.0f));
		map.put("B", Modifier.fromConstant(-3.0f));
		map.put("C", Modifier.fromScale(0.3f));
		map.put("D", Modifier.from(0.1f, 0.5f, -0.1f));

		final Modifier total = map.getTotal();
		assertEquals(0.4f, total.scale);
		assertEquals(7.5f, total.constant);
		assertEquals(-0.1f, total.multiplier);
	}
}
