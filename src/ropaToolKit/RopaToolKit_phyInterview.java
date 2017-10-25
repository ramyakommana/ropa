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
* 3:37:28 PM
 */
public class RopaToolKit_phyInterview extends Ropa_pom{

	@Test(priority=11)
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
}//method
	
	@Test(priority=12)
		public void physicist_Interview_forms() throws InterruptedException{
	
		//opening toolkit from left side
		wd.findElement(By.linkText("ROPA Tool Kit")).click();
		wd.findElement(By.linkText("Physicist Interview")).click();
		Thread.sleep(300);
		//***********************************Physicist Interview*********************************
		log.info("Ropatoolkit: Physicist Interview");
		 //assigning for options
		Long site =Math.round(sh.getRow(1).getCell(149).getNumericCellValue());
		int i =site.intValue();
		
		String s="string"+i;
		// 	Do you have an ADCL Instrumentation calibration report that was performed within the last 2 years? 
		wd.findElement(By.id("PHYINTV3_1A_rb_"+i)).click();
		 JavascriptExecutor jse = (JavascriptExecutor)wd;
		jse.executeScript("window.scrollBy(0,200)", "");
		
		// 	Do you have a second independent dosimetry system?
		wd.findElement(By.id("PHYINTV3_1B_rb_"+i)).click();
		
		if(s.equals("string0"))
		{
			// 	If yes, do you have records of periodic cross checks against the calibrated system?*
			wd.findElement(By.id("PHYINTV3_1B_i_rb_"+i)).click();
		}
		
		// 	Do you have a daily output constancy/flatness & symmetry device? 
		wd.findElement(By.id("PHYINTV3_1C_rb_"+i)).click();
		
		// 	Do you have access to patient dosimetry devices (e.g. diodes/MOSFET/TLD/OSLD)?
		wd.findElement(By.id("PHYINTV3_1D_rb_"+i)).click();
		
	 	//Do you have access to a water tank scanning system for annual calibrations? 
		wd.findElement(By.id("PHYINTV3_1E_rb_"+i)).click();
		 jse.executeScript("window.scrollBy(0,100)", "");
		//Do you have QA phantoms (e.g. plastic water, 1D water tank, specialty phantom)?
		wd.findElement(By.id("PHYINTV3_1F_rb_"+i)).click();
		
		// 	Do you have QA devices (e.g. IMRT, IGRT, CT Sim, SRS, Film)?
		wd.findElement(By.id("PHYINTV3_1G_rb_"+i)).click();
		
		// 	Do you have documentation of periodic barometer/thermometer calibration checks/intercomparisons?
		wd.findElement(By.id("PHYINTV3_1H_rb_"+i)).click();
		
		// 	If Brachytherapy is performed, do you have source calibration instrumentation? (e.g. electrometer and well chamber) 
		wd.findElement(By.id("PHYINTV3_1I_rb_"+i)).click();
		if(s.equals("string0"))
		{
			// 	If yes, do you have records of ADCL instrument calibration performed within the last 2 years? 
			wd.findElement(By.id("PHYINTV3_1I_i_rb_"+i)).click();
		}
		
		// 	Do you have a survey meter(s)?
		wd.findElement(By.id("PHYINTV3_1J_rb_"+i)).click();
		if(s.equals("string0"))
		{
			// 	If yes, do you have records of instrument calibration performed within the last year?  
			wd.findElement(By.id("PHYINTV3_1J_i_rb_"+i)).click();
		}
		
		//Instrumentation Comments
		 wd.findElement(By.id("cphMaster_FSAPhysicist_PHYINTV3_1_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
		 jse.executeScript("window.scrollBy(0,300)", "");
		 
		//Rating Scale
		 wd.findElement(By.id("InstrumentationLikeRating_rb_2")).click();
		
		 jse.executeScript("window.scrollBy(0,300)", "");
		 
		 //Do you have records of QA performed on the simulator/CT-simulator conforming to the current guidelines of the AAPM (TG-66)? 
		 wd.findElement(By.id("PHYINTV3_2A_rb_"+i)).click();
		 
		 //Do you have the Acceptance Testing and Commissioning Report(s) conforming to the current AAPM guidelines? 
		 wd.findElement(By.id("PHYINTV3_2B_rb_"+i)).click();
		 
		 //Do you have records of daily QA including output constancy checks? 
		 wd.findElement(By.id("PHYINTV3_2C_rb_"+i)).click();
		 
		 //Do you have records of monthly QA including output constancy checks as per AAPM/International guidelines? 
		 wd.findElement(By.id("PHYINTV3_2D_rb_"+i)).click();
		 
		 //Do you have the most recent Annual Calibration Report(s) following current AAPM/International guidelines? 
		 wd.findElement(By.id("PHYINTV3_2E_rb_"+i)).click();
		 
		 //Do you have an Independent Physicist calibration and/or RPC OSLD/TLD report performed within the last year? 
		 wd.findElement(By.id("PHYINTV3_2F_rb_"+i)).click();
		 
		 //7.On board imaging 
		 wd.findElement(By.id("PHYINTV3_Q2G_A_rb_"+i)).click();
		 //Cone Beam CT (CBCT) 
		 wd.findElement(By.id("PHYINTV3_Q2G_B_rb_"+i)).click();
		 //Respiratory Gating
		 wd.findElement(By.id("PHYINTV3_Q2G_C_rb_"+i)).click();
		 //Multi-leaf collimators (MLC)
		 wd.findElement(By.id("PHYINTV3_Q2G_D_rb_"+i)).click();
		
		 //Do you have periodic safety performance checks of all equipment used for patient setup and treatment (e.g. breast boards, immobilization devices, wedges, trays, etc.)? 
		 wd.findElement(By.id("PHYINTV3_2H_rb_"+i)).click();
		 
		 //Are there machine service and maintenance logs available and are they reviewed by the physicist?
		 wd.findElement(By.id("PHYINTV3_2I_rb_"+i)).click();
		 jse.executeScript("window.scrollBy(0,100)", "");
		 //10.Do you perform Proton Therapy? 
		 wd.findElement(By.id("PHYINTV3_Q11_rb_0")).click();
		 
		 // A. Do you perform daily QA 
		 wd.findElement(By.id("PHYINTV3_Q11A_rb_0")).click();
		 
		 //i. 	Beam Dosimetry Parameters 
		 wd.findElement(By.id("PHYINTV3_Q11A_ai_rb_"+i)).click();
		 
		 //ii. 	Patient setup verification
		 wd.findElement(By.id("PHYINTV3_Q11A_aii_rb_"+i)).click();
		 
		 //iii.  Communication 
		 wd.findElement(By.id("PHYINTV3_Q11A_aiii_rb_"+i)).click();
		 jse.executeScript("window.scrollBy(0,300)", "");
		 
		 //iv. 	Safety 
		 wd.findElement(By.id("PHYINTV3_Q11A_aiv_rb_"+i)).click();
		 
		 //B. 	Do you perform weekly QA 
		 wd.findElement(By.id("PHYINTV3_Q11B_rb_0")).click();
		 
		 //i. 	Gantry angle vs gantry angle indicators
		 wd.findElement(By.id("PHYINTV3_Q11B_ai_rb_"+i)).click();
		 
		 //ii. 	Snout or applicator extensions
		 wd.findElement(By.id("PHYINTV3_Q11B_aii_rb_"+i)).click();
		 
		 //iii. Collision interlocks
		 wd.findElement(By.id("PHYINTV3_Q11B_aiii_rb_"+i)).click();
		 
		 //iv. 	Imaging Systems 
		 wd.findElement(By.id("PHYINTV3_Q11B_aiv_rb_"+i)).click();
		 
		 //v. 	Respiratory gating equipment
		 wd.findElement(By.id("PHYINTV3_Q11B_av_rb_"+i)).click();
		 jse.executeScript("window.scrollBy(0,100)", "");
		 //C.	Do you perform monthly QA
		 wd.findElement(By.id("PHYINTV3_Q11C_rb_0")).click();
		 
		 //i. 	Beam dosimetry parameters
		 wd.findElement(By.id("PHYINTV3_Q11C_ai_rb_"+i)).click();
		 
		 //ii. 	Mechanical checks
		 wd.findElement(By.id("PHYINTV3_Q11C_aii_rb_"+i)).click();
		 
		 //Multi-leaf collimator
		 wd.findElement(By.id("PHYINTV3_Q11C_aiii_rb_"+i)).click();
		 jse.executeScript("window.scrollBy(0,200)", "");
		 //D. 	Do you perform annual QA
		 wd.findElement(By.id("PHYINTV3_Q11D_rb_0")).click();
		 
		 // i. Beam dosimetry parameters 
		 wd.findElement(By.id("PHYINTV3_Q11D_ai_rb_"+i)).click();
		 
		 //ii. 	Mechanical
		 wd.findElement(By.id("PHYINTV3_Q11D_aii_rb_"+i)).click();
		 
		 //iii. 	Imaging devices
		 wd.findElement(By.id("PHYINTV3_Q11D_aiii_rb_"+i)).click();
		 
		 //iv. 	Safety checks
		 wd.findElement(By.id("PHYINTV3_Q11D_aiv_rb_"+i)).click();
		 
		 //v. 	Visual Inspections 
		 wd.findElement(By.id("PHYINTV3_Q11D_av_rb_"+i)).click();
		 jse.executeScript("window.scrollBy(0,300)", "");
		 
		//E. 	Is protocol TRS 398 performed? 
		 wd.findElement(By.id("PHYINTV3_Q11E_rb_0")).click();
		 
		 //F. 	Is the site reviewed by RPC or iROC Houston for calibration and QA?
		 wd.findElement(By.id("PHYINTV3_Q11F_rb_"+i)).click();
		 
		 //G. 	Do you use a proton specific treatment planning system? 
		 wd.findElement(By.id("PHYINTV3_Q11G_rb_0")).click();
		 
		 //a. 	If yes, do you perform commissioning for your treatment planning system?
		 wd.findElement(By.id("PHYINTV3_Q11G_i_rb_"+i)).click();
		 
		 //H. 	Following staff have documented training to perform proton therapy treatment:
		 // 	i. 	Radiation Oncologist
		 wd.findElement(By.id("PHYINTV3_Q11H_i_rb_"+i)).click();
		 
		 //ii. 	Medical Physicists
		 wd.findElement(By.id("PHYINTV3_Q11H_ii_rb_"+i)).click();
		 
		 //iii. 	Dosimetrist
		 wd.findElement(By.id("PHYINTV3_Q11H_iii_rb_"+i)).click();
		 
		 //iv. 	Radiation Therapist
		 wd.findElement(By.id("PHYINTV3_Q11H_iv_rb_"+i)).click();
	
		 //Simulation/Treatment Machine/Quality Assurance Comments
		 wd.findElement(By.id("cphMaster_FSAPhysicist_PHYINTV3_2_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
		 jse.executeScript("window.scrollBy(0,200)", "");
		 //Rating Scale
		 wd.findElement(By.id("SimulationLikeRating_rb_2")).click();
		 jse.executeScript("window.scrollBy(0,300)", "");
		 
		 //Is treatment planning data obtained or confirmed on-site by direct measurement? 
		 wd.findElement(By.id("PHYINTV3_3A_rb_"+i)).click();
		 
		 //Do you have an Acceptance/Commissioning report for the treatment planning system(s) including IMRT planning evaluation? 
		 wd.findElement(By.id("PHYINTV3_3B_rb_"+i)).click();
		 
		 //Do you have periodic/annual check of the treatment planning system(s)?
		 wd.findElement(By.id("PHYINTV3_3C_rb_"+i)).click();
		 
		 //Do you have records of QA evaluations following hardware/software updates? 
		 wd.findElement(By.id("PHYINTV3_3D_rb_"+i)).click();
		 
		 //Do you have evidence of initial and continuing staff training for the treatment planning system(s)? 
		 wd.findElement(By.id("PHYINTV3_3E_rb_"+i)).click();
		 
		 //TREATMENT PLANNING (External and Brachytherapy) Comments
		 wd.findElement(By.id("cphMaster_FSAPhysicist_PHYINTV3_3_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
		 jse.executeScript("window.scrollBy(0,200)", "");
		//Rating Scale 
		 wd.findElement(By.id("TreatmentPlanningLikeRating_rb_2")).click();
		 jse.executeScript("window.scrollBy(0,300)", "");
		 
		 //For external beam and temporary brachytherapy (i.e. HDR) treatments, do you have an independent MU/time calculation? 
		 wd.findElement(By.id("PHYINTV3_4A_rb_"+i)).click();
		 
		 if(s.equals("string0"))
		 {
			// If yes, is this calculation performed prior to initiation of treatment? *
			 wd.findElement(By.id("PHYINTV3_4A_i_rb_"+i)).click();
		 }
		 
		 //Do you have a policy and procedure related to hypofractionation treatment (greater than 300 cGy/fraction)? 
		 wd.findElement(By.id("PHYINTV3_4B_rb_"+i)).click();
		 
		 //For the solo physicist, do you have a AAPM TG-103 or equivalent physics peer review program in place?
		 wd.findElement(By.id("PHYINTV3_4C_rb_"+i)).click();
		 
		 // Do you have a physics policy and procedure manual that describes all aspects of the physics program
		 wd.findElement(By.id("PHYINTV3_4D_rb_"+i)).click();
		 
		 //Do you have policies and procedures regarding new equipment evaluation and assessment? 
		 wd.findElement(By.id("PHYINTV3_4E_rb_"+i)).click();
		 jse.executeScript("window.scrollBy(0,300)", "");
		 //Does the physicist participate in QA meetings and report on QA activities? 
		 wd.findElement(By.id("PHYINTV3_4F_rb_"+i)).click();
		 
		 //Are output measurements done for custom electron cutouts? 
		 wd.findElement(By.id("PHYINTV3_4G_rb_"+i)).click();
		 
		 //GENERAL QUALITY ASSURANCE Comments
		 wd.findElement(By.id("cphMaster_FSAPhysicist_PHYINTV3_4_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
		 jse.executeScript("window.scrollBy(0,900)", "");
		//Rating Scale
		 wd.findElement(By.id("GenQtyAssuranceLikeRating_rb_2")).click();
		
		 
		 wd.findElement(By.id("cphMaster_FSAPhysicist_btnSave")).click();//save
		 Thread.sleep(500);
		 log.info("End of Ropatoolkit: Physicist Interview");
		 
		 Ropa_pom.logout();
 		log.info("Logout");
 		
 		
}//method
	
	
	
	
	
	
}	
