package com.sms.consumers;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.sms.dto.TwilioAutuacaoDTO;
import com.sms.entities.TwilioAutuacao;
import com.sms.enums.NotificationStatus;
import com.sms.services.SmsServiceInterf;

@Component
public class NotificationConsumer {

	@Autowired

	SmsServiceInterf smsServiceInterf;

	@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${api.broker.queue.notificationCommandQueue.name}", durable = "true"), exchange = @Exchange(value = "${api.broker.exchange.notificationCommandExchange}", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"), key = "${api.broker.key.notificationCommandKey}"))

	public void listen(@Payload TwilioAutuacaoDTO twilioAutuacaoDTO) {
		var twilioAutuacao = new TwilioAutuacao();

		BeanUtils.copyProperties(twilioAutuacaoDTO, twilioAutuacao);
		twilioAutuacaoDTO.setNotificationStatus(NotificationStatus.CREATED);
		smsServiceInterf.insertMessageSMS(twilioAutuacaoDTO);
	}
}
