package com.xworkz.stars.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xworkz.stars.service.GoogleSheetService;

@RestController
@RequestMapping("/")
public class SheetController {

	@Autowired
	private GoogleSheetService googleSheetService;
	
	public SheetController() {
	System.out.println("SheetController Running....");
	}
	
	@GetMapping("suhas")
	public String getString() {
		return "Redday to start";
	}
	
//	@PostMapping("/sheet/{name}")
//	public String sheetName(@PathVariable String name) throws IOException {
//		return this.googleSheetService.createSpreadsheet(name);
//		
//	}
	
	
	
	

}
