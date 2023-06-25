package com.sms.services;

import com.sms.dto.TwilioAutuacaoDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MockSmsService implements SmsServiceInterf {

	public void insertMessageSMS(TwilioAutuacaoDTO dto) {
		log.info("LOG - Simulation, was sent Mock SMS!");
	}
}