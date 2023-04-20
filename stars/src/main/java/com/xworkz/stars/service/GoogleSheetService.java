package com.xworkz.stars.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ClearValuesRequest;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.xworkz.stars.dto.StarsDTO;

@Service
public class GoogleSheetService {
	private static final String APPLICATION_NAME = "Goldan-Stars";
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKEN_DIRECTORY_PATH = "src/main/resources/credentials.json";
	private static Sheets sheets;
	private static String range = "A2:E";

	public GoogleSheetService() throws FileNotFoundException, IOException, GeneralSecurityException {
		GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(TOKEN_DIRECTORY_PATH))
				.createScoped(SCOPES);

		sheets = new Sheets.Builder(new NetHttpTransport(), JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
		System.out.println("newSheets " + sheets);
	}

	public List<StarsDTO> readDataToSheet(String spreadsheetId) throws IOException {

		ValueRange result = sheets.spreadsheets().values().get(spreadsheetId, range).execute();

		List<List<Object>> values = result.getValues();
		List<StarsDTO> data = new ArrayList();
		for (List<Object> list : values) {
			StarsDTO dto = new StarsDTO();
			Object name = list.get(0);
			Object email = list.get(1);
			Object mobileNo = list.get(2);
			Object location = list.get(3);
			String status = (String) list.get(4);
			Boolean activeStatus = Boolean.valueOf(status);
			dto.setName((String) name);
			dto.setMobileNo((String) mobileNo);
			dto.setEmail((String) email);
			dto.setLocation((String) location);
			dto.setStatusActive(activeStatus);
			data.add(dto);
		}

		System.out.println("result " + result);
		return data;
	}

	public String writeDataToSheet(String spreadsheetId, StarsDTO dto) throws IOException, GeneralSecurityException {

		List<Object> list = new ArrayList();
		list.add(dto.getName());
		list.add(dto.getEmail());
		list.add(dto.getMobileNo());
		list.add(dto.getLocation());
		list.add(dto.getStatusActive());
		List<List<Object>> lists = new ArrayList();
		lists.add(list);
		ValueRange valueRange = new ValueRange().setValues(lists);
		sheets.spreadsheets().values().append(spreadsheetId, range, valueRange).setValueInputOption("RAW").execute();
		return "value saved";

	}

	public String updateGoogleSheet(String spreadsheetId, String range, StarsDTO dto) throws IOException {
		range = "A2:E";
		List<Object> list = new ArrayList();
		list.add(dto.getName());
		list.add(dto.getEmail());
		list.add(dto.getMobileNo());
		list.add(dto.getLocation());
		List<List<Object>> lists = new ArrayList();
		lists.add(list);
		ValueRange valueRange = new ValueRange().setValues(lists);
		UpdateValuesResponse result = sheets.spreadsheets().values().update(spreadsheetId, range, valueRange)
				.setValueInputOption("RAW").execute();
		System.out.printf("%d cells updated.", result.getUpdatedCells());
		return "update successfully";
	}

	public String deleteGoogleSheetData(String spreadsheetId) throws IOException {

		ClearValuesRequest requestBody = new ClearValuesRequest();
		sheets.spreadsheets().values().clear(spreadsheetId, range, requestBody).execute();
		return "delete successfully";
	}

	public String getColumnLetter(int column) {
		StringBuilder sb = new StringBuilder();
		while (column > 0) {
			int remainder = (column - 1) % 26;
			sb.insert(0, (char) ('A' + remainder));
			column = (column - 1) / 26;
		}
		return sb.toString();
	}

