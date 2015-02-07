package ru.nstu.cs.dss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.nstu.cs.dss.controller.json.ObjectJson;
import ru.nstu.cs.dss.model.object.ObjectOfEstimation;
import ru.nstu.cs.dss.service.ObjectService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ObjectOfEstimationController {

	@Autowired
	private ObjectService objectService;

	@RequestMapping(value = "/object", method = RequestMethod.PUT)
	@ResponseBody
	public ObjectJson save(@RequestBody ObjectJson objectJson) throws Throwable {
		ObjectOfEstimation object = ObjectJson.fromJson.apply(objectJson);

		objectService.save(object);

		return objectJson;
	}

	@RequestMapping(value = "/object", method = RequestMethod.GET)
	@ResponseBody
	public List<ObjectJson> getAll() throws Throwable {
		return objectService.findAll()
				.stream()
				.map(ObjectJson.toJson::apply)
				.collect(Collectors.toList());
	}
}
