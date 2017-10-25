package ropa_1site_Application;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import page_objects.Ropa_pom;

public class IE_Application extends Ropa_pom {
	public static Alert alert; 
	public static String beam;
	public static String c;
	public static String url;
	public static int site;
	public static String user;
	public static String password;

	  public  static void ie_app()throws InterruptedException {
		  try{
			
			/*login with facility*/
			 wd.get(prop.getProperty("QA_URL"));
			 Ropa_pom.login(sh.getRow(1).getCell(2).getStringCellValue(), sh.getRow(1).getCell(1).getStringCellValue());
			log.info("login with facility");
				
			/*selecting type of cycle*/
			wd.findElement(By.id("cphMaster_rdbtnSelectSurvey_0")).click();//initial
			//1 consultative
			//2 mini audit
			Thread.sleep(900);
			log.info("selecting type of cycle");
			
			jse.executeScript("window.scrollBy(0,700)", "");
			wd.findElement(By.id("cphMaster_new_app")).click();
			
			//*****no of sites******
			Long site =Math.round(sh.getRow(1).getCell(147).getNumericCellValue());
			int sitenum =site.intValue();

			//selecting number of sites
			Select dropdown = new Select(wd.findElement(By.id("cphMaster_ddlNoOfSites")));
			dropdown.selectByIndex(sitenum);
			log.info("selecting no of sites");
			
			//JavascriptExecutor jse = (JavascriptExecutor)wd;
			jse.executeScript("window.scrollBy(0,700)", "");
			wd.findElement(By.id("cphMaster_btnProceed")).click();
		
			//wd.findElement(By.linkText("Open")).click();
			//wd.findElement(By.linkText("View")).click();
			//wd.findElement(By.linkText("Edit")).click();

	  for(int i=1;i<=sitenum;i++)
	  {
				
				wd.navigate().to(prop.getProperty("app_view"));
				wd.findElement(By.linkText("Open")).click();
				log.info("site"+i+" Application forms");
				
			 //************************************page1************************************************
				log.info("site" +i+ "page1 start");
				Thread.sleep(500);
				
      /*Site Information*/
		Select drop = new Select(wd.findElement(By.id("cphMaster_cphApplication_ddlCountry")));
		drop.selectByVisibleText("US");
		wd.findElement(By.id("cphMaster_cphApplication_p1_FacilityName_txt")).sendKeys(sh.getRow(i).getCell(5).getStringCellValue());
		wd.findElement(By.id("cphMaster_cphApplication_p1_Address_txt")).sendKeys(sh.getRow(i).getCell(6).getStringCellValue());//street
		wd.findElement(By.id("cphMaster_cphApplication_p1_Address1_txt")).sendKeys("1400 Patten Rd, Lookout Mountain");//street2
		wd.findElement(By.id("cphMaster_cphApplication_p1_City_txt")).sendKeys(sh.getRow(i).getCell(7).getStringCellValue());//city
		
		Select s = new Select(wd.findElement(By.id("cphMaster_cphApplication_pl_State_ddl")));//state
		s.selectByVisibleText(sh.getRow(i).getCell(8).getStringCellValue());//state
		
		wd.findElement(By.id("cphMaster_cphApplication_p1_Zip_txt")).sendKeys("23456");//zip
		jse.executeScript("window.scrollBy(0,700)", "");
		wd.findElement(By.id("cphMaster_cphApplication_p1_sitewebaddress_txt")).sendKeys(sh.getRow(i).getCell(9).getStringCellValue());//website
		
		/*Mailing Address If different from above*/
		Thread.sleep(1500);
		wd.findElement(By.id("cphMaster_cphApplication_P1_mailcheck")).click();
		
		wd.findElement(By.id("cphMaster_cphApplication_p1_Phone_txt")).sendKeys(String.valueOf(sh.getRow(i).getCell(10).getNumericCellValue()));//converting integer to string
		wd.findElement(By.id("cphMaster_cphApplication_p1_Fax_txt")).sendKeys(String.valueOf(sh.getRow(i).getCell(11).getNumericCellValue()));		
		wd.findElement(By.id("cphMaster_cphApplication_p1_ContactPerson_txt")).sendKeys(sh.getRow(i).getCell(12).getStringCellValue());
		wd.findElement(By.id("cphMaster_cphApplication_p1_ContactPhone_txt")).sendKeys(String.valueOf(sh.getRow(i).getCell(13).getNumericCellValue()));
		wd.findElement(By.id("cphMaster_cphApplication_p1_ContactEmail_txt")).sendKeys(sh.getRow(i).getCell(14).getStringCellValue());
		wd.findElement(By.id("cphMaster_cphApplication_p1_CRO_txt")).sendKeys(sh.getRow(i).getCell(15).getStringCellValue());
		wd.findElement(By.id("cphMaster_cphApplication_p1_contactEmail2_txt")).sendKeys(sh.getRow(i).getCell(16).getStringCellValue());

		jse.executeScript("window.scrollBy(0,700)", "");
		/*Will there be two staff members?*/
		wd.findElement(By.id(sh.getRow(i).getCell(17).getStringCellValue())).click();


		jse.executeScript("window.scrollBy(0,900)", "");
		/*Conference room within the Radiation Oncology Department?*/
		wd.findElement(By.id(sh.getRow(i).getCell(18).getStringCellValue())).click();
		
		
		/*If you are EMR,? */
		wd.findElement(By.id(sh.getRow(i).getCell(19).getStringCellValue())).click();
		
		/*Are you a Veterans Health Administration facility?*/
		//wd.findElement(By.id(sh.getRow(i).getCell(20).getStringCellValue())).click(); 
		 String b=sh.getRow(i).getCell(20).getStringCellValue();
		 String a="cphMaster_cphApplication_Veterans_Affairs_rbl_0";
		
		 if(a.equals(b))
		 {
			 wd.findElement(By.id(sh.getRow(i).getCell(20).getStringCellValue())).click();//yes 
			
		 }
		 else
		 {
			 
		 wd.findElement(By.id(sh.getRow(i).getCell(20).getStringCellValue())).click();//no
		
		/*Can you provide web access to the surveyors to complete the survey ?*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_wa_rbl_0")).click(); //yes
		//wd.findElement(By.id("cphMaster_cphApplication_p1_wa_rbl_1")).click(); //no
		
		
		/*Can you provide two computers with Internet access ? */
		wd.findElement(By.id("p1_ia_rb1_0")).click(); //yes
		//wd.findElement(By.id("p1_ia_rb1_1")).click(); //no
		//jse.executeScript("window.scrollBy(0,700)", "");
		
		
		/*Can you provide 2 monitors per computer( The two monitors will be connected to the one computer to view your EMR(s) and for the data entry to website) ?*/
		wd.findElement(By.id("p1_ia_rb2_0")).click(); //yes
		//wd.findElement(By.id("p1_ia_rb2_1")).click(); //no
		
		 }
		 jse.executeScript("window.scrollBy(0,1500)", "");
		/*How did you hear about Radiation Oncology Accreditation? */
		//wd.findElement(By.id("cphMaster_cphApplication_p1_HearROA_chk_0")).click();//renewal
	    wd.findElement(By.id("cphMaster_cphApplication_p1_HearROA_chk_2")).click(); //website  
	    
		//wd.findElement(By.id("cphMaster_cphApplication_p1_HearROA_chk_3")).click(); //Conference 
		//wd.findElement(By.id("cphMaster_cphApplication_p1_HearROA_chk_4")).click(); //Other 
		//wd.findElement(By.id("cphMaster_cphApplication_p1_HearROA_Other_txt")).sendKeys("wtrehjgy"); //if other is checked
	    
	    wd.findElement(By.id("ulFiles")).click();
	    String browser=prop.getProperty("Browser");
	    if(browser.equalsIgnoreCase("IE")){
	    Runtime.getRuntime().exec("D:\\FileUpload.exe");
	    Thread.sleep(200);
	    }else{
	    	Runtime.getRuntime().exec("D:\\Upload.exe");
	    }
	    wd.findElement(By.id("btnUpload")).click();
	    Thread.sleep(200);
	    jse.executeScript("window.scrollBy(0,1800)", "");
	    wd.findElement(By.id("cphMaster_btnNext")).click();
	    log.info("site" +i+ "page1 end");
	    Thread.sleep(500);
	    //**************************************page 2*******************************************************

	    log.info("site" +i+ "page2 start");
       /*Which of the following best describes where this facility is located?*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_Sb_q1_rbl_0")).click(); //Hospital Based
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		/*If the practice offers these services at another facility, please indicate where the services are performed*/
		c=sh.getRow(i).getCell(21).getStringCellValue();
		beam="cphMaster_cphApplication_p1_sc_q1_ch1_chk";
		
		if(beam.equals(c))
		{
		wd.findElement(By.id(sh.getRow(i).getCell(21).getStringCellValue())).click(); //External Beam Therapy
		
		}
		else
		{
			jse.executeScript("window.scrollBy(0,1500)", "");
        wd.findElement(By.id(sh.getRow(i).getCell(21).getStringCellValue())).click(); //  IGRT 

	    wd.findElement(By.id("cphMaster_cphApplication_p1_sc_q11_IGRT_chk_0")).click(); // Stereoscopic KV x-rays OR orthogonal x-rays //if IGRT is cheched
	   
		}
		jse.executeScript("window.scrollBy(0,1100)", "");
		
		/*Is all or any of your treatment record electronic and no longer recorded in hardcopy? */
		wd.findElement(By.id(sh.getRow(i).getCell(22).getStringCellValue())).click();
	
		
		/*Are images (simulation and port films) electronic?*/
		wd.findElement(By.id(sh.getRow(i).getCell(23).getStringCellValue())).click();
		
		/*Do you have a record and verify system? */
		wd.findElement(By.id(sh.getRow(i).getCell(24).getStringCellValue())).click();
		jse.executeScript("window.scrollBy(0,1100)", "");
	
		
		/*If Yes:  
			What type of record and verify system do you have?*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_sc_q20_chkl_0")).click();
	
		
		/*Contouring Software*/
		wd.findElement(By.id(sh.getRow(i).getCell(25).getStringCellValue())).click();
		
		/*What type of treatment planning system do you have? */
		wd.findElement(By.id(sh.getRow(i).getCell(26).getStringCellValue())).click();
	
		
		/*What algorithm are you using?*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_sc_q19_chkl_0")).click();
		jse.executeScript("window.scrollBy(0,1500)", "");
		wd.findElement(By.id("cphMaster_btnNext")).click();
		log.info("site" +i+ "page2 end");
		Thread.sleep(500);
		 //****************************************page3*****************************************************
		
		log.info("site" +i+ "page3 Start");
		 /*Total Number of New treatment courses ACR ROPA defines New....*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_sd_q1_txt")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(27).getNumericCellValue())));
		
		/*Total number of low dose rate brachytherapy procedures at this facility*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_sd_q2_txt")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(28).getNumericCellValue())));
		
		/*Total number of high dose rate brachytherapy procedures at this facility*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_sd_q3_txt")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(29).getNumericCellValue())));
		
		/*Total number of prostate seed implants at this facility*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_sd_q4_txt")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(30).getNumericCellValue())));
	
		
		/*Total Number of Treatment Units (Only include External Beam Units)*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_se_q1_txt")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(31).getNumericCellValue())));
	
		
		/*Number of Linear Accelerators with electrons*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_se_q2_txt")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(32).getNumericCellValue())));
		
		
		/*Number of single photon energy machines*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_se_q3_txt")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(33).getNumericCellValue())));
		
		
		/*Number of dual photon energy machines*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_se_q4_txt")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(34).getNumericCellValue())));
		
		
		/*Number of Robotic Linear Accelerators*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_se_q5_txt")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(35).getNumericCellValue())));
		
		
		/*Number of superficial or orthovoltage units*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_se_q6_txt")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(36).getNumericCellValue())));
		
		/*Helical tomotherapy*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_se_q7_txt")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(37).getNumericCellValue())));
		
		
		/*Number of HDR remote afterloading units*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_se_q8_txt")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(38).getNumericCellValue())));
		
		
		/*Number of stereotactic radiotherapy units*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_se_q9_txt")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(39).getNumericCellValue())));
		
		
		/*Number of proton beam units*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_se_q10_txt")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(40).getNumericCellValue())));
		
		
		/*Other*/
		wd.findElement(By.id("cphMaster_cphApplication_p1_se_q11_txt")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(41).getNumericCellValue())));
		
		//JavascriptExecutor jse = (JavascriptExecutor)wd;
		jse.executeScript("window.scrollBy(0,1100)", "");
		
		/*SECTION F . TREATMENT PLANNING EQUIPMENT AND DEVICES*/ 
		wd.findElement(By.id("cphMaster_cphApplication_p1_HearROA_chk_0")).click();
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		wd.findElement(By.id("cphMaster_btnNext")).click();
		log.info("site" +i+ "page3 end");
		Thread.sleep(500);
		//*****************************************page 4*******************************************
		
		/*Add "Radiation Oncologist" staff*/
		log.info("site" +i+ "page4 Start");
		//1st staff
		for(int j=1;j<=2;j++)
		{
		wd.findElement(By.id("cphMaster_cphApplication_tablStaf_ctl00_ctl00_lnkNew")).click();//add
		Thread.sleep(300);
		 		
		wd.findElement(By.id("cphMaster_cphApplication_tablStaf_ctl00_ctl00_gvFTEStaff_txtFName")).sendKeys(sh.getRow(j).getCell(42).getStringCellValue());//First Name
		
		wd.findElement(By.id("cphMaster_cphApplication_tablStaf_ctl00_ctl00_gvFTEStaff_txtInitial")).sendKeys(sh.getRow(j).getCell(43).getStringCellValue());//last name
		
		wd.findElement(By.id("chkbox_ropeer_0")).click();//Participation of R-O PEER
		
		wd.findElement(By.id("cphMaster_cphApplication_tablStaf_ctl00_ctl00_gvFTEStaff_txtHours")).sendKeys(String.valueOf(Math.round(sh.getRow(j).getCell(44).getNumericCellValue())));//Hours Worked PerWeek
		
		wd.findElement(By.id(sh.getRow(j).getCell(45).getStringCellValue())).click();//certificate
		jse.executeScript("window.scrollBy(0,900)", "");
		wd.findElement(By.id("cphMaster_cphApplication_tablStaf_ctl00_ctl00_gvFTEStaff_btnInsert")).click();//insert
		Thread.sleep(500);
		
		}
		//JavascriptExecutor jse = (JavascriptExecutor)wd;
		jse.executeScript("window.scrollBy(0,1500)", "");
		wd.findElement(By.id("cphMaster_btnNext")).click();//next
		log.info("site" +i+ "page4 end");
		Thread.sleep(500);
		//****************************************page 5**************************************************
			
		log.info("site" +i+ "page5 Start");
		if(beam.equals(c))
		{
		/*If External Beam Therapy is checked in page2*/
		wd.findElement(By.id("cphMaster_cphApplication_Machine_btn")).click();
		
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_txtManufacturer")).sendKeys(sh.getRow(i).getCell(46).getStringCellValue());//Manufacturer
		
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_txtMachineType")).sendKeys(sh.getRow(i).getCell(47).getStringCellValue());//Machine type 
		jse.executeScript("window.scrollBy(0,900)", "");
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_txtModelNumber")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(48).getNumericCellValue())));//Model Number 
		
		//JavascriptExecutor jse = (JavascriptExecutor)wd;
	
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_txtSerialNumber")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(49).getNumericCellValue())));//Serial Number 
		jse.executeScript("window.scrollBy(0,900)", "");
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_txtYearInstalled")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(50).getNumericCellValue())));//Year Installed
		
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_beamEnergies_Chkl_0")).click();//Photon 
		
		jse.executeScript("window.scrollBy(0,900)", "");
	
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_txtDateOfCalibration")).click();

		//calender
		    Ropa_pom.calendar("calendar",String.valueOf(Math.round(sh.getRow(i).getCell(51).getNumericCellValue())) );
			jse.executeScript("window.scrollBy(0,900)", "");
			
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_uploadPage5New_ulFiles")).click();
		if(browser.equalsIgnoreCase("IE")){
		    Runtime.getRuntime().exec("D:\\FileUpload.exe");
		    Thread.sleep(200);
		    }else{
		    	Runtime.getRuntime().exec("D:\\Upload.exe");
		    	 Thread.sleep(200);
		    }		
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_uploadPage5New_btnUpload")).click();
		 Thread.sleep(200);	
		jse.executeScript("window.scrollBy(0,700)", "");
			
		/*Do you participate in IROC Houston Physics QA for clinical trials? */
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_IROC_rbl_0")).click();
	
		//if yes
		/*What is your Radiation Treatment Facility (RTF) number?*/
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_RTF_txt")).sendKeys(String.valueOf(Math.round(sh.getRow(i).getCell(53).getNumericCellValue())));
	
		/*Which end-to-end QA phantoms have you irradiated for IROC : */
		/* if checked as 2. IMRT Head and Neck*/
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_chk_head_neck")).click();
		
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_headneck_year_txt")).sendKeys("4366");
		jse.executeScript("window.scrollBy(0,700)", "");
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_headneck_IROC_credentialed_rbl_0")).click();

		jse.executeScript("window.scrollBy(0,2500)", "");
		//Most recent independent beam output verification reports for your treatment machine. 
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_TLD_OSLD_upload_ulFiles")).click();
		if(browser.equalsIgnoreCase("IE")){
		    Runtime.getRuntime().exec("D:\\FileUpload.exe");
		    Thread.sleep(200);
		    }else{
		    	Runtime.getRuntime().exec("D:\\Upload.exe");
		    	 Thread.sleep(200);
		    }	
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_TLD_OSLD_upload_btnUpload")).click();
		Thread.sleep(200);	
		jse.executeScript("window.scrollBy(0,2500)", "");
		
		//Do you have IROC Houston end-to-end QA phantom reports for the past 5 years?
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_QAphantom_rbl_0")).click();
		Thread.sleep(1500);
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_QAphantom_upload_ulFiles")).click();
		if(browser.equalsIgnoreCase("IE")){
		    Runtime.getRuntime().exec("D:\\FileUpload.exe");
		    Thread.sleep(200);
		    }else{
		    	Runtime.getRuntime().exec("D:\\Upload.exe");
		    	 Thread.sleep(200);
		    }	
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_QAphantom_upload_btnUpload")).click();
		Thread.sleep(200);	
		jse.executeScript("window.scrollBy(0,2500)", "");
		
		 jse.executeScript("window.scrollBy(0,1500)", "");
		//A copy of the most recent IROC Houston onsite dosimetry review visit report performed during the last 5 years (optional upload). 
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_dosimetry_upload_ulFiles")).click();
		if(browser.equalsIgnoreCase("IE")){
		    Runtime.getRuntime().exec("D:\\FileUpload.exe");
		    Thread.sleep(200);
		    }else{
		    	Runtime.getRuntime().exec("D:\\Upload.exe");
		    	 Thread.sleep(200);
		    }	
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_dosimetry_upload_btnUpload")).click();
		Thread.sleep(200);	
		 jse.executeScript("window.scrollBy(0,1500)", "");
		 jse.executeScript("window.scrollBy(0,1500)", "");
		//Any additional physics QA documents 
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_physicsQA_upload_ulFiles")).click();
		if(browser.equalsIgnoreCase("IE")){
		    Runtime.getRuntime().exec("D:\\FileUpload.exe");
		    Thread.sleep(200);
		    }else{
		    	Runtime.getRuntime().exec("D:\\Upload.exe");
		    	 Thread.sleep(200);
		    }	
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_physicsQA_upload_btnUpload")).click();
		Thread.sleep(200);	
		 jse.executeScript("window.scrollBy(0,1500)", "");
		 jse.executeScript("window.scrollBy(0,1500)", "");
		//Do you follow AAPM TG-142?
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_AAPM_TG_142_rbl_0")).click();
	
	 	//Do you follow AAPM TG-51 (or TRS398 for protons)?
		wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_AAPM_TG_51_rbl_0")).click();
		
		//wd.findElement(By.id("cphMaster_cphApplication_tabMachine_ctl00_cntMachine0_AAPM_TG_51_txt")).sendKeys("sdgaeh");
		
		wd.findElement(By.id("cphMaster_btnNext")).click();
		}
		else
		{
			wd.findElement(By.id("cphMaster_btnNext")).click();
		}
		
		log.info("site" +i+ "page5 end");
		Thread.sleep(500);
		//********************************Physician Release Form*************************************

		log.info("site" +i+ "Physician Release Form");
for(int k=0;k<=1;k++)
{
	String tab="__tab_cphMaster_cphApplication_tabPhysician_Page4Tab_"+k;
			wd.findElement(By.id(tab)).click();
			Thread.sleep(1500);
		//I will provide electronic signatures
		//wd.findElement(By.linkText("cphMaster_cphApplication_tabPhysician_Page4Tab_0_cntRleaseForm0_rdbtn_prvideelesigns_0")).click();
		//JavascriptExecutor jse = (JavascriptExecutor)wd;
		jse.executeScript("window.scrollBy(0,1800)", "");
			jse.executeScript("window.scrollBy(0,1500)", "");
		//Executed on
	    String str_txtExcutedDate="cphMaster_cphApplication_tabPhysician_Page4Tab_"+k+"_cntRleaseForm"+k+"_txtExcutedDate"; 
	   
		wd.findElement(By.id(str_txtExcutedDate)).click();
		Thread.sleep(1500);
		 Ropa_pom.calendar("calendar", String.valueOf(Math.round(sh.getRow(i).getCell(56).getNumericCellValue())));
				
		   String cntRleaseForm="cphMaster_cphApplication_tabPhysician_Page4Tab_"+k+"_cntRleaseForm"+k+"_fuROUpload";                  
		wd.findElement(By.id(cntRleaseForm)).click();
		if(browser.equalsIgnoreCase("IE")){
		    Runtime.getRuntime().exec("D:\\FileUpload.exe");
		    Thread.sleep(200);
		    }else{
		    	Runtime.getRuntime().exec("D:\\Upload.exe");
		    	 Thread.sleep(200);
		    }
	    String upload="cphMaster_cphApplication_tabPhysician_Page4Tab_"+k+"_cntRleaseForm"+k+"_btnROUpload";
		wd.findElement(By.id(upload)).click();
		Thread.sleep(200);
		
		
		jse.executeScript("window.scrollBy(0,-900)", "");
		jse.executeScript("window.scrollBy(0,-900)", "");
		jse.executeScript("window.scrollBy(0,-900)", "");
		
		
}
	
	jse.executeScript("window.scrollBy(0,1800)", "");
	
		wd.findElement(By.id("cphMaster_btnNext")).click();

		Thread.sleep(500);
		//****************************************R-O PEER Release Form********************************************* 
		
		log.info("site" +i+ "R-O PEER Release Form");
for(int m=0;m<=1;m++)
	{
	 String rotab="__tab_cphMaster_cphApplication_tabPhysician_Page4Tab_"+m;
	wd.findElement(By.id(rotab)).click();
	
	String country="cphMaster_cphApplication_tabPhysician_Page4Tab_"+m+"_cntRleaseForm"+m+"_phyDdlCountry"; 
		
		Select d = new Select(wd.findElement(By.id(country)));
		d.selectByVisibleText("US");
		
		//street
		String street="cphMaster_cphApplication_tabPhysician_Page4Tab_"+m+"_cntRleaseForm"+m+"_txtPhyStreetAdd";
		wd.findElement(By.id(street)).sendKeys(sh.getRow(i).getCell(6).getStringCellValue());
		
		String city="cphMaster_cphApplication_tabPhysician_Page4Tab_"+m+"_cntRleaseForm"+m+"_txtPhyCity";
		
		wd.findElement(By.id(city)).sendKeys(sh.getRow(i).getCell(7).getStringCellValue());//city
		
		String state="cphMaster_cphApplication_tabPhysician_Page4Tab_"+m+"_cntRleaseForm"+m+"_ddlPhyState";
		Select se = new Select(wd.findElement(By.id(state)));
		
		se.selectByVisibleText(sh.getRow(i).getCell(8).getStringCellValue());//state
		
		String zip="cphMaster_cphApplication_tabPhysician_Page4Tab_"+m+"_cntRleaseForm"+m+"_txtPhyZip";
		wd.findElement(By.id(zip)).sendKeys("12456");//zip
		
		String mail="cphMaster_cphApplication_tabPhysician_Page4Tab_"+m+"_cntRleaseForm"+m+"_txtropeermailid";
		wd.findElement(By.id(mail)).sendKeys(sh.getRow(i).getCell(14).getStringCellValue());//mailid
		
		jse.executeScript("window.scrollBy(0,3000)", "");
		String cal="cphMaster_cphApplication_tabPhysician_Page4Tab_"+m+"_cntRleaseForm"+m+"_txtExcutedDate";
		wd.findElement(By.id(cal)).click();//calendar
		 Ropa_pom.calendar("calendar", String.valueOf(Math.round(sh.getRow(i).getCell(58).getNumericCellValue())));
		 
		 String load=   "cphMaster_cphApplication_tabPhysician_Page4Tab_"+m+"_cntRleaseForm"+m+"_fuROUpload";
		wd.findElement(By.id(load)).click();
		if(browser.equalsIgnoreCase("IE")){
		    Runtime.getRuntime().exec("D:\\FileUpload.exe");
		    Thread.sleep(200);
		    }else{
		    	Runtime.getRuntime().exec("D:\\Upload.exe");
		    	 Thread.sleep(200);
		    }
		String upbtn="cphMaster_cphApplication_tabPhysician_Page4Tab_"+m+"_cntRleaseForm"+m+"_btnROUpload";
		wd.findElement(By.id(upbtn)).click();
	
		Thread.sleep(1500);
		
		jse.executeScript("window.scrollBy(0,-1800)", "");
		jse.executeScript("window.scrollBy(0,-1800)", "");
		jse.executeScript("window.scrollBy(0,-900)", "");
	}	//for m 
		
		
		jse.executeScript("window.scrollBy(0,3000)", "");
		jse.executeScript("window.scrollBy(0,3000)", "");
		jse.executeScript("window.scrollBy(0,3000)", "");
		wd.findElement(By.id("cphMaster_btnNext")).click();//next
		Thread.sleep(900);
		
			 }//for

