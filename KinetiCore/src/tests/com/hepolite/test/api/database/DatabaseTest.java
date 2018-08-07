package com.hepolite.test.api.database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hepolite.api.user.IUser;
import com.hepolite.api.user.UserConsole;
import com.hepolite.kineticore.database.Database;

class DatabaseTest
{
	private static final Database database = new Database();
	private static final MockDataHandler handler = new MockDataHandler();
	private static final IUser user = new UserConsole();

	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		database.register("mock", handler);
	}
	@BeforeEach
	void setUp() throws Exception
	{
		handler.data.clear();
	}
	@AfterEach
	void tearDown() throws Exception
	{
		database.erase(user);
	}

	@Test
	void testLoad()
	{
		assertFalse(handler.data.containsKey(user));
		database.load(user);
		assertTrue(handler.data.containsKey(user));
	}
	@Test
	void testSave()
	{
		handler.data.put(user, "Hello World!");
		database.save(user);
		handler.clear();

		assertNotEquals("Hello World!", handler.data.get(user));
		database.load(user);
		assertEquals("Hello World!", handler.data.get(user));
	}
	@Test
	void testSaveAll()
	{
		database.load(user);
		handler.data.put(user, "Hello World!");
		database.save();
		handler.clear();

		assertNotEquals("Hello World!", handler.data.get(user));
		database.load(user);
		assertEquals("Hello World!", handler.data.get(user));
	}
}
