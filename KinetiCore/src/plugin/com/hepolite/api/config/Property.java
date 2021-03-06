package com.hepolite.api.config;

import java.io.File;

public final class Property implements IProperty
{
	private final String path;
	private final String root;
	private final String name;

	public Property()
	{
		this.path = this.root = this.name = "";
	}
	public Property(final String path)
	{
		this.path = path;
		this.root = getRootFromPath(path);
		this.name = getNameFromPath(path);
	}
	public Property(final String root, final String name)
	{
		this(root.isEmpty() ? name : root + "." + name);
	}
	public Property(final File file)
	{
		this(getPathFromFile(file));
	}

	private static String getRootFromPath(final String path)
	{
		final int index = path.lastIndexOf('.');
		return index == -1 ? "" : path.substring(0, index);
	}
	private static String getNameFromPath(final String path)
	{
		final int index = path.lastIndexOf('.');
		return index == -1 ? path : path.substring(index + 1);
	}
	private static String getPathFromFile(final File file)
	{
		String path = file.getPath();

		// Remove file extension, if any (be super-aggressive and kill all .'s)
		final int index = path.indexOf('.');
		if (index != -1)
			path = path.substring(0, index);

		// Must escape \ in the regex, thus \\ twice
		return path.replaceAll("/", ".").replaceAll("\\\\", ".");
	}

	// ...

	@Override
	public String path()
	{
		return path;
	}
	@Override
	public String root()
	{
		return root;
	}
	@Override
	public String name()
	{
		return name;
	}
	@Override
	public File file(final String extension)
	{
		if (name().isEmpty())
			throw new IllegalArgumentException("Property must specify a name");

		final String path = root().replaceAll("\\.", "/");
		final String name = name() + "." + extension;
		return path.isEmpty() ? new File(name) : new File(path, name);
	}

	@Override
	public IProperty parent()
	{
		return new Property(root());
	}
	@Override
	public IProperty child(final String child)
	{
		return new Property(path(), child);
	}
	@Override
	public IProperty child(final IProperty child)
	{
		return new Property(path(), child.path());
	}

	// ...

	@Override
	public boolean equals(final Object object)
	{
		if (object instanceof IProperty)
			return path.equals(((IProperty) object).path());
		return false;
	}
	@Override
	public String toString()
	{
		return path;
	}
	@Override
	public int hashCode()
	{
		return path.hashCode();
	}
}
