package com.sms.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.dto.TwilioAutuacaoDTO;
import com.sms.services.SmsServiceInterf;

@RestController
@RequestMapping(value = "/twilioSms")
public class TwilioSmsResource {

	@Autowired
	private SmsServiceInterf smsServiceInterf;

	@PostMapping("/sendSMS")
	public void insert(@RequestBody TwilioAutuacaoDTO dto) {
		smsServiceInterf.insertMessageSMS(dto);
	}
}
