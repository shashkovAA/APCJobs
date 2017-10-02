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
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Exel
{
	private String templateHeadersFile;
	private String outJobsFile;
	private ArrayList<JobSettingsHeader> headerList = new ArrayList<JobSettingsHeader>();
	
	public void createWorkBook() {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("new sheet");
		sheet.setDefaultColumnWidth(15);
		XSSFRow row = sheet.createRow((short)0);
		    // Create a cell and put a value in it.
		XSSFCell cell = row.createCell(0);
		XSSFCellStyle style = workbook.createCellStyle(); //Create new style
        style.setWrapText(true); //Set wordwrap
        cell.setCellStyle(style);
		cell.setCellValue("The confirmation message that the system plays to the called party after the called party opts out to DNC.");
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
	
	public void setTemplateHeadersFile(String templateHeadersFile) {
		this.templateHeadersFile = templateHeadersFile;	
	}
	public void setOutJobsExelFile(String outJobsFile) {
		this.outJobsFile = outJobsFile;	
	}
	
	public void addHeadersDataToExelNoExceptHandl() throws InvalidFormatException, IOException {

		deleteOUTFileIfExist(outJobsFile);
		readJobsHeadersFromFileToList();

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("new sheet");
		sheet.setDefaultColumnWidth(15);
		
		XSSFFont headerFont= workbook.createFont();
        headerFont.setBold(true);
		
		XSSFCellStyle styleHeaderOverview = workbook.createCellStyle(); //Create new style
		styleHeaderOverview.setWrapText(true); //Set wordwrap
		styleHeaderOverview.setVerticalAlignment(VerticalAlignment.CENTER);
		
		XSSFCellStyle styleHeaderTag = workbook.createCellStyle(); //Create new style
		styleHeaderTag.setFont(headerFont);
		       
        
        XSSFRow rowHeaderOvetview = sheet.createRow((short)0);
		XSSFRow rowHeaderTag = sheet.createRow((short)1);
		for (int i = 0; i < headerList.size(); i++) {
			XSSFCell headerOverviewCell = rowHeaderOvetview.createCell(i);
			headerOverviewCell.setCellStyle(styleHeaderOverview);
			headerOverviewCell.setCellValue(headerList.get(i).getOverview());
			
			XSSFCell headerTagCell =rowHeaderTag.createCell(i);
			headerTagCell.setCellStyle(styleHeaderTag);
			headerTagCell.setCellValue(headerList.get(i).getTag());
		}
			  
		  FileOutputStream fileOut = new FileOutputStream(outJobsFile);
		    workbook.write(fileOut);
		    workbook.close();
		    fileOut.flush();
		    fileOut.close();
	}
	
	public void readJobsHeadersFromFileToListNoExceptHandl() throws InvalidFormatException, IOException 
	{
		OPCPackage pkg = OPCPackage.open(new File(templateHeadersFile));
		XSSFWorkbook wb = new XSSFWorkbook(pkg);
		Sheet sheet = wb.getSheetAt(0);
		XSSFRow row; 

		Iterator<Row> rows = sheet.rowIterator();

		while (rows.hasNext()) {
			row=(XSSFRow) rows.next();
			
			headerList.add(new JobSettingsHeader(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue()));
			
		}
		//wb.close();
		pkg.close();
		
		Debug.log.debug("---===  Headers getted from file Job_Templates:   ===---");
		for (int i=0;i<headerList.size();i++)
			Debug.log.debug(headerList.get(i));	
	}
	
	

	public void deleteOUTFileIfExist(String outXLSXFile) {
		File prevXLSXFile = new File(outXLSXFile);
		if (prevXLSXFile.exists())
			prevXLSXFile.delete();
	}

	public void addJobDataToExelNoExceptHandl(HashMap<String,String> jobMap) throws InvalidFormatException, IOException {
			
		InputStream inExcelFile = new FileInputStream(outJobsFile);
		XSSFWorkbook  workbook = new XSSFWorkbook(inExcelFile);	
		XSSFRow rowHeader = workbook.getSheetAt(0).getRow(1);
		int lastRowIndex = workbook.getSheetAt(0).getLastRowNum();
		XSSFRow rowJob = workbook.getSheetAt(0).createRow(lastRowIndex + 1);
		XSSFCell cell;	
		Iterator<Cell> cells = rowHeader.cellIterator();
		String cellValue = "";
		int cellIndex = 0;
		
		Debug.log.debug("---=== Inserted values to ExelFile from JobMap  ===---");
		while (cells.hasNext()) {
			cell=(XSSFCell) cells.next();
			cellValue = cell.getStringCellValue();
			cellIndex = cell.getColumnIndex();	
			rowJob.createCell(cellIndex).setCellValue(jobMap.get(cellValue));
			Debug.log.debug("[cell"+ cellIndex +"value] for " + cellValue + " = " + jobMap.get(cellValue));
		}
		inExcelFile.close();
		  
		FileOutputStream fileOut = new FileOutputStream(outJobsFile);	
		workbook.write(fileOut);
		workbook.close();
		fileOut.flush();
		fileOut.close();	   
	}
	
	public void readJobsHeadersFromFileToList() {
		
		try {
			readJobsHeadersFromFileToListNoExceptHandl();
		} catch (InvalidFormatException except) {
			Debug.log.error(except.getMessage());
		} catch (IOException except) {
			Debug.log.error(except.getMessage());
		}	
	}
	public void addHeadersDataToExelFile() {
		
		try {
			addHeadersDataToExelNoExceptHandl();
		} catch (InvalidFormatException except) {
			Debug.log.error(except.getMessage());
		} catch (IOException except) {
			Debug.log.error(except.getMessage());
		}	
	}
	public void addJobDataToExel(HashMap<String,String> jobMap) {
		
		try {
			addJobDataToExelNoExceptHandl(jobMap);
		} catch (InvalidFormatException except) {
			Debug.log.error(except.getMessage());
		} catch (IOException except) {
			Debug.log.error(except.getMessage());
		}	
	}
	
	
	

}
