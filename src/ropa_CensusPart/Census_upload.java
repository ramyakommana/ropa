package ropa_CensusPart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import page_objects.Ropa_pom;

/**
 * @author K RamyaSree
 *
 * QA_Ropa
* March 30, 2017
* 3:36:20 PM
 */

public class Census_upload extends Ropa_pom {

	public static Alert alert;
	public static String user;
	public static String password;

	 @Test(priority=3)
	public void login() throws InterruptedException {

		Ropa_pom.configuration();
		// Login
		Ropa_pom.login(sh.getRow(1).getCell(2).getStringCellValue(), sh.getRow(1).getCell(1).getStringCellValue());
		log.info("Login with Facility");

	}// method
	 
	 	@Test(priority=4)
	  public void census_upload() throws InterruptedException{
	 		
	 		try{
					
					//opening census sheet from left side
					wd.findElement(By.linkText("Data Collection")).click();
					Thread.sleep(100);
					wd.findElement(By.linkText("Census Sheets")).click();
					
					//page1
					wd.findElement(By.id("cphMaster_top_Next_btn")).click();//next
					Thread.sleep(200);
					//no of cencus cases
					Long i=Math.round(sh.getRow(1).getCell(148).getNumericCellValue());
					int census=i.intValue();
					
					//****loop for multiple cases
					for(int c=0;c<=census-1;c++)
					
					{
					//page2
					 JavascriptExecutor jse = (JavascriptExecutor)wd;
			         jse.executeScript("window.scrollBy(0,500)", "");
			       
			         
			         wd.findElement(By.id("cphMaster_tabCases_ctl00_Case1_lnkNew")).click();
			         jse.executeScript("window.scrollBy(0,500)", "");
			         Thread.sleep(300);
			         
			         wd.findElement(By.id("cphMaster_tabCases_ctl00_Case1_gvCases_CenPatID_txt_"+c)).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(98).getNumericCellValue()))+c);
			         wd.findElement(By.id("cphMaster_tabCases_ctl00_Case1_gvCases_FTreatmentDate_txt_"+c)).click();
			         WebElement date = wd.findElement(By.id("calendar"));
			  	   
			   		   // List<WebElement> row=date.findElements(By.tagName("tr"));
			   		    List<WebElement> cols=date.findElements(By.tagName("td"));
			   		   
			   		    for (WebElement cel: cols)
			   		    {
			   		    String d=String.valueOf(Math.round(sh.getRow(1).getCell(99).getNumericCellValue()));
			   		    	   //Select Date 
			   		    	   if (cel.getText().equals(d))
			   		    	   {
			   		    		 
			   		    	   cel.findElement(By.linkText(d)).click();
			   		    	   break;
			   		    	   }
			   		    } 
			   		    
			   		 wd.findElement(By.xpath("//*[@id='cphMaster_tabCases_ctl00_Case1_gvCases_ddlTxCode_"+c+"_0_"+c+"']")).click();//Treatment Code
			   		 
			   		Long site =Math.round(sh.getRow(1).getCell(147).getNumericCellValue());
					int sitenum =site.intValue();
			   		 if(sitenum>1)
			   		 {
			   		
			   		 if(c==1||c==3){
			   			 
				   			Select drop1 = new Select(wd.findElement(By.id("cphMaster_tabCases_ctl00_Case1_gvCases_ddlSname_"+c)));
				   			drop1.selectByIndex(1);
				   		 }
			   		 }
			   		else
			   		 {
			   			Select drop1 = new Select(wd.findElement(By.id("cphMaster_tabCases_ctl00_Case1_gvCases_ddlSname_"+c)));
			   			drop1.selectByIndex(0);
			   		 }
			   		 
			   		wd.findElement(By.id("cblRoPeer_0")).click();//check yes
			   		Select drop = new Select(wd.findElement(By.id("cphMaster_tabCases_ctl00_Case1_gvCases_ddlropeerStaff_"+c)));
			   		String staff=sh.getRow(1).getCell(42).getStringCellValue()+" "+sh.getRow(1).getCell(43).getStringCellValue();
			   		
		    		drop.selectByVisibleText(staff);
		    		wd.findElement(By.id("cphMaster_tabCases_ctl00_Case1_gvCases_DiseaseSite_txt_"+c)).sendKeys(sh.getRow(1).getCell(101).getStringCellValue());//disease site
		    		wd.findElement(By.id("cphMaster_tabCases_ctl00_Case1_gvCases_Lblupdate_"+c)).click();//save
		    		Thread.sleep(300);
 
			         log.info("Census" +(c+1)+ "added");
					}
					jse.executeScript("window.scrollBy(0,3000)", "");
			         jse.executeScript("window.scrollBy(0,3000)", "");
			         jse.executeScript("window.scrollBy(0,3000)", "");
			         wd.findElement(By.id("cphMaster_Button2")).click();//next
			         Thread.sleep(200);
			         wd.findElement(By.id("cphMaster_Finish")).click();//submit
			         Thread.sleep(200);
			         Ropa_pom.logout();
			         log.info("Logout");
			         wd.quit();
			         
	 	}
			         catch (Exception e) {
			     		// TODO: handle exception
			     		log.error(e);
			         }
			         
	}//method
	 	
	 }//class

