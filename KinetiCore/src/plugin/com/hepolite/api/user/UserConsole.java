package com.hepolite.api.user;

import java.util.UUID;

import org.bukkit.Bukkit;

import com.hepolite.api.chat.Text;

public final class UserConsole extends User
{
	private static final UUID uuid = new UUID(0L, 0L);

	// ...

	@Override
	public UUID getUUID()
	{
		return uuid;
	}
	@Override
	public String getName()
	{
		return "CONSOLE";
	}

	// ...

	@Override
	public boolean hasPermission(final String permission)
	{
		return true;
	}

	@Override
	public void sendMessage(final Text text)
	{
		Bukkit.getLogger().info(text.toPlain());
	}
}
