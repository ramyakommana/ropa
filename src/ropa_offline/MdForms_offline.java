package ropa_offline;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
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
import org.openqa.selenium.firefox.FirefoxProfile;
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
* Jun 1, 2017
* 11:30:40 AM
 */
public class MdForms_offline extends Ropa_pom {
	
	public static  int num;
	
	private static String downloadFilepath;
	
	  @Test(priority=7)
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
				
				downloadFilepath = prop.getProperty("downloadFilepath_md");

				  
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
				
			    // Login
			    wd.get(prop.getProperty("QA_URL"));
			    wd.manage().window().maximize();
  				wd.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
				Ropa_pom.login(sh.getRow(1).getCell(102).getStringCellValue(), sh.getRow(1).getCell(103).getStringCellValue());
				log.info("Login as MD");
				//Change Role:
				Select dropdown = new Select(wd.findElement(By.id("ddrROles")));
				dropdown.selectByVisibleText("MD Surveyor");
				wd.findElement(By.id("cphMaster_btnAppliAssgndtoMe")).click(); //app for survey

				//aplliaction name
			    String name=sh.getRow(1).getCell(60).getStringCellValue();
			    
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
				log.info("Downloading File");
				 Thread.sleep(5000);
				 wd.findElement(By.linkText("Logout")).click();
					Thread.sleep(5000);
					log.info("logout");
				 
				  }//main try
					catch(Exception e)
					{
					System.out.println(e);
					}
				}
	  
	  	@Test(priority=8)
				  public static void filezip (){
					  Unzipper ropa=new Unzipper();
					  ropa.zip();
					  
				  }
					
	
	  	@Test(priority=9)
		 public void mdforms() throws InterruptedException{
	  		
	  		//application name
		   String name=sh.getRow(1).getCell(60).getStringCellValue();
		
			 wd.get(prop.getProperty("md_unzipped_file")+"/OfflineFiles/"+name+"-MD/Index.html");
			 log.info("Opening Onsite Interview Forms from local drive");
				//opening Onsite Interview Forms from left side
			
				wd.findElement(By.linkText("Onsite Interview Pages")).click();
				Thread.sleep(500);
				//Onsite Interview Page 1
				
				wd.findElement(By.id("ON_P1")).click();
				wd.findElement(By.id("ON_P1")).click();
				
				Thread.sleep(500);
				//*********************************Onsite Interview Page*********************************** 
	
				Long site =Math.round(sh.getRow(1).getCell(149).getNumericCellValue());
				int i =site.intValue();
				//0=yes
				//1=Recommendation
				//2-no
				log.info("Offline: Onsite Interview Page1");
				
						//1. 	Do you offer your patients a psycho-social assessment 
					    wd.findElement(By.id("OP1_Q1_rb_"+i)).click();  //recommendation
						wd.findElement(By.id("OP1_1_txtcomments")).sendKeys("text");
						jse = (JavascriptExecutor)wd;
						jse.executeScript("window.scrollBy(0,400)", "");
						
						//2. 	Do you have a time out policy and procedure for simulation? 
						wd.findElement(By.id("OP1_Q2_rb_"+i)).click(); //recommendation
						
						//3.Do you use contrast? 
						
						wd.findElement(By.id("OP1_Q4_rb_0")).click(); //yes
						//Who screens the patient *
						wd.findElement(By.id("OP1_Q4_ch_"+i)).click(); //Radiation Therapist

						//What do you screen for?
						// i. GFR *
						wd.findElement(By.id("OP1_Q4Bi_rb_"+i)).click(); //yes
						//Allergies *
						wd.findElement(By.id("OP1_Q4Bii_rb_"+i)).click(); //recommendation
						// Creatinine *
						wd.findElement(By.id("OP1_Q4Biii_rb_"+i)).click(); //recommendation
						jse.executeScript("window.scrollBy(0,400)", "");
						//Contrast within 24 plus hours*
						wd.findElement(By.id("OP1_Q4Biv_rb_"+i)).click(); //yes
						
						
						//4.Is there a formal written simulation order used in the department for the following: 
						
						// IV contrast   (Surveyor to confirm on Checklist Section) 
						wd.findElement(By.id("OP1_Q3A_rb_"+i)).click(); //yes
						//. Oral contrast   (Surveyor to confirm on Checklist Section) 
						wd.findElement(By.id("OP1_Q3B_rb_"+i)).click(); //recommendation
						// Treatment site 
						wd.findElement(By.id("OP1_Q3C_rb_"+i)).click(); //recommendation
						//Patient position 
						wd.findElement(By.id("OP1_Q3D_rb_"+i)).click(); //recommendation
						//Immobilization device 
						wd.findElement(By.id("OP1_Q3E_rb_"+i)).click(); //doesnot apply
						
						jse.executeScript("window.scrollBy(0,500)", "");
						//Simulation Comments: 
						wd.findElement(By.id("OP1_txtcomments")).sendKeys(sh.getRow(1).getCell(122).getStringCellValue());
						
						wd.findElement(By.id("btn_ONP_btm_next")).click();//next
						Thread.sleep(300);
						log.info("offline: End of Onsite Interview Page1");
						
					    //*******************************Onsite Interview Page 2****************************
						
						log.info("offline: Onsite Interview Page2");
						//wd.findElement(By.linkText("Onsite Interview Page 2")).click();
						jse.executeScript("window.scrollBy(0,-1500)", "");
						//Once the simulation is completed, does the radiation oncologist draw the target, GTV, and CTV? 
						wd.findElement(By.id("OP2_Q6_rb_"+i)).click();//yes
						
						//Does the radiation oncologist review the Organs At Risk (OAR) if someone else contours them? 
						wd.findElement(By.id("OP2_Q7_rb_"+i)).click();//recommendation
						jse.executeScript("window.scrollBy(0,300)", "");
						//For IMRT patients, is there a written order for dose volume constraints prescribed by the Radiation Oncologist?
						wd.findElement(By.id("OP2_Q8_rb_"+i)).click();//recommendation
						
						// 	Is there a formal Physician peer review of dose volume constraints? 
						wd.findElement(By.id("OP2_Q9A_rb_"+i)).click();//recommendation
						
						//Is there a formal Physician peer review of target volumes and organs at risk (segmentation)? 
						wd.findElement(By.id("OP2_Q9B_rb_"+i)).click();//yes
						
						//Does the Radiation Oncologist review and approve the treatment plan prior to first delivery? 
						wd.findElement(By.id("OP2_Q13_rb_"+i)).click();//does not apply

						// 	Does the radiation oncologist sign/date the prescription before the first treatment? 
						wd.findElement(By.id("OP2_Q14_rb_"+i)).click(); //yes
						
						// 	Is a radiation oncologist within the radiation oncology department during treatment? 
						wd.findElement(By.id("OP2_Q12_rb_"+i)).click(); //recommendation
						jse.executeScript("window.scrollBy(0,400)", "");
						
						//Treatment Planning Comments:
						wd.findElement(By.id("OP2_1_txtcomments")).sendKeys(sh.getRow(1).getCell(131).getStringCellValue());
						
						//Patient Treatment
						//A. Do you have a policy that requires you to perform a time out prior to each treatment?
					      wd.findElement(By.id("OP2_Q16A_rb_"+i)).click();
					      
					      // If yes, do you check for correct patient, correct site, correct energy and accessories (if any)
					      wd.findElement(By.id("OP2_Q16B_rb_"+i)).click();
					      
					      // Is it documented? 
					      wd.findElement(By.id("OP2_Q16C_rb_"+i)).click();
  
					      //Do you have a policy for emergency treatment of patients after business hours? 
					      wd.findElement(By.id("OP2_Q17_rb_"+i)).click();
					      jse.executeScript("window.scrollBy(0,500)", "");
						//Patient Treatment Comments:
					      wd.findElement(By.id("OP2_txtcomments")).sendKeys(sh.getRow(1).getCell(132).getStringCellValue());
					
							wd.findElement(By.id("btn_ONP_btm_next")).click();//next
							log.info("offline: End of Onsite Interview Page2");
							Thread.sleep(300);
							 //*****************************Onsite Interview Page 3********************************
							
							log.info("offline: Onsite Interview Page3");
							jse.executeScript("window.scrollBy(0,-1500)", "");
						    //Is there a policy for obtaining patient imaging? 
							 wd.findElement(By.id("OP3_Q18_rb_"+i)).click();
							 
							 //	Do you have a policy for shift changes? 
							 wd.findElement(By.id("OP3_Q19_rb_"+i)).click();
							 jse.executeScript("window.scrollBy(0,400)", "");
						    //Portal Imaging Comments :
							 wd.findElement(By.id("OP3_1_txtcomments")).sendKeys(sh.getRow(1).getCell(133).getStringCellValue());
							
							 //Do you perform or receive documentation of patient follow up care? 
							 wd.findElement(By.id("OP3_Q20_rb_"+i)).click();
							 
							 //Follow Up Policy Comments:
							 wd.findElement(By.id("OP3_2_txtcomments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					          
							 String a="OP3_Q21_rb_"+i;
							 String b="OP3_Q21_rb_0";
							 String c="OP3_Q21_rb_1";
							 
					 if(a.equals(b))
					 {
							 //A. Do you have chart rounds? 
							 wd.findElement(By.id("OP3_Q21A_rb_"+i)).click();
							
							 //Who attends chart rounds
							 wd.findElement(By.id("OP3_Q21B_ch_"+i)).click();
					
							 //C. What is presented at chart rounds ?
							 wd.findElement(By.id("OP3_Q21C_ch_"+i)).click();
					
					  }
					 else
					   {
						if(a.equals(c))
						       {
								//A. Do you have chart rounds? 
								 wd.findElement(By.id("OP3_Q21A_rb_"+i)).click();
								
								 //Who attends chart rounds
								 wd.findElement(By.id("OP3_Q21B_ch_"+i)).click();
					
								 //C. What is presented at chart rounds ?
								 wd.findElement(By.id("OP3_Q21C_ch_"+i)).click(); 
							    }
						  else
							  {
							 
							//A. Do you have chart rounds? 
							 wd.findElement(By.id("OP3_Q21A_rb_"+i)).click();
							 
							   }
					
					    }
					
					 String e="OP3_Q22A_rb_0";
					 String f="OP3_Q22A_rb_"+i;
					 String g="OP3_Q22A_rb_1";
					 
					 if(e.equals(f)||g.equals(f))
					 {
						 //	A. Do you have periodic Quality Assurance (QA) or Continous Quality Improvement (CQI) meetings
						 wd.findElement(By.id("OP3_Q22A_rb_"+i)).click();
						 
						//B. Who attends these meetings? 
						 wd.findElement(By.id("OP3_Q22B_ch_"+i)).click();
					 }	 
					 else
						    {
							// A. Do you have periodic Quality Assurance (QA) or Continous Quality Improvement (CQI) meetings
							 wd.findElement(By.id("OP3_Q22A_rb_"+i)).click(); 
						    }
					 
					 jse.executeScript("window.scrollBy(0,300)", "");
					 String h="OP3_Q23A_rb_0";
					 String j="OP3_Q23A_rb_"+i;
					 String k1="OP3_Q23A_rb_1";
					 
					 if(j.equals(h))
					 {
					// Do you have Morbidity and Mortality (M&M) conferences? 
					 wd.findElement(By.id("OP3_Q23A_rb_"+i)).click();
					 
					 //Who attends these meetings? 
					 wd.findElement(By.id("OP3_Q23B_ch_"+i)).click();
					 
					 //C. Please describe the screening criteria to capture the patients for M&M: 
					 wd.findElement(By.id("OP3_Q23C_ch_"+i)).click();
					 
					 // Who screens for these criteria?
					 wd.findElement(By.id("OP3_Q23D_ch_"+i)).click(); 
					 }
					 else
					 {
						 if(j.equals(k1))
						 {
						// Do you have Morbidity and Mortality (M&M) conferences? 
						 wd.findElement(By.id("OP3_Q23A_rb_"+i)).click();
						 
						 //Who attends these meetings? 
						 wd.findElement(By.id("OP3_Q23B_ch_"+i)).click();
						 jse.executeScript("window.scrollBy(0,200)", "");
						 //C. Please describe the screening criteria to capture the patients for M&M: 
						 wd.findElement(By.id("OP3_Q23C_ch_"+i)).click();
						
						 // Who screens for these criteria?
						 wd.findElement(By.id("OP3_Q23D_ch_"+i)).click(); 
						 } 
						 else
						 {
							// Do you have Morbidity and Mortality (M&M) conferences? 
							 wd.findElement(By.id("OP3_Q23A_rb_"+i)).click(); 
						 }
					 }
					 jse.executeScript("window.scrollBy(0,500)", "");
					 //Do you perform Facility Qualtiy Improvement (FQI or Focus Studies)? 
					 wd.findElement(By.id("OP3_Q24A_rb_"+i)).click(); 
					 
					 //Do you perform outcome studies? 
					 wd.findElement(By.id("OP3_Q25A_rb_"+i)).click(); 
					 
					// QA Activities Comments: 
					 wd.findElement(By.id("OP3_txtcomments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					 
					 wd.findElement(By.id("btn_ONP_btm_next")).click();//next
					 log.info("offline: End of Onsite Interview Page3");
					 Thread.sleep(300);
					 //******************************Onsite Interview Page 4*************************
					 
					 log.info("offline: Onsite Interview Page4");
					 jse.executeScript("window.scrollBy(0,-1500)", "");
					 //Do you participate in protocols?
					 wd.findElement(By.id("OP4_Q26_rb_0")).click();
					 
					 // If yes, Select the following:
					 wd.findElement(By.id("OP4_Q26_ch_"+i)).click();
					 jse.executeScript("window.scrollBy(0,400)", "");
					 //Divisional Policies Comments: 
					 wd.findElement(By.id("OP4_1_txtcomments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					 
					 // Is training and competence assessed at the time of employment? 
					 wd.findElement(By.id("OP4_Q27_rb_"+i)).click();
					 
					 //Is training and competence assessed when a new technology is introduced? 
					 wd.findElement(By.id("OP4_Q28_rb_"+i)).click();
					 
					 //How are new procedures/new equipment introduced and implemented into the department? 
					 wd.findElement(By.id("OP4_Q29_ch_"+i)).click();
					 
					 // Is there Annual Radiation Safety Training? 
					 wd.findElement(By.id("OP4_Q29A_rb_"+i)).click();
					 
					 //Is there an Infection Control Policy? 
					 wd.findElement(By.id("OP4_Q29B_rb_"+i)).click();
					 jse.executeScript("window.scrollBy(0,500)", "");
					 //Is there a Disaster Plan? 
					 wd.findElement(By.id("OP4_Q30_rb_"+i)).click();
					 
					 //Training and Competence of Staff Comments: 
					 wd.findElement(By.id("OP4_txtcomments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					 
					 wd.findElement(By.id("btn_ONP_btm_next")).click();//next
					 Thread.sleep(500);
					 log.info("offline: End of Onsite Interview Page4");
					 jse.executeScript("window.scrollBy(0,-500)", "");
					 
					 wd.findElement(By.linkText("MD Data Collection Forms")).click();			
					 Thread.sleep(300);
					 
				//*************************MD Data Collection*************************
					List<WebElement> count=wd.findElements(By.name("btn_Dc_Cases_view"));
				num=count.size();
					
			    	//***loop for multiple views***
					for(int v=1;v<=num;v++)
					{
						log.info("offline: MD Data Collection" +v);
						jse.executeScript("window.scrollBy(0,-500)", "");
						Thread.sleep(1000);
						 wd.findElement(By.linkText("MD Data Collection")).click();
						Thread.sleep(1000);
						wd.findElement(By.id("btn_Dc_Cases_view"+v)).click();
						Thread.sleep(5000);
				//Treating Physician:
				wd.findElement(By.id("DC_Page_TreatingPhysician_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				
				//***********************History and Physical/Consultation********************************
				wd.findElement(By.id("ui-accordion-div_Page_DC-header-0")).click();
				

				if(v!=1){
					
					wd.findElement(By.id("ui-accordion-div_Page_DC-header-0")).click();
					
				}
				
				jse.executeScript("window.scrollBy(0,400)", "");
				
int r=1; //for radio button
				
				String radio="DC_Page_Section1_Modalities_rb_"+r;
				String Breast="DC_Page_Section1_Modalities_rb_0"; 
				String Prostate="DC_Page_Section1_Modalities_rb_1";
				String Lung="DC_Page_Section1_Modalities_rb_2";
				String Rectosigmoid="DC_Page_Section1_Modalities_rb_3";
				String Head_Neck="DC_Page_Section1_Modalities_rb_4";
				String Other="DC_Page_Section1_Modalities_rb_5";
				
if(r==0)
{
					//breast radio button
					wd.findElement(By.id("DC_Page_Section1_Modalities_rb_0")).click();
					
					Select d = new Select(wd.findElement(By.id("DC_Page_Breast_1_ddl")));
					d.selectByVisibleText("Definitive - Intact Breast");
					
					// 	The tumor final margin status
					wd.findElement(By.id("DC_Page_Breast_2A_rb_"+i)).click();
					
					//The estrogen receptor, progesterone receptor and Her2-new status
					wd.findElement(By.id("DC_Page_Breast_2B_rb_"+i)).click();
					
					//Tumor Size 
					wd.findElement(By.id("DC_Page_Breast_2C_rb_"+i)).click();
					
					// 	Use or non-use of oral contraceptives and/or hormone replacement therapy 
					wd.findElement(By.id("DC_Page_Breast_2D_rb_"+i)).click();
					
					//Findings of a breast examination by the Radiation Oncologist 
					wd.findElement(By.id("DC_Page_Breast_2E_rb_"+i)).click();
					
					// 	Other 
					wd.findElement(By.id("DC_Page_Breast_2F_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				if(i==1){
					//Breast Comments:
					wd.findElement(By.id("Breast_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());	
				}
} //breast
else
{
   if(r==1)
		{
						//Prostate radio button
						wd.findElement(By.id("DC_Page_Section1_Modalities_rb_1")).click();
						
						//Pre-Treatment Status:
						Select d = new Select(wd.findElement(By.id("DC_Page_Prostate_1_ddl")));
						d.selectByVisibleText("Prostatectomy-Adjuvant");
						
						//Pre-treatment PSA 
						wd.findElement(By.id("DC_Page_Prostate_2A_rb_"+i)).click();
						
						//Pre-treatment erectile status
						wd.findElement(By.id("DC_Page_Prostate_2B_rb_"+i)).click();
						
						//The number and extent of positive cores (only for Definitive)
						wd.findElement(By.id("DC_Page_Prostate_2C_rb_"+i)).click();
						
						//Digital rectal exam 
						wd.findElement(By.id("DC_Page_Prostate_2D_rb_"+i)).click();
						
						//Gleason Score 
						wd.findElement(By.id("DC_Page_Prostate_2E_rb_"+i)).click();
						
						//Urinary Status
						wd.findElement(By.id("DC_Page_Prostate_2F_rb_"+i)).click();
						jse.executeScript("window.scrollBy(0,300)", "");
						
						// 	Was the pathology report reviewed
						wd.findElement(By.id("DC_Page_Prostate_3A_rb_"+i)).click();
						
						// 	Margins
						wd.findElement(By.id("DC_Page_Prostate_3B_i_rb_"+i)).click();
						
						// 	Capsular extensions
						wd.findElement(By.id("DC_Page_Prostate_3B_ii_rb_"+i)).click();
						
						//Seminal vesicles extensions 
						wd.findElement(By.id("DC_Page_Prostate_3B_iii_rb_"+i)).click();
						jse.executeScript("window.scrollBy(0,100)", "");
						
						//Prostate Comments:
						wd.findElement(By.id("Prostate_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());		
       } //Prostate
		 else
			{
				if(r==2)
					{
					//lung radio button
					wd.findElement(By.id("DC_Page_Section1_Modalities_rb_2")).click();
					
					//Pre-Treatment Status:
					Select d = new Select(wd.findElement(By.id("DC_Page_Lung_1_rb")));
					d.selectByVisibleText("Primary");
					
					//Did the Radiation Oncologist make note of the patient's pulmonary function results?
					wd.findElement(By.id("DC_Page_Lung_q2_rb_"+i)).click();
					
					// 	Did the Radiation Oncologist make note of the patient's smoking history?
					wd.findElement(By.id("DC_Page_Lung_q3_rb_"+i)).click();
					
					//Lung Comments:
					wd.findElement(By.id("Lung_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());		
					} //lung
				
				else
				 {
						if(r==3)
						  {
						// Rectosigmoid radio button
						wd.findElement(By.id("DC_Page_Section1_Modalities_rb_3")).click();
							
						// 	Pre-Treatment Status:
						Select d = new Select(wd.findElement(By.id("DC_Page_Rectosigmoid_1_rb")));
						d.selectByVisibleText("Primary");
							
						//Were any of the following tests (endorectal ultrasound, MRI, PET/CT Scans, or digital 
						wd.findElement(By.id("DC_Page_Rectosigmoid_2_rb_"+i)).click();
						
						//Was a bowel sparing technique used, which could include, belly board, abdominal 
						wd.findElement(By.id("DC_Page_Rectosigmoid_3_rb_"+i)).click();
						
						//Rectosigmoid Comments:
						wd.findElement(By.id("Rectosigmoid_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());			
						
						  }//Rectosigmoid
						
						 else
						  {
							 if(r==4)
							 {
							//Head_Neck radio button
							wd.findElement(By.id("DC_Page_Section1_Modalities_rb_4")).click();  
								 
							// 	Pre-Treatment Status
							Select d = new Select(wd.findElement(By.id("DC_Page_HEADNECK_1_rb")));
							d.selectByVisibleText("Primary");
							
							//Name of the site
							Select s = new Select(wd.findElement(By.id("DC_Page_HEADNECK_2_rb_ddl")));
							s.selectByVisibleText("Hypopharynx");
							
							//Did the Radiation Oncologist make note of the smoking history?
							wd.findElement(By.id("DC_Page_HEADNECK_3_rb_"+i)).click();
							
							//Did the Radiation Oncologist make note of alcohol history?
							wd.findElement(By.id("DC_Page_HEADNECK_4_rb_"+i)).click();
							
							//Did the Radiation Oncologist perform an indirect exam or fiberoptic endoscopy?
							wd.findElement(By.id("DC_Page_HEADNECK_5_rb_"+i)).click();
							
							//Head & Neck Comments:
							wd.findElement(By.id("HeadNeck_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());			
								 
							 }//Head_Neck
							  else
							   {
								 //others
								//other radio button
								wd.findElement(By.id("DC_Page_Section1_Modalities_rb_5")).click();
								
								//Site:
								wd.findElement(By.id("DC_Page_Other_1_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());	
								
								// 	Comments: 
								wd.findElement(By.id("DC_Page_Other_2_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());	
								  
							   }
						  }
				  }
				
			} // 2nd else close	
} //main else

								// 	Is there an appropriate pathology report available for review and referenced in the H&P? 
								wd.findElement(By.id("DC_Page_Section1_q1_rb_"+i)).click();
								jse.executeScript("window.scrollBy(0,400)", "");
								
								//What is the histology of the cancer?
								Select s = new Select(wd.findElement(By.id("DC_Page_Section1_q2_ddl")));
								s.selectByVisibleText("Adenocarcinoma");
								
								//Was TNM documented?
								wd.findElement(By.id("DC_Page_Section1_q3_A_rb_"+i)).click();
								
								String q="DC_Page_Section1_q3_rb_0";
								
								 if(q.equals("DC_Page_Section1_q3_rb_"+i))
								 {
									 // T : 
									 Select t = new Select(wd.findElement(By.id("DC_Page_Section1_q3_2_ddl")));
									 t.selectByVisibleText("TX");
									// N :	
									 Select n = new Select(wd.findElement(By.id("DC_Page_Section1_q3_3_ddl")));
									 n.selectByVisibleText("2");	
									// M :
									 Select m = new Select(wd.findElement(By.id("DC_Page_Section1_q3_4_ddl")));
									 m.selectByVisibleText("1");	
								 }
								 
								//Was Staging document?
								 wd.findElement(By.id("DC_Page_Section1_q3_B_rb_"+i)).click();
								 
								 String sd="DC_Page_Section1_q3_B_rb_0";
								 if(sd.equals("DC_Page_Section1_q3_B_rb_"+i))
								 {
									 //Stage 
									 Select st = new Select(wd.findElement(By.id("DC_Page_Section1_q3_1_ddl")));
									 st.selectByVisibleText("III");	 
								 }
								 jse.executeScript("window.scrollBy(0,400)", "");
							  //Were risk factors for the diagnosis contained in the radiation oncologist’s history and physical examination? 
								 wd.findElement(By.id("DC_Page_Section1_q4_1_rb_"+i)).click(); 
								 
								//Was an appropriate examination for the diagnosis contained in the radiation oncologist’s history and physical examination? 
								 wd.findElement(By.id("DC_Page_Section1_q4_2_rb_"+i)).click(); 
								 
								 //Was performance status contained in the radiation oncologist’s history and physical examination?
								 wd.findElement(By.id("DC_Page_Section1_q4_3_rb_"+i)).click(); 
								 
								 //Was systemic therapy used/documented (Select Does not apply if systemic therapy did not pertain to patient’s treatment course) 
								 wd.findElement(By.id("DC_Page_Section1_q5_rb_"+i)).click(); 
						
								 String systemic="DC_Page_Section1_q5_rb_0";
								 if(systemic.equals("DC_Page_Section1_q5_rb_"+i))
								 {
									 //If yes, Agent Name Included?
									 wd.findElement(By.id("DC_Page_Section1_q5_A_rb_"+i)).click(); 
									 
									 //Schedule?
									 wd.findElement(By.id("DC_Page_Section1_q5_B_rb_"+i)).click();
								 }
								 
								 //Is there evidence of an informed consent process with a signed document and evidence of review of side effects, complications, benefits, and alternatives with the patient?
								 wd.findElement(By.id("DC_Page_Section1_q6_rb_"+i)).click();
								 jse.executeScript("window.scrollBy(0,400)", "");
								 
								 //History Physical / Consultative Comments:
								 wd.findElement(By.id("Section1_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());

								 //Rating Scale
								 wd.findElement(By.id("Section1_LikeRating_2")).click();
								 
			//********************************Treatment Section*******************************************
								 wd.findElement(By.id("ui-accordion-div_Page_DC-header-1")).click();
								 Thread.sleep(300);
								 
								 if(v!=1){
										
									 wd.findElement(By.id("ui-accordion-div_Page_DC-header-1")).click();
									 Thread.sleep(300);	
									}
								 jse.executeScript("window.scrollBy(0,400)", "");
									
								 //***************2D/3D tab click*****************************
								 Thread.sleep(300);
								 for(int chk=0;chk<=8;chk++)
							   		{
							   		// Site/Target
							   		 wd.findElement(By.id("DC_P2_Ch_"+chk)).click();
							   		}
							
								 //***************2D/3D tab click*****************************
								 wd.findElement(By.id("ui-id-7")).click();
								 
								 //Date of first treatment
								 wd.findElement(By.id("DC_Page_U2D3DV3_1_date")).click();//calendar

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
						   	   
						   		jse.executeScript("window.scrollBy(0,300)", "");
						   		
						   		for(char l='A';l<='H';l++)
						   		{
						   		// Site/Target
						   		 wd.findElement(By.id("DC_Page_U2D3DV3_1"+l+"_rb_"+i)).click();
						   		}
						   		jse.executeScript("window.scrollBy(0,300)", "");
						   		
						   		//Was the prescription signed and dated by radiation oncologist prior to first treatment?
						   		wd.findElement(By.id("DC_Page_U2D3DV3_4_rb_"+i)).click();
						   		
						   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
						   		wd.findElement(By.id("DC_Page_U2D3DV3_6_rb_"+i)).click();
						   		
						   		//Dose(Gy)/Fraction
						   		wd.findElement(By.id("DC_Page_U2D3DV3_Q5_A_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
						   		   
								//Number of Fractions
						   		wd.findElement(By.id("DC_Page_U2D3DV3_Q5_B_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
						   		
						   		//Energy
						   		wd.findElement(By.id("DC_Page_U2D3DV3_Q5_C_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
						   		wd.findElement(By.id("DC_Page_U2D3DV3_Q5_D_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
						   		//Total Dose(Gy)
						   		wd.findElement(By.id("DC_Page_U2D3DV3_Q5_E_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
						   		
						   		// 	Were the portal verification images obtained at the time of or immediately prior to the first treatment? *
						   		wd.findElement(By.id("DC_Page_U2D3DV3_7A_rb_"+i)).click();
						   		jse.executeScript("window.scrollBy(0,400)", "");
						   		
						   		//Were the portal verification images obtained when the fields were changed (e.g. at cone down/boost)
						   		wd.findElement(By.id("DC_Page_U2D3DV3_7B_rb_"+i)).click();
								 
						   		// 	Was portal verification obtained at least every 5-10 treatments?
						   		wd.findElement(By.id("DC_Page_U2D3DV3_7C_rb_"+i)).click();
						   		
						   		//Were portal verification images signed/approved/rejected/dated by radiation oncologist with timing consistent (example: before next treatment)?
						   		wd.findElement(By.id("DC_Page_U2D3DV3_7D_rb_"+i)).click();
						   		
						   		//Were electron fields documented with a photograph or portal image?
						   		wd.findElement(By.id("DC_Page_U2D3DV3_7E_rb_"+i)).click();
						   		
						   		//2D3D Comments:
						   		wd.findElement(By.id("TD3D_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						   	 jse.executeScript("window.scrollBy(0,150)", "");
						   		//Rating Scale
						   		wd.findElement(By.id("TD3D_LikeRating_2")).click(); //Compliant=3
						   		
						   		jse.executeScript("window.scrollBy(0,-1500)", "");
						   		
						   	 //***********IMRT tab click************************************
							 wd.findElement(By.id("ui-id-8")).click();
							 jse.executeScript("window.scrollBy(0,400)", "");
							 //Was there a written planning instructions/dose volume constraints?
							 wd.findElement(By.id("DC_Page_IMRTV3_1_A_rb_"+i)).click();
							
							 
							 //Did the radiation oncologist review and approve multi-slice, coronal, and sagittal images with isodose lines and DVHs?
							 wd.findElement(By.id("DC_Page_IMRTV3_1_C_rb_"+i)).click();
							 
							 //A. For prostate IMRT, mark the important structures that were contoured:
							 wd.findElement(By.id("DC_Page_IMRTV3_2A_ch")).click();
							 
							 wd.findElement(By.id("DC_Page_IMRTV3_2A_i_ch")).click();//Bladder
							 
							 //Comments:
							 wd.findElement(By.id("DC_Page_IMRTV3_2A_i_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							 
							 //B.For lung cancers, mark the important structures that were contoured: 
							 wd.findElement(By.id("DC_Page_IMRTV3_2B_ch")).click();
							 jse.executeScript("window.scrollBy(0,400)", "");
							 wd.findElement(By.id("DC_Page_IMRTV3_2B_i_ch")).click();//Brachial Plexus
							
							//Comments:
							 wd.findElement(By.id("DC_Page_IMRTV3_2B_i_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							 
							 //C. For head and neck IMRT: mark the important structures that are contoured:
							 wd.findElement(By.id("DC_Page_IMRTV3_2C_ch")).click();
							 
							 wd.findElement(By.id("DC_Page_IMRTV3_2C_i_ch")).click();//i. Brachial Plexus 
							 wd.findElement(By.id("DC_Page_IMRTV3_2C_viii_ch")).click();//viii. Optic chiasm
							 
							//Comments:
							 wd.findElement(By.id("DC_Page_IMRTV3_2C_i_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							 
							// D. Other:  
							 wd.findElement(By.id("DC_Page_IMRTV3_2D_ch")).click();
							 
							//Comments:
							 wd.findElement(By.id("DC_Page_IMRTV3_2D_i_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							
							 //Date of first treatment
							 wd.findElement(By.id("DC_Page_IMRTV3_2C_1_date")).click();//calendar
							 WebElement date = wd.findElement(By.id("ui-datepicker-div"));
					   		   
					   		   List<WebElement> row=datenum.findElements(By.tagName("tr"));
					   		   List<WebElement> column=datenum.findElements(By.tagName("td"));
					   		
					   		   for (WebElement cell: column)
					   		   {
					   		
					   		    	   //Select Date 
					   		    if (cell.getText().equals(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue()))))
					   		    	  {
					   		    		 
					   		    	 cell.findElement(By.linkText(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue())))).click();
					   		    	  break;
					   		    	 }
					   		   } 
					   		Thread.sleep(1500);
					   	 jse.executeScript("window.scrollBy(0,300)", "");
					   		for(char l='A';l<='H';l++)
					   		{
					   		// Site/Target
					   		 wd.findElement(By.id("DC_Page_IMRTV3_2C_"+l+"_rb_"+i)).click();
					   		}
					   		jse.executeScript("window.scrollBy(0,400)", "");
					   	
					   		//Was the prescription signed and dated by radiation oncologist prior to first treatment? 
					   		wd.findElement(By.id("DC_Page_IMRTV3_4_rb_"+i)).click();
					   		
					   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
					   		wd.findElement(By.id("DC_Page_IMRTV3_6_rb_"+i)).click();
					   		
					     	//Dose(Gy)/Fraction
					   		wd.findElement(By.id("DC_Page_IMRTV3_Q5_A_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
					   		wd.findElement(By.id("DC_Page_IMRTV3_Q5_B_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
					   		   
							//Number of Fractions
					   		wd.findElement(By.id("DC_Page_IMRTV3_Q5_C_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
					   		
					   		//Energy
					   		wd.findElement(By.id("DC_Page_IMRTV3_Q5_D_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
					   		
					   		//Total Dose(Gy)
					   		wd.findElement(By.id("DC_Page_IMRTV3_Q5_E_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
					   		
					   		//A. Were the portal verification images obtained at the time of or immediately prior to the first treatment? 
					   		wd.findElement(By.id("DC_Page_IMRTV3_6_A_rb_"+i)).click();
					   		jse.executeScript("window.scrollBy(0,400)", "");
					   		
					   		//Were the portal verification images obtained when the fields were changed (e.g. at cone down/boost) 
					   		wd.findElement(By.id("DC_Page_IMRTV3_6_B_rb_"+i)).click();
					   		
					   		//Was portal verification obtained at least every 5-10 treatments?
					   		wd.findElement(By.id("DC_Page_IMRTV3_6_C_rb_"+i)).click();
					   		
					   		//Were portal verification images signed/approved/rejected/dated by radiation oncologist with timing consistent (example: before next treatment)?
					   		wd.findElement(By.id("DC_Page_IMRTV3_6_D_rb_"+i)).click();
					   		
					   		//Were electron fields documented with a photograph or portal image?
					   		wd.findElement(By.id("DC_Page_IMRTV3_6_E_rb_"+i)).click();
					   		
					   		//IMRT Comments:
					   		wd.findElement(By.id("IMRT_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					   		
					   		//Rating Scale
					   		wd.findElement(By.id("IMRT_LikeRating_2")).click(); //Compliant=3
					   		
					   		jse.executeScript("window.scrollBy(0,-1700)", "");
					   		
					   			//***********IGRT tab click************************************
							 wd.findElement(By.id("ui-id-9")).click();
							 
						    //Did the Radiation Oncologist document patient specific instructions for image guidance in the chart? 
							 wd.findElement(By.id("DC_Page_IGRT_Q1_rb_"+i)).click();
							 
							 //Were the IGRT images approved, signed and dated before the next treatment?
							 wd.findElement(By.id("DC_Page_IGRT_Q2_rb_"+i)).click();
							 jse.executeScript("window.scrollBy(0,300)", "");
							 //IGRT Comments:
							 wd.findElement(By.id("IGRT_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							 
							 //Rating Scale
							 wd.findElement(By.id("IGRT_LikeRating_2")).click(); //Compliant=3
							 
							 jse.executeScript("window.scrollBy(0,-400)", "");
							 
								//***********SBRT  tab click************************************
							 wd.findElement(By.id("ui-id-10")).click();
							 jse.executeScript("window.scrollBy(0,400)", "");
							 //Was there a written planning instructions/dose volume constraints? 
							 wd.findElement(By.id("DC_Page_SBRTV3_1_A_rb_"+i)).click();
							 
							 //Did the radiation oncologist review and approve multi-slice, coronal, and sagittal images with isodose lines and DVHs?
							 wd.findElement(By.id("DC_Page_SBRTV3_1_B_rb_"+i)).click();
							 Thread.sleep(300);
							 String browser=prop.getProperty("Browser");
							  if(browser.equalsIgnoreCase("IE")){
							jse.executeScript("window.scrollBy(0,100)", "");
							  }
							 
							 //***********SBRT Lung Stereotactic Body Radiation Therapy check***********
							 wd.findElement(By.id("DC_Page_SBRTV3_A_ch")).click();
							 
							 //Did the target volume receive greater than 95% of the prescribed dose? 
							 wd.findElement(By.id("DC_Page_SBRTV3_A_i_rb_"+i)).click();
							 
							 //Was the lesion treated peripheral or central in location? 
							 wd.findElement(By.id("DC_Page_SBRTV3_A_ii_rb_0")).click();
							 
							 //Was respiratory motion accounted for using gating or by creating an ITV?
							 wd.findElement(By.id("DC_Page_SBRTV3_A_iii_rb_"+i)).click();
							 
							 //Date of first treatment
							 wd.findElement(By.id("DC_Page_SBRTV3_A_1_date")).click();//calendar
							 WebElement day = wd.findElement(By.id("ui-datepicker-div"));
					   		   
					   		   List<WebElement> rows=datenum.findElements(By.tagName("tr"));
					   		   List<WebElement> columns=datenum.findElements(By.tagName("td"));
					   		
					   		   for (WebElement cell: columns)
					   		   {
					   		
					   		    	   //Select Date 
					   		    if (cell.getText().equals(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue()))))
					   		    	  {
					   		    		 
					   		    	 cell.findElement(By.linkText(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue())))).click();
					   		    	  break;
					   		    	 }
					   		   } 
					   		Thread.sleep(1500);
					   		jse.executeScript("window.scrollBy(0,400)", "");
					   		
					   	   //*******iteration for Did the prescription include the following elements:*******
					   		for(char rb='A';rb<='H';rb++)
					   		{
					   		
					   		// Site/Target
					   		 wd.findElement(By.id("DC_Page_SBRTV3_A_"+rb+"_rb_"+i)).click();
					   		
					   		}
					   		jse.executeScript("window.scrollBy(0,400)", "");
					   		
					   		//Was the prescription signed and dated by radiation oncologist prior to first treatment? 
					   		wd.findElement(By.id("DC_Page_SBRTV3_A_4_rb_"+i)).click();
					   		
					   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
					   		wd.findElement(By.id("DC_Page_SBRTV3_A_6_rb_"+i)).click();
					   		
					     	//Dose(Gy)/Fraction
					   		wd.findElement(By.id("DC_Page_SBRTV3A_Q5_A_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
					   		wd.findElement(By.id("DC_Page_SBRTV3A_Q5_B_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
					   		   
							//Number of Fractions
					   		wd.findElement(By.id("DC_Page_SBRTV3A_Q5_C_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
					   		
					   		//Energy
					   		wd.findElement(By.id("DC_Page_SBRTV3A_Q5_D_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
					   		
					   		//Total Dose(Gy)
					   		wd.findElement(By.id("DC_Page_SBRTV3A_Q5_E_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
					   		
					   		//***********SBRT Spine Stereotactic Body Radiation Therapy***********
							 wd.findElement(By.id("DC_Page_SBRTV3_B_ch")).click();
							
							 //Did the target volume receive greater than 90% of the prescribed dose?
							 wd.findElement(By.id("DC_Page_SBRTV3_B_i_rb_"+i)).click();
							 
							 //How was the cord identified ?
							 wd.findElement(By.id("DC_Page_SBRTV3_B_ii_rb_0")).click();//CT mylegram
							 jse.executeScript("window.scrollBy(0,400)", "");
							//Date of first treatment
							 wd.findElement(By.id("DC_Page_SBRTV3_B_1_date")).click();//calendar
							 WebElement days = wd.findElement(By.id("ui-datepicker-div"));
					   		   
					   		   List<WebElement> ro=datenum.findElements(By.tagName("tr"));
					   		   List<WebElement> co=datenum.findElements(By.tagName("td"));
					   		
					   		   for (WebElement cell: co)
					   		   {
					   		
					   		    	   //Select Date 
					   		    if (cell.getText().equals(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue()))))
					   		    	  {
					   		    		 
					   		    	 cell.findElement(By.linkText(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue())))).click();
					   		    	  break;
					   		    	 }
					   		   } 
					   		Thread.sleep(1500);
					   		
					   	//*******iteration for Did the prescription include the following elements:*******
					   		for(char rb='A';rb<='H';rb++)
					   		{
					   		
					   		// Site/Target
					   		 wd.findElement(By.id("DC_Page_SBRTV3_B_"+rb+"_rb_"+i)).click();
					   		
					   		}
					   		
					   		jse.executeScript("window.scrollBy(0,400)", "");
					   		
					   		//Was the prescription signed and dated by radiation oncologist prior to first treatment? 
					   		wd.findElement(By.id("DC_Page_SBRTV3_B_4_rb_"+i)).click();
					   		
					   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
					   		wd.findElement(By.id("DC_Page_SBRTV3_B_6_rb_"+i)).click();
					   		
					     	//Dose(Gy)/Fraction
					   		wd.findElement(By.id("DC_Page_SBRTV3B_Q5_A_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
					   		wd.findElement(By.id("DC_Page_SBRTV3B_Q5_B_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
					   		   
							//Number of Fractions
					   		wd.findElement(By.id("DC_Page_SBRTV3B_Q5_C_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
					   		
					   		//Energy
					   		wd.findElement(By.id("DC_Page_SBRTV3B_Q5_D_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
					   		
					   		//Total Dose(Gy)
					   		wd.findElement(By.id("DC_Page_SBRTV3B_Q5_E_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
					   		
					   	//***********************checkbox looping from C to E********************************************
					   		char k='C';
					 for(char x='C';x<='E';x++)
					   {
					   		
					   		//***********SBRT chech box***********
							 wd.findElement(By.id("DC_Page_SBRTV3_"+x+"_ch")).click();
							
							//Date of first treatment
							 wd.findElement(By.id("DC_Page_SBRTV3_"+x+"_1_date")).click();//calendar
							 wd.findElement(By.id("ui-datepicker-div"));
							 
					   		   List<WebElement> t=datenum.findElements(By.tagName("tr"));
					   		   List<WebElement> cl=datenum.findElements(By.tagName("td"));
					   		
					   		   for (WebElement cell: cl)
					   		   {
					   		
					   		    	   //Select Date 
					   		    if (cell.getText().equals(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue()))))
					   		    	  {
					   		    		 
					   		    	 cell.findElement(By.linkText(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue())))).click();
					   		    	  break;
					   		    	 }
					   		   } 
					   		Thread.sleep(1500);
					   		jse.executeScript("window.scrollBy(0,350)", "");
					   		for(char l='A';l<='H';l++)
					   		{
					   		// Site/Target
					   		 wd.findElement(By.id("DC_Page_SBRTV3_"+x+"_"+l+"_rb_"+i)).click();
					   		}
					   		jse.executeScript("window.scrollBy(0,400)", "");
					   		
					   		//Was the prescription signed and dated by radiation oncologist prior to first treatment? 
					   		wd.findElement(By.id("DC_Page_SBRTV3_"+x+"_4_rb_"+i)).click();
					   		
					   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
					   		wd.findElement(By.id("DC_Page_SBRTV3_"+x+"_6_rb_"+i)).click();
					   		
					   		
                          while(k<='E')
					   		{
					     	//Dose(Gy)/Fraction
                       	wd.findElement(By.id("DC_Page_SBRTV3"+k+"_Q5_A_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
					   		wd.findElement(By.id("DC_Page_SBRTV3"+k+"_Q5_B_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
					   		
							//Number of Fractions
					   		wd.findElement(By.id("DC_Page_SBRTV3"+k+"_Q5_C_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
					   		
					   		//Energy
					   		wd.findElement(By.id("DC_Page_SBRTV3"+k+"_Q5_D_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
					   		
					   		//Total Dose(Gy)
					   		wd.findElement(By.id("DC_Page_SBRTV3"+k+"_Q5_E_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
					   		break;
					   		}
                      		++k;	   		
				      }   	
					 
					 //SBRT Comments:
					 wd.findElement(By.id("SBRT_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					 jse.executeScript("window.scrollBy(0,100)", "");
				   	//Rating Scale
				   	wd.findElement(By.id("SBRT_LikeRating_2")).click(); //Compliant=3
				   		
				   	jse.executeScript("window.scrollBy(0,-1500)", "");
				   	jse.executeScript("window.scrollBy(0,-1500)", "");
				   	jse.executeScript("window.scrollBy(0,-1000)", "");
				
				    //***********SRS  tab click************************************
					 wd.findElement(By.id("ui-id-11")).click();	
					 
					 //	Was there a written planning instructions/dose volume constraints?
					 wd.findElement(By.id("DC_Page_SRSv3_1_A_rb_"+i)).click();
					 
					 // Did the radiation oncologist review and approve multi-slice, coronal, and sagittal images with isodose lines and DVHs?
					 wd.findElement(By.id("DC_Page_SRSv3_1_B_rb_"+i)).click();
						 
					 //Is this a brain metastasis case?
					 wd.findElement(By.id("DC_Page_SRSv3_1_rb_1")).click();
					 
					 //Was the conformity index documented ? 
					 wd.findElement(By.id("DC_Page_SRSv3_2_rb_1")).click();
					 
					 //Was the course of treatment appropriate: including volume, dose fractionation and total dose?
					 wd.findElement(By.id("DC_Page_SRSv3_4_rb_"+i)).click();
					
					 //Was the isocernter verified?
					 wd.findElement(By.id("DC_Page_SRSv3_5_rb_"+i)).click();
					 
					 ////Date of first treatment
					 wd.findElement(By.id("DC_Page_SRSv3_1_date")).click();//calendar
					 wd.findElement(By.id("ui-datepicker-div"));
			   		   
			   		   List<WebElement> rownum=datenum.findElements(By.tagName("tr"));
			   		   List<WebElement> colnum=datenum.findElements(By.tagName("td"));
			   		
			   		   for (WebElement cell: colnum)
			   		   {
			   		
			   		    	   //Select Date 
			   		    if (cell.getText().equals(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue()))))
			   		    	  {
			   		    		 
			   		    	 cell.findElement(By.linkText(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue())))).click();
			   		    	  break;
			   		    	 }
			   		   } 
			   		Thread.sleep(1500);
			   		jse.executeScript("window.scrollBy(0,400)", "");
			   		
			   		// Site/Target
			   		 wd.findElement(By.id("DC_Page_SRSv3_3A_rb_"+i)).click();
			   		
			   		//*******iteration for Did the prescription include the following elements:*******
			   		for(char rb='B';rb<='H';rb++)
			   		{
			   		
			   		// Site/Target
			   		 wd.findElement(By.id("DC_Page_SRSv3_3"+rb+"_rb_"+i)).click();
			 
			   		
			   		}
			   		jse.executeScript("window.scrollBy(0,400)", "");
			   		//Was the prescription signed and dated by radiation oncologist prior to first treatment? 
			   		wd.findElement(By.id("DC_Page_SRSv3_3_4_rb_"+i)).click();
			   		
			   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
			   		wd.findElement(By.id("DC_Page_SRSv3_3_6_rb_"+i)).click();
			   		
			     	//Dose(Gy)/Fraction
			   		wd.findElement(By.id("DC_Page_SRSv3_Q5_A_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
			   		wd.findElement(By.id("DC_Page_SRSv3_Q5_B_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
			   		   
					//Number of Fractions
			   		wd.findElement(By.id("DC_Page_SRSv3_Q5_C_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
			   		
			   		//Energy
			   		wd.findElement(By.id("DC_Page_SRSv3_Q5_D_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
			   		
			   		//Total Dose(Gy)
			   		wd.findElement(By.id("DC_Page_SRSv3_Q5_E_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
			   		//SRS Comments:
			   	    wd.findElement(By.id("SRS_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
			   	 jse.executeScript("window.scrollBy(0,100)", "");
			   	    //Rating Scale
			   	    wd.findElement(By.id("SRS_LikeRating_2")).click(); //Compliant=3
			   	    jse.executeScript("window.scrollBy(0,-1000)", "");
			   	    
			   	    //***********LDR tab click************************************
					 wd.findElement(By.id("ui-id-12")).click();
					 
					 int ldr=1;
					 
					//*************Gyn check from A to B iteration**********
	for(char l='A';l<='B';l++)
			{
					 //*****Gyn check **********
						 wd.findElement(By.id("DC_Page_LDR"+l+"_ch")).click();
						 
					//Date of first treatment
					 wd.findElement(By.id("DC_Page_LDRControlV3_LDR"+l+"_1_txt")).click();//calendar
					 wd.findElement(By.id("ui-datepicker-div"));
			   		   
			   		   List<WebElement> roww=datenum.findElements(By.tagName("tr"));
			   		   List<WebElement> coll=datenum.findElements(By.tagName("td"));
			   		
			   		   for (WebElement cell: coll)
			   		   {
			   		
			   		    	   //Select Date 
			   		    if (cell.getText().equals(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue()))))
			   		    	  {
			   		    		 
			   		    	 cell.findElement(By.linkText(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue())))).click();
			   		    	  break;
			   		    	 }
			   		   } 
			   		Thread.sleep(1500);
			   		jse.executeScript("window.scrollBy(0,350)", "");
			   		
			   		for(char lr='A';lr<='H';lr++)
			   		{
			   		// Site/Target
			   			
			   		 wd.findElement(By.id("DC_Page_LDRControlV3_LDR"+l+"_"+lr+"_rb_"+i)).click();
			   		}
			   		jse.executeScript("window.scrollBy(0,300)", "");
			   		//Was the prescription signed and dated by radiation oncologist prior to first treatment?
			   		wd.findElement(By.id("DC_Page_LDRControlV3_LDR"+l+"_4_rb_"+i)).click();
			   		
			   		
			   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
			   		wd.findElement(By.id("DC_Page_LDRControlV3_LDR"+l+"_6_rb_"+i)).click();
			   	
			   		//Dose(Gy)/Fraction DC_Page_LDRV3_LDR1_gvSites_LDR1_txtDose_0
			   		wd.findElement(By.id("DC_Page_LDR"+l+"_Q5_A_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
			   		wd.findElement(By.id("DC_Page_LDR"+l+"_Q5_B_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
			   		
					//Number of Fractions
			   		wd.findElement(By.id("DC_Page_LDR"+l+"_Q5_C_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
			   		
			   		//Energy
			   		wd.findElement(By.id("DC_Page_LDR"+l+"_Q5_E_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
			   		
			   		//Prescription	Point 
			   		Select d = new Select(wd.findElement(By.id("DC_Page_LDR"+l+"_Q5_F_ddl1")));
					d.selectByVisibleText("Surface");
				
			   		//Total Dose(Gy) 
			   		wd.findElement(By.id("DC_Page_LDR"+l+"_Q5_G_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
			   		               		
              		jse.executeScript("window.scrollBy(0,250)", "");
			   		//What isotope was used for treatment? *
			   		Select treat = new Select(wd.findElement(By.id("DC_Page_LDRControlV3_LDR"+l+"_I_rb")));
					treat.selectByVisibleText("CS137");
					
					// 	Was the dose to the bladder determined?
					wd.findElement(By.id("DC_Page_LDRControlV3_LDR"+l+"_II_rb_"+i)).click();
					
					//Was the dose to the rectum determined?
					wd.findElement(By.id("DC_Page_LDRControlV3_LDR"+l+"_III_rb_"+i)).click();   	    
			}
	
					//*************Gyn Brachytherapy-Definitive Endometrial Cancer (no hysterectomy) check**********
					wd.findElement(By.id("DC_Page_LDRC_ch")).click();
	 
					//Date of first treatment
					wd.findElement(By.id("DC_Page_LDRControlV3_LDRC_1_txt")).click();//calendar
					wd.findElement(By.id("ui-datepicker-div"));
  		   
					List<WebElement> roww=datenum.findElements(By.tagName("tr"));
					List<WebElement> coll=datenum.findElements(By.tagName("td"));
  		
					for (WebElement cell: coll)
					{
  		
						//Select Date 
						if (cell.getText().equals(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue()))))
  		    	  {
  		    		 
  		    	 cell.findElement(By.linkText(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue())))).click();
  		    	  break;
  		    	  }
					} 
					Thread.sleep(1500);
					jse.executeScript("window.scrollBy(0,400)", "");
  		
					for(char lr='A';lr<='H';lr++)
					{
						// Site/Target
  			
						wd.findElement(By.id("DC_Page_LDRControlV3_LDRC_"+lr+"_rb_"+i)).click();
					}
  		
						//Was the prescription signed and dated by radiation oncologist prior to first treatment?
						wd.findElement(By.id("DC_Page_LDRControlV3_LDRC_4_rb_"+i)).click();
  		
						//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
						wd.findElement(By.id("DC_Page_LDRControlV3_LDRC_6_rb_"+i)).click();
						jse.executeScript("window.scrollBy(0,400)", "");
						//Dose(Gy)/Fraction 
						wd.findElement(By.id("DC_Page_LDRC_Q5_A_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
						wd.findElement(By.id("DC_Page_LDRC_Q5_B_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
  		   
						//Number of Fractions
						wd.findElement(By.id("DC_Page_LDRC_Q5_C_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
  		
						//DoseRate
						wd.findElement(By.id("DC_Page_LDRC_Q5_E_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
  		
						//Prescription	Point
						Select d = new Select(wd.findElement(By.id("DC_Page_LDRC_Q5_F_ddl1")));
						d.selectByVisibleText("Surface");
  		
						//Total Dose(Gy)
						wd.findElement(By.id("DC_Page_LDRC_Q5_G_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
						
						//What isotope was used for treatment?
						Select treat = new Select(wd.findElement(By.id("DC_Page_LDRControlV3_LDRC_II_rb")));
						treat.selectByVisibleText("CS137");
						
						//Was this? 
						wd.findElement(By.id("DC_Page_LDRControlV3_LDRC_I_rb_0")).click();
		
						// 	Was the dose to the bladder determined?
						wd.findElement(By.id("DC_Page_LDRControlV3_LDRC_III_rb_"+i)).click();
		
						//Was the dose to the rectum determined?
						wd.findElement(By.id("DC_Page_LDRControlV3_LDRC_IV_rb_"+i)).click();
						
						int num=4;
						 
							//*************Gyn check from d to E iteration**********
			for(char l='D';l<='E';l++)
					{
							 //*****Gyn check **********
								 wd.findElement(By.id("DC_Page_LDR"+l+"_ch")).click();
								 jse.executeScript("window.scrollBy(0,400)", "");
							//Date of first treatment
							 wd.findElement(By.id("DC_Page_LDRControlV3_LDR"+l+"_1_txt")).click();//calendar
							 wd.findElement(By.id("ui-datepicker-div"));
					   		   
					   		   List<WebElement> rws=datenum.findElements(By.tagName("tr"));
					   		   List<WebElement> cols=datenum.findElements(By.tagName("td"));
					   		
					   		   for (WebElement cell: cols)
					   		   {
					   		
					   		    	   //Select Date 
					   		    if (cell.getText().equals(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue()))))
					   		    	  {
					   		    		 
					   		    	 cell.findElement(By.linkText(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue())))).click();
					   		    	  break;
					   		    	 }
					   		   } 
					   		Thread.sleep(1500);
					   		for(char lr='A';lr<='H';lr++)
					   		{
					   		// Site/Target
					   			
					   		 wd.findElement(By.id("DC_Page_LDRControlV3_LDR"+l+"_"+lr+"_rb_"+i)).click();
					   		}
					   		jse.executeScript("window.scrollBy(0,450)", "");
					   		//Was the prescription signed and dated by radiation oncologist prior to first treatment?
					   		wd.findElement(By.id("DC_Page_LDRControlV3_LDR"+l+"_4_rb_"+i)).click();
					   		
					   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
					   		wd.findElement(By.id("DC_Page_LDRControlV3_LDR"+l+"_6_rb_"+i)).click();
					   		
					   		//Dose(Gy)/Fraction 
					   		wd.findElement(By.id("DC_Page_LDR"+l+"_Q5_A_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
					   		wd.findElement(By.id("DC_Page_LDR"+l+"_Q5_B_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
					   		
							//Number of Fractions
					   		wd.findElement(By.id("DC_Page_LDR"+l+"_Q5_C_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
					   		
					   		//Energy
					   		wd.findElement(By.id("DC_Page_LDR"+l+"_Q5_E_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
					   		
					   		//Prescription	Point
					   		Select point = new Select(wd.findElement(By.id("DC_Page_LDR"+l+"_Q5_F_ddl1")));
							point.selectByVisibleText("Surface");
					   		
					   		//Total Dose(Gy)
					   		wd.findElement(By.id("DC_Page_LDR"+l+"_Q5_G_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));

					   		//What isotope was used for treatment? *
					   		Select treatment = new Select(wd.findElement(By.id("DC_Page_LDRControlV3_LDR"+l+"_II_rb")));
							treatment.selectByVisibleText("CS137");
							
							//Was this? 
							wd.findElement(By.id("DC_Page_LDRControlV3_LDR"+l+"_I_rb_0")).click();	  	    
					}
							//LDR Comments:
							wd.findElement(By.id("LDR_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							jse.executeScript("window.scrollBy(0,300)", "");
							
							//Rating Scale
							wd.findElement(By.id("LDR_LikeRating_2")).click(); //Compliant=3
		   		
							jse.executeScript("window.scrollBy(0,-1500)", "");
							jse.executeScript("window.scrollBy(0,-1500)", "");
							jse.executeScript("window.scrollBy(0,-1800)", "");
							
							//***********HDR  tab click************************************
							 wd.findElement(By.id("ui-id-13")).click();	
							 
					   	
					   		//*************Gyn check from B to C iteration**********
					   		
for(char l='A';l<='E';l++)
	{
			
						String id="string"+l;
						String id1="DC_Page_HDRControlV3_HDR"+l+"_gvSites_HDR"+l+"_txtDose_0";
						if(l=='E'){
				   			jse.executeScript("window.scrollBy(0,100)", "");
				   		}
					   			//*****Gyn check **********
							 wd.findElement(By.id("DC_Page_HDRControlV3_HDR"+l+"_ch")).click();
							 
							 //Date of first treatment
							 wd.findElement(By.id("DC_Page_HDRControlV3_HDR"+l+"_1_txt")).click();//calendar
							 wd.findElement(By.id("ui-datepicker-div"));
				   		   
				   		   List<WebElement> hr=datenum.findElements(By.tagName("tr"));
				   		   List<WebElement> vr=datenum.findElements(By.tagName("td"));
				   		
				   		   for (WebElement cell: vr)
				   		   {
				   		
				   		    	   //Select Date 
				   		    if (cell.getText().equals(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue()))))
				   		    	  {
				   		    		 
				   		    	 cell.findElement(By.linkText(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue())))).click();
				   		    	  break;
				   		    	 }
				   		   } 
				   		Thread.sleep(1500);
				   		jse.executeScript("window.scrollBy(0,400)", "");
				   		if(l=='C'||l=='D'){
				   			jse.executeScript("window.scrollBy(0,100)", "");
				   		}
				   		
				   		for(char lr='A';lr<='H';lr++)
				   		{
				   		// Site/Target
				   			//HDRControlV3_HDRB_A_rb_0
				   		 wd.findElement(By.id("DC_Page_HDRControlV3_HDR"+l+"_"+lr+"_rb_"+i)).click();
				   		}
				   		
				   		if(l=='E'){
				   			jse.executeScript("window.scrollBy(0,100)", "");
				   		}
				   		
				   		if(id.equals("stringC")) //for option c
				   		{
				   		//Was the prescription signed and dated by radiation oncologist prior to first treatment?
					   		wd.findElement(By.id("DC_Page_HDRControlV3_HDRC_4_rb_"+i)).click();
					   		jse.executeScript("window.scrollBy(0,300)", "");	
					   	//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
					   		wd.findElement(By.id("DC_Page_HDRControlV3_HDRC_6_rb_"+i)).click();
				   		}
				   		else{
				   			
				   		//Was the prescription signed and dated by radiation oncologist prior to first treatment?
				   		wd.findElement(By.id("DC_Page_HDRControlV3_HDR"+l+"_4_rb_"+i)).click();
				   		jse.executeScript("window.scrollBy(0,300)", "");
				   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
				   		wd.findElement(By.id("DC_Page_HDRControlV3_HDR"+l+"_6_rb_"+i)).click();
				   			}
				   		
				   		if(id.equals("stringA"))
				   		{
				   		//Does the patient meet the suitable criteria for partial breast brachytherapy 
				   			wd.findElement(By.id("DC_Page_HDRControlV3_HDRA_I_rb_"+i)).click();	
				   		}
				   		jse.executeScript("window.scrollBy(0,375)", "");
				   		//Dose(Gy)/Fraction
				   		wd.findElement(By.id("DC_Page_HDR"+l+"_Q5_A_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
				   		wd.findElement(By.id("DC_Page_HDR"+l+"_Q5_B_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
				   		
						//Number of Fractions
				   		wd.findElement(By.id("DC_Page_HDR"+l+"_Q5_C_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
				   		
				   		//DoseRate
				   		wd.findElement(By.id("DC_Page_HDR"+l+"_Q5_E_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
				   		
				   		//Prescription	Point
				   		Select pt = new Select(wd.findElement(By.id("DC_Page_HDR"+l+"_Q5_F_ddl1")));
						pt.selectByVisibleText("Surface");
				   		
				   		//Total Dose(Gy)
				   		wd.findElement(By.id("DC_Page_HDR"+l+"_Q5_G_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
				   		
				   		if(id.equals("stringA"))
				   		{
				   				
				   		    //What isotope was used for treatment? 
					   		Select treatment = new Select(wd.findElement(By.id("DC_Page_HDRControlV3_HDRA_II_rb")));
					   		treatment.selectByVisibleText("CS137");
					   		
					   		//What applicator/technique was used?
					   		Select app = new Select(wd.findElement(By.id("DC_Page_HDRControlV3_HDRA_II_ddl")));
					   		app.selectByVisibleText("Single channel balloon device");
					   		
					   		//If treatments were given twice a day, was the time between treatments six hours or more?
					   		wd.findElement(By.id("DC_Page_HDRControlV3_HDRA_III_rb_"+i)).click();	
				   		}
			else{
				   		
					if(id.equals("stringB"))
				   		{
				   		//What isotope was used for treatment? *
				   		Select t = new Select(wd.findElement(By.id("DC_Page_HDRControlV3_HDRB_6_i_rb")));
						t.selectByVisibleText("CS137");
						
						//Was the dose to the bladder determined?
						wd.findElement(By.id("DC_Page_HDRControlV3_HDRB_6_ii_rb_"+i)).click();
						
						//Was the dose to the rectum determined?
						wd.findElement(By.id("DC_Page_HDRControlV3_HDRB_6_iii_rb_"+i)).click();
				   		}
				   		else
				   		{  
				   			if(id.equals("stringC"))
				   			{
				   			//What isotope was used for treatment? 
					   		Select t = new Select(wd.findElement(By.id("DC_Page_HDRControlV3_HDRC_i_rb")));
							t.selectByVisibleText("CS137");	
							
							//Was the dose to the bladder determined?
							wd.findElement(By.id("DC_Page_HDRControlV3_HDRC_ii_rb_"+i)).click();
							
							//Was the dose to the rectum determined?
							wd.findElement(By.id("DC_Page_HDRControlV3_HDRC_iii_rb_"+i)).click(); 
				   			}
				   			else 
				   			{
				   				if(id.equals("stringD"))
				   				{
				   				//What isotope was used for treatment? 
						   		Select t = new Select(wd.findElement(By.id("DC_Page_HDRControlV3_HDRD_ii_rb")));
								t.selectByVisibleText("CS137");	
								
								//Was this?
								wd.findElement(By.id("DC_Page_HDRControlV3_HDRD_i_rb_0")).click();
								
								//Was the dose to the bladder determined?
								wd.findElement(By.id("DC_Page_HDRControlV3_HDRD_iii_rb_"+i)).click();
								
								//Was the dose to the rectum determined?
								wd.findElement(By.id("DC_Page_HDRControlV3_HDRD_iv_rb_"+i)).click(); 	
				   				} 
				   				else
				   				{
				   				//What isotope was used for treatment? 
							   	Select t = new Select(wd.findElement(By.id("DC_Page_HDRControlV3_HDRE_ii_rb")));
								t.selectByVisibleText("CS137");	
									
								//Was this?
								wd.findElement(By.id("DC_Page_HDRControlV3_HDRE_i_rb_0")).click();
									
								//What applicator was used?
								wd.findElement(By.id("DC_Page_HDRControlV3_HDRE_iii_ch_0")).click();
										
				   				}
				   			}	
				   		
				   	}		   		
			}
	}
						jse.executeScript("window.scrollBy(0,400)", "");
						
				   		//HDR Comments:
				   		wd.findElement(By.id("HDR_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				   		
				   		//Rating Scale
				   		wd.findElement(By.id("HDR_LikeRating_2")).click(); //Compliant=3
				   		
				   		jse.executeScript("window.scrollBy(0,-1500)", "");
					   	jse.executeScript("window.scrollBy(0,-1500)", "");
					   	jse.executeScript("window.scrollBy(0,-1500)", "");
					
				   	   //**********************Seed Implant   tab click************************************
						 wd.findElement(By.id("ui-id-14")).click();	
						 
						//Date of first treatment
						 wd.findElement(By.id("DC_Page_SEEDV3_2C_1_date")).click();//calendar
						 wd.findElement(By.id("ui-datepicker-div"));
			   		   
			   		   List<WebElement> hr=datenum.findElements(By.tagName("tr"));
			   		   List<WebElement> vr=datenum.findElements(By.tagName("td"));
			   		
			   		   for (WebElement cell: vr)
			   		   {
			   		
			   		    	   //Select Date 
			   		    if (cell.getText().equals(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue()))))
			   		    	  {
			   		    		 
			   		    	 cell.findElement(By.linkText(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue())))).click();
			   		    	  break;
			   		    	 }
			   		   } 
			   		Thread.sleep(1500);
			   		jse.executeScript("window.scrollBy(0,300)", "");
			   		
			   		for(char lr='A';lr<='H';lr++)
			   		{
			   		// Site/Target
			   			//HDRControlV3_HDRB_A_rb_0
			   		 wd.findElement(By.id("DC_Page_SEEDV3_2C_"+lr+"_rb_"+i)).click();
			   		}
			   		
			   		//Was the prescription signed and dated by radiation oncologist prior to first treatment?
			   		wd.findElement(By.id("DC_Page_SEEDV3_4_rb_"+i)).click();
			   		
			   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
			   		wd.findElement(By.id("DC_Page_SEEDV3_6_rb_"+i)).click();
			   		jse.executeScript("window.scrollBy(0,400)", "");
			   		//Dose(Gy)/Fraction
			   		wd.findElement(By.id("DC_Page_SEEDV3_Q5_A_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
			   		wd.findElement(By.id("DC_Page_SEEDV3_Q5_B_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
			   		   
					//Number of Fractions
			   		wd.findElement(By.id("DC_Page_SEEDV3_Q5_C_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
			   		
			   		//DoseRate
			   		wd.findElement(By.id("DC_Page_SEEDV3_Q5_E_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
			   		
			   		//Prescription	Point
			   		Select pt = new Select(wd.findElement(By.id("DC_Page_SEEDV3_Q5_F_ddl1")));
					pt.selectByVisibleText("Surface");
			   		
			   		//Total Dose(Gy)
			   		wd.findElement(By.id("DC_Page_SEEDV3_Q5_G_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
			   		
			   		//Was this?
					wd.findElement(By.id("DC_Page_SEEDV3_6_i_rb_0")).click();
					
					//What isotope was used for treatment? 
			   		Select t = new Select(wd.findElement(By.id("DC_Page_SEEDV3_6_ii_rb")));
					t.selectByVisibleText("CS137");
					
					//When was the post implant CT scan done? 
					wd.findElement(By.id("DC_Page_SEEDV3_6_iii_rb_0")).click();
					jse.executeScript("window.scrollBy(0,400)", "");
					//What was the D90 
					wd.findElement(By.id("DC_Page_SEEDV3_6_iv_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					
					//. What was the V100 
					wd.findElement(By.id("DC_Page_SEEDV3_6_v_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				   	
					//Seed Implant Comments:
					wd.findElement(By.id("SI_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					
			   			jse.executeScript("window.scrollBy(0,200)", "");
				   	//Rating Scale	
					wd.findElement(By.id("SI_LikeRating_2")).click(); //Compliant=3	
				   		
				 	jse.executeScript("window.scrollBy(0,-1000)", "");
				   	
				  //**********************Protons tab click************************************  
				   	wd.findElement(By.id("ui-id-15")).click();	
				   		
				   	//Did the radiation oncologist review and approve the volumes including the PTV
				   	wd.findElement(By.id("DC_Page_PROTON_1_A_rb_"+i)).click();
				   	
				   	// Was there a written planning instructions/dose volume constraints?
				   	wd.findElement(By.id("DC_Page_PROTON_1_B_rb_"+i)).click();
				   	
				   	//Did the radiation oncologist review and approve multi-slice, coronal, and sagittal images with isodose lines and DVHs?
				   	wd.findElement(By.id("DC_Page_PROTON_1_C_rb_"+i)).click();
				   	jse.executeScript("window.scrollBy(0,300)", "");
				   	
				   	//How was the dose prescribed?
				   	wd.findElement(By.id("DC_Page_PROTON_1_D_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				   	
				   	//Is ICRU-78 Used? 
				   	wd.findElement(By.id("DC_Page_PROTON_1_E_rb_0")).click();
				   	
				   	// Is RBE included?
					wd.findElement(By.id("DC_Page_PROTON_1_F_rb_0")).click();
					
					//What was the RBE value? 
					wd.findElement(By.id("DC_Page_PROTON_1_G_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					
					//***********For prostate protons, mark the important structures that were contoured? check *****************
					wd.findElement(By.id("DC_Page_PROTON_2A_ch")).click();
					
					//i. Bladder
					wd.findElement(By.id("DC_Page_PROTON_2A_i_ch")).click();
					//v. Rectal volume including contents
					wd.findElement(By.id("DC_Page_PROTON_2A_v_ch")).click();
					jse.executeScript("window.scrollBy(0,400)", "");
					//Comments:
					wd.findElement(By.id("DC_Page_PROTON_2A_i_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					
					//***********B. For lung cancers, mark the important structures that were contoured? check *****************
					wd.findElement(By.id("DC_Page_PROTON_2B_ch")).click();
				   	//i. Brachial Plexus
					wd.findElement(By.id("DC_Page_PROTON_2B_i_ch")).click();
					//Comments:
					wd.findElement(By.id("DC_Page_PROTON_2B_i_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					
					//***********C. For head and neck IMRT: mark the important structures that are contoured? check *****************	
					wd.findElement(By.id("DC_Page_PROTON_2C_ch")).click();
					//i. Brachial Plexus
					wd.findElement(By.id("DC_Page_PROTON_2C_i_ch")).click();
					//v. Constrictor Muscles/Upper Esophagus
					wd.findElement(By.id("DC_Page_PROTON_2C_iv_ch")).click();
					//Comments:
					wd.findElement(By.id("DC_Page_PROTON_2C_i_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());

			   			jse.executeScript("window.scrollBy(0,300)", "");

					//********************D.Other  check *************************
					wd.findElement(By.id("DC_Page_PROTON_2D_ch")).click();
					//Comments:
					wd.findElement(By.id("DC_Page_PROTON_2D_i_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					
					//Date of first treatment
					 wd.findElement(By.id("DC_Page_PROTON_2C_1_date")).click();//calendar
					 wd.findElement(By.id("ui-datepicker-div"));
		   		   
		   		   List<WebElement> row1=datenum.findElements(By.tagName("tr"));
		   		   List<WebElement> col1=datenum.findElements(By.tagName("td"));
		   		
		   		   for (WebElement cell: col1)
		   		   {
		   		
		   		    	   //Select Date 
		   		    if (cell.getText().equals(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue()))))
		   		    	  {
		   		    		 
		   		    	 cell.findElement(By.linkText(String.valueOf(Math.round(sh.getRow(1).getCell(136).getNumericCellValue())))).click();
		   		    	  break;
		   		    	 }
		   		   } 
		   		Thread.sleep(1500);
		   		jse.executeScript("window.scrollBy(0,400)", "");
		   		for(char lr='A';lr<='H';lr++)
		   		{
		   		// Site/Target
		   			//HDRControlV3_HDRB_A_rb_0
		   		 wd.findElement(By.id("DC_Page_PROTON_2C_"+lr+"_rb_"+i)).click();
		   		}
		   		
		   		//Was the prescription signed and dated by radiation oncologist prior to first treatment?
		   		wd.findElement(By.id("DC_Page_PROTON_4_rb_"+i)).click();
		   		jse.executeScript("window.scrollBy(0,400)", "");
		   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
		   		wd.findElement(By.id("DC_Page_PROTON_6_rb_"+i)).click();
		   		
		   		//Dose(Gy)/Fraction
		   		wd.findElement(By.id("DC_Page_PROTON_Q5_A_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
		   		wd.findElement(By.id("DC_Page_PROTON_Q5_B_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
		   		   
				//Number of Fractions
		   		wd.findElement(By.id("DC_Page_PROTON_Q5_C_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
		   		
		   		//DoseRate
		   		wd.findElement(By.id("DC_Page_PROTON_Q5_D_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
		   		
		   		//Total Dose(Gy)
		   		wd.findElement(By.id("DC_Page_PROTON_Q5_E_txt1")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
		   		
		   		for(char lt='A';lt<='D';lt++)
		   		{
		   		wd.findElement(By.id("DC_Page_PROTON_6_"+lt+"_rb_"+i)).click();
		   		}
		   		jse.executeScript("window.scrollBy(0,400)", "");
		   		
		   		//Protons Comments:
		   		wd.findElement(By.id("PROTON_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				   		
				//Rating Scale
		   		wd.findElement(By.id("PROTON_LikeRating_2")).click(); //Compliant=3
		   		jse.executeScript("window.scrollBy(0,200)", "");
		   		//************************Patient Evaluation tab******************************************
				wd.findElement(By.id("ui-accordion-div_Page_DC-header-2")).click();
				Thread.sleep(300);
				
				if(v!=1){
					
					wd.findElement(By.id("ui-accordion-div_Page_DC-header-2")).click();
					
				}
				
				// Is there evidence of a progress note performed for each five fractions of therapy? (Answer Does not Apply if patient received less than 5 fractions)
				wd.findElement(By.id("DC_Page_Section3_q1_1_rb_"+i)).click();
				
				//Was accumulated dose included? 
				wd.findElement(By.id("DC_Page_Section3_q1_3_rb_"+i)).click();
				jse.executeScript("window.scrollBy(0,300)", "");
				
				//Was tolerance to treatment documented?
				wd.findElement(By.id("DC_Page_Section3_q1_4_rb_"+i)).click();
				
				// Was there documentation of interruption, continuation or conclusion of therapy?
				wd.findElement(By.id("DC_Page_Section3_q1_5_rb_0")).click();
				
				//Is this patient on treatment?
				wd.findElement(By.id("DC_Page_Section3_q2_rb_0")).click();
				
				//Patient Evaluation Comments:
		   		wd.findElement(By.id("Section3_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
		   		jse.executeScript("window.scrollBy(0,300)", "");
				//Rating Scale
		   		wd.findElement(By.id("Section3_LikeRating_2")).click(); //Compliant=3 
		   		
		   		jse.executeScript("window.scrollBy(0,300)", "");
		   		//************************Patient Evaluation tab******************************************
				//wd.findElement(By.id("ui-accordion-accordion-header-2")).click();
		   		
		   		wd.findElement(By.id("btn_ONP_btm_next")).click();//next button
		   		log.info("offline: End of MD Data Collection" +v);
					}
					Thread.sleep(500);
					wd.findElement(By.className("scrollup")).click();
					Thread.sleep(500);
					 //******************************CheckList page********************************************
					 
					 log.info("CheckList page");
					 
					 wd.findElement(By.linkText("CheckList")).click();
					 wd.findElement(By.id("CheckList")).click();//opening check list from left side menu
					 
					 //Time Out Policy *
					 wd.findElement(By.id("CheckListV3Offline_P_Q1_rb_"+i)).click();
					 jse.executeScript("window.scrollBy(0,300)", "");
					 //Contrast Policy 
					 wd.findElement(By.id("CheckListV3Offline_P_Q2_rb_"+i)).click();
					 
					 //Imaging Portal and IGRT 
					 wd.findElement(By.id("CheckListV3Offline_P_Q3_rb_"+i)).click();
					
					 
					 //Disaster Plan *
					 wd.findElement(By.id("CheckListV3Offline_P_Q4_rb_"+i)).click();

					 //Infection Control *
					 wd.findElement(By.id("CheckListV3Offline_P_Q5_rb_"+i)).click();
					 
					 //	Policy and Procedures Comments 
					 wd.findElement(By.id("CheckListV3Offline_P_txtcmmt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					 
					 //Rating Scale 
					 wd.findElement(By.id("CheckListV3Offline_P_LikeRating_2")).click();
					 
					 //Chart Rounds *
					 wd.findElement(By.id("CheckListV3Offline_Q_Q1_rb_"+i)).click();
					  jse.executeScript("window.scrollBy(0,400)", "");
					 //M&M *
					 wd.findElement(By.id("CheckListV3Offline_Q_Q2_rb_"+i)).click();
					
					 //Focus Studies
					 wd.findElement(By.id("CheckListV3Offline_Q_Q3_rb_"+i)).click();
					 
					 //Internal Outcome
					 wd.findElement(By.id("CheckListV3Offline_Q_Q4_rb_"+i)).click();
					 
					 //Physician Peer Review Documentation
					 wd.findElement(By.id("CheckListV3Offline_Q_Q5_rb_"+i)).click();
					
					 
					 //Physicist Peer Review Documentation
					 wd.findElement(By.id("CheckListV3Offline_Q_Q6_rb_"+i)).click();
					 
					 //QA and CQI 
					 wd.findElement(By.id("CheckListV3Offline_Q_txtcmmt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					  jse.executeScript("window.scrollBy(0,400)", "");
					 //Rating Scale
					 wd.findElement(By.id("CheckListV3Offline_Q_LikeRating_2")).click();
					 
					 //comment
					 wd.findElement(By.id("CheckListV3Offline_Overall_txtcmmt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					 
					 wd.findElement(By.id("btn_ONP_btm_next")).click();//next
					 Thread.sleep(300);
					 log.info("End of CheckList page");
					 Thread.sleep(500);
					wd.findElement(By.className("scrollup")).click();
					Thread.sleep(500);
					wd.findElement(By.id("btn_sync_ON_P")).click();// onsite syn button
					log.info("onsite forms syn");
					Thread.sleep(500);
					wd.findElement(By.id("btn_sync_MDDC")).click();// dc syn button
					Thread.sleep(500);
					log.info("Md forms syn");
					wd.findElement(By.id("btn_sync_CheckList")).click();// dc syn button
					Thread.sleep(500);
					log.info("checklist syn");
Thread.sleep(5000);
					
					//opening url
					 wd.get(prop.getProperty("QA_URL"));
					 
					 Ropa_pom.login(sh.getRow(1).getCell(102).getStringCellValue(), sh.getRow(1).getCell(103).getStringCellValue());
						log.info("Login as MD");
						
						//Change Role:
						Select dropdown = new Select(wd.findElement(By.id("ddrROles")));
						dropdown.selectByVisibleText("MD Surveyor");
						Ropa_pom.view_assigned_Application("cphMaster_btnAppliAssgndtoMe");
						//opening MD DataCollection Forms from left side
						wd.findElement(By.linkText("MD DataCollection Forms")).click();
						
						//MD Data Collection
						wd.findElement(By.linkText("MD Data Collection")).click();
						
						wd.findElement(By.id("cphMaster_Next")).click(); //next
						
						Thread.sleep(300);
						
						//*********************Physician Final Report V3*********************
						
						log.info("Physician Final Report V3");
				   		//Has the facility complied with recommendations included in the last Survey Report (if applicable)? Please refer to prior survey report included in your documents. 
				   		wd.findElement(By.id("Q1_rb_"+i)).click(); 
				   		// 	Comments: 
				   		wd.findElement(By.id("cphMaster_Q1_txtComment")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				   		
				   		//Please provide any comments or recommendations that may help the facility to improve or enhance patient care but that do not impact their accreditation
				   		// 	Comments:
				   		wd.findElement(By.id("cphMaster_Q2_txtComment")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				   		jse.executeScript("window.scrollBy(0,200)", "");
				   		
				   		//3. 	Recommendation for accreditation: 
				   		//Rating Scale
				   		wd.findElement(By.id("Q3_rb_2")).click(); //Compliant=3 
				   		
				   		// 	Please comment on your score:
				   		wd.findElement(By.id("CAP_txtComment")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				   		jse.executeScript("window.scrollBy(0,900)", "");
				   		
				   		wd.findElement(By.id("cphMaster_btnNext")).click(); //next
				   		
				   		wd.findElement(By.id("cphMaster_Finish")).click(); //submit
				   		
				   		Ropa_pom.logout();
				   		log.info("Logout");
			    		Thread.sleep(1500);

	
							
}//method
  		
		
		
}//class