	public String updateSpreadsheet(String spreadsheetId, StarsDTO dto) throws IOException, GeneralSecurityException {
		System.out.println("updateSpreadsheet Running...");
		ValueRange result = sheets.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = result.getValues();
		System.out.println("values " + values);
		int index = 0;
		int updatedIndex = 2;
		boolean validName = false;
		for (List<Object> list : values) {
			System.out.println("list " + list);// 1 row : 4 col
			Object name = values.get(index).get(1);
			System.out.println("comparing name with " + name);
			if (name.equals(dto.getName())) {
				validName = true;
				System.out.println("List index 0" + name);
				String columnLetter = getColumnLetter(1) + updatedIndex;// thisshould be bynaic

				System.out.println("columnLetter " + columnLetter);

				System.out.println("name " + name);
				String dto1 = (String) name;
				String updateRange = columnLetter + ":E";
				System.out.println("updateRange =" + updateRange);
				List<Object> list1 = new ArrayList();

				list1.add(dto.getName());
				list1.add(dto.getEmail());
				list1.add(dto.getMobileNo());
				list1.add(dto.getLocation());
				System.out.println("list1 =" + list1);
				List<List<Object>> lists = new ArrayList();

				lists.add(list1);
				System.out.println("lists =" + lists);
				ValueRange valueRange = new ValueRange().setValues(lists);
				System.out.println("valueRange =" + valueRange);
				UpdateValuesResponse result1 = sheets.spreadsheets().values()
						.update(spreadsheetId, updateRange, valueRange).setValueInputOption("RAW").execute();
				System.out.println("result1 =" + result1);
				return "Update successfuly";

			}

			index++;
			updatedIndex++;
		}
		if (!validName) {
			return "user name is not found";
		}

		return "Update not successfuly";

	}

	public StarsDTO findByEmail(String spreadsheetId, String email) throws IOException {
		System.out.println("dto email =" + email);
		ValueRange value = sheets.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> lists = value.getValues();
		System.out.println("total lists =" + lists);
		int index = 0;
		for (List<Object> list : lists) {
			Object findEmail = lists.get(index).get(1);
			System.out.println("list of object =" + list);
			System.out.println("get a range =" + findEmail);

			if (findEmail.equals(email)) {
				StarsDTO dto = new StarsDTO();
				Object name = list.get(0);
				Object emails = list.get(1);
				Object mobileNo = list.get(2);
				Object location = list.get(3);
				dto.setName((String) name);
				dto.setMobileNo((String) mobileNo);
				dto.setEmail((String) emails);
				dto.setLocation((String) location);
				System.out.println("dto =" + dto);
				return dto;
			} else {
				System.out.println("email not found");
			}
			index++;
		}
		return null;

	}

	public StarsDTO findByMobileNo(String spreadsheetId, String mobileNo) throws IOException {
		int index = 0;
		ValueRange valueRange = sheets.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> lists = valueRange.getValues();
		for (List<Object> list : lists) {
			Object mNumber = lists.get(index).get(2);
			if (mNumber.equals(mobileNo)) {
				StarsDTO dto = new StarsDTO();
				Object name = list.get(0);
				Object email = list.get(1);
//				Object mobileNumber = list.get(2);
				Object location = list.get(3);
				dto.setName((String) name);
				dto.setEmail((String) email);
				dto.setMobileNo(mobileNo);
				dto.setLocation((String) location);
				return dto;
			} else {
				System.out.println("mobileNumber not found....");
			}
			index++;
		}
		return null;
	}

	public List<StarsDTO> findByName(String spreadsheetId, String name) throws IOException {
		int index = 0;
		boolean validName = false;
		ValueRange value = sheets.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> lists = value.getValues();
		List<StarsDTO> dtos = new ArrayList<StarsDTO>();
		for (List<Object> list : lists) {
			Object listname = lists.get(index).get(0);
			if (listname.equals(name)) {
				validName = true;
				StarsDTO dto = new StarsDTO();
				Object names1 = lists.get(index).get(0);
				Object email = list.get(1);
				Object mobileNumber = list.get(2);
				Object location = list.get(3);
				dto.setName((String) names1);
				dto.setEmail((String) email);
				dto.setMobileNo((String) mobileNumber);
				dto.setLocation((String) location);
				dtos.add(dto);
				System.out.println("find all name =" + dtos);

			}
			index++;

		}
		if (!validName) {
			System.err.println("user name is not found");
		}
		return dtos;

	}

