package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	    public static ArrayList<Object> readRowData(String filePath, String sheetName, int rowNumber) {
	        ArrayList<Object> rowData = new ArrayList<>();

	        try (FileInputStream fileInputStream = new FileInputStream(filePath);
	             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

	            Sheet sheet = workbook.getSheet(sheetName);
	            Row row = sheet.getRow(rowNumber);

	            if (row != null) {
	                for (Cell cell : row) {
	                    switch (cell.getCellType()) {
	                        case STRING:
	                            rowData.add(cell.getStringCellValue());
	                            break;
	                        case NUMERIC:
	                            if (DateUtil.isCellDateFormatted(cell)) {
	                                rowData.add(cell.getDateCellValue());
	                            } else {
	                                rowData.add(cell.getNumericCellValue());
	                            }
	                            break;
	                        case BOOLEAN:
	                            rowData.add(cell.getBooleanCellValue());
	                            break;
	                        case FORMULA:
	                            rowData.add(cell.getCellFormula());
	                            break;
	                        default:
	                            rowData.add(null);
	                    }
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return rowData;
	    }
	}
