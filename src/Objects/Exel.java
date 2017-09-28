package Objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
	
	public void addDataToExel() throws InvalidFormatException, IOException {
		// XSSFWorkbook, File
		InputStream ExcelFile = new FileInputStream("workbook.xlsx");
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
		  
		  FileOutputStream fileOut = new FileOutputStream("workbook.xlsx");
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
		
		Debug.log.info("---===  Headers getted from file Job_Templates:   ===---");
		for (int i=0;i<headerList.size();i++)
			Debug.log.debug(headerList.get(i));
		
		return headerList;
		
	
	}

}
