package com.hepolite.kineticore.attribute;

import org.bukkit.plugin.java.JavaPlugin;

import com.hepolite.api.attribute.Attribute;
import com.hepolite.api.attribute.AttributeDatabase;
import com.hepolite.api.event.Handler;

public class Attributes extends Handler
{
	/// @formatter:off
	
	// Generic attributes
	public static final String DAMAGE_ATTACK_ALL = "damage_attack_all";
	public static final String DEFENCE_ATTACK_ALL = "defence_attack_all";
	
	// Player attributes
	public static final String SPEED_WALK = "speed_walk";
	public static final String SPEED_FLY = "speed_fly";
	
	// ...
	
	public Attributes(final JavaPlugin plugin,
		final AttributeDatabase attributes)
	{
		super(plugin);

		attributes.register(DAMAGE_ATTACK_ALL, new Attribute().setMin(0.0f));
		attributes.register(DEFENCE_ATTACK_ALL, new Attribute());
		
		attributes.register(SPEED_WALK, new Attribute(0.2f, -1.0f, 1.0f));
		attributes.register(SPEED_FLY, new Attribute(0.05f, -1.0f, 1.0f));
	}
	
	/// @formatter:on
}
