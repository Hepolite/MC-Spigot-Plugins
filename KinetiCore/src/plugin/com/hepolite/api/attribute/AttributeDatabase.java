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
		data.put(user, config.getValue(property, new AttributeMap()));
	}

	@Override
	public void clear()
	{
		data.clear();
	}
}
