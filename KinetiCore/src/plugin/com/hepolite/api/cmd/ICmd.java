package com.hepolite.api.cmd;

import com.hepolite.api.cmd.elements.ICmdElement;
import com.hepolite.api.user.IUser;

public interface ICmd
{
	/**
	 * Generates the overall structure of the command. This determines whether
	 * there are any child commands and arguments, as well as the order.
	 * 
	 * @return The overall structure of the command
	 */
	ICmdElement getStructure();

	/**
	 * Generates the name of the command. The user must specify this name to
	 * execute the command.
	 * 
	 * @return The name of the command
	 */
	String getName();
	/**
	 * Generates a description explaining what the command will do when
	 * executed.
	 * 
	 * @return The description of the command
	 */
	String getDescription();
	/**
	 * Generates a help section, which explains how the command should be used.
	 * 
	 * @return The help section of the command
	 */
	String getHelp();

	/**
	 * Performs the command logic, the actual command implementation.
	 * 
	 * @param user The user invoking the command
	 * @param context The context of the command
	 * @throws CmdExecutionException If something went wrong
	 */
	void execute(IUser user, CmdContext context) throws CmdExecutionException;
}
