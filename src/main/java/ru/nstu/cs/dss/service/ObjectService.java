package ru.nstu.cs.dss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nstu.cs.dss.model.object.ObjectOfEstimation;
import ru.nstu.cs.dss.repository.ObjectRepository;

import java.util.List;

@Service
@Transactional
public class ObjectService {

	@Autowired
	private ObjectRepository objectRepository;

	public ObjectOfEstimation save(ObjectOfEstimation object) {
		return objectRepository.save(object);
	}

	public List<ObjectOfEstimation> findAll() {
		return objectRepository.findAll();
	}
}
