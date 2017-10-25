package ropa_mdforms;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import page_objects.Ropa_pom;

/**
 * @author K RamyaSree
 *
 * QA_Ropa
 * 
* April 5, 2017
* 10:45:53 AM
 */

public class Md_OnsiteForms extends Ropa_pom{
	
	public static String s;
	public static String user;
	public static String password;
	
	@Test(priority=7)
	  public void MD_onsiteInterviewForms() throws InterruptedException,ClassNotFoundException{
			
		try{
					Ropa_pom.configuration();
					// Login
					Ropa_pom.login(sh.getRow(1).getCell(102).getStringCellValue(), sh.getRow(1).getCell(103).getStringCellValue());
					log.info("Login as MD");
					//Change Role:
					Select dropdown = new Select(wd.findElement(By.id("ddrROles")));
					dropdown.selectByVisibleText("MD Surveyor");
				
					Ropa_pom.view_assigned_Application("cphMaster_btnAppliAssgndtoMe");
					
					//opening Onsite Interview Forms from left side
					
					wd.findElement(By.linkText("Onsite Interview Forms")).click();
					//Onsite Interview Page 1
					
					wd.findElement(By.linkText("Onsite Interview Page 1")).click();
					Thread.sleep(300);
					//*********************************Onsite Interview Page1*********************************** 
					log.info("Onsite Inteview page1");
					Long site =Math.round(sh.getRow(1).getCell(149).getNumericCellValue());
					int i =site.intValue();
			//0=yes
			//1=Recommendation
			//2-no
					
					//1. 	Do you offer your patients a psycho-social assessment 
	                wd.findElement(By.id("Q1_rb_1")).click();  //recommendation
					wd.findElement(By.id("cphMaster_cphAstro_txt_Consultseccomments")).sendKeys("text");
					
					jse.executeScript("window.scrollBy(0,700)", "");
					
					//2. 	Do you have a time out policy and procedure for simulation? 
					wd.findElement(By.id("Q2_rb_"+i)).click(); //recommendation
					
					//3.Do you use contrast? 
					
					wd.findElement(By.id("cphMaster_cphAstro_Q4_rb_0")).click(); //yes
					//Who screens the patient *
					wd.findElement(By.id("Q4_ch_"+i)).click(); //Radiation Therapist
					
					jse.executeScript("window.scrollBy(0,700)", "");
					//What do you screen for?
					// i. GFR *
					wd.findElement(By.id("cphMaster_cphAstro_Q4Bi_rb_"+i)).click(); //yes
					//Allergies *
					wd.findElement(By.id("cphMaster_cphAstro_Q4Bii_rb_"+i)).click(); //recommendation
					// Creatinine *
					wd.findElement(By.id("cphMaster_cphAstro_Q4Biii_rb_"+i)).click(); //recommendation
					//Contrast within 24 plus hours*
					wd.findElement(By.id("cphMaster_cphAstro_Q4Biv_rb_"+i)).click(); //yes
					
					jse.executeScript("window.scrollBy(0,700)", "");
					//4.Is there a formal written simulation order used in the department for the following: 
					
					// IV contrast   (Surveyor to confirm on Checklist Section) 
					wd.findElement(By.id("Q3A_rb_"+i)).click(); //yes
					//. Oral contrast   (Surveyor to confirm on Checklist Section) 
					wd.findElement(By.id("cphMaster_cphAstro_Q3B_rb_"+i)).click(); //recommendation
					// Treatment site 
					wd.findElement(By.id("cphMaster_cphAstro_Q3C_rb_"+i)).click(); //recommendation
					//Patient position 
					wd.findElement(By.id("cphMaster_cphAstro_Q3D_rb_"+i)).click(); //recommendation
					//Immobilization device 
					wd.findElement(By.id("cphMaster_cphAstro_Q3E_rb_"+i)).click(); //doesnot apply
					
					jse.executeScript("window.scrollBy(0,700)", "");
					//Simulation Comments: 
					wd.findElement(By.id("cphMaster_cphAstro_txt_SimulSecComments")).sendKeys(sh.getRow(1).getCell(122).getStringCellValue());
					
					jse.executeScript("window.scrollBy(0,700)", "");
					wd.findElement(By.id("cphMaster_btnNext")).click();//next
					log.info("End of page1");
					Thread.sleep(300);
                    //*******************************Onsite Interview Page 2****************************
					
					log.info("Onsite Interview Page 2");
					//wd.findElement(By.linkText("Onsite Interview Page 2")).click();
					
					//Once the simulation is completed, does the radiation oncologist draw the target, GTV, and CTV? 
					wd.findElement(By.id("cphMaster_cphAstro_Q6_rb_"+i)).click();//yes
					
					//Does the radiation oncologist review the Organs At Risk (OAR) if someone else contours them? 
					wd.findElement(By.id("cphMaster_cphAstro_Q7_rb_"+i)).click();//recommendation
					
					//For IMRT patients, is there a written order for dose volume constraints prescribed by the Radiation Oncologist?
					wd.findElement(By.id("cphMaster_cphAstro_Q8_rb_"+i)).click();//recommendation
					
					// 	Is there a formal Physician peer review of dose volume constraints? 
					wd.findElement(By.id("cphMaster_cphAstro_Q9A_rb_"+i)).click();//recommendation
					
					//Is there a formal Physician peer review of target volumes and organs at risk (segmentation)? 
					wd.findElement(By.id("cphMaster_cphAstro_Q9B_rb_"+i)).click();//yes
					jse.executeScript("window.scrollBy(0,500)", "");
					//Does the Radiation Oncologist review and approve the treatment plan prior to first delivery? 
					wd.findElement(By.id("Q13_rb_"+i)).click();//does not apply
					
					// 	Does the radiation oncologist sign/date the prescription before the first treatment? 
					wd.findElement(By.id("cphMaster_cphAstro_Q14_rbl_"+i)).click(); //yes
					
					// 	Is a radiation oncologist within the radiation oncology department during treatment? 
					wd.findElement(By.id("cphMaster_cphAstro_Q12_rb_"+i)).click(); //recommendation
					
					//Treatment Planning Comments:
					wd.findElement(By.id("cphMaster_cphAstro_txt_PlannningseSectionCommnts")).sendKeys(sh.getRow(1).getCell(131).getStringCellValue());
					
					//Patient Treatment
					//A. Do you have a policy that requires you to perform a time out prior to each treatment?
				      wd.findElement(By.id("cphMaster_cphAstro_Q16A_rb_"+i)).click();
				      jse.executeScript("window.scrollBy(0,3000)", "");
				      // If yes, do you check for correct patient, correct site, correct energy and accessories (if any)
				      wd.findElement(By.id("cphMaster_cphAstro_Q16B_rb_"+i)).click();
				      
				      // Is it documented? 
				      wd.findElement(By.id("cphMaster_cphAstro_Q16C_rb_"+i)).click();
				     
				        
				      //Do you have a policy for emergency treatment of patients after business hours? 
				      wd.findElement(By.id("cphMaster_cphAstro_Q17_rb_"+i)).click();
				
					//Patient Treatment Comments:
				      wd.findElement(By.id("cphMaster_cphAstro_txt_PatientTreatmentSectionComments")).sendKeys(sh.getRow(1).getCell(132).getStringCellValue());
				
				      jse.executeScript("window.scrollBy(0,700)", "");
						wd.findElement(By.id("cphMaster_btnNext")).click();//next
						log.info("End of Onsite Interview Page 2");
						Thread.sleep(300);
						 //*****************************Onsite Interview Page 3********************************
                       
						log.info("Onsite Interview Page 3");
					    //Is there a policy for obtaining patient imaging? 
						 wd.findElement(By.id("cphMaster_cphAstro_Q18_rb_"+i)).click();
						 
						 //	Do you have a policy for shift changes? 
						 wd.findElement(By.id("cphMaster_cphAstro_Q19_rb_"+i)).click();
					
					    //Portal Imaging Comments :
						 wd.findElement(By.id("cphMaster_cphAstro_txt_PortalImangSectioncmmnts")).sendKeys(sh.getRow(1).getCell(133).getStringCellValue());
						 jse.executeScript("window.scrollBy(0,200)", "");
						 //Do you perform or receive documentation of patient follow up care? 
						 wd.findElement(By.id("cphMaster_cphAstro_Q20_rb_"+i)).click();
						 
						 //Follow Up Policy Comments:
						 wd.findElement(By.id("cphMaster_cphAstro_txt_FolUpPolSectComments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
			              
						 String a="cphMaster_cphAstro_Q21_rb_"+i;
						 String b="cphMaster_cphAstro_Q21_rb_0";
						 String c="cphMaster_cphAstro_Q21_rb_1";
						 jse.executeScript("window.scrollBy(0,400)", "");
				 if(a.equals(b))
				 {
						 //A. Do you have chart rounds? 
						 wd.findElement(By.id("cphMaster_cphAstro_Q21_rb_"+i)).click();
						
						 //Who attends chart rounds
						 wd.findElement(By.id("cphMaster_cphAstro_Q21B_ch_"+i)).click();
			
						 //C. What is presented at chart rounds ?
						 wd.findElement(By.id("cphMaster_cphAstro_Q21C_ch_"+i)).click();
			
			      }
				 else
				   {
					if(a.equals(c))
					       {
							//A. Do you have chart rounds? 
							 wd.findElement(By.id("cphMaster_cphAstro_Q21_rb_"+i)).click();
							
							 //Who attends chart rounds
							 wd.findElement(By.id("cphMaster_cphAstro_Q21B_ch_"+i)).click();
				
							 //C. What is presented at chart rounds ?
							 wd.findElement(By.id("cphMaster_cphAstro_Q21C_ch_"+i)).click(); 
						    }
					  else
						  {
						 
						//A. Do you have chart rounds? 
						 wd.findElement(By.id("cphMaster_cphAstro_Q21_rb_"+i)).click();
						 
						   }
			
				    }
				
				 String e="Q22A_rb_0";
				 String f="Q22A_rb_"+i;
				 String g="Q22A_rb_1";
				 
				 if(e.equals(f))
				 {
					 //	A. Do you have periodic Quality Assurance (QA) or Continous Quality Improvement (CQI) meetings
					 wd.findElement(By.id("Q22A_rb_"+i)).click();
					 
					//B. Who attends these meetings? 
					 wd.findElement(By.id("cphMaster_cphAstro_Q22B_ch_"+i)).click();
				 }	 
				 else
				 {
					 if(g.equals(f)) 
					 {
						// A. Do you have periodic Quality Assurance (QA) or Continous Quality Improvement (CQI) meetings
						 wd.findElement(By.id("Q22A_rb_"+i)).click();
						 
						//B. Who attends these meetings? 
						 wd.findElement(By.id("cphMaster_cphAstro_Q22B_ch_"+i)).click();
				     }
						else
					    {
						// A. Do you have periodic Quality Assurance (QA) or Continous Quality Improvement (CQI) meetings
						 wd.findElement(By.id("Q22A_rb_"+i)).click(); 
					    }
				 }
				 jse.executeScript("window.scrollBy(0,200)", "");
				 String h="Q23A_rb_0";
				 String j="Q23A_rb_"+i;
				 String k="Q23A_rb_1";
				 
				 if(j.equals(h))
				 {
				// Do you have Morbidity and Mortality (M&M) conferences? 
				 wd.findElement(By.id("Q23A_rb_"+i)).click();
				 
				 //Who attends these meetings? 
				 wd.findElement(By.id("cphMaster_cphAstro_Q23B_ch_"+i)).click();
				 
				 //C. Please describe the screening criteria to capture the patients for M&M: 
				 wd.findElement(By.id("cphMaster_cphAstro_Q23c_ch_"+i)).click();
				 jse.executeScript("window.scrollBy(0,900)", "");
				 // Who screens for these criteria?
				 wd.findElement(By.id("cphMaster_cphAstro_Q23D_ch_"+i)).click(); 
				 }
				 else
				 {
					 if(j.equals(k))
					 {
					// Do you have Morbidity and Mortality (M&M) conferences? 
					 wd.findElement(By.id("Q23A_rb_"+i)).click();
					 
					 //Who attends these meetings? 
					 wd.findElement(By.id("cphMaster_cphAstro_Q23B_ch_"+i)).click();
					 
					 //C. Please describe the screening criteria to capture the patients for M&M: 
					 wd.findElement(By.id("cphMaster_cphAstro_Q23c_ch_"+i)).click();
					 jse.executeScript("window.scrollBy(0,900)", "");
					 // Who screens for these criteria?
					 wd.findElement(By.id("cphMaster_cphAstro_Q23D_ch_"+i)).click(); 
					 } 
					 else
					 {
						// Do you have Morbidity and Mortality (M&M) conferences? 
						 wd.findElement(By.id("Q23A_rb_"+i)).click(); 
						 jse.executeScript("window.scrollBy(0,900)", "");
					 }
				 }
				 
				 //Do you perform Facility Qualtiy Improvement (FQI or Focus Studies)? 
				 wd.findElement(By.id("Q24A_rb_"+i)).click(); 
				 
				 //Do you perform outcome studies? 
				 wd.findElement(By.id("Q25A_rb_"+i)).click(); 
				 
				// QA Activities Comments: 
				 wd.findElement(By.id("cphMaster_cphAstro_txt_QAActivitieSectComments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				 
				 wd.findElement(By.id("cphMaster_btnNext")).click();//next
				 log.info("End of Onsite Interview Page 3");
				 Thread.sleep(300);
				 //******************************Onsite Interview Page 4*************************
				 
				 log.info("Onsite Interview Page 4");
				 //Do you participate in protocols?
				 wd.findElement(By.id("cphMaster_cphAstro_Q23_rb_0")).click();
				 
				 // If yes, Select the following:
				 wd.findElement(By.id("cphMaster_cphAstro_Q26_ch_"+i)).click();
				 
				 //Divisional Policies Comments: 
				 wd.findElement(By.id("cphMaster_cphAstro_txt_DivisionalPolicesSectionComments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				 jse.executeScript("window.scrollBy(0,700)", "");
				 
				 // Is training and competence assessed at the time of employment? 
				 wd.findElement(By.id("cphMaster_cphAstro_Q27_rb_"+i)).click();
				 
				 //Is training and competence assessed when a new technology is introduced? 
				 wd.findElement(By.id("cphMaster_cphAstro_Q28_rb_"+i)).click();
				 jse.executeScript("window.scrollBy(0,700)", "");
				 
				 //How are new procedures/new equipment introduced and implemented into the department? 
				 wd.findElement(By.id("cphMaster_cphAstro_Q27_ch_"+i)).click();
				 
				 // Is there Annual Radiation Safety Training? 
				 wd.findElement(By.id("cphMaster_cphAstro_Q27A_rb_"+i)).click();
				 
				 //Is there an Infection Control Policy? 
				 wd.findElement(By.id("Q27B_rb_"+i)).click();
				 jse.executeScript("window.scrollBy(0,700)", "");
				 //Is there a Disaster Plan? 
				 wd.findElement(By.id("Q29_rb_"+i)).click();
				 
				 //Training and Competence of Staff Comments: 
				 wd.findElement(By.id("cphMaster_cphAstro_txt_TranngComptnceStaffCmmnts")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				 jse.executeScript("window.scrollBy(0,700)", "");
				 
				 wd.findElement(By.id("cphMaster_btnNext")).click();//next
				 log.info("End of Onsite Interview Page 4");
				 Thread.sleep(300);
				 //******************************CheckList page********************************************
				 
				 log.info("CheckList page");
				 //Time Out Policy *
				 wd.findElement(By.id("CheckListV3_1_rb_"+i)).click();
			     
				 //Contrast Policy 
				 wd.findElement(By.id("CheckListV3_2_rb_"+i)).click();
				 
				 //Imaging Portal and IGRT 
				 wd.findElement(By.id("CheckListV3_3_rb_"+i)).click();
				
				 
				 //Disaster Plan *
				 wd.findElement(By.id("CheckListV3_4_rb_"+i)).click();
				 
				
				 //Infection Control *
				 wd.findElement(By.id("CheckListV3_5_rb_"+i)).click();
				 
				 //	Policy and Procedures Comments 
				 wd.findElement(By.id("cphMaster_cphDataCollection_CheckListV1_PolicyProcedures_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				 jse.executeScript("window.scrollBy(0,300)", "");
				 
				 //Rating Scale 
				 wd.findElement(By.id("PolicyAndProceduresLikeRating_rb_2")).click();
				 jse.executeScript("window.scrollBy(0,400)", "");
				 
				 //Chart Rounds *
				 wd.findElement(By.id("CheckListV3_6_rb_"+i)).click();
				
				 //M&M *
				 wd.findElement(By.id("CheckListV3_7_rb_"+i)).click();
				
				 //Focus Studies
				 wd.findElement(By.id("CheckListV3_8_rb_"+i)).click();
				 
				 //Internal Outcome
				 wd.findElement(By.id("CheckListV3_9_rb_"+i)).click();
				 
				 //Physician Peer Review Documentation
				 wd.findElement(By.id("CheckListV3_10_rb_"+i)).click();
				  jse.executeScript("window.scrollBy(0,900)", "");
				 
				 //Physicist Peer Review Documentation
				 wd.findElement(By.id("CheckListV3_11_rb_"+i)).click();
				 
				 //QA and CQI 
				 wd.findElement(By.id("cphMaster_cphDataCollection_CheckListV1_QA_CQI_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				 jse.executeScript("window.scrollBy(0,900)", "");
				 
				 //Rating Scale
				 wd.findElement(By.id("QAAndCQILikeRating_rb_2")).click();
				 jse.executeScript("window.scrollBy(0,900)", "");
				 
				 //comment
				 wd.findElement(By.id("cphMaster_cphDataCollection_CheckListV1_CheckList_OverallComments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				 
				 wd.findElement(By.id("cphMaster_cphDataCollection_bottom_Next_btn")).click();//next
				 Thread.sleep(300);
				 log.info("End of CheckList page");
				 Ropa_pom.logout();
				 log.info("Logout");
				 Thread.sleep(500);
				 
				 	//next md login
		    		Long n=Math.round(sh.getRow(1).getCell(154).getNumericCellValue());
		    		int md =n.intValue();
		    		
		    		if(md>1)
		    		{
		    			
		    			wd.navigate().to(prop.getProperty("QA_URL"));
		    			Ropa_pom.login("pgarlapally@acr.org", "Welcome1!");
			  			log.info("login with md2");
			  			
			  			Ropa_pom.view_assigned_Application("cphMaster_btnAppliAssgndtoMe");
			  			
			  			//opening Onsite Interview Forms from left side
						
						wd.findElement(By.linkText("Onsite Interview Forms")).click();
						
						//Onsite Interview Page 1
						wd.findElement(By.linkText("Onsite Interview Page 1")).click();
						Thread.sleep(300);
						//*********************************Onsite Interview Page1***********************************
						
						wd.findElement(By.id("cphMaster_top_Next_btn")).click();//next
						Thread.sleep(300);
						log.info("Onsite Interview Page1 of md"+md);
						
						//*********************************Onsite Interview Page2***********************************
			  			
						wd.findElement(By.id("cphMaster_top_Next_btn")).click();//next
						Thread.sleep(300);
						log.info("Onsite Interview Page2 of md"+md); 
						
						//*********************************Onsite Interview Page3***********************************
			  			
						wd.findElement(By.id("cphMaster_top_Next_btn")).click();//next
						Thread.sleep(300);
						log.info("Onsite Interview Page3 of md"+md);
						
						//*********************************Onsite Interview Page4***********************************
			  			
						wd.findElement(By.id("cphMaster_top_Next_btn")).click();//next
						Thread.sleep(300);
						log.info("Onsite Interview Page4 of md"+md);
						
						 //******************************CheckList page********************************************
						
						wd.findElement(By.id("cphMaster_cphDataCollection_top_Next_btn")).click();//next
						Thread.sleep(300);
						 log.info("End of CheckList page of md"+md);
						 wd.quit();
		    		}//md
		    		else{
		    			wd.quit();
		    		}
	}catch (Exception e) {
		// TODO: handle exception
		log.error(e);
	}	  

	}
}

