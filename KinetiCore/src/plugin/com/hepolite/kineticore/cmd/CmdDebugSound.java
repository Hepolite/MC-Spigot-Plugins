package com.hepolite.kineticore.cmd;

import org.bukkit.entity.Player;

import com.hepolite.api.chat.Color;
import com.hepolite.api.chat.Text;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.CmdExecutionException;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.api.cmd.ICmd;
import com.hepolite.api.cmd.elements.ICmdElement;
import com.hepolite.api.config.ConfigFactory;
import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.Property;
import com.hepolite.api.config.values.Sounds;
import com.hepolite.api.user.IUser;
import com.hepolite.kineticore.KinetiCore;

public final class CmdDebugSound implements ICmd
{
	@Override
	public String getName()
	{
		return "sound";
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
		return GenericArgs.playerOrUser("player");
	}
	@Override
	public void execute(final IUser user, final CmdContext context)
		throws CmdExecutionException
	{
		final Sounds sound = new Sounds();
		final Player player = context.get("player", null);

		final IConfig config = ConfigFactory.create(KinetiCore.getInstance(),
			new Property("debug", "config"));
		config.getValue(new Property("sound"), sound);
		sound.playFrom(player);

		final String size = String.format("'%d'", sound.size());
		final String name = String.format("'%s'", player.getName());
		user.sendMessage(Text.of(Color.AQUA, "Playing sound with ", Color.BLUE,
			size, Color.AQUA, " component(s) to ", Color.BLUE, name));
	}
}
