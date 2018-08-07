package com.hepolite.api.user;

import java.util.UUID;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public final class UserFactory
{
	/**
	 * Constructs a new user from the provided uuid
	 * 
	 * @param uuid The uuid of the user
	 * @return A new user instance
	 */
	public static IUser from(final UUID uuid)
	{
		if (uuid.getLeastSignificantBits() == 0L
			&& uuid.getMostSignificantBits() == 0L)
			return new UserConsole();
		else
			return new UserPlayer(uuid);
	}
	/**
	 * Constructs a new user from the provided uuid
	 * 
	 * @param uuid The uuid of the user
	 * @return A new user instance
	 */
	public static IUser from(final String uuid)
	{
		return from(UUID.fromString(uuid));
	}

	/**
	 * Constructs a new user from the provided player
	 * 
	 * @param player The player to generate a user for
	 * @return A new user instance
	 */
	public static IUser from(final Player player)
	{
		return from(player.getUniqueId());
	}
	/**
	 * Constructs a new user from the provided entity
	 * 
	 * @param entity The entity to generate a user for
	 * @return A new user instance
	 */
	public static IUser from(final LivingEntity entity)
	{
		return from(entity.getUniqueId());
	}
}
