package com.hepolite.kineticore.cmd.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.kineticore.cmd.CmdArgs;
import com.hepolite.kineticore.util.BaseTest;

class CmdArgParserDefaultTest extends BaseTest
{
	@Test
	void testParse()
	{
		final ICmdArgParser parser = new CmdArgParserDefault();
		final CmdArgs args = parser.parse("Hello World !");

		assertArrayEquals(strings("Hello", "World", "!"), args.consume(3));
	}
}
