package com.hepolite.kineticore.cmd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public final class CmdContext
{
	private final Multimap<String, Object> values = ArrayListMultimap.create();

	// ...

	/**
	 * Checks if the context contains the given key.
	 * 
	 * @param key The key to check if exists
	 * @return True iff there is any value in the context under the given key
	 */
	public boolean has(final String key)
	{
		return values.containsKey(key);
	}

	/**
	 * Adds the given value to the context and stores it under the given key.
	 * 
	 * @param key The key to store the value under
	 * @param value The value to store in the context
	 */
	public <T> void put(final String key, final T value)
	{
		if (value == null)
			throw new IllegalArgumentException("Value cannot be null");
		values.put(key, value);
	}

	/**
	 * Retrieves the value stored under the given key if it exists. If multiple
	 * values are stored in the context, the value returned is the first value
	 * added under the key.
	 * 
	 * @param key The key to retrieve the value from
	 * @return The value under the given key if it exists
	 */
	public <T> Optional<T> get(final String key)
	{
		if (!has(key))
			return Optional.empty();

		@SuppressWarnings("unchecked")
		final Collection<T> contents = (Collection<T>) values.get(key);
		if (contents.isEmpty())
			return Optional.empty();
		return Optional.of(contents.iterator().next());
	}
	/**
	 * Retrieves the value stored under the given key if it exists. If multiple
	 * values are stored in the context, the value returned is the first value
	 * added under the key. If the key does not exist, the default value is
	 * returned.
	 * 
	 * @param key The key to retrieve the value from
	 * @param def The default value
	 * @return The value under the given key if it exists
	 */
	public <T> T get(final String key, final T def)
	{
		return this.<T> get(key).orElse(def);
	}
	/**
	 * Retrieves all values stored under the given key. If the key does not
	 * exist, an empty collection is returned.
	 * 
	 * @param key The key to retrieve values from
	 * @return The values under the given key
	 */
	public <T> Collection<T> getAll(final String key)
	{
		if (!has(key))
			return new ArrayList<>();

		@SuppressWarnings("unchecked")
		final Collection<T> contents = (Collection<T>) values.get(key);
		return Collections.unmodifiableCollection(contents);
	}
	/**
	 * Retrieves all values stored under the given key. If the key does not
	 * exist, an empty array is returned.
	 * 
	 * @param key The key to retrieve values from
	 * @param def The runtime type of the underlying array. Should always be
	 *            {@code new T[0]}
	 * @return The values under the given key
	 */
	public <T> T[] getAll(final String key, final T[] def)
	{
		if (!has(key))
			return def;

		@SuppressWarnings("unchecked")
		final Collection<T> contents = (Collection<T>) values.get(key);
		return contents.toArray(def);
	}
}
