package ru.nstu.cs.dss.model.object;

import ru.nstu.cs.dss.model.map.Marker;

import javax.persistence.*;

@Entity
public class ObjectOfEstimation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ObjectType objType;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Marker marker;

    private boolean deleted;


    public ObjectOfEstimation() {
    }

    public ObjectOfEstimation(Integer id, String name, ObjectType objType, Marker marker, boolean deleted) {
        this.id = id;
        this.name = name;
        this.objType = objType;
        this.marker = marker;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectType getObjType() {
        return objType;
    }

    public void setObjType(ObjectType objType) {
        this.objType = objType;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
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
        if (!(o instanceof ObjectOfEstimation)) return false;

        ObjectOfEstimation that = (ObjectOfEstimation) o;

        if (deleted != that.deleted) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (marker != null ? !marker.equals(that.marker) : that.marker != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (objType != null ? !objType.equals(that.objType) : that.objType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (objType != null ? objType.hashCode() : 0);
        result = 31 * result + (marker != null ? marker.hashCode() : 0);
        result = 31 * result + (deleted ? 1 : 0);
        return result;
    }

    @Override
    public String
    toString() {
        final StringBuffer sb = new StringBuffer("ObjectOfEstimation{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", objType=").append(objType);
        sb.append(", marker=").append(marker);
        sb.append(", deleted=").append(deleted);
        sb.append('}');
        return sb.toString();
    }

}
