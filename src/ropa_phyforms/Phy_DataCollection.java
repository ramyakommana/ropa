package ropa_phyforms;

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
 * @author KRamyaSree
 *
 * QA_Ropa
 * 
* April 21, 2017
* 12:32:58 PM
 */

public class Phy_DataCollection extends Ropa_pom{
	
	public static int num;
	
	@Test(priority=13)
	  public void Phy_dataCollection() throws InterruptedException{
		
			try{
				
				Ropa_pom.configuration();
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
					
				//*******************Physicist  Data Collection*******************
			
			List<WebElement> count=wd.findElements(By.linkText("View"));
	    	num=count.size();
			
			//***loop for multiple views***
			for(  int v=0;v<=num-1;v++)
			{
				Long site =Math.round(sh.getRow(1).getCell(149).getNumericCellValue());
				int i =site.intValue();
		//0=yes
		//1=Recommendation
		//2-no
				if(v==1){
					i=2;
				}
				
				if(v>6)
				{
				jse.executeScript("window.scrollBy(0,1500)", "");
				}
				wd.findElement(By.id("cphMaster_grdcases_lnkEdit_"+v)).click();//view
				Thread.sleep(300);
				log.info("Physicist  Data Collection");
				
					//Treating Physician:
					wd.findElement(By.id("cphMaster_cphDataCollection_txt_trtphysicist")).sendKeys("Alex");
					
					//Chart and Physics Documentation
					wd.findElement(By.id("ui-accordion-accordion-header-0")).click();
					Thread.sleep(300);
					JavascriptExecutor jse = (JavascriptExecutor)wd;
					jse.executeScript("window.scrollBy(0,300)", "");
					
					//Date of first treatment
					 wd.findElement(By.id("cphMaster_cphDataCollection_Sec1_Q1_txt")).click();//calendar

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
			   		
			   	
					//0=yes
					//1=Recommendation
					//2-no
			   		
			   		// 	What site is being treated? 
			   		wd.findElement(By.id("Section1_q1_rb_0")).click();// Breast 
			   		
			   		//a. Is there a written directive? 
			   		wd.findElement(By.id("Section1_q1_1_rb_"+i)).click();
			   		
			   		//b. Is the written directive signed and dated by the radiation oncologist? 
			   		wd.findElement(By.id("Section1_q1_2_rb_"+i)).click();
			   		
			   		// 4	a. Are there verifiable photo(s) of patient set-up in the chart? 
			   		wd.findElement(By.id("Section1_q2_1_rb_"+i)).click();
			   		jse.executeScript("window.scrollBy(0,300)", "");
			   		// 	b. Are DRR’s present in the chart? 
			   		wd.findElement(By.id("Section1_q2_2_rb_"+i)).click();
			   		
			   		// 	c. Are weekly physics chart checks documented by a physicist? 
			   		wd.findElement(By.id("Section1_q2_3_rb_"+i)).click();
			   		
			   		// 	d. Is there evidence that the physicist performed an end of treatment check within one week? 
			   		wd.findElement(By.id("Section1_q2_4_rb_"+i)).click();
			   		
			   		//Chart and Physics Documentation Comments
			   		wd.findElement(By.id("cphMaster_cphDataCollection_Section1_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
			   		jse.executeScript("window.scrollBy(0,300)", "");
			   		
			   		//Rating Scale
			   		wd.findElement(By.id("ChartLikeRating_rb_2")).click(); //Compliant=3
			   		
			   		//**************Chart and Physics Documentation*****************
					wd.findElement(By.id("ui-accordion-accordion-header-1")).click();
					Thread.sleep(300);
					jse.executeScript("window.scrollBy(0,300)", "");
			   		
			   		//5. 	Was there documentation of a simulation? 
			   		wd.findElement(By.id("Section2_q5_rb_"+i)).click();
			   		
			   		String s="string"+i;
			   		if(s.equals("string0"))
			   		{
			   			Select drop = new Select(wd.findElement(By.id("Section2_q4_ddl")));
						drop.selectByVisibleText("Conventional Simulation");
			   		}
			   		
			   		//6 	A. Was there an immobilization device(s) used? 
			   		wd.findElement(By.id("Section2_q6a_rb_0")).click();
			   		
			   		//B. Immobilization device documented
			   		wd.findElement(By.id("cphMaster_cphDataCollection_Section2_q3_ch_"+i)).click();	
			   		
			   		//Simulation Comments
			   		wd.findElement(By.id("cphMaster_cphDataCollection_Section2_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
			   		jse.executeScript("window.scrollBy(0,300)", "");
			   		
			   		//Rating Scale
			   		wd.findElement(By.id("TreatmentLikeRating_rb_2")).click();
			   		
			   		//*************************Treatment Planning ***********************
			   		wd.findElement(By.id("ui-accordion-accordion-header-2")).click();
					jse.executeScript("window.scrollBy(0,300)", "");
					
					// 	a. Are calculation/isodose distribution present in chart? 
					wd.findElement(By.id("Section3_q5_1_rb_"+i)).click();
					
					// 	b. Are calculation/isodose distribution signed and dated by physician? 
					wd.findElement(By.id("Section3_q5_2_rb_"+i)).click();
					
					// 	c. Are calculation/isodose distribution signed and dated by physicist? 
					wd.findElement(By.id("Section3_q5_3_rb_"+i)).click();
					
					//8. 	Is there documentation of heterogeneity corrections? 
					wd.findElement(By.id("Section3_q6_rb_"+i)).click();
					
					//9. 	What algorithm are you using? 
					wd.findElement(By.id("cphMaster_cphDataCollection_Section3_q7_ch_0")).click();//BATHO
					
					// 	a. Organs at risk (OAR) 
					wd.findElement(By.id("Section3_q9_1_rb_"+i)).click();
					
					//b. Planning Target Volume (PTV) 
					wd.findElement(By.id("Section3_q9_2_rb_"+i)).click();
					jse.executeScript("window.scrollBy(0,300)", "");
					// 	Is there evidence of an independent check of plan and calculations by medical physicist? 
					wd.findElement(By.id("Section3_q10_rb_"+i)).click();
					
					//Treatment Planning Comments
					wd.findElement(By.id("cphMaster_cphDataCollection_Section3_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					jse.executeScript("window.scrollBy(0,300)", "");
					
					//Rating Scale
					wd.findElement(By.id("IsodoseLikeRating_rb_2")).click();
					jse.executeScript("window.scrollBy(0,500)", "");
					//*********************Modalities**********************
					wd.findElement(By.id("ui-accordion-accordion-header-3")).click();
					Thread.sleep(300);
					
					
					for(int k=0;k<=0;k++)
					{
					wd.findElement(By.id("cphMaster_cphDataCollection_Section4_ch_"+k)).click();
					}
					jse.executeScript("window.scrollBy(0,50)", "");
					 //*******************************IMRT tab****************************
					wd.findElement(By.id("ui-id-3")).click();
					Thread.sleep(300);
					//1. 	Type of Treatment
					Select drop= new Select(wd.findElement(By.id("IMRTV3_Q1_ddl")));
					drop.selectByVisibleText("Tomotherapy");
					jse.executeScript("window.scrollBy(0,300)", "");
					//2. 	Are dose volume constraints documented by the Radiation Oncologist?
					wd.findElement(By.id("IMRTV3_Q2_rb_"+i)).click();
					
					//Was the accuracy of dose distribution documented by QA performed prior to treatment?
					wd.findElement(By.id("IMRTV3_Q3_rb_"+i)).click();
					
					//A. 	What is the gamma % passing? 
					wd.findElement(By.id("cphMaster_cphDataCollection_PHY_IMRTV3_IMRTV3_QA_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					
					//B. 	What is the dose difference (if absolute dose is measured)?
					wd.findElement(By.id("cphMaster_cphDataCollection_PHY_IMRTV3_IMRTV3_QB_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					
					//C. 	If calculated, what is the Distance to Agreement? 
					wd.findElement(By.id("cphMaster_cphDataCollection_PHY_IMRTV3_IMRTV3_QC_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					
					//IMRT Comments
					wd.findElement(By.id("cphMaster_cphDataCollection_PHY_IMRTV3_IMRTV3_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					jse.executeScript("window.scrollBy(0,300)", "");
					
					//Rating Scale 
					wd.findElement(By.id("IMRTV3LikeRating_rb_2")).click();
					
					jse.executeScript("window.scrollBy(0,-600)", "");
					
					/*//***********************SBRT tab**************************
					
					wd.findElement(By.id("ui-id-4")).click();
					Thread.sleep(300);
					//1. 	What is the device used for SBRT treatment? 
					Select drop1= new Select(wd.findElement(By.id("SBRTV3_Q1_ddl")));
					drop1.selectByVisibleText("Cyberknife");
					
					//2. 	Was a immobilization device used for treatment?
					wd.findElement(By.id("SBRTV3_Q2_rb_"+i)).click();
					jse.executeScript("window.scrollBy(0,300)", "");
					
					//3. 	Was patient specific QA performed prior to treatment?
					wd.findElement(By.id("SBRTV3_Q3_rb_"+i)).click();
					
					//4. 	Were the physician and physicist present onsite during imaging and treatment? 
					wd.findElement(By.id("SBRTV3_Q4_rb_"+i)).click();
					
					//SBRT Comments
					wd.findElement(By.id("cphMaster_cphDataCollection_PHY_SBRTV3_SBRTV3_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					jse.executeScript("window.scrollBy(0,300)", "");
					
					//Rating Scale 
					wd.findElement(By.id("SBRTV3LikeRating_rb_2")).click();
					
					jse.executeScript("window.scrollBy(0,-400)", "");
					
					//****************** SRS tab ************************
					
					wd.findElement(By.id("ui-id-5")).click();
					Thread.sleep(300);
					//1. 	What is the device used for SRS treatment?
					Select drop2= new Select(wd.findElement(By.id("SRSV3_Q1_ddl")));
					drop2.selectByVisibleText("Cyberknife");
					
					//2. 	Was a Winston-Lutz test performed prior to the patient treatment?
					wd.findElement(By.id("SRSV3_Q2_rb_"+i)).click();
					
					//3. 	Were the physician and physicist present during imaging and treatment?
					wd.findElement(By.id("SRSV3_Q3_rb_"+i)).click();
					
					//4. 	Which immobilization system was used for SRS treatment 
					Select drop3= new Select(wd.findElement(By.id("cphMaster_cphDataCollection_PHY_SRSV3_SRSV3_Q4_ddl")));
					drop3.selectByVisibleText("Neurosurgical frame");
					
					//5. 	When fusion is performed, did the radiation oncologist request an order?
					wd.findElement(By.id("SRSV3_Q5_rb_"+i)).click();
					
					//SRS Comments
					wd.findElement(By.id("cphMaster_cphDataCollection_PHY_SRSV3_SRSV3_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					jse.executeScript("window.scrollBy(0,300)", "");
					
					//Rating Scale 
					wd.findElement(By.id("SRSV3LikeRating_rb_2")).click();
					
					jse.executeScript("window.scrollBy(0,-300)", "");
					
					//****************** Proton tab ************************
					
					wd.findElement(By.id("ui-id-6")).click();
					Thread.sleep(300);
					//1. 	What type of machine was used? 
					Select drop4= new Select(wd.findElement(By.id("ProtonV3_Q3_1_ddl")));
					drop4.selectByVisibleText("Cyclotron");
					
					//2. 	Were patient specific devices such as aperture and compensators used?
					wd.findElement(By.id("ProtonV3_Q3_2_rb_"+i)).click();
					
					//3. 	Is there documentation that a physician and physicist were present during imaging and treatment?
					wd.findElement(By.id("ProtonV3_Q3_3_rb_"+i)).click();
					
					//4. 	Were calculations verified by an additional and independent method prior to treatment?
					wd.findElement(By.id("ProtonV3_Q7_2_rb_"+i)).click();
					
					//5. 	Was patient specific QA performed prior to treatment?
					wd.findElement(By.id("ProtonV3_Q7_3_rb_"+i)).click();
					
					//Proton Comments
					wd.findElement(By.id("cphMaster_cphDataCollection_PHY_ProtonV3_ProtonV3_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					jse.executeScript("window.scrollBy(0,300)", "");
					
					//Rating Scale 
					wd.findElement(By.id("ProtonV3LikeRating_rb_2")).click();
					
					jse.executeScript("window.scrollBy(0,-300)", "");
					
					//****************Brachytherapy tab******************
					
					wd.findElement(By.id("ui-id-7")).click();
					Thread.sleep(300);
					//1. 	Is there a written directive signed and dated by the radiation oncologist prior to treatment? 
					wd.findElement(By.id("Brachyteraphy_q1_rb_"+i)).click();
					
					//2. 	Does it include an anatomic description of treatment area? 
					wd.findElement(By.id("Brachyteraphy_q2_rb_"+i)).click();

					//3. 	What isotope was used for treatment? 
					Select drop5= new Select(wd.findElement(By.id("Brachyteraphy_q3_ddl")));
					drop5.selectByVisibleText("I125");
					
					//4. 	Was the patient identified as required by the NRC or state?
					wd.findElement(By.id("Brachyteraphy_q4_rb_"+i)).click();
					
					//5. 	Is there evidence of a post-treatment radiation survey in the patient records?
					wd.findElement(By.id("Brachyteraphy_q5_rb_"+i)).click();
					
					// 	Were calculations verified by an additional and independent method prior to treatment?
					wd.findElement(By.id("Brachyteraphy_q6_rb_"+i)).click();
					
					//7. 	Type of Treatment 
					
					for(int j=0;j<=2;j++)
					{
						wd.findElement(By.id("cphMaster_cphDataCollection_PHY_BrachyteraphyV3_Brachytherapy_b_ch_"+j)).click();
					}
					
					//**************** LDR tab******************
					
					wd.findElement(By.id("ui-id-9")).click();
					Thread.sleep(300);
					// 	Was some type of QA performed on the applicator(s) before treatment?  
					wd.findElement(By.id("LDRv3_Q1_rb_"+i)).click();
					
					// 	Was there some type of imaging device used to perform the placement of the application for the treatment 
					wd.findElement(By.id("LDRv3_Q2_rb_"+i)).click();
					
					// 	Was the prescribed loading of applicators independently confirmed? 
					wd.findElement(By.id("LDRv3_Q3_rb_"+i)).click();
					String browser=prop.getProperty("Browser");
					  if(browser.equalsIgnoreCase("IE")){
					jse.executeScript("window.scrollBy(0,100)", "");
					  }
					//4. 	Were radiation safety precautions demonstrated?
					wd.findElement(By.id("LDRv3_Q4_rb_"+i)).click();
					
					//Was there documentation that the Radiation Oncologist and Medical Physicist were present during treatment? 
					wd.findElement(By.id("LDRv3_Q5_rb_"+i)).click();
					
					//LDR Comments
					wd.findElement(By.id("cphMaster_cphDataCollection_PHY_BrachyteraphyV3_PHY_LDRV3_LDRV3_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					jse.executeScript("window.scrollBy(0,300)", "");
					
					//Rating Scale 
					wd.findElement(By.id("LDRV3LikeRating_rb_2")).click();
					
					jse.executeScript("window.scrollBy(0,-300)", "");
					
					//****************HDR  tab******************
					
					wd.findElement(By.id("ui-id-10")).click();
					Thread.sleep(300);
					//1. 	Was pre-treatment QA of unit performed?
					wd.findElement(By.id("HDRv3_Q1_rb_"+i)).click();
					
					//2. 	Is there evidence of a pre-treatment patient radiation survey in the patient records?
					wd.findElement(By.id("HDRv3_Q2_rb_"+i)).click();
					
					//Was there documentation that correct applicator was verified before each treatment? (e.g. size or dimension) 
					wd.findElement(By.id("HDRv3_Q3_rb_"+i)).click();
					
					// 	Was there documentation that the Radiation Oncologist and Medical Physicist were present during treatment
					wd.findElement(By.id("HDRv3_Q4_rb_"+i)).click();
					
					//HDR Comments
					wd.findElement(By.id("cphMaster_cphDataCollection_PHY_BrachyteraphyV3_PHY_HDRV3_HDRV3_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					jse.executeScript("window.scrollBy(0,300)", "");
					
					//Rating Scale 
					wd.findElement(By.id("HDRV3LikeRating_rb_2")).click();
					
					jse.executeScript("window.scrollBy(0,-300)", "");
					
					//**************** Seed Implant  tab******************
					
					wd.findElement(By.id("ui-id-11")).click();
					Thread.sleep(300);
					//1. 	Was a pre-planning Ultrasound performed for volume determination?
					wd.findElement(By.id("SeedImplantV3_Q1_rb_"+i)).click();
					
					//2. 	Was the seed calibration verified onsite? 
					wd.findElement(By.id("SeedImplantV3_Q2_rb_"+i)).click();
					
					// 	A. Intended prescription dose
					wd.findElement(By.id("SeedImplantV3_QA_rb_"+i)).click();
					
					// 	B. Actual total dose (D90)
					wd.findElement(By.id("SeedImplantV3_QC_rb_"+i)).click();
					jse.executeScript("window.scrollBy(0,100)", "");
					
					// 	Was there documentation that the Radiation Oncologist and Medical Physicist were present during treatment?
					wd.findElement(By.id("SeedImplantV3_Q4_rb_"+i)).click();
					
					//Seed Implant Comments
					wd.findElement(By.id("cphMaster_cphDataCollection_PHY_BrachyteraphyV3_PHY_SeedImplantV3_SeedImplantV3_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					jse.executeScript("window.scrollBy(0,300)", "");
					
					//Rating Scale 
					wd.findElement(By.id("SeedImplantV3LikeRating_rb_2")).click();
					
					jse.executeScript("window.scrollBy(0,-300)", "");
					
					//*********************2D/3D **********************
					
					wd.findElement(By.id("ui-id-8")).click();
					Thread.sleep(300);
					//2D/3D Comments 
					wd.findElement(By.id("cphMaster_cphDataCollection_PHY_2D3DV3_TD3D_comments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					jse.executeScript("window.scrollBy(0,300)", "");
					jse.executeScript("window.scrollBy(0,700)", "");
					//Rating Scale 
					wd.findElement(By.id("TD3DLikeRating_rb_2")).click();*/
					
					jse.executeScript("window.scrollBy(0,1500)", "");
					
					wd.findElement(By.id("cphMaster_cphDataCollection_next_btn")).click();
					Thread.sleep(300);
					log.info("End of Physicist  Data Collection");
					
			}//v loop
			
			}//try
			catch (Exception a) {
				// TODO: handle exception
				log.error(a);
			}

		}//method
					//*********************Physician Final Report V3*********************
	@Test(priority=15)
			public void physician_final_report() throws InterruptedException{
				try{
				
					wd.findElement(By.id("cphMaster_Next")).click(); //next
					Thread.sleep(300);
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
				
			}
	catch (Exception e) {
		// TODO: handle exception
		log.error(e);
	}
				 
				 	//*************************next phy login**********************
		    		Long n=Math.round(sh.getRow(1).getCell(155).getNumericCellValue());
		    		int phy =n.intValue();
		    		
		    		if(phy>1){
		    			
		    			wd.navigate().to(prop.getProperty("QA_URL"));
		    			Ropa_pom.login("bmonzon@acr.org", "Welcome1!");
			  			log.info("login with md2");
			  			
		    			//Change Role:
						Select dropdown = new Select(wd.findElement(By.id("ddrROles")));
						dropdown.selectByVisibleText("Physicist Surveyor");
					
						Ropa_pom.view_assigned_Application("cphMaster_btnAppliAssgndtoMe");	
							
							//opening from left side
							wd.findElement(By.linkText("Physicist DC Forms")).click();
							
							wd.findElement(By.linkText("Physicist Data Collection")).click();
							Thread.sleep(300);
							
						//*******************Physicist  Data Collection*******************
					
					List<WebElement> count=wd.findElements(By.linkText("View"));
			    	num=count.size();
					
					//***loop for multiple views***
					for(  int v=0;v<=num-1;v++)
					{
						Long site =Math.round(sh.getRow(1).getCell(149).getNumericCellValue());
						int i =site.intValue();
				//0=yes
				//1=Recommendation
				//2-no
						if(v==1){
							i=2;
						}
						
						if(v>6)
						{
						jse.executeScript("window.scrollBy(0,1500)", "");
						}
						wd.findElement(By.id("cphMaster_grdcases_lnkEdit_"+v)).click();//view
						Thread.sleep(300);
						log.info("Physicist  Data Collection");
						Thread.sleep(300);
						
						//*******************Physicist  Data Collection*******************
						wd.findElement(By.id("cphMaster_cphDataCollection_top_next_btn")).click();//next

		    		}//v

					wd.findElement(By.id("cphMaster_Next")).click(); //next
					Thread.sleep(300);
					log.info("Physician Final Report V3");
				
					//***********************Physician Final Report V3****************************
					
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
			   		wd.quit();

			}//phy
		    		else{
		    			wd.quit();
		    		}


	
}
						
}//class