	public List<StarsDTO> findByLocation(String spreadsheetId, String location) throws IOException {
		int index = 0;
		boolean validLocation = false;
		ValueRange value = sheets.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> lists = value.getValues();
		List<StarsDTO> dtos = new ArrayList<StarsDTO>();
		for (List<Object> list : lists) {
			Object locations = lists.get(index).get(3);
			if (locations.equals(location)) {
				validLocation = true;
				StarsDTO dto = new StarsDTO();
				Object name = list.get(0);
				Object email = list.get(1);
				Object mobileNo = list.get(2);
				Object location1 = list.get(3);
				dto.setName((String) name);
				dto.setEmail((String) email);
				dto.setMobileNo((String) mobileNo);
				dto.setLocation((String) location1);
				dtos.add(dto);
			}
			index++;
		}
		if (!validLocation) {
			System.out.println("location is not found...");
		}

		return dtos;

	}

	public List<StarsDTO> findByEnabled(String spreadsheetId) throws IOException {

		ValueRange value = sheets.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> lists = value.getValues();
		List<StarsDTO> dtos = new ArrayList<StarsDTO>();
		for (List<Object> list : lists) {
			String statuss = (String) list.get(4);
			Boolean active = Boolean.valueOf(statuss);
			if (!active.equals(true)) {
				StarsDTO dto = new StarsDTO();
				Object name = list.get(0);
				Object email = list.get(1);
				Object mobileNo = list.get(2);
				Object location1 = list.get(3);
				String statu = (String) list.get(4);
				Boolean activeStatus = Boolean.valueOf(statu);
				dto.setName((String) name);
				dto.setEmail((String) email);
				dto.setMobileNo((String) mobileNo);
				dto.setLocation((String) location1);
				dto.setStatusActive(activeStatus);
				dtos.add(dto);
			} else {
				System.out.println("enabled is not found");
			}
		}
		return dtos;

	}

	public String updateDisabledByEmail(String spreadsheetId, String emails) throws IOException {
		int updatedIndex = 2;
		ValueRange valueRange = sheets.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = valueRange.getValues();
		System.out.println("all values " + values);
		for (List<Object> list : values) {
			String updateRange = getColumnLetter(5) + updatedIndex;
			System.out.println("list values =" + list);
			if (list.get(1).equals(emails)) {
				System.out.println("emails =" + list.get(1));
				Object statusSet = list.get(4);
				if (statusSet.equals("FALSE")) {
					statusSet = "TRUE";
					List<Object> objects = new ArrayList<Object>();
					objects.add(statusSet);
					List<List<Object>> lists = new ArrayList<List<Object>>();
					lists.add(objects);
					ValueRange range = new ValueRange().setValues(lists);
					UpdateValuesResponse result1 = sheets.spreadsheets().values()
							.update(spreadsheetId, updateRange, range).setValueInputOption("RAW").execute();
					System.out.println("result1 =" + result1);
					return "Update successfuly";

				}
			}
			updatedIndex++;
		}
		return "update not sucessfuly";

	}

	public String updateStarsByEmail(String spreadsheetId, StarsDTO dto) throws IOException {
		System.out.println("updateStarsByName Running...");
		ValueRange result = sheets.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = result.getValues();

		System.out.println("values " + values);
		int index = 0;
		int updatedIndex = 2;
		boolean validName = false;
		for (List<Object> list : values) {
			System.out.println("list " + list);// 1 row : 4 col
			Object emails = values.get(index).get(1);
			System.out.println("comparing name with " + emails);
			if (emails.equals(dto.getEmail())) {
				validName = true;
				System.out.println("List index 0" + emails);
				String columnLetter = getColumnLetter(1) + updatedIndex;// thisshould be bynaic
				String updateRange = columnLetter + ":E";
				System.out.println("updateRange =" + updateRange);
				List<Object> list1 = new ArrayList();
				list1.add(dto.getName());
				list1.add(dto.getEmail());
				list1.add(dto.getMobileNo());
				list1.add(dto.getLocation());
				System.out.println("list1 =" + list1);
				List<List<Object>> lists = new ArrayList();

				lists.add(list1);
				System.out.println("lists =" + lists);
				ValueRange valueRange = new ValueRange().setValues(lists);
				System.out.println("valueRange =" + valueRange);
				UpdateValuesResponse result1 = sheets.spreadsheets().values()
						.update(spreadsheetId, updateRange, valueRange).setValueInputOption("RAW").execute();
				System.out.println("result1 =" + result1);
				return "Update successfuly";

			}

			index++;
			updatedIndex++;
		}
		if (!validName) {
			return "user email is not found";
		}

		return "Update not successfuly";
	}

}
