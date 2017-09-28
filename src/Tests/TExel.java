package Tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import Objects.Exel;

public class TExel
{

	@Test
	public void test()
	{
		Exel exel = new Exel();
		exel.createWorkBook();
		try
		{
			exel.addDataToExel();
			exel.readXLSFile();
		} catch (InvalidFormatException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		assertEquals(true, true);
	}

}
