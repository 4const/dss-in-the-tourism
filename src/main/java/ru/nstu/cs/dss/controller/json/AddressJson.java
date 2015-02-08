package ru.nstu.cs.dss.controller.json;

import ru.nstu.cs.dss.model.object.Address;

import java.util.function.Function;

public class AddressJson {

	public Integer id;
	public String administrativeArea;
	public String locality;
	public String street;
	public String streetNumber;

	public AddressJson() {
	}

	public AddressJson(String administrativeArea, String locality, String street, String streetNumber) {
		this.administrativeArea = administrativeArea;
		this.locality = locality;
		this.street = street;
		this.streetNumber = streetNumber;
	}

	public AddressJson(Integer id, String administrativeArea, String locality, String street, String streetNumber) {
		this.id = id;
		this.administrativeArea = administrativeArea;
		this.locality = locality;
		this.street = street;
		this.streetNumber = streetNumber;
	}

	public static Function<Address, AddressJson> toJson =
		a -> new AddressJson(
			a.getId(),
			a.getAdministrativeArea(), a.getLocality(),
			a.getStreet(), a.getStreetNumber());

	public static Function<AddressJson, Address> fromJson =
		a -> new Address(
			a.id,
			a.administrativeArea, a.locality,
			a.street, a.streetNumber);
}
