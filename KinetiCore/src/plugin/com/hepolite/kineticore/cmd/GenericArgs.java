package com.hepolite.kineticore.cmd;

import com.hepolite.kineticore.cmd.elements.CmdElementBool;
import com.hepolite.kineticore.cmd.elements.CmdElementNumber;
import com.hepolite.kineticore.cmd.elements.CmdElementString;
import com.hepolite.kineticore.cmd.elements.ICmdElement;

public final class GenericArgs
{
	/**
	 * Requires an argument to be a boolean.
	 * 
	 * @param key The key to store the value under
	 * @return The element to match the input
	 */
	public final static ICmdElement bool(final String key)
	{
		return new CmdElementBool(key);
	}

	/**
	 * Requires an argument to be a byte. The value may be specified as base 2,
	 * 10 or 16.
	 * 
	 * @param key The key to store the value under
	 * @return The element to match the input
	 */
	public final static ICmdElement byteNum(final String key)
	{
		return new CmdElementNumber<>(key, Byte::parseByte, Byte::parseByte,
			"Expected a byte, but '%s' was not");
	}
	/**
	 * Requires an argument to be a double.
	 * 
	 * @param key The key to store the value under
	 * @return The element to match the input
	 */
	public final static ICmdElement doubleNum(final String key)
	{
		return new CmdElementNumber<>(key, Double::parseDouble, null,
			"Expected a double, but '%s' was not");
	}
	/**
	 * Requires an argument to be a float.
	 * 
	 * @param key The key to store the value under
	 * @return The element to match the input
	 */
	public final static ICmdElement floatNum(final String key)
	{
		return new CmdElementNumber<>(key, Float::parseFloat, null,
			"Expected a float, but '%s' was not");
	}
	/**
	 * Requires an argument to be an integer. The value may be specified as base
	 * 2, 10 or 16.
	 * 
	 * @param key The key to store the value under
	 * @return The element to match the input
	 */
	public final static ICmdElement intNum(final String key)
	{
		return new CmdElementNumber<>(key, Integer::parseInt, Integer::parseInt,
			"Expected an int, but '%s' was not");
	}
	/**
	 * Requires an argument to be a long. The value may be specified as base 2,
	 * 10 or 16.
	 * 
	 * @param key The key to store the value under
	 * @return The element to match the input
	 */
	public final static ICmdElement longNum(final String key)
	{
		return new CmdElementNumber<>(key, Long::parseLong, Long::parseLong,
			"Expected a long, but '%s' was not");
	}
	/**
	 * Requires an argument to be a short. The value may be specified as base 2,
	 * 10 or 16.
	 * 
	 * @param key The key to store the value under
	 * @return The element to match the input
	 */
	public final static ICmdElement shortNum(final String key)
	{
		return new CmdElementNumber<>(key, Short::parseShort, Short::parseShort,
			"Expected a short, but '%s' was not");
	}

	/**
	 * Requires an argument to be a string.
	 * 
	 * @param key The key to store the value under
	 * @return The element to match the input
	 */
	public final static ICmdElement string(final String key)
	{
		return new CmdElementString.Single(key);
	}
	/**
	 * Consumes all remaining arguments, concatenated to a single string
	 * 
	 * @param key The key to store the value under
	 * @return The element to match the input
	 */
	public final static ICmdElement remainder(final String key)
	{
		return new CmdElementString.Remaining(key);
	}
}
