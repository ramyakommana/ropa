package ropa_CensusPart;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import page_objects.Ropa_pom;

/**
 * @author K RamyaSree
 *
 * QA_Ropa
 * 
* March 30, 2017
* 3:35:40 PM
 */

public class Census_agree extends Ropa_pom {
	
	public static Alert alert;
	public static String user;
	public static String password;
	
	 @Test(priority=5)
	  public void admin() throws InterruptedException{
		 
		 try{
		 
		 	Ropa_pom.configuration();
			// Login
			Ropa_pom.login(sh.getRow(1).getCell(0).getStringCellValue(), sh.getRow(1).getCell(1).getStringCellValue());
			log.info("Login with Admin");
			
					//search
		            wd.findElement(By.id("cphMaster_txtSearchApplicationName")).sendKeys(sh.getRow(1).getCell(60).getStringCellValue());
		            wd.findElement(By.id("cphMaster_btnSearch")).click();
		            JavascriptExecutor jse = (JavascriptExecutor)wd;
		    		jse.executeScript("window.scrollBy(0,1500)", "");
		    		//wd.findElement(By.linkText("View")).click();//view
		    		wd.findElement(By.id("cphMaster_grdapps_lnkView_grd_0")).click();
		    		 Thread.sleep(1500);
		    		 
		    		 String browser=prop.getProperty("Browser");
					  if(browser.equalsIgnoreCase("IE")){
						  wd.findElement(By.linkText("Approve Census Sheets")).click();
				    		Thread.sleep(200);
					  }else{
		    		//opening census sheet and approving from left side
		    		wd.findElement(By.linkText("Approved Census Sheets")).click();
		    		Thread.sleep(200);
					  }
		    		wd.navigate().to(prop.getProperty("common_url")+"CensusSheets/ApproveCensusSheets.aspx");
		    		
		    		wd.findElement(By.id("cphMaster_gvCases_Avilable_chk_move_all")).click();//selecting census cases
		    		Thread.sleep(300);
		    		wd.findElement(By.id("cphMaster_btn_single_toright")).click();
		    		Thread.sleep(300);
		    		wd.findElement(By.id("cphMaster_gvCases_Approved_chk_moveback_all")).click();
		    		Thread.sleep(300);
		    		wd.findElement(By.id("cphMaster_btnSave")).click();
		    		Thread.sleep(300);
		    		log.info("approved Census sheets");
		    		
		    		Ropa_pom.logout();
		    		log.info("Logout");
		    		wd.quit();
		    		
	 			}	catch (Exception e) {
		    			// TODO: handle exception
		    			log.error(e);
	 			}
		    		
		  }//method
		  	
}


