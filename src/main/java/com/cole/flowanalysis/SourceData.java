package com.cole.flowanalysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

public class SourceData {
	
	
	
	
	
	public List<VisitData> readSourceData() throws Exception{
		
		List<VisitData> datas = new ArrayList<VisitData>();
		
		InputStream fi = SourceData.class.getClassLoader().getResourceAsStream("data.csv");
	
		 CSVParser parser = new CSVParser(
				       new InputStreamReader(fi),
				       CSVFormat.DEFAULT.withHeader());
		 		   VisitData data = null;
				   for (CSVRecord record : parser) {
				     data = new VisitData();
				     data.setId(record.get("ID"));
				     data.setApMacAddress(record.get("ApMacAddress"));
				     data.setSourceMacAddress("SourceMacAddress");
				     data.setRssi(record.get("Rssi"));
				     data.setTime(record.get("Time"));
				     datas.add(data);
				   }
				   parser.close();
		return datas;
		
	}

}
