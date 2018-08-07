package com.hepolite.api.cmd.elements;

import java.util.function.BiFunction;
import java.util.function.Function;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdParseException;
import com.hepolite.api.user.IUser;

public final class CmdElementNumber<T extends Number> extends CmdElementValue
{
	private final Function<String, T> defaultParser;
	private final BiFunction<String, Integer, T> radixParser;

	private final String errorMessage;

	public CmdElementNumber(final String key,
		final Function<String, T> defaultParser,
		final BiFunction<String, Integer, T> radixParser,
		final String errorMessage)
	{
		super(key);
		this.defaultParser = defaultParser;
		this.radixParser = radixParser;
		this.errorMessage = errorMessage;
	}

	@Override
	protected Object parse(final IUser user, final CmdArgs args)
		throws CmdParseException
	{
		final String arg = args.consume();
		try
		{
			if (radixParser != null)
			{
				if (arg.startsWith("0x"))
					return radixParser.apply(arg.substring(2), 16);
				if (arg.startsWith("0b"))
					return radixParser.apply(arg.substring(2), 2);
			}
			return defaultParser.apply(arg);
		}
		catch (final NumberFormatException e)
		{
			throw new CmdParseException(String.format(errorMessage, arg));
		}
	}
}
