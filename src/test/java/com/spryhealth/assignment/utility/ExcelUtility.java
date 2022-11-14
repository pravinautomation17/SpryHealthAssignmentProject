package com.spryhealth.assignment.utility;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	static XSSFWorkbook workBook;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;

	public static void setup() throws IOException
	{
		FileInputStream fin = new FileInputStream("src\\ExcelFile\\TestData.xlsx");
		workBook = new XSSFWorkbook(fin);
		
	}
	public static String getData(String sheetname, int rowNum, int colNum) throws IOException {
		
		sheet= workBook.getSheet(sheetname);
		String cellData=sheet.getRow(rowNum).getCell(colNum).getRawValue();
		
		return cellData;

	}
	public static int getRowCount(String sheetname)
	{
		sheet= workBook.getSheet(sheetname);
		return sheet.getLastRowNum();
		
		
	}
	public static int getColumnCount(String sheetname, int row)
	{
		sheet= workBook.getSheet(sheetname);
		return sheet.getRow(row).getLastCellNum();
	}
}
