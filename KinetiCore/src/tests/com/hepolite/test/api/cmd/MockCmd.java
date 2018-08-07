package com.hepolite.test.api.cmd;

import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.CmdExecutionException;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.api.cmd.ICmd;
import com.hepolite.api.cmd.elements.ICmdElement;
import com.hepolite.api.user.IUser;

public class MockCmd implements ICmd
{
	private final String name;

	public boolean executed = false;

	public MockCmd(final String name)
	{
		this.name = name;
	}

	// ...

	@Override
	public ICmdElement getStructure()
	{
		return GenericArgs.none();
	}

	@Override
	public String getName()
	{
		return name;
	}
	@Override
	public String getDescription()
	{
		return "";
	}
	@Override
	public String getHelp()
	{
		return "";
	}

	@Override
	public void execute(final IUser user, final CmdContext context)
		throws CmdExecutionException
	{
		executed = true;
	}
}
