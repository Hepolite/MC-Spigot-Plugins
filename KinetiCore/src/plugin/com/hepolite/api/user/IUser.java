package com.hepolite.api.user;

import java.util.UUID;

import com.hepolite.api.chat.Text;

public interface IUser
{
	/**
	 * Retrieves the unique identifier for this specific user
	 * 
	 * @return The UUID of the user
	 */
	UUID getUUID();
	/**
	 * Retrieves the name of the user. The name may be formatted and colored if
	 * the underlying user has some special name. For players, this will return
	 * their displayed name (or username if there is no custom name). For
	 * entities this will be their name (or type if they have no name).
	 * 
	 * @return The name of the user
	 */
	String getName();

	// ...

	/**
	 * Checks if the user has the specified permission
	 * 
	 * @param permission The permission to check
	 * @return True iff the user has the specified permission
	 */
	boolean hasPermission(String permission);

	/**
	 * Sends the specified text as a message to the user. Only the user will be
	 * able to see the message.
	 * 
	 * @param text The message to send to the user
	 */
	void sendMessage(Text text);
}
