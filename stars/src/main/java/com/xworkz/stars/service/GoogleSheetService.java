package com.xworkz.stars.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

@Service
public class GoogleSheetService {
	private static final String APPLICATION_NAME = "Goldan-Stars";
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKEN_DIRECTORY_PATH = "src/main/resources/credentials.json";
	private static Sheets sheets;
	//private static String spreadsheetId = "1zjrR1nnfpjiuxWjfdw01U5cHlM2YiGUz8pk4giYsnO8/edit#gid=0";

	public GoogleSheetService() throws FileNotFoundException, IOException, GeneralSecurityException {
		GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(TOKEN_DIRECTORY_PATH))
				.createScoped(SCOPES);

		sheets = new Sheets.Builder(new NetHttpTransport(), JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
		System.out.println("newSheets " + sheets);
	}
//	 public static String createSpreadsheet(String title) throws IOException {
//		 Spreadsheet spreadsheet = new Spreadsheet()
//			        .setProperties(new SpreadsheetProperties()
//			            .setTitle(title));
//			    spreadsheet = sheets.spreadsheets().create(spreadsheet)
//			        .setFields("spreadsheetId")
//			        .execute();
//			    // Prints the new spreadsheet id
//			    System.out.println("Spreadsheet ID: " + spreadsheet.getSpreadsheetId());
//			    return spreadsheet.getSpreadsheetId();
//			  }

	public void writeDataToSheet(String spreadsheetId,String range, List<List<Object>> data)
			throws IOException, GeneralSecurityException {
		range="A2:D2";
		ValueRange valueRange = new ValueRange().setValues(data);
		sheets.spreadsheets().values().append(spreadsheetId, range, valueRange).setValueInputOption("RAW")
				.execute();

	}
}
