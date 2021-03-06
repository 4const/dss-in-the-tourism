package ru.nstu.cs.dss.model.object;

public enum ObjectType {

	COMPANY((short)1, "Органиация"),
	CAFE((short)2, "Кафе"),
	RESTAURANT((short)3, "Ресторан"),
	HOTEL((short)4, "Гостиница");

	public final short id;
	public final String name;

	ObjectType(short id, String name) {
		this.id = id;
		this.name = name;
	}

	public static ObjectType byId(short id) {
		ObjectType[] types = ObjectType.values();

		int i = id - 1;
		if (i < 0 || i > types.length) {
			return null;
		}

		return types[i];
	}
}
