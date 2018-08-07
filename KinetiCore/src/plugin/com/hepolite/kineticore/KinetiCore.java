package com.hepolite.kineticore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.hepolite.api.attribute.AttributeDatabase;
import com.hepolite.api.config.Property;

public final class KinetiCore extends JavaPlugin
{
	private final Database database = new Database();
	private final AttributeDatabase attribute = new AttributeDatabase();

	// ...

	@Override
	public void onEnable()
	{
		database.setDataFolder(new Property(getDataFolder()).child("users"));
		database.register("attribute", attribute);
	}
	@Override
	public void onDisable()
	{
		database.save();
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command,
		final String label, final String[] args)
	{
		return false;
	}
}
