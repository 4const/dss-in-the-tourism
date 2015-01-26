package ru.nstu.cs.dss.model.object;

import ru.nstu.cs.dss.model.map.Marker;

import javax.persistence.*;

@Entity
public class ObjectOfEstimation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private short objType;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Marker marker;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getObjType() {
        return objType;
    }

    public void setObjType(short objType) {
        this.objType = objType;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjectOfEstimation)) {
            return false;
        }

        ObjectOfEstimation that = (ObjectOfEstimation)o;

        if (objType != that.objType) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (marker != null ? !marker.equals(that.marker) : that.marker != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (int)objType;
        result = 31 * result + (marker != null ? marker.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ObjectOfEstimation{" +
            "id=" + id +
            ", objType=" + objType +
            ", marker=" + marker +
            '}';
    }
}
