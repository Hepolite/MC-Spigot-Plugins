package com.hepolite.kineticore.cmd.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.hepolite.kineticore.cmd.CmdArgs;

public final class CmdArgParserString implements ICmdArgParser
{
	@Override
	public CmdArgs parse(final String input) throws CmdParseException
	{
		final Worker worker = new Worker(input);

		final Collection<String> args = new ArrayList<>();
		while (!worker.done())
			worker.next().ifPresent(arg -> args.add(arg));
		return new CmdArgs(args);
	}

	// ...

	public static final class Worker
	{
		private final String input;

		private int index = 0;
		private char separator = ' ';

		public Worker(final String input)
		{
			this.input = input;
		}

		// ...

		/**
		 * @return True iff there are no more arguments to parse
		 */
		public boolean done()
		{
			return index >= input.length();
		}
		/**
		 * @return The next argument if it exists
		 */
		public Optional<String> next()
		{
			final int begin = findStart(index);
			final int end = findEnd(begin);
			index = end;

			if (begin == input.length() || end == input.length())
				return Optional.empty();
			return Optional.of(input.substring(begin, end));
		}

		// ...

		/**
		 * Attempts to find the beginning of the next argument, starting the
		 * search from the provided index.
		 * 
		 * @param index The index of the first character of the argument
		 * @return The index where the argument starts
		 */
		public int findStart(final int index)
		{
			if (index >= input.length())
				return input.length();
			final char separator = input.charAt(index);

			// If the separator is either space or quote, the next argument
			// begins on the next character, usually
			if (separator == ' ' || separator == '"')
			{
				this.separator = separator;
				return findStart(index + 1);
			}
			return index;
		}
		/**
		 * Attempts to find the end of the next argument, starting the search
		 * from the provided index.
		 * 
		 * @param index The index beyond the last character of the argument
		 * @return The index where the argument ends
		 */
		public int findEnd(final int index)
		{
			final int end = input.indexOf(separator, index);
			return end == -1 ? input.length() : end;
		}
	}
}
