package com.hepolite.test.api.cmd;

import com.hepolite.api.chat.Text;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.CmdExecutionException;
import com.hepolite.api.cmd.ICmd;
import com.hepolite.api.cmd.elements.ICmdElement;
import com.hepolite.api.user.IUser;

public class MockCmd implements ICmd
{
	private final String name;
	private final ICmdElement structure;

	public boolean executed = false;

	public MockCmd(final String name, final ICmdElement structure)
	{
		this.name = name;
		this.structure = structure;
	}

	// ...

	@Override
	public ICmdElement getStructure()
	{
		return structure;
	}

	@Override
	public String getName()
	{
		return name;
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

	@Override
	public void execute(final IUser user, final CmdContext context)
		throws CmdExecutionException
	{
		executed = true;
	}
}
