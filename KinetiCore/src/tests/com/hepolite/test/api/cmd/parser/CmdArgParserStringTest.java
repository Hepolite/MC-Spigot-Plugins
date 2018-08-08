package com.hepolite.test.api.cmd.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hepolite.api.cmd.CmdArgs;
import com.hepolite.api.cmd.parser.CmdArgParserString;
import com.hepolite.api.cmd.parser.CmdArgParserString.Worker;
import com.hepolite.api.cmd.parser.ICmdArgParser;
import com.hepolite.test.api.util.BaseTest;

class CmdArgParserStringTest extends BaseTest
{
	@Test
	void testParse()
	{
		final ICmdArgParser parser = new CmdArgParserString();
		final CmdArgs argsA = parser.parse("\"W Hi\" foo bar \"!\"");
		final CmdArgs argsB = parser.parse("Testing 1 2 3");

		assertArrayEquals(strings("W Hi", "foo", "bar", "!"), argsA.consume(4));
		assertArrayEquals(strings("Testing", "1", "2", "3"), argsB.consume(4));
	}

	// ...

	static class WorkerTest
	{
		@Test
		void testFindStartsAndEnds()
		{
			final Worker workerA = new Worker("Hello World!");
			final Worker workerB = new Worker("\"Hello World!\"");

			assertEquals(0, workerA.findStart(0));
			assertEquals(5, workerA.findEnd(0));
			assertEquals(6, workerA.findStart(5));
			assertEquals(12, workerA.findEnd(6));
			assertEquals(12, workerA.findStart(999));
			assertEquals(12, workerA.findEnd(999));

			assertEquals(1, workerB.findStart(0));
			assertEquals(13, workerB.findEnd(1));
			assertEquals(14, workerB.findStart(999));
			assertEquals(14, workerB.findEnd(999));
		}

		@Test
		void testNext()
		{
			final Worker worker = new Worker("\"Foo Bar\" Test \"is string\"");

			assertEquals("Foo Bar", worker.next().get());
			assertEquals("Test", worker.next().get());
			assertEquals("is string", worker.next().get());
			assertFalse(worker.next().isPresent());
		}
		@Test
		void testDone()
		{
			final Worker worker = new Worker("Test");

			assertFalse(worker.done());
			worker.next();
			assertTrue(worker.done());
		}
	}
}
