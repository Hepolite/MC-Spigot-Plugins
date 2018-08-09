package com.hepolite.api.config.values;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.IValue;
import com.hepolite.kineticore.KinetiCore;

public final class PotionTypeSet extends TreeSet<PotionType> implements IValue
{
	private static final long serialVersionUID = 2068307477773003002L;

	public PotionTypeSet(final PotionType... types)
	{
		for (final PotionType type : types)
			add(type);
	}

	// ...

	@Override
	public void save(final IConfig config, final IProperty property)
	{
		final Collection<String> strings = new ArrayList<>();
		forEach(type -> strings.add(type.toString().toLowerCase()));

		config.set(property, strings);
	}
	@Override
	public void load(final IConfig config, final IProperty property)
	{
		clear();
		for (final String string : config.getStrings(property))
			try
			{
				add(PotionType.valueOf(string.toUpperCase()));
			}
			catch (final Exception e)
			{
				KinetiCore.WARN(
					String.format("Failed loading potion type '%s'", string));
			}
	}
}
