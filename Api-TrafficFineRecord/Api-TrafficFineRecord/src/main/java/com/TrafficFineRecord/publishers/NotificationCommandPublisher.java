package com.TrafficFineRecord.publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.TrafficFineRecord.dto.NotificationCommandDTO;

@Component
public class NotificationCommandPublisher {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Value(value = "${api.broker.exchange.notificationCommandExchange}")
	private String notificationCommandExchange;

	@Value(value = "${api.broker.key.notificationCommandKey}")
	private String notificationCommandKey;

	public void publishNotificationCommand(NotificationCommandDTO notificationCommandDto) {
		rabbitTemplate.convertAndSend(notificationCommandExchange, notificationCommandKey, notificationCommandDto);
	}

}
