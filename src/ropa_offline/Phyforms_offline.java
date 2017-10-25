package ropa_offline;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import page_objects.Ropa_pom;

/**
 * @author KRamyaSree
 *
 * QA_Ropa
* may 30, 2017
* 6:30:33 PM
 */
public class Phyforms_offline extends Ropa_pom {
	
	public static int i;
	private static String downloadFilepath;
	public static  int num;
	  	
	  @Test(priority=10)
	  public void login() {

		  try{
			  file = new File("D:/workspace/QA_Ropa/src/DataFile.properties");
	  			FileInputStream fileInput = null;
	  			try {
	  				fileInput = new FileInputStream(file);
	  			} catch (FileNotFoundException e) {
	  				e.printStackTrace();
	  			}
	  			prop = new Properties();
	  			
	  			//load properties file
	  			try {
	  				prop.load(fileInput);
	  			} catch (IOException e) {
	  				e.printStackTrace();
	  			}
	  			
	  			 fis= new FileInputStream(prop.getProperty("Sheet_path"));
	  			   wb=WorkbookFactory.create(fis);
	  			    sh=wb.getSheet(prop.getProperty("QA_sheet"));
	  				 
	  			System.setProperty("webdriver.chrome.driver",prop.getProperty("driver")+"chromedriver.exe");
	  			 log = Logger.getLogger("Testnew");
	
	  			downloadFilepath = prop.getProperty("downloadFilepath_phy");

				  
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			    chromePrefs.put("profile.default_content_settings.popups", 0);
			    chromePrefs.put("download.default_directory", downloadFilepath);
			    ChromeOptions options = new ChromeOptions();
			    HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
			    options.setExperimentalOption("prefs", chromePrefs);
			    options.addArguments("--test-type");
			    options.addArguments("--disable-extensions"); 
			    
			    DesiredCapabilities cap = DesiredCapabilities.chrome();
			    cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
			    cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			    cap.setCapability(ChromeOptions.CAPABILITY, options);
			    log.info("New driver instantiated");
			    wd = new ChromeDriver(cap);
			    wd.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
			    log = Logger.getLogger("Testnew");
				 //wd = new ChromeDriver();
			    
			    wd.get(prop.getProperty("QA_URL"));
			    wd.manage().window().maximize();
  				wd.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
  				
  				// Login
				Ropa_pom.login(sh.getRow(1).getCell(0).getStringCellValue(), sh.getRow(1).getCell(1).getStringCellValue());
				log.info("Login as PHY");
				
				//Change Role:
				Select dropdown = new Select(wd.findElement(By.id("ddrROles")));
				dropdown.selectByVisibleText("Physicist Surveyor");

				wd.findElement(By.id("cphMaster_btnAppliAssgndtoMe")).click(); //app for survey
				Thread.sleep(300);
				//application name
			   String  name=sh.getRow(1).getCell(60).getStringCellValue();
			    
				JavascriptExecutor jse = (JavascriptExecutor)wd;
				//jse.executeScript("window.scrollBy(0,700)", "");
				int v=2;
				for(int view=0;view<=10;view++)
				{
					String app=wd.findElement(By.id("cphMaster_grdapps_lblSiteName_"+view)).getText();
					
					 name=sh.getRow(1).getCell(60).getStringCellValue();

					if(app.equals(name))
					{
				wd.findElement(By.xpath(".//*[@id='cphMaster_grdapps']/tbody/tr["+v+"]/td[4]/a[2]")).click();
				Thread.sleep(300);
				break;
					}
					v++;
				}//view
				
				Thread.sleep(500);
				String parentWindowHandler = wd.getWindowHandle(); // Store your parent window	
	    		String subWindowHandler = null;
	    		Set<String> handles = wd.getWindowHandles();
	    		
	    		Iterator<String> iterator = handles.iterator();
	    		while (iterator.hasNext()){
	    		    subWindowHandler = iterator.next();
	    		}
	    		wd.switchTo().window(subWindowHandler);
	    		
				wd.findElement(By.id("chkSelect")).click();
				Thread.sleep(300);
				wd.findElement(By.id("cphMaster_btnok")).click();
				Thread.sleep(300);
				 log.info("Downloading Phy offline file");
				 Thread.sleep(5000);
				 wd.findElement(By.linkText("Logout")).click();
					Thread.sleep(5000);

				 
				  }//main try
					catch(Exception e)
					{
					log.error(e);
					}
				}
	  
