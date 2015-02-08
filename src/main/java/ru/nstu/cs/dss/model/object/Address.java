package ru.nstu.cs.dss.model.object;


import javax.persistence.*;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String administrativeArea;

	@Column(nullable = false)
	private String locality;

	@Column(nullable = false)
	private String street;

	@Column(nullable = false)
	private String streetNumber;

	public Address() {
	}

	public Address(Integer id, String administrativeArea, String locality, String street, String streetNumber) {
		this.id = id;
		this.administrativeArea = administrativeArea;
		this.locality = locality;
		this.street = street;
		this.streetNumber = streetNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdministrativeArea() {
		return administrativeArea;
	}

	public void setAdministrativeArea(String administrativeArea) {
		this.administrativeArea = administrativeArea;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Address)) {
			return false;
		}

		Address address = (Address)o;

		if (administrativeArea != null ? !administrativeArea.equals(address.administrativeArea) : address.administrativeArea != null) {
			return false;
		}
		if (id != null ? !id.equals(address.id) : address.id != null) {
			return false;
		}
		if (locality != null ? !locality.equals(address.locality) : address.locality != null) {
			return false;
		}
		if (street != null ? !street.equals(address.street) : address.street != null) {
			return false;
		}
		if (streetNumber != null ? !streetNumber.equals(address.streetNumber) : address.streetNumber != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (administrativeArea != null ? administrativeArea.hashCode() : 0);
		result = 31 * result + (locality != null ? locality.hashCode() : 0);
		result = 31 * result + (street != null ? street.hashCode() : 0);
		result = 31 * result + (streetNumber != null ? streetNumber.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Address{" +
			"id=" + id +
			", administrativeArea='" + administrativeArea + '\'' +
			", locality='" + locality + '\'' +
			", street='" + street + '\'' +
			", streetNumber='" + streetNumber + '\'' +
			'}';
	}
}
