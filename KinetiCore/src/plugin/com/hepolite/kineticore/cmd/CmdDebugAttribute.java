package com.hepolite.kineticore.cmd;

import com.hepolite.api.attribute.Attribute;
import com.hepolite.api.attribute.Modifier;
import com.hepolite.api.chat.Color;
import com.hepolite.api.chat.Text;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.CmdExecutionException;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.api.cmd.ICmd;
import com.hepolite.api.cmd.elements.ICmdElement;
import com.hepolite.api.user.IUser;
import com.hepolite.kineticore.KinetiCore;

public final class CmdDebugAttribute implements ICmd
{
	@Override
	public String getName()
	{
		return "attribute";
	}
	@Override
	public Text getDescription()
	{
		return Text.of("");
	}
	@Override
	public Text getHelp()
	{
		return Text.of("");
	}

	// ...

	@Override
	public ICmdElement getStructure()
	{
		return GenericArgs.sequence(GenericArgs.string("name"));
	}

	@Override
	public void execute(final IUser user, final CmdContext context)
		throws CmdExecutionException
	{
		final String name = context.get("name", "");
		final Attribute attr = KinetiCore.getAttributes().get(user, name);

		user.sendMessage(attribute(name, attr));
	}

	// ...

	private Text attribute(final String key, final Attribute attribute)
	{
		// Convert attribute data to strings
		final String name = String.format("'%s'", key);
		final String data = String.format("%.2f / %.2f", attribute.getBase(),
			attribute.getTotal());
		final String min = String.format("%.2f", attribute.getMin());
		final String max = String.format("%.2f", attribute.getMax());

		// Start constructing the attribute text
		final Text text = Text.of(Color.AQUA, "Attribute ", Color.BLUE, name,
			Color.AQUA, ": base/total: ", Color.BLUE, data);
		if (attribute.getMin() != -Float.MAX_VALUE)
			text.append(Text.of(Color.AQUA, " min: ", Color.BLUE, min));
		if (attribute.getMax() != Float.MAX_VALUE)
			text.append(Text.of(Color.AQUA, " max: ", Color.BLUE, max));
		text.append(Text.of("\n"));
		text.append(modifier("total", attribute.getModifier()));

		text.hover(modifiers(attribute));
		return text;
	}
	private Text modifiers(final Attribute attribute)
	{
		// Concatenate all modifier texts together
		final Text text = Text.of(Color.AQUA, "Current modifiers:\n");

		attribute.getKeys().forEach(
			key -> text.append(modifier(key, attribute.getModifier(key))));

		// If no modifiers were added, add in a default string
		if (attribute.getKeys().isEmpty())
			text.append(Text.of(Color.BLUE, "No modifiers active"));
		return text;
	}
	private Text modifier(final String key, final Modifier modifier)
	{
		// Convert modifier data to strings
		final String name = String.format("'%s'", key);
		final String data = String.format("%.0f%%%% / %.0f%%%% | %.2f",
			100.0f * modifier.multiplier, 100.0f * modifier.scale,
			modifier.constant);

		// Construct modifier text
		return Text.of(Color.AQUA, "Modifier ", Color.BLUE, name, Color.AQUA,
			" (multiplier/scale | constant): ", Color.BLUE, data);
	}
}
