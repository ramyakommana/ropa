/**
 * 
 */
package page_objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;


/**
 * @author KRamyaSree
 *
 * QA_Ropa
* Sep 4, 2017
* 3:15:32 PM
 */
public class Ropa_pom {

	public static WebDriver wd;
	public static FileInputStream fis;
	public static Workbook wb;
	public static Sheet sh;
	public static JavascriptExecutor jse; 
	public static Alert alert; 
	public static String beam;
	public static String c;
	public static String url;
	public static int site;
	public static Long appsite;
	public static int i;
	public static int v;
	public static Logger log;
	public static String user;
	public static String password;
	public static File file;
	public static Properties prop;

	/**
	 * @param args
	 */
	
	//CONFIGURATION
	 public static void configuration() {

  		try{
  			log = Logger.getLogger("Testnew");
  			file = new File("D:/workspace/QA_Ropa/src/DataFile.properties");
  			FileInputStream fileInput = null;
  			try {
  				fileInput = new FileInputStream(file);
  			} catch (FileNotFoundException e) {
  				e.printStackTrace();
  				log.error(e);
  			}
  			prop = new Properties();
  			
  			//load properties file
  			try {
  				prop.load(fileInput);
  			} catch (IOException e) {
  				e.printStackTrace();
  				log.error(e);
  			}
  			
  			 fis= new FileInputStream(prop.getProperty("Sheet_path"));
  			   wb=WorkbookFactory.create(fis);
  			    sh=wb.getSheet(prop.getProperty("QA_sheet"));
  			    
  			   browser(prop.getProperty("Browser")); 
  		}//try
			 catch (Exception e) {
					// TODO: handle exception
				}
	 }//method
	 