	  Thread.sleep(500);
		  //*****************************************page6***********************************************

		
				//opening part 2 from left side
				wd.findElement(By.linkText("Part II")).click();
				wd.findElement(By.linkText("Page 6")).click();
				
				log.info("Page6 start");
		wd.findElement(By.id("cphMaster_cphApplication_txtPName")).sendKeys(sh.getRow(1).getCell(60).getStringCellValue());//Practice/Organization Name :
		//JavascriptExecutor jse = (JavascriptExecutor)wd;
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		//1. Select the type of Application?  
		wd.findElement(By.id(sh.getRow(1).getCell(61).getStringCellValue())).click();//Hospital Corporation of America (HCA/Sarah Cannon)
		
	 	//Do you have up-to-date policy and procedure manuals to direct the administrative and clinical aspects of the practice?
		wd.findElement(By.id(sh.getRow(1).getCell(62).getStringCellValue())).click();//yes
		
		//Do you have a policy in place to monitor, analyze and report and periodically review complications and adverse events or activities that may have the potential for sentinel events?
		wd.findElement(By.id(sh.getRow(1).getCell(63).getStringCellValue())).click();//yes
		
		//Do you have a policy in place to control the spread of infection among patients and personnel
		wd.findElement(By.id(sh.getRow(1).getCell(64).getStringCellValue())).click();//yes
		
