package com.hepolite.api.config;

public interface IConfig
{
	void save();
	void load();
	void delete();

	boolean has(IProperty property);
	void add(IProperty property, Object value);
	void set(IProperty property, Object value);
	void remove(IProperty property);
}
