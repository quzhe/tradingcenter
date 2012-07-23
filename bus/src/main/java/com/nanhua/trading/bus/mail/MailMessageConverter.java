package com.nanhua.trading.bus.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MailMessageConverter extends AbstractMessageConverter {
	@Autowired
	private JavaMailSender mailSender;
	@Override
	protected Message createMessage(Object object,
			MessageProperties messageProperties) {
		// TODO Auto-generated method stub
		String values = new String(((Message)object).getBody());
		
		MimeMessage mm = mailSender.createMimeMessage();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> jsonValues;
		try {
			jsonValues = mapper.readValue(values, HashMap.class);
			MimeMessageHelper helper = new MimeMessageHelper(mm);
			helper.setFrom("aaa@gmail.com");
			helper.setSubject("sfffffffffff");
			helper.setTo(jsonValues.get("email"));
			helper.setText(jsonValues.get("content"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return null;
		
	}

	@Override
	public Object fromMessage(Message message)
			throws MessageConversionException {
		String values = new String(message.getBody());
		MimeMessage mm = mailSender.createMimeMessage();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> jsonValues;
		try {
			jsonValues = mapper.readValue(values, HashMap.class);
			MimeMessageHelper helper = new MimeMessageHelper(mm);
			helper.setFrom("aaa@gmail.com");
			helper.setSubject("sfffffffffff");
			helper.setTo(jsonValues.get("email"));
			helper.setText(jsonValues.get("content"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new MimeMailMessage(mm);
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	

	

}
