package com.sms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sms.services.MockSmsService;
import com.sms.services.SmsServiceInterf;

@Configuration
@Profile("testMockSms")
public class TestConfigSMSMock {

	@Bean
	public SmsServiceInterf smsServiceInterf() {
		return new MockSmsService();
	}
}
