package com.hepolite.api.cmd;

import java.util.ArrayList;
import java.util.Collection;

public final class CmdArgs
{
	private final ArrayList<String> arguments = new ArrayList<>();
	private int index = 0;

	public CmdArgs(final Collection<String> arguments)
	{
		this.arguments.addAll(arguments);
	}
	public CmdArgs(final String... arguments)
	{
		for (final String arg : arguments)
			this.arguments.add(arg);
	}

	// ...

	/**
	 * @return The number of remaining arguments stored in the structure
	 */
	public int size()
	{
		return arguments.size() - index;
	}
	/**
	 * @return True iff there are no arguments in the structure
	 */
	public boolean empty()
	{
		return size() == 0;
	}

	// ...

	/**
	 * Consumes a single argument from the structure. The argument is in its raw
	 * form and should be processed before actually being used in a command.
	 * 
	 * @return The raw argument
	 * @throws CmdParseException If there were no more arguments
	 */
	public String consume() throws CmdParseException
	{
		return consume(1)[0];
	}
	/**
	 * Consumes the given number of arguments from the structure. The arguments
	 * are in their raw form and should be processed before actually being used
	 * in a command.
	 * 
	 * @param count The number of arguments to consume
	 * @return The raw arguments
	 * @throws CmdParseException If there were not enough arguments
	 */
	public String[] consume(final int count) throws CmdParseException
	{
		if (size() < count)
			throw new CmdParseException(String.format(
				"Attempting to extract %d argument(s), %d remains", count,
				size()));

		final String[] args = new String[count];
		for (int i = 0; i < count; ++i)
			args[i] = arguments.get(index++);
		return args;
	}

	// ...

	/**
	 * Creates a new snapshot of the argument state, which may be restored at a
	 * later point in time.
	 * 
	 * @return The snapshot of the current argument state
	 */
	public Snapshot getSnapshot()
	{
		return new Snapshot(index);
	}
	/**
	 * Restores the argument state to the state of the given snapshot.
	 * 
	 * @param snapshot The snapshot to restore to
	 */
	public void restoreSnapshot(final Snapshot snapshot)
	{
		index = snapshot.index;
	}

	// ...

	public static final class Snapshot
	{
		private final int index;

		public Snapshot(final int index)
		{
			this.index = index;
		}
	}
}
