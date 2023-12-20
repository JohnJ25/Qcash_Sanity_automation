package com.qcash.reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.Test;

import com.qcash.testbase.TestBase;
import com.qcash.utilities.Util;
public class QaLocalssl_gmail extends TestBase {

	@Test
	public static void sendEmail() throws Exception {
		initialize();
		driver.quit();
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
								//587
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("<yourmailID>","<yourPassword>");
			}
		});

		Util.summaryScreenShot();
		try {
			CONFIG= new Properties();
			FileInputStream fn =new FileInputStream(System.getProperty("user.dir")+"//confi.properties");
			CONFIG.load(fn);
			final String reportPath =System.getProperty("user.dir")+"/testng-xslt/index.html";
			final Date date = new Date();
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("<yourmailID>"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("<recepientMailID>"));
			
			message.setSubject("Real Appeal Automation Report - ( "+ environment(en)+" | "+CONFIG.get("browser")+" | "+v+" ) "+ date);
			message.setText("Hi All," + "\n\n Please find the Real Appeal Automation Test Results below");

			BodyPart messageBodyPar = new MimeBodyPart();
			messageBodyPar.setContent(
					"Hi,<br/> <br/> \n Please find the Validated Automated Test Results below <br/> <br/> <br/> \n\n <img src='cid:image-id'  alt='Automation Report SummaryPage' height='350' width='750'> <br> <br>"
							+"Steps for open the detailed reports on "+environment(en)+"<br> \n"
							+"1.Unzip the attached Vulcan O/P file.   <br> \n"
							+"2.Open the index.html file in available browser.  <br> \n"
							+ "<br> <br> \n\n\n"
							+"For Juit Report Unzip Junit file and open package-summary.html \n"
							+ "<br> <br> \n\n\n"
							+ "Vulcan Automation", "text/html");


			// create the message part 
			MimeBodyPart messageBodyPart = new MimeBodyPart();

			//fill message
			messageBodyPart.setText("Hi All," +
					"\n\n Please find the Vulcan Automation Test Results below");

			// Summary
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPar);
			final DataSource fds = new FileDataSource(System.getProperty("user.dir")+"\\Reports\\Summary.png");
			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "<image-id>");
			messageBodyPart.setFileName(System.getProperty("user.dir")+"\\Reports\\Summary.png");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			System.out.println("Summary");

			// Report
			messageBodyPart = new MimeBodyPart();
			String filename =reportPathSearch();
			DataSource source = new FileDataSource(System.getProperty("user.dir")+"\\Report.zip");
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			System.out.println("Report");

			// Snapshot
			messageBodyPart = new MimeBodyPart();
			String file =reportPathSearchSnaps();
			File f=new File(System.getProperty("user.dir")+"\\screenshots");
			String s[]=f.list();
			System.out.println(s.length);
			if(s.length>0)
			{
				for (String name : s) 
				{
					System.out.println(name);
					if(name.contains("Snapshot.zip"))
					{
						DataSource so = new FileDataSource(System.getProperty("user.dir")+"\\screenshots\\Snapshot.zip");
						messageBodyPart.setDataHandler(new DataHandler(so));
						messageBodyPart.setFileName(file);
						multipart.addBodyPart(messageBodyPart);
						message.setContent(multipart);
						System.out.println("Snapshot");
					}
				}
			}
			else
			{
				System.out.println("No Snapshot");
			}
			
			
			// Junit Zip
			messageBodyPart = new MimeBodyPart();
			String jUnitfile =reportPathSearchSnapsJunit(); // ("user.dir")+"\\Junit"
			File fil =new File(System.getProperty("user.dir"));
			String sList[]=fil.list();
			System.out.println(sList.length);
			if(sList.length>0)
			{
				for (String name1 : sList) 
				{
					System.out.println(name1);
					if(name1.contains("Junit.zip"))
					{
						DataSource so = new FileDataSource(System.getProperty("user.dir")+"\\Junit.zip");
						messageBodyPart.setDataHandler(new DataHandler(so));
						messageBodyPart.setFileName(jUnitfile);
						multipart.addBodyPart(messageBodyPart);
						message.setContent(multipart);
						System.out.println("Junit Report");
					}
				}
			}
			else
			{
				System.out.println("No Junit Report");
			}

			// Put parts in message
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	public static String reportPathSearch()
	{ 
		String reportFilepath=null;
		File fin = null;
		try {
			fin = new File(System.getProperty("user.dir"));
			String rep[] = fin.list();
			for (String reports : rep) {

				if (reports.endsWith(".zip")) {
					reportFilepath = reports;
					//System.out.println(reportFilepath);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportFilepath;
	}

	public static String reportPathSearchSnaps()
	{ 
		String reportFilepath=null;
		File fin = null;
		try {
			fin = new File(System.getProperty("user.dir")+"\\screenshots");
			String rep[] = fin.list();
			for (String reports : rep) {

				if (reports.endsWith(".zip")) {
					reportFilepath = reports;
					//System.out.println(reportFilepath);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportFilepath;
	}
	
	// Junit
	
	public static String reportPathSearchSnapsJunit()
	{ 
		String reportFilepath=null;
		File fin = null;
		try {
			fin = new File(System.getProperty("user.dir"));
			String rep[] = fin.list();
			for (String junitreports : rep) {

				if (junitreports.endsWith("Junit.zip")) {
					reportFilepath = junitreports;
					//System.out.println(reportFilepath);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportFilepath;
	}
	
	
}

