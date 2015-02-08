package ru.nstu.cs.dss.controller.json;

import ru.nstu.cs.dss.model.object.ObjectOfEstimation;
import ru.nstu.cs.dss.model.object.ObjectType;

import java.util.function.Function;


public class ObjectJson {

	public MarkerJson marker;
	public AddressJson address;

	public Integer id;
	public String name;
	public short type;

	public ObjectJson() {
	}

	public ObjectJson(MarkerJson marker, AddressJson address, String name, short type) {
		this.marker = marker;
		this.address = address;
		this.name = name;
		this.type = type;
	}

	public ObjectJson(MarkerJson marker, AddressJson address, Integer id, String name, short type) {
		this.marker = marker;
		this.address = address;
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public static Function<ObjectOfEstimation, ObjectJson> toJson =
		o -> new ObjectJson(
			MarkerJson.toJson.apply(o.getMarker()),
			o.getAddress() != null ? AddressJson.toJson.apply(o.getAddress()) : null,
			o.getId(), o.getName(), o.getObjType().id);

	public static Function<ObjectJson, ObjectOfEstimation> fromJson =
		o -> new ObjectOfEstimation(
			o.id, o.name,
			ObjectType.byId(o.type),
			MarkerJson.fromJson.apply(o.marker),
			o.address != null ? AddressJson.fromJson.apply(o.address) : null,
			false);
}
