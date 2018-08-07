package com.hepolite.api.attribute;

public final class Modifier
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

	public final float scale;
	public final float constant;
	public final float multiplier;

	private Modifier(final float scale, final float constant,
		final float multiplier)
	{
		this.scale = scale;
		this.constant = constant;
		this.multiplier = multiplier;
	}
}
