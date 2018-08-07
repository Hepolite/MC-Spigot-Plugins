package com.hepolite.api.event;

public interface IHandler extends IListener
{
	/**
	 * Invoked each and every server tick, for as long as the server is running.
	 * 
	 * @param tick The current server tick
	 */
	void onTick(int tick);
	/**
	 * Invoked whenever the plugin configurations are reloaded
	 */
	void onReload();
	/**
	 * Invoked whenever the server is shutting down
	 */
	void onDisable();
}
