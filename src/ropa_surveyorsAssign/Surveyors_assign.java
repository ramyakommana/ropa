package ropa_surveyorsAssign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hamcrest.Description;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import page_objects.Ropa_pom;
import ropa_1site_Application.Application;

/**
 * @author K RamyaSree
 *
 * QA_Ropa
* March 30, 2017
* 3:28:23 PM
 */

public class Surveyors_assign extends Ropa_pom{
	
	public static Alert alert; 
	public static String url;
	public static int num;
	public static String user;
	public static String password;
	
	  @Test(priority=2)
	  public void login() throws InterruptedException{
		  
		  try{
		  
		  			Ropa_pom.configuration();//if run separately
			 /* wd.findElement(By.xpath("//a[@class='head']")).click();
			  wd.findElement(By.xpath("//div[@id='loginDiv']/a[1]/span[1]")).click();*/
			
	  				//Login 
	  				Ropa_pom.login(sh.getRow(1).getCell(0).getStringCellValue(), sh.getRow(1).getCell(1).getStringCellValue());
	  				log.info("Login as admin");
		  
	  				Thread.sleep(200);
					//search
		           wd.findElement(By.id("cphMaster_txtSearchApplicationName")).sendKeys(sh.getRow(1).getCell(60).getStringCellValue());
		          wd.findElement(By.id("cphMaster_btnSearch")).click();
		          log.info("Application Search");
		    		jse.executeScript("window.scrollBy(0,1500)", "");
		    		Thread.sleep(200);
		    		wd.findElement(By.linkText("Select")).click();
		    		Thread.sleep(200);
		    		wd.findElement(By.linkText("View")).click();
		    	//wd.findElement(By.linkText("View")).click();//once selected
		    		Thread.sleep(200);
		    		List<WebElement> count=wd.findElements(By.linkText("Edit"));
			    	num=count.size();

			  
			  //********loop for multiple sites fmlid edit*******
			  for(int edit=0;edit<=num-1;edit++)
			  {
				  	
		    		//FMLID
		    	wd.findElement(By.id("cphMaster_gvSites_LBtnEdit_"+edit)).click();
		    	Thread.sleep(1500);
		    	wd.findElement(By.id("cphMaster_gvSites_txtFMLID_"+edit)).sendKeys("37"+edit);
		        wd.findElement(By.id("cphMaster_gvSites_Lblupdate_"+edit)).click();
		        log.info("FMLID edit for"+(edit+1)+"time");
			  }
			       JavascriptExecutor jse = (JavascriptExecutor)wd;
		    		//survey aggrement from left side
		    	  wd.findElement(By.linkText("Application Part II")).click();
		    	  Thread.sleep(900);
		    	  wd.findElement(By.linkText("Survey Agreement")).click();
		    		
		    		jse.executeScript("window.scrollBy(0,1500)", "");
		    		jse.executeScript("window.scrollBy(0,1500)", "");
		    		jse.executeScript("window.scrollBy(0,1500)", "");
		    		jse.executeScript("window.scrollBy(0,1500)", "");
		    		
		    		wd.findElement(By.id("cphMaster_cphApplication_txtACRDate")).click();//calendar
		    	    Ropa_pom.calendar("calendar", String.valueOf(Math.round(sh.getRow(1).getCell(93).getNumericCellValue())));
		   		   jse.executeScript("window.scrollBy(0,900)", "");
		            wd.findElement(By.id("cphMaster_btnNext")).click();
		            Thread.sleep(200);
		            //alert
					alert = wd.switchTo().alert();
					alert.accept();
		            wd.navigate().to(prop.getProperty("common_url")+"AddminApplication.aspx");
		            wd.findElement(By.id("cphMaster_chkbox_Ropeer_payment")).click();//Ro_peer payment
		    		wd.findElement(By.id("cphMaster_payment_chk")).click();//payment
		    		Thread.sleep(200);
		    		wd.findElement(By.id("cphMaster_btnSubmit")).click();//accept
		    		Thread.sleep(200);
		    		log.info("Application Accept");
		    			
					wd.navigate().to(prop.getProperty("common_url")+"Admin/ReviewApplication.aspx");
					
					//search
		            wd.findElement(By.id("cphMaster_txtSearchApplicationName")).sendKeys(sh.getRow(1).getCell(60).getStringCellValue());
		            Thread.sleep(900);
		            wd.findElement(By.id("cphMaster_btnSearch")).click();
		    		jse.executeScript("window.scrollBy(0,1500)", "");
		    		//wd.findElement(By.linkText("View")).click();//view
		    		wd.findElement(By.id("cphMaster_grdapps_lnkView_grd_0")).click();
		    		
		    		wd.findElement(By.linkText("View")).click();//view
	    		
		    		//assign surveyors
		    		wd.findElement(By.id("cphMaster_btnAssgnSurveyors")).click();
		    		
		    		//No of mds assign
		    		Long n=Math.round(sh.getRow(1).getCell(154).getNumericCellValue());
		    		int md =n.intValue();
		    		
		    		for( int m=1;m<=md;m++)
		    		{
		    		Thread.sleep(500);
		    		jse.executeScript("window.scrollBy(0,1500)", "");
		    		jse.executeScript("window.scrollBy(0,1500)", "");
		    		jse.executeScript("window.scrollBy(0,1500)", "");
		    		
		    		wd.findElement(By.id("btn_selectMD")).click();//md button
		    		Thread.sleep(1500);
		    		String parentWindowHandler = wd.getWindowHandle(); // Store your parent window	
		    		String subWindowHandler = null;
		    		Set<String> handles = wd.getWindowHandles();
		    		
		    		Iterator<String> iterator = handles.iterator();
		    		while (iterator.hasNext()){
		    		    subWindowHandler = iterator.next();
		    					}
		    		wd.switchTo().window(subWindowHandler);
		    		wd.findElement(By.id("cphMaster_txtSearchSurveyorName")).sendKeys(prop.getProperty("MD"+m));
		    		wd.findElement(By.id("cphMaster_btnSearch")).click();
		    		Thread.sleep(500);
		    		wd.findElement(By.id(sh.getRow(1).getCell(94).getStringCellValue())).click();//selecting md surveyors
		    		wd.findElement(By.id("cphMaster_btnAddSurveyorsToGrid")).click();
		    		Thread.sleep(500);
		    		log.info("Md surveyor assign");
		    		
		    		
		    		Thread.sleep(200);
		    		jse.executeScript("window.scrollBy(0,1500)", "");
		    		jse.executeScript("window.scrollBy(0,1500)", "");
		    		}
		    		
		    		//No of phys assign
		    		Long no=Math.round(sh.getRow(1).getCell(155).getNumericCellValue());
		    		int phy =no.intValue();

		    		for( int p=1;p<=phy;p++)
		    		{
		    		wd.findElement(By.id("btn_selectphy")).click();//physicist button
		    		Thread.sleep(1500);
		    		String parentWindowHandler = wd.getWindowHandle(); // Store your parent window	
		    		String subWindowHandler = null;
		    		Set<String> handles = wd.getWindowHandles();
		    		
		    		Iterator<String> iterator = handles.iterator();
		    		while (iterator.hasNext()){
		    		    subWindowHandler = iterator.next();
		    					}
		    		wd.switchTo().window(subWindowHandler);
		    		wd.findElement(By.id("cphMaster_txtSearchSurveyorName")).sendKeys(prop.getProperty("PHY"+p));
		    		Thread.sleep(300);
		    		wd.findElement(By.id("cphMaster_btnSearch")).click();
		    		wd.findElement(By.id(sh.getRow(1).getCell(95).getStringCellValue())).click();//selecting physicist
		    		Thread.sleep(500);
		    		wd.findElement(By.id("cphMaster_btnAddSurveyorsToGrid")).click();
		    		Thread.sleep(300);
		    		jse.executeScript("window.scrollBy(0,1500)", "");
		    		log.info("PHY surveyor assign");
		    		}
		    		
		    		Select drop = new Select(wd.findElement(By.id("cphMaster_ddlDates")));
		    		drop.selectByIndex(1);
		    		
		    		
		   		   wd.findElement(By.id("txtNoofdays")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(97).getNumericCellValue())));
		   		   jse.executeScript("window.scrollBy(0,1500)", "");
	    		   jse.executeScript("window.scrollBy(0,1500)", "");
	    		
		    		wd.findElement(By.id("cphMaster_btn_Assign")).click();	
		    		
		    		 try {
		    		        WebDriverWait wait = new WebDriverWait(wd, 2);
		    		        wait.until(ExpectedConditions.alertIsPresent());
		    		        Alert alert = wd.switchTo().alert();
		    		        alert.accept();
		    		        Thread.sleep(300);
				    		Ropa_pom.logout();
				    		log.info("Logout");
				    		Thread.sleep(1500);
				    		wd.quit();
		    		    } catch (Exception e) {
		    		        //exception handling
		    		    	Thread.sleep(300);
				    		Ropa_pom.logout();
				    		log.info("Logout");
				    		Thread.sleep(1500);
				    		
		    		    }
		  
		    wd.quit();		
		  }
		  catch (Exception e) {
				// TODO: handle exception
				log.error(e);
		  }
}//method
		    
			  }
	
