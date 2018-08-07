package com.hepolite.api.attribute;

import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.IValue;

public final class Modifier implements IValue
{
	/**
	 * Constructs a new modifier that modifies the base attribute value by a
	 * multiplicative factor
	 * 
	 * @param value The multiplicative factor to apply to the base value
	 * @return The new modifier instance
	 */
	public static Modifier fromScale(final float value)
	{
		return from(value, 0.0f, 0.0f);
	}
	/**
	 * Constructs a new modifier that modifies the scaled base value by adding a
	 * flat value
	 * 
	 * @param value The flat amount to add after scaling
	 * @return The new modifier instance
	 */
	public static Modifier fromConstant(final float value)
	{
		return from(0.0f, value, 0.0f);
	}
	/**
	 * Constructs a new modifier that modifies the total attribute value by a
	 * multiplicative factor
	 * 
	 * @param value The multiplicative factor to apply to the attribute value
	 * @return The new modifier instance
	 */
	public static Modifier fromMultiplier(final float value)
	{
		return from(0.0f, 0.0f, value);
	}
	/**
	 * Constructs a new modifier that modifies an attribute in multiple ways.
	 * The scale is applied only to the base value, after which the constant is
	 * added. The combined value is then modified by the multiplier.
	 * 
	 * @param scale The multiplicative factor to apply to the base value
	 * @param constant The flat amount to add after scaling
	 * @param multiplier The multiplicative factor to apply to the attribute
	 *            value
	 * @return
	 */
	public static Modifier from(final float scale, final float constant,
		final float multiplier)
	{
		return new Modifier(scale, constant, multiplier);
	}

	// ...

	public float scale;
	public float constant;
	public float multiplier;

	private Modifier(final float scale, final float constant,
		final float multiplier)
	{
		this.scale = scale;
		this.constant = constant;
		this.multiplier = multiplier;
	}

	// ...

	@Override
	public void save(final IConfig config, final IProperty property)
	{
		config.set(property.child("scale"), scale);
		config.set(property.child("constant"), constant);
		config.set(property.child("multiplier"), multiplier);
	}
	@Override
	public void load(final IConfig config, final IProperty property)
	{
		scale = config.getFloat(property.child("scale"));
		constant = config.getFloat(property.child("constant"));
		multiplier = config.getFloat(property.child("multiplier"));
	}
}
