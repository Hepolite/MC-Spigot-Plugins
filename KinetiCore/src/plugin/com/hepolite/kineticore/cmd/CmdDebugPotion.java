package com.hepolite.kineticore.cmd;

import org.bukkit.entity.Player;

import com.hepolite.api.chat.Text;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.CmdExecutionException;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.api.cmd.ICmd;
import com.hepolite.api.cmd.elements.ICmdElement;
import com.hepolite.api.config.values.Potion;
import com.hepolite.api.config.values.Potion.Effect;
import com.hepolite.api.config.values.PotionType;
import com.hepolite.api.unit.Time;
import com.hepolite.api.user.IUser;

public final class CmdDebugPotion implements ICmd
{
	@Override
	public String getName()
	{
		return "potion";
	}
	@Override
	public Text getDescription()
	{
		return Text.of("");
	}
	@Override
	public Text getHelp()
	{
		return Text.of("");
	}

	// ...

	@Override
	public ICmdElement getStructure()
	{
		/// @formatter:off
		return GenericArgs.sequence(
			GenericArgs.player("player"),
			GenericArgs.potionType("type"),
			GenericArgs.optional(GenericArgs.time("duration")),
			GenericArgs.optional(GenericArgs.intNum("level")),
			GenericArgs.optional(GenericArgs.bool("ambient")),
			GenericArgs.optional(GenericArgs.bool("particles"))
		);
		/// @formatter:on
	}
	@Override
	public void execute(final IUser user, final CmdContext context)
		throws CmdExecutionException
	{
		final Potion potion = new Potion();
		final Effect effect = potion.add(context.get("type", PotionType.SPEED));
		effect.duration = context.get("duration", Time.fromInstant());
		effect.level = context.get("level", 0);
		effect.ambient = context.get("ambient", false);
		effect.particles = context.get("particles", true);

		final Player player = context.get("player", null);
		potion.apply(player);
	}
}
