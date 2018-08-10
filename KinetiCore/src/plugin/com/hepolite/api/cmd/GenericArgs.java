package com.hepolite.api.cmd;

import com.hepolite.api.cmd.elements.CmdElementBool;
import com.hepolite.api.cmd.elements.CmdElementChildren;
import com.hepolite.api.cmd.elements.CmdElementEnum;
import com.hepolite.api.cmd.elements.CmdElementFirst;
import com.hepolite.api.cmd.elements.CmdElementNone;
import com.hepolite.api.cmd.elements.CmdElementNumber;
import com.hepolite.api.cmd.elements.CmdElementOptional;
import com.hepolite.api.cmd.elements.CmdElementPlayer;
import com.hepolite.api.cmd.elements.CmdElementSequence;
import com.hepolite.api.cmd.elements.CmdElementString;
import com.hepolite.api.cmd.elements.CmdElementTime;
import com.hepolite.api.cmd.elements.ICmdElement;
import com.hepolite.api.config.values.PotionType;

public final class GenericArgs
{
	/**
	 * Specifies that there should be no elements
	 * 
	 * @return The element to match the input
	 */
	public static final ICmdElement none()
	{
		return new CmdElementNone();
	}

	// ...

	/**
	 * Consumes a series of arguments. Usages is the matching child's elements
	 * concatenated.
	 * 
	 * @param children The sub-commands to register
	 * @return The element to match the input
	 */
	public static final ICmdElement children(final ICmd... children)
	{
		return new CmdElementChildren(children);
	}

	/**
	 * Consumes one of the argument. Only the first valid is used, the rest are
	 * ignored.
	 * 
	 * @param elements The elements to choose from
	 * @return The element to match the input
	 */
	public static final ICmdElement first(final ICmdElement... elements)
	{
		return new CmdElementFirst(elements);
	}
	/**
	 * Consumes a series of optional arguments. Usage is the elements
	 * concatenated.
	 * 
	 * @param elements The sequence that is optional
	 * @return The element to match the input
	 */
	public static final ICmdElement optional(final ICmdElement... elements)
	{
		return new CmdElementOptional(elements);
	}
	/**
	 * Consumes a series of arguments. Usage is the elements concatenated.
	 * 
	 * @param elements The sequence that is required
	 * @return The element to match the input
	 */
	public static final ICmdElement sequence(final ICmdElement... elements)
	{
		return new CmdElementSequence(elements);
	}

	// ...

	/**
	 * Requires an argument to be a boolean.
	 * 
	 * @param key The key to store the value under
	 * @return The element to match the input
	 */
	public static final ICmdElement bool(final String key)
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
	public static final ICmdElement byteNum(final String key)
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
	public static final ICmdElement doubleNum(final String key)
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
	public static final ICmdElement floatNum(final String key)
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
	public static final ICmdElement intNum(final String key)
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
	public static final ICmdElement longNum(final String key)
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
	public static final ICmdElement shortNum(final String key)
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
	public static final ICmdElement string(final String key)
	{
		return new CmdElementString.Single(key);
	}
	/**
	 * Consumes all remaining arguments, concatenated to a single string
	 * 
	 * @param key The key to store the value under
	 * @return The element to match the input
	 */
	public static final ICmdElement remainder(final String key)
	{
		return new CmdElementString.Remaining(key);
	}

	/**
	 * Requires an argument to be a time.
	 * 
	 * @param key The key to store the value under
	 * @return The element to match the input
	 */
	public static final ICmdElement time(final String key)
	{
		return new CmdElementTime(key);
	}

	// ...

	/**
	 * Requires and argument to be a player type.
	 * 
	 * @param key The key to store the value under
	 * @return The element to match the input
	 */
	public static final ICmdElement player(final String key)
	{
		return new CmdElementPlayer(key);
	}
	/**
	 * Requires and argument to be a potion type.
	 * 
	 * @param key The key to store the value under
	 * @return The element to match the input
	 */
	public static final ICmdElement potionType(final String key)
	{
		return new CmdElementEnum<>(key, PotionType::valueOf,
			"Expected a potion type, but '%s' was not");
	}
}
