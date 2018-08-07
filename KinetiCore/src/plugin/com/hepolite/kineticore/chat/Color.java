package com.hepolite.kineticore.chat;

import net.md_5.bungee.api.ChatColor;

public enum Color
{
	AQUA(ChatColor.AQUA),
	AQUA_DARK(ChatColor.DARK_AQUA),
	BLACK(ChatColor.BLACK),
	BLUE(ChatColor.BLUE),
	BLUE_DARK(ChatColor.DARK_BLUE),
	GRAY(ChatColor.GRAY),
	GRAY_DARK(ChatColor.DARK_GRAY),
	GREEN(ChatColor.GREEN),
	GREEN_DARK(ChatColor.DARK_GREEN),
	ORANGE(ChatColor.GOLD),
	PURPLE(ChatColor.DARK_PURPLE),
	PURPLE_DARK(ChatColor.LIGHT_PURPLE),
	RED(ChatColor.RED),
	RED_DARK(ChatColor.DARK_RED),
	YELLOW(ChatColor.YELLOW),
	WHITE(ChatColor.WHITE),

	; // ...

	private final ChatColor chatColor;

	Color(final ChatColor chatColor)
	{
		this.chatColor = chatColor;
	}

	/**
	 * @return The underlying Bukkit ChatColor instance
	 */
	public final ChatColor get()
	{
		return chatColor;
	}
}
