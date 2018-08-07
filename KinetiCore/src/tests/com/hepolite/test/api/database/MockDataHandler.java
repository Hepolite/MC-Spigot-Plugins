package com.hepolite.test.api.database;

import java.util.HashMap;

import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.database.IDataHandler;
import com.hepolite.api.user.IUser;

public class MockDataHandler implements IDataHandler
{
	public HashMap<IUser, String> data = new HashMap<>();

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
		data.put(user, config.getString(property));
	}

	@Override
	public void clear()
	{
		data.clear();
	}
}
