package com.mercadona.springboot.backend.apirest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductsController {
	
	@GetMapping("helloworld")
	public String helloWorld() {
		return "helloworld";
	}
}
