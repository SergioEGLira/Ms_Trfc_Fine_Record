package com.sms.services;

import com.sms.dto.TwilioAutuacaoDTO;

public interface SmsServiceInterf {

	void insertMessageSMS(TwilioAutuacaoDTO dto);
}
