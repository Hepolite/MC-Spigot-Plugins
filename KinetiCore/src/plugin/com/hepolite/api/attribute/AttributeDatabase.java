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

	/**
	 * Initializes the data for the provided user. All registered attributes are
	 * copied over to the user.
	 * 
	 * @param user The user to initialize
	 */
	private void init(final IUser user)
	{
		final AttributeMap map = new AttributeMap();
		attributes.forEach((k, a) -> {
			if (!map.containsKey(k))
				map.put(k, new Attribute(a.getBase(), a.getMin(), a.getMax()));
		});
		data.put(user, map);
	}
	/**
	 * Retrieves the attribute with the given key under the given user.
	 * 
	 * @param user The user to look at
	 * @param key The key to look under
	 * @return The attribute for the player at the given key
	 */
	public Attribute get(final IUser user, final String key)
	{
		if (!data.containsKey(user))
			init(user);
		final AttributeMap map = data.get(user);
		if (!map.containsKey(key))
			throw new IllegalArgumentException("No such attribute");
		return map.get(key);
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
		init(user);
		data.put(user, config.getValue(property, data.get(user)));
	}

	@Override
	public void clear()
	{
		data.clear();
	}
}
