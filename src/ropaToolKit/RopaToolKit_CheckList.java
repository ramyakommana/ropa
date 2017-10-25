package ropaToolKit;

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
 * @author KRamyaSree
 *
 * QA_Ropa
* May 30, 2017
* 3:37:16 PM
 */
public class RopaToolKit_CheckList extends Ropa_pom{
			
				@Test(priority=15)
				public void login() throws InterruptedException
				{
					try {
						Ropa_pom.configuration();
						// Login
						Ropa_pom.login(sh.getRow(1).getCell(2).getStringCellValue(), sh.getRow(1).getCell(1).getStringCellValue());
						log.info("Login with Facility");
					}

					catch (Exception e) {
						log.error(e);
					}
			
				//opening toolkit from left side
				wd.findElement(By.linkText("ROPA Tool Kit")).click();
				wd.findElement(By.linkText("CheckList")).click();
				Thread.sleep(300);
				 //******************************CheckList page********************************************
				log.info("Ropatoolkit: CheckList page");
				 //Time Out Policy *
				 wd.findElement(By.id("CheckListV3_1_rb_0")).click();
			    
				 //Contrast Policy 
				 wd.findElement(By.id("CheckListV3_2_rb_0")).click();
				 
				 //Imaging Portal and IGRT 
				 wd.findElement(By.id("CheckListV3_3_rb_0")).click();
				
				 
				 //Disaster Plan *
				 wd.findElement(By.id("CheckListV3_4_rb_0")).click();
				 
				 JavascriptExecutor jse = (JavascriptExecutor)wd;
				 jse.executeScript("window.scrollBy(0,150)", "");
				
				 //Infection Control *
				 wd.findElement(By.id("CheckListV3_5_rb_0")).click();
				 
				 //	Policy and Procedures Comments 
				 wd.findElement(By.id("cphMaster_cphDataCollection_CheckListV1_PolicyProcedures_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				 jse.executeScript("window.scrollBy(0,300)", "");
				 
				 //Rating Scale 
				 wd.findElement(By.id("PolicyAndProceduresLikeRating_rb_2")).click();
				 jse.executeScript("window.scrollBy(0,150)", "");
				 
				 //Chart Rounds *
				 wd.findElement(By.id("CheckListV3_6_rb_0")).click();
				
				 //M&M *
				 wd.findElement(By.id("CheckListV3_7_rb_0")).click();
				
				 //Focus Studies
				 wd.findElement(By.id("CheckListV3_8_rb_0")).click();
				 
				 //Internal Outcome
				 wd.findElement(By.id("CheckListV3_9_rb_0")).click();
				 
				 //Physician Peer Review Documentation
				 wd.findElement(By.id("CheckListV3_10_rb_0")).click();
				  jse.executeScript("window.scrollBy(0,150)", "");
				 
				 //Physicist Peer Review Documentation
				 wd.findElement(By.id("CheckListV3_11_rb_0")).click();
				 
				 //QA and CQI 
				 wd.findElement(By.id("cphMaster_cphDataCollection_CheckListV1_QA_CQI_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				 jse.executeScript("window.scrollBy(0,300)", "");
				 
				 //Rating Scale
				 wd.findElement(By.id("QAAndCQILikeRating_rb_2")).click();
				 jse.executeScript("window.scrollBy(0,150)", "");
				 
				 //comment
				 wd.findElement(By.id("cphMaster_cphDataCollection_CheckListV1_CheckList_OverallComments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				 
				 wd.findElement(By.id("cphMaster_cphDataCollection_bottom_Next_btn")).click();//next
				 Thread.sleep(300);
				 log.info("End of CheckList page");
				 
				 Ropa_pom.logout();
		    		log.info("Logout");
		    		
		    		
				}//method
				
			}//class
