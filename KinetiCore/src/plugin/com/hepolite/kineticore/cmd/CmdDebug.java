package com.hepolite.kineticore.cmd;

import com.hepolite.api.chat.Text;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.CmdExecutionException;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.api.cmd.ICmd;
import com.hepolite.api.cmd.elements.ICmdElement;
import com.hepolite.api.user.IUser;

public final class CmdDebug implements ICmd
{
	@Override
	public String getName()
	{
		return "debug";
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
		return GenericArgs.children(
			new CmdDebugAttribute(),
			new CmdDebugPotion(),
			new CmdDebugSound()
		);
		/// @formatter:on
	}

	@Override
	public void execute(final IUser user, final CmdContext context)
		throws CmdExecutionException
	{}
}
