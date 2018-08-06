package com.hepolite.kineticore.cmd.elements;

import com.hepolite.kineticore.cmd.CmdArgs;
import com.hepolite.kineticore.cmd.CmdContext;
import com.hepolite.kineticore.cmd.CmdParseException;
import com.hepolite.kineticore.user.IUser;

public interface ICmdElement
{
	/**
	 * Attempts to parse the command element. Any elements that may cause
	 * rollbacks in either the arguments or the context must take special care
	 * to prevent values bleeding out into the surroundings if parsing failed.
	 * 
	 * @param user The user running the command
	 * @param args The arguments provided by the user
	 * @param context The command context
	 * @throws CmdParseException If something failed
	 */
	void parse(IUser user, CmdArgs args, CmdContext context)
		throws CmdParseException;
}
