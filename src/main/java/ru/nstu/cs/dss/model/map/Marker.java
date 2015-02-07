package ru.nstu.cs.dss.model.map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Marker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double lng;
    private double lat;

    private boolean deleted;

    public Marker() {
    }

    public Marker(Integer id, double lng, double lat, boolean deleted) {
        this.id = id;
        this.lng = lng;
        this.lat = lat;
        this.deleted = deleted;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Marker)) return false;

        Marker marker = (Marker) o;

        if (deleted != marker.deleted) return false;
        if (Double.compare(marker.lng, lng) != 0) return false;
        if (Double.compare(marker.lat, lat) != 0) return false;
        if (id != null ? !id.equals(marker.id) : marker.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (deleted ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Marker{");
        sb.append("id=").append(id);
        sb.append(", lng=").append(lng);
        sb.append(", lat=").append(lat);
        sb.append(", deleted=").append(deleted);
        sb.append('}');
        return sb.toString();
    }


}
