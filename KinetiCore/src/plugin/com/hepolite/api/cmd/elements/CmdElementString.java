package com.hepolite.api.cmd.elements;

import org.apache.commons.lang.StringUtils;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdParseException;
import com.hepolite.api.user.IUser;

public final class CmdElementString
{
	public static final class Single extends CmdElementValue
	{
		public Single(final String key)
		{
			super(key);
		}

		@Override
		protected Object parse(final IUser user, final CmdArgs args)
			throws CmdParseException
		{
			return args.consume();
		}
	}

	public static final class Remaining extends CmdElementValue
	{
		public Remaining(final String key)
		{
			super(key);
		}

		@Override
		protected Object parse(final IUser user, final CmdArgs args)
			throws CmdParseException
		{
			final String[] data = new String[args.size()];
			for (int i = 0; i < data.length; ++i)
				data[i] = args.consume();
			return StringUtils.join(data, " ");
		}
	}
}
