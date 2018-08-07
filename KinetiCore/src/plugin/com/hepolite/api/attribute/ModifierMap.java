package com.hepolite.api.attribute;

import java.util.HashMap;

public class ModifierMap extends HashMap<String, Modifier>
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
}
