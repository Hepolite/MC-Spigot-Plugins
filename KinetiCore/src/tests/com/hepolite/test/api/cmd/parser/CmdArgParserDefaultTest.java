package com.hepolite.test.api.cmd.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.parser.CmdArgParserDefault;
import com.hepolite.api.cmd.parser.ICmdArgParser;
import com.hepolite.test.api.util.BaseTest;

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
