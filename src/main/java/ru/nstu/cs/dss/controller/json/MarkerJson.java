package ru.nstu.cs.dss.controller.json;

import ru.nstu.cs.dss.model.map.Marker;

import java.util.function.Function;

public class MarkerJson {

	public Integer id;
	public double lat;
	public double lng;

	public MarkerJson() {
	}

	public MarkerJson(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}

	public MarkerJson(Integer id, double lat, double lng) {
		this.id = id;
		this.lat = lat;
		this.lng = lng;
	}

	public static Function<Marker, MarkerJson> toJson = m -> new MarkerJson(m.getId(), m.getLat(), m.getLng());
	public static Function<MarkerJson, Marker> fromJson = m -> new Marker(m.id, m.lng, m.lat, false);
}