		//Do you have a time out policy in place ?
		wd.findElement(By.id(sh.getRow(1).getCell(65).getStringCellValue())).click();//yes
		
		
		//Do you dispense medications, including intravenous contrast ?
		wd.findElement(By.id(sh.getRow(1).getCell(66).getStringCellValue())).click();//yes
		
		
		//Do you have a medication reconciliation policy ?
		wd.findElement(By.id(sh.getRow(1).getCell(67).getStringCellValue())).click();//yes
		 jse.executeScript("window.scrollBy(0,1500)", "");
		
		//is there a board certified radiation oncologist on the premises when treatment is delivered to the patients during normal business hours? 
		wd.findElement(By.id(sh.getRow(1).getCell(68).getStringCellValue())).click();//yes
		jse.executeScript("window.scrollBy(0,900)", "");
	
		//Is there a board certified medical physicist on the premises at least two full business days (or 0.4 FTE) per week? 
		wd.findElement(By.id(sh.getRow(1).getCell(69).getStringCellValue())).click();//no
		wd.findElement(By.id("cphMaster_cphApplication_p2_sa_q8_txt")).sendKeys(sh.getRow(1).getCell(70).getStringCellValue());//comment
		
	 	//Are there two radiation therapists on the treatment machine during treatment during normal business hours? 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sa_q9_rbl_2")).click();//NA
		
