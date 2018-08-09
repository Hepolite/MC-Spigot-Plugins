package com.hepolite.api.config.values;

import org.bukkit.potion.PotionEffectType;

public enum PotionType
{
	/**
	 * Provides an additional buffer of health which cannot be replenished by
	 * natural regeneration or other healing effects.<br>
	 * <br>
	 * Adds 4 additional health per level
	 */
	ABSORPTION(PotionEffectType.ABSORPTION, true, true),
	/**
	 * Decreases the chance of obtaining high-quality loot, and the chances of
	 * gaining larger volumes of loot.<br>
	 * <br>
	 * Subtracts 1 point per level from the attribute generic.luck
	 */
	BAD_LUCK(PotionEffectType.UNLUCK, true, true),
	/**
	 * Significantly reduces the visibility range on the target. Players will be
	 * unable to sprint or land critical hits.<br>
	 * <br>
	 * Notes:<br>
	 * - When combined with night vision, the screen will be completely black
	 */
	BLINDNESS(PotionEffectType.BLINDNESS, true, false),
	/**
	 * Drastically improves the underwater visibility while also preventing
	 * drowning.<br>
	 * <br>
	 * Notes:<br>
	 * - When stacked with water breathing, both effects' timers tick down
	 */
	CONDUIT_POWER(PotionEffectType.CONDUIT_POWER, true, false),
	/**
	 * Drastically improves the underwater movement speed (both walking on
	 * ground and swimming).<br>
	 * <br>
	 * Notes:<br>
	 * - The player becomes very "slippery" and slides on the ground with this
	 * effect, rather than quickly coming to a standstill
	 */
	DOLPHINS_GRACE(PotionEffectType.DOLPHINS_GRACE, true, false),
	/**
	 * Grants full immunity to fire damage, lava damage, magma block damage and
	 * direct impact by blaze fireballs.
	 */
	FIRE_RESISTANCE(PotionEffectType.FIRE_RESISTANCE, true, false),
	/**
	 * Adds an outline to the target entity, making it visible through
	 * walls.<br>
	 * <br>
	 * Notes:<br>
	 * - Entities affected by invisibility will also be revealed
	 */
	GLOWING(PotionEffectType.GLOWING, true, false),
	/**
	 * Improves the mining and attack speed.<br>
	 * <br>
	 * Increases mining speed by 20% per level<br>
	 * Increases attack speed by 10% per level
	 */
	HASTE(PotionEffectType.FAST_DIGGING, true, true),
	/**
	 * Provides additional health for the target, effectively increasing the
	 * entity's maximum health. Negative levels reduce the entity's maximum
	 * health.<br>
	 * <br>
	 * Increases maximum health by 4 per level
	 */
	HEALTH_BOOST(PotionEffectType.HEALTH_BOOST, true, true),
	/**
	 * Increases the amount of hunger points consumed over time.<br>
	 * <br>
	 * Notes:<br>
	 * // TODO: Details depends on custom hunger system
	 */
	HUNGER(PotionEffectType.HUNGER, true, true),
	/**
	 * Instantly applies damage to the target entity. Undead entities will not
	 * be damaged, but healed instead.<br>
	 * <br>
	 * Base damage is given as 3 * 2^level<br>
	 * The base damage is dealt every tick if the potion lasts longer than an
	 * instant
	 */
	INSTANT_DAMAGE(PotionEffectType.HARM, false, true),
	/**
	 * Instantly applies healing to the target entity. Undead entities will not
	 * be healed, but damaged instead.<br>
	 * <br>
	 * Base healing is given as 2 * 2^level<br>
	 * The base healing is applied every tick if the potion lasts longer than an
	 * instant
	 */
	INSTANT_HEALTH(PotionEffectType.HEAL, false, true),
	/**
	 * Makes the target entity invisible. Certain aspects of entities will not
	 * be hidden. Items worn will not be invisible either.<br>
	 * <br>
	 * Notes:<br>
	 * - Entities with the glowing effect can still be fairly easily seen
	 */
	INVISIBILITY(PotionEffectType.INVISIBILITY, true, false),
	/**
	 * Improves the jumping height of an entity, as well as reduces the fall
	 * damage taken.<br>
	 * <br>
	 * Subtracts 1 damage per level from fall damage<br>
	 * Improves jump height in a non-trivial manner per level
	 */
	JUMP_BOOST(PotionEffectType.JUMP, true, true),
	/**
	 * Causes the target entity to float upwards.<br>
	 * <br>
	 * Floats upwards 0.9 blocks per second per level<br>
	 * <br>
	 * Notes:<br>
	 * - Does not affect swimming or flying players<br>
	 * - Prevents the use of elytra
	 */
	LEVITATION(PotionEffectType.LEVITATION, true, true),
	/**
	 * Increases the chance of obtaining high-quality loot, and the chances of
	 * gaining larger volumes of loot.<br>
	 * <br>
	 * Adds 1 point per level from the attribute generic.luck
	 */
	LUCK(PotionEffectType.LUCK, true, true),
	/**
	 * Reduces the mining and attack speed.<br>
	 * <br>
	 * Reduces mining speed by a non-trivial amount per level<br>
	 * Reduces attack speed by 10% per level
	 */
	MINIG_FATIGUE(PotionEffectType.SLOW_DIGGING, true, true),
	/**
	 * Causes the player screen to wobble and warp, but does nothing else. The
	 * effect on vision builds up over the first two seconds of the effect, and
	 * then declines over the last two seconds.
	 */
	NAUSEA(PotionEffectType.CONFUSION, true, false),
	/**
	 * Allows the player to view the world as if light levels were at maximum at
	 * all locations. The screen will flash the last during the last ten
	 * seconds.
	 */
	NIGHT_VISION(PotionEffectType.NIGHT_VISION, true, false),
	/**
	 * Inflicts damage over time, but cannot do the last bit of damage to fully
	 * kill the target entity.<br>
	 * <br>
	 * Notes:<br>
	 * // TOOD: Details pending custom damage system
	 */
	POISON(PotionEffectType.POISON, true, true),
	/**
	 * Restores health over time, up to the maximum health allowed.<br>
	 * <br>
	 * Heals 1 point every 50/level ticks
	 */
	REGENERATION(PotionEffectType.REGENERATION, true, true),
	/**
	 * Reduces all common forms of damage dealt to the target entity.<br>
	 * <br>
	 * Notes:<br>
	 * // TOOD: Details pending custom damage system
	 */
	RESISTANCE(PotionEffectType.DAMAGE_RESISTANCE, true, true),
	/**
	 * Instantly provides hunger points to the target entity.<br>
	 * <br>
	 * Notes:<br>
	 * // TODO: Details depends on custom hunger system
	 */
	SATURATION(PotionEffectType.SATURATION, false, true),
	/**
	 * Decreases the speed at which the entity falls, while negating all fall
	 * damage the entity would take.
	 */
	SLOW_FALLING(PotionEffectType.SLOW_FALLING, true, false),
	/**
	 * Decreases the speed of the entity. The screen field of view will be
	 * contracted for players.<br>
	 * <br>
	 * Decreases speed by 15% per level
	 */
	SLOWNESS(PotionEffectType.SLOW, true, true),
	/**
	 * Increases the speed of the entity. The screen field of view will be
	 * expanded for players.<br>
	 * <br>
	 * Increases speed by 20% per level
	 */
	SPEED(PotionEffectType.SPEED, true, true),
	/**
	 * Improves the damage dealt by the target entity.<br>
	 * <br>
	 * Increases damage by 3 points per level
	 */
	STRENGTH(PotionEffectType.INCREASE_DAMAGE, true, true),
	/**
	 * Allows the target to breathe underwater
	 */
	WATER_BREATHING(PotionEffectType.WATER_BREATHING, true, false),
	/**
	 * Reduces the damage dealt by the target entity.<br>
	 * <br>
	 * Decreases damage by 4 points per level
	 */
	WEAKNESS(PotionEffectType.WEAKNESS, true, true),
	/**
	 * Inflicts damage over time.<br>
	 * Notes:<br>
	 * // TOOD: Details pending custom damage system
	 */
	WITHER(PotionEffectType.WITHER, true, true),

	; // ...

	private PotionEffectType type;
	private boolean duration;
	private boolean amplifier;

	private PotionType()
	{}
	private PotionType(final PotionEffectType type, final boolean duration,
		final boolean amplifier)
	{
		this.type = type;
		this.duration = duration;
		this.amplifier = amplifier;
	}

	public PotionEffectType get()
	{
		return type;
	}
	public boolean hasDuration()
	{
		return duration;
	}
	public boolean hasAmplifier()
	{
		return amplifier;
	}

	// ...

	@Override
	public String toString()
	{
		return name().toLowerCase();
	}
}
