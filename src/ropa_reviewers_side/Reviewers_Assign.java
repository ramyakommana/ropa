package ropa_reviewers_side;

import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
 * 
* May 15, 2017
* 3:34:03 PM
 */

public class Reviewers_Assign extends Ropa_pom {
	
	@Test(priority=16)
	  public void search() throws InterruptedException{
	try{
		
			Ropa_pom.configuration();
			// Login
			Ropa_pom.login(sh.getRow(1).getCell(0).getStringCellValue(), sh.getRow(1).getCell(1).getStringCellValue());
			log.info("Login as Admin");
			
					log.info("Search for application");
					//search
		           wd.findElement(By.id("cphMaster_txtSearchApplicationName")).sendKeys(sh.getRow(1).getCell(60).getStringCellValue());
		          wd.findElement(By.id("cphMaster_btnSearch")).click();
		          jse = (JavascriptExecutor)wd;
		    	jse.executeScript("window.scrollBy(0,1500)", "");
		    	
		         wd.findElement(By.linkText("View")).click();//select
		         jse.executeScript("window.scrollBy(0,1500)", "");
		       
		         Thread.sleep(300);
		         wd.findElement(By.linkText("Admin Draft Report")).click();//select
		         
		         jse.executeScript("window.scrollBy(0,2000)", "");
		         wd.findElement(By.linkText("Draft Report")).click();
		         Thread.sleep(100);
		      	//ACR Draft Recommendation  
		         wd.findElement(By.id("cphMaster_RblDraftDecision_0")).click();//accredit
		         log.info("accredit the application");
		         Thread.sleep(500);
		         wd.findElement(By.id("cphMaster_btnNext")).click();
		         Thread.sleep(300);
		         
		}catch (Exception e) {
		// TODO: handle exception
		log.error(e);
		}	
 }
	
	  @Test(priority=18)
	  public void admin_draft() throws InterruptedException{
	  
		  try{
    		   	wd.findElement(By.id("cphMaster_Finish")).click();//submit
    		   	Thread.sleep(300);
    		   	wd.findElement(By.linkText("Home")).click();//back to application search
	
    		   	//search
		         wd.findElement(By.id("cphMaster_txtSearchApplicationName")).sendKeys(sh.getRow(1).getCell(60).getStringCellValue());
		        wd.findElement(By.id("cphMaster_btnSearch")).click();
		        Thread.sleep(300);
		        jse = (JavascriptExecutor)wd;
		        jse.executeScript("window.scrollBy(0,1500)", "");
		    	
		        wd.findElement(By.linkText("View")).click();//view
		        Thread.sleep(300);
		        wd.findElement(By.linkText("View")).click();//view
		        Thread.sleep(300);
		        wd.findElement(By.id("cphMaster_btnAssgnReviewer")).click();//assign button

		        // ************* Assigning Committee Reviewer1******************
		        Select dropdown = new Select(wd.findElement(By.id("cphMaster_ddlCommitteeReviewer1")));
				dropdown.selectByVisibleText(prop.getProperty("reviewer1"));
				 log.info("Assigning Committee Reviewer1");
				 Thread.sleep(300);
				 // ****************** Assigning Committee Reviewer2***********************
				Select dropdown1 = new Select(wd.findElement(By.id("cphMaster_ddlCommitteeReviewer2")));
				dropdown1.selectByVisibleText(prop.getProperty("reviewer2"));
				 log.info("Assigning Committee Reviewer2");
				 Thread.sleep(300);
				wd.findElement(By.id("cphMaster_btnSubmit")).click();
				Thread.sleep(300);
				
				 Ropa_pom.logout();
				 log.info("Logout");
				 wd.quit();
				 	 
		  }catch (Exception e) {
				// TODO: handle exception
				log.error(e);
			}	
	
	  }	
	
}