	 public static void browser(String browser) throws Exception{
		 
		 try{
  				if(browser.equalsIgnoreCase("Firefox")){
  					
  				//create firefox instance
  					System.setProperty("webdriver.firefox.marionette", prop.getProperty("driver")+"geckodriver.exe");
  					wd = new FirefoxDriver();
  				}
  				//Check if parameter passed as 'chrome'
  				else if(browser.equalsIgnoreCase("Chrome")){
  							
  					//set path to chromedriver.exe
  					System.setProperty("webdriver.chrome.driver",prop.getProperty("driver")+"chromedriver.exe");
  		 			 wd = new ChromeDriver();
  		   			
  				}
  				//Check if parameter passed as 'Edge'
  						else if(browser.equalsIgnoreCase("IE")){
  							
  							 System.setProperty("webdriver.ie.driver",prop.getProperty("driver")+"IEDriverServer.exe");
  				   			 wd = new InternetExplorerDriver();
  				   		 log.info("New  IE driver instantiated");
  						}
  				else{
  					//If no browser passed throw exception
  					throw new Exception("Browser is not correct");
  				}
			/* launching application */
			wd.get(prop.getProperty("QA_URL"));
			wd.manage().window().maximize();
			wd.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);

			wd.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			log.info("opening url");
			if(browser.equalsIgnoreCase("IE")){
			wd.findElement(By.id("overridelink")).click();
			}
			jse = (JavascriptExecutor) wd;
			
  			}//try
  				 catch (Exception e) {
  						// TODO: handle exception
  					}

  				}//method
	 //LOGIN
	 public static void login(String user, String password)throws InterruptedException{
		 // TODO Auto-generated method stub
		 //wd.get(prop.getProperty("QA_URL"));
		wd.findElement(By.id("cphMaster_txtEmail")).sendKeys(user);
		WebElement pass=wd.findElement(By.id("cphMaster_txtPassword"));
		pass.clear();
		pass.sendKeys(password);
		wd.findElement(By.id("cphMaster_Button1")).click();
		Thread.sleep(200);
		
	 }
	 
	 //LOGOUT
	 public static void logout() throws InterruptedException{
		 Thread.sleep(300);
		 wd.findElement(By.linkText("Logout")).click();
	 }
	 
	 //CALENDAR
	 public static void calendar( String id, String cal) throws InterruptedException{
		 WebElement date = wd.findElement(By.id(id));
		    List<WebElement> row=date.findElements(By.tagName("tr"));
		    List<WebElement> cols=date.findElements(By.tagName("td"));
		   
		    for (WebElement cel: cols)
		    {
		    	   //Select Date 
		    	   if (cel.getText().equals(cal))
		    	   {
		    		 
		    	   cel.findElement(By.linkText(cal)).click();
		    	   Thread.sleep(300);
		    	   break;
		    	   }
		    } 
	 }
	 
	 //APPLICATION VIEW
	 public static void view_assigned_Application( String assign_button) throws InterruptedException{
		 
		 wd.findElement(By.id(assign_button)).click(); //app for survey
		 Thread.sleep(200);
			
		 appsite =Math.round(sh.getRow(1).getCell(149).getNumericCellValue());
		 i =appsite.intValue();
		 //0=yes
		 //1=Recommendation
		 //2-no
		
					//loop to view the application
						v=2;
						for(int view=0;view<=10;view++)
						{
							if(v>6)
							{
							jse.executeScript("window.scrollBy(0,1500)", "");
							}
							
							String app=wd.findElement(By.id("cphMaster_grdapps_lblSiteName_"+view)).getText();
							
							String name=sh.getRow(1).getCell(60).getStringCellValue();

							if(app.equals(name))
							{
						wd.findElement(By.xpath(".//*[@id='cphMaster_grdapps']/tbody/tr["+v+"]/td[4]/a[1]")).click();
						break;
							}
							v++;
						}//view
						
						Thread.sleep(500);
		  }
	 
	 //CHAIR ASSIGN
	 public static void chair_assign() throws InterruptedException{
		  
		  try{
			  Thread.sleep(300);
			  //search
	          wd.findElement(By.id("cphMaster_txtSearchApplicationName")).sendKeys(sh.getRow(1).getCell(60).getStringCellValue());
	          wd.findElement(By.id("cphMaster_btnSearch")).click();
	          jse.executeScript("window.scrollBy(0,1500)", "");
	    	
	         wd.findElement(By.linkText("View")).click();//view
	         
	         wd.findElement(By.linkText("View")).click();//view
	         wd.findElement(By.id("cphMaster_btnAssignChair")).click();
	         
	         Thread.sleep(300);
	         
	         //Assigning Committee Chair:
	        Select dropdown1 = new Select(wd.findElement(By.id("cphMaster_ddlCommitteeCoChair")));
	        log.info("Assigning Committee Chair");
				
				dropdown1.selectByVisibleText(prop.getProperty("chair"));
				
				wd.findElement(By.id("cphMaster_btnSubmit")).click();
				
				Thread.sleep(300);
				 wd.findElement(By.linkText("Logout")).click();
				 Thread.sleep(500);
	  }
	  catch (Exception e) {
			// TODO: handle exception
			log.error(e);
		}
} //method
	 
	 public static void chair_forms( Long site) throws InterruptedException{
		 
		 	wd.navigate().to(prop.getProperty("QA_URL"));
		 	Thread.sleep(500);
		 	
		 	/*login with chair*/
		 	login(sh.getRow(1).getCell(145).getStringCellValue(), sh.getRow(1).getCell(146).getStringCellValue());
			log.info("login with chair");
			
			//Change Role:
			Select dropdown = new Select(wd.findElement(By.id("ddrROles")));
			dropdown.selectByVisibleText("Committee Chair Member");
			
			view_assigned_Application("cphMaster_bttnAppsFaciltiy");

		    	jse.executeScript("window.scrollBy(0,1500)", "");
		    	
		    	//opening chair forms from left side
				wd.findElement(By.linkText("Chair Forms")).click();
				jse.executeScript("window.scrollBy(0,1500)", "");
				wd.findElement(By.linkText("Chair Report")).click();
				log.info("Chair Report");
				
				//assigning rating scale value
				int i =site.intValue();

				//Recommendation for accreditation: 
				wd.findElement(By.id("cphMaster_p1_q1_rbl_"+i)).click();
				
				//Committee Chair Report: 
				wd.findElement(By.id("cphMaster_p1_q1_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				
				wd.findElement(By.id("cphMaster_BtnTopSubmit")).click();//submit to ACR
				log.info("submit to ACR");
				
	 		 
	 }
	 
	 
	 
	
	 
	 
}
