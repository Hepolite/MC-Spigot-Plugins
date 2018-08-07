package com.hepolite.test.api.config;

import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.IValue;

public class MockValue implements IValue
{
	public int fieldA = 0;
	public String fieldB = "";

	public MockValue()
	{}
	public MockValue(final int fieldA, final String fieldB)
	{
		this.fieldA = fieldA;
		this.fieldB = fieldB;
	}

	@Override
	public void save(final IConfig config, final IProperty property)
	{
		config.set(property.child("int"), fieldA);
		config.set(property.child("string"), fieldB);
	}
	@Override
	public void load(final IConfig config, final IProperty property)
	{
		fieldA = config.getInt(property.child("int"));
		fieldB = config.getString(property.child("string"));
	}

	@Override
	public String toString()
	{
		return String.format("[%d, %s]", fieldA, fieldB);
	}
	@Override
	public boolean equals(final Object object)
	{
		if (!(object instanceof MockValue))
			return false;
		final MockValue value = (MockValue) object;
		return fieldA == value.fieldA && fieldB.equals(value.fieldB);
	}
}
