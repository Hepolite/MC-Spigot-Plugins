package com.hepolite.test.api.cmd.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.CmdContext;
import com.hepolite.api.cmd.CmdParseException;
import com.hepolite.api.cmd.GenericArgs;
import com.hepolite.api.config.values.PotionType;
import com.hepolite.api.user.IUser;
import com.hepolite.api.user.UserConsole;

class CmdElementEnumTest
{
	@Test
	void testParse()
	{
		final IUser user = new UserConsole();
		final CmdArgs args = new CmdArgs("water_breathing", "night vision");
		final CmdContext context = new CmdContext();

		GenericArgs.potionType("foo").parse(user, args, context);
		GenericArgs.potionType("bar").parse(user, args, context);

		assertEquals(PotionType.WATER_BREATHING, context.get("foo", null));
		assertEquals(PotionType.NIGHT_VISION, context.get("bar", null));
	}
	@Test
	void testParseInvalid()
	{
		final IUser user = new UserConsole();
		final CmdContext context = new CmdContext();

		assertThrows(CmdParseException.class, () -> GenericArgs
			.potionType("key").parse(user, new CmdArgs("invalid"), context));
	}
}
