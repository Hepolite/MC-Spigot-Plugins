package com.hepolite.kineticore.cmd.elements;

import com.hepolite.kineticore.cmd.CmdArgs;
import com.hepolite.kineticore.cmd.CmdContext;
import com.hepolite.kineticore.cmd.CmdParseException;
import com.hepolite.kineticore.user.IUser;

public abstract class CmdElementValue implements ICmdElement
{
	private final String key;

	protected CmdElementValue(final String key)
	{
		this.key = key;
	}

	@Override
	public final void parse(final IUser user, final CmdArgs args,
		final CmdContext context) throws CmdParseException
	{
		final Object value = parse(user, args);
		if (value == null)
			throw new CmdParseException("Value cannot be null");

		if (value instanceof Iterable<?>)
			for (final Object object : (Iterable<?>) value)
				context.put(key, object);
		else
			context.put(key, value);
	}

	// ...

	/**
	 * Calculates a value from the given arguments as the given user.
	 * 
	 * @param user The user executing the command
	 * @param args The arguments specified by the user
	 * @return The value of the element, could be either a single value or an
	 *         iterable structure
	 * @throws CmdParseException
	 */
	protected abstract Object parse(final IUser user, final CmdArgs args)
		throws CmdParseException;
}