		//Have you been cited by federal or state regulatory agencies (such as NRC/Agreement State) within the last 3 years? If yes submit all correspondence and documentation.
		wd.findElement(By.id("cphMaster_cphApplication_p2_sb_q1_rb_0")).click();//yes
		Thread.sleep(300);
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		//How does your state define misadministration of radiation therapy and what are their requirements?
		wd.findElement(By.id("cphMaster_cphApplication_p2_sb_q2_txt")).sendKeys("here is a comment");//comment
		jse.executeScript("window.scrollBy(0,2500)", "");
		//Frequency Routinely Performed 
		//New Patient Rounds
		wd.findElement(By.id(sh.getRow(1).getCell(71).getStringCellValue())).click();//Daily
		
		jse.executeScript("window.scrollBy(0,1500)", "");
		//Chart Rounds
		wd.findElement(By.id(sh.getRow(1).getCell(72).getStringCellValue())).click();//Weekly
	
		//Physician Peer Review
		wd.findElement(By.id(sh.getRow(1).getCell(73).getStringCellValue())).click();//Monthly
		
		//Tumor Boards
		wd.findElement(By.id(sh.getRow(1).getCell(74).getStringCellValue())).click();//Daily
		
		//Physicist Peer Review
		wd.findElement(By.id(sh.getRow(1).getCell(75).getStringCellValue())).click();//Daily
		
