package ru.nstu.cs.dss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.nstu.cs.dss.controller.json.ObjectTypeJson;
import ru.nstu.cs.dss.model.object.ObjectType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ObjectTypeController {

	@RequestMapping(value = "/objectType", method = RequestMethod.GET)
	@ResponseBody
	public List<ObjectTypeJson> getAll() {
		return Arrays.stream(ObjectType.values())
			.map(ObjectTypeJson.toJson)
			.collect(Collectors.toList());
	}
}
