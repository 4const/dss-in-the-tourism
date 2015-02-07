package ru.nstu.cs.dss.controller.json;

import ru.nstu.cs.dss.model.object.ObjectType;

import java.util.function.Function;

public class ObjectTypeJson {

	public short id;
	public String name;

	public ObjectTypeJson(short id, String name) {
		this.id = id;
		this.name = name;
	}

	public static Function<ObjectType, ObjectTypeJson> toJson = o -> new ObjectTypeJson(o.id, o.name);
	public static Function<ObjectTypeJson, ObjectType> fromJson = o -> ObjectType.byId(o.id);
}
