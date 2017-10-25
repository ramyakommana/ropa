package ropa_reviewers_side;

import org.testng.annotations.Test;

import page_objects.Ropa_pom;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

/**
 * @author K RamyaSree
 *
 * QA_Ropa
 * 
* May 16, 2017
* 10:34:40 AM
 */

public class Reviewers_Report extends Ropa_pom {
	
	@Test(priority=20)
  public void review1_form()throws InterruptedException{
	  
		try{
			Ropa_pom.configuration();
			//login with reviewer1
			Ropa_pom.login(sh.getRow(1).getCell(141).getStringCellValue(), sh.getRow(1).getCell(142).getStringCellValue());
			 log.info("Login as reviewer1");
			
			//Change Role:
			Select dropdown = new Select(wd.findElement(By.id("ddrROles")));
			dropdown.selectByVisibleText("Committee Review Member");
			Thread.sleep(200);
			
			Ropa_pom.view_assigned_Application("cphMaster_bttnAppsFaciltiy");
			jse.executeScript("window.scrollBy(0,1500)", "");
			
			//opening review forms from left side
			wd.findElement(By.linkText("Review Forms")).click();
			Thread.sleep(300);
			jse.executeScript("window.scrollBy(0,1500)", "");
			wd.findElement(By.linkText("Committee Report")).click();
			log.info("Committee Report");
				
	    		jse.executeScript("window.scrollBy(0,1500)", "");
	  
	    		// 	Identify any discrepancies in report vs. supporting documentation:   
	    		wd.findElement(By.id("cphMaster_p1_q1_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
	  
	    		//Describe your recommendations for changes/corrections to the report:  
	    		wd.findElement(By.id("cphMaster_p1_q2_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
	    		
	    		// 	List any additional survey report recommendations to the facility:  
	    		wd.findElement(By.id("cphMaster_p1_q3_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
	    		
	    		jse.executeScript("window.scrollBy(0,1500)", "");
	    		
	    		// 	Please include any comments regarding the Surveyors’ reports 
	    		wd.findElement(By.id("cphMaster_p1_q7_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
	    		
	    		//assigning rating scale value
	    		Long site =Math.round(sh.getRow(1).getCell(150).getNumericCellValue());
				int i =site.intValue();
	    			    	
	    		//State your recommendation for accreditation outcome.(rating scale) 
	    		wd.findElement(By.id("cphMaster_p1_q4_rb_"+i)).click();
	    		
	    		if(i==0||i==1){
	    			
	    			//If Minor Deviation/Non-Compliant, should this site be revisited prior to granting accreditation? 
		    		wd.findElement(By.id("cphMaster_p1_q5_rb_0")).click();
		    		wd.findElement(By.id("cphMaster_p1_q6_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
	    		}    		
	    		
	    		jse.executeScript("window.scrollBy(0,1500)", "");
	    		
	    		wd.findElement(By.id("cphMaster_Button1")).click();//submit to ACR
	    		log.info("Committee Report submit to ACR");
	    		Thread.sleep(300);
	    		
	    		 Ropa_pom.logout();
				 log.info("Logout");
	    		
		}catch (Exception e) {
			// TODO: handle exception
			log.error(e);
		}	
    		
  } 
				  @Test(priority=21)
			  public void review2_form()throws InterruptedException{
				  
			 try{
					  wd.navigate().to(prop.getProperty("QA_URL"));
					  
					//login with reviewer2
					Ropa_pom.login(sh.getRow(1).getCell(143).getStringCellValue(), sh.getRow(1).getCell(144).getStringCellValue());
					log.info("login with reviewer2");
					Thread.sleep(300);
					//Change Role:
					Select dropdown = new Select(wd.findElement(By.id("ddrROles")));
					dropdown.selectByVisibleText("Committee Review Member");
					Thread.sleep(300);
					Ropa_pom.view_assigned_Application("cphMaster_bttnAppsFaciltiy");
					
					jse.executeScript("window.scrollBy(0,1500)", "");
					//opening review forms from left side
					wd.findElement(By.linkText("Review Forms")).click();
					jse = (JavascriptExecutor)wd;
			  		jse.executeScript("window.scrollBy(0,1500)", "");
					wd.findElement(By.linkText("Committee Report")).click();
					log.info("Committee Report");
					Thread.sleep(300);
			  		// 	Identify any discrepancies in report vs. supporting documentation:   
			  		wd.findElement(By.id("cphMaster_p1_q1_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
			
			  		//Describe your recommendations for changes/corrections to the report:  
			  		wd.findElement(By.id("cphMaster_p1_q2_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
			  		
			  		// 	List any additional survey report recommendations to the facility:  
			  		wd.findElement(By.id("cphMaster_p1_q3_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
			  		
			  		jse.executeScript("window.scrollBy(0,1500)", "");
			  		
			  		// 	Please include any comments regarding the Surveyors’ reports 
			  		wd.findElement(By.id("cphMaster_p1_q7_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
			  		
			  		//assigning rating scale value
					Long site =Math.round(sh.getRow(1).getCell(151).getNumericCellValue());
					int i =site.intValue();
			  			    	
			  		//State your recommendation for accreditation outcome.(rating scale) 
			  		wd.findElement(By.id("cphMaster_p1_q4_rb_"+i)).click();
			  		
			  		if(i==0||i==1){
			  			
			  			//If Minor Deviation/Non-Compliant, should this site be revisited prior to granting accreditation? 
				    		wd.findElement(By.id("cphMaster_p1_q5_rb_0")).click();
				    		wd.findElement(By.id("cphMaster_p1_q6_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
			  		}
			  		
			  		jse.executeScript("window.scrollBy(0,1500)", "");
			  		
			  		wd.findElement(By.id("cphMaster_Button1")).click();//submit to ACR
			  		log.info("Committee Report submit to ACR");
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
