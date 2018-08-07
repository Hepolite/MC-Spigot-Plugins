package com.hepolite.api.attribute;

import java.util.HashMap;

import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.IValue;

public class ModifierMap extends HashMap<String, Modifier> implements IValue
{
	private static final long serialVersionUID = -940484661927511634L;

	/**
	 * Calculates a combined total modifier for all modifies stored
	 * 
	 * @return A single modifier containing the total modifier value
	 */
	public Modifier getTotal()
	{
		float scale = 0.0f;
		float constant = 0.0f;
		float multiplier = 0.0f;

		for (final Modifier modifier : values())
		{
			scale += modifier.scale;
			constant += modifier.constant;
			multiplier += modifier.multiplier;
		}
		return Modifier.from(scale, constant, multiplier);
	}

	// ...

	@Override
	public void save(final IConfig config, final IProperty property)
	{
		forEach((key, modifier) -> modifier.save(config, property.child(key)));
	}
	@Override
	public void load(final IConfig config, final IProperty property)
	{
		clear();
		config.properties(property).forEach(key -> {
			final Modifier modifier = Modifier.from(0.0f, 0.0f, 0.0f);
			put(key.name(), config.getValue(property.child(key), modifier));
		});
	}
}
