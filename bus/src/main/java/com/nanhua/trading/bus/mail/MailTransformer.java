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
public class MailTransformer extends AbstractPayloadTransformer<String,MimeMessage>{
	@Autowired
	private JavaMailSender mailSender;
	
	/*
	@Transformer
	public MimeMessage doTransform(String values) throws Exception {
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
		return mm;
	}
	 */
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
			helper.setSubject("yesterday");
			helper.setTo(jsonValues.get("email"));
			helper.setText(jsonValues.get("content"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return mm;
	}

}
