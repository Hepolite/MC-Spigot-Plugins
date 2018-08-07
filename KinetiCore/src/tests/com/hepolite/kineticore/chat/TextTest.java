package com.hepolite.kineticore.chat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TextTest
{
	@Test
	void testToPlain()
	{
		final Text text = Text.of(Color.AQUA, "Hello ", Format.BOLD, Color.RED,
			"World!");

		assertEquals("Hello World!", text.toPlain());
	}

	@Test
	void testSimpleText()
	{
		final Text textA = Text.of("Hello World!");
		final Text textB = Text.of(Color.RED, "[admin]");
		final Text textC = Text.of(Format.BOLD, "*loud noises*");

		assertEquals("�fHello World!", textA.toString());
		assertEquals("�c[admin]", textB.toString());
		assertEquals("�f�l*loud noises*", textC.toString());
	}
	@Test
	void testConcatedText()
	{
		final Text textA = Text.of("Test ", Color.AQUA, "colored");
		final Text textB = Text.of(Format.BOLD, "Hello ", Color.ORANGE,
			"World ", Format.ITALIC, "!");

		assertEquals("�fTest �bcolored", textA.toString());
		assertEquals("�f�lHello �6�lWorld �6�l�o!", textB.toString());
	}
	@Test
	void testResettingTextFormat()
	{
		final Text text = Text.of(Format.BOLD, "Foo ", Format.NONE, "Bar");

		assertEquals("�f�lFoo �fBar", text.toString());
	}

	@Test
	void testSpecialText()
	{
		final Text textA = Text.of("Hover").hover(Text.of(Color.AQUA, "Hover"));
		final Text textB = Text.of("Command").command("stop");
		final Text textC = Text.of("Link").link("https://www.google.com");

		assertEquals("�fHover", textA.toString());
		assertEquals("�fCommand", textB.toString());
		assertEquals("�fLink", textC.toString());
	}
}
