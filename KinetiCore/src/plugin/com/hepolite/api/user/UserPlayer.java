package com.hepolite.api.user;

import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.hepolite.api.chat.Text;

public class UserPlayer extends User
{
	private final UUID uuid;

	public UserPlayer(final UUID uuid)
	{
		this.uuid = uuid;
	}

	// ...

	@Override
	public UUID getUUID()
	{
		return uuid;
	}
	@Override
	public String getName()
	{
		final Optional<Player> player = getPlayer();
		return player.isPresent() ? player.get().getDisplayName() : "UNKNOWN";
	}

	@Override
	public boolean isOnline()
	{
		return getPlayer().isPresent();
	}

	// ...

	@Override
	public boolean hasPermission(final String permission)
	{
		return false;
	}

	@Override
	public void sendMessage(final Text text)
	{
		getPlayer().ifPresent(p -> p.spigot().sendMessage(text.spigot()));
	}

	// ...

	private Optional<Player> getPlayer()
	{
		return Optional.ofNullable(Bukkit.getPlayer(uuid));
	}
}
