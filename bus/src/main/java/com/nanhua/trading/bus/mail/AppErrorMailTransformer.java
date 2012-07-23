package com.nanhua.trading.bus.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.AbstractPayloadTransformer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.fasterxml.jackson.databind.ObjectMapper;

//@MessageEndpoint
public class AppErrorMailTransformer extends AbstractPayloadTransformer<String,MimeMessage>{
	@Autowired
	private JavaMailSender mailSender;
	
	private String to;
	private String subject;
	//private String ;
	
	
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	protected MimeMessage transformPayload(String payload) throws Exception {
		MimeMessage mm = mailSender.createMimeMessage();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> jsonValues;
		try {
			jsonValues = mapper.readValue(payload, HashMap.class);
			MimeMessageHelper helper = new MimeMessageHelper(mm);
			helper.setFrom("quzhe1978@sina.cn");
			helper.setSubject(subject);
			helper.setTo(to);
			helper.setText(payload);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return mm;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
}
