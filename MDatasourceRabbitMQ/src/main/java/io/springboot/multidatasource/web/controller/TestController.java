package io.springboot.multidatasource.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.springboot.multidatasource.service.TestService;

@RestController
@RequestMapping("/")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@GetMapping
	public Object test() {
		Map<String,String> map = new HashMap<>();
		map.put("mapper1", this.testService.mapper1());
		map.put("mapper2", this.testService.mapper2());

		return map;
	}

	@RequestMapping("/db")
	@ResponseBody
	public String getRedis()
	{
		String strVal = "just test1";
		return strVal;
	}

	@RequestMapping("/persons")
	@ResponseBody
	public Object getAdmins()
	{
		return this.testService.admin1();
	}

	@RequestMapping("/admins")
	@ResponseBody
	public Object getAdmins2()
	{
		return this.testService.admin2();
	}
}
