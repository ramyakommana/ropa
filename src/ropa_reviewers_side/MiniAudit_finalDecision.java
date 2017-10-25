package ropa_reviewers_side;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import page_objects.Ropa_pom;

public class MiniAudit_finalDecision  extends Ropa_pom{
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
		         
		         wd.findElement(By.id("cphMaster_Finish")).click();//finish
		         Thread.sleep(300);
		         
		         wd.quit();
		         
		}catch (Exception e) {
		// TODO: handle exception
		log.error(e);
		}	
}
}
