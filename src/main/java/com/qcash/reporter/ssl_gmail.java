package com.qcash.reporter;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.qcash.testbase.*;
import com.qcash.utilities.Util;

public class ssl_gmail extends TestBase {

	private static Properties emailconfig = null;
	static Logger logger = LogManager.getLogger(ssl_gmail.class);
	

	public static void main(String args[]) throws Exception {
		
		System.out.println("System current dir is: "+System.getProperty("user.dir")+"//src//main//resources//email_config.properties");
		emailconfig = new Properties();		
		FileInputStream ec =new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//email_config.properties");
		emailconfig.load(ec);
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		System.out.println("program reached 1");
		//props.put("mail.smtp.connectiontimeout", "5000"); // 5 seconds timeout

		//props.setProperty("mail.smtp.timeout", "50000");
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				//return new PasswordAuthentication("","");
				return new PasswordAuthentication(emailconfig.getProperty("sender_email"), emailconfig.getProperty("sender_email_password"));
			}
		});
		
		Util.summaryScreenShot();
		System.out.println("program reached 2");		
		try {
			CONFIG= new Properties();			
			FileInputStream fn =new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//confi.properties");			
			CONFIG.load(fn);	
		    System.out.println("program reached 3");			
			final String reportPath = System.getProperty("user.dir")+"//reports//Summary.png";
			System.out.println(reportPath);
			System.out.println("program reached 4");
			
			final Date date = new Date();
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailconfig.getProperty("sender_email")));
			System.out.println("EmailRecipients="+CONFIG.getProperty("EmailRecipient"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(CONFIG.getProperty("EmailRecipient")));
			
			message.setSubject(emailconfig.getProperty("email_subject") +" - "+ date);
			
			//message.setText("Hi All," + "\n\n Please find the Automation Test Results below");
			message.setText(emailconfig.getProperty("email_text1"));
			
			BodyPart messageBodyPar = new MimeBodyPart();
			messageBodyPar.setContent(
					emailconfig.getProperty("email_content1")+" <br/> <br/> <br/> \n\n <img src='cid:image-id'  alt='Automation Report SummaryPage' height='350' width='750'> <br> <br>"
//							+"Steps for opening the detailed reports on "+environment(en)+":<br> \n"
							+ "<br> <br> \n\n\n"
							+ "Please follow the below steps to analyze the artifacts attached with this email. <br> \n"
							+ "<br> <br> \n\n\n"
							+"1. Unzip the attached "+emailconfig.getProperty("email_testsummaryzipname")+" file.   <br> \n"
							+"2. Open the extent.html file in a web browser.  <br> \n"
							+"3. The dashboard provides information about the total number of test methods run, the passed and failed test methods, the time taken for each method as well as the entire suite run, the reason for failure along with detailed logs, and other details that serve as a health status check for the application under test.<br> \n"
							+"4. Also, if there are failures in the test run, a screenshot zip folder would be attached with the mail. This contains snapshots of the screens where the scripts had failed. <br> \n"
							+ "<br> <br> \n\n\n"
							+ emailconfig.getProperty("email_signature"), "text/html");
			// create the message part 
			MimeBodyPart messageBodyPart = new MimeBodyPart();

			//fill message
			messageBodyPart.setText(emailconfig.getProperty("email_text1"));

			// Summary
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPar);
			final DataSource fds = new FileDataSource(System.getProperty("user.dir")+"/reports/Summary.png");
			System.out.println("program reached 5");
			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "<image-id>");
			//messageBodyPart.setFileName("Summary.png");
			messageBodyPart.setFileName(emailconfig.getProperty("email_summary_filename"));
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			System.out.println("Summary inserted");

			// Report
			try
			{
				messageBodyPart = new MimeBodyPart();
				//String filename =reportPathSearch();
				DataSource source = new FileDataSource(System.getProperty("user.dir")+"//reports//Report.zip");
				System.out.println("program reached 6");
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(emailconfig.getProperty("email_testsummaryzipname"));
				multipart.addBodyPart(messageBodyPart);
				message.setContent(multipart);
				System.out.println("Report ZIP attached");
			}
			catch(Exception e)
			{
				System.out.println("Error while attaching Report ZIP : "+e.getMessage());
			}
			// Snapshot
			messageBodyPart = new MimeBodyPart();
			String file = reportPathSearchSnaps();
			File f=new File(System.getProperty("user.dir")+"//screenshots");
			String s[]=f.list();
			if(s.length>0)
			{
				for (String name : s) 
				{
					System.out.println("fileorfolder:"+name);
					if(name.contains("Screenshots.zip"))
					{
						DataSource so = new FileDataSource(System.getProperty("user.dir")+"//screenshots//Screenshots.zip");
						System.out.println("program reached 7");
						messageBodyPart.setDataHandler(new DataHandler(so));
						messageBodyPart.setFileName(file);
						multipart.addBodyPart(messageBodyPart);
						message.setContent(multipart);
						System.out.println("Screenshot ZIP attached");
					}
				}
			}
			else
			{
				System.out.println("program reached 7");
				System.out.println("No Screenshot ZIP available");
			}

			// Output files
/*			messageBodyPart = new MimeBodyPart();
			file = reportPathSearchOutputs();
			f=new File(System.getProperty("user.dir")+"//QCashBDD//outputfiles");
			//f=new File("./reports");
			String s1[]=f.list();
			if(s1.length>0)
			{
				for (String name : s1) 
				{
					if(name.contains("ResultFile.zip"))
					{
						DataSource so = new FileDataSource(System.getProperty("user.dir")+"//QCashBDD//outputfiles//ResultFile.zip");
						//DataSource so = new FileDataSource(System.getProperty("user.dir")+"\\reports\\Summary.png");
						messageBodyPart.setDataHandler(new DataHandler(so));
						messageBodyPart.setFileName(file);
						multipart.addBodyPart(messageBodyPart);
						message.setContent(multipart);
						System.out.println("ResultFile attached");
					}
				}
			}

			else
			{
				System.out.println("No Result file");
			}
			*/
			// End of output files

			// Put parts in message
			Transport.send(message);

			System.out.println("Mail Sent");

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
			fin = new File(System.getProperty("user.dir")+"//screenshots");
			
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

	public static String reportPathSearchOutputs()
	{ 
		String reportFilepath=null;
		File fin = null;
		try {
			fin = new File(System.getProperty("user.dir")+"//outputfiles");
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
}

