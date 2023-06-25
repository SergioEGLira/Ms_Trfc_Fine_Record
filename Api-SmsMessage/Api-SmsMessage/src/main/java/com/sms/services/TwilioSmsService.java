package com.sms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import com.sms.dto.TwilioAutuacaoDTO;
import com.sms.entities.TwilioAutuacao;
import com.sms.repositories.TwilioAutuacaoRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.extern.log4j.Log4j2;

@Log4j2

public class TwilioSmsService implements SmsServiceInterf {
	
//TODO 1- TWILIO inerte
//	@Value("${twilio.sid}")
//	private String twilioSid;
//
//	@Value("${twilio.key}")
//	private String twilioKey;
//
//	@Value("${twilio.phone.from}")
//	private String twilioPhoneFrom;
//
//	@Value("${twilio.phone.to}")
//	private String twilioPhoneTo;

	@Autowired
	TwilioAutuacaoRepository twilioAutuacaoRepository;

	@Transactional
	public void insertMessageSMS(TwilioAutuacaoDTO dto) {

		TwilioAutuacao twilioAutuacao = new TwilioAutuacao();

		twilioAutuacao.setPlacaDoCarro(dto.getPlacaDoCarro());
		twilioAutuacao.setInfracao(dto.getInfracao());
		twilioAutuacao.setCpf(dto.getCpf());
		twilioAutuacao.setTelefone(dto.getTelefone());
		twilioAutuacao.setNotificationStatus(dto.getNotificationStatus());

//TODO 2- TWILIO inerte
//		String msg = "Seu automovel de placa " + dto.getPlacaDoCarro() + " foi multado pela infração: "
//				+ dto.getInfracao();
//
//		Twilio.init(twilioSid, twilioKey);
//		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
//		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);
//		Message message = Message.creator(to, from, msg).create();
//
//		System.out.println(message.getSid());
//
//		log.info("LOG - Twilio Sid da mensagem enviada = {}", message.getSid());
		log.info("LOG - Twilio-SMS enviado com sucesso!");

		twilioAutuacao = twilioAutuacaoRepository.save(twilioAutuacao);

	}
}
