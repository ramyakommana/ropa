package smoke_testing;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import page_objects.Ropa_pom;

public class Smoke_testing extends Ropa_pom {
	public static int num;

	@Test
	public void Basic_smoke() throws InterruptedException {

		Ropa_pom.configuration();
		// Login
		Ropa_pom.login(sh.getRow(1).getCell(0).getStringCellValue(), sh.getRow(1).getCell(1).getStringCellValue());
		log.info("login");

		Thread.sleep(200);
		/*// search
		wd.findElement(By.id("cphMaster_txtSearchApplicationName"))
				.sendKeys(sh.getRow(1).getCell(60).getStringCellValue());
		wd.findElement(By.id("cphMaster_btnSearch")).click();
		log.info("Application Search");
		jse.executeScript("window.scrollBy(0,1500)", "");
		Thread.sleep(200);
		wd.findElement(By.linkText("Select")).click();
		Thread.sleep(200);
		wd.findElement(By.linkText("View")).click();
		// wd.findElement(By.linkText("View")).click();//once selected
		Thread.sleep(200);
		List<WebElement> count = wd.findElements(By.linkText("Edit"));
		num = count.size();

		// ********loop for multiple sites fmlid edit*******
		for (int edit = 0; edit <= num - 1; edit++) {

			// FMLID
			wd.findElement(By.id("cphMaster_gvSites_LBtnEdit_" + edit)).click();
			Thread.sleep(1500);
			wd.findElement(By.id("cphMaster_gvSites_txtFMLID_" + edit)).sendKeys("37" + edit);
			wd.findElement(By.id("cphMaster_gvSites_Lblupdate_" + edit)).click();
			log.info("FMLID edit for" + (edit + 1) + "time");
		}
		JavascriptExecutor jse = (JavascriptExecutor) wd;
		// survey aggrement from left side
		wd.findElement(By.linkText("Application Part II")).click();
		Thread.sleep(900);
		wd.findElement(By.linkText("Survey Agreement")).click();

		jse.executeScript("window.scrollBy(0,1500)", "");
		jse.executeScript("window.scrollBy(0,1500)", "");
		jse.executeScript("window.scrollBy(0,1500)", "");
		jse.executeScript("window.scrollBy(0,1500)", "");

		wd.findElement(By.id("cphMaster_cphApplication_txtACRDate")).click();// calendar
		Ropa_pom.calendar("calendar", String.valueOf(Math.round(sh.getRow(1).getCell(93).getNumericCellValue())));
		jse.executeScript("window.scrollBy(0,900)", "");
		wd.findElement(By.id("cphMaster_btnNext")).click();
		Thread.sleep(200);
		// alert
		alert = wd.switchTo().alert();
		alert.accept();
		wd.navigate().to(prop.getProperty("common_url") + "AddminApplication.aspx");
		wd.findElement(By.id("cphMaster_chkbox_Ropeer_payment")).click();// Ro_peer
																			// payment
		wd.findElement(By.id("cphMaster_payment_chk")).click();// payment
		Thread.sleep(200);
		wd.findElement(By.id("cphMaster_btnSubmit")).click();// accept
		Thread.sleep(200);
		log.info("Application Accept");*/
		
		wd.navigate().to(prop.getProperty("common_url")+"Admin/ReviewApplication.aspx");//home page
		
		wd.navigate().to(prop.getProperty("user_url"));//user search page
		
		String present_userUrl=wd.getCurrentUrl();
		if(prop.getProperty("user_url")==present_userUrl){
			
			log.info("Redirected to user URL");
		}else
		{
			log.info("Unsuccessfully Redirected to user URL");
		}
		
		wd.navigate().to(prop.getProperty("sent_mail"));//sent mail page
		String sent_mainPage=wd.getCurrentUrl();
		
		if(prop.getProperty("sent_mail")==sent_mainPage)
		{
			log.info("Redirected to sent mail page");
		}
		else
		{
			log.info("Unsuccessfully Redirected to sent mail page");
		}
		
		wd.navigate().to(prop.getProperty("Assign_surveyors"));//Assign surveyors page
		String Assign_surveyorsPage=wd.getCurrentUrl();
		
		if(prop.getProperty("Assign_surveyors")==Assign_surveyorsPage)
		{
			log.info("Redirected to Assign surveyors page");
		}
		else
		{
			log.info("Unsuccessfully Redirected to Assign surveyors Page");
		}
		
		wd.navigate().to(prop.getProperty("Assign_reviewers"));//Assign reviewers page
		String Assign_reviewersPage=wd.getCurrentUrl();
		
		if(prop.getProperty("Assign_surveyors")==Assign_reviewersPage)
		{
			log.info("Redirected to Assign reviewers page");
		}
		else
		{
			log.info("Unsuccessfully Redirected to Assign reviewers Page");
		}
		
		wd.navigate().to(prop.getProperty("Assign_chair"));//Assign chair page
		String Assign_chairPage=wd.getCurrentUrl();
		
		if(prop.getProperty("Assign_chair")==Assign_chairPage)
		{
			log.info("Redirected to Assign chair page");
		}
		else
		{
			log.info("Unsuccessfully Redirected to Assign chair Page");
		}
		
		boolean elementvisible=wd.findElement(By.linkText("Status wise applications")).isDisplayed();//Status wise application tab
		
		if(elementvisible==true){
			wd.findElement(By.linkText("Status wise applications")).click();
			
			
			for(int i=1;i<=17;i++){
				jse.executeScript("window.scrollBy(0,500)", "");
			boolean Status=wd.findElement(By.xpath(".//*[@id='AdminMenu_lev012']/a["+i+"]/span")).isDisplayed();
			
			if(Status==true){
				wd.findElement(By.xpath(".//*[@id='AdminMenu_lev012']/a["+i+"]/span")).click();
				Thread.sleep(300);
			}
			else{
				log.info("element"+i +"is not visisble");
			}
			
			}//for

			
		}//if
		
		Ropa_pom.logout();
		Thread.sleep(1500);
		
		wd.get(prop.getProperty("QA_URL"));
		
		// Login
		Ropa_pom.login(sh.getRow(1).getCell(102).getStringCellValue(), sh.getRow(1).getCell(103).getStringCellValue());
		log.info("Login as MD");
		Thread.sleep(300);
		
		//Change Role:
		Select dropdown = new Select(wd.findElement(By.id("ddrROles")));
		dropdown.selectByVisibleText("MD Surveyor");
		 
		 wd.navigate().to(prop.getProperty("surveyors_app"));//surveyors Application page
		
		 String surveyors_appPage=wd.getCurrentUrl();
			
			if(prop.getProperty("surveyors_app")==surveyors_appPage)
			{
				log.info("Redirected to surveyors Application page of md");
			}
			else
			{
				log.info("Unsuccessfully Redirected to surveyors Application page of md");
				
			}
			
			
			Ropa_pom.logout();
			Thread.sleep(1500);
			
			wd.get(prop.getProperty("QA_URL"));
			
			// Login
			Ropa_pom.login(sh.getRow(1).getCell(0).getStringCellValue(), sh.getRow(1).getCell(1).getStringCellValue());
			log.info("Login as PHY");
			Thread.sleep(300);
			
			//Change Role:
			Select dropdown1 = new Select(wd.findElement(By.id("ddrROles")));
			dropdown1.selectByVisibleText("Physicist Surveyor");
			
			wd.navigate().to(prop.getProperty("surveyors_app"));//surveyors Application page
			
			 surveyors_appPage=wd.getCurrentUrl();
				
				if(prop.getProperty("surveyors_app")==surveyors_appPage)
				{
					log.info("Redirected to surveyors Application page of phy");
				}
				else
				{
					log.info("Unsuccessfully Redirected to surveyors Application page oh phy");
					
				}
				
				
				Ropa_pom.logout();
				Thread.sleep(1500);

		
		
		
		
		
		

	}
}
