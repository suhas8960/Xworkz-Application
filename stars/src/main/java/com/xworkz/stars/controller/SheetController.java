package com.xworkz.stars.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xworkz.stars.dto.StarsDTO;
import com.xworkz.stars.service.GoogleSheetService;

@RestController
@RequestMapping("/trainee")
@CrossOrigin("http://localhost:3000")
public class SheetController {

	@Autowired
	private GoogleSheetService googleSheetService;

	public SheetController() {
		System.out.println("SheetController Running....");
	}

	@GetMapping("/getString")
	public String getString() {
		return "Redday to start";
	}

//	@GetMapping()
//	public List<StarsDTO> getSheetData(@RequestHeader String spreadsheetId) throws IOException {
//		return googleSheetService.readDataToSheet(spreadsheetId);
//	}

	@PostMapping()
	public String saveDataToSheet(@RequestHeader String spreadsheetId, @RequestBody @Val StarsDTO dto)
			throws IOException, GeneralSecurityException {
		return googleSheetService.writeDataToSheet(spreadsheetId, dto);
	}

//	@PutMapping("/{range}")
//	public String updateSheetData(@RequestHeader String spreadsheetId, @PathVariable String range,
//			@RequestBody StarsDTO dto) throws IOException {
//		return googleSheetService.updateGoogleSheet(spreadsheetId, range, dto);
//
//	}

	@PutMapping("/edit")
	public String updateSheet(@RequestHeader String spreadsheetId, @RequestBody StarsDTO dto)
			throws IOException, GeneralSecurityException {
		return googleSheetService.updateSpreadsheet(spreadsheetId, dto);
	}

	@GetMapping("byEmail/{email}")
	public StarsDTO findEmail(@RequestHeader String spreadsheetId, @RequestParam String email) throws IOException {
		return googleSheetService.findByEmail(spreadsheetId, email);
	}

	@GetMapping("byMobile/{mobileNo}")
	public StarsDTO findByMobileNo(@RequestHeader String spreadsheetId, @PathVariable String mobileNo)
			throws IOException {
		return googleSheetService.findByMobileNo(spreadsheetId, mobileNo);

	}

	@GetMapping("byName/{name}")
	public List<StarsDTO> findname(@RequestHeader String spreadsheetId, @PathVariable String name) throws IOException {
		return googleSheetService.findByName(spreadsheetId, name);
	}

//	@GetMapping("/{location}")
//	public List<StarsDTO> findLocation(@RequestHeader String spreadsheetId, @PathVariable String location)
//			throws IOException {
//		return googleSheetService.findByLocation(spreadsheetId, location);
//	}

	@GetMapping()
	public List<StarsDTO> findEnabled(@RequestHeader String spreadsheetId) throws IOException {
		return googleSheetService.findByEnabled(spreadsheetId);
	}

	@DeleteMapping("/{emails}")
	public String updateDisabled(@RequestHeader String spreadsheetId, @PathVariable String emails) throws IOException {
		return googleSheetService.updateDisabledByEmail(spreadsheetId, emails);

	}

	@PutMapping()
	public String updateByEmail(@RequestHeader String spreadsheetId, @RequestBody StarsDTO dto) throws IOException {
		return googleSheetService.updateStarsByEmail(spreadsheetId, dto);
	}

}
