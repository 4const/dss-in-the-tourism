package ru.nstu.cs.dss.model.map;

import ru.nstu.cs.dss.model.object.ObjectOfEstimation;

import javax.persistence.*;

@Entity
public class Marker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double lng;
    private double lat;


	@OneToOne(mappedBy = "marker", cascade = CascadeType.ALL)
	private ObjectOfEstimation objectOfEstimation;

    public Marker() {
    }

    public Marker(Integer id, double lng, double lat) {
        this.id = id;
        this.lng = lng;
        this.lat = lat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

	public ObjectOfEstimation getObjectOfEstimation() {
		return objectOfEstimation;
	}

	public void setObjectOfEstimation(ObjectOfEstimation objectOfEstimation) {
		this.objectOfEstimation = objectOfEstimation;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Marker)) {
			return false;
		}

		Marker marker = (Marker)o;

		if (Double.compare(marker.lat, lat) != 0) {
			return false;
		}
		if (Double.compare(marker.lng, lng) != 0) {
			return false;
		}
		if (id != null ? !id.equals(marker.id) : marker.id != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id != null ? id.hashCode() : 0;
		temp = Double.doubleToLongBits(lng);
		result = 31 * result + (int)(temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lat);
		result = 31 * result + (int)(temp ^ (temp >>> 32));
		return result;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Marker{");
        sb.append("id=").append(id);
        sb.append(", lng=").append(lng);
        sb.append(", lat=").append(lat);
        sb.append('}');
        return sb.toString();
    }


}
