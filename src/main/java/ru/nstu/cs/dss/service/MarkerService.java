package ru.nstu.cs.dss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nstu.cs.dss.model.map.Marker;
import ru.nstu.cs.dss.repository.MarkerRepository;

import java.util.List;

@Service
@Transactional
public class MarkerService {

	@Autowired
	private MarkerRepository markerRepository;

	public List<Marker> findAll() {
		return markerRepository.findAll();
	}
}
