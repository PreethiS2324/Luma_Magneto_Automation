package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetDataFromExcel {
	
	  public static ArrayList<String> getTestData(String filePath, String sheetName) {
	        
	    	ArrayList<String> data = new ArrayList<String>();
	        try (FileInputStream fis = new FileInputStream(filePath)) {
	        	XSSFWorkbook wb = new XSSFWorkbook(fis);
	    		XSSFSheet sheet = wb.getSheet(sheetName);
	            int col_count = sheet.getRow(0).getLastCellNum();
	    
	            for (int i = 0; i < col_count; i++) {
	                XSSFRow row = sheet.getRow(1);
	                data.add(row.getCell(i).toString());
	                }
	                	                           
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }

}