	  @Test(priority=11)
				  public static void filezip (){
					  Unzipper ropa=new Unzipper();
					  ropa.zip1();
	  }
					  
	
		@Test(priority=12)
		public void phy_interview_form() throws InterruptedException{
			
			 //application name
		     String name=sh.getRow(1).getCell(60).getStringCellValue();
		
			 wd.get(prop.getProperty("phy_unzipped_file")+"/OfflineFiles/"+name+"-PHY/Index.html");
			 log.info("Opening Phy offline forms from local drive");

			//*******************Physicist Interview Forms*******************
	
			 log.info("Offline: Physicist Interview Forms");
			 	//opening from left side
				wd.findElement(By.linkText("Physicist Interview Forms")).click();
				
				wd.findElement(By.linkText("Physicist Interview Page 1")).click();
				wd.findElement(By.linkText("Physicist Interview Page 1")).click();
				Thread.sleep(300);
				Long site =Math.round(sh.getRow(1).getCell(149).getNumericCellValue());
				int i =site.intValue();
				//0=yes
				//1=Recommendation
				//2-no
							String s="string"+i;
							
							// 	Do you have an ADCL Instrumentation calibration report that was performed within the last 2 years? 
							wd.findElement(By.id("ON_P1_PHYINTV3_1A_rb_"+i)).click();
							 JavascriptExecutor jse = (JavascriptExecutor)wd;
							
							
							// 	Do you have a second independent dosimetry system?
							wd.findElement(By.id("ON_P1_PHYINTV3_1B_rb_"+i)).click();
							jse.executeScript("window.scrollBy(0,400)", "");
							if(s.equals("string0"))
							{
								// 	If yes, do you have records of periodic cross checks against the calibrated system?*
								wd.findElement(By.id("ON_P1_PHYINTV3_1B_i_rb_"+i)).click();
							}
							
							// 	Do you have a daily output constancy/flatness & symmetry device? 
							wd.findElement(By.id("ON_P1_PHYINTV3_1C_rb_"+i)).click();
							
							// 	Do you have access to patient dosimetry devices (e.g. diodes/MOSFET/TLD/OSLD)?
							wd.findElement(By.id("ON_P1_PHYINTV3_1D_rb_"+i)).click();
							
						 	//Do you have access to a water tank scanning system for annual calibrations? 
							wd.findElement(By.id("ON_P1_PHYINTV3_1E_rb_"+i)).click();
							
							//Do you have QA phantoms (e.g. plastic water, 1D water tank, specialty phantom)?
							wd.findElement(By.id("ON_P1_PHYINTV3_1F_rb_"+i)).click();
							
							// 	Do you have QA devices (e.g. IMRT, IGRT, CT Sim, SRS, Film)?
							wd.findElement(By.id("ON_P1_PHYINTV3_1G_rb_"+i)).click();
							
							// 	Do you have documentation of periodic barometer/thermometer calibration checks/intercomparisons?
							wd.findElement(By.id("ON_P1_PHYINTV3_1H_rb_"+i)).click();
							jse.executeScript("window.scrollBy(0,400)", "");
							// 	If Brachytherapy is performed, do you have source calibration instrumentation? (e.g. electrometer and well chamber) 
							wd.findElement(By.id("ON_P1_PHYINTV3_1I_rb_"+i)).click();
							if(s.equals("string0"))
							{
								// 	If yes, do you have records of ADCL instrument calibration performed within the last 2 years? 
								wd.findElement(By.id("ON_P1_PHYINTV3_1I_i_rb_"+i)).click();
							}
							
							// 	Do you have a survey meter(s)?
							wd.findElement(By.id("ON_P1_PHYINTV3_1J_rb_"+i)).click();
							if(s.equals("string0"))
							{
								// 	If yes, do you have records of instrument calibration performed within the last year?  
								wd.findElement(By.id("ON_P1_PHYINTV3_1J_i_rb_"+i)).click();
							}
							
							//Instrumentation Comments
							 wd.findElement(By.id("ON_P1_PHYINTV3_1_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							 jse.executeScript("window.scrollBy(0,300)", "");
							//Rating Scale
							 wd.findElement(By.id("Instrumentation_LikeRating_2")).click();
							
							 jse.executeScript("window.scrollBy(0,300)", "");
							 
							 //Do you have records of QA performed on the simulator/CT-simulator conforming to the current guidelines of the AAPM (TG-66)? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_2A_rb_"+i)).click();
							 
							 //Do you have the Acceptance Testing and Commissioning Report(s) conforming to the current AAPM guidelines? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_2B_rb_"+i)).click();
							 
							 //Do you have records of daily QA including output constancy checks? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_2C_rb_"+i)).click();
							 
							 //Do you have records of monthly QA including output constancy checks as per AAPM/International guidelines? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_2D_rb_"+i)).click();
							 
							 //Do you have the most recent Annual Calibration Report(s) following current AAPM/International guidelines? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_2E_rb_"+i)).click();
							 
							 //Do you have an Independent Physicist calibration and/or RPC OSLD/TLD report performed within the last year? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_2F_rb_"+i)).click();
							 jse.executeScript("window.scrollBy(0,400)", "");
							 //7.On board imaging 
							 wd.findElement(By.id("ON_P1_PHYINTV3_Q2G_A_rb_"+i)).click();
							 //Cone Beam CT (CBCT) 
							 wd.findElement(By.id("ON_P1_PHYINTV3_Q2G_B_rb_"+i)).click();
							 //Respiratory Gating
							 wd.findElement(By.id("ON_P1_PHYINTV3_Q2G_C_rb_"+i)).click();
							 //Multi-leaf collimators (MLC)
							 wd.findElement(By.id("ON_P1_PHYINTV3_Q2G_D_rb_"+i)).click();
							
							 //Do you have periodic safety performance checks of all equipment used for patient setup and treatment (e.g. breast boards, immobilization devices, wedges, trays, etc.)? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_2H_rb_"+i)).click();
							 jse.executeScript("window.scrollBy(0,400)", "");
							 //Are there machine service and maintenance logs available and are they reviewed by the physicist?
							 wd.findElement(By.id("ON_P1_PHYINTV3_2I_rb_"+i)).click();
							 
							 //10.Do you perform Proton Therapy? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_11_rb_0")).click();
							 
							 // A. Do you perform daily QA 
							 wd.findElement(By.id("ON_P1_PHYINTV3_11A_rb_0")).click();
							 
							 //i. 	Beam Dosimetry Parameters 
							 wd.findElement(By.id("ON_P1_PHYINTV3_11A_ai_rb_"+i)).click();
							 
							 //ii. 	Patient setup verification
							 wd.findElement(By.id("ON_P1_PHYINTV3_11A_aii_rb_"+i)).click();
							 
							 //iii.  Communication 
							 wd.findElement(By.id("ON_P1_PHYINTV3_11A_aiii_rb_"+i)).click();
							
							 jse.executeScript("window.scrollBy(0,400)", "");
							 //iv. 	Safety 
							 wd.findElement(By.id("ON_P1_PHYINTV3_11A_aiv_rb_"+i)).click();
							 
							 //B. 	Do you perform weekly QA 
							 wd.findElement(By.id("ON_P1_PHYINTV3_11B_rb_0")).click();
							 
							 //i. 	Gantry angle vs gantry angle indicators
							 wd.findElement(By.id("ON_P1_PHYINTV3_11B_ai_rb_"+i)).click();
							 
							 //ii. 	Snout or applicator extensions
							 wd.findElement(By.id("ON_P1_PHYINTV3_11B_aii_rb_"+i)).click();
							 
							 //iii. Collision interlocks
							 wd.findElement(By.id("ON_P1_PHYINTV3_11B_aiii_rb_"+i)).click();
							 
							 //iv. 	Imaging Systems 
							 wd.findElement(By.id("ON_P1_PHYINTV3_11B_aiv_rb_"+i)).click();
							 
							 //v. 	Respiratory gating equipment
							 wd.findElement(By.id("ON_P1_PHYINTV3_11B_av_rb_"+i)).click();
							 jse.executeScript("window.scrollBy(0,300)", "");
							 //C.	Do you perform monthly QA
							 wd.findElement(By.id("ON_P1_PHYINTV3_11C_rb_0")).click();
							 
							 //i. 	Beam dosimetry parameters
							 wd.findElement(By.id("ON_P1_PHYINTV3_11C_ai_rb_"+i)).click();
							 
							 //ii. 	Mechanical checks
							 wd.findElement(By.id("ON_P1_PHYINTV3_11C_aii_rb_"+i)).click();
							 
							 //Multi-leaf collimator
							 wd.findElement(By.id("ON_P1_PHYINTV3_11C_aiii_rb_"+i)).click();
							 jse.executeScript("window.scrollBy(0,300)", "");
							 //D. 	Do you perform annual QA
							 wd.findElement(By.id("ON_P1_PHYINTV3_11D_rb_0")).click();
							 
							 // i. Beam dosimetry parameters 
							 wd.findElement(By.id("ON_P1_PHYINTV3_11D_ai_rb_"+i)).click();
							 
							 //ii. 	Mechanical
							 wd.findElement(By.id("ON_P1_PHYINTV3_11D_aii_rb_"+i)).click();
							 
							 //iii. 	Imaging devices
							 wd.findElement(By.id("ON_P1_PHYINTV3_11D_aiii_rb_"+i)).click();
							 
							 //iv. 	Safety checks
							 wd.findElement(By.id("ON_P1_PHYINTV3_11D_aiv_rb_"+i)).click();
							 
							 //v. 	Visual Inspections 
							 wd.findElement(By.id("ON_P1_PHYINTV3_11D_av_rb_"+i)).click();
							 jse.executeScript("window.scrollBy(0,400)", "");
							 
							//E. 	Is protocol TRS 398 performed? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_11E_rb_0")).click();
							 
							 //F. 	Is the site reviewed by RPC or iROC Houston for calibration and QA?
							 wd.findElement(By.id("ON_P1_PHYINTV3_11F_rb_"+i)).click();
							 
							 //G. 	Do you use a proton specific treatment planning system? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_11G_rb_0")).click();
							 
							 //a. 	If yes, do you perform commissioning for your treatment planning system?
							 wd.findElement(By.id("ON_P1_PHYINTV3_11G_i_rb_"+i)).click();
							 
							 //H. 	Following staff have documented training to perform proton therapy treatment:
							 // 	i. 	Radiation Oncologist
							 wd.findElement(By.id("ON_P1_PHYINTV3_11H_i_rb_"+i)).click();
							 
							 //ii. 	Medical Physicists
							 wd.findElement(By.id("ON_P1_PHYINTV3_11H_ii_rb_"+i)).click();
							 jse.executeScript("window.scrollBy(0,300)", "");
							 //iii. 	Dosimetrist
							 wd.findElement(By.id("ON_P1_PHYINTV3_11H_iii_rb_"+i)).click();
							
							 //iv. 	Radiation Therapist
							 wd.findElement(By.id("ON_P1_PHYINTV3_11H_iv_rb_"+i)).click();
						
							 //Simulation/Treatment Machine/Quality Assurance Comments
							 wd.findElement(By.id("ON_P1_PHYINTV3_2_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							 jse.executeScript("window.scrollBy(0,300)", "");
							 //Rating Scale
							 wd.findElement(By.id("Simulation_LikeRating_2")).click();
							 jse.executeScript("window.scrollBy(0,200)", "");
							 
							 //Is treatment planning data obtained or confirmed on-site by direct measurement? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_3A_rb_"+i)).click();
							 
							 //Do you have an Acceptance/Commissioning report for the treatment planning system(s) including IMRT planning evaluation? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_3B_rb_"+i)).click();
							 
							 //Do you have periodic/annual check of the treatment planning system(s)?
							 wd.findElement(By.id("ON_P1_PHYINTV3_3C_rb_"+i)).click();
							 
							 //Do you have records of QA evaluations following hardware/software updates? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_3D_rb_"+i)).click();
							 
							 //Do you have evidence of initial and continuing staff training for the treatment planning system(s)? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_3E_rb_"+i)).click();
							 
							 //TREATMENT PLANNING (External and Brachytherapy) Comments
							 wd.findElement(By.id("ON_P1_PHYINTV3_3_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							
							//Rating Scale 
							 wd.findElement(By.id("Treatment_Planning_LikeRating_2")).click();
							 jse.executeScript("window.scrollBy(0,400)", "");
							 
							 //For external beam and temporary brachytherapy (i.e. HDR) treatments, do you have an independent MU/time calculation? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_4A_rb_"+i)).click();
							 
							 if(s.equals("string0"))
							 {
								// If yes, is this calculation performed prior to initiation of treatment? *
								 wd.findElement(By.id("ON_P1_PHYINTV3_4A_i_rb_"+i)).click();
							 }
							 
							 //Do you have a policy and procedure related to hypofractionation treatment (greater than 300 cGy/fraction)? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_4B_rb_"+i)).click();
							 
							 //For the solo physicist, do you have a AAPM TG-103 or equivalent physics peer review program in place?
							 wd.findElement(By.id("ON_P1_PHYINTV3_4C_rb_"+i)).click();
							 
							 // Do you have a physics policy and procedure manual that describes all aspects of the physics program
							 wd.findElement(By.id("ON_P1_PHYINTV3_4D_rb_"+i)).click();
							 
							 //Do you have policies and procedures regarding new equipment evaluation and assessment? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_4E_rb_"+i)).click();
							 
							 //Does the physicist participate in QA meetings and report on QA activities? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_4F_rb_"+i)).click();
							 jse.executeScript("window.scrollBy(0,500)", "");
							 //Are output measurements done for custom electron cutouts? 
							 wd.findElement(By.id("ON_P1_PHYINTV3_4G_rb_"+i)).click();
							
							 //GENERAL QUALITY ASSURANCE Comments
							 wd.findElement(By.id("ON_P1_PHYINTV3_txtComment")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							 
							//Rating Scale
							 wd.findElement(By.id("Gen_Qty_Assurance_LikeRating_2")).click();
							 jse.executeScript("window.scrollBy(0,900)", "");
							 
							 wd.findElement(By.id("btn_ONP_btm_next")).click();
							 Thread.sleep(300);
							
							 jse.executeScript("window.scrollBy(0,-500)", "");
							 
							 wd.findElement(By.linkText("Physicist DC Cases")).click();
							 
			//*******************Physicist  Data Collection*******************
					List<WebElement> count=wd.findElements(By.name("btn_Dc_Cases_view"));
					num=count.size();
			    	
			    	//***loop for multiple views***
					for(int v=1;v<=num;v++)
					{
						jse.executeScript("window.scrollBy(0,-500)", "");
						Thread.sleep(1000);
						wd.findElement(By.id("DC_Cases")).click();
						Thread.sleep(1000);
					wd.findElement(By.id("btn_Dc_Cases_view"+v)).click();
					Thread.sleep(300);
					log.info("Ropa offline:Physicist  Data Collection");
					Thread.sleep(5000);
		
						//Treating Physician:
						wd.findElement(By.id("DC_P1_TreatingPhysician_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						
						Thread.sleep(1000);
						//Chart and Physics Documentation
						wd.findElement(By.id("ui-accordion-div_Page_DC-header-0")).click();
						
						if(v!=1){
							
							wd.findElement(By.id("ui-accordion-div_Page_DC-header-0")).click();
							
						}
						Thread.sleep(1000);
						 
						jse.executeScript("window.scrollBy(0,400)", "");
						
						//Date of first treatment
						 wd.findElement(By.id("DC_Page_Sec1_Q1_txt")).click();//calendar

				   		   WebElement datenum = wd.findElement(By.id("ui-datepicker-div"));
				   		   
				   		   List<WebElement> rw=datenum.findElements(By.tagName("tr"));
				   		   List<WebElement> cs=datenum.findElements(By.tagName("td"));
				   		   
				   		
				   		   for (WebElement cellnum: cs)
				   		   {
				   		
				   		    	   //Select Date 
				   		    if (cellnum.getText().equals(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue()))))
				   		    	  {
				   		    		 
				   		    	 cellnum.findElement(By.linkText(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue())))).click();
				   		    	  break;
				   		    	 }
				   		   } 
				   		Thread.sleep(1500);
				   		
				   		
				   		// 	What site is being treated? 
				   		wd.findElement(By.id("DC_Page_Section1_q1_rb_0")).click();// Breast 
				   		
				   		//a. Is there a written directive? 
				   		wd.findElement(By.id("DC_Page_Section1_q1_1_rb_"+i)).click();
				   		
				   		//b. Is the written directive signed and dated by the radiation oncologist? 
				   		wd.findElement(By.id("DC_Page_Section1_q1_2_rb_"+i)).click();
				   		
				   		// 4	a. Are there verifiable photo(s) of patient set-up in the chart? 
				   		wd.findElement(By.id("DC_Page_Section1_q2_1_rb_"+i)).click();
				   		
				   		// 	b. Are DRR’s present in the chart? 
				   		wd.findElement(By.id("DC_Page_Section1_q2_2_rb_"+i)).click();
				   		
				   		// 	c. Are weekly physics chart checks documented by a physicist? 
				   		wd.findElement(By.id("DC_Page_Section1_q2_3_rb_"+i)).click();
				   		
				   		// 	d. Is there evidence that the physicist performed an end of treatment check within one week? 
				   		wd.findElement(By.id("DC_Page_Section1_q2_4_rb_"+i)).click();
				   		jse.executeScript("window.scrollBy(0,400)", "");
				   		//Chart and Physics Documentation Comments
				   		wd.findElement(By.id("DC_Page_Section1_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				   		
				   		
				   		//Rating Scale
				   		wd.findElement(By.id("Chart_LikeRating_2")).click(); //Compliant=3
				   		
				   		//**************Simulation*****************
						wd.findElement(By.id("ui-accordion-div_Page_DC-header-1")).click();
						
						if(v!=1){
							wd.findElement(By.id("ui-accordion-div_Page_DC-header-1")).click();
						}
						Thread.sleep(500);
						jse.executeScript("window.scrollBy(0,400)", "");
				   		
				   		//5. 	Was there documentation of a simulation? 
				   		wd.findElement(By.id("DC_Page_Section2_q5_rb_"+i)).click();
				   		
				   		s="string"+i;
				   		if(s.equals("string0"))
				   		{
				   			Select drop = new Select(wd.findElement(By.id("DC_Page_Section2_q4_ddl")));
							drop.selectByVisibleText("Conventional Simulation");
				   		}
				   		
				   		//6 	A. Was there an immobilization device(s) used? 
				   		wd.findElement(By.id("DC_Page_Section2_q6a_rb_0")).click();
				   		
				   		//B. Immobilization device documented
				   		wd.findElement(By.id("DC_Page_Section2_q3_ch_"+i)).click();	
				   		
				   		//Simulation Comments
				   		wd.findElement(By.id("DC_Page_Section2_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				   		jse.executeScript("window.scrollBy(0,400)", "");
				   		
				   		//Rating Scale
				   		wd.findElement(By.id("Treatment_LikeRating_2")).click();
				   		
				   		//*************************Treatment Planning ***********************
				   		
				   		wd.findElement(By.id("ui-accordion-div_Page_DC-header-2")).click();
				   		
				   		if(v!=1){
				   			wd.findElement(By.id("ui-accordion-div_Page_DC-header-2")).click();
						}
				   		Thread.sleep(500);
						jse.executeScript("window.scrollBy(0,400)", "");
						
						// 	a. Are calculation/isodose distribution present in chart? 
						wd.findElement(By.id("DC_Page_Section3_q5_1_rb_"+i)).click();
						
						// 	b. Are calculation/isodose distribution signed and dated by physician? 
						wd.findElement(By.id("DC_Page_Section3_q5_2_rb_"+i)).click();
						
						// 	c. Are calculation/isodose distribution signed and dated by physicist? 
						wd.findElement(By.id("DC_Page_Section3_q5_3_rb_"+i)).click();
						
						//8. 	Is there documentation of heterogeneity corrections? 
						wd.findElement(By.id("DC_Page_Section3_q6_rb_"+i)).click();
						
						//9. 	What algorithm are you using? 
						wd.findElement(By.id("DC_Page_Section3_q7_ch_0")).click();//BATHO
						
						// 	a. Organs at risk (OAR) 
						wd.findElement(By.id("DC_Page_Section3_q9_1_rb_"+i)).click();
						
						//b. Planning Target Volume (PTV) 
						wd.findElement(By.id("DC_Page_Section3_q9_2_rb_"+i)).click();
						
						// 	Is there evidence of an independent check of plan and calculations by medical physicist? 
						wd.findElement(By.id("DC_Page_Section3_q10_rb_"+i)).click();
						jse.executeScript("window.scrollBy(0,400)", "");
						//Treatment Planning Comments
						wd.findElement(By.id("DC_Page_Section3_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						
						
						//Rating Scale
						wd.findElement(By.id("Isodose_LikeRating_2")).click();
						
						//*********************Modalities**********************
						
						wd.findElement(By.id("ui-accordion-div_Page_DC-header-3")).click();

						if(v!=1){
				   			wd.findElement(By.id("ui-accordion-div_Page_DC-header-3")).click();
						}
						Thread.sleep(500);
						jse.executeScript("window.scrollBy(0,300)", "");
						
						for(int k=0;k<=5;k++)
						{
						wd.findElement(By.id("DC_Page_Section4_ch_"+k)).click();
						}
						
						 //*******************************IMRT tab****************************
					
						wd.findElement(By.id("ui-id-1")).click();
						Thread.sleep(500);
						//1. 	Type of Treatment
						Select drop= new Select(wd.findElement(By.id("DC_Page_IMRTV3_Q1_ddl")));
						drop.selectByVisibleText("Tomotherapy");
						
						//2. 	Are dose volume constraints documented by the Radiation Oncologist?
						wd.findElement(By.id("DC_Page_IMRTV3_Q2_rb_"+i)).click();
						
						//Was the accuracy of dose distribution documented by QA performed prior to treatment?
						wd.findElement(By.id("DC_Page_IMRTV3_Q3_rb_"+i)).click();
						
						//A. 	What is the gamma % passing? 
						wd.findElement(By.id("DC_Page_IMRTV3_QA_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						
						//B. 	What is the dose difference (if absolute dose is measured)?
						wd.findElement(By.id("DC_Page_IMRTV3_QB_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						jse.executeScript("window.scrollBy(0,400)", "");
						//C. 	If calculated, what is the Distance to Agreement? 
						wd.findElement(By.id("DC_Page_IMRTV3_QC_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						
						//IMRT Comments
						wd.findElement(By.id("DC_Page_IMRTV3_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					
						
						//Rating Scale 
						wd.findElement(By.id("IMRT_LikeRating_2")).click();
						
						jse.executeScript("window.scrollBy(0,-500)", "");
						
						//***********************SBRT tab**************************
						
						wd.findElement(By.id("ui-id-2")).click();
						Thread.sleep(500);
						//1. 	What is the device used for SBRT treatment? 
						Select drop1= new Select(wd.findElement(By.id("DC_Page_SBRTV3_Q1_ddl")));
						drop1.selectByVisibleText("Cyberknife");
						
						//2. 	Was a immobilization device used for treatment?
						wd.findElement(By.id("DC_Page_SBRTV3_Q2_rb_"+i)).click();
						jse.executeScript("window.scrollBy(0,300)", "");
						
						//3. 	Was patient specific QA performed prior to treatment?
						wd.findElement(By.id("DC_Page_SBRTV3_Q3_rb_"+i)).click();
						
						//4. 	Were the physician and physicist present onsite during imaging and treatment? 
						wd.findElement(By.id("DC_Page_SBRTV3_Q4_rb_"+i)).click();
						
						//SBRT Comments
						wd.findElement(By.id("DC_Page_SBRTV3_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						jse.executeScript("window.scrollBy(0,300)", "");
						
						//Rating Scale 
						wd.findElement(By.id("SBRT_LikeRating_2")).click();
						
						jse.executeScript("window.scrollBy(0,-500)", "");
						
						//****************** SRS tab ************************
						
						wd.findElement(By.id("ui-id-3")).click();
						Thread.sleep(500);
						//1. 	What is the device used for SRS treatment?
						Select drop2= new Select(wd.findElement(By.id("DC_Page_SRSV3_Q1_ddl")));
						drop2.selectByVisibleText("Cyberknife");
						
						//2. 	Was a Winston-Lutz test performed prior to the patient treatment?
						wd.findElement(By.id("DC_Page_SRSV3_Q2_rb_"+i)).click();
						
						//3. 	Were the physician and physicist present during imaging and treatment?
						wd.findElement(By.id("DC_Page_SRSV3_Q3_rb_"+i)).click();
						jse.executeScript("window.scrollBy(0,300)", "");
						//4. 	Which immobilization system was used for SRS treatment 
						Select drop3= new Select(wd.findElement(By.id("DC_Page_SRSV3_Q4_ddl")));
						drop3.selectByVisibleText("Neurosurgical frame");
						
						//5. 	When fusion is performed, did the radiation oncologist request an order?
						wd.findElement(By.id("DC_Page_SRSV3_Q5_rb_"+i)).click();
						
						//SRS Comments
						wd.findElement(By.id("DC_Page_SRSV3_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						
						
						//Rating Scale 
						wd.findElement(By.id("SRS_LikeRating_2")).click();
						
						jse.executeScript("window.scrollBy(0,-400)", "");
						
						//****************** Proton tab ************************
						
						wd.findElement(By.id("ui-id-4")).click();
						Thread.sleep(500);
						//1. 	What type of machine was used? 
						Select drop4= new Select(wd.findElement(By.id("DC_Page_ProtonV3_Q3_1_rb")));
						drop4.selectByVisibleText("Cyclotron");
						
						//2. 	Were patient specific devices such as aperture and compensators used?
						wd.findElement(By.id("DC_Page_ProtonV3_Q3_2_rb_"+i)).click();
						
						//3. 	Is there documentation that a physician and physicist were present during imaging and treatment?
						wd.findElement(By.id("DC_Page_ProtonV3_Q3_3_rb_"+i)).click();
						
						//4. 	Were calculations verified by an additional and independent method prior to treatment?
						wd.findElement(By.id("DC_Page_ProtonV3_Q7_2_rb_"+i)).click();
						
						//5. 	Was patient specific QA performed prior to treatment?
						wd.findElement(By.id("DC_Page_ProtonV3_Q7_3_rb_"+i)).click();
						
						//Proton Comments
						wd.findElement(By.id("DC_Page_ProtonV3_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						jse.executeScript("window.scrollBy(0,300)", "");
						
						//Rating Scale 
						wd.findElement(By.id("Proton_LikeRating_2")).click();
						
						jse.executeScript("window.scrollBy(0,-400)", "");
						
						//****************Brachytherapy tab******************
						
						wd.findElement(By.id("ui-id-5")).click();
						Thread.sleep(500);
						//1. 	Is there a written directive signed and dated by the radiation oncologist prior to treatment? 
						wd.findElement(By.id("DC_Page_Section4_q1_rb_"+i)).click();
						
						//2. 	Does it include an anatomic description of treatment area? 
						wd.findElement(By.id("DC_Page_Section4_q2_rb_"+i)).click();

						//3. 	What isotope was used for treatment? 
						Select drop5= new Select(wd.findElement(By.id("DC_Page_Section4_q3_ddl")));
						drop5.selectByVisibleText("I125");
						jse.executeScript("window.scrollBy(0,300)", "");
						//4. 	Was the patient identified as required by the NRC or state?
						wd.findElement(By.id("DC_Page_Section4_q4_rb_"+i)).click();
						
						//5. 	Is there evidence of a post-treatment radiation survey in the patient records?
						wd.findElement(By.id("DC_Page_Section4_q5_rb_"+i)).click();
						
						// 	Were calculations verified by an additional and independent method prior to treatment?
						wd.findElement(By.id("DC_Page_Section4_q6_rb_"+i)).click();
						
						//7. 	Type of Treatment 
						
						for(int j=0;j<=2;j++)
						{
							wd.findElement(By.id("DC_Page_Section4_B_ch_"+j)).click();
						}
						
						//**************** LDR tab******************
						
						wd.findElement(By.id("ui-id-7")).click();
						Thread.sleep(500);
						// 	Was some type of QA performed on the applicator(s) before treatment?  
						wd.findElement(By.id("DC_Page_LDRv3_Q1_rb_"+i)).click();
						
						// 	Was there some type of imaging device used to perform the placement of the application for the treatment 
						wd.findElement(By.id("DC_Page_LDRv3_Q2_rb_"+i)).click();
						jse.executeScript("window.scrollBy(0,300)", "");
						// 	Was the prescribed loading of applicators independently confirmed? 
						wd.findElement(By.id("DC_Page_LDRv3_Q3_rb_"+i)).click();
						
						String browser=prop.getProperty("Browser");
						  if(browser.equalsIgnoreCase("IE")){
						jse.executeScript("window.scrollBy(0,100)", "");
						  }
						//4. 	Were radiation safety precautions demonstrated?
						wd.findElement(By.id("DC_Page_LDRv3_Q4_rb_"+i)).click();
						
						//Was there documentation that the Radiation Oncologist and Medical Physicist were present during treatment? 
						wd.findElement(By.id("DC_Page_LDRv3_Q5_rb_"+i)).click();
						
						//LDR Comments
						wd.findElement(By.id("DC_Page_LDRV3_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						jse.executeScript("window.scrollBy(0,300)", "");
						
						//Rating Scale 
						wd.findElement(By.id("LDR_LikeRating_2")).click();
						
						jse.executeScript("window.scrollBy(0,-500)", "");
						
						//****************HDR  tab******************
						
						wd.findElement(By.id("ui-id-8")).click();
						Thread.sleep(500);
						//1. 	Was pre-treatment QA of unit performed?
						wd.findElement(By.id("DC_Page_HDRv3_Q1_rb_"+i)).click();
						
						//2. 	Is there evidence of a pre-treatment patient radiation survey in the patient records?
						wd.findElement(By.id("DC_Page_HDRv3_Q2_rb_"+i)).click();
						jse.executeScript("window.scrollBy(0,300)", "");
						//Was there documentation that correct applicator was verified before each treatment? (e.g. size or dimension) 
						wd.findElement(By.id("DC_Page_HDRv3_Q3_rb_"+i)).click();
						
						// 	Was there documentation that the Radiation Oncologist and Medical Physicist were present during treatment
						wd.findElement(By.id("DC_Page_HDRv3_Q4_rb_"+i)).click();
						
						//HDR Comments
						wd.findElement(By.id("DC_Page_HDRV3_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						jse.executeScript("window.scrollBy(0,300)", "");
						
						//Rating Scale 
						wd.findElement(By.id("HDR_LikeRating_2")).click();
						
						jse.executeScript("window.scrollBy(0,-500)", "");
						
						//**************** Seed Implant  tab******************
						
						wd.findElement(By.id("ui-id-9")).click();
						Thread.sleep(500);
						//1. 	Was a pre-planning Ultrasound performed for volume determination?
						wd.findElement(By.id("DC_Page_SeedImplantV3_Q1_rb_"+i)).click();
						
						//2. 	Was the seed calibration verified onsite? 
						wd.findElement(By.id("DC_Page_SeedImplantV3_Q2_rb_"+i)).click();
						jse.executeScript("window.scrollBy(0,300)", "");
						// 	A. Intended prescription dose
						wd.findElement(By.id("DC_Page_SeedImplantV3_QA_rb_"+i)).click();
						
						// 	B. Actual total dose (D90)
						wd.findElement(By.id("DC_Page_SeedImplantV3_QC_rb_"+i)).click();
						
						// 	Was there documentation that the Radiation Oncologist and Medical Physicist were present during treatment?
						wd.findElement(By.id("DC_Page_SeedImplantV3_Q4_rb_"+i)).click();
						jse.executeScript("window.scrollBy(0,300)", "");
						//Seed Implant Comments
						wd.findElement(By.id("DC_Page_SeedImplantV3_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						
						
						//Rating Scale 
						wd.findElement(By.id("SI_LikeRating_2")).click();
						
						jse.executeScript("window.scrollBy(0,-500)", "");
						
						//*********************2D/3D **********************
						
						wd.findElement(By.id("ui-id-6")).click();
						Thread.sleep(500);
						//2D/3D Comments 
						wd.findElement(By.id("DC_Page_TD3Dv3_Comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						jse.executeScript("window.scrollBy(0,900)", "");
						
						//Rating Scale 
						wd.findElement(By.id("TD3Dv3_LikeRating_2")).click();
						
						jse.executeScript("window.scrollBy(0,900)", "");
						
						wd.findElement(By.id("btn_ONP_btm_next")).click();//next button
						log.info("offline: End of Onsite interview forms");
						Thread.sleep(300);
				}
					Thread.sleep(500);
					jse.executeScript("window.scrollBy(0,900)", "");
					wd.findElement(By.id("btn_ONP_btm_next")).click();//dc next button
					Thread.sleep(300);
					jse.executeScript("window.scrollBy(0,-500)", "");
					Thread.sleep(500);
						wd.findElement(By.id("btn_sync_ON_P")).click();//onsite syn button
						Thread.sleep(300);
						log.info("onsite froms syn");
						Thread.sleep(500);
						
						wd.findElement(By.id("btn_sync_MDDC")).click();//dc syn button
						Thread.sleep(300);
						log.info("DC forms syn");

						Thread.sleep(5000);
						
						//opening url
						 wd.get(prop.getProperty("QA_URL"));
						// Login
						Ropa_pom.login(sh.getRow(1).getCell(0).getStringCellValue(), sh.getRow(1).getCell(1).getStringCellValue());
						log.info("Login as PHY");
						
						//Change Role:
						Select dropdown = new Select(wd.findElement(By.id("ddrROles")));
						dropdown.selectByVisibleText("Physicist Surveyor");
					
						Ropa_pom.view_assigned_Application("cphMaster_btnAppliAssgndtoMe");	
							
							//opening from left side
							wd.findElement(By.linkText("Physicist DC Forms")).click();
							
							wd.findElement(By.linkText("Physicist Data Collection")).click();
							Thread.sleep(300);
						
							wd.findElement(By.id("cphMaster_Next")).click(); //next
							
							Thread.sleep(300);
							
							//*********************Physician Final Report V3*********************
							log.info("Physician Final Report V3");
						
					   		//Has the facility complied with recommendations included in the last Survey Report (if applicable)? Please refer to prior survey report included in your documents. 
					   		wd.findElement(By.id("cphMaster_p1_q1_rbl_0")).click(); 
					   		// 	Comments: 
					   		wd.findElement(By.id("cphMaster_p1_q1_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					   		
					   		//Please provide any comments or recommendations that may help the facility to improve or enhance patient care but that do not impact their accreditation
					   		// 	Comments:
					   		wd.findElement(By.id("cphMaster_p1_q2_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					   		jse = (JavascriptExecutor)wd;
					   		jse.executeScript("window.scrollBy(0,300)", "");
					   		
					   		//3. Recommendation for accreditation: 
					   		//Rating Scale
					   		wd.findElement(By.id("p1_q2_rbl_2")).click(); //Compliant=3 
					   		
					   		// 	Please comment on your score:
					   		wd.findElement(By.id("cphMaster_p1_q4_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					   		jse.executeScript("window.scrollBy(0,900)", "");
					   		
					   		wd.findElement(By.id("cphMaster_btnNext")).click(); //next
					   		Thread.sleep(300);
					   		wd.findElement(By.id("cphMaster_Finish")).click(); //submit
					   		Thread.sleep(300);
					   		log.info("End of Physician Final Report V3");
							
					   	 Ropa_pom.logout();
						 log.info("Logout");


			}//method
			
}