		jse.executeScript("window.scrollBy(0,1500)", "");
		//QA Meetings
		wd.findElement(By.id(sh.getRow(1).getCell(76).getStringCellValue())).click();//Every 3 months
		
		//Morbidity and Mortality (M&M)
		wd.findElement(By.id(sh.getRow(1).getCell(77).getStringCellValue())).click();//Not Done
	
		//Internal Outcome Studies
		wd.findElement(By.id(sh.getRow(1).getCell(78).getStringCellValue())).click();//Weekly
		
		//Focus Studies
		wd.findElement(By.id(sh.getRow(1).getCell(79).getStringCellValue())).click();//Daily
	
		//Patient Satisfaction Survey
		wd.findElement(By.id(sh.getRow(1).getCell(80).getStringCellValue())).click();//Monthly
		
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		wd.findElement(By.id("cphMaster_btnNext")).click();//next
		log.info("Page6 end");
		Thread.sleep(500);
		//*******************************************page 7*******************************************

		log.info("Page7 start");
		//1A.Treatment prescriptions are signed by the radiation oncologist prior to initiation of radiation therapy.
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_1a_rbl_0")).click();//yes
		
		//1B.Treatment prescriptions include Volume
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_1b_q1_rbl_2")).click();//NA
		jse.executeScript("window.scrollBy(0,2500)", "");
	 	//Description of portals (i.e., AP, PA, lateral, etc.)
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_1b_q2_rbl_0")).click();//yes
		
		//JavascriptExecutor jse = (JavascriptExecutor)wd;
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		//Radiation modality
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_1b_q3_rbl_1")).click();//no
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_1b_q3_txt")).sendKeys("here is a comment");
		
		//Dose per fraction
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_1b_q4_rbl_0")).click();//yes
		
		//Number of fractions per day
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_1b_q5_rbl_2")).click();//NA
	
		//Total number of fractions
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_1b_q6_rbl_2")).click();//NA
		
		//Total tumor dose
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_1b_q7_rbl_0")).click();//yes
		jse.executeScript("window.scrollBy(0,1500)", "");
	
		//Prescription point or isodose
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_1b_q8_rbl_0")).click();//yes
		
		//Calculations are checked by an independent person or method before the first treatment if the total number of fractions is five or fewer, or otherwise before the third fraction 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_1c_q1_rbl_1")).click();//no
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_1c_q1_txt")).sendKeys("here is a comment");//comment
		
	 	//A written order is provided prior to initiation of simulation
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_1d_q1_rbl_0")).click();
		
		// 	If contrast is used, who screens the patient? 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_1e_txt")).sendKeys("text");
		
		// 	What do you screen for?  
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_1e_a_txt")).sendKeys("screening text");
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		// 	Who draws the target following simulation? 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_2a_a_ch1_Chkl_0")).click();//Physician
		
		//Who draws the Organs At Risk (OAR) contours ? 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_2a_b_ch1_chkl_1")).click();//Physicist
		
