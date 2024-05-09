package com.swd.agri.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class TestController {

	@GetMapping("/hello")
	public String hello() {
		return "hello succese";
	}
	
	@PostMapping("/getdata")
	public ResponseEntity<?> getData(){
		return ResponseEntity.status(200).body(new Object());
	}
	
	@GetMapping("/search")
	public ModelAndView search(@ModelAttribute ModelAndView model) {
		
		List<String> strs = new ArrayList<String>();
		strs.add("test1");
		strs.add("test2");
		
		model.setViewName("right_html");
		model.addObject("results", strs);
		
		
		//return "right_html";
		return model;
	}
	
	@PostMapping("/csrftest")
	public ModelAndView csrfTestPost(@ModelAttribute ModelAndView model) {
		
		List<String> strs = new ArrayList<String>();
		strs.add("test1");
		strs.add("test2");
		
		model.setViewName("right_html");
		model.addObject("results", strs);
		
		
		//return "right_html";
		return model;
	}
	
	
}
