package com.hepolite.api.user;

public abstract class User implements IUser
{
	@Override
	public boolean equals(final Object other)
	{
		if (!(other instanceof IUser))
			return false;
		final IUser user = (IUser) other;
		return getUUID().equals(user.getUUID());
	}
	@Override
	public int hashCode()
	{
		return getUUID().hashCode();
	}
	@Override
	public String toString()
	{
		return getUUID().toString();
	}
}