		// 	When does physician finalize and approve the plan ? 
		wd.findElement(By.id("cphMaster_cphApplication_pfap_rbl_0")).click();//Before the first treatment 
		
		// 	What happens if the physician does not approve the plan? 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_2a_e_txt")).sendKeys("text");
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		//Portal verification films/images are performed For any new fields (including field changes)
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_2b_a_rbl_0")).click();//yes
		
		// 	At least weekly
		wd.findElement(By.id("cphMaster_cphApplication_P2_SC_2b_b_rbl_0")).click();//yes
		
		//All films/images are labeled with Patient’s name
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_2c_a_rbl_0")).click();//yes
		
		// 	Date
		wd.findElement(By.id("cphMaster_cphApplication_P2_SC_2c_b_rbl_0")).click();//yes
		
		//Reviewing MD initials/signature/date of review
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_2c_c_rbl_2")).click();//NA
	
		//Reviewing MD initials/signature/date of review
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_2c_c_rbl_2")).click();//NA
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		//Chart checks are performed by a medical physicist at least weekly 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_3a_rbl_0")).click();//yes
		
		// 	Is the end of treatment (EOT) chart check done by the medical physicist within 1 week of completion?
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_3c_rbl_1")).click();//no
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_3c_txt")).sendKeys("text");
		jse.executeScript("window.scrollBy(0,1500)", "");
		wd.findElement(By.id("cphMaster_btnNext")).click();//next
		log.info("Page7 End");
		Thread.sleep(500);
		 //*****************************************page8************************************************
		
		log.info("Page8 Start");
		//	Each patient chart contains a documented comprehensive history and physical examination performed by the radiation oncologist.
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_4_rbl_0")).click();//yes
		
		//	Pain is assessed 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_4_a1_rbl_0")).click();//yes
	
		//If yes, what method is used:   
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_4_a2_txt")).sendKeys("text");
		
		//Patients are evaluated by the radiation oncologist at least weekly.
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_5_rbl_0")).click();//yes
		
		//JavascriptExecutor jse = (JavascriptExecutor)wd;
		jse.executeScript("window.scrollBy(0,700)", "");
		
		//At the completion of treatment, a follow-up plan is documented in the patient chart, and patients are seen by the radiation oncologist at regular on-going intervals (or have documentation of follow up by another physician). 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_6_rbl_2")).click();//NA
	
		//Is Brachytherapy performed? 
		wd.findElement(By.id("p2_sc_7_rbl_0")).click();//yes
		
		jse.executeScript("window.scrollBy(0,700)", "");
		//wd.findElement(By.linkText("p2_sc_7_rbl_1")).click();//NO
		//if yes
		//7A.Complete documentation is found in the patient record when brachytherapy is performed
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_7a_rbl_0")).click();//yes
		
		//A written directive is documented for each procedure and includes :Treatment site
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_7a_a_rbl_0")).click();//yes
		
		// 	Isotope 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_7a_b_rbl_1")).click();//no
		
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_7a_b_txt")).sendKeys("text");
		jse.executeScript("window.scrollBy(0,700)", "");
		//Number of sources
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_7a_c_rbl_0")).click();//yes
		
		//Planned dose to designated points
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_7a_d_rbl_0")).click();//yes
		
		// 7B.After brachytherapy, a written summary is done which includes :
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_7b_rbl_0")).click();//yes
		
		//Total dose of brachytherapy + external beam therapy
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_7b_a_rbl_2")).click();//NA
	
		jse.executeScript("window.scrollBy(0,700)", "");
		//Time of source insertion, removal and/or treatment time
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_7b_b_rbl_0")).click();//yes
		
		//  	Documentation of a radiation safety survey of patient and room
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_7b_c_rbl_2")).click();//NA
		
		// 	At the end of prostate seed implant, the total number of seeds implanted and total number remaining are verified independently by the physician and the physicist. 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_7c_rbl_0")).click();//yes
		
		
		//Physician peer review activities are formalized and documented.
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_8a_rbl_0")).click();//yes
		
		jse.executeScript("window.scrollBy(0,700)", "");
		
		//Physicist peer review activities are formalized and documented
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_8b_rbl_0")).click();//yes
		
		//9.The department has a documented, formal treatment planning system quality assurance plan, including periodic confirmation of the treatment planning system constancy. 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_9_rbl_1")).click();//No
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_9_txt")).sendKeys("text");
		
		jse.executeScript("window.scrollBy(0,700)", "");
		
	 	//When was the IMRT QA performed ? 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_10_rbl_0")).click();// Before the first treatment 
		
		// 	The qualified medical physicist has developed a protocol for performing and checking monitor units (time) calculations. 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_11_rbl_0")).click();//yes
		jse.executeScript("window.scrollBy(0,700)", "");
		
		//All calculation of monitor units or treat time shall be verified by a system for independent checking by another person or method before the first treatment. 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sc_13_rbl_0")).click();//yes
		jse.executeScript("window.scrollBy(0,1500)", "");
		wd.findElement(By.id("cphMaster_btnNext")).click();//next
		log.info("Page8 End");
		Thread.sleep(500);
		
		//**********************************************page9********************************************
		
		log.info("Page9 Start");
		//There are documented procedures for instrument calibration to ensure traceability to accredited calibration facilities and to affirm instrument precision and accuracy.
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_1_rbl_0")).click();//yes
		
		//JavascriptExecutor jse = (JavascriptExecutor)wd;
		jse.executeScript("window.scrollBy(0,1800)", "");
		//if yes
		wd.findElement(By.id("cphMaster_cphApplication_ufFiles1_ulFiles")).click();
		  String browser=prop.getProperty("Browser");
		if(browser.equalsIgnoreCase("IE")){
		    Runtime.getRuntime().exec("D:\\FileUpload.exe");
		    Thread.sleep(200);
		    }else{
		    	Runtime.getRuntime().exec("D:\\Upload.exe");
		    	 Thread.sleep(200);
		    }
	    wd.findElement(By.id("cphMaster_cphApplication_ufFiles1_btnUpload")).click();
		
		jse.executeScript("window.scrollBy(0,1800)", "");
		
		//When newly published techniques or procedures are being implemented for the first time within the institution, the medical physicist should...
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_2_rbl_1")).click();//NO
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_2_txt")).sendKeys("text");
		
		//An independent check of the output of each beam is performed annually to..
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_3_rbl_0")).click();//yes
		Thread.sleep(900);
		jse.executeScript("window.scrollBy(0,900)", "");
		jse.executeScript("window.scrollBy(0,900)", "");
		
		//The qualified medical physicist ensures that those elements of imaging equipment (e.g., CT and MR scanners)
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_4_rbl_0")).click();//yes
		
