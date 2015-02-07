package ru.nstu.cs.dss.model.object;

public enum ObjectType {

	COMPANY((short)1, "Органиация");

	public final short id;
	public final String name;

	ObjectType(short id, String name) {
		this.id = id;
		this.name = name;
	}

	public static ObjectType byId(short id) {
		switch (id) {
			case 1: return COMPANY;
		}

		return null;
	}
}
