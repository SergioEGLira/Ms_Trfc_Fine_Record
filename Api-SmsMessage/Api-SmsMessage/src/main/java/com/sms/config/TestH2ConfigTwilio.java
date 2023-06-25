package com.sms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sms.services.SmsServiceInterf;
import com.sms.services.TwilioSmsService;

@Configuration
@Profile("testH2TwilioSms")
public class TestH2ConfigTwilio {

	@Bean
	public SmsServiceInterf smsServiceInterf() {
		return new TwilioSmsService();
	}

}
