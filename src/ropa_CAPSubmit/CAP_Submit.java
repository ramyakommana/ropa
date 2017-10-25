package ropa_CAPSubmit;

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
import org.openqa.selenium.remote.server.handler.RefreshPage;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import page_objects.Ropa_pom;


/**
 * @author KRamyaSree
 *
 * QA_Ropa
* May 30, 2017
* 3:36:45 PM
 */
public class CAP_Submit extends Ropa_pom {
	
	 @Test(priority=25)
	 public void cap_submit() throws InterruptedException{
		 
		  try{
			  Ropa_pom.configuration();
			  	
				// Login
				Ropa_pom.login(sh.getRow(1).getCell(0).getStringCellValue(), sh.getRow(1).getCell(1).getStringCellValue());
				log.info("login with admin");
				Thread.sleep(300);
				
				//search
		           wd.findElement(By.id("cphMaster_txtSearchApplicationName")).sendKeys(sh.getRow(1).getCell(60).getStringCellValue());
		          wd.findElement(By.id("cphMaster_btnSearch")).click();
		          Thread.sleep(300);
		    	jse.executeScript("window.scrollBy(0,1500)", "");
		    	
		         wd.findElement(By.linkText("View")).click();//view
		         Thread.sleep(300);
		         jse.executeScript("window.scrollBy(0,2000)", "");
		         
		         //opening CAP from left side
		         wd.findElement(By.linkText("CAP")).click();//CAP
		         Thread.sleep(300);
		        wd.navigate().to(prop.getProperty("common_url")+"CAP.aspx");
		        
		        String browser=prop.getProperty("Browser");
				  if(browser.equalsIgnoreCase("IE")){
					  wd.findElement(By.id("ulFiles")).click();
					  Runtime.getRuntime().exec("D:\\FileUpload.exe");
					    Thread.sleep(200);
					    wd.findElement(By.id("btnUpload")).click();
					    Thread.sleep(200);
				  } if(browser.equalsIgnoreCase("Firefox")){
					  wd.findElement(By.id("ulFiles")).click();
					  Runtime.getRuntime().exec("D:\\Upload.exe");
					    Thread.sleep(200);
					    wd.findElement(By.id("btnUpload")).click();
					    Thread.sleep(200);
				  }
				  else{
		        wd.findElement(By.id("ulFiles")).sendKeys(sh.getRow(1).getCell(59).getStringCellValue());
		       
		        wd.findElement(By.id("btnUpload")).click();
		        Thread.sleep(500);
				  }
		        wd.findElement(By.id("cphMaster_cphApplication_Finish")).click();
		        
		        log.info("Cap upload");
		        Thread.sleep(300);
		  }
		  catch (Exception e) {
				// TODO: handle exception
			  log.error(e);
			}
		        
}//method
	 
	 @Test(priority=26)
	  public void chair_after_cap() throws InterruptedException{
		 
		wd.get(prop.getProperty("common_url")+"Admin/ReviewApplication.aspx");
		 Thread.sleep(200);
		wd.navigate().refresh();
		 Thread.sleep(300);
		 
		 //Assign chair
		 Ropa_pom.chair_assign();
		 
		 //chair forms
		 Ropa_pom.chair_forms(Math.round(sh.getRow(1).getCell(153).getNumericCellValue()));
			//if i=0 cap accepted
			//if i=1 resubmit cap(so need to run the same script again)
			//if i=2 Recommend Resurvey
			//if i=3 Accept CAP (with SOSS)
		 
		 Ropa_pom.logout();
		 log.info("Logout");
	 	 wd.quit();
	 }
	  
}//class
