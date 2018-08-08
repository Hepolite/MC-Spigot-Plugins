package com.hepolite.api.attribute;

import java.util.HashMap;
import java.util.Map;

import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.database.IDataHandler;
import com.hepolite.api.user.IUser;

public final class AttributeDatabase implements IDataHandler
{
	private final Map<IUser, AttributeMap> data = new HashMap<>();
	private final Map<String, Attribute> attributes = new HashMap<>();

	/**
	 * Registers a new attribute to apply to all users. This should only be done
	 * during server initialization.
	 * 
	 * @param key The key referring to the attribute
	 * @param attribute The base values of the attribute
	 */
	public void register(final String key, final Attribute attribute)
	{
		if (attributes.containsKey(key))
			throw new IllegalArgumentException(
				String.format("Attribute '%s' already exists!", key));
		attributes.put(key, attribute);
	}

	// ...

	@Override
	public void save(final IUser user, final IConfig config,
		final IProperty property)
	{
		config.set(property, data.get(user));
	}
	@Override
	public void load(final IUser user, final IConfig config,
		final IProperty property)
	{
		final AttributeMap map = new AttributeMap();
		data.put(user, config.getValue(property, map));
		attributes.forEach((k, a) -> {
			if (!map.containsKey(k))
				map.put(k, new Attribute(a.getBase(), a.getMin(), a.getMax()));
		});
	}

	@Override
	public void clear()
	{
		data.clear();
	}
}
