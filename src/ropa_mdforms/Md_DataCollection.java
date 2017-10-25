package ropa_mdforms;

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
 * @author K RamyaSree
 *
 * QA_Ropa
 * 
* April 13, 2017
* 12:30:54 PM
 */

public class Md_DataCollection extends Ropa_pom {
	

	public static int num;
	public static int i;
	public static String user;
	public static String password;

		@Test(priority=8)
		  public void MD_DataCollection() throws InterruptedException{
		
				try{
					
					Ropa_pom.configuration();
					// Login
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
						
						List<WebElement> count=wd.findElements(By.linkText("View"));
				    	num=count.size();
						
						//***loop for multiple views***
						for( v=0;v<=num-1;v++)
						{
						wd.findElement(By.id("cphMaster_grdcases_lnkEdit_"+v)).click();
						Thread.sleep(300);
						Thread.sleep(1000);
						Long site =Math.round(sh.getRow(1).getCell(149).getNumericCellValue());
						int i =site.intValue();
				//0=yes
				//1=Recommendation
				//2-no
						if(v==1){
							i=2;
						}

						//*************************MD Data Collection*************************
						log.info("MD Data Collection");
						//Treating Physician:
						wd.findElement(By.id("cphMaster_cphDataCollection_txt_treatingphy")).sendKeys("john");
						
					//*************History and Physical/Consultation*********************
						log.info("History and Physical/Consultation");
						wd.findElement(By.id("ui-accordion-accordion-header-0")).click();
						Thread.sleep(500);
						jse.executeScript("window.scrollBy(0,500)", "");
						
	    int r=1; //for radio button
						
						String radio="cphMaster_cphDataCollection_Modalities_rb_"+r;
						String Breast="cphMaster_cphDataCollection_Modalities_rb_0"; 
						String Prostate="cphMaster_cphDataCollection_Modalities_rb_1";
						String Lung="cphMaster_cphDataCollection_Modalities_rb_2";
						String Rectosigmoid="cphMaster_cphDataCollection_Modalities_rb_3";
						String Head_Neck="cphMaster_cphDataCollection_Modalities_rb_4";
						String Other="cphMaster_cphDataCollection_Modalities_rb_5";
						
	if(radio.equals(Breast))
	{
							//breast radio button
							wd.findElement(By.id("cphMaster_cphDataCollection_Modalities_rb_0")).click();
							
							Select d = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_Breast_Breast_1_ddl")));
							d.selectByVisibleText("Definitive - Intact Breast");
							
							// 	The tumor final margin status
							wd.findElement(By.id("cphMaster_cphDataCollection_Breast_Breast_2A_rb_"+i)).click();
							
							//The estrogen receptor, progesterone receptor and Her2-new status
							wd.findElement(By.id("cphMaster_cphDataCollection_Breast_Breast_2B_rb_"+i)).click();
							
							//Tumor Size 
							wd.findElement(By.id("cphMaster_cphDataCollection_Breast_Breast_2C_rb_"+i)).click();
							
							// 	Use or non-use of oral contraceptives and/or hormone replacement therapy 
							wd.findElement(By.id("cphMaster_cphDataCollection_Breast_Breast_2D_rb_"+i)).click();
							
							//Findings of a breast examination by the Radiation Oncologist 
							wd.findElement(By.id("cphMaster_cphDataCollection_Breast_Breast_2E_rb_"+i)).click();
							jse.executeScript("window.scrollBy(0,500)", "");
							// 	Other 
							wd.findElement(By.id("cphMaster_cphDataCollection_Breast_Breast_2F_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							
							//Breast Comments:
							wd.findElement(By.id("cphMaster_cphDataCollection_Breast_Breast_Comments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());	
	} //breast
	  else
		{
		    if(radio.equals(Prostate))
				{
								//Prostate radio button
								wd.findElement(By.id("cphMaster_cphDataCollection_Modalities_rb_1")).click();
								
								//Pre-Treatment Status:
								Select d = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_Prostate_Prostate_1_ddl")));
								d.selectByVisibleText("Prostatectomy-Adjuvant");
								
								//Pre-treatment PSA 
								wd.findElement(By.id("Prostate_2A_rb_"+i)).click();
								
								//Pre-treatment erectile status
								wd.findElement(By.id("Prostate_2B_rb_"+i)).click();
								
								//The number and extent of positive cores (only for Definitive)
								wd.findElement(By.id("Prostate_2C_rb_"+i)).click();
								
								//Digital rectal exam 
								wd.findElement(By.id("Prostate_2D_rb_"+i)).click();
								
								//Gleason Score 
								wd.findElement(By.id("Prostate_2E_rb_"+i)).click();
								
								//Urinary Status
								wd.findElement(By.id("Prostate_2F_rb_"+i)).click();
								jse.executeScript("window.scrollBy(0,500)", "");
								
								// 	Was the pathology report reviewed
								wd.findElement(By.id("Prostate_3A_rb_"+i)).click();
								
								// 	Margins
								wd.findElement(By.id("Prostate_3B_i_rb_"+i)).click();
								
								// 	Capsular extensions
								wd.findElement(By.id("Prostate_3B_ii_rb_"+i)).click();
								
								//Seminal vesicles extensions 
								wd.findElement(By.id("Prostate_3B_iii_rb_"+i)).click();
								jse.executeScript("window.scrollBy(0,1500)", "");
								
								//Prostate Comments:
								wd.findElement(By.id("cphMaster_cphDataCollection_Prostate_Prostate_OverallComments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());		
		        } //Prostate
				 else
					{
						if(radio.equals(Lung))
							{
							//lung radio button
							wd.findElement(By.id("cphMaster_cphDataCollection_Modalities_rb_2")).click();
							
							//Pre-Treatment Status:
							Select d = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_Lung_Lung_1_ddl")));
							d.selectByVisibleText("Primary");
							
							//Did the Radiation Oncologist make note of the patient's pulmonary function results?
							wd.findElement(By.id("cphMaster_cphDataCollection_Lung_Lung_q2_rb_"+i)).click();
							
							// 	Did the Radiation Oncologist make note of the patient's smoking history?
							wd.findElement(By.id("cphMaster_cphDataCollection_Lung_Lung_q3_rb_"+i)).click();
							
							//Lung Comments:
							wd.findElement(By.id("cphMaster_cphDataCollection_Lung_Lung_OverallComments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());		
							} //lung
						
						else
						 {
								if(radio.equals(Rectosigmoid))
								  {
								// Rectosigmoid radio button
								wd.findElement(By.id("cphMaster_cphDataCollection_Modalities_rb_3")).click();
									
								// 	Pre-Treatment Status:
								Select d = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_Rectosigmoid_rectosogmoid_1_rb")));
								d.selectByVisibleText("Primary");
									
								//Were any of the following tests (endorectal ultrasound, MRI, PET/CT Scans, or digital 
								wd.findElement(By.id("cphMaster_cphDataCollection_Rectosigmoid_rectosogmoid_2_rb_"+i)).click();
								
								//Was a bowel sparing technique used, which could include, belly board, abdominal 
								wd.findElement(By.id("cphMaster_cphDataCollection_Rectosigmoid_rectosogmoid_3_rb_"+i)).click();
								
								//Rectosigmoid Comments:
								wd.findElement(By.id("cphMaster_cphDataCollection_Rectosigmoid_Recto_OverallComments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());			
								
								  }//Rectosigmoid
								
								 else
								  {
									 if(radio.equals(Head_Neck))
									 {
									//Head_Neck radio button
									wd.findElement(By.id("cphMaster_cphDataCollection_Modalities_rb_4")).click();  
										 
									// 	Pre-Treatment Status
									Select d = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_HeadNeck_HEADNECK_1_rb")));
									d.selectByVisibleText("Primary");
									
									//Name of the site
									Select s = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_HeadNeck_HEADNECK_2__rb_ddl")));
									s.selectByVisibleText("Hypopharynx");
									
									//Did the Radiation Oncologist make note of the smoking history?
									wd.findElement(By.id("cphMaster_cphDataCollection_HeadNeck_HEADNECK_3_rb_"+i)).click();
									
									//Did the Radiation Oncologist make note of alcohol history?
									wd.findElement(By.id("cphMaster_cphDataCollection_HeadNeck_HEADNECK_4_rb_"+i)).click();
									
									//Did the Radiation Oncologist perform an indirect exam or fiberoptic endoscopy?
									wd.findElement(By.id("cphMaster_cphDataCollection_HeadNeck_HEADNECK_5_rb_"+i)).click();
									
									//Head & Neck Comments:
									wd.findElement(By.id("cphMaster_cphDataCollection_HeadNeck_HeadNeck_OverallComments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());			
										 
									 }//Head_Neck
									  else
									   {
										 //others
										//other radio button
										wd.findElement(By.id("cphMaster_cphDataCollection_Modalities_rb_5")).click();
										
										//Site:
										wd.findElement(By.id("cphMaster_cphDataCollection_Other_OtherV3_1_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());	
										
										// 	Comments: 
										wd.findElement(By.id("cphMaster_cphDataCollection_Other_OtherV3_2_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());	
										  
									   }
								  }
						  }
						
					} // 2nd else close	
		} //main else

										// 	Is there an appropriate pathology report available for review and referenced in the H&P? 
										wd.findElement(By.id("Section1_q1_rb_"+i)).click();
										jse.executeScript("window.scrollBy(0,500)", "");
										
										//What is the histology of the cancer?
										Select s = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_Section1_q2_ddl")));
										s.selectByVisibleText("Adenocarcinoma");
										
										//Was TNM documented?
										wd.findElement(By.id("cphMaster_cphDataCollection_Section1_q3_rb_"+i)).click();
										String q="cphMaster_cphDataCollection_Section1_q3_rb_0";
										
										 if(q.equals("cphMaster_cphDataCollection_Section1_q3_rb_"+i))
										 {
											 // T : 
											 Select t = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_Section1_q3_2_ddl")));
											 t.selectByVisibleText("TX");
											// N :	
											 Select n = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_Section1_q3_3_ddl")));
											 n.selectByVisibleText("2");	
											// M :
											 Select m = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_Section1_q3_4_ddl")));
											 m.selectByVisibleText("1");	
										 }
										 
										//Was Staging document?
										 wd.findElement(By.id("cphMaster_cphDataCollection_Section1_3B_rb_"+i)).click();
										 
										 String sd="cphMaster_cphDataCollection_Section1_3B_rb_0";
										 if(sd.equals("cphMaster_cphDataCollection_Section1_3B_rb_"+i))
										 {
											 //Stage 
											 Select st = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_Section1_q3_1_ddl")));
											 st.selectByVisibleText("III");	 
										 }
										 
									  //Were risk factors for the diagnosis contained in the radiation oncologist’s history and physical examination? 
										 wd.findElement(By.id("Section1_q4_1_rb_"+i)).click(); 
										 
										//Was an appropriate examination for the diagnosis contained in the radiation oncologist’s history and physical examination? 
										 wd.findElement(By.id("Section1_q4_2_rb_"+i)).click(); 
										 
										 //Was performance status contained in the radiation oncologist’s history and physical examination?
										 wd.findElement(By.id("Section1_q4_3_rb_"+i)).click(); 
										 
										 //Was systemic therapy used/documented (Select Does not apply if systemic therapy did not pertain to patient’s treatment course) 
										 wd.findElement(By.id("Section1_q5_rb_"+i)).click(); 
										 
										 String systemic="Section1_q5_rb_0";
										 if(systemic.equals("Section1_q5_rb_"+i))
										 {
											 //If yes, Agent Name Included?
											 wd.findElement(By.id("Section1_q5_1_rb_"+i)).click(); 
											 
											 //Schedule?
											 wd.findElement(By.id("Section1_q5_2_rb_"+i)).click();
										 }
										 
										 //Is there evidence of an informed consent process with a signed document and evidence of review of side effects, complications, benefits, and alternatives with the patient?
										 wd.findElement(By.id("Section1_q6_rb_"+i)).click();
										 jse.executeScript("window.scrollBy(0,1500)", "");
										 
										 //History Physical / Consultative Comments:
										 wd.findElement(By.id("cphMaster_cphDataCollection_Section1_OverAllComments")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
										 jse.executeScript("window.scrollBy(0,500)", "");
										 
										 //Rating Scale
										 wd.findElement(By.id("HistoryLikeRating_rb_2")).click();
										 
				 //**************************Treatment Section ******************************************
										 log.info("Treatment Section");
										 wd.findElement(By.id("ui-accordion-accordion-header-1")).click();
										 Thread.sleep(500);
										 jse.executeScript("window.scrollBy(0,1500)", "");
											
										 //***************tab click*****************************
										 
										 for(int chk=0;chk<=0;chk++)
									   		{
									   		// Site/Target
									   		 wd.findElement(By.id("cphMaster_cphDataCollection_Section2_ch_"+chk)).click();
									   		Thread.sleep(300);
									   		}
									
										 //***************2D/3D tab click*****************************
										 wd.findElement(By.id("ui-id-3")).click();
										 Thread.sleep(300);
										 //Date of first treatment
										 wd.findElement(By.id("cphMaster_cphDataCollection_U2D3DV3_U2D3DV3_1_date")).click();//calendar

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
								   	   
								   		jse.executeScript("window.scrollBy(0,200)", "");
								   		
								   		for(char l='A';l<='H';l++)
								   		{
								   		// Site/Target
								   		 wd.findElement(By.id("U2D3DV3_1"+l+"_rb_"+i)).click();
								   		}
								   		jse.executeScript("window.scrollBy(0,200)", "");
								   		
								   		//Was the prescription signed and dated by radiation oncologist prior to first treatment?
								   		wd.findElement(By.id("U2D3DV3_4_rb_"+i)).click();
								   		
								   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
								   		wd.findElement(By.id("U2D3DV3_6_rb_"+i)).click();
								   		jse.executeScript("window.scrollBy(0,500)", "");
								   		
								   		//Dose(Gy)/Fraction
								   		wd.findElement(By.name("ctl00$ctl00$cphMaster$cphDataCollection$U2D3DV3$gvSites$ctl02$txtDose")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
								   		   
										//Number of Fractions
								   		wd.findElement(By.id("cphMaster_cphDataCollection_U2D3DV3_gvSites_txtFractions_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
								   		
								   		//Energy
								   		wd.findElement(By.id("cphMaster_cphDataCollection_U2D3DV3_gvSites_txtDoseGiven_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
								   		
								   		//Total Dose(Gy)
								   		wd.findElement(By.id("cphMaster_cphDataCollection_U2D3DV3_gvSites_txtTotal_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
								   		
								   		// 	Were the portal verification images obtained at the time of or immediately prior to the first treatment? *
								   		wd.findElement(By.id("U2D3DV3_5A_rb_"+i)).click();
								   		jse.executeScript("window.scrollBy(0,200)", "");
								   		
								   		//Were the portal verification images obtained when the fields were changed (e.g. at cone down/boost)
								   		wd.findElement(By.id("U2D3DV3_5B_rb_"+i)).click();
										 
								   		// 	Was portal verification obtained at least every 5-10 treatments?
								   		wd.findElement(By.id("U2D3DV3_5C_rb_"+i)).click();
								   		
								   		//Were portal verification images signed/approved/rejected/dated by radiation oncologist with timing consistent (example: before next treatment)?
								   		wd.findElement(By.id("U2D3DV3_5D_rb_"+i)).click();
								   		jse.executeScript("window.scrollBy(0,200)", "");
								   		
								   		//Were electron fields documented with a photograph or portal image?
								   		wd.findElement(By.id("U2D3DV3_5E_rb_"+i)).click();
								   		
								   		//2D3D Comments:
								   		wd.findElement(By.id("cphMaster_cphDataCollection_U2D3DV3_U2D3DV3_OverAllComments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
								   		jse.executeScript("window.scrollBy(0,200)", "");
								   		//Rating Scale
								   		wd.findElement(By.id("U2D3DLikeRating_rb_2")).click(); //Compliant=3
								   		
								   		/*jse.executeScript("window.scrollBy(0,-1500)", "");
								   		
								   	 //***********IMRT tab click************************************
									 wd.findElement(By.id("ui-id-4")).click();
									 Thread.sleep(300);
									 //Was there a written planning instructions/dose volume constraints?
									 wd.findElement(By.id("IMRTV3_1_A_rb_"+i)).click();
									 jse.executeScript("window.scrollBy(0,200)", "");
									 
									 //Did the radiation oncologist review and approve multi-slice, coronal, and sagittal images with isodose lines and DVHs?
									 wd.findElement(By.id("IMRTV3_1_C_rb_"+i)).click();
									 
									 //A. For prostate IMRT, mark the important structures that were contoured:
									 wd.findElement(By.id("IMRTV3_2_ch")).click();
									 
									 wd.findElement(By.id("IMRTV3_2A_i_ch")).click();//Bladder
									 
									 //Comments:
									 wd.findElement(By.id("cphMaster_cphDataCollection_IMRTV3_IMRTV3_2A_i_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
									 jse.executeScript("window.scrollBy(0,200)", "");
									 
									 //B.For lung cancers, mark the important structures that were contoured: 
									 wd.findElement(By.id("IMRTV3_2B_ch")).click();
									 
									 wd.findElement(By.id("IMRTV3_2B_i_ch")).click();//Brachial Plexus
									 
									//Comments:
									 wd.findElement(By.id("cphMaster_cphDataCollection_IMRTV3_IMRTV3_2B_i_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
									 jse.executeScript("window.scrollBy(0,200)", "");
									 
									 //C. For head and neck IMRT: mark the important structures that are contoured:
									 wd.findElement(By.id("IMRTV3_2C_ch")).click();
									 
									 wd.findElement(By.id("IMRTV3_2C_i_ch")).click();//i. Brachial Plexus 
									 wd.findElement(By.id("IMRTV3_2C_viii_ch")).click();//viii. Optic chiasm
									 
									//Comments:
									 wd.findElement(By.id("cphMaster_cphDataCollection_IMRTV3_IMRTV3_2C_i_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
									 jse.executeScript("window.scrollBy(0,200)", "");
									 
									// D. Other:  
									 wd.findElement(By.id("IMRTV3_2D_ch")).click();
									 
									//Comments:
									 wd.findElement(By.id("cphMaster_cphDataCollection_IMRTV3_IMRTV3_2D_i_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
									 
									 //Date of first treatment
									 wd.findElement(By.id("cphMaster_cphDataCollection_IMRTV3_IMRTV3_2C_1_date")).click();//calendar
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
							   		   
							   		jse.executeScript("window.scrollBy(0,200)", "");
							   		
							   		for(char l='A';l<='H';l++)
							   		{
							   		// Site/Target
							   		 wd.findElement(By.id("IMRTV3_2C_"+l+"_rb_"+i)).click();
							   		}
							   		jse.executeScript("window.scrollBy(0,200)", "");
							   	
							   		//Was the prescription signed and dated by radiation oncologist prior to first treatment? 
							   		wd.findElement(By.id("IMRTV3_4_rb_"+i)).click();
							   		
							   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
							   		wd.findElement(By.id("IMRTV3_6_rb_"+i)).click();
							   		
							     	//Dose(Gy)/Fraction
							   		wd.findElement(By.id("cphMaster_cphDataCollection_IMRTV3_IMRT_gvSites_IMRT_txtDose_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
							   		   
									//Number of Fractions
							   		wd.findElement(By.id("cphMaster_cphDataCollection_IMRTV3_IMRT_gvSites_IMRT_txtFractions_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
							   		
							   		//Energy
							   		wd.findElement(By.id("cphMaster_cphDataCollection_IMRTV3_IMRT_gvSites_IMRT_txtDoseGiven_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
							   		
							   		//Total Dose(Gy)
							   		wd.findElement(By.id("cphMaster_cphDataCollection_IMRTV3_IMRT_gvSites_IMRT_txtTotal_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
							   		
							   		//A. Were the portal verification images obtained at the time of or immediately prior to the first treatment? 
							   		wd.findElement(By.id("IMRTV3_6_A_rb_"+i)).click();
							   		jse.executeScript("window.scrollBy(0,500)", "");
							   		
							   		//Were the portal verification images obtained when the fields were changed (e.g. at cone down/boost) 
							   		wd.findElement(By.id("IMRTV3_6_B_rb_"+i)).click();
							   		
							   		//Was portal verification obtained at least every 5-10 treatments?
							   		wd.findElement(By.id("IMRTV3_6_C_rb_"+i)).click();
							   		
							   		//Were portal verification images signed/approved/rejected/dated by radiation oncologist with timing consistent (example: before next treatment)?
							   		wd.findElement(By.id("IMRTV3_6_D_rb_"+i)).click();
							   		
							   		//Were electron fields documented with a photograph or portal image?
							   		wd.findElement(By.id("IMRTV3_6_E_rb_"+i)).click();
							   		
							   		//IMRT Comments:
							   		wd.findElement(By.id("cphMaster_cphDataCollection_IMRTV3_IMRTV3_OverallComments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							   		
							   		//Rating Scale
							   		wd.findElement(By.id("IMRTLikeRating_rb_2")).click(); //Compliant=3
							   		
							   		jse.executeScript("window.scrollBy(0,-1600)", "");
							   		
							   			//***********IGRT tab click************************************
									 wd.findElement(By.id("ui-id-5")).click();
									 Thread.sleep(300);
								    //Did the Radiation Oncologist document patient specific instructions for image guidance in the chart? 
									 wd.findElement(By.id("IGRTV3_1_rb_"+i)).click();
									 
									 //Were the IGRT images approved, signed and dated before the next treatment?
									 wd.findElement(By.id("IGRTV3_2_rb_"+i)).click();
									 
									 //IGRT Comments:
									 wd.findElement(By.id("cphMaster_cphDataCollection_IGRTV3_IGRTV3_OverallComments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
									 
									 //Rating Scale
									 wd.findElement(By.id("IGRTLikeRating_rb_2")).click(); //Compliant=3
									 
									 jse.executeScript("window.scrollBy(0,-200)", "");
									 
										//***********SBRT  tab click************************************
									 wd.findElement(By.id("ui-id-6")).click();
									 Thread.sleep(300);
									 //Was there a written planning instructions/dose volume constraints? 
									 wd.findElement(By.id("SBRTV3_1_A_rb_"+i)).click();
									 
									 //Did the radiation oncologist review and approve multi-slice, coronal, and sagittal images with isodose lines and DVHs?
									 wd.findElement(By.id("SBRTV3_2_A_rb_"+i)).click();
									 Thread.sleep(300);
									 String browser=prop.getProperty("Browser");
									  if(browser.equalsIgnoreCase("IE")){
									jse.executeScript("window.scrollBy(0,100)", "");
									  }
									 //***********SBRT Lung Stereotactic Body Radiation Therapy check***********
									 wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_A_ch")).click();
									 
									 Thread.sleep(300);
									 //Did the target volume receive greater than 95% of the prescribed dose? 
									 wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_A_i_rb_0")).click();
									 
									 //Was the lesion treated peripheral or central in location? 
									 wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_A_ii_rb_0")).click();
									 jse.executeScript("window.scrollBy(0,500)", "");
									 
									 //Was respiratory motion accounted for using gating or by creating an ITV?
									 wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_A_iii_rb_"+i)).click();
									 
									 //Date of first treatment
									 wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_A_1_date")).click();//calendar
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
							   		jse.executeScript("window.scrollBy(0,200)", "");
							   		
							   	   //*******iteration for Did the prescription include the following elements:*******
							   		for(char rb='A';rb<='H';rb++)
							   		{
							   		
							   		// Site/Target
							   		 wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_A_"+rb+"_rb_"+i)).click();
							   		
							   		}
							   		jse.executeScript("window.scrollBy(0,200)", "");
							   		
							   		//Was the prescription signed and dated by radiation oncologist prior to first treatment? 
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_A_4_rb_"+i)).click();
							   		
							   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_A_6_rb_"+i)).click();
							   		
							     	//Dose(Gy)/Fraction
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRT1_gvSites_SBRT1_txtDose_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
							   		   
									//Number of Fractions
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRT1_gvSites_SBRT1_txtFractions_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
							   		
							   		//Energy
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRT1_gvSites_SBRT1_txtDoseGiven_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
							   		
							   		//Total Dose(Gy)
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRT1_gvSites_SBRT1_txtTotal_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
							   		
							   		//***********SBRT Spine Stereotactic Body Radiation Therapy***********
									 wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_B_ch")).click();
									 
									 //Did the target volume receive greater than 90% of the prescribed dose?
									 wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_B_i_rb_"+i)).click();
									 
									 //How was the cord identified ?
									 wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_B_ii_rb_0")).click();//CT mylegram
									 jse.executeScript("window.scrollBy(0,300)", "");
									//Date of first treatment
									 wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_B_1_date")).click();//calendar
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
							   		jse.executeScript("window.scrollBy(0,200)", "");
							   		
							   	//*******iteration for Did the prescription include the following elements:*******
							   		for(char rb='A';rb<='H';rb++)
							   		{
							   		
							   		// Site/Target
							   		 wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_B_"+rb+"_rb_"+i)).click();
							   		
							   		}
							   		
							   		jse.executeScript("window.scrollBy(0,200)", "");
							   		
							   		//Was the prescription signed and dated by radiation oncologist prior to first treatment? 
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_B_4_rb_"+i)).click();
							   		
							   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_B_6_rb_"+i)).click();
							   		
							     	//Dose(Gy)/Fraction
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRT2_gvSites_SBRT2_txtDose_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
							   		   
									//Number of Fractions
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRT2_gvSites_SBRT2_txtFractions_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
							   		
							   		//Energy
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRT2_gvSites_SBRT2_txtDoseGiven_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
							   		
							   		//Total Dose(Gy)
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRT2_gvSites_SBRT2_txtTotal_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
							   		
							   	//***********************checkbox looping from C to E********************************************
							   		int k=3;
							 for(char x='C';x<='E';x++)
							   {
							   		
							   		//***********SBRT chech box***********
									 wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_"+x+"_ch")).click();
									 jse.executeScript("window.scrollBy(0,400)", "");
									//Date of first treatment
									 wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_"+x+"_1_date")).click();//calendar
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
							   		jse.executeScript("window.scrollBy(0,100)", "");
							   		
							   		for(char l='A';l<='H';l++)
							   		{
							   		// Site/Target
							   		 wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_"+x+"_"+l+"_rb_"+i)).click();
							   		}
							   		jse.executeScript("window.scrollBy(0,300)", "");
							   		
							   		//Was the prescription signed and dated by radiation oncologist prior to first treatment? 
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_"+x+"_4_rb_"+i)).click();
							   		
							   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_"+x+"_6_rb_"+i)).click();
							   		
							   		
	                               while(k<=5)
							   		{
							     	//Dose(Gy)/Fraction
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRT"+k+"_gvSites_SBRT"+k+"_txtDose_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
							   		   
									//Number of Fractions
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRT"+k+"_gvSites_SBRT"+k+"_txtFractions_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
							   		
							   		//Energy
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRT"+k+"_gvSites_SBRT"+k+"_txtDoseGiven_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
							   		
							   		//Total Dose(Gy)
							   		wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRT"+k+"_gvSites_SBRT"+k+"_txtTotal_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
							   		break;
							   		}
	                           		++k;	   		
						      }   	
							 
							 //SBRT Comments:
							 wd.findElement(By.id("cphMaster_cphDataCollection_SBRTV3_SBRTV3_OverallComments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							 jse.executeScript("window.scrollBy(0,200)", "");
						   	//Rating Scale
						   	wd.findElement(By.id("SBRTLikeRating_rb_2")).click(); //Compliant=3
						   		
						   	jse.executeScript("window.scrollBy(0,-1500)", "");
						   	jse.executeScript("window.scrollBy(0,-1500)", "");
						   	jse.executeScript("window.scrollBy(0,-1000)", "");
						
						    //***********SRS  tab click************************************
							 wd.findElement(By.id("ui-id-7")).click();	
							 Thread.sleep(300);
							 //	Was there a written planning instructions/dose volume constraints?
							 wd.findElement(By.id("SRSV3_1_A_rb_"+i)).click();
							 
							 // Did the radiation oncologist review and approve multi-slice, coronal, and sagittal images with isodose lines and DVHs?
							 wd.findElement(By.id("SRSV3_2_A_rb_"+i)).click();
								 
							 //Is this a brain metastasis case?
							 wd.findElement(By.id("SRSv3_1_rb_1")).click();
							 
							 //Was the conformity index documented ? 
							 wd.findElement(By.id("SRSv3_2_rb_0")).click();
							 
							 //Was the course of treatment appropriate: including volume, dose fractionation and total dose?
							 wd.findElement(By.id("SRSv3_4A_rb_"+i)).click();
							
							 //Was the isocernter verified?
							 wd.findElement(By.id("SRSv3_5A_rb_"+i)).click();
							 
							 ////Date of first treatment
							 wd.findElement(By.id("cphMaster_cphDataCollection_SRSV3_SRSv3_1_date")).click();//calendar
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
					   		jse.executeScript("window.scrollBy(0,300)", "");
					   		
					   		// Site/Target
					   		 wd.findElement(By.id("SRSv3_3A_rb_"+i)).click();
					   		
					   		//*******iteration for Did the prescription include the following elements:*******
					   		for(char rb='B';rb<='H';rb++)
					   		{
					   		
					   		// Site/Target
					   		 wd.findElement(By.id("SRSv3_1"+rb+"_rb_"+i)).click();
					   		
					   		}
					   		jse.executeScript("window.scrollBy(0,400)", "");
					   		
					   		//Was the prescription signed and dated by radiation oncologist prior to first treatment? 
					   		wd.findElement(By.id("SRSv3_3_4_rb_"+i)).click();
					   		
					   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
					   		wd.findElement(By.id("SRSv3_3_6_rb_"+i)).click();
					   		
					     	//Dose(Gy)/Fraction
					   		wd.findElement(By.id("cphMaster_cphDataCollection_SRSV3_SRS_gvSites_SRS_txtDose_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
					   		   
							//Number of Fractions
					   		wd.findElement(By.id("cphMaster_cphDataCollection_SRSV3_SRS_gvSites_SRS_txtFractions_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
					   		
					   		//Energy
					   		wd.findElement(By.id("cphMaster_cphDataCollection_SRSV3_SRS_gvSites_SRS_txtDoseGiven_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
					   		
					   		//Total Dose(Gy)
					   		wd.findElement(By.id("cphMaster_cphDataCollection_SRSV3_SRS_gvSites_SRS_txtTotal_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
					   		
					   		//SRS Comments:
					   	    wd.findElement(By.id("cphMaster_cphDataCollection_SRSV3_SRSV3_OverallComments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
					   	 jse.executeScript("window.scrollBy(0,200)", "");
					   	    //Rating Scale
					   	    wd.findElement(By.id("SRSLikeRating_rb_2")).click(); //Compliant=3
					   	    jse.executeScript("window.scrollBy(0,-1000)", "");
					   	    
					   	  //******************************LDR tab click************************************
							 wd.findElement(By.id("ui-id-8")).click();
							 Thread.sleep(300);
							 int ldr=1;
							 
							//*************Gyn check from A to B iteration**********
			for(char l='A';l<='B';l++)
					{
							 //*****Gyn check **********
								 wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR"+l+"_ch")).click();
								 
							//Date of first treatment
							 wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDR"+l+"_1_txt")).click();//calendar
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
					   		jse.executeScript("window.scrollBy(0,300)", "");
					   		
					   		for(char lr='A';lr<='H';lr++)
					   		{
					   		// Site/Target
					   			
					   		 wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDR"+l+"_"+lr+"_rb_"+i)).click();
					   		}
					   		jse.executeScript("window.scrollBy(0,200)", "");
					   		
					   		//Was the prescription signed and dated by radiation oncologist prior to first treatment?
					   		wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDR"+l+"_4_rb_"+i)).click();
					   		
					   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
					   		wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDR"+l+"_6_rb_"+i)).click();
					   		while(ldr<=5)
					   		{
					   		//Dose(Gy)/Fraction cphMaster_cphDataCollection_LDRV3_LDR1_gvSites_LDR1_txtDose_0
					   		wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR"+ldr+"_gvSites_LDR"+ldr+"_txtDose_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
					   		   
							//Number of Fractions
					   		wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR"+ldr+"_gvSites_LDR"+ldr+"_txtFractions_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
					   		
					   		//Energy
					   		wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR"+ldr+"_gvSites_LDR"+ldr+"_txtDoseRate_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
					   		
					   		//Prescription	Point
					   		Select d = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR"+ldr+"_gvSites_LDR"+ldr+"_ddlPrescriptionPoint_0")));
							d.selectByVisibleText("Surface");
					   		
					   		//Total Dose(Gy)
					   		wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR"+ldr+"_gvSites_LDR"+ldr+"_txtTotal_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
					   		break;
					   		}
	                   		++ldr;
	                   		
	                   		jse.executeScript("window.scrollBy(0,300)", "");
					   		//What isotope was used for treatment? *
					   		Select treat = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDR"+l+"_I_rb")));
							treat.selectByVisibleText("CS137");
							
							// 	Was the dose to the bladder determined?
							wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDR"+l+"_II_rb_"+i)).click();
							
							//Was the dose to the rectum determined?
							wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDR"+l+"_III_rb_"+i)).click();   	    
					}
			
							//*************Gyn Brachytherapy-Definitive Endometrial Cancer (no hysterectomy) check**********
							wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRC_ch")).click();
			 
							//Date of first treatment
							wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDRC_1_txt")).click();//calendar
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
							jse.executeScript("window.scrollBy(0,300)", "");
		   		
							for(char lr='A';lr<='H';lr++)
							{
								// Site/Target
		   			
								wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDRC_"+lr+"_rb_"+i)).click();
							}
		   		
								jse.executeScript("window.scrollBy(0,300)", "");
		   		
								//Was the prescription signed and dated by radiation oncologist prior to first treatment?
								wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDRC_4_rb_"+i)).click();
		   		
								//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
								wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDRC_6_rb_"+i)).click();
								
								//Dose(Gy)/Fraction 
								wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR3_gvSites_LDR3_txtDose_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
		   		   
								//Number of Fractions
								wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR3_gvSites_LDR3_txtFractions_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
		   		
								//DoseRate
								wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR3_gvSites_LDR3_txtDoseRate_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
		   		
								//Prescription	Point
								Select d = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR3_gvSites_LDR3_ddlPrescriptionPoint_0")));
								d.selectByVisibleText("Surface");
		   		
								//Total Dose(Gy)
								wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR3_gvSites_LDR3_txtTotal_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
								
								jse.executeScript("window.scrollBy(0,200)", "");
								
								//What isotope was used for treatment?
								Select treat = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDRC_II_rb")));
								treat.selectByVisibleText("CS137");
								
								//Was this? 
								wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDRC_I_rb_0")).click();
				
								// 	Was the dose to the bladder determined?
								wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDRC_III_rb_"+i)).click();
				
								//Was the dose to the rectum determined?
								wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDRC_IV_rb_"+i)).click();
								
								 int num=4;
								 
									//*************Gyn check from d to E iteration**********
					for(char l='D';l<='E';l++)
							{
									 //*****Gyn check **********
										 wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR"+l+"_ch")).click();
										 
									//Date of first treatment
									 wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDR"+l+"_1_txt")).click();//calendar
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
							   		jse.executeScript("window.scrollBy(0,400)", "");
							   		
							   		for(char lr='A';lr<='H';lr++)
							   		{
							   		// Site/Target
							   			
							   		 wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDR"+l+"_"+lr+"_rb_"+i)).click();
							   		}
							   		jse.executeScript("window.scrollBy(0,200)", "");
							   		
							   		//Was the prescription signed and dated by radiation oncologist prior to first treatment?
							   		wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDR"+l+"_4_rb_"+i)).click();
							   		
							   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
							   		wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDR"+l+"_6_rb_"+i)).click();
							   		while(num<=5)
							   		{
							   		//Dose(Gy)/Fraction cphMaster_cphDataCollection_LDRV3_LDR1_gvSites_LDR1_txtDose_0
							   		wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR"+num+"_gvSites_LDR"+num+"_txtDose_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
							   		   
									//Number of Fractions
							   		wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR"+num+"_gvSites_LDR"+num+"_txtFractions_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
							   		
							   		//Energy
							   		wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR"+num+"_gvSites_LDR"+num+"_txtDoseRate_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
							   		
							   		//Prescription	Point
							   		Select point = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR"+num+"_gvSites_LDR"+num+"_ddlPrescriptionPoint_0")));
									point.selectByVisibleText("Surface");
							   		
							   		//Total Dose(Gy)
							   		wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDR"+num+"_gvSites_LDR"+num+"_txtTotal_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
							   		break;
							   		}
			                   		++num;
			                   		
			                   		jse.executeScript("window.scrollBy(0,200)", "");
							   		//What isotope was used for treatment? *
							   		Select treatment = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDR"+l+"_II_rb")));
									treatment.selectByVisibleText("CS137");
									
									//Was this? 
									wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRControlV3_LDR"+l+"_I_rb_0")).click();	  	    
							}
									//LDR Comments:
									wd.findElement(By.id("cphMaster_cphDataCollection_LDRV3_LDRV3_OverallComments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
									jse.executeScript("window.scrollBy(0,300)", "");
									
									//Rating Scale
									wd.findElement(By.id("LDRLikeRating_rb_2")).click(); //Compliant=3
				   		
									jse.executeScript("window.scrollBy(0,-1500)", "");
									jse.executeScript("window.scrollBy(0,-2700)", "");
									
									//***********HDR  tab click************************************
									 wd.findElement(By.id("ui-id-9")).click();	
									 
									 Thread.sleep(300);
							   		//*************Gyn check from B to C iteration**********
							   		
	for(char l='A';l<='E';l++)
			{
					
								String id="string"+l;
								String id1="cphMaster_cphDataCollection_HDRControlV3_HDR"+l+"_gvSites_HDR"+l+"_txtDose_0";
								
							   			//*****Gyn check **********
									 wd.findElement(By.id("cphMaster_cphDataCollection_HDRControlV3_HDR"+l+"_ch")).click();
									 if(l!='A'){
									 jse.executeScript("window.scrollBy(0,200)", "");
									 }
									 //Date of first treatment
									 wd.findElement(By.id("cphMaster_cphDataCollection_HDRControlV3_HDRControlV3_HDR"+l+"_1_txt")).click();//calendar
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
						   		jse.executeScript("window.scrollBy(0,200)", "");
						   		
						   		for(char lr='A';lr<='H';lr++)
						   		{
						   		// Site/Target
						   			//HDRControlV3_HDRB_A_rb_0
						   		 wd.findElement(By.id("HDRControlV3_HDR"+l+"_"+lr+"_rb_"+i)).click();
						   		}
						   		jse.executeScript("window.scrollBy(0,300)", "");
						   		
						   		if(id.equals("stringC")) //for option c
						   		{
						   		//Was the prescription signed and dated by radiation oncologist prior to first treatment?
							   		wd.findElement(By.id("cphMaster_cphDataCollection_HDRControlV3_HDRControlV3_HDRC_4_rb_"+i)).click();
							   		
							   	//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
							   		wd.findElement(By.id("HDRControlV3_HDRC_6_rb_"+i)).click();
						   		}
						   		else{
						   			
						   		//Was the prescription signed and dated by radiation oncologist prior to first treatment?
						   		wd.findElement(By.id("HDRControlV3_HDR"+l+"_4_rb_"+i)).click();
						   		
						   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
						   		wd.findElement(By.id("HDRControlV3_HDR"+l+"_6_rb_"+i)).click();
						   			}
						   		
						   		if(id.equals("stringA"))
						   		{
						   		//Does the patient meet the suitable criteria for partial breast brachytherapy 
						   			wd.findElement(By.id("HDRControlV3_HDRA_I_rb_"+i)).click();	
						   		}
						   		
						   		//Dose(Gy)/Fraction
						   		wd.findElement(By.id("cphMaster_cphDataCollection_HDRControlV3_HDR"+l+"_gvSites_HDR"+l+"_txtDose_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
						   		   
								//Number of Fractions
						   		wd.findElement(By.id("cphMaster_cphDataCollection_HDRControlV3_HDR"+l+"_gvSites_HDR"+l+"_txtFractions_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
						   		
						   		//DoseRate
						   		wd.findElement(By.id("cphMaster_cphDataCollection_HDRControlV3_HDR"+l+"_gvSites_HDR"+l+"_txtDoseRate_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
						   		
						   		//Prescription	Point
						   		Select pt = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_HDRControlV3_HDR"+l+"_gvSites_HDR"+l+"_ddlPrescriptionPoint_0")));
								pt.selectByVisibleText("Surface");
						   		
						   		//Total Dose(Gy)
						   		wd.findElement(By.id("cphMaster_cphDataCollection_HDRControlV3_HDR"+l+"_gvSites_HDR"+l+"_txtTotal_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
						   		
						   		if(id.equals("stringA"))
						   		{
						   			jse.executeScript("window.scrollBy(0,100)", "");
						   		    //What isotope was used for treatment? 
							   		Select treatment = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_HDRControlV3_HDRControlV3_HDRA_II_rb")));
							   		treatment.selectByVisibleText("CS137");
							   		
							   		//What applicator/technique was used?
							   		Select app = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_HDRControlV3_HDRControlV3_HDRA_II_ddl")));
							   		app.selectByVisibleText("Single channel balloon device");
							   		
							   		//If treatments were given twice a day, was the time between treatments six hours or more?
							   		wd.findElement(By.id("HDRControlV3_HDRA_III_rb_"+i)).click();	
						   		}
					else{
						   		
							if(id.equals("stringB"))
						   		{
								jse.executeScript("window.scrollBy(0,100)", "");
						   		//What isotope was used for treatment? *
						   		Select t = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_HDRControlV3_HDRControlV3_HDRB_6_i_rb")));
								t.selectByVisibleText("CS137");
								
								//Was the dose to the bladder determined?
								wd.findElement(By.id("HDRControlV3_HDR"+l+"_6_ii_rb_"+i)).click();
								
								//Was the dose to the rectum determined?
								wd.findElement(By.id("HDRControlV3_HDR"+l+"_6_iii_rb_"+i)).click();
						   		}
						   		else
						   		{  
						   			if(id.equals("stringC"))
						   			{
						   				jse.executeScript("window.scrollBy(0,100)", "");
						   			//What isotope was used for treatment? 
							   		Select t = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_HDRControlV3_HDRControlV3_HDR"+l+"_i_rb")));
									t.selectByVisibleText("CS137");	
									
									//Was the dose to the bladder determined?
									wd.findElement(By.id("HDRControlV3_HDR"+l+"_ii_rb_"+i)).click();
									
									//Was the dose to the rectum determined?
									wd.findElement(By.id("HDRControlV3_HDR"+l+"_iii_rb_"+i)).click(); 
						   			}
						   			else 
						   			{
						   				if(id.equals("stringD"))
						   				{
						   					jse.executeScript("window.scrollBy(0,100)", "");
						   				//What isotope was used for treatment? 
								   		Select t = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_HDRControlV3_HDRControlV3_HDRD_ii_rb")));
										t.selectByVisibleText("CS137");	
										
										//Was this?
										wd.findElement(By.id("HDRControlV3_HDRD_i_rb_0")).click();
										
										//Was the dose to the bladder determined?
										wd.findElement(By.id("HDRControlV3_HDRD_iii_rb_"+i)).click();
										
										//Was the dose to the rectum determined?
										wd.findElement(By.id("HDRControlV3_HDRD_iv_rb_"+i)).click(); 	
						   				} 
						   				else
						   				{
						   					jse.executeScript("window.scrollBy(0,100)", "");
						   				//What isotope was used for treatment? 
									   	Select t = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_HDRControlV3_HDRControlV3_HDRE_ii_rb")));
										t.selectByVisibleText("CS137");	
											
										//Was this?
										wd.findElement(By.id("HDRControlV3_HDRE_i_rb_0")).click();
											
										//What applicator was used?
										wd.findElement(By.id("cphMaster_cphDataCollection_HDRControlV3_HdrEiiichk1")).click();
												
						   				}
						   			}	
						   		
						   	}		   		
					}
			}
								jse.executeScript("window.scrollBy(0,200)", "");
								
						   		//HDR Comments:
						   		wd.findElement(By.id("cphMaster_cphDataCollection_HDRControlV3_HDRControlV3_OverallComments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						   		jse.executeScript("window.scrollBy(0,300)", "");
						   		
						   		//Rating Scale
						   		wd.findElement(By.id("HDRLikeRating_rb_2")).click(); //Compliant=3
						   		
						   		jse.executeScript("window.scrollBy(0,-1500)", "");
							   	jse.executeScript("window.scrollBy(0,-1500)", "");
							   	jse.executeScript("window.scrollBy(0,-1500)", "");
							
						   	   //**********************Seed Implant   tab click************************************
								 wd.findElement(By.id("ui-id-10")).click();	
								 Thread.sleep(300);
								 
								//Date of first treatment
								 wd.findElement(By.id("cphMaster_cphDataCollection_UCSeedImplimented_SEEDV3_2C_1_date")).click();//calendar
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
					   		jse.executeScript("window.scrollBy(0,200)", "");
					   		
					   		for(char lr='A';lr<='H';lr++)
					   		{
					   		// Site/Target
					   			//HDRControlV3_HDRB_A_rb_0
					   		 wd.findElement(By.id("SEEDV3_2C_"+lr+"_rb_"+i)).click();
					   		}
					   		jse.executeScript("window.scrollBy(0,300)", "");
					   		
					   		//Was the prescription signed and dated by radiation oncologist prior to first treatment?
					   		wd.findElement(By.id("cphMaster_cphDataCollection_UCSeedImplimented_SEEDV3_2C_4_rb_"+i)).click();
					   		
					   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
					   		wd.findElement(By.id("cphMaster_cphDataCollection_UCSeedImplimented_SEEDV3_6_rb_"+i)).click();
					   		
					   		//Dose(Gy)/Fraction
					   		wd.findElement(By.id("cphMaster_cphDataCollection_UCSeedImplimented_SD_gvSites_SD_txtDose_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
					   		   
							//Number of Fractions
					   		wd.findElement(By.id("cphMaster_cphDataCollection_UCSeedImplimented_SD_gvSites_SD_txtFractions_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
					   		
					   		//DoseRate
					   		wd.findElement(By.id("cphMaster_cphDataCollection_UCSeedImplimented_SD_gvSites_SD_txtDoseRate_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
					   		
					   		//Prescription	Point
					   		Select pt = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_UCSeedImplimented_SD_gvSites_SD_ddlPrescriptionPoint_0")));
							pt.selectByVisibleText("Surface");
					   		
					   		//Total Dose(Gy)
					   		wd.findElement(By.id("cphMaster_cphDataCollection_UCSeedImplimented_SD_gvSites_SD_txtTotal_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
					   		
					   		//Was this?
							wd.findElement(By.id("cphMaster_cphDataCollection_UCSeedImplimented_SEEDV3_6_i_rb_0")).click();
							
							//What isotope was used for treatment? 
					   		Select t = new Select(wd.findElement(By.id("cphMaster_cphDataCollection_UCSeedImplimented_SEEDV3_6_ii_rb")));
							t.selectByVisibleText("CS137");
							
							//When was the post implant CT scan done? 
							wd.findElement(By.id("cphMaster_cphDataCollection_UCSeedImplimented_SEEDV3_6_iii_rb_0")).click();
							
							//What was the D90 
							wd.findElement(By.id("cphMaster_cphDataCollection_UCSeedImplimented_SEEDV3_6_iv_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							
							//. What was the V100 
							wd.findElement(By.id("cphMaster_cphDataCollection_UCSeedImplimented_SEEDV3_6_v_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						   	
							//Seed Implant Comments:
							wd.findElement(By.id("cphMaster_cphDataCollection_UCSeedImplimented_SeedImplant_OverallComments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							jse.executeScript("window.scrollBy(0,300)", "");
							
						   	//Rating Scale	
							wd.findElement(By.id("SeedImplantLikeRating_rb_2")).click(); //Compliant=3	
						   		
						 	jse.executeScript("window.scrollBy(0,-1000)", "");
						   	
						  //**********************Protons tab click************************************  
						   	wd.findElement(By.id("ui-id-11")).click();	
						   	Thread.sleep(300);
						   	
						   	//Did the radiation oncologist review and approve the volumes including the PTV
						   	wd.findElement(By.id("PROTON_1_A_rb_"+i)).click();
						   	
						   	// Was there a written planning instructions/dose volume constraints?
						   	wd.findElement(By.id("PROTON_1_B_rb_"+i)).click();
						   	
						   	//Did the radiation oncologist review and approve multi-slice, coronal, and sagittal images with isodose lines and DVHs?
						   	wd.findElement(By.id("PROTON_1_C_rb_"+i)).click();
						   	jse.executeScript("window.scrollBy(0,200)", "");
						   	
						   	//How was the dose prescribed?
						   	wd.findElement(By.id("cphMaster_cphDataCollection_Protons_V3_PROTON_1_D_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						   	
						   	//Is ICRU-78 Used? 
						   	wd.findElement(By.id("PROTON_1_E_rb_0")).click();
						   	
						   	// Is RBE included?
							wd.findElement(By.id("PROTON_1_F_rb_0")).click();
							
							//What was the RBE value? 
							wd.findElement(By.id("cphMaster_cphDataCollection_Protons_V3_PROTON_1_G_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							
							//***********For prostate protons, mark the important structures that were contoured? check *****************
							wd.findElement(By.id("PROTON_2_ch")).click();
							jse.executeScript("window.scrollBy(0,200)", "");
							
							//i. Bladder
							wd.findElement(By.id("PROTON_2A_i_ch")).click();
							//v. Rectal volume including contents
							wd.findElement(By.id("PROTON_2A_v_ch")).click();
							//Comments:
							wd.findElement(By.id("cphMaster_cphDataCollection_Protons_V3_PROTON_2A_i_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							
							//***********B. For lung cancers, mark the important structures that were contoured? check *****************
							wd.findElement(By.id("PROTON_2B_ch")).click();
							jse.executeScript("window.scrollBy(0,200)", "");
						   	//i. Brachial Plexus
							wd.findElement(By.id("PROTON_2B_i_ch")).click();
							//Comments:
							wd.findElement(By.id("cphMaster_cphDataCollection_Protons_V3_PROTON_2B_i_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							
							//***********C. For head and neck IMRT: mark the important structures that are contoured? check *****************	
							wd.findElement(By.id("PROTON_2_C_ch")).click();
							//i. Brachial Plexus
							wd.findElement(By.id("PROTON_2C_1_ch")).click();
							//v. Constrictor Muscles/Upper Esophagus
							wd.findElement(By.id("PROTON_2C_iv_ch")).click();
							//Comments:
							wd.findElement(By.id("cphMaster_cphDataCollection_Protons_V3_PROTON_2C_i_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							jse.executeScript("window.scrollBy(0,200)", "");
							//********************D.Other  check *************************
							wd.findElement(By.id("PROTON_2_D_ch")).click();
							//Comments:
							wd.findElement(By.id("cphMaster_cphDataCollection_Protons_V3_PROTON_2_D_i_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
							
							//Date of first treatment
							 wd.findElement(By.id("cphMaster_cphDataCollection_Protons_V3_PROTON_A_1_txt")).click();//calendar
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
				   		 wd.findElement(By.id("cphMaster_cphDataCollection_Protons_V3_PROTON_"+lr+"_rb_"+i)).click();
				   		}
				   		jse.executeScript("window.scrollBy(0,200)", "");
				   		
				   		//Was the prescription signed and dated by radiation oncologist prior to first treatment?
				   		wd.findElement(By.id("cphMaster_cphDataCollection_Protons_V3_PROTON_4_rb_"+i)).click();
				   		
				   		//Was the course of treatment appropriate: including volume, dose fractionation and total dose?
				   		wd.findElement(By.id("cphMaster_cphDataCollection_Protons_V3_PROTON_6_rb_"+i)).click();
				   		
				   		//Dose(Gy)/Fraction
				   		wd.findElement(By.id("cphMaster_cphDataCollection_Protons_V3_Protons_gvSites_PROTONS_txtDose_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(137).getNumericCellValue())));
				   		   
						//Number of Fractions
				   		wd.findElement(By.id("cphMaster_cphDataCollection_Protons_V3_Protons_gvSites_PROTONS_txtFractions_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(138).getNumericCellValue())));
				   		
				   		//DoseRate
				   		wd.findElement(By.id("cphMaster_cphDataCollection_Protons_V3_Protons_gvSites_PROTONS_txtDoseGiven_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(139).getNumericCellValue())));
				   		
				   		//Total Dose(Gy)
				   		wd.findElement(By.id("cphMaster_cphDataCollection_Protons_V3_Protons_gvSites_PROTONS_txtTotal_0")).sendKeys(String.valueOf(Math.round(sh.getRow(1).getCell(140).getNumericCellValue())));
				   		jse.executeScript("window.scrollBy(0,200)", "");
				   		
				   		for(char lt='A';lt<='D';lt++)
				   		{
				   		wd.findElement(By.id("cphMaster_cphDataCollection_Protons_V3_PROTON_6"+lt+"_rb_"+i)).click();
				   		}
				   		jse.executeScript("window.scrollBy(0,200)", "");
				   		
				   		//Protons Comments:
				   		wd.findElement(By.id("cphMaster_cphDataCollection_Protons_V3_Protons_OverallComments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
						jse.executeScript("window.scrollBy(0,400)", "");	
						   		
						//Rating Scale
				   		wd.findElement(By.id("ProtonsLikeRating_rb_2")).click(); //Compliant=3
*/				   		jse.executeScript("window.scrollBy(0,200)", "");
				   		//************************Patient Evaluation tab******************************************
				   		log.info("Patient Evaluation tab");
						wd.findElement(By.id("ui-accordion-accordion-header-2")).click();
						Thread.sleep(300);
						// Is there evidence of a progress note performed for each five fractions of therapy? (Answer Does not Apply if patient received less than 5 fractions)
						wd.findElement(By.id("Section3_q1_rb_"+i)).click();
						
						//Was accumulated dose included? 
						wd.findElement(By.id("Section3_q3_rb_"+i)).click();
						jse.executeScript("window.scrollBy(0,200)", "");
						
						//Was tolerance to treatment documented?
						wd.findElement(By.id("Section3_q4_rb_"+i)).click();
						
						// Was there documentation of interruption, continuation or conclusion of therapy?
						wd.findElement(By.id("Section3_q5_rb_"+i)).click();
						
						//Is this patient on treatment?
						wd.findElement(By.id("Section3_q6_rb_0")).click();
						
						//Patient Evaluation Comments:
				   		wd.findElement(By.id("cphMaster_cphDataCollection_Section3_OverallComments_txt")).sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
				   		jse.executeScript("window.scrollBy(0,900)", "");	
						//Rating Scale
				   		wd.findElement(By.id("PateintEvaluationLikeRating_rb_2")).click(); //Compliant=3 
				   		
				   		
				   		//************************Patient Evaluation tab******************************************
						//wd.findElement(By.id("ui-accordion-accordion-header-2")).click();
				   		Thread.sleep(300);
				   		wd.findElement(By.id("cphMaster_cphDataCollection_bottom_Next_btn")).click(); //next
				   		Thread.sleep(300);
				   		log.info("End of MD Data Collection");
						}//v loop
				   		
				   		wd.findElement(By.id("cphMaster_Next")).click(); //next
				   		Thread.sleep(300);
				}
				   		catch (Exception e) {
							// TODO: handle exception
							log.error(e);
						}	   		 		
				   		
	}//method
		   		
				   		//*********************Physician Final Report V3*********************
		@Test(priority=10)
		public void phy_final_report() throws InterruptedException{
			
			try{
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
				   		
			}
			catch (Exception e) {
				// TODO: handle exception
				log.error(e);
			}	
			    		
			    		//next md login
			    		Long n=Math.round(sh.getRow(1).getCell(154).getNumericCellValue());
			    		int md =n.intValue();
			    		
			    		if(md>1)
			    		{
			    			
			    			wd.navigate().to(prop.getProperty("QA_URL"));
			    			Ropa_pom.login("pgarlapally@acr.org", "Welcome1!");
				  			log.info("login with md2");
				  			
				  			Ropa_pom.view_assigned_Application("cphMaster_btnAppliAssgndtoMe");
				  			
				  		//opening MD DataCollection Forms from left side
							wd.findElement(By.linkText("MD DataCollection Forms")).click();
							
							//MD Data Collection
							wd.findElement(By.linkText("MD Data Collection")).click();
							
							List<WebElement> count=wd.findElements(By.linkText("View"));
					    	num=count.size();
							
							//***loop for multiple views***
							for( v=0;v<=num-1;v++)
							{
							wd.findElement(By.id("cphMaster_grdcases_lnkEdit_"+v)).click();
							Thread.sleep(300);
							Thread.sleep(1000);
							Long site =Math.round(sh.getRow(1).getCell(149).getNumericCellValue());
							int i =site.intValue();
					//0=yes
					//1=Recommendation
					//2-no
							if(v==1){
								i=2;
							}

							//*************************MD Data Collection*************************
							log.info("MD Data Collection of md"+md);
							wd.findElement(By.id("cphMaster_cphDataCollection_top_Next_btn")).click();
								
							}//v loop
							
							wd.findElement(By.id("cphMaster_Next")).click(); //next
					   		Thread.sleep(300);
					   		
					   		//*******************************Physician Final Report V3************************
					   		
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
				    		wd.quit();
			    		
		}else
		{
			wd.quit();
		}
			
			 
		}//method
	}//class
