package Objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Exel
{
	
	
	public void createWorkBook() {
		 Workbook workbook = new XSSFWorkbook();
		 Sheet sheet = workbook.createSheet("new sheet");
		 Row row = sheet.createRow((short)0);
		    // Create a cell and put a value in it.
		    Cell cell = row.createCell(0);
		    cell.setCellValue("1p9");
		    row.createCell(1).setCellValue("2t8");
		    System.out.println(row.getLastCellNum());
		    

		 FileOutputStream fileOut;
		try
		{
			fileOut = new FileOutputStream("workbook.xlsx");
			workbook.write(fileOut);
			workbook.close();
			fileOut.close();
			
		} catch (FileNotFoundException e1) {	
			e1.printStackTrace();
		}catch (IOException e2) {
			e2.printStackTrace();
		}
		 
	}
	
	public void addDataToExel(String outFile) throws InvalidFormatException, IOException {

		InputStream ExcelFile = new FileInputStream(outFile);
		XSSFWorkbook  wb = new XSSFWorkbook(ExcelFile);
		XSSFRow rowHeader = wb.getSheetAt(0).getRow(0);
		XSSFRow rowNew = wb.getSheetAt(0).createRow(1);
		
		XSSFCell cell;
		Iterator<Cell> cells = rowHeader.cellIterator();
		
		while (cells.hasNext())
		{
			cell=(XSSFCell) cells.next();
			String cellValue = cell.getStringCellValue();
			int cellIndex = cell.getColumnIndex();
			rowNew.createCell(cellIndex).setCellValue("qwerty" + cellValue);
			
		}
		  
		  ExcelFile.close();
		  
		  FileOutputStream fileOut = new FileOutputStream(outFile);
		    wb.write(fileOut);
		    fileOut.flush();
		    fileOut.close();
		    
		 
	}
	
	public void readXLSFile() throws IOException, InvalidFormatException
	{
		OPCPackage pkg = OPCPackage.open(new File("workbook.xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(pkg);
		Sheet sheet = wb.getSheetAt(0);
		XSSFRow row; 
		XSSFCell cell;

		Iterator<Row> rows = sheet.rowIterator();

		while (rows.hasNext()) {
			row=(XSSFRow) rows.next();
			Iterator<Cell> cells = row.cellIterator();
			
			while (cells.hasNext())
			{
				cell=(XSSFCell) cells.next();

					System.out.print(cell.getStringCellValue()+" ");			
			}
			
		}
		
		pkg.close();
	
	}
	
	public ArrayList<String> readJobsHeadersFromFileToList(String exelFile) throws IOException, InvalidFormatException
	{
		ArrayList<String> headerList = new ArrayList<String>();
		OPCPackage pkg = OPCPackage.open(new File(exelFile));
		XSSFWorkbook wb = new XSSFWorkbook(pkg);
		Sheet sheet = wb.getSheetAt(0);
		XSSFRow row; 

		Iterator<Row> rows = sheet.rowIterator();

		while (rows.hasNext()) {
			row=(XSSFRow) rows.next();
			headerList.add(row.getCell(0).getStringCellValue());
			
		}
		pkg.close();
		
		Debug.log.debug("---===  Headers getted from file Job_Templates:   ===---");
		for (int i=0;i<headerList.size();i++)
			Debug.log.debug(headerList.get(i));
		
		return headerList;	
	}
	
	public void addHeadersDataToExel(ArrayList<String> headerList, String outXLSXFile) throws InvalidFormatException, IOException {

		deleteOUTFileIfExist(outXLSXFile);

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("new sheet");
		Row rowHeader = sheet.createRow((short)0);

		for (int i = 0; i < headerList.size(); i++) {
			rowHeader.createCell(i).setCellValue(headerList.get(i));
		}
			  
		  FileOutputStream fileOut = new FileOutputStream(outXLSXFile);
		    workbook.write(fileOut);
		    workbook.close();
		    fileOut.flush();
		    fileOut.close();
	}

	public void deleteOUTFileIfExist(String outXLSXFile) {
		File prevXLSXFile = new File(outXLSXFile);
		if (prevXLSXFile.exists())
			prevXLSXFile.delete();
	}

	public void addJobDataToExel(HashMap<String,String> jobMap, String outFile) throws InvalidFormatException, IOException {
		
		
		InputStream inExcelFile = new FileInputStream(outFile);
		XSSFWorkbook  workbook = new XSSFWorkbook(inExcelFile);	
		XSSFRow rowHeader = workbook.getSheetAt(0).getRow(0);
		XSSFRow rowJob = workbook.getSheetAt(0).createRow(1);
		XSSFCell cell;	
		Iterator<Cell> cells = rowHeader.cellIterator();
		String cellValue = "";
		int cellIndex = 0;
		
		Debug.log.debug("---=== Inserted values to cells from JobMap  ===---");
		while (cells.hasNext()) {
			cell=(XSSFCell) cells.next();
			cellValue = cell.getStringCellValue();
			cellIndex = cell.getColumnIndex();	
			rowJob.createCell(cellIndex).setCellValue(jobMap.get(cellValue));
			Debug.log.debug("[cellvalue" + cellIndex + "] for " + cellValue + " = " + jobMap.get(cellValue));
		}
		inExcelFile.close();
		  
		FileOutputStream fileOut = new FileOutputStream(outFile);	
		workbook.write(fileOut);
		workbook.close();
		fileOut.flush();
		fileOut.close();	   
	}

}