		//a. 	Do you follow TG66 for CT Simulator ? 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_4a_rbl_0")).click();//yes
		//b. 	Do you use MR fusion for planning ?
		
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_4b1_rbl_1")).click();//no
		jse.executeScript("window.scrollBy(0,1800)", "");
		//c. 	Do you use PET fusion for planning ? 
		
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_4c_rbl_0")).click();//yes
		
		//if yes
		// 	If so, For which anatomical sites?   
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_4c_txt")).sendKeys("text");
		
		//Does your facility use a heterogeneity correction factor in the planning process? 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_5_rbl_1")).click();//no
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_5_txt")).sendKeys("text");
		jse.executeScript("window.scrollBy(0,1800)", "");
		
		// 	Procedures shall be established to verify the manufacturer’s specifications and to establish baseline performance values for new or refurbished 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_6_rbl_2")).click();//NA
	
		jse.executeScript("window.scrollBy(0,1800)", "");
		
		wd.findElement(By.id("cphMaster_btnNext")).click();
		log.info("Page9 End");
		Thread.sleep(500);
		
		//*************************************page10*********************************************
		
		log.info("Page10 Start");
		//07-15. 	Do you perform LDR and/or HDR ?
		wd.findElement(By.id("p2_sd_Q7_rbl_0")).click();//yes
		//if yes
		Thread.sleep(1500);
		// 	Prescription from the radiation oncologist including the treatment site, 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_7_rbl_0")).click();//yes
		
		//JavascriptExecutor jse = (JavascriptExecutor)wd;
		jse.executeScript("window.scrollBy(0,900)", "");
		
		//8.For direct calibration of sources, a well ionization chamber with NIST-traceable calibration and known axial response and has the 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_8_rbl_1")).click();//no
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_8_txt")).sendKeys("text");
		
		//Brachytherapy applicators are radiographically inspected annually and 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_9_rbl_0")).click();//yes
		
		//Written dosimetry report is completed by the qualified medical physicist for each brachytherapy procedure. The report includes:
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_10_rbl_0")).click();//yes
		jse.executeScript("window.scrollBy(0,900)", "");
		
		//All HDR sources are calibrated prior to the first clinical use. 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_11_rbl_0")).click();//yes
		
		//if yes
		jse.executeScript("window.scrollBy(0,900)", "");
		wd.findElement(By.id("cphMaster_cphApplication_UploadFiles1_ulFiles")).click();
		if(browser.equalsIgnoreCase("IE")){
		    Runtime.getRuntime().exec("D:\\FileUpload.exe");
		    Thread.sleep(200);
		    }else{
		    	Runtime.getRuntime().exec("D:\\Upload.exe");
		    	 Thread.sleep(200);
		    }
		wd.findElement(By.id("cphMaster_cphApplication_UploadFiles1_btnUpload")).click();
		 Thread.sleep(200);
		jse.executeScript("window.scrollBy(0,900)", "");
		//The radiation oncologist and a medical physicist are in the immediate 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_12_rbl_0")).click();//yes
		
		//When HDR brachytherapy is being administered, the functioning of..
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_13_rbl_1")).click();//no
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_13_txt")).sendKeys("text");
		
		//The qualified medical physicist, in conjunction with the Radiation Safety Officer (RSO)
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_14_rbl_0")).click();//yes
		jse.executeScript("window.scrollBy(0,1800)", "");
		
		// 	Radiation safety requirements are in compliance with appropriate state 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_15_rbl_0")).click();//yes
		jse.executeScript("window.scrollBy(0,1500)", "");
		wd.findElement(By.id("cphMaster_btnNext")).click();
		log.info("Page10 End");
		Thread.sleep(500);
		//**************************************page11****************************************************
		
		log.info("Page11 Start");
		// 	Do you perform Transperineal Permanent Brachytherapy of Prostate Cancer?
		wd.findElement(By.id("p2_Q16_rbl_0")).click();//yes

		//if yes
	 	//In addition to the standard expected qualifications for the radiation oncologist, for those participating in 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_16_rbl_0")).click();//yes
		//JavascriptExecutor jse = (JavascriptExecutor)wd;
		jse.executeScript("window.scrollBy(0,900)", "");
	
		//Dosimetric planning is performed in all patients prior to or during prostate seed implantation.
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_17_rbl_0")).click();//yes
	
		// 	AAPM TG-43 and its successors are utilized for dose calculations for prostate seed implant.
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_18_rbl_1")).click();//no
		jse.executeScript("window.scrollBy(0,900)", "");
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_18_txt")).sendKeys("text");
	
		//At the end of the prostate seed implant, the total number of seeds implanted and the total number of seeds 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_19_rbl_0")).click();//yes

		// 	Do you perform Stereotactic Radiosurgery? 
		wd.findElement(By.id("p2_Q20_rbl_0")).click();//yes

		//if yes
		//In addition to standard expected qualifications for the radiation oncologist, if stereotactic radiosurgery (SRS
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_20_rbl_0")).click();//yes
		jse.executeScript("window.scrollBy(0,900)", "");
		//For stereotactic radiation therapy/radiosurgery, the qualified medical physicist performs acceptance testing
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_21_rbl_0")).click();//yes
		Thread.sleep(200);
		//For stereotactic radiation therapy/radiosurgery, the qualified medical physicist supervises the beam- 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_22_rbl_1")).click();//no
	
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_22_txt")).sendKeys("text");
		// 	For stereotactic radiation therapy/radiosurgery, the QC program includes an “operational test” of the SRS
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_23_rbl_0")).click();//yes
		jse.executeScript("window.scrollBy(0,900)", "");
	
		// 	Do you perform Intensity Modulated Radiation Therapy?
		wd.findElement(By.id("p2_Q24_rbl_0")).click();//yes

		//if yes
		// 	Documentation exists indicating that the medical physicist has authorized the system for the intended
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_24_rbl_0")).click();//yes
		
		jse.executeScript("window.scrollBy(0,900)", "");
		Thread.sleep(300);
		//Prior to start of treatment, accuracy of dose delivered is documented by irradiating a phantom, containing either 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_25_rbl_0")).click();//yes
	
		// 	Treatment verification: The radiation oncologist will be responsible for assuring that the positioning and field 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_26_rbl_0")).click();//yes
		jse.executeScript("window.scrollBy(0,700)", "");
		
		wd.findElement(By.id("cphMaster_btnNext")).click();//yes
		log.info("Page11 End");
		Thread.sleep(500);
		
		//************************************page12************************************************

		log.info("Page12 Start");
		// 	Does your facility perform proton therapy treatment?
		wd.findElement(By.id("p2_sd_ptt_rbl_0")).click();//yes

		//if yes
		//The training requirements of the radiation oncologist should conform to the qualifications
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_27_rbl_0")).click();//yes
		//A Qualified Medical Physicist must have proton specific training before assuming
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_28_rbl_2")).click();//NA
		//JavascriptExecutor jse = (JavascriptExecutor)wd;
		jse.executeScript("window.scrollBy(0,900)", "");
	
