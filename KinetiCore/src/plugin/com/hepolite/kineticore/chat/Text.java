package com.hepolite.kineticore.chat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;

public final class Text
{
	/**
	 * Generates a new text instance from the specified parameters. The text
	 * consists of three components; the text string, the text color and the
	 * text format. Any text requires at least one string, with one optional
	 * color. Text strings may have as many formats as desired.
	 * 
	 * @param format Any combination of strings, colors and formats
	 * @return A new text instance
	 */
	public static Text of(final Object... format)
	{
		return new Text(format);
	}

	/**
	 * Assigns the text that should be displayed when the user hovers the mouse
	 * over the base text.
	 * 
	 * @param text The text that should be displayed
	 * @return The same base text, for convenience
	 */
	public Text hover(final Text text)
	{
		base.setHoverEvent(
			new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[] {
				text.base
			}));
		return this;
	}
	/**
	 * Assigns the command that is executed when the user clicks the base text.
	 * 
	 * @param command The command to run as the user
	 * @return The same base text, for convenience
	 */
	public Text command(final String command)
	{
		base.setClickEvent(
			new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
		return this;
	}
	/**
	 * Opens a specific web page when the user clicks the base text.
	 * 
	 * @param link The link to direct to
	 * @return The same base text, for convenience
	 */
	public Text link(final String link)
	{
		base.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link));
		return this;
	}

	/**
	 * Translates all underlying fields using the specified values
	 *
	 * @return The same base text, for convenience
	 */
	public Text translate(final Object... values)
	{
		final List<BaseComponent> translations = new ArrayList<>();
		for (final Object value : values)
			if (value instanceof Text)
				translations.add(((Text) value).base);
			else if (value instanceof BaseComponent)
				translations.add((BaseComponent) value);
			else
				translations.add(new TextComponent(value.toString()));

		translate(base, translations);
		return this;
	}
	private void translate(final TranslatableComponent component,
		final List<BaseComponent> translations)
	{
		component.setWith(translations);

		if (component.getExtra() != null)
			component.getExtra().forEach(extra -> {
				if (extra instanceof TranslatableComponent)
					translate((TranslatableComponent) extra, translations);
			});
	}

	// ...

	private TranslatableComponent base;

	private final Collection<Format> formats = new ArrayList<>();
	private Color color = Color.WHITE;

	private Text(final Object... data)
	{
		for (final Object object : data)
			if (object instanceof String)
			{
				final TranslatableComponent text = new TranslatableComponent(
					(String) object);
				applyColor(text);
				applyFormat(text);

				if (base == null)
					base = text;
				else
					base.addExtra(text);
			}
			else if (object instanceof Color)
				color = (Color) object;
			else if (object instanceof Format)
			{
				if ((Format) object == Format.NONE)
					formats.clear();
				formats.add((Format) object);
			}
	}

	// ...

	private void applyColor(final BaseComponent component)
	{
		component.setColor(color.get());
	}
	private void applyFormat(final BaseComponent component)
	{
		for (final Format format : formats)
			switch (format)
			{
			case NONE:
				component.setBold(false);
				component.setItalic(false);
				component.setObfuscated(false);
				component.setStrikethrough(false);
				component.setUnderlined(false);
				break;
			case BOLD:
				component.setBold(true);
				break;
			case ITALIC:
				component.setItalic(true);
				break;
			case OBFUSCATED:
				component.setObfuscated(true);
				break;
			case STRIKETHROUGH:
				component.setStrikethrough(true);
				break;
			case UNDERLINED:
				component.setUnderlined(true);
				break;
			default:
				throw new IllegalArgumentException("Invalid text format");
			}
	}

	// ...

	/**
	 * Converts the message to a plaintext version with no color formatting.
	 * 
	 * @return The string version of the message
	 */
	public String toPlain()
	{
		return ChatColor.stripColor(base.toPlainText());
	}
	/**
	 * Converts the message to a plaintext version. It may contain formatting.
	 * 
	 * @return The string version of the message
	 */
	@Override
	public String toString()
	{
		return base.toLegacyText();
	}

	/**
	 * Retrieves the base component of the message
	 * 
	 * @return The base component of the message
	 */
	public final BaseComponent spigot()
	{
		return base;
	}
}
