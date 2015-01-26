package ru.nstu.cs.dss.model.object;

import ru.nstu.cs.dss.model.map.Marker;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private short objType;

    @ManyToOne
    private Marker marker;


}
