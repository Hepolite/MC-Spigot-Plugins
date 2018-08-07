package com.hepolite.api.config;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.primitives.Booleans;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.common.primitives.Shorts;

public final class Config implements IConfig
{
	private File file;
	private FileConfiguration config;

	public Config()
	{
		this.file = null;
		clear();
	}
	public Config(final IProperty property)
	{
		this.file = getFileFromProperty(property);
		load();
	}

	private static File getFileFromProperty(final IProperty property)
	{
		if (property.name().isEmpty())
			throw new IllegalArgumentException("Property must specify a name");

		final String path = property.root().replaceAll("\\.", "/");
		final String name = property.name() + ".yml";
		return path.isEmpty() ? new File(name) : new File(path, name);
	}

	// ...

	@Override
	public boolean exists()
	{
		return file == null ? false : file.exists();
	}
	@Override
	public void save()
	{
		final File root = file.getParentFile();
		if (root != null && root.isDirectory())
			root.mkdirs();

		try
		{
			config.save(file);
		}
		catch (final IOException e)
		{}
	}
	@Override
	public void load()
	{
		if (file != null)
			config = YamlConfiguration.loadConfiguration(file);
	}
	@Override
	public void delete()
	{
		if (exists())
			file.delete();
	}

	// ...

	@Override
	public boolean has(final IProperty property)
	{
		return config.contains(property.path());
	}
	@Override
	public void add(final IProperty property, final Object value)
	{
		if (!has(property))
			set(property, value);
	}
	@Override
	public void set(final IProperty property, Object value)
	{
		if (value instanceof boolean[])
			value = Booleans.asList((boolean[]) value);
		else if (value instanceof byte[])
			value = Bytes.asList((byte[]) value);
		else if (value instanceof double[])
			value = Doubles.asList((double[]) value);
		else if (value instanceof float[])
			value = Floats.asList((float[]) value);
		else if (value instanceof int[])
			value = Ints.asList((int[]) value);
		else if (value instanceof long[])
			value = Longs.asList((long[]) value);
		else if (value instanceof short[])
			value = Shorts.asList((short[]) value);
		else if (value instanceof String[])
			value = Arrays.asList((String[]) value);

		config.set(property.path(), value);
	}

	@Override
	public void remove(final IProperty property)
	{
		config.set(property.path(), null);
	}
	@Override
	public void clear()
	{
		config = new YamlConfiguration();
	}

	@Override
	public Set<IProperty> properties()
	{
		return properties(new Property());
	}
	@Override
	public Set<IProperty> properties(final IProperty property)
	{
		final Set<IProperty> set = new HashSet<>();
		final ConfigurationSection section = property.path().isEmpty() ? config
			: config.getConfigurationSection(property.path());

		if (section != null)
			section.getKeys(false).forEach(key -> set.add(new Property(key)));
		return set;
	}

	// ...

	@Override
	public boolean getBool(final IProperty property)
	{
		return getBool(property, false);
	}
	@Override
	public boolean getBool(final IProperty property, final boolean def)
	{
		return config.getBoolean(property.path(), def);
	}
	@Override
	public boolean[] getBools(final IProperty property, final boolean... def)
	{
		final List<Boolean> values = config.getBooleanList(property.path());
		return values.isEmpty() ? def : Booleans.toArray(values);
	}

	@Override
	public byte getByte(final IProperty property)
	{
		return getByte(property, (byte) 0);
	}
	@Override
	public byte getByte(final IProperty property, final byte def)
	{
		return (byte) getInt(property, def);
	}
	@Override
	public byte[] getBytes(final IProperty property, final byte... def)
	{
		final List<Byte> values = config.getByteList(property.path());
		return values.isEmpty() ? def : Bytes.toArray(values);
	}

	@Override
	public double getDouble(final IProperty property)
	{
		return getDouble(property, 0.0);
	}
	@Override
	public double getDouble(final IProperty property, final double def)
	{
		return config.getDouble(property.path(), def);
	}
	@Override
	public double[] getDoubles(final IProperty property, final double... def)
	{
		final List<Double> values = config.getDoubleList(property.path());
		return values.isEmpty() ? def : Doubles.toArray(values);
	}

	@Override
	public float getFloat(final IProperty property)
	{
		return getFloat(property, 0.0f);
	}
	@Override
	public float getFloat(final IProperty property, final float def)
	{
		return (float) getDouble(property, def);
	}
	@Override
	public float[] getFloats(final IProperty property, final float... def)
	{
		final List<Float> values = config.getFloatList(property.path());
		return values.isEmpty() ? def : Floats.toArray(values);
	}

	@Override
	public int getInt(final IProperty property)
	{
		return getInt(property, 0);
	}
	@Override
	public int getInt(final IProperty property, final int def)
	{
		return config.getInt(property.path(), def);
	}
	@Override
	public int[] getInts(final IProperty property, final int... def)
	{
		final List<Integer> values = config.getIntegerList(property.path());
		return values.isEmpty() ? def : Ints.toArray(values);
	}

	@Override
	public long getLong(final IProperty property)
	{
		return getLong(property, 0L);
	}
	@Override
	public long getLong(final IProperty property, final long def)
	{
		return config.getLong(property.path(), def);
	}
	@Override
	public long[] getLongs(final IProperty property, final long... def)
	{
		final List<Long> values = config.getLongList(property.path());
		return values.isEmpty() ? def : Longs.toArray(values);
	}

	@Override
	public short getShort(final IProperty property)
	{
		return getShort(property, (short) 0);
	}
	@Override
	public short getShort(final IProperty property, final short def)
	{
		return (short) getInt(property, def);
	}
	@Override
	public short[] getShorts(final IProperty property, final short... def)
	{
		final List<Short> values = config.getShortList(property.path());
		return values.isEmpty() ? def : Shorts.toArray(values);
	}

	@Override
	public String getString(final IProperty property)
	{
		return getString(property, "");
	}
	@Override
	public String getString(final IProperty property, final String def)
	{
		return config.getString(property.path(), def);
	}
	@Override
	public String[] getStrings(final IProperty property, final String... def)
	{
		final List<String> values = config.getStringList(property.path());
		return values.isEmpty() ? def : values.toArray(new String[0]);
	}
}
