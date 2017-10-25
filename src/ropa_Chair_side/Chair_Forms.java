package ropa_Chair_side;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import page_objects.Ropa_pom;

public class Chair_Forms extends Ropa_pom {
	
	@Test(priority=22)
	  public void login() throws InterruptedException{
		
		  try{
			  	Ropa_pom.configuration();
			  	
				// Login
				Ropa_pom.login(sh.getRow(1).getCell(0).getStringCellValue(), sh.getRow(1).getCell(1).getStringCellValue());
				log.info("login with admin");
				Thread.sleep(500);

		  }
		  catch (Exception e) {
				// TODO: handle exception
				log.error(e);
			}
	}
		         
		  @Test(priority=23)
		  public static void chair() throws InterruptedException{
			  
		 try{
			 
			// Assign chair
			Ropa_pom.chair_assign();
			Thread.sleep(300);
			Ropa_pom.chair_forms(Math.round(sh.getRow(1).getCell(152).getNumericCellValue()));
					//if i=0 accredit
					//if i=1 defer(i.e, need to submit cap)
					//if i=2 deny
			
			Thread.sleep(1500);	
			Ropa_pom.logout();
			Thread.sleep(1500);
			wd.quit();
					
		  }
		  catch (Exception e) {
				// TODO: handle exception
				log.error(e);
			}
	 } //method
 
 	 
}//class
