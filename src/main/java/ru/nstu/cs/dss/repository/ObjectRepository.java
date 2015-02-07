package ru.nstu.cs.dss.repository;

import org.springframework.data.repository.Repository;
import ru.nstu.cs.dss.model.object.ObjectOfEstimation;

import java.util.List;

public interface ObjectRepository extends Repository<ObjectOfEstimation, Integer> {

	void save(ObjectOfEstimation object);

	List<ObjectOfEstimation> findAll();
}
