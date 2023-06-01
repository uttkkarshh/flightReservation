package com.utkarsh.flightreservation.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import   org.springframework.mail.javamail.*;
import org.springframework.stereotype.Component;



import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.File;
@Component
public class EmailUtil {
	@Value("${com.utkarsh.flightreservation.itinerary.email.body}")
	private String EMAIL_BODY;
	
	@Value("${com.utkarsh.flightreservation.itinerary.email.subject}") 
	private  String EMAIL_SUBJECT; 
	private static final Logger LOGGER=LoggerFactory.getLogger(EmailUtil.class);
	 @Autowired
	    private JavaMailSender j;
	public void sendItinerary(String toAddress,String filePath) {
		
		MimeMessage message=j.createMimeMessage();
		
		
		
		try {
			MimeMessageHelper helper=new MimeMessageHelper(message,true);
			helper.setTo(toAddress);
			helper.setSubject(EMAIL_SUBJECT);
			helper.setText(EMAIL_BODY);
			helper.addAttachment("Itinerary", new File(filePath));
			j.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Failed To sendEmail due to:" +e.getMessage()+e);
		}
		
	}
}
