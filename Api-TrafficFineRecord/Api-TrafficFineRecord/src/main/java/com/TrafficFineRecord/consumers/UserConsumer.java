package com.TrafficFineRecord.consumers;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.TrafficFineRecord.dto.UserPartialReplicaDTO;
import com.TrafficFineRecord.enums.ActionType;
import com.TrafficFineRecord.services.UserService;

@Component
public class UserConsumer {

	@Autowired
	UserService userService;

	@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${api.broker.queue.usereventqueue.name}", durable = "true"), exchange = @Exchange(value = "${api.broker.exchange.usereventexchange}", type = ExchangeTypes.FANOUT, ignoreDeclarationExceptions = "true")))

	public void listenUserEvent(@Payload UserPartialReplicaDTO userEventDto) {
		var user = userEventDto.convertToUser();

		switch (ActionType.valueOf(userEventDto.getActionType())) {
		case CREATE:
		case UPDATE:
			userService.save(user);
			break;
		}
	}
}
