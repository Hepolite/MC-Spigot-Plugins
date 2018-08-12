package com.hepolite.api.attribute;

import java.util.HashMap;

import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.IValue;

public class AttributeMap extends HashMap<String, Attribute> implements IValue
{
	private static final long serialVersionUID = 4361760516595192086L;

	// ...

	@Override
	public void save(final IConfig config, final IProperty property)
	{
		forEach((key, attr) -> attr.save(config, property.child(key)));
	}
	@Override
	public void load(final IConfig config, final IProperty property)
	{
		config.properties(property).forEach(key -> {
			if (containsKey(key.name()))
				config.getValue(key, get(key.name()));
		});
	}
}
