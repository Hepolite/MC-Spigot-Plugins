package com.hepolite.api.attribute;

import java.util.Collections;
import java.util.Set;

import com.hepolite.api.config.IConfig;
import com.hepolite.api.config.IProperty;
import com.hepolite.api.config.IValue;

public final class Attribute implements IValue
{
	private final ModifierMap modifiers = new ModifierMap();

	private float base = 0.0f;
	private float min = -Float.MAX_VALUE;
	private float max = Float.MAX_VALUE;

	public Attribute()
	{}
	public Attribute(final float base)
	{
		this.base = base;
	}
	public Attribute(final float base, final float min, final float max)
	{
		this.base = base;
		this.min = min;
		this.max = max;
	}

	// ...

	/**
	 * Sets the base value of the attribute.
	 * 
	 * @param value The new base value
	 * @return The same attribute for convenience
	 */
	public Attribute setBase(final float value)
	{
		this.base = value;
		return this;
	}
	/**
	 * Sets the smallest value possible for the attribute. If the new value is
	 * larger than the largest allowed value, the largest allowed value is set
	 * to the new value.
	 * 
	 * @param value The new minimum value
	 * @return The same attribute for convenience
	 */
	public Attribute setMin(final float value)
	{
		this.min = value;
		this.max = Math.max(min, max);
		return this;
	}
	/**
	 * Sets the largest value possible for the attribute. If the new value is
	 * smaller than the smallest allowed value, the smallest allowed value is
	 * set to the new value.
	 * 
	 * @param value The new maximum value
	 * @return The same attribute for convenience
	 */
	public Attribute setMax(final float value)
	{
		this.max = value;
		this.min = Math.min(min, max);
		return this;
	}

	/**
	 * @return The base attribute value
	 */
	public float getBase()
	{
		return base;
	}
	/**
	 * @return The smallest allowed attribute value
	 */
	public float getMin()
	{
		return min;
	}
	/**
	 * @return The largest allowed attribute value
	 */
	public float getMax()
	{
		return max;
	}

	/**
	 * Calculates the total attribute value, factoring all modifiers. <br>
	 * <br>
	 * The final attribute value is calculated as following:<br>
	 * (1 + multiplier) * (base * (1 + scale) + constant)<br>
	 * where the total multiplier/scale/constant is summed over all modifiers
	 * acting on the attribute.<br>
	 * <br>
	 * The resulting value is then clamped to the maximum and minimum values, if
	 * present.
	 * 
	 * @return The total attribute value
	 */
	public float getTotal()
	{
		final Modifier modifier = getModifier();
		final float total = (1.0f + modifier.multiplier)
			* (base * (1.0f + modifier.scale) + modifier.constant);
		return Math.min(max, Math.max(min, total));
	}

	// ...

	/**
	 * Retrieves all keys stored under the attribute.
	 * 
	 * @return The modifier key set
	 */
	public Set<String> getKeys()
	{
		return Collections.unmodifiableSet(modifiers.keySet());
	}

	/**
	 * Calculates the total modifier effect on this attribute, from all
	 * modifiers currently active.
	 * 
	 * @return The total modifier for this attribute
	 */
	public Modifier getModifier()
	{
		return modifiers.getTotal();
	}
	/**
	 * Retrieves the modifier stored in the attribute under the given key. If
	 * the modifier does not exist, null is returned.
	 * 
	 * @param key The key to search under
	 * @return The modifier if it exists
	 */
	public Modifier getModifier(final String key)
	{
		return modifiers.get(key);
	}

	/**
	 * Checks if the attribute has a modifier stored under the given key
	 * 
	 * @param key The key to check
	 * @return True iff there is a modifier under the given key
	 */
	public boolean hasModifier(final String key)
	{
		return modifiers.containsKey(key);
	}
	/**
	 * Stores the given modifier under the given key. If another modifier was
	 * stored under the same key, it will be overwritten.
	 * 
	 * @param key The key to store the modifier under
	 * @param modifier The modifier to store
	 */
	public void setModifier(final String key, final Modifier modifier)
	{
		modifiers.put(key, modifier);
	}
	/**
	 * Removes any modifier stored under the specified key.
	 * 
	 * @param key The key to remove from the modifiers
	 */
	public void removeModifier(final String key)
	{
		modifiers.remove(key);
	}

	// ...

	@Override
	public void save(final IConfig config, final IProperty property)
	{
		config.set(property.child("base"), base);
		config.set(property.child("modifiers"), modifiers);
	}
	@Override
	public void load(final IConfig config, final IProperty property)
	{
		base = config.getFloat(property.child("base"));
		config.getValue(property.child("modifiers"), modifiers);
	}
}
