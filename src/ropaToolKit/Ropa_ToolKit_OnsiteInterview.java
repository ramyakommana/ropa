package ropaToolKit;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import ropa_mdforms.Md_OnsiteForms;

/**
 * @author KRamyaSree
 *
 *         QA_Ropa May 30, 2017 3:37:11 PM
 */
public class Ropa_ToolKit_OnsiteInterview extends Ropa_pom {

	@Test(priority = 7)
	public void login() throws InterruptedException {
		try {
			Ropa_pom.configuration();
			// Login
			Ropa_pom.login(sh.getRow(1).getCell(2).getStringCellValue(), sh.getRow(1).getCell(1).getStringCellValue());
			log.info("Login with Facility");
		}

		catch (Exception e) {
			log.error(e);
		}
	}

	@Test(priority = 8)
	public void Onsite_interview_forms() throws InterruptedException {
		try {

			// opening toolkit from left side
			wd.findElement(By.linkText("ROPA Tool Kit")).click();
			wd.findElement(By.linkText("Onsite Interview 1 V3")).click();
			Thread.sleep(300);
			
			// *********************************Onsite Interview 1// V3***********************************
			log.info("Ropatoolkit: Onsite Interview 1 V3");
			Long site = Math.round(sh.getRow(1).getCell(149).getNumericCellValue());
			int i = site.intValue();
			// 0=yes
			// 1=Recommendation
			// 2-no

			// 1.Do you offer your patients a psycho-social assessment
			wd.findElement(By.id("Q1_rb_1")).click(); // recommendation

			wd.findElement(By.id("cphMaster_cphSelfassement_txt_Consultseccomments")).sendKeys("text");
			JavascriptExecutor jse = (JavascriptExecutor) wd;
			jse.executeScript("window.scrollBy(0,200)", "");

			// 2. Do you have a time out policy and procedure for simulation?
			wd.findElement(By.id("Q2_rb_" + i)).click(); // recommendation

			// 3.Do you use contrast?

			wd.findElement(By.id("cphMaster_cphSelfassement_Q3_rb_0")).click(); // yes
			// Who screens the patient *
			wd.findElement(By.id("Q3A_ch_" + i)).click(); // Radiation Therapist

			jse.executeScript("window.scrollBy(0,700)", "");
			// What do you screen for?
			// i. GFR *
			wd.findElement(By.id("cphMaster_cphSelfassement_Q3b_i_rb_" + i)).click(); // yes
			// Allergies *
			wd.findElement(By.id("cphMaster_cphSelfassement_Q3b_ii_rb_" + i)).click(); // recommendation
			// Creatinine *
			wd.findElement(By.id("cphMaster_cphSelfassement_Q3b_iii_rb_" + i)).click(); // recommendation
			// Contrast within 24 plus hours*
			wd.findElement(By.id("cphMaster_cphSelfassement_Q3b_iv_rb_" + i)).click(); // yes

			jse.executeScript("window.scrollBy(0,700)", "");
			// 4.Is there a formal written simulation order used in the
			// department for the following:

			// IV contrast (Surveyor to confirm on Checklist Section)
			wd.findElement(By.id("Q4A_rb_" + i)).click(); // yes
			// . Oral contrast (Surveyor to confirm on Checklist Section)
			wd.findElement(By.id("cphMaster_cphSelfassement_Q4B_rb_" + i)).click(); // recommendation
			// Treatment site
			wd.findElement(By.id("cphMaster_cphSelfassement_Q4C_rb_" + i)).click(); // recommendation
			// Patient position
			wd.findElement(By.id("cphMaster_cphSelfassement_Q4D_rb_" + i)).click(); // recommendation
			// Immobilization device
			wd.findElement(By.id("cphMaster_cphSelfassement_Q4E_rb_" + i)).click(); // doesnot
																					// apply

			jse.executeScript("window.scrollBy(0,700)", "");
			// Simulation Comments:
			wd.findElement(By.id("cphMaster_cphSelfassement_txt_SimulSecComments"))
					.sendKeys(sh.getRow(1).getCell(122).getStringCellValue());

			jse.executeScript("window.scrollBy(0,700)", "");
			wd.findElement(By.id("cphMaster_btnNext")).click();// next
			Thread.sleep(300);
			log.info("Ropatoolkit: End of Onsite Interview 1 V3");

			// ********************************Onsite Interview 2// V3***************************
			log.info("Ropatoolkit: Onsite Interview 2 V3");
			// wd.findElement(By.linkText("Onsite Interview Page 2")).click();

			// Once the simulation is completed, does the radiation oncologist
			// draw the target, GTV, and CTV?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q5_rb_" + i)).click();// yes

			// Does the radiation oncologist review the Organs At Risk (OAR) if
			// someone else contours them?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q6_rb_" + i)).click();// recommendation

			// For IMRT patients, is there a written order for dose volume
			// constraints prescribed by the Radiation Oncologist?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q7_rb_" + i)).click();// recommendation

			// Is there a formal Physician peer review of dose volume
			// constraints?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q8_rb_" + i)).click();// recommendation

			// Is there a formal Physician peer review of target volumes and
			// organs at risk (segmentation)?
			
			wd.findElement(By.id("cphMaster_cphSelfassement_Q9_rb_" + i)).click();// yes
			jse.executeScript("window.scrollBy(0,500)", "");
			// Does the Radiation Oncologist review and approve the treatment
			// plan prior to first delivery?
			wd.findElement(By.id("Q10_rb_" + i)).click();// does not apply

			// Does the radiation oncologist sign/date the prescription before
			// the first treatment?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q11_rb_" + i)).click(); // yes

			// Is a radiation oncologist within the radiation oncology
			// department during treatment?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q12_rb_" + i)).click(); // recommendation

			// Treatment Planning Comments:
			wd.findElement(By.id("cphMaster_cphSelfassement_txt_PlannningseSectionCommnts"))
					.sendKeys(sh.getRow(1).getCell(131).getStringCellValue());

			// Patient Treatment
			// A. Do you have a policy that requires you to perform a time out
			// prior to each treatment?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q13a_rb_" + i)).click();
			  jse.executeScript("window.scrollBy(0,3000)", "");
			// If yes, do you check for correct patient, correct site, correct
			// energy and accessories (if any)
			wd.findElement(By.id("cphMaster_cphSelfassement_Q13b_rb_" + i)).click();

			// Is it documented?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q13c_rb_" + i)).click();

			// Do you have a policy for emergency treatment of patients after
			// business hours?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q14_rb_" + i)).click();

			// Patient Treatment Comments:
			wd.findElement(By.id("cphMaster_cphSelfassement_txt_PatientTreatmentSectionComments"))
					.sendKeys(sh.getRow(1).getCell(132).getStringCellValue());

			jse.executeScript("window.scrollBy(0,700)", "");
			wd.findElement(By.id("cphMaster_btnNext")).click();// next
			log.info("Ropatoolkit: End of Onsite Interview 2 V3");
			Thread.sleep(300);
			
			// ****************************Onsite Interview 3// V3****************************
			log.info("Ropatoolkit: Onsite Interview 3 V3");
			// Is there a policy for obtaining patient imaging?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q15_rb_" + i)).click();

			// Do you have a policy for shift changes?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q16_rb_" + i)).click();

			// Portal Imaging Comments :
			wd.findElement(By.id("cphMaster_cphSelfassement_txt_PortalImangSectioncmmnts"))
					.sendKeys(sh.getRow(1).getCell(133).getStringCellValue());
			jse.executeScript("window.scrollBy(0,200)", "");
			// Do you perform or receive documentation of patient follow up
			// care?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q17_rb_" + i)).click();

			// Follow Up Policy Comments:
			wd.findElement(By.id("cphMaster_cphSelfassement_txt_FolUpPolSectComments"))
					.sendKeys(sh.getRow(1).getCell(134).getStringCellValue());

			String a = "cphMaster_cphSelfassement_Q18_rb_" + i;
			String b = "cphMaster_cphSelfassement_Q18_rb_0";
			String c = "cphMaster_cphSelfassement_Q18_rb_1";
			jse.executeScript("window.scrollBy(0,400)", "");
			if (a.equals(b)) {
				// A. Do you have chart rounds?
				wd.findElement(By.id("cphMaster_cphSelfassement_Q18_rb_" + i)).click();

				// Who attends chart rounds
				wd.findElement(By.id("cphMaster_cphSelfassement_Q18B_ch_" + i)).click();

				// C. What is presented at chart rounds ?
				wd.findElement(By.id("cphMaster_cphSelfassement_Q18c_ch_" + i)).click();

			} else {
				if (a.equals(c)) {
					// A. Do you have chart rounds?
					wd.findElement(By.id("cphMaster_cphSelfassement_Q18_rb_" + i)).click();

					// Who attends chart rounds
					wd.findElement(By.id("cphMaster_cphSelfassement_Q18B_ch_" + i)).click();

					// C. What is presented at chart rounds ?
					wd.findElement(By.id("cphMaster_cphSelfassement_Q18c_ch_" + i)).click();
				} else {

					// A. Do you have chart rounds?
					wd.findElement(By.id("cphMaster_cphSelfassement_Q18_rb_" + i)).click();

				}

			}

			String e = "Q19A_rb_0";
			String f = "Q19A_rb_" + i;
			String g = "Q19A_rb_1";

			if (e.equals(f)) {
				// A. Do you have periodic Quality Assurance (QA) or Continous
				// Quality Improvement (CQI) meetings
				wd.findElement(By.id("Q19A_rb_" + i)).click();

				// B. Who attends these meetings?
				wd.findElement(By.id("cphMaster_cphSelfassement_Q19B_ch_" + i)).click();
			} else {
				if (g.equals(f)) {
					// A. Do you have periodic Quality Assurance (QA) or
					// Continous Quality Improvement (CQI) meetings
					wd.findElement(By.id("Q19A_rb_" + i)).click();

					// B. Who attends these meetings?
					wd.findElement(By.id("cphMaster_cphSelfassement_Q19B_ch_" + i)).click();
				} else {
					// A. Do you have periodic Quality Assurance (QA) or
					// Continous Quality Improvement (CQI) meetings
					wd.findElement(By.id("Q19A_rb_" + i)).click();
				}
			}
			jse.executeScript("window.scrollBy(0,200)", "");
			String h = "Q23A_rb_0";
			String j = "Q23A_rb_" + i;
			String k = "Q23A_rb_1";

			if (j.equals(h)) {
				// Do you have Morbidity and Mortality (M&M) conferences?
				wd.findElement(By.id("Q20A_rb_" + i)).click();

				// Who attends these meetings?
				wd.findElement(By.id("cphMaster_cphSelfassement_Q20B_ch_" + i)).click();

				// C. Please describe the screening criteria to capture the
				// patients for M&M:
				wd.findElement(By.id("cphMaster_cphSelfassement_Q20c_ch_" + i)).click();

				// Who screens for these criteria?
				wd.findElement(By.id("cphMaster_cphSelfassement_Q20D_ch_" + i)).click();
			} else {
				if (j.equals(k)) {
					// Do you have Morbidity and Mortality (M&M) conferences?
					wd.findElement(By.id("Q20A_rb_" + i)).click();

					// Who attends these meetings?
					wd.findElement(By.id("cphMaster_cphSelfassement_Q20B_ch_" + i)).click();

					// C. Please describe the screening criteria to capture the
					// patients for M&M:
					wd.findElement(By.id("cphMaster_cphSelfassement_Q20c_ch_" + i)).click();
					 jse.executeScript("window.scrollBy(0,900)", "");
					// Who screens for these criteria?
					wd.findElement(By.id("cphMaster_cphSelfassement_Q20D_ch_" + i)).click();
				} else {
					// Do you have Morbidity and Mortality (M&M) conferences?
					wd.findElement(By.id("Q20A_rb_" + i)).click();
					jse.executeScript("window.scrollBy(0,900)", "");
					
				}
			}
		
			// Do you perform Facility Qualtiy Improvement (FQI or Focus
			// Studies)?
			wd.findElement(By.id("Q21_rb_" + i)).click();

			// Do you perform outcome studies?
			wd.findElement(By.id("Q22_rb_" + i)).click();

			// QA Activities Comments:
			wd.findElement(By.id("cphMaster_cphSelfassement_txt_QAActivitieSectComments"))
					.sendKeys(sh.getRow(1).getCell(134).getStringCellValue());

			wd.findElement(By.id("cphMaster_btnNext")).click();// next
			log.info("Ropatoolkit: End of Onsite Interview 3 V3");
			Thread.sleep(300);
			
			// ************************Onsite Interview Page 4********************************
			log.info("Ropatoolkit: Onsite Interview Page 4");
			// Do you participate in protocols?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q23_rb_0")).click();

			// If yes, Select the following:
			wd.findElement(By.id("cphMaster_cphSelfassement_Q23_ch_" + i)).click();

			// Divisional Policies Comments:
			wd.findElement(By.id("cphMaster_cphSelfassement_txt_DivisionalPolicesSectionComments"))
					.sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
			jse.executeScript("window.scrollBy(0,700)", "");

			// Is training and competence assessed at the time of employment?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q24_rb_" + i)).click();

			// Is training and competence assessed when a new technology is
			// introduced?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q25_rb_" + i)).click();
			jse.executeScript("window.scrollBy(0,700)", "");

			// How are new procedures/new equipment introduced and implemented
			// into the department?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q26_ch_0")).click();

			// Is there Annual Radiation Safety Training?
			wd.findElement(By.id("cphMaster_cphSelfassement_Q27_rb_" + i)).click();

			// Is there an Infection Control Policy?
			wd.findElement(By.id("Q28_rb_" + i)).click();
			jse.executeScript("window.scrollBy(0,700)", "");
			// Is there a Disaster Plan?
			wd.findElement(By.id("Q29_rb_" + i)).click();

			// Training and Competence of Staff Comments:
			wd.findElement(By.id("cphMaster_cphSelfassement_txt_TranngComptnceStaffCmmnts"))
					.sendKeys(sh.getRow(1).getCell(134).getStringCellValue());
			jse.executeScript("window.scrollBy(0,700)", "");

			wd.findElement(By.id("cphMaster_btnNext")).click();// next
			Thread.sleep(300);
			log.info("Ropatoolkit: End of Onsite Interview Page 4");

			wd.quit();
		} catch (Exception e) {
			log.error(e);
		}
	}

}