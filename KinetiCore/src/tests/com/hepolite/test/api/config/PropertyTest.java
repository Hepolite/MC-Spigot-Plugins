package com.hepolite.test.api.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.Property;

class PropertyTest
{
	@Test
	void testDefaultCtor()
	{
		final IProperty property = new Property();

		test(property, "", "", "");
	}
	@Test
	void testPathCtor()
	{
		final IProperty propertyA = new Property("");
		final IProperty propertyB = new Property("name");
		final IProperty propertyC = new Property("root.name");

		test(propertyA, "", "", "");
		test(propertyB, "name", "", "name");
		test(propertyC, "root.name", "root", "name");
	}
	@Test
	void testRootNameCtor()
	{
		final IProperty propertyA = new Property("", "");
		final IProperty propertyB = new Property("", "name");
		final IProperty propertyC = new Property("root", "name");

		test(propertyA, "", "", "");
		test(propertyB, "name", "", "name");
		test(propertyC, "root.name", "root", "name");
	}

	@Test
	void testParent()
	{
		final IProperty propertyA = new Property("child");
		final IProperty propertyB = new Property("parent.child");
		final IProperty propertyC = new Property("root.parent.child");

		test(propertyA.parent(), "", "", "");
		test(propertyB.parent(), "parent", "", "parent");
		test(propertyC.parent(), "root.parent", "root", "parent");
	}
	@Test
	void testChild()
	{
		final IProperty property = new Property("parent");
		final IProperty childA = new Property("child");
		final IProperty childB = new Property("root", "child");

		test(property.child("child"), "parent.child", "parent", "child");
		test(property.child(childA), "parent.child", "parent", "child");
		test(property.child(childB), "parent.root.child", "parent.root",
			"child");
	}

	@Test
	void testEquals()
	{
		final IProperty propertyA = new Property("root.name");
		final IProperty propertyB = new Property("root.name");
		final IProperty propertyC = new Property("root.different");

		assertTrue(propertyA.equals(propertyB));
		assertFalse(propertyA.equals(propertyC));
	}

	// ...

	void test(final IProperty property, final String path, final String root,
		final String name)
	{
		assertEquals(path, property.path());
		assertEquals(root, property.root());
		assertEquals(name, property.name());
	}
}
