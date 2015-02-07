package ru.nstu.cs.dss.repository;

import org.springframework.data.repository.Repository;
import ru.nstu.cs.dss.model.map.Marker;

import java.util.List;

public interface MarkerRepository extends Repository<Marker, Integer> {

	List<Marker> findAll();
}
