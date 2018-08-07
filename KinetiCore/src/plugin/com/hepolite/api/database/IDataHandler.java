package com.hepolite.api.database;

import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.user.IUser;

public interface IDataHandler
{
	/**
	 * Invoked when a player logs out from the server, during data autosaving,
	 * or at server shutdown. Any specific player data that should be persistent
	 * must be written at this time.
	 * 
	 * @param user The user which is currently being stored
	 * @param config The config the user data should be written to
	 * @param property The location in the config the data should be written at
	 */
	void save(IUser user, IConfig config, IProperty property);
	/**
	 * Invoked when a player logs in to the server. Any player data that should
	 * be persistent must be loaded up at this time.
	 * 
	 * @param user The user which is currently being loaded
	 * @param config The config the user data should be read from
	 * @param property The location in the config the data should be read from
	 */
	void load(IUser user, IConfig config, IProperty property);

	/**
	 * Removes all data stored in the handler, usually invoked just before
	 * reloading data from the persistent data storage.
	 */
	void clear();
}
