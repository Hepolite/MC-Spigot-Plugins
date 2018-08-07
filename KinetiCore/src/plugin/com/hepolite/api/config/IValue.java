package com.hepolite.api.config;

public interface IValue
{
	/**
	 * Stores the value to the provided configuration under the given property.
	 * 
	 * @param config The configuration to store the value in
	 * @param property The property to store the value under
	 */
	void save(IConfig config, IProperty property);
	/**
	 * Loads the value from the provided configuration under the given property.
	 * 
	 * @param config The configuration to load the value from
	 * @param property The property to load the value from
	 */
	void load(IConfig config, IProperty property);
}