		//Targets and organs at risk should be defined according to ICRU Reports 50 and 62 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_29_rbl_0")).click();//yes

		//Proton dose calculations for treatment planning must be based on computed tomography (CT)
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_30_rbl_1")).click();//NO
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_30_txt")).sendKeys("text");
		
		// 	There must be a documented method to translate the prescribed dose
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_31_rbl_0")).click();//yes
		jse.executeScript("window.scrollBy(0,900)", "");
		//Patient-specific devices used in patient treatment for conforming the beam 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_32_rbl_2")).click();//NA
	
		//Before each fraction the patient setup should be verified by imaging. 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_33_rbl_0")).click();//yes
	
		//Annual verification of the dose and credentialing by the Radiological Physics 
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_34_rbl_1")).click();//no
		wd.findElement(By.id("cphMaster_cphApplication_p2_sd_34_txt")).sendKeys("text");
	
		jse.executeScript("window.scrollBy(0,900)", "");
		
		wd.findElement(By.id("cphMaster_btnNext")).click();
		log.info("Page12 End");
		Thread.sleep(500);
		
		//******************************************Organizational Chart****************************************
		
		log.info("Organizational Chart");
		//upload
		wd.findElement(By.id("cphMaster_cphApplication_updChart_ulFiles")).click();
		if(browser.equalsIgnoreCase("IE")){
		    Runtime.getRuntime().exec("D:\\FileUpload.exe");
		    Thread.sleep(200);
		    }else{
		    	Runtime.getRuntime().exec("D:\\Upload.exe");
		    	 Thread.sleep(200);
		    }
		wd.findElement(By.id("cphMaster_cphApplication_updChart_btnUpload")).click();
		Thread.sleep(200);
		jse.executeScript("window.scrollBy(0,1500)", "");
		wd.findElement(By.id("cphMaster_btnNext")).click();
		Thread.sleep(500);
	
		//**********************************Survey Agreement****************************************
	
		log.info("Survey Agreement");
		//JavascriptExecutor jse = (JavascriptExecutor)wd;
		jse.executeScript("window.scrollBy(0,1500)", "");
		jse.executeScript("window.scrollBy(0,1500)", "");
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		//Print Name of Radiation Oncology Medical Director/Chief
		wd.findElement(By.id("cphMaster_cphApplication_txtMDName")).sendKeys(sh.getRow(1).getCell(84).getStringCellValue());
		//Print EmailID of Radiation Oncology Medical Director/Chief
		wd.findElement(By.id("cphMaster_cphApplication_txtMDEmailID")).sendKeys(sh.getRow(1).getCell(85).getStringCellValue());
		wd.findElement(By.id("cphMaster_cphApplication_TxtExecDate")).click();//calendar
		Ropa_pom.calendar("calendar", String.valueOf(Math.round(sh.getRow(1).getCell(86).getNumericCellValue())));
		
		//Radiation Oncology Medical Director/Chief 
		//upload
		wd.findElement(By.id("cphMaster_cphApplication_fuMDSignature")).click();

		if(browser.equalsIgnoreCase("IE")){
		    Runtime.getRuntime().exec("D:\\FileUpload.exe");
		    Thread.sleep(200);
		    }else{
		    	Runtime.getRuntime().exec("D:\\Upload.exe");
		    	 Thread.sleep(200);
		    }
	    wd.findElement(By.id("cphMaster_cphApplication_btnMDSignature")).click();
	    Thread.sleep(200);
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		//Print Name of Practice Site President/CEO or Owner
		wd.findElement(By.id("cphMaster_cphApplication_txtCEOName")).sendKeys(sh.getRow(1).getCell(88).getStringCellValue());

		//Print EmailID of Practice Site President/CEO or Owner
		wd.findElement(By.id("cphMaster_cphApplication_txtCEOEmailID")).sendKeys(sh.getRow(1).getCell(89).getStringCellValue());

	   //Print Title of Practice Site President/CEO or Owner
		wd.findElement(By.id("cphMaster_cphApplication_txtCEOTitle")).sendKeys(sh.getRow(1).getCell(90).getStringCellValue());

		//Practice Site President/CEO or Owner 
		//upload
		wd.findElement(By.id("cphMaster_cphApplication_fuCEOSignature")).click();
		if(browser.equalsIgnoreCase("IE")){
		    Runtime.getRuntime().exec("D:\\FileUpload.exe");
		    Thread.sleep(200);
		    }else{
		    	Runtime.getRuntime().exec("D:\\Upload.exe");
		    	 Thread.sleep(200);
		    }
	    wd.findElement(By.id("cphMaster_cphApplication_btnCEOSignature")).click();
	    Thread.sleep(200);
		Thread.sleep(1500);
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		wd.findElement(By.id("cphMaster_btnNext")).click();
		Thread.sleep(500);
		
		//****************************************Payment page*************************************
		
		log.info("Payment page");
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		//Please Select Payment Type: 
		wd.findElement(By.id("cphMaster_cphApplication_Fac_Pay_1_rbl_0")).click();
		jse.executeScript("window.scrollBy(0,1500)", "");
		wd.findElement(By.id("cphMaster_btnNext")).click();
		Thread.sleep(500);
		
		//************************************R-O PEER Payment*********************************
		
		log.info("R-O PEER Payment");
		//JavascriptExecutor jse = (JavascriptExecutor)wd;
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		//Please Select Payment Type: 
		wd.findElement(By.id("cphMaster_cphApplication_Fac_Pay_1_rbl_0")).click();
		jse.executeScript("window.scrollBy(0,1500)", "");
		wd.findElement(By.id("cphMaster_btnNext")).click();		
		
		Thread.sleep(500);
		//**********************************Schedule Dates*************************************
		
		log.info("Schedule Dates");
	    wd.findElement(By.id("cphMaster_cphApplication_txtDate1")).click();
	    Ropa_pom.calendar("ui-datepicker-div", String.valueOf(Math.round(sh.getRow(1).getCell(86).getNumericCellValue())));
	    
	    Thread.sleep(3000);
	    //JavascriptExecutor jse = (JavascriptExecutor)wd;
		jse.executeScript("window.scrollBy(0,900)", "");
	    	    
	    wd.findElement(By.id("cphMaster_btnNext")).click();
	    Thread.sleep(500);
	    		
		//**********************************submit*************************************
		
		wd.findElement(By.id("cphMaster_Finish")).click();	
		Thread.sleep(1500);
		log.info("Application Submitted");
		Ropa_pom.logout();
		Thread.sleep(1500);
		log.info("Facility Logout");
		
		
		  }
		  catch (Exception e) {
				// TODO: handle exception
				log.error(e);
		  }
	}

}//class

