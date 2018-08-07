package com.hepolite.kineticore.database;

import java.util.HashMap;
import java.util.Map;

import com.hepolite.api.config.Config;
import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.Property;
import com.hepolite.api.database.IDataHandler;
import com.hepolite.api.user.IUser;
import com.hepolite.kineticore.KinetiCore;

public final class Database
{
	private final Map<String, IDataHandler> handlers = new HashMap<>();
	private final Map<IUser, IConfig> data = new HashMap<>();

	private IProperty dataFolder = new Property();

	/**
	 * Assigns the folder where all data should be stored to. This path is
	 * relative to where the server is running from, NOT any specific plugin
	 * data folder!
	 * 
	 * @param folder The path to the folder
	 */
	public void setDataFolder(final IProperty folder)
	{
		this.dataFolder = folder;
	}

	/**
	 * Registers the data handler to the database. The data will be
	 * automatically saved whenever a player logs out, the server shuts down, or
	 * when the autosave time interval has passed.
	 * 
	 * @param key The key to register the data handler under
	 * @param handler The data handler that should be registered
	 */
	public void register(final String key, final IDataHandler handler)
	{
		if (handlers.containsKey(key))
			throw new IllegalArgumentException(
				String.format("A handler with key '%s' exists already!", key));
		handlers.put(key, handler);
	}

	// ...

	/**
	 * Saves every online user to their persistent data storage
	 */
	public void save()
	{
		data.keySet().forEach(this::save);
	}
	/**
	 * Saves the specific user's data to its persistent data storage on disk.
	 * 
	 * @param user The user to save
	 */
	public void save(final IUser user)
	{
		final IConfig config = getConfigForUser(user);
		handlers.forEach((key, handler) -> {
			handler.save(user, config, new Property(key));
		});
		config.save();
	}
	/**
	 * Loads the specific user's persistent data from data storage on disk.
	 * 
	 * @param user The user to load
	 */
	public void load(final IUser user)
	{
		final IConfig config = getConfigForUser(user);
		config.load();
		handlers.forEach((key, handler) -> {
			handler.clear();
			handler.load(user, config, new Property(key));
		});
	}

	/**
	 * Erases all persistent data associated with the given user. THERE WILL BE
	 * NO RETURN IF THIS IS EVER INVOKED! You have been warned!
	 * 
	 * @param user The user to utterly and completely annihilate from the
	 *            database
	 */
	public void erase(final IUser user)
	{
		KinetiCore
			.WARN(String.format("Erasing all config data for '%s'!", user));

		handlers.values().forEach(handler -> handler.clear());
		getConfigForUser(user).delete();
		data.remove(user);
	}

	// ...

	private IConfig getConfigForUser(final IUser user)
	{
		if (data.containsKey(user))
			return data.get(user);

		KinetiCore.INFO(
			String.format("Found no data for '%s', creating config...", user));

		final IProperty path = dataFolder.child(user.getUUID().toString());
		final IConfig config = new Config(path);
		data.put(user, config);
		return config;
	}
}
