package com.hepolite.api.cmd.elements;

import java.util.function.Function;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdParseException;
import com.hepolite.api.user.IUser;

public class CmdElementEnum<T extends Enum<T>> extends CmdElementValue
{
	private final Function<String, T> defaultParser;

	private final String errorMessage;

	public CmdElementEnum(final String key,
		final Function<String, T> defaultParser, final String errorMessage)
	{
		super(key);
		this.defaultParser = defaultParser;
		this.errorMessage = errorMessage;
	}

	@Override
	protected Object parse(final IUser user, final CmdArgs args)
		throws CmdParseException
	{
		final String arg = args.consume();
		try
		{
			return defaultParser.apply(arg.replaceAll(" ", "_").toUpperCase());
		}
		catch (final IllegalArgumentException e)
		{
			throw new CmdParseException(String.format(errorMessage, arg));
		}
	}
}
