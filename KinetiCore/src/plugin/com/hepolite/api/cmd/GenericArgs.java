package com.hepolite.api.cmd;

import com.hepolite.api.cmd.elements.CmdElementBool;
import com.hepolite.api.cmd.elements.CmdElementChildren;
import com.hepolite.api.cmd.elements.CmdElementFirst;
import com.hepolite.api.cmd.elements.CmdElementNone;
import com.hepolite.api.cmd.elements.CmdElementNumber;
import com.hepolite.api.cmd.elements.CmdElementOptional;
import com.hepolite.api.cmd.elements.CmdElementSequence;
import com.hepolite.api.cmd.elements.CmdElementString;
import com.hepolite.api.cmd.elements.ICmdElement;

public final class GenericArgs
{
	/**
	 * Specifies that there should be no elements
	 * 
	 * @return The element to match the input
	 */
	public final static ICmdElement none()
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
	public final static ICmdElement children(final ICmd... children)
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
	public final static ICmdElement first(final ICmdElement... elements)
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
	public final static ICmdElement optional(final ICmdElement... elements)
	{
		return new CmdElementOptional(elements);
	}
	/**
	 * Consumes a series of arguments. Usage is the elements concatenated.
	 * 
	 * @param elements The sequence that is required
	 * @return The element to match the input
	 */
	public final static ICmdElement sequence(final ICmdElement... elements)
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
