package com.hepolite.api.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.plugin.java.JavaPlugin;

public final class ConfigFactory
{
	/**
	 * Constructs a new configuration, linked to the provided plugin and path.
	 * If the configuration does not exist on disk but exists in the plugin
	 * {@code config} folder, the internal file will be copied to disk. The root
	 * location on disk is the plugin data directory. The filepath to the
	 * configuration on disk is the provided path.
	 * 
	 * @param plugin The plugin owning the config to be created
	 * @param path The path to the config, relative to the plugin data directory
	 * @return The new configuration instance
	 */
	public static IConfig create(final JavaPlugin plugin, final IProperty path)
	{
		copy(plugin, path);
		return new Config(getTarget(plugin, path));
	}

	private static void copy(final JavaPlugin plugin, final IProperty path)
	{
		final File source = getSource(path);
		final File target = getTarget(plugin, path);

		if (target.exists())
		{
			plugin.getLogger().info(
				String.format("Found existing config file '%s'...", path));
			return;
		}

		final String actualSource = source.getPath().replaceAll("\\\\", "/");
		final InputStream stream = plugin.getResource(actualSource);
		if (stream == null)
		{
			plugin.getLogger().warning(String
				.format("Could not find config resource '%s' (%s)!", path));
			return;
		}

		try
		{
			final byte[] buffer = new byte[stream.available()];
			stream.read(buffer);

			if (target.getParentFile() != null)
				target.getParentFile().mkdirs();
			final OutputStream out = new FileOutputStream(target);
			out.write(buffer);
			out.close();

			plugin.getLogger()
				.info(String.format("Created config file '%s'!", path));
		}
		catch (final IOException e)
		{
			plugin.getLogger().warning(String
				.format("Failed saving internal config file '%s'!", path));
			e.printStackTrace();
		}
	}

	// ...

	private static File getSource(final IProperty path)
	{
		return new Property("config", path.path()).file("yml");
	}
	private static File getTarget(final JavaPlugin plugin, final IProperty path)
	{
		return new Property(plugin.getDataFolder()).child(path).file("yml");
	}
}
